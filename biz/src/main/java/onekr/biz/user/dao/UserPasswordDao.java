package onekr.biz.user.dao;

import onekr.biz.model.UserPassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * 
 * @author MICHAEL
 *
 */
@Repository
public interface UserPasswordDao extends JpaRepository<UserPassword, Long> {
	
}
