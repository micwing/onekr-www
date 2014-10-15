package onekr.commonservice.common.intf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import onekr.commonservice.model.Count;

public interface CountBiz {

	Map<String, Count> findCounts(String biz, Collection<String> owners);
	
	Count findCount(String biz, String owner);
	
	Count addCount(String biz, String owner, Long step);
	
	void updateCounts(List<Count> counts);
}
