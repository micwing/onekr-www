package onekr.biz.user.dao;

import java.util.List;

import onekr.biz.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * 
 * @author MICHAEL
 *
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	User findByName(String loginName);
	
	List<User> findByIdIn(List<Long> ids);
}
