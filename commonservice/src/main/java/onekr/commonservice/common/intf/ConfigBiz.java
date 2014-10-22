package onekr.commonservice.common.intf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.Config;

public interface ConfigBiz {

	Map<String, Config> findConfigs(Biz biz, Collection<String> owners);
	
	Config findConfig(Biz biz, String owner);
	
	void updateConfigs(List<Config> configs);
}
