package onekr.web.portal;
//package onekr.web.portal;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import onekr.framework.controller.BaseController;
//import onekr.framework.result.Result;
//import onekr.framework.utils.DateUtil;
//import onekr.portalservice.domain.dto.DomainDto;
//import onekr.portalservice.domain.intf.DomainBiz;
//import onekr.portalservice.utils.GlobalConstants;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.time.DateUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@Controller
//@RequestMapping(value = "/domain")
//public class WebDomainController extends BaseController {
//	
//	@Autowired
//	private DomainBiz domainBiz;
//	
//	@RequestMapping(value = "/group", method = RequestMethod.GET)
//	public ModelAndView groupQuery(
//			@RequestParam(value = "first", required = false) String first,
//			@RequestParam(value = "second", required = false) String second,
//			@RequestParam(value = "atype", required = false) String atype,
//			@RequestParam(value = "suffix", required = false) String suffix) {
//		first = first == null ? "" : first;
//		first = first.toLowerCase();
//		
//		second = second == null? "" : second;
//		second = second.toLowerCase();
//		
//		atype = atype == null?"" : atype;
//		suffix = suffix == null?"":suffix;
//		ModelAndView mav = new ModelAndView("portalweb:domain-group");
//		mav.addObject("first", first);
//		mav.addObject("second", second);
//		mav.addObject("atype", atype);
//		mav.addObject("suffix", suffix);
//		if (StringUtils.isEmpty(atype)) {
//			return mav;
//		}
//		if (StringUtils.isEmpty(suffix)) {
//			return mav;
//		}
//		if (!GlobalConstants.DOMAIN_GROUP_TYPE_LIST.contains(atype)) {
//			return mav;
//		}
//		List<DomainDto> domainDtoList = domainBiz.listDomains4Group(first, second, atype, suffix);
//		mav.addObject("domainDtoList", domainDtoList);
//		return mav;
//	}
//	
//	@RequestMapping(value = "/whois", method = RequestMethod.GET)
//	public ModelAndView whoisQuery(@RequestParam(value = "domain", required = false) String domain) {
//		if (StringUtils.isEmpty(domain)) {
//			domain = GlobalConstants.DOMAIN_DEFAULT_SEACH_DOMAIN;
//		}
//		domain = domain.toLowerCase();
//		
//		ModelAndView mav = new ModelAndView("portalweb:domain-whois");
//		DomainDto dto = domainBiz.queryDomainWhois(domain);
//		mav.addObject("dto", dto);
//		return mav;
//	}
//	
//	@RequestMapping(value = "/suffix", method = RequestMethod.GET)
//	public ModelAndView suffixQuery(
//			@RequestParam(value = "name", required = false) String name,
//			@RequestParam(value = "suffix", required = false) List<String> suffix) {
//		if (StringUtils.isEmpty(name)) {
//			name = GlobalConstants.DOMAIN_DEFAULT_SEACH_DOMAIN_NAME;
//		}
//		name = name.toLowerCase();
//		
//		if (CollectionUtils.isEmpty(suffix)) {
//			suffix = Collections.singletonList("com");
//		}
//		List<DomainDto> domainDtoList = new ArrayList<DomainDto>();
//		for (String suf : suffix) {
//			DomainDto dto = new DomainDto();
//			dto.setDomain(name+"."+suf);
//			dto.setName(name);
//			dto.setSuffix(suf);
//			domainDtoList.add(dto);
//		}
//		ModelAndView mav = new ModelAndView("portalweb:domain-suffix");
//		mav.addObject("name", name);
//		mav.addObject("domainDtoList", domainDtoList);
//		return mav;
//	} 
//	
//	@RequestMapping(value = "/expired", method = RequestMethod.GET)
//	public ModelAndView expiredQuery(
//			@RequestParam(value = "date", required = false) String date,
//			@RequestParam(value = "key", required = false) String key,
//			@RequestParam(value = "keypos", required = false) Integer keyPos,
//			@RequestParam(value = "suffix", required = false) List<String> suffix,
//			@RequestParam(value = "minlength", required = false) Integer minlength,
//			@RequestParam(value = "maxlength", required = false) Integer maxlength,
//			@RequestParam(value = "pinyintype", required = false) Integer pinyinType,
//			@RequestParam(value = "texttype", required = false) List<String> textType) {
//		Date now = new Date();
//		now = DateUtils.addHours(now, 12);
//		date = StringUtils.isEmpty(date) ? DateUtil.cnvDate2Str(now) : date;
//		
//		key = key == null ? "" : key;
//		key = key.toLowerCase();
//		
//		keyPos = keyPos == null ? 0 : keyPos;
//		
//		if (CollectionUtils.isEmpty(suffix)) {
//			suffix = new ArrayList<String>();
//			suffix.add("com");
//			suffix.add("net");
//		}
//		
//		if (minlength == null) {
//			minlength = 3;
//		}
//		
//		if (maxlength == null) {
//			maxlength = 8;
//		}
//		
//		if (pinyinType == null) {
//			pinyinType = 2;
//		}
//		
//		if (CollectionUtils.isEmpty(textType)) {
//			textType = new ArrayList<String>();
//			textType.add("1");
//		}
//		
//		List<DomainDto> domainDtoList = domainBiz.listDomains4Expired(
//				date, key, keyPos, suffix, minlength, maxlength, pinyinType, textType);
//		
//		ModelAndView mav = new ModelAndView("portalweb:domain-expired");
//		mav.addObject("date", date);
//		List<String> dates = new ArrayList<String>();
//		dates.add(DateUtil.cnvDate2Str(now));
//		dates.add(DateUtil.cnvDate2Str(DateUtil.addDate(now, -1)));
//		dates.add(DateUtil.cnvDate2Str(DateUtil.addDate(now, -2)));
//		dates.add(DateUtil.cnvDate2Str(DateUtil.addDate(now, -3)));
//		dates.add(DateUtil.cnvDate2Str(DateUtil.addDate(now, -4)));
//		mav.addObject("key", key);
//		mav.addObject("keyPos", keyPos);
//		mav.addObject("dates", dates);
//		mav.addObject("minlength", minlength);
//		mav.addObject("maxlength", maxlength);
//		mav.addObject("pinyinType", pinyinType);
//		mav.addObject("textType", textType);
//		mav.addObject("suffix", suffix);
//		mav.addObject("allSuffix", GlobalConstants.DOMAIN_ALL_SUFFIX);
//		mav.addObject("domainDtoList", domainDtoList);
//		return mav;
//	}
//	
//	@RequestMapping(value = "/domainAvailable", method = RequestMethod.POST)
//	@ResponseBody
//	public Result domainAvailable(String domain) {
//		Result result = new Result();
//		DomainDto dto = domainBiz.queryJudgeDomain(domain);
//		result.setValue(dto);
//		return result;
//	}
//	
//}
//
