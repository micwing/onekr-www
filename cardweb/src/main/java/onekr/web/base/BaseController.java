/**
 * @Project: web-controller
 * @File: BaseController.java
 * @package onekr.web.base
 * @Description:
 * @author micwing
 * @date 2013-3-26 下午5:41:09
 * @version V1.0
 *
 * Copyright (c) 2013 OneKr Soft Studio. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package onekr.web.base;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import onekr.framework.lang.NormalDateEditor;
import onekr.identityservice.model.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/** 
 * @ClassName: BaseController 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:41:09 
 */
public abstract class BaseController {
	@Autowired
	private MessageSource messageSource;
	
	public static final String CARD = "card:";
	public static final String CONSOLE = "console:";
	public static final String SINGLE = "single:";
	
	protected HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
		return ((ServletRequestAttributes) ra).getRequest();
	}
	
	protected HttpSession getSession() {
		HttpServletRequest request = getRequest();  
		HttpSession session = request.getSession();
		return session;
	}
	
	protected Locale getLocale() {
		return (Locale) getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
	}
	
	protected User getCurrentUser() {
		PrincipalCollection pc = SecurityUtils.getSubject().getPrincipals();
		return (User) pc.getPrimaryPrincipal();
	}
	
	protected String getMessage(String messageKey) {
		return messageSource.getMessage(messageKey,null,null, getLocale());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new NormalDateEditor());
	}
}
