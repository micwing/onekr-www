package onekr.biz.domain.dao;

import java.util.List;

import onekr.biz.domain.dto.DomainDto;

public interface DomainSpecificDao {

	List<DomainDto> listExpiredDomain(String date, String key,
			Integer keyPos, List<String> suffix, Integer minlength,
			Integer maxlength, Integer pinyinType, List<String> textType);
}
