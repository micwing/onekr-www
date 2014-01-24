package onekr.web.portal;

import onekr.biz.common.intf.ConfigBiz;
import onekr.biz.leaveComment.intf.LeaveCommentBiz;
import onekr.biz.utils.GlobalConstants;
import onekr.web.base.BaseController;

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
		ModelAndView mav = new ModelAndView(PORTAL+"/home");
		mav.addObject("HOME_SLIDER", 
				configBiz.findConfig(GlobalConstants.BIZ_SYSTEM, GlobalConstants.OWNER_HOME_SLIDER));
		return mav;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView(PORTAL+"/leaveComment");
		mav.addObject("comments", leaveCommentBiz.listAll());
		return mav;
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView(PORTAL+"/about");
		return mav;
	}
	
	@RequestMapping(value = "/product/introduce", method = RequestMethod.GET)
	public ModelAndView productIntroduce() {
		ModelAndView mav = new ModelAndView(PORTAL+"/productIntroduce");
		return mav;
	}
}
