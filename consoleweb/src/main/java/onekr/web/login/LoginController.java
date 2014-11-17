package onekr.web.login;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
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
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "single:login/register";
	}
	
	@RequestMapping(value = "/doSignin", method = RequestMethod.POST)
	@ResponseBody
	public Result doSignin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username") String credential,
			@RequestParam("password") String password,
			@RequestParam(value="remember",required=false) String remember) {
		
		userLoginBiz.login(credential, password, remember);
		
		return new Result(ErrorCode.SUCCEED);
	}
	
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	@ResponseBody
	public Result doRegister(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email) {
		
		userBiz.register(username, password, email, null, Group.USER);
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
	
	
	@RequestMapping(value = "/registerNameAvailable", method = RequestMethod.POST)
	@ResponseBody
	public Boolean registerNameAvailable(@RequestParam("username") String username) {
		User user = userLoginBiz.findUserByName(username);
		return user == null;
	}
	
}
