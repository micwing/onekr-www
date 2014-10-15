package onekr.portalservice.article.dao;

import java.util.Collection;
import java.util.List;

import onekr.portalservice.model.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * 
 * @author MICHAEL
 *
 */
@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {
	
	List<Article> findByIdIn(Collection<Long> ids);
}
