package onekr.commonservice.order.impl;

import java.util.Date;
import java.util.List;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.Order;
import onekr.commonservice.order.dao.OrderDao;
import onekr.commonservice.order.intf.OrderBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Order> findOrders(Biz biz) {
		return orderDao.findByBizOrderByCreateAtDesc(biz.name());
	}

	@Override
	public List<Order> findOrders(Biz biz, String owner) {
		return orderDao.findByBizAndOwnerOrderByCreateAtDesc(biz.name(), owner);
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
			
			entity.setService(order.getService());
			entity.setPartner(order.getPartner());
			entity.setInputCharset(order.getInputCharset());
			entity.setPaymentType(order.getPaymentType());
			
			entity.setAntiPhishingKey(order.getAntiPhishingKey());
			entity.setBody(order.getBody());
			entity.setExterInvokeIp(order.getExterInvokeIp());
			entity.setNotifyUrl(order.getNotifyUrl());
			
			entity.setReturnUrl(order.getReturnUrl());
			entity.setSellerEmail(order.getSellerEmail());
			
			entity.setShowUrl(order.getShowUrl());
			entity.setSubject(order.getSubject());
			entity.setTotalFee(order.getTotalFee());
		}
		
		entity.setNoticeAt(order.getNoticeAt());
		entity.setReturnAt(order.getReturnAt());
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