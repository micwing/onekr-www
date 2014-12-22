/**
 * @Project: web-controller
 * @File: GlobalController.java
 * @package onekr.web.base
 * @Description:
 * @author micwing
 * @date 2013-3-27 下午5:37:42
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

import onekr.framework.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * @ClassName: GlobalController 
 * @Description: 
 * @author micwing
 * @date 2013-3-27 下午5:37:42 
 */
@Controller
@RequestMapping(value = "/global")
public class GlobalController extends BaseController{
	
	@RequestMapping(value = "/noPermissions", method = RequestMethod.GET)
	public String noPermissions() {
		return "single:common/noPermissions";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String pageNotFound() {
		return "single:common/404";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "single:common/error";
	}

}
