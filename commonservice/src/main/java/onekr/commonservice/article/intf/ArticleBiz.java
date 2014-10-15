package onekr.commonservice.article.intf;

import java.util.Collection;
import java.util.List;

import onekr.commonservice.model.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ArticleBiz {
	
	void saveArticle(Article article);
	
	Article findArticle(Long id);
	
	Page<Article> list(Pageable pageable);
	
	void putTotalComment2Articles(Collection<Article> articles);
	
	void putTotalViewCount2Articles(Collection<Article> articles);
	
	void deleteArticle(List<Long> ids);
	
	List<Article> findAll();
}