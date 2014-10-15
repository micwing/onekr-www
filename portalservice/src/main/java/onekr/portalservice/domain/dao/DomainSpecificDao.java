package onekr.portalservice.domain.dao;

import java.util.List;

import onekr.portalservice.domain.dto.DomainDto;

public interface DomainSpecificDao {

	List<DomainDto> listExpiredDomain(String date, String key,
			Integer keyPos, List<String> suffix, Integer minlength,
			Integer maxlength, Integer pinyinType, List<String> textType);
}
