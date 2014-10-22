package onekr.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView mav) throws Exception {
		if (mav == null)
			mav = new ModelAndView();
		if (request != null && request.getServletPath() != null)
			mav.addObject("requestServletPath", request.getServletPath()+"/");
		
	}
}
