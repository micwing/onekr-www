package onekr.commonservice.message.intf;


public interface EmailBiz {

	void sendEmail(String sendTo,String title, String html);
	
}
