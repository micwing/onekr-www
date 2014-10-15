package onekr.commonservice.domain.impl;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import onekr.commonservice.domain.dao.ExpiredDomainDao;
import onekr.commonservice.model.ExpiredDomain;
import onekr.commonservice.utils.GlobalConstants;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpiredDomainUpdater {
	
	private String address = "http://domain.webmasterhome.cn/todayDel_domain.asp";
	private String addressCn = "http://www.cnnic.cn/download/registar_list/1todayDel.txt";
	
	@Autowired
	private ExpiredDomainDao expiredDomainDao;
	
	public void execute() {
		String date = executeCom();
		executeCn(date);
	}
	
	public String executeCom() {
		Document doc = null;
		try {
			doc = Jsoup.connect(address)
					.userAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.timeout(5000).get();
		} catch (IOException e) {
			return null;
		}  
	    
	    String title = doc.select("#Head table .mainr pre strong").text();
	    String date = title.substring(0,10);
	    
	    String main = doc.select("#Head table .mainr pre").text().replace(title, "");
	    String[] domains = main.split("\n");
	    for (String domain : domains) {
	    	saveExpiredDomain(domain.trim(), date);
	    }
	    return date;
	}
	
	public void executeCn(String date) {
		Document doc = null;
		try {
			doc = Jsoup.connect(addressCn)
					.userAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.timeout(5000).get();
		} catch (IOException e) {
			return;
		}  
	    
	    String main = doc.select("body").text();
	    
	    String[] domains = main.split(" ");
	    for (String domain : domains) {
	    	if (domain.startsWith("[")) {
	    		saveExpiredDomain(domain.replace("[", "").replace("]", "").trim(), date);
	    	}
	    }
	}
	
	private void saveExpiredDomain(String domain,String date) {
		System.out.println(domain);
		int pointIndex = domain.indexOf(".");
		String domainBody = domain.substring(0, pointIndex);
		String suffix = domain.substring(pointIndex+1);
		ExpiredDomain entity = new ExpiredDomain();
		entity.setCreateAt(new Date());
		entity.setDeleteType(0);
		entity.setExpiredDate(date);
		entity.setName(domainBody);
		entity.setSuffix(suffix);
		entity.setPinyinType(workPinyinType(domainBody));
		entity.setTextType(workTextType(domainBody));
		expiredDomainDao.save(entity);
	}
	
	private Integer workTextType(String domainBody) {
		int type = 0;
		Matcher m=Pattern.compile(".*[a-zA-Z]+.*").matcher(domainBody);
		if (m.matches()) {
			type += 1;
		}
		
		m=Pattern.compile(".*[0-9]+.*").matcher(domainBody);
		if (m.matches()) {
			type += 10;
		}
		
		if (domainBody.contains("-")) {
			type += 100;
		}
		
		return type;
	}
	
	/*
	 * 判断拼音个数
	 */
	private Integer workPinyinType(String domainBody) {
		int type = 0;
		out: for (String p1 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
			String tmp1 = domainBody;
			if (tmp1.startsWith(p1)) {
				tmp1 = tmp1.substring(p1.length(), tmp1.length());
				if (StringUtils.isEmpty(tmp1)) {
					type += 1;
					break out;
				}
			}
		}
		
		out: for (String p1 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
			String tmp1 = domainBody;
			if (tmp1.startsWith(p1)) {
				tmp1 = tmp1.substring(p1.length(), tmp1.length());
				if (StringUtils.isEmpty(tmp1)) {
					break out;
				}
				
				for (String p2 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
					String tmp2 = tmp1;
					if (tmp2.startsWith(p2)) {
						tmp2 = tmp2.substring(p2.length(), tmp2.length());
						if (StringUtils.isEmpty(tmp2)) {
							type += 10;
							break out;
						}
					}
				}
			}
		}
		
		out: for (String p1 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
			String tmp1 = domainBody;
			if (tmp1.startsWith(p1)) {
				tmp1 = tmp1.substring(p1.length(), tmp1.length());
				if (StringUtils.isEmpty(tmp1)) {
					break out;
				}
				
				for (String p2 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
					String tmp2 = tmp1;
					if (tmp2.startsWith(p2)) {
						tmp2 = tmp2.substring(p2.length(), tmp2.length());
						if (StringUtils.isEmpty(tmp2)) {
							break out;
						}
						
						for (String p3 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
							String tmp3 = tmp2;
							if (tmp3.startsWith(p3)) {
								tmp3 = tmp3.substring(p3.length(), tmp3.length());
								if (StringUtils.isEmpty(tmp3)) {
									type += 100;
									break out;
								}
							}
						}
					}
				}
			}
		}
		
		out: for (String p1 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
			String tmp1 = domainBody;
			if (tmp1.startsWith(p1)) {
				tmp1 = tmp1.substring(p1.length(), tmp1.length());
				if (StringUtils.isEmpty(tmp1)) {
					break out;
				}
				
				for (String p2 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
					String tmp2 = tmp1;
					if (tmp2.startsWith(p2)) {
						tmp2 = tmp2.substring(p2.length(), tmp2.length());
						if (StringUtils.isEmpty(tmp2)) {
							break out;
						}
						
						for (String p3 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
							String tmp3 = tmp2;
							if (tmp3.startsWith(p3)) {
								tmp3 = tmp3.substring(p3.length(), tmp3.length());
								if (StringUtils.isEmpty(tmp3)) {
									break out;
								}
							}
							
							for (String p4 : GlobalConstants.DOMAIN_ALL_PINYIN_ARRAY) {
								String tmp4 = tmp3;
								if (tmp4.startsWith(p4)) {
									tmp4 = tmp4.substring(p4.length(), tmp4.length());
									if (StringUtils.isEmpty(tmp4)) {
										type += 1000;
										break out;
									}
								}
							}
						}
					}
				}
			}
		}
		return type;
	}
	
	public static void main(String[] args) {
		new ExpiredDomainUpdater().workPinyinType("seawu");
	}
}
