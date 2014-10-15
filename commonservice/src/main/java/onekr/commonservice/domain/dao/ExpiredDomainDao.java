package onekr.commonservice.domain.dao;

import onekr.commonservice.model.ExpiredDomain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * 
 * @author MICHAEL
 *
 */
@Repository
public interface ExpiredDomainDao extends JpaRepository<ExpiredDomain, Long> {
	
}
