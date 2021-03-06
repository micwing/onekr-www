//package onekr.portalservice.leaveComment.impl;
//
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import onekr.commonservice.biz.Biz;
//import onekr.commonservice.common.intf.CommentBiz;
//import onekr.commonservice.common.intf.CountBiz;
//import onekr.commonservice.model.Comment;
//import onekr.commonservice.model.Status;
//import onekr.framework.exception.AppException;
//import onekr.framework.exception.ErrorCode;
//import onekr.framework.lang.Observable;
//import onekr.framework.utils.HtmlUtil;
//import onekr.portalservice.leaveComment.intf.LeaveCommentBiz;
//import onekr.portalservice.utils.GlobalConstants;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//
//@Service
//public class LeaveCommentBizImpl implements LeaveCommentBiz {
//    
//	private Observable messageSaveObservable = new Observable();
//	
//	@Value("#{systemConfig['email.adminNoticeEmail']}")
//	private String adminNoticeEmail;
//	
//	@Autowired
//	private CommentBiz commentBiz;
//	@Autowired
//	private CountBiz countBiz;
//	
//	
//	
//	private static final int POOL_SIZE = 10;
//	private static final int DELAY_SEC = 2;
//	private static final ScheduledExecutorService scheduledExecutor = Executors
//			.newScheduledThreadPool(POOL_SIZE);
//	@Autowired
//	private JavaMailSenderImpl javaMailSender;
//
//	public void emailNotice(String sendToEmail, String username, String cardId) {
//		SimpleMailMessage smm = new SimpleMailMessage();
//		smm.setTo(sendToEmail);
//		smm.setSubject(username + "您好，您的请柬有新留言！");
//		smm.setText("http://card.onekr.com/card/"+cardId+"/cover");
//		smm.setFrom(javaMailSender.getUsername());
//
//		final SimpleMailMessage messageObj = smm;
//		scheduledExecutor.schedule(new Runnable() {
//			@Override
//			public void run() {
//				javaMailSender.send(messageObj);
//			}
//		}, DELAY_SEC, TimeUnit.SECONDS);
//	}
//	
//	private void putGoodBad(Comment comment) {
////		Count goodcount = countBiz.findCount(Biz.PORTAL_COMMENTS_GOOD_COUNT, comment.getId()+"");
////		Count badcount = countBiz.findCount(Biz.PORTAL_COMMENTS_BAD_COUNT, comment.getId()+"");
////		if (goodcount != null) {				
////			comment.setGood(goodcount.getNum());
////		} else {
////			comment.setGood(0L);
////		}
////		if (badcount != null) {				
////			comment.setBad(badcount.getNum());
////		} else {
////			comment.setBad(0L);
////		}
//	}
//	
//	private void putGoodBad(Collection<Comment> comments) {
////		List<String> ids = new ArrayList<String>();
////		for (Comment c : comments) {
////			ids.add(c.getId()+"");
////		}
////		Map<String, Count> goodmap = countBiz.findCounts(Biz.PORTAL_COMMENTS_GOOD_COUNT, ids);
////		Map<String, Count> badmap = countBiz.findCounts(Biz.PORTAL_COMMENTS_BAD_COUNT, ids);
////		for (Comment c : comments) {
////			if (goodmap.containsKey(c.getId()+"")) {				
////				c.setGood(goodmap.get(c.getId()+"").getNum());
////			} else {
////				c.setGood(0L);
////			}
////			if (badmap.containsKey(c.getId()+"")) {				
////				c.setBad(badmap.get(c.getId()+"").getNum());
////			} else {
////				c.setBad(0L);
////			}
////		}
//	}
//	
//	private void putJsonValue(Comment comment, String key, String value) {
//		String json = comment.getJson();
//		Map<String, String> map = null;
//		if (StringUtils.isEmpty(json)) {
//			map = new HashMap<String, String>();
//		} else {
//			map = JSON.parseObject(json, new TypeReference<HashMap<String, String>>(){});
//		}
//		map.put(key, value);
//		comment.setJson(JSON.toJSONString(map));
//	}
//	
//	private void putValueFromJson(Comment comment) {
//		String json = comment.getJson();
//		if (StringUtils.isEmpty(json)) {
//			return;
//		}
//		Map<String, String> map = JSON.parseObject(json, new TypeReference<HashMap<String, String>>(){});
////		if (map.containsKey(Comment.KEY_EMAIL));
////		comment.setEmail(map.get(Comment.KEY_EMAIL));
//	}
//	
//	private void putValueFromJson(Collection<Comment> comments) {
//		if (CollectionUtils.isEmpty(comments))
//			return;
//		for (Comment comment : comments)
//			putValueFromJson(comment);
//	}
//
//	@Override
//	public Comment saveComment(Long id, String name, String email,
//			Date createAt, String content, Boolean isNoticeSender) {
//		name = HtmlUtil.delHTMLTag(name.trim());
//		email = HtmlUtil.delHTMLTag(email.trim());
//		
//		Comment comment = null;
//		if (id == null) {
//			comment = new Comment();
//			comment.setBiz(Biz.PORTAL_COMMENTS.name());
//			comment.setOwner(GlobalConstants.OWNER_NONE);
//		} else {
//			comment = getComment(id);
//		}
//		comment.setContent(content);
//		comment.setCreateAt(createAt);
//		comment.setUserName(name);
////		putJsonValue(comment, Comment.KEY_EMAIL, email);
//		
//		commentBiz.saveComment(comment);
//
//		if (isNoticeSender != null && isNoticeSender) {			
//			notifyAdmin4CommentSave(comment);
//		}
//		
//		putGoodBad(comment);
//		putValueFromJson(comment);
//		return comment;
//	}
//
//	@Override
//	public Comment getComment(Long id) {
//		Comment comment = commentBiz.findById(id);
//		putGoodBad(comment);
//		putValueFromJson(comment);
//		return comment;
//	}
//
//	@Override
//	public List<Comment> listAll() {
//		List<Comment> list = commentBiz.findAll(Biz.PORTAL_COMMENTS);
//		putGoodBad(list);
//		putValueFromJson(list);
//		return list;
//	}
//
//	@Override
//	public Long addGoodBad(Long commentId, boolean isGood) {
//		return countBiz.addCount(
//				isGood ? Biz.PORTAL_COMMENTS_GOOD_COUNT : Biz.PORTAL_COMMENTS_BAD_COUNT, 
//						commentId+"", 1L).getNum();
//	}
//
//	@Override
//	public void deleteComment(Long id) {
//		commentBiz.delete(id);
//	}
//
//	@Override
//	public Comment replyComment(Long id, String replyContent, Date replyAt,
//			Boolean isNoticeSender) {
//		Date now = new Date();
//		Comment comment = getComment(id);
//		if (comment == null)
//			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
//		Comment replyComment = new Comment();
//		replyComment.setBiz(null);
//		replyComment.setOwner(GlobalConstants.OWNER_NONE);
//		replyComment.setUserName(GlobalConstants.ADMIN_USER_NAME);
//		replyComment.setContent(replyContent);
//		replyComment.setCreateAt(now);
//		replyComment.setStatus(Status.NORMAL);
////		comment.setSubComment(replyComment);
//		
//		commentBiz.saveComment(comment);
//		
//		if (isNoticeSender != null && isNoticeSender) {
//			notifySender4CommentReply(comment);
//		}
//		
//		putGoodBad(comment);
//		putValueFromJson(comment);
//		return comment;
//	}
//
//	@Override
//	public void notifySender4CommentReply(Long id) {
//		notifySender4CommentReply(getComment(id));
//	}
//	
//	public void notifySender4CommentReply(Comment comment) {
//		String json = comment.getJson();
//		if (StringUtils.isEmpty(json))
//			return;
//		Map<String, String> map = JSON.parseObject(json, new TypeReference<HashMap<String, String>>(){});
//		
////		emailNotice();
////		SimpleMailMessage smm = new SimpleMailMessage();  
////      smm.setTo(email);
////      smm.setSubject(comment.getUserName()+"您好，您在 onekr.com 的留言已经被回复！");
////      smm.setText("http://www.onekr.com/contect");
//		
//		
////		String email = map.get(Comment.KEY_EMAIL);
////		if (StringUtils.isEmpty(email))
////			return;
////		
////		SimpleMailMessage smm = new SimpleMailMessage();  
////        smm.setTo(email);
////        smm.setSubject(comment.getUserName()+"您好，您在 onekr.com 的留言已经被回复！");
////        smm.setText("http://www.onekr.com/contect");
////        
////		messageSaveObservable.notifyObservers(smm);
//	}
//	
//	private void notifyAdmin4CommentSave(Comment comment) {
//		SimpleMailMessage smm = new SimpleMailMessage();  
//        smm.setTo(adminNoticeEmail);
//        smm.setSubject("onekr.com 有新留言");
//        smm.setText("留言内容："+comment.getContent());
//        
//		messageSaveObservable.notifyObservers(smm);
//	}
//	
//}
