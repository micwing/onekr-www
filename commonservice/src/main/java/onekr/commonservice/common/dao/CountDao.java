package onekr.commonservice.common.dao;

import java.util.Collection;
import java.util.List;

import onekr.commonservice.model.Count;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountDao extends JpaRepository<Count, Long> {

	List<Count> findByBizAndOwnerIn(String biz, Collection<String> owners);
	
	Count findByBizAndOwner(String biz, String owner);
}
