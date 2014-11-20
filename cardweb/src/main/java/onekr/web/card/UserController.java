package onekr.web.card;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardPhotoFileBiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardPhotoFileBiz cardFileBiz;
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("user:info");
		return mav;
	}
	
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public ModelAndView modifyPassword() {
		ModelAndView mav = new ModelAndView("user:modifyPassword");
		return mav;
	}
	
	@RequestMapping(value = "/doModifyPassword", method = RequestMethod.GET)
	public ModelAndView doModifyPassword() {
		ModelAndView mav = new ModelAndView("user:modifyPassword");
		return mav;
	}
}
