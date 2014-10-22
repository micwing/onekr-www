package onekr.commonservice.common.intf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.Count;

public interface CountBiz {

	Map<String, Count> findCounts(Biz biz, Collection<String> owners);
	
	Count findCount(Biz biz, String owner);
	
	Count addCount(Biz biz, String owner, Long step);
	
	void updateCounts(List<Count> counts);
}
