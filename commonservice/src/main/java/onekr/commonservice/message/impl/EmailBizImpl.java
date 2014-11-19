package onekr.commonservice.message.impl;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.biz.ConfigOwner;
import onekr.commonservice.common.dao.ConfigDao;
import onekr.commonservice.message.intf.EmailBiz;
import onekr.commonservice.model.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailBizImpl implements EmailBiz {

	private static final int POOL_SIZE = 4;
	private static final int DELAY_SEC = 1;
	private static final ScheduledExecutorService scheduledExecutor = Executors
			.newScheduledThreadPool(POOL_SIZE);

	@Autowired
	private ConfigDao configDao;
	
	@Override
	public void sendEmail(final String sendTo, final String title, final String html) {
		scheduledExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				_sendEmail(sendTo, title, html);
			}
		}, DELAY_SEC, TimeUnit.SECONDS);
	}
	
	private void _sendEmail(String sendTo, String title, String html) {
		Config server = configDao.findByBizAndOwner(Biz.SYSTEM.name(),
				ConfigOwner.SYSTEM_EMAIL_SERVER.name());
		Config port = configDao.findByBizAndOwner(Biz.SYSTEM.name(),
				ConfigOwner.SYSTEM_EMAIL_PORT.name());
		Config username = configDao.findByBizAndOwner(Biz.SYSTEM.name(),
				ConfigOwner.SYSTEM_EMAIL_USERNAME.name());
		Config pwd = configDao.findByBizAndOwner(Biz.SYSTEM.name(),
				ConfigOwner.SYSTEM_EMAIL_PASSWORD.name());
		Config defaultEncoding = configDao.findByBizAndOwner(Biz.SYSTEM.name(),
				ConfigOwner.SYSTEM_EMAIL_DEFAULTENCODING.name());

		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(server.getValue());
		sender.setUsername(username.getValue());
		sender.setPassword(pwd.getValue());
		sender.setPort(Integer.parseInt(port.getValue()));
		sender.setDefaultEncoding(defaultEncoding.getValue());

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", "true");
		sender.setJavaMailProperties(mailProperties);

		try {
			MimeMessage msg=sender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(msg,false,"utf8");//由于是html邮件，不是mulitpart类型  
			helper.setFrom(username.getValue());
			helper.setTo(sendTo);
			helper.setSubject(title);
			helper.setText(html, true);
			
			sender.send(msg);
		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}  
        
	}

}
