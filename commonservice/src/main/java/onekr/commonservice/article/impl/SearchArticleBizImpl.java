package onekr.commonservice.article.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import onekr.commonservice.article.dao.ArticleDao;
import onekr.commonservice.article.intf.SearchArticleBiz;
import onekr.commonservice.model.Article;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

@Service
public class SearchArticleBizImpl implements SearchArticleBiz {
	@Value("#{systemConfig['searcher.indexDir']}")
	public String indexDir;
	@Autowired
	private ArticleDao articleDao;

	public int indexFile(List<Article> articles)
			throws IOException {
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35,
				new StandardAnalyzer(Version.LUCENE_35));
		conf.setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(
				FSDirectory.open(new File(indexDir)), conf);
		for (Article article : articles) {
			Document doc = new Document();
			doc.add(new Field("id", String.valueOf(article.getId()),
					Field.Store.YES, Field.Index.NO));// Do not index the field
														// value
			
			if (!StringUtils.isEmpty(article.getTitle()))
				doc.add(new Field("title", article.getTitle(),
						Field.Store.YES, Field.Index.ANALYZED));// 通过分析器，延长这个字段的索引
			
			if (!StringUtils.isEmpty(article.getContent()))
				doc.add(new Field("content", article.getContent(),
						Field.Store.YES, Field.Index.ANALYZED));
			
			if (!StringUtils.isEmpty(article.getSummary()))
				doc.add(new Field("summary", article.getSummary(),
						Field.Store.YES, Field.Index.ANALYZED));
			
			if (!StringUtils.isEmpty(article.getAuthor()))
				doc.add(new Field("author", article.getAuthor(),
						Field.Store.YES, Field.Index.NOT_ANALYZED));
			writer.addDocument(doc);
		}
		int numIndexed = writer.maxDoc();
		writer.forceMerge(1);
		writer.close();
		return numIndexed;
	}

	public List<Article> search(String keys) throws IOException, ParseException {
		IndexReader reader = IndexReader.open(FSDirectory.open(new File(
			     indexDir)), false);
		IndexSearcher searcher = new IndexSearcher(reader);		
		
		Analyzer analyzer = new IKAnalyzer();
		String[] q = {keys,keys,keys,keys};
		String[] fields = { "title", "content", "summary", "author" };
		// 解析查询搜索领域的指定
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_35, q, fields,
				analyzer);
		TopDocs topDocs = searcher.search(query, 100);// 100是显示队列的Size (显示前100条)
		ScoreDoc[] hits = topDocs.scoreDocs;
		System.out.println("共有" + searcher.maxDoc() + "条索引，命中" + hits.length
				+ "条");
		Set<Long> ids = new HashSet<Long>();
		for (int i = 0; i < hits.length; i++) {
			int DocId = hits[i].doc;
			Document document = searcher.doc(DocId);
			String idStr = document.get("id");
			ids.add(Long.parseLong(idStr));
		}
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		List<Article> articles = articleDao.findByIdIn(ids);
		return articles;
	}

}