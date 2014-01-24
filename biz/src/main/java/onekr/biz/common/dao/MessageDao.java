package onekr.biz.common.dao;

import java.util.Collection;
import java.util.List;

import onekr.biz.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * 
 * @author MICHAEL
 *
 */
@Repository
public interface MessageDao extends JpaRepository<Message, Long> {
	
	List<Message> findByIdIn(Collection<Long> ids);
	
	List<Message> findByBizAndOwner(String biz, String owner);
	
	List<Message> findByBizAndOwnerIn(String biz, Collection<String> owners);
}
