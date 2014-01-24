/**
 * @Project: biz-trading
 * @File: SpecificDaoImpl.java
 * @package onekr.biz.trading.site.dao.impl
 * @Description:
 * @author micwing
 * @date 2013-7-25 下午5:20:37
 * @version V1.0
 *
 * Copyright (c) 2013 BGoal Soft Studio. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package onekr.biz.common.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import onekr.biz.common.dao.CommonSpecificDao;

import org.springframework.stereotype.Repository;

/** 
 * @ClassName: SpecificDaoImpl 
 * @Description: 
 * @author micwing
 * @date 2013-7-25 下午5:20:37 
 */
@Repository
public class CommonSpecificDaoImpl implements CommonSpecificDao {
	@PersistenceContext
	private EntityManager em;
	
	private static final String COUNTOWNERCOMMENTS_SQL = 
			"select owner,count(owner) from Comment where biz = :biz and owner in (:owners) group by owner";
	@Override
	public Map<String, Long> countOwnerComments(String biz,
			Collection<String> owners) {
		Query query = em.createQuery(COUNTOWNERCOMMENTS_SQL);
		query.setParameter("biz", biz);
		query.setParameter("owners", owners);
		List<Object[]> list = query.getResultList();
		Map<String, Long> map = new HashMap<String, Long>();
		for (String owner : owners) {
			map.put(owner, 0L);
		}
		for (Object[] arr : list) {
			map.put(String.valueOf(arr[0]), Long.parseLong(String.valueOf(arr[1])));
		}
		return map;
	}
}
