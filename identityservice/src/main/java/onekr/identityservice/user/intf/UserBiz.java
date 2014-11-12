package onekr.identityservice.user.intf;

import javax.validation.constraints.NotNull;

import onekr.identityservice.model.User;

import org.springframework.validation.annotation.Validated;

@Validated
public interface UserBiz {
	
	User findById(@NotNull Long id);
	
	User getAnonymous();
	
	Long getAnonymousId();
}