package onekr.identityservice.user.impl;

import onekr.identityservice.model.User;
import onekr.identityservice.model.UserPassword;
import onekr.identityservice.user.dao.UserDao;
import onekr.identityservice.user.dao.UserPasswordDao;
import onekr.identityservice.user.intf.UserLoginBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginBizImpl implements UserLoginBiz {
	//这里添加的dao需要在shiro.xml中显式配置依赖
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserPasswordDao userPasswordDao;
	
	@Override
	public User findUserByName(String loginName) {
		return userDao.findByName(loginName);
	}
	
	@Override
	public UserPassword findUserPasswordByName(String loginName) {
		User user = userDao.findByName(loginName);
		if (user == null)
			throw new AppException(ErrorCode.USER_NOT_EXIST);
		return userPasswordDao.findOne(user.getId());
	}
}
