package onekr.commonservice.common.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import onekr.commonservice.common.dao.CountDao;
import onekr.commonservice.common.intf.CountBiz;
import onekr.commonservice.model.Count;
import onekr.commonservice.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CountBizImpl implements CountBiz {
	
	@Autowired
	private CountDao countDao;

	@Override
	public Map<String, Count> findCounts(String biz, Collection<String> owners) {
		Map<String, Count> map = new HashMap<String, Count>();
		if (CollectionUtils.isEmpty(owners))
			return map;
		List<Count> counts = countDao.findByBizAndOwnerIn(biz, owners);
		if (!CollectionUtils.isEmpty(counts)) {
			for (Count c : counts) {
				map.put(c.getOwner(), c);
			}
		}
		return map;
	}
	
	@Override
	public Count findCount(String biz, String owner) {
		return countDao.findByBizAndOwner(biz, owner);
	}
	
	@Override
	public Count addCount(String biz, String owner, Long step) {
		step = step == null ? 1L : step;
		Count count = findCount(biz, owner);
		if (count == null) {
			count = new Count();
			count.setBiz(biz);
			count.setOwner(owner);
			count.setNum(0L);
			count.setStatus(Status.NORMAL);
		}
		count.setNum(count.getNum()+step);
		count.setUpdateAt(new Date());
		countDao.save(count);
		return count;
	}
	
	@Override
	public void updateCounts(List<Count> counts) {
		for (Count param : counts) {
			Count entry = countDao.findByBizAndOwner(param.getBiz(), param.getOwner());
			if (entry == null) {
				entry = new Count();
				entry.setBiz(param.getBiz());
				entry.setOwner(param.getOwner());
				entry.setStatus(Status.NORMAL);
			}
			if (param.getStatus() != null) {				
				entry.setStatus(param.getStatus());
			}
			entry.setUpdateAt(new Date());
			entry.setNum(param.getNum());
			countDao.save(entry);
		}
	}
}
