package onekr.identityservice.user.intf;

import java.util.Set;

import onekr.identityservice.model.User;
import onekr.identityservice.model.UserPassword;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserLoginBiz {
	
	void login(String credential, String password, String remember);
	
	User findUserByName(@NotBlank String loginName);
	
	User findUserByEmail(@NotBlank String email);
	
	UserPassword findUserPasswordByName(@NotBlank String loginName);
	
	Set<String> findUserRoles(Long userId);
}

