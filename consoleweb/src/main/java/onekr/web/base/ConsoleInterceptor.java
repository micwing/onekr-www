package onekr.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onekr.cardservice.card.intf.CardBiz;
import onekr.cardservice.model.Card;
import onekr.framework.controller.BaseInterceptor;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.identityservice.model.Group;
import onekr.identityservice.model.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
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
			
			//普通用户只能管理自己的请柬，管理员可以管理全部请柬
			User user = getCurrentUser();
			if (!Group.ADMINISTRATOR.equals(user.getGroup())) {
				if (!user.getId().equals(card.getCreateBy())) {
					throw new AppException(ErrorCode.NO_PERMISSON);
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
	
	protected User getCurrentUser() {
		if (SecurityUtils.getSubject() == null)
			return null;
		PrincipalCollection pc = SecurityUtils.getSubject().getPrincipals();
		return (User) pc.getPrimaryPrincipal();
	}
	
}
