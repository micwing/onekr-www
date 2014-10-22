package onekr.cardservice.card.impl;

import java.util.Date;
import java.util.List;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.LeaveCommentBiz;
import onekr.cardservice.model.Card;
import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.CommentBiz;
import onekr.commonservice.model.Comment;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.utils.HtmlUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveCommentBizImpl implements LeaveCommentBiz {
	
	@Autowired
	private CommentBiz commentBiz;
	@Autowired
	private CardBiz cardBiz;
	
	@Override
	public Comment saveCardComment(
			Long cardId,
			Long commentId,
			String userName,
			String content,
			Date createAt) {
		userName = HtmlUtil.delHTMLTag(userName.trim());
		content = HtmlUtil.delHTMLTag(content.trim());
		
		Card card = cardBiz.findById(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		
		Comment comment = null;
		if (commentId == null) {
			comment = new Comment();
			comment.setBiz(Biz.CARD_COMMENTS.name());
			comment.setOwner(cardId+"");
		} else {
			comment = commentBiz.findById(commentId);
			if (comment == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		}
		comment.setContent(content);
		comment.setCreateAt(createAt);
		comment.setUserName(userName);
		comment.setUserId(0L);
		
		commentBiz.saveComment(comment);

		return comment;
	}

	@Override
	public List<Comment> listAll(Long cardId) {
		List<Comment> list = commentBiz.findComments(Biz.CARD_COMMENTS, cardId+"");
		return list;
	}

	@Override
	public void deleteComment(Long id) {
		commentBiz.delete(id);
	}

 }
