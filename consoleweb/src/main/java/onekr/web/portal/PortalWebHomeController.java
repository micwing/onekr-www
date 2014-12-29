package onekr.web.portal;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.model.Card;
import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.framework.controller.BaseController;
import onekr.portalservice.utils.GlobalConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 门户页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/portal")
public class PortalWebHomeController extends BaseController {
	
	@Autowired
	private ConfigBiz configBiz;
	@Autowired
	private CardBiz cardBiz;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("portalweb:home");
		mav.addObject("HOME_SLIDER", 
				configBiz.findConfig(Biz.SYSTEM, GlobalConstants.OWNER_HOME_SLIDER));
		return mav;
	}
	
	@RequestMapping(value = "/buymakecode", method = RequestMethod.GET)
	public ModelAndView buymakecode() {
		ModelAndView mav = new ModelAndView("portalweb:order-buy-makecode");
		return mav;
	}
	
	@RequestMapping(value = "/templatelist", method = RequestMethod.GET)
	public ModelAndView templatelist() {
		ModelAndView mav = new ModelAndView("portalweb:template-list");
		return mav;
	}
	
	@RequestMapping(value = "/frame/{cardId}", method = RequestMethod.GET)
	public ModelAndView frame(@PathVariable("cardId") Long cardId) {
		Card card = cardBiz.findById(cardId);
		ModelAndView mav = new ModelAndView("single:cardtpl/frame");
		mav.addObject("card", card);
		return mav;
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("portalweb:about");
		return mav;
	}
	
	@RequestMapping(value = "/product/introduce", method = RequestMethod.GET)
	public ModelAndView productIntroduce() {
		ModelAndView mav = new ModelAndView("portalweb:productIntroduce");
		return mav;
	}
}
