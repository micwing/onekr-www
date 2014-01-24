package onekr.biz.common.dao;

import java.util.Collection;
import java.util.List;

import onekr.biz.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentDao extends JpaRepository<Comment, Long> {
	List<Comment> findByBiz(String biz);
	
	List<Comment> findByBizAndOwnerIn(String biz, Collection<String> owners);
	
	List<Comment> findByBizAndOwner(String biz, String owner);
}
