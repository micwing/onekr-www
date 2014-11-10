package onekr.identityservice.user.impl;

import onekr.identityservice.model.User;
import onekr.identityservice.user.dao.UserDao;
import onekr.identityservice.user.intf.UserBiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserBizImpl implements UserBiz {
	@Value("#{systemConfig['user.anonymousId']}")
	private Long anonymousId;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User findById(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public User getAnonymous() {
		return userDao.findOne(anonymousId);
	}
}
