package onekr.biz.common.impl;

import java.util.Collection;
import java.util.List;

import onekr.biz.common.dao.MessageDao;
import onekr.biz.common.intf.MessageBiz;
import onekr.biz.model.Message;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageBizImpl implements MessageBiz {

	@Autowired
	private MessageDao messageDao;
	
	@Override
	public Message findById(Long messageId) {
		return messageDao.findOne(messageId);
	}

	@Override
	public List<Message> findByIds(Collection<Long> ids) {
		return messageDao.findByIdIn(ids);
	}

	@Override
	public List<Message> findByBizOwner(String biz, String owner) {
		return messageDao.findByBizAndOwner(biz, owner);
	}

	@Override
	public Message save(Message message) {
		return messageDao.save(message);
	}
	
	@Override
	public Message delete(Long id) {
		Message message = messageDao.findOne(id);
		if (message == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		messageDao.delete(message);
		return message;
	}
}
