package onekr.identityservice.user.impl;

import onekr.identityservice.model.User;
import onekr.identityservice.user.dao.UserDao;
import onekr.identityservice.user.intf.UserBiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBizImpl implements UserBiz {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findById(Long id) {
		return userDao.findOne(id);
	}

}
