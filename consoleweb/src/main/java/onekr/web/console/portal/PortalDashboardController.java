package onekr.web.console.portal;

import onekr.web.base.ConsoleBaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/dashboard")
public class PortalDashboardController extends ConsoleBaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("portal:dashboard");
		return mav;
	}
	
}
