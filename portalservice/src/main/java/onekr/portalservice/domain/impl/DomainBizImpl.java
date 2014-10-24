package onekr.portalservice.domain.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.commonservice.model.Config;
import onekr.framework.utils.PinyinUtil;
import onekr.portalservice.domain.dao.DomainSpecificDao;
import onekr.portalservice.domain.dto.DomainDto;
import onekr.portalservice.domain.intf.DomainBiz;
import onekr.portalservice.utils.GlobalConstants;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class DomainBizImpl implements DomainBiz,InitializingBean {
	private Map<String, DomainSeach> domainSeacherMap;
	@Autowired
	private ConfigBiz configBiz;
	@Autowired
	private DomainSpecificDao domainSpecificDao;
	
	@Qualifier("PandaDomainSeacher")
	@Autowired
	private DomainSeach pandaDomainSeacher;
	
	@Qualifier("QiuyumiDomainSeacher")
	@Autowired
	private DomainSeach qiuyumiDomainSeacher;
	
	@Qualifier("WwwWhoisDomainSeacher")
	@Autowired
	private DomainSeach wwwWhoisDomainSeacher;
	
	@Qualifier("InternicDomainSeacher")
	@Autowired
	private DomainSeach internicDomainSeacher;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		domainSeacherMap = new HashMap<String, DomainSeach>();
		domainSeacherMap.put(GlobalConstants.DOMAIN_PANDA_DOMAIN_SEACHER, pandaDomainSeacher);
		domainSeacherMap.put(GlobalConstants.DOMAIN_QIUYUMI_DOMAIN_SEACHER, qiuyumiDomainSeacher);
		domainSeacherMap.put(GlobalConstants.DOMAIN_WWW_WHOIS_COM_SEACHER, wwwWhoisDomainSeacher);
		domainSeacherMap.put(GlobalConstants.DOMAIN_INTERNIC_SEACHER, internicDomainSeacher);
	}
	
	private DomainSeach getJudgeSearcher() {
		Config config = configBiz.findConfig(
				Biz.PORTAL_SYSTEM, GlobalConstants.OWNER_DOMAIN_SEARCHER_NAME);
		String seacherName = GlobalConstants.DOMAIN_DEFAULT_DOMAIN_SEARCHER_NAME;
		if (config != null) {
			seacherName = config.getValue();
		}
		return domainSeacherMap.get(seacherName);
	}
	
	private DomainSeach getWhoisSearcher() {
		Config config = configBiz.findConfig(
				Biz.PORTAL_SYSTEM, GlobalConstants.OWNER_DOMAIN_WHOIS_SEARCHER_NAME);
		String seacherName = GlobalConstants.DOMAIN_DEFAULT_DOMAIN_SEARCHER_NAME;
		if (config != null) {
			seacherName = config.getValue();
		}
		return domainSeacherMap.get(seacherName);
	}
	
	/**
	 * DOMAIN_GROUP_TYPE_LIST.add("PINYINZIDIAN");
		DOMAIN_GROUP_TYPE_LIST.add("1PINYIN");
		DOMAIN_GROUP_TYPE_LIST.add("2PINYIN");
		DOMAIN_GROUP_TYPE_LIST.add("3PINYIN");
		DOMAIN_GROUP_TYPE_LIST.add("4PINYIN");
		DOMAIN_GROUP_TYPE_LIST.add("5PINYIN");
		
		DOMAIN_GROUP_TYPE_LIST.add("1SHUZI");
		DOMAIN_GROUP_TYPE_LIST.add("2SHUZI");
		DOMAIN_GROUP_TYPE_LIST.add("3SHUZI");
		
		DOMAIN_GROUP_TYPE_LIST.add("1ZIMU");
		DOMAIN_GROUP_TYPE_LIST.add("2ZIMU");
		
		DOMAIN_GROUP_TYPE_LIST.add("YINGWENDANCI");
	 */
	@Override
	public List<DomainDto> listDomains4Group(String first, String second,
			String atype, String suffix) {
		List<DomainDto> list = new ArrayList<DomainDto>();
		if (atype.equals("PINYINZIDIAN")) {
 			for (String i : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("PINYINZIDIAN2")) {
			for (String i : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+i+second+"."+suffix);
				dto.setName(first+i+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("1PINYIN")) {
			for (String i : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
				if (i.length() != 1)
					continue;
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("2PINYIN")) {
			for (String i : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
				if (i.length() != 2)
					continue;
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("3PINYIN")) {
			for (String i : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
				if (i.length() != 3)
					continue;
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("4PINYIN")) {
			for (String i : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
				if (i.length() != 4)
					continue;
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("5PINYIN")) {
			for (String i : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
				if (i.length() != 5)
					continue;
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("1SHUZI")) {
			for (int i = 0; i < 10; i++) {
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("2SHUZI")) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					DomainDto dto = new DomainDto();
					dto.setDomain(first+i+j+second+"."+suffix);
					dto.setName(first+i+j+second);
					dto.setSuffix(suffix);
					list.add(dto);
				}
			}
		} else if (atype.equals("3SHUZI")) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 10; k++) {
						DomainDto dto = new DomainDto();
						dto.setDomain(first+i+j+k+second+"."+suffix);
						dto.setName(first+i+j+k+second);
						dto.setSuffix(suffix);
						list.add(dto);
					}
				}
			}
		} else if (atype.equals("1ZIMU")) {
			for (String i : GlobalConstants.DOMAIN_ALL_ZIMU_ARRAY) {
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		} else if (atype.equals("2ZIMU")) {
			for (String i : GlobalConstants.DOMAIN_ALL_ZIMU_ARRAY) {
				for (String j : GlobalConstants.DOMAIN_ALL_ZIMU_ARRAY) {
					DomainDto dto = new DomainDto();
					dto.setDomain(first+i+j+second+"."+suffix);
					dto.setName(first+i+j+second);
					dto.setSuffix(suffix);
					list.add(dto);
				}
			}
		} else if (atype.equals("YINGWENDANCI")) {
			for (String i : GlobalConstants.DOMAIN_ALL_YINGWEN_ARRAY) {
				DomainDto dto = new DomainDto();
				dto.setDomain(first+i+second+"."+suffix);
				dto.setName(first+i+second);
				dto.setSuffix(suffix);
				list.add(dto);
			}
		}
		return list;
	}
	
	@Override
	public DomainDto queryJudgeDomain(String domain) {
		if (!Pattern.matches(GlobalConstants.DOMAIN_REGULAR_EXPRESSION, domain)) {
			DomainDto dto = new DomainDto();
			dto.setDomain(domain);
			return dto;
		}
		DomainSeach searcher = getJudgeSearcher();
		DomainDto dto = searcher.search(domain);
		if (dto != null && dto.getAvailable() != null) {
			
			String name = dto.getDomain().substring(0, domain.indexOf("."));
			String baidu = queryBaiduSuggest(name);
			if (!StringUtils.isEmpty(baidu)) {			
				dto.setBaiduSuggest(baidu);
				dto.setRecommend(queryRecommend(name, baidu));
			}
		}
		return dto;
	}
	
	private Boolean queryRecommend(String name,String baiduSuggest) {
		return name.equalsIgnoreCase(PinyinUtil.cn2Spell(baiduSuggest.trim()));
	}
	
	private String queryBaiduSuggest(String name) {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.baidu.com/s?wd="+name)
					.userAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.timeout(5000).get();
		} catch (IOException e) {
			return "";
		}  
		
		Elements elements = doc.select(".hit_top strong em:eq(0)");
		Elements elements2 = doc.select(".hit_top .jc a:eq(0)");
		
		String text = null;
	    if (elements.size() > 0) {
	    	text = elements.get(0).text();
	    } else if (elements2.size() > 0) {
	    	text = elements2.get(0).text();
	    }
	    return StringUtils.isEmpty(text) ? "":text;
	}
	
	@Override
	public DomainDto queryDomainWhois(String domain) {
		if (!Pattern.matches(GlobalConstants.DOMAIN_REGULAR_EXPRESSION, domain)) {
			DomainDto dto = new DomainDto();
			dto.setDomain(domain);
			return dto;
		}
		DomainSeach searcher = getWhoisSearcher();
		return searcher.search(domain);
	}
	
	@Override
	public List<DomainDto> listDomains4Expired(String date, String key,
			Integer keyPos, List<String> suffix, Integer minlength,
			Integer maxlength, Integer pinyinType, List<String> textType) {
		List<DomainDto> lst = domainSpecificDao.listExpiredDomain(
				date, key, keyPos, suffix, minlength, maxlength, pinyinType, textType);
		return lst;
	}
}
