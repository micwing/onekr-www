package onekr.web.identity;

import onekr.framework.controller.BaseController;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;

public class IdentityBaseController extends BaseController {

	protected Object getCurrentUser() {
		PrincipalCollection pc = SecurityUtils.getSubject().getPrincipals();
		return pc.getPrimaryPrincipal();
	}
}
