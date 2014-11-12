package onekr.web.console.identity;

import onekr.web.console.ConsoleBaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/account")
public class IdentityAccountController extends ConsoleBaseController {
	
	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public ModelAndView accountInfo() {
		ModelAndView mav = new ModelAndView("portal:accountInfo");
		return mav;
	}
	
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public ModelAndView modifyPassword() {
		ModelAndView mav = new ModelAndView("portal:modifyPassword");
		return mav;
	}
	
}
