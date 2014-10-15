package onekr.commonservice.common.intf;

import java.util.Collection;
import java.util.List;

import onekr.commonservice.model.Message;

public interface MessageBiz {
	Message findById(Long messageId);
	
	List<Message> findByIds(Collection<Long> ids);
	
	List<Message> findByBizOwner(String biz, String owner);
	
	Message save(Message message);
	
	Message delete(Long id);
}
