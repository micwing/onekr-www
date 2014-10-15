package onekr.identityservice.user.dao;

import onekr.identityservice.model.UserPassword;

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
