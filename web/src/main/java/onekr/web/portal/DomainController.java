package onekr.web.portal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import onekr.biz.domain.dto.DomainDto;
import onekr.biz.domain.intf.DomainBiz;
import onekr.biz.utils.GlobalConstants;
import onekr.framework.result.Result;
import onekr.web.base.BaseController;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/domain")
public class DomainController extends BaseController {
	
	@Autowired
	private DomainBiz domainBiz;
	
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public ModelAndView groupQuery(
			@RequestParam(value = "first", required = false) String first,
			@RequestParam(value = "second", required = false) String second,
			@RequestParam(value = "atype", required = false) String atype,
			@RequestParam(value = "suffix", required = false) String suffix) {
		first = first == null ? "" : first;
		second = second == null? "" : second;
		atype = atype == null?"" : atype;
		suffix = suffix == null?"":suffix;
		ModelAndView mav = new ModelAndView(PORTAL+"/domain-group");
		mav.addObject("first", first);
		mav.addObject("second", second);
		mav.addObject("atype", atype);
		mav.addObject("suffix", suffix);
		if (StringUtils.isEmpty(atype)) {
			return mav;
		}
		if (StringUtils.isEmpty(suffix)) {
			return mav;
		}
		if (!GlobalConstants.DOMAIN_GROUP_TYPE_LIST.contains(atype)) {
			return mav;
		}
		List<DomainDto> domainDtoList = domainBiz.listDomains4Group(first, second, atype, suffix);
		mav.addObject("domainDtoList", domainDtoList);
		return mav;
	}
	
	@RequestMapping(value = "/whois", method = RequestMethod.GET)
	public ModelAndView whoisQuery(@RequestParam(value = "domain", required = false) String domain) {
		if (StringUtils.isEmpty(domain)) {
			domain = GlobalConstants.DOMAIN_DEFAULT_SEACH_DOMAIN;
		}
		ModelAndView mav = new ModelAndView(PORTAL+"/domain-whois");
		DomainDto dto = domainBiz.queryDomainWhois(domain);
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value = "/suffix", method = RequestMethod.GET)
	public ModelAndView suffixQuery(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "suffix", required = false) List<String> suffix) {
		if (StringUtils.isEmpty(name)) {
			name = GlobalConstants.DOMAIN_DEFAULT_SEACH_DOMAIN_NAME;
		}
		if (CollectionUtils.isEmpty(suffix)) {
			suffix = Collections.singletonList("com");
		}
		List<DomainDto> domainDtoList = new ArrayList<DomainDto>();
		for (String suf : suffix) {
			DomainDto dto = new DomainDto();
			dto.setDomain(name+"."+suf);
			dto.setName(name);
			dto.setSuffix(suf);
			domainDtoList.add(dto);
		}
		ModelAndView mav = new ModelAndView(PORTAL+"/domain-suffix");
		mav.addObject("name", name);
		mav.addObject("domainDtoList", domainDtoList);
		return mav;
	} 
	
	@RequestMapping(value = "/domainAvailable", method = RequestMethod.POST)
	@ResponseBody
	public Result domainAvailable(String domain) {
//		domain = "kuangtu.net";
		Result result = new Result();
//		String value = "";
		DomainDto dto = domainBiz.queryJudgeDomain(domain);
//		if (dto != null && dto.getAvailable() != null) {
//			if (dto.getAvailable()) {
//				value = "available";
//			} else {
//				value = "notAvailable";
//			}
//		}
		result.setValue(dto);
		return result;
	}
	
}

