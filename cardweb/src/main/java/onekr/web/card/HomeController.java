package onekr.web.card;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardPhotoFileBiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@Autowired
	private CardPhotoFileBiz cardFileBiz;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("single:portal/index");
		return mav;
	}
	
}
