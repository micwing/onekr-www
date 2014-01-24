package onekr.biz.common.intf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import onekr.biz.model.Config;

public interface ConfigBiz {

	Map<String, Config> findConfigs(String biz, Collection<String> owners);
	
	Config findConfig(String biz, String owner);
	
	void updateConfigs(List<Config> configs);
}
