package onekr.biz.domain.dao;

import onekr.biz.model.ExpiredDomain;

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
