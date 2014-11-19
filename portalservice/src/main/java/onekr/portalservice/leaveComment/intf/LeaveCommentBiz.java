//package onekr.portalservice.leaveComment.intf;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.validation.constraints.NotNull;
//
//import onekr.commonservice.model.Comment;
//
//import org.hibernate.validator.constraints.NotBlank;
//import org.springframework.validation.annotation.Validated;
//
//@Validated
//public interface LeaveCommentBiz {
//	
//	Comment saveComment(
//			Long id,
//			@NotBlank String name,
//			@NotBlank String email, 
//			@NotNull Date createAt,
//			@NotBlank String content,
//			Boolean isNoticeSender
//			);
//	
//	Comment getComment(Long id);
//	
//	List<Comment> listAll();
//	
//	Long addGoodBad(Long messageId, boolean isGood);
//	
//	void deleteComment(Long id);
//	
//	Comment replyComment(Long id, String replyContent, Date replyAt, Boolean isNoticeSender);
//	
//	void notifySender4CommentReply(Long id);
//}