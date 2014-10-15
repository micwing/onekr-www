package onekr.portalservice.article.intf;

import java.io.IOException;
import java.util.List;

import onekr.portalservice.model.Article;

import org.apache.lucene.queryParser.ParseException;

public interface SearchArticleBiz {

	int indexFile(List<Article> articles) throws IOException;
	
	List<Article> search(String keys) throws IOException, ParseException;
}
