package onekr.cardservice.card.impl;

import java.util.Date;

import onekr.cardservice.card.dao.CardDao;
import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.model.Card;
import onekr.cardservice.model.CardType;
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

		} else {
			entity.setPeople1Name(card.getPeople1Name());
			entity.setPeople2Name(card.getPeople2Name());
			
			card.setCreateAt(now);
			card.setCreateBy(uid);
			// 默认值
			if (card.getCardType() == null) {
				entity.setCardType(CardType.WED_CARD);
			}
			if (card.getStatus() == null) {
				entity.setStatus(Status.NORMAL);
			}
		}
		
		entity.setAddress(card.getAddress());
		entity.setAfterInfo(card.getAfterInfo());
		entity.setBeforeInfo(card.getBeforeInfo());
		entity.setPartyTime(card.getPartyTime());
		entity.setPeople1Mobile(card.getPeople1Mobile());
		entity.setPeople2Mobile(card.getPeople2Mobile());
		entity.setRemark(card.getRemark());
		entity.setRemind(card.getRemind());
		entity.setRestaurant(card.getRestaurant());
		entity.setStatus(card.getStatus());
		entity.setTempletId(card.getTempletId());
		entity.setTitle(card.getTitle());
		entity.setTraffic(card.getTraffic());

		entity.setUpdateAt(now);
		entity.setUpdateBy(uid);

		return cardDao.save(entity);
	}

	@Override
	public Card updateCardMap(Long cardId, String mapPicUrl, String mapUrl, Long uid) {
		Card card = cardDao.findOne(cardId);
		if (card == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		card.setMapPicUrl(mapPicUrl);
		card.setMapUrl(mapUrl);
		card.setUpdateAt(new Date());
		card.setUpdateBy(uid);
		return cardDao.save(card);
	}
}
