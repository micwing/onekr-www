package onekr.biz.user.intf;

import onekr.biz.model.User;
import onekr.biz.model.UserPassword;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserLoginBiz {
	
	User findUserByName(@NotBlank String loginName);
	
	UserPassword findUserPasswordByName(@NotBlank String loginName);
}

