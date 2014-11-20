package onekr.web.console.card;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.model.Card;
import onekr.web.console.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card/2dcode")
public class Card2dCodeController extends ConsoleBaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@RequestMapping(value = "/index/{cardId}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-2dcode");
		Card card = cardBiz.findById(cardId);
		mav.addObject("card", card);
		return mav;
	}
	
}
