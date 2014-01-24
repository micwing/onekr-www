package onekr.biz.domain.intf;

import java.util.List;

import onekr.biz.domain.dto.DomainDto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface DomainBiz {
	DomainDto queryJudgeDomain(@NotBlank String domain);
	
	DomainDto queryDomainWhois(@NotBlank String domain); 
	
	List<DomainDto> listDomains4Group(String first, String second, String atype, String suffix);
}