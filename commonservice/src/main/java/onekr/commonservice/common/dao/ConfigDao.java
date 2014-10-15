package onekr.commonservice.common.dao;

import java.util.Collection;
import java.util.List;

import onekr.commonservice.model.Config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigDao extends JpaRepository<Config, Long> {

	List<Config> findByBizAndOwnerIn(String biz, Collection<String> owners);
	
	Config findByBizAndOwner(String biz, String owner);
}
