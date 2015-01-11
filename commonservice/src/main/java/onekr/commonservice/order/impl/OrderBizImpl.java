package onekr.commonservice.order.impl;

import java.util.Date;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.Order;
import onekr.commonservice.order.dao.OrderDao;
import onekr.commonservice.order.intf.OrderBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderBizImpl implements OrderBiz {
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Order findById(Long id) {
		return orderDao.findOne(id);
	}

	@Override
	public Page<Order> listOrder(Biz biz, Pageable pageable) {
		return orderDao.findByBizOrderByCreateAtDesc(biz.name(),pageable);
	}

	@Override
	public Page<Order> listOrder(Biz biz, String owner, Pageable pageable) {
		return orderDao.findByBizAndOwnerOrderByCreateAtDesc(biz.name(), owner, pageable);
	}

	@Override
	public Order saveOrder(Order order) {
		Date now = new Date();
		
		Order entity = null;
		if (order.getId() != null) {
			entity = orderDao.findOne(order.getId());
			if (entity == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		} else {
			entity = new Order();
			
			if (order.getBiz() == null)
				throw new AppException(ErrorCode.ILLEGAL_PARAM, "参数biz不能为空");
			entity.setBiz(order.getBiz());
			if (order.getOwner() == null)
				throw new AppException(ErrorCode.ILLEGAL_PARAM, "参数owner不能为空");
			entity.setOwner(order.getOwner());
			
			entity.setCreateAt(now);
			if (order.getCreateBy() == null)
				throw new AppException(ErrorCode.ILLEGAL_PARAM, "参数createBy不能为空");
			entity.setCreateBy(order.getCreateBy());
		}
		
		entity.setService(order.getService());
		entity.setPartner(order.getPartner());
		entity.setInputCharset(order.getInputCharset());
		entity.setPaymentType(order.getPaymentType());
		
		entity.setBody(order.getBody());
		entity.setNotifyUrl(order.getNotifyUrl());
		
		entity.setReturnUrl(order.getReturnUrl());
		entity.setSellerEmail(order.getSellerEmail());
		
		entity.setShowUrl(order.getShowUrl());
		entity.setSubject(order.getSubject());
		
		entity.setPrice(order.getPrice());
		entity.setQuantity(order.getQuantity());
		
		entity.setLogistics_fee(order.getLogistics_fee());
		entity.setLogistics_payment(order.getLogistics_payment());
		entity.setLogistics_type(order.getLogistics_type());
		entity.setReceive_name(order.getReceive_name());
		entity.setReceive_address(order.getReceive_address());
		entity.setReceive_zip(order.getReceive_zip());
		entity.setReceive_phone(order.getReceive_phone());
		entity.setReceive_mobile(order.getReceive_mobile());
		
		entity.setNoticeAt(order.getNoticeAt());
		entity.setNotifyUrl(order.getNotifyUrl());
		entity.setReturnAt(order.getReturnAt());
		entity.setReturnUrl(order.getReturnUrl());
		entity.setTradeNo(order.getTradeNo());
		entity.setTradeStatus(order.getTradeStatus());
		entity.setRemark(order.getRemark());
		
		entity.setUpdateAt(now);
		if (order.getUpdateBy() == null)
			throw new AppException(ErrorCode.ILLEGAL_PARAM, "参数updateBy不能为空");
		entity.setUpdateBy(order.getUpdateBy());
		
		order = orderDao.save(entity);
		return order;
	}
	
}
