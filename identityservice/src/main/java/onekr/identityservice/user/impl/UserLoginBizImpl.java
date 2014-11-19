package onekr.identityservice.user.impl;

import java.util.HashSet;
import java.util.Set;

import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.identityservice.model.Group;
import onekr.identityservice.model.User;
import onekr.identityservice.model.UserPassword;
import onekr.identityservice.user.dao.UserDao;
import onekr.identityservice.user.dao.UserPasswordDao;
import onekr.identityservice.user.intf.UserLoginBiz;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
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
	public void login(String credential, String password, String remember) {
		UsernamePasswordToken token = new UsernamePasswordToken(credential, new Md5Hash(password).toHex());
		if (!StringUtils.isEmpty(remember)) {
			token.setRememberMe(true);
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			throw new AppException(ErrorCode.USER_PSW_NOT_MATCH);
		}
	}
	
	@Override
	public User findUserByName(String loginName) {
		return userDao.findByName(loginName);
	}
	
	@Override
	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	@Override
	public UserPassword findUserPasswordByName(String loginName) {
		User user = userDao.findByName(loginName);
		if (user == null)
			throw new AppException(ErrorCode.USER_NOT_EXIST);
		return userPasswordDao.findByUserId(user.getId());
	}
	
	@Override
	public Set<String> findUserRoles(Long userId) {
		User user = userDao.findOne(userId);
		if (user == null)
			throw new AppException(ErrorCode.USER_NOT_EXIST);
		Set<String> set = new HashSet<String>();
		switch(user.getGroup()) {
			case ADMINISTRATOR : 
				set.add(Group.ADMINISTRATOR.name());
			case SELLER : 
				set.add(Group.SELLER.name());
			case USER :
				set.add(Group.USER.name());
		}
		return set;
	}
}
