package onekr.web.identity.account;

import onekr.web.identity.IdentityBaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/identity/dashboard")
public class DashboardController extends IdentityBaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView accountInfo() {
		ModelAndView mav = new ModelAndView("identity:dashboard");
		return mav;
	}
	
}
