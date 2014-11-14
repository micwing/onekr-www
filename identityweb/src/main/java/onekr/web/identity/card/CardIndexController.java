package onekr.web.identity.card;

import onekr.web.identity.IdentityBaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card/index")
public class CardIndexController extends IdentityBaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("card:index");
		return mav;
	}
	
}
