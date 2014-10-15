package onekr.portalservice.domain.impl;

import java.io.IOException;

import onekr.portalservice.domain.dto.DomainDto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service("WwwWhoisDomainSeacher")
public class WwwWhoisDomainSeacher implements DomainSeach {
	public static final String address = "http://www.whois.com/whois/";

	@Override
	public DomainDto search(String domain) {
		Document doc = null;
		try {
			doc = Jsoup.connect(address+domain)
					.userAgent("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.timeout(5000).get();
		} catch (IOException e) {
			return null;
		}  
		
		DomainDto dto = new DomainDto();
		dto.setDomain(domain);	  
		
		
		Elements registryBlkElements = doc.select("#registryBlk");
		Elements errorboxElements = doc.select(".whois_errorbox");
		Elements registryDataElements = doc.select("#registryData");
    	Elements registrarDataElements = doc.select("#registrarData");
    	
	    if (registryBlkElements.size() > 0) {
	    	dto.setAvailable(false);
	    	
	    	registrarDataElements.get(0).select("img").remove();
	    	dto.setWhoisInfo(
	    			"<h3>"+domain+" registry whois</h3>"+registryDataElements.get(0).html()+
	    			"<h3>"+domain+" registrar whois</h3>"+registrarDataElements.get(0).html());
	    } else if (errorboxElements.size() > 0) {
	    	dto.setAvailable(null);
	    	dto.setWhoisInfo("");
	    } else {
	    	dto.setAvailable(true);
	    	dto.setWhoisInfo(domain+" is available!");
	    }
	    return dto;
	}
	
}
