package onekr.web.card;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.card.intf.CardDto;
import onekr.cardservice.model.Card;
import onekr.web.base.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card")
public class CardController extends BaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@RequestMapping(value = "/{cardId}/cover", method = RequestMethod.GET)
	public ModelAndView cover(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView(CARD+"cover");
		Card card = cardBiz.findById(cardId);
		mav.addObject("card", card);
		return mav;
	}
	
	@RequestMapping(value = "/{cardId}/main", method = RequestMethod.GET)
	public ModelAndView main(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView(CARD+"main");
		CardDto dto = cardBiz.findCardInfo(cardId);
		mav.addObject("dto", dto);
		return mav;
	}
	
}
