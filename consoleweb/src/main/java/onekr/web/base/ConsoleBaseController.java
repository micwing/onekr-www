package onekr.web.base;

import onekr.framework.controller.BaseController;
import onekr.identityservice.model.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;

public class ConsoleBaseController extends BaseController {

	protected User getCurrentUser() {
		PrincipalCollection pc = SecurityUtils.getSubject().getPrincipals();
		return (User) pc.getPrimaryPrincipal();
	}
}
