package onekr.cardservice.card.impl;

import java.util.Date;
import java.util.List;

import onekr.cardservice.card.dao.CardDao;
import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardCommentBiz;
import onekr.cardservice.card.intf.CardDto;
import onekr.cardservice.card.intf.CardFileBiz;
import onekr.cardservice.model.Card;
import onekr.cardservice.model.CardType;
import onekr.commonservice.model.Comment;
import onekr.commonservice.model.Status;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardBizImpl implements CardBiz {

	@Autowired
	private CardDao cardDao;
	@Autowired
	private CardFileBiz cardFileBiz;
	@Autowired
	private CardCommentBiz cardCommentBiz;

	@Override
	public CardDto findCardInfo(Long cardId) {
		CardDto dto = new CardDto();
		Card card = cardDao.findOne(cardId);
		dto.setCard(card);

		List<Comment> comments = cardCommentBiz.listAll(cardId);
		dto.setComments(comments);

		// TODO fileBiz

		return dto;
	}

	@Override
	public Card findById(Long cardId) {
		return cardDao.findOne(cardId);
	}

	@Override
	public Page<Card> listCard(CardType cardType, Status status,
			Pageable pageable) {
		return cardDao.findByCardTypeAndStatusOrderByCreateAtDesc(cardType,
				status, pageable);
	}

	@Override
	public Card saveCard(Card card, Long uid) {
		Date now = new Date();

		Card entity = new Card();
		if (card.getId() != null) {
			entity = cardDao.findOne(card.getId());
			if (entity == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND);

			card.setCreateAt(entity.getCreateAt());
			card.setCreateBy(entity.getCreateBy());
			card.setCardType(entity.getCardType());
			card.setStatus(entity.getStatus());
		}

		// 默认值
		if (card.getCardType() == null) {
			card.setCardType(CardType.WED_CARD);
		}
		if (card.getStatus() == null) {
			card.setStatus(Status.NORMAL);
		}
		card.setUpdateAt(now);
		card.setUpdateBy(uid);

		return cardDao.save(card);
	}

}
