package onekr.identityservice.user.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import onekr.commonservice.message.intf.EmailBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.type.Gender;
import onekr.identityservice.model.Group;
import onekr.identityservice.model.User;
import onekr.identityservice.model.UserPassword;
import onekr.identityservice.user.dao.UserDao;
import onekr.identityservice.user.dao.UserPasswordDao;
import onekr.identityservice.user.intf.UserBiz;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class UserBizImpl implements UserBiz {
	@Value("#{systemConfig['user.anonymousId']}")
	private Long anonymousId;
	
	@Value("#{systemConfig['site.root.url']}")
	private String siteRootUrl;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserPasswordDao userPasswordDao;
	
	@Autowired
	private EmailBiz emailBiz;
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	private Map<String, String> resetPasswordCodeMap = new HashMap<String, String>();
	
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
		upw.setUserId(user.getId());
		upw.setPassword(new Md5Hash(pwd).toHex());
		userPasswordDao.save(upw);
		return user;
	}
	
	@Override
	public User findPassword(String name, String email) {
		User user = userDao.findByName(name);
		if (user == null || StringUtils.isEmpty(user.getEmail()))
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
			
		if (user.getEmail().equals(email)) {
			String code = new Md5Hash(System.currentTimeMillis()+user.getName()).toHex();
			System.out.println(user.getName()+":"+code);
			try {
				Template tpl=freeMarkerConfigurer.getConfiguration().getTemplate("findUserPassword.ftl"); 
				Map<String, String> map=new HashMap<String, String>();
				map.put("username",user.getName());
				map.put("url", siteRootUrl+"/login/resetpassword?username="+user.getName()+"&code="+code);
				emailBiz.sendEmail(user.getEmail(), "重置密码", FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			resetPasswordCodeMap.put(user.getName(), code);
		} else {
			throw new AppException(ErrorCode.UN_SUPPORTED);
		}
		return user;
	}
	
	@Override
	public boolean validateFindPasswordCode(String name, String code) {
		String cacheCode = resetPasswordCodeMap.get(name);
		return code.equals(cacheCode);
	}
	
	@Override
	public User resetPassword(String name, String pwd, String code) {
		User user = userDao.findByName(name);
		if (user == null || StringUtils.isEmpty(user.getEmail())) {			
			throw new AppException(ErrorCode.USER_NOT_EXIST);
		}
		
		if (!validateFindPasswordCode(name, code)) {
			throw new AppException(ErrorCode.NO_PERMISSON);
		}
		
		UserPassword up = userPasswordDao.findByUserId(user.getId());
		up.setPassword(new Md5Hash(pwd).toHex());
		userPasswordDao.save(up);
		
		resetPasswordCodeMap.remove(user.getName());
		return user;
	}
}
