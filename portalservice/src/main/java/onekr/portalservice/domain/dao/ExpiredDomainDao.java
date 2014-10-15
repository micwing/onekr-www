package onekr.portalservice.domain.dao;

import onekr.portalservice.model.ExpiredDomain;

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
