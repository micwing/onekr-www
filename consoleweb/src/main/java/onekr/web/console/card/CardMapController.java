package onekr.web.console.card;

import onekr.cardservice.card.intf.CardBiz;
import onekr.framework.result.Result;
import onekr.identityservice.model.User;
import onekr.web.console.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/card/map")
public class CardMapController extends ConsoleBaseController {
	
	@Autowired
	private CardBiz cardBiz;
	
	@RequestMapping(value = "/cardmap/{cardId}", method = RequestMethod.GET)
	public ModelAndView cardmap(ModelMap model, @PathVariable("cardId") Long cardId) {
		ModelAndView mav = new ModelAndView("card:card-map", model);
		mav.addObject("card", cardBiz.findById(cardId));
		return mav;
	}
	
	@RequestMapping(value = "/doUpdateMap", method = RequestMethod.POST)
	public ModelAndView doUpdateMap(Long cardId, String mapUrl, String mapPicUrl) {
		User user = (User) getCurrentUser();
		cardBiz.updateCardMap(cardId, mapPicUrl, mapUrl, user.getId());
		return cardmap(new ModelMap("result", new Result("保存成功！")), cardId);
	}
	
}
