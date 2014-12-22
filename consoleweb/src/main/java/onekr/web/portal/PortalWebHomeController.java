package onekr.web.portal;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.framework.controller.BaseController;
import onekr.portalservice.utils.GlobalConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/portal")
public class PortalWebHomeController extends BaseController {
	
//	@Autowired
//	private LeaveCommentBiz leaveCommentBiz;
	@Autowired
	private ConfigBiz configBiz;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("portalweb:home");
		mav.addObject("HOME_SLIDER", 
				configBiz.findConfig(Biz.SYSTEM, GlobalConstants.OWNER_HOME_SLIDER));
		return mav;
	}
	
	@RequestMapping(value = "/buymakecode", method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("portalweb:buymakecode");
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
