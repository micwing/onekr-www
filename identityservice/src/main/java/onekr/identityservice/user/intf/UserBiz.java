package onekr.identityservice.user.intf;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.identityservice.model.Group;
import onekr.identityservice.model.User;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserBiz {
	
	User findById(@NotNull @Min(1) Long id);
	
	User getAnonymous();
	
	Long getAnonymousId();
	
	User register(@NotBlank String name,@NotBlank String pwd, String email, String mobile,@NotNull Group group);
	
}