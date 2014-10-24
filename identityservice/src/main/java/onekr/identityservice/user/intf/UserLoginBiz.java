package onekr.identityservice.user.intf;

import onekr.identityservice.model.User;
import onekr.identityservice.model.UserPassword;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserLoginBiz {
	
	void login(String credential, String password, String remember);
	
	User findUserByName(@NotBlank String loginName);
	
	UserPassword findUserPasswordByName(@NotBlank String loginName);
}

