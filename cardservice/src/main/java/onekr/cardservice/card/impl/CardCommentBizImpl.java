package onekr.cardservice.card.impl;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.internet.MimeMessage;

import onekr.cardservice.card.dao.CardDao;
import onekr.cardservice.card.intf.CardCommentBiz;
import onekr.cardservice.model.Card;
import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.CommentBiz;
import onekr.commonservice.filestore.intf.FileBiz;
import onekr.commonservice.model.Comment;
import onekr.commonservice.model.Status;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.utils.HtmlUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class CardCommentBizImpl implements CardCommentBiz {

	@Autowired
	private CardDao cardDao;
	@Autowired
	private FileBiz fileBiz;
	@Autowired
	private CommentBiz commentBiz;

	private static final int POOL_SIZE = 10;
	private static final int DELAY_SEC = 2;
	private static final ScheduledExecutorService scheduledExecutor = Executors
			.newScheduledThreadPool(POOL_SIZE);
	@Autowired
	private JavaMailSenderImpl javaMailSender;

	public void emailNotice(String sendToEmail, String username, String cardId) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(sendToEmail);
		smm.setSubject(username + "您好，您的请柬有新留言！");
		smm.setText("http://card.onekr.com/card/"+cardId+"/cover");
		smm.setFrom(javaMailSender.getUsername());

		final SimpleMailMessage messageObj = smm;
		scheduledExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				javaMailSender.send(messageObj);
			}
		}, DELAY_SEC, TimeUnit.SECONDS);
	}

	@Override
	public Comment saveCardComment(Long cardId, Long commentId,
			String userName, String content, Date createAt) {
		Card card = cardDao.findOne(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		if (card.getStatus().equals(Status.PAUSED))
			throw new AppException(ErrorCode.ILLEGAL_STATE);

		userName = HtmlUtil.delHTMLTag(userName);
		content = HtmlUtil.delHTMLTag(content);

		Comment comment = null;
		if (commentId == null) {
			comment = new Comment();
			comment.setBiz(Biz.CARD_COMMENTS.name());
			comment.setOwner(cardId + "");
		} else {
			comment = commentBiz.findById(commentId);
			if (comment == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		}
		comment.setContent(content);
		comment.setCreateAt(createAt);
		comment.setUserName(userName);
		comment.setUserId(0L);

		return commentBiz.saveComment(comment);
	}

	@Override
	public List<Comment> listAll(Long cardId) {
		List<Comment> list = commentBiz.findComments(Biz.CARD_COMMENTS, cardId
				+ "");
		return list;
	}

	@Override
	public void deleteComment(Long id) {
		commentBiz.delete(id);
	}
}
