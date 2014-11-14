package onekr.web.identity.card;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.model.Card;
import onekr.identityservice.model.User;
import onekr.web.identity.IdentityBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card/map")
public class CardMapController extends IdentityBaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@RequestMapping(value = "/cardmap/{cardId}", method = RequestMethod.GET)
	public ModelAndView cardmap(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-map");
		mav.addObject("card", cardBiz.findById(cardId));
		return mav;
	}
	
	@RequestMapping(value = "/doUpdateMap", method = RequestMethod.POST)
	public String doUpdateMap(Long cardId, String mapUrl, String mapPicUrl) {
		User user = (User) getCurrentUser();
		Card card = cardBiz.updateCardMap(cardId, mapPicUrl, mapUrl, user.getId());
		return "redirect:/card/map/cardmap/"+card.getId();
	}
	
}
