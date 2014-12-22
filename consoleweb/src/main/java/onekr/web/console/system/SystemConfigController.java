package onekr.web.console.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.biz.ConfigOwner;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.commonservice.model.Config;
import onekr.framework.result.Result;
import onekr.framework.spring.web.annotation.RequestJsonParam;
import onekr.web.base.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/system/config")
public class SystemConfigController extends ConsoleBaseController {
	@Autowired
	private ConfigBiz configBiz;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "redirect:/console/system/config/normalConfig";
	}
	
	@RequestMapping(value = "/normalConfig", method = RequestMethod.GET)
	public ModelAndView normalConfig() {
		ModelAndView mav = new ModelAndView("system:normalConfig");
		return mav;
	}
	
	@RequestMapping(value = "/emailConfig", method = RequestMethod.GET)
	public ModelAndView emailConfig() {
		ModelAndView mav = new ModelAndView("system:emailConfig");
		List<String> owners = new ArrayList<String>();
		owners.add(ConfigOwner.SYSTEM_EMAIL_DEFAULTENCODING.name());
		owners.add(ConfigOwner.SYSTEM_EMAIL_PASSWORD.name());
		owners.add(ConfigOwner.SYSTEM_EMAIL_PORT.name());
		owners.add(ConfigOwner.SYSTEM_EMAIL_SERVER.name());
		owners.add(ConfigOwner.SYSTEM_EMAIL_USERNAME.name());
		Map<String, Config> configs = configBiz.findConfigs(Biz.SYSTEM, owners);
		mav.addObject("configs", configs);
		return mav;
	}
	
	@RequestMapping(value = "/doSaveConfig", method = RequestMethod.POST)
	@ResponseBody
	public Result doSaveBaseConfig(@RequestJsonParam("configs") List<Config> configs) {
		configBiz.updateConfigs(configs);
		return new Result();
	}
	
}

