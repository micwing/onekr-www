package onekr.cardservice.card.impl;

import java.util.List;

import onekr.cardservice.card.dao.CardDao;
import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardDto;
import onekr.cardservice.model.Card;
import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.CommentBiz;
import onekr.commonservice.filestore.intf.FileBiz;
import onekr.commonservice.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardBizImpl implements CardBiz {
	
	@Autowired
	private CardDao cardDao;
	@Autowired
	private FileBiz fileBiz;
	@Autowired
	private CommentBiz commentBiz;

	@Override
	public CardDto findCardInfo(Long cardId) {
		CardDto dto = new CardDto();
		Card card = cardDao.findOne(cardId);
		dto.setCard(card);
		
		
		List<Comment> comments = commentBiz.findComments(Biz.CARD_COMMENTS, cardId+"");
		dto.setComments(comments);
		
		return dto;
	}

	@Override
	public Card findById(Long cardId) {
		return cardDao.findOne(cardId);
	}

	@Override
	public Page<Card> listCard(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card saveCard(Card card) {
		// TODO Auto-generated method stub
		return null;
	}

}
