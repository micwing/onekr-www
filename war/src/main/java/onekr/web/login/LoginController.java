package onekr.web.login;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onekr.biz.utils.ConverterUtils;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.web.base.BaseController;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String redirect() {
		return SINGLE+"login/login";
	}
	
	@RequestMapping(value = "/doSignin", method = RequestMethod.POST)
	@ResponseBody
	public Result doSignin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username") String credential,
			@RequestParam("password") String password,
			@RequestParam(value="remember",required=false) String remember) {
		
		UsernamePasswordToken token = new UsernamePasswordToken(credential, ConverterUtils.cvtPasswordStr(password));
		if (!StringUtils.isEmpty(remember)) {
			token.setRememberMe(true);
		}
		
		Subject currentUser = SecurityUtils.getSubject();  
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			throw new AppException(ErrorCode.USER_PSW_NOT_MATCH);
		}
		
		return new Result(ErrorCode.SUCCEED);
	}
	
//	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
//	public String redirect() {
//		return "redirect:/home/index";
//	}

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
	
	
//	@RequestMapping(value = "/signfind", method = RequestMethod.GET)
//	public String signfind() {
//		return "console/signin/signfind";
//	}
	
}
