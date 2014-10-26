package onekr.web.console.card;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.model.Card;
import onekr.cardservice.model.CardType;
import onekr.commonservice.model.Status;
import onekr.framework.controller.BaseController;
import onekr.framework.contstants.Constants;
import onekr.identityservice.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/card/info")
public class CardInfoController extends BaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView("card:card-info");
		return mav;
	}
	
	@RequestMapping(value = "/modify/{cardId}", method = RequestMethod.GET)
	public ModelAndView modify(@PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-info");
		mav.addObject("card", cardBiz.findById(cardId));
		return mav;
	}
	
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public ModelAndView doSave(Card card) {
		User user = (User) getCurrentUser();
		card = cardBiz.saveCard(card, user.getId());
		ModelAndView mav = new ModelAndView("card:card-info");
		mav.addObject("card", card);
		return mav;
	}
	
	@RequestMapping(value = "/doSaveGotoPhoto", method = RequestMethod.POST)
	public ModelAndView doSaveGotoPhoto(Card card) {
		ModelAndView mav = doSave(card);
		mav.setViewName("card:card-photo");
		return mav;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(CardType cardType, Status status, Pageable pageable) {
		if (cardType == null)
			cardType = CardType.WED_CARD;
		if (status == null)
			status = Status.NORMAL;
		if (pageable == null)
			pageable = new PageRequest(0, Constants.PAGE_DEFAULT_SIZE);
		Page<Card> page = cardBiz.listCard(cardType, status, pageable);
		ModelAndView mav = new ModelAndView("card:card-list");
		mav.addObject("page", page);
		mav.addObject("cardType", cardType);
		mav.addObject("status", status);
		return mav;
	}
}
