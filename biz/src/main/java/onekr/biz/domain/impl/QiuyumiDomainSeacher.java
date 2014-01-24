package onekr.biz.domain.impl;

import java.io.IOException;

import onekr.biz.domain.dto.DomainDto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service("QiuyumiDomainSeacher")
public class QiuyumiDomainSeacher implements DomainSeach {
	public static final String address = "http://www.qiuyumi.com/whois/?domain=";

	@Override
	public DomainDto search(String domain) {
		Document doc = null;
		DomainDto dto = new DomainDto();
		try {
			doc = Jsoup.connect(address+domain)
					.userAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.timeout(5000).get();
		} catch (IOException e) {
			return null;
		}  
	    
	    Elements domainavaElements = doc.select("#domainava");
	    if (domainavaElements.size() > 0) {
	    	String text = domainavaElements.get(0).text();
	    	if (text.contains("已被注册")) {
	    		dto.setAvailable(false);
	    	} else {
	    		dto.setAvailable(true);
	    	}
	    }
	    domainavaElements.remove();
	    Elements whoisinfoboxElements = doc.select("#whoisinfobox");
		if (whoisinfoboxElements.size() > 0) {
			Element element = whoisinfoboxElements.get(0);
			String html = element.html();
			dto.setWhoisInfo(html);
		}
		
		dto.setDomain(domain);
		return dto;
	}
	
}
