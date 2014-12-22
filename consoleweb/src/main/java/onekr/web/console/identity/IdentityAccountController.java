package onekr.web.console.identity;

import onekr.identityservice.model.User;
import onekr.identityservice.user.intf.UserBiz;
import onekr.web.base.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/identity/account")
public class IdentityAccountController extends ConsoleBaseController {
	
	@Autowired
	private UserBiz userBiz;
	
	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public ModelAndView accountInfo() {
		ModelAndView mav = new ModelAndView("card:identity-accountInfo");
		User user = userBiz.findById(getCurrentUser().getId());
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public ModelAndView modifyPassword() {
		ModelAndView mav = new ModelAndView("card:identity-modifyPassword");
		return mav;
	}
	
}
