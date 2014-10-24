package onekr.web.portal;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.framework.controller.BaseController;
import onekr.portalservice.leaveComment.intf.LeaveCommentBiz;
import onekr.portalservice.utils.GlobalConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private LeaveCommentBiz leaveCommentBiz;
	@Autowired
	private ConfigBiz configBiz;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("portal:/home");
		mav.addObject("HOME_SLIDER", 
				configBiz.findConfig(Biz.PORTAL_SYSTEM, GlobalConstants.OWNER_HOME_SLIDER));
		return mav;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("portal:/leaveComment");
		mav.addObject("comments", leaveCommentBiz.listAll());
		return mav;
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("portal:/about");
		return mav;
	}
	
	@RequestMapping(value = "/product/introduce", method = RequestMethod.GET)
	public ModelAndView productIntroduce() {
		ModelAndView mav = new ModelAndView("portal:/productIntroduce");
		return mav;
	}
}
