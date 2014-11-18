package onekr.commonservice.message.impl;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.biz.ConfigOwner;
import onekr.commonservice.common.dao.ConfigDao;
import onekr.commonservice.message.intf.EmailBiz;
import onekr.commonservice.model.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailBizImpl implements EmailBiz {
	
	@Autowired
	private ConfigDao configDao;
	
	@Override
	public void sendTextEmail(String sendTo, String text) {
		Config server = configDao.findByBizAndOwner(Biz.SYSTEM.name(), ConfigOwner.SYSTEM_EMAIL_SERVER.name());
		Config port = configDao.findByBizAndOwner(Biz.SYSTEM.name(), ConfigOwner.SYSTEM_EMAIL_PORT.name());
		Config username = configDao.findByBizAndOwner(Biz.SYSTEM.name(), ConfigOwner.SYSTEM_EMAIL_USERNAME.name());
		Config pwd = configDao.findByBizAndOwner(Biz.SYSTEM.name(), ConfigOwner.SYSTEM_EMAIL_PASSWORD.name());
		Config defaultencoding = configDao.findByBizAndOwner(Biz.SYSTEM.name(), ConfigOwner.SYSTEM_EMAIL_DEFAULTENCODING.name());
		Config smtpAuth = configDao.findByBizAndOwner(Biz.SYSTEM.name(), ConfigOwner.SYSTEM_EMAIL_SMTP_AUTH.name());
		
		
	}
}

