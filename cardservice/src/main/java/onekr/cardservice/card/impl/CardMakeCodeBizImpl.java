package onekr.cardservice.card.impl;

import java.util.Date;

import onekr.cardservice.card.dao.CardDao;
import onekr.cardservice.card.dao.CardMakeCodeDao;
import onekr.cardservice.card.intf.CardMakeCodeBiz;
import onekr.cardservice.model.Card;
import onekr.cardservice.model.CardMakeCode;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.utils.DateUtil;
import onekr.framework.utils.EncriptUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardMakeCodeBizImpl implements CardMakeCodeBiz {
	
	@Autowired
	private CardDao cardDao;
	
	@Autowired
	private CardMakeCodeDao cardMakeCodeDao;
	
	@Override
	public String generateNewCode(String buyerName, Long uid) {
		Date createtime = new Date();
		String md5 = EncriptUtil.encript(buyerName+DateUtil.cnvDate2StrF1(createtime));
		CardMakeCode entity = new CardMakeCode();
		entity.setMaker(buyerName);
		entity.setCode(md5);
		entity.setCreateAt(createtime);
		entity.setCreateBy(uid);
		entity.setCard(null);
		cardMakeCodeDao.save(entity);
		return md5;
	}
	
	@Override
	public CardMakeCode findNoUseCode(String code) {
		return cardMakeCodeDao.findByCodeAndCardIsNull(code);
	}
	
	@Override
	public CardMakeCode useCode(String code, Long cardId) {
		CardMakeCode entity = cardMakeCodeDao.findByCodeAndCardIsNull(code);
		if (entity == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "制作码不存在或者已被使用！");
		Card card = cardDao.findOne(cardId);
		entity.setCard(card);
		entity.setMakeAt(new Date());
		cardMakeCodeDao.save(entity);
		return entity;
	}
	
	@Override
	public Page<CardMakeCode> listAll(Date start, Date end, Pageable pageable) {
		Page<CardMakeCode> page = cardMakeCodeDao.findByCreateAtGreaterThanAndCreateAtLessThanOrderByCreateAtDesc(start, end, pageable);
		return page;
	}
	
	@Override
	public CardMakeCode delete(String code) {
		CardMakeCode entity = cardMakeCodeDao.findByCode(code);
		if (entity == null) {
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		}
		cardMakeCodeDao.delete(entity);
		return entity;
	}
}
