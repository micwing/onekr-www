package onekr.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.model.Card;
import onekr.framework.controller.BaseInterceptor;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;

public class ConsoleInterceptor extends BaseInterceptor {
	
	@Autowired
	private CardBiz cardBiz;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (request.getParameterMap().containsKey("cardId")) {
			
			Long cardId = Long.parseLong(request.getParameter("cardId"));
			
			Card card = cardBiz.findById(cardId);
			if (card == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
		}
		return super.preHandle(request, response, handler);
	}
	
}
