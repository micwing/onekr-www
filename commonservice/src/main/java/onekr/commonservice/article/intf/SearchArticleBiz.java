package onekr.commonservice.article.intf;

import java.io.IOException;
import java.util.List;

import onekr.commonservice.model.Article;

import org.apache.lucene.queryParser.ParseException;

public interface SearchArticleBiz {

	int indexFile(List<Article> articles) throws IOException;
	
	List<Article> search(String keys) throws IOException, ParseException;
}
