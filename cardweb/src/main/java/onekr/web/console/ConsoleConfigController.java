package onekr.web.console;

import java.util.List;

import onekr.commonservice.common.intf.ConfigBiz;
import onekr.commonservice.model.Config;
import onekr.framework.result.Result;
import onekr.framework.spring.web.annotation.RequestJsonParam;
import onekr.web.base.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/config")
public class ConsoleConfigController extends BaseController {
	@Autowired
	private ConfigBiz configBiz;
	
	@RequestMapping(value = "/baseConfig", method = RequestMethod.GET)
	public ModelAndView baseConfig() {
		ModelAndView mav = new ModelAndView(CONSOLE+"baseConfig");
		//TODO
//		List<String> owners = new ArrayList<String>();
//		owners.add(GlobalConstants.OWNER_HOME_SLIDER);
//		Map<String, Config> configs = configBiz.findConfigs(GlobalConstants.BIZ_SYSTEM, owners);
//		mav.addObject("configs", configs);
		return mav;
	}
	
	@RequestMapping(value = "/doSaveConfig", method = RequestMethod.POST)
	@ResponseBody
	public Result doSaveBaseConfig(@RequestJsonParam("configs") List<Config> configs) {
		configBiz.updateConfigs(configs);
		return new Result();
	}
	
}

