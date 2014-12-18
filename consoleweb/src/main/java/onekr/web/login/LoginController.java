package onekr.web.login;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.framework.verifycode.SunImageValidateCodeServlet;
import onekr.identityservice.model.Group;
import onekr.identityservice.model.User;
import onekr.identityservice.user.intf.UserBiz;
import onekr.identityservice.user.intf.UserLoginBiz;
import onekr.web.console.ConsoleBaseController;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/login")
public class LoginController extends ConsoleBaseController {
	
	@Autowired
	private UserLoginBiz userLoginBiz;
	
	@Autowired
	private UserBiz userBiz;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "single:login/login";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "single:login/test";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "single:login/register";
	}
	
	@RequestMapping(value = "/findpassword", method = RequestMethod.GET)
	public String findpassword() {
		return "single:login/findpassword";
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ModelAndView resetpassword(
			@RequestParam("username") String username,
			@RequestParam("code") String code) {
		ModelAndView mav = new ModelAndView("single:login/resetpassword");
		
		if (!userBiz.validateFindPasswordCode(username, code)) {
			throw new AppException(ErrorCode.NO_PERMISSON);
		}
		mav.addObject("username", username);
		mav.addObject("code", code);
		return mav;
	}
	
	@RequestMapping(value = "/doSignin", method = RequestMethod.POST)
	@ResponseBody
	public Result doSignin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username") String credential,
			@RequestParam("password") String password,
			@RequestParam(value="remember",required=false) String remember) {
		
		if (!SunImageValidateCodeServlet.noValidateTimes(request) && !SunImageValidateCodeServlet.validate(request)) {
			//超过了免校验的次数，并且验证码错误，则提示错误结果
			return new Result(ErrorCode.VALIDATE_ERROR);
		}
		
		userLoginBiz.login(credential, password, remember);
		
		//重置免校验次数
		SunImageValidateCodeServlet.noValidateTimesReset(request);
		
		return new Result(ErrorCode.SUCCEED);
	}
	
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	@ResponseBody
	public Result doRegister(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email) {
		
		if (!SunImageValidateCodeServlet.validate(request)) {
			return new Result(ErrorCode.VALIDATE_ERROR);
		}
		userBiz.register(username, password, email, null, Group.USER);
		return new Result(ErrorCode.SUCCEED);
	}
	
	@RequestMapping(value = "/doFindPassword", method = RequestMethod.POST)
	@ResponseBody
	public Result doFindPassword(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username") String username,
			@RequestParam("email") String email) {
		
		if (!SunImageValidateCodeServlet.validate(request)) {
			return new Result(ErrorCode.VALIDATE_ERROR);
		}
		userBiz.findPassword(username, email);
		return new Result(ErrorCode.SUCCEED);
	}
	
	@RequestMapping(value = "/doResetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Result doResetPassword(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username") String username,
			@RequestParam("code") String code,
			@RequestParam("password") String password) {
		doSignout();
		
		if (!SunImageValidateCodeServlet.validate(request)) {
			return new Result(ErrorCode.VALIDATE_ERROR);
		}
		userBiz.resetPassword(username, password, code);
		return new Result(ErrorCode.SUCCEED);
	}
	
	@RequestMapping(value = "/doSignout", method = RequestMethod.GET)
	public String doSignout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		
		//清除session
		HttpSession session = getSession();
        Enumeration<?> em = session.getAttributeNames();
        while(em.hasMoreElements()){
        	session.removeAttribute(em.nextElement().toString());
        }
        session.invalidate();
        
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/getNoValidateTimes", method = RequestMethod.POST)
	@ResponseBody
	public Result getNoValidateTimes(HttpServletRequest request,
			HttpServletResponse response) {
		int times = SunImageValidateCodeServlet.getNoValidateTimes(request);
		Result result =  new Result();
		result.setValue(times);
		return result;
	}
	
	@RequestMapping(value = "/registerEmailAvailable", method = RequestMethod.POST)
	@ResponseBody
	public Boolean registerEmailAvailable(@RequestParam("email") String email) {
		User user = userLoginBiz.findUserByEmail(email);
		return user == null;
	}
	
	@RequestMapping(value = "/registerNameAvailable", method = RequestMethod.POST)
	@ResponseBody
	public Boolean registerNameAvailable(@RequestParam("username") String username) {
		User user = userLoginBiz.findUserByName(username);
		return user == null;
	}
}
