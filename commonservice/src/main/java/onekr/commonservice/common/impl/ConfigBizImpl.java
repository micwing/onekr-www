package onekr.commonservice.common.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.common.dao.ConfigDao;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.commonservice.model.Config;
import onekr.commonservice.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ConfigBizImpl implements ConfigBiz {
	
	@Autowired
	private ConfigDao configDao;

	@Override
	public Map<String, Config> findConfigs(Biz biz, Collection<String> owners) {
		List<Config> configs = configDao.findByBizAndOwnerIn(biz.name(), owners);
		Map<String, Config> map = new HashMap<String, Config>();
		if (!CollectionUtils.isEmpty(configs)) {
			for (Config c : configs) {
				map.put(c.getOwner(), c);
			}
		}
		return map;
	}
	
	@Override
	public Config findConfig(Biz biz, String owner) {
		return configDao.findByBizAndOwner(biz.name(), owner);
	}
	
	@Override
	public void updateConfigs(List<Config> configs) {
		for (Config param : configs) {
			Config entry = configDao.findByBizAndOwner(param.getBiz(), param.getOwner());
			if (entry == null) {
				entry = new Config();
				entry.setBiz(param.getBiz());
				entry.setOwner(param.getOwner());
				entry.setStatus(Status.NORMAL);
			}
			if (param.getStatus() != null) {				
				entry.setStatus(param.getStatus());
			}
			entry.setUpdateAt(new Date());
			entry.setValue(param.getValue());
			configDao.save(entry);
		}
	}
}
