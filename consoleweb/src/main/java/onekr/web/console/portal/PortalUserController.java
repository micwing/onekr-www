package onekr.web.console.portal;
//package onekr.web.sysmanage;
//
//import java.util.List;
//
//import onekr.commonservice.model.User;
//import onekr.commonservice.user.intf.UserBiz;
//import onekr.framework.result.Result;
//import onekr.framework.springmvc.annotation.RequestJsonParam;
//import onekr.web.base.BaseController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@Controller
//@RequestMapping(value = "/user")
//public class UserController extends BaseController {
//	
//	@Autowired
//	private UserBiz userBiz;
//	
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView index() {
//		ModelAndView mav = new ModelAndView(LAYOUT+"user/user");
//		return mav;
//	}
//	
//	@RequestMapping(value = "/list", method = RequestMethod.POST)
//	@ResponseBody
//	public Page<User> list(
//			@RequestParam(value = "loginName", required = false) String loginName, 
//			@RequestParam(value = "username", required = false) String name, 
//			Pageable pageRequest) {
//		Page<User> page = userBiz.listUser(loginName, name, pageRequest);
//		return page;
//	}
//	
//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//	public ModelAndView add() {
//		ModelAndView mav = new ModelAndView(LAYOUT+"user/modifyUser");
//		return mav;
//	}
//	
//	@RequestMapping(value = "/isLoginNameNoUse", method = RequestMethod.POST)
//	@ResponseBody
//	public boolean isLoginNameNoUse(
//			@RequestParam(value = "id") Long excludeId,
//			@RequestParam(value = "loginName") String loginName) {
//		User user = userBiz.findUser(loginName);
//		if (user == null) {			
//			return true;
//		}
//		if (user.getId().equals(excludeId)) {
//			return true;
//		}
//		return false;
//	}
//	
//	@RequestMapping(value = "/modify/{userId}",method = RequestMethod.GET)
//	public ModelAndView modify(@PathVariable("userId") Long userId) {
//		ModelAndView mav = new ModelAndView(LAYOUT+"user/modifyUser");
//		mav.addObject("user", userBiz.findUser(userId));
//		return mav;
//	}
//	
//	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
//	@ResponseBody
//	public Result doSave(User user) {
//		setRequestInfo("user.modifyUser", "saveBtn");
//		user = userBiz.saveUser(user);
//		Result result = new Result();
//		result.setValue(user);
//		return result;
//	}
//	
//	@RequestMapping(value = "/doSaveUserCarrier", method = RequestMethod.POST)
//	@ResponseBody
//	public Result doSaveUserCarrier(
//			@RequestJsonParam("ids") List<Long> ids,
//			@RequestParam("carrierId") Long carrierId) {
//		userBiz.doSaveUserCarrier(ids, carrierId);
//		Result result = new Result();
//		return result;
//	}
//	
//	@RequestMapping(value = "/doLock/{lockValue}", method = RequestMethod.POST)
//	@ResponseBody
//	public Result doLock(
//			@RequestJsonParam("ids") List<Long> ids, 
//			@PathVariable("lockValue") String lockValue) {
//		if (lockValue != null && "T".equals(lockValue))
//			setRequestInfo("user.user", "lockBtn");
//		else
//			setRequestInfo("user.user", "unlockBtn");
//		userBiz.lockUser(ids, lockValue);
//		Result result = new Result();
//		return result;
//	}
//	
//	@RequestMapping(value = "/findByIds", method = RequestMethod.POST)
//	@ResponseBody
//	public List<User> findByIds(@RequestJsonParam("ids") List<Long> ids) {
//		List<User> users = userBiz.findUsers(ids);
//		return users;
//	}
//}
