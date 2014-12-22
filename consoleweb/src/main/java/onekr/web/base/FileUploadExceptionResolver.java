package onekr.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class FileUploadExceptionResolver implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		response.setCharacterEncoding("UTF-8");
		
		if (ex instanceof MaxUploadSizeExceededException) {
			Result result = new Result(ErrorCode.RANGE_ERROR);
			result.setMessage("单个上传文件的大小超过限制");
			ModelAndView mav = new ModelAndView("single:common/error");
			mav.addObject("result", result);
			return mav;
		}
		
		return null;
	}

}
