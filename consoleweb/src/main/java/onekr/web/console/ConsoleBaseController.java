package onekr.web.console;

import onekr.framework.controller.BaseController;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;

public class ConsoleBaseController extends BaseController {

	protected Object getCurrentUser() {
		PrincipalCollection pc = SecurityUtils.getSubject().getPrincipals();
		return pc.getPrimaryPrincipal();
	}
}
