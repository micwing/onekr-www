package onekr.cardservice.card.impl;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service("EmailSenderBizImpl")
public class EmailSenderBizImpl implements Observer {

	private static final int POOL_SIZE = 10;
    private static final int DELAY_SEC = 2;
    private static final ScheduledExecutorService scheduledExecutor = 
    		Executors.newScheduledThreadPool(POOL_SIZE);
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			if (arg instanceof SimpleMailMessage) {
				final SimpleMailMessage smm = (SimpleMailMessage) arg;
				smm.setFrom(javaMailSender.getUsername());
				sendSimpleMailMessage(smm);
			} else if (arg instanceof MimeMessage) {
				final MimeMessage mm = (MimeMessage) arg;
				sendMimeMessage(mm);
			}
		}
	}
	
	private void sendSimpleMailMessage(final SimpleMailMessage messageObj) {
		
		scheduledExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				javaMailSender.send(messageObj);
			}
		}, DELAY_SEC, TimeUnit.SECONDS);
	}
	
	private void sendMimeMessage(final MimeMessage messageObj) {
		
		scheduledExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				javaMailSender.send(messageObj);
			}
		}, DELAY_SEC, TimeUnit.SECONDS);
	}
	
}
