package onekr.commonservice.common.intf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.Comment;
import onekr.commonservice.model.Status;

public interface CommentBiz {
	List<Comment> findCommentsByOwners(Biz biz, Collection<String> owners);
	
	Map<String, Long> findOwnerCountMap(Biz biz, Collection<String> owners);
	
	List<Comment> findAll(Biz biz);
	
	List<Comment> findComments(Biz biz, String owner);
	
	Comment findById(Long id);
	
	Comment saveComment(Comment comment);
	
	Comment updateCommentStatus(Long id, Status status);
	
	void delete(Long id);
}
