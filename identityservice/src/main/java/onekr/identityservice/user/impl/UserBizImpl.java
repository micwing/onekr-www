package onekr.identityservice.user.impl;

import java.util.Date;

import onekr.framework.type.Gender;
import onekr.identityservice.model.Group;
import onekr.identityservice.model.User;
import onekr.identityservice.model.UserPassword;
import onekr.identityservice.user.dao.UserDao;
import onekr.identityservice.user.dao.UserPasswordDao;
import onekr.identityservice.user.intf.UserBiz;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserBizImpl implements UserBiz {
	@Value("#{systemConfig['user.anonymousId']}")
	private Long anonymousId;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserPasswordDao userPasswordDao;
	
	@Override
	public User findById(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public User getAnonymous() {
		return userDao.findOne(anonymousId);
	}
	
	@Override
	public Long getAnonymousId() {
		return anonymousId;
	}
	
	@Override
	public User register(String name, String pwd, String email, String mobile,
			Group group) {
		
		Date now = new Date();
		
		User user = new User();
		user.setBirthday(null);
		user.setCreateAt(now);
		user.setDescription(null);
		user.setEmail(email);
		user.setGender(Gender.UNKONW);
		user.setGroup(group);
		user.setMobile(mobile);
		user.setName(name);
		user.setPhoto(null);
		user.setQq(null);
		user.setTel(null);
		user.setUpdateAt(now);
		userDao.save(user);
		
		UserPassword upw = new UserPassword();
		upw.setId(user.getId());
		upw.setPassword(new Md5Hash(pwd).toHex());
		userPasswordDao.save(upw);
		return user;
	}
}
