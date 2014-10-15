package onekr.web.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import onekr.commonservice.common.intf.ConfigBiz;
import onekr.commonservice.model.Config;
import onekr.portalservice.utils.GlobalConstants;
import onekr.web.base.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/domain")
public class ConsoleDomainController extends BaseController {
	@Autowired
	private ConfigBiz configBiz;
	
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public ModelAndView baseConfig() {
		ModelAndView mav = new ModelAndView(CONSOLE+"domain-config");
		List<String> owners = new ArrayList<String>();
		owners.add(GlobalConstants.OWNER_DOMAIN_SEARCHER_NAME);
		owners.add(GlobalConstants.OWNER_DOMAIN_WHOIS_SEARCHER_NAME);
		
		Map<String, Config> configs = configBiz.findConfigs(GlobalConstants.BIZ_SYSTEM, owners);
		String searchName = "";
		String whoisSearchName = "";
		if (configs.containsKey(GlobalConstants.OWNER_DOMAIN_SEARCHER_NAME)) {			
			searchName = configs.get(GlobalConstants.OWNER_DOMAIN_SEARCHER_NAME).getValue();
		}
		if (configs.containsKey(GlobalConstants.OWNER_DOMAIN_WHOIS_SEARCHER_NAME)) {			
			whoisSearchName = configs.get(GlobalConstants.OWNER_DOMAIN_WHOIS_SEARCHER_NAME).getValue();
		}
		mav.addObject("searcherName", searchName);
		mav.addObject("whoisSearcherName", whoisSearchName);
		return mav;
	}
	
}

