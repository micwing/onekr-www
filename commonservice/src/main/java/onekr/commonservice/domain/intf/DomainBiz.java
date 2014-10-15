package onekr.commonservice.domain.intf;

import java.util.List;

import onekr.commonservice.domain.dto.DomainDto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface DomainBiz {
	DomainDto queryJudgeDomain(@NotBlank String domain);
	
	DomainDto queryDomainWhois(@NotBlank String domain); 
	
	List<DomainDto> listDomains4Group(String first, String second, String atype, String suffix);
	
	List<DomainDto> listDomains4Expired(
			String date, String key, Integer keyPos, List<String> suffix,
			Integer minlength, Integer maxlength, Integer pinyinType, List<String> textType);
}