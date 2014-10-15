package onekr.framework.springframework.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.framework.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

public class AppExceptionResolver implements HandlerExceptionResolver {
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		response.setCharacterEncoding("UTF-8");
		
		if (handler instanceof HandlerMethod) {
			
			HandlerMethod hm = (HandlerMethod) handler;
			
			if (hm.getMethod().isAnnotationPresent(ResponseBody.class)) {
				if (ex instanceof AppException) {
					AppException aex = (AppException)ex;
					try {
						PrintWriter writer = response.getWriter();
						Result result = new Result(aex);
						result.setMessage(messageSource.getMessage(Constants.ERROR_MESSAGE_PREFIX+result.getCode(),null, Locale.CHINESE));
						writer.write(JSON.toJSONString(result));
						writer.flush();
					} catch (IOException e) {
						//ignore
					}
					return null;
				} else {
					try {
						PrintWriter writer = response.getWriter();
						Result result = new Result(ErrorCode.SERVER_ERROR);
						writer.write(JSON.toJSONString(result));
						writer.flush();
					} catch (IOException e) {
						//ignore
					}
					return null;
				}
				
			} else {
				
				if (ex instanceof AppException) {
					AppException aex = (AppException)ex;
					Result result = new Result(aex);
					result.setMessage(messageSource.getMessage(Constants.ERROR_MESSAGE_PREFIX+result.getCode(),null, Locale.CHINESE));
					ModelAndView mav = new ModelAndView("single:common/error");
					mav.addObject("result", result);
					return mav;
				} else {
					ModelAndView mav = new ModelAndView("single:common/error");
					mav.addObject("result", new Result(1, ex.toString()));
					return mav;
				}
			}
		} else {
			return null;
		}
	}

}
