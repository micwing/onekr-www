package onekr.commonservice.common.intf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import onekr.commonservice.model.Comment;
import onekr.commonservice.model.Status;

public interface CommentBiz {
	List<Comment> findCommentsByOwners(String biz, Collection<String> owners);
	
	Map<String, Long> findOwnerCountMap(String biz, Collection<String> owners);
	
	List<Comment> findAll(String biz);
	
	List<Comment> findComments(String biz, String owner);
	
	Comment findById(Long id);
	
	Comment saveComment(Comment comment);
	
	Comment updateCommentStatus(Long id, Status status);
	
	void delete(Long id);
}
