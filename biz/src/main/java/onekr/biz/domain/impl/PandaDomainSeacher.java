package onekr.biz.domain.impl;

import onekr.biz.domain.dto.DomainDto;
import onekr.framework.utils.HtmlUtil;
import onekr.framework.utils.HttpClientUtil;

import org.springframework.stereotype.Service;

@Service("PandaDomainSeacher")
public class PandaDomainSeacher implements DomainSeach {
	public static final String address = "http://panda.www.net.cn/cgi-bin/check.cgi?area_domain=";

	@Override
	public DomainDto search(String domain) {
		String info = HttpClientUtil.doGet(address+domain,"");
		DomainDto dto = new DomainDto();
		if (info.contains("<original>210")) {
			dto.setAvailable(true);
		} else if (info.contains("<original>211")) {
			dto.setAvailable(false);
		} else {
			dto.setAvailable(null);
		}
		dto.setDomain(domain);
		info = HtmlUtil.cvtHtmlTxt(info);
		dto.setWhoisInfo(info.replace("\n", "<br/>"));
		return dto;
	}
	
}
