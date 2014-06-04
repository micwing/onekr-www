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
package onekr.biz.domain.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import onekr.biz.domain.dao.DomainSpecificDao;
import onekr.biz.domain.dto.DomainDto;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/** 
 * @ClassName: SpecificDaoImpl 
 * @Description: 
 * @author micwing
 * @date 2013-7-25 下午5:20:37 
 */
@Repository
public class DomainSpecificDaoImpl implements DomainSpecificDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DomainDto> listExpiredDomain(String date, String key,
			Integer keyPos, List<String> suffix, Integer minlength,
			Integer maxlength, Integer pinyinType, List<String> textType) {
		
		List<DomainDto> dtoList = new ArrayList<DomainDto>();
		StringBuffer sql = new StringBuffer("select " +
				" t.id as id,t.create_at as create_at,t.delete_type as delete_type,t.expired_date as expired_date,t.name as name,t.pinyin_type as pinyin_type,t.suffix as suffix,t.text_type as text_type " +
				" from t_expired_domain t " +
				" where 1=1 ");
		
		if (StringUtils.isEmpty(date)) {
			return dtoList;
		}
		date = date.replace("'", "");
		sql.append(" and expired_date = '"+date+"'");
		
		if (!StringUtils.isEmpty(key)) {
			key = key.replace("'", "");
			if (keyPos == 0) {
				sql.append(" and name like '%"+key+"%'");
			} else if (keyPos == 1) {
				sql.append(" and name like '"+key+"%'");
			} else if (keyPos == 2) {
				sql.append(" and name like '%"+key+"'");
			} else if (keyPos == 3) {
				sql.append(" and ( name like '"+key+"%' or name like '%"+key+"' )");
			}
		}
		
		if (CollectionUtils.isEmpty(suffix)) {
			return dtoList;
		}
		List<String> tmp = new ArrayList<String>();
		for (String s : suffix) {
			tmp.add(s.replace("'", ""));
		}
		sql.append(" and suffix in ('"+StringUtils.join(tmp, "','")+"')");
		
		if (minlength != null && minlength > 0) {
			sql.append(" and LENGTH(name) >= "+minlength);
		}
		
		if (maxlength != null && maxlength > 0) {
			sql.append(" and LENGTH(name) <= "+maxlength);
		}
		
		if (pinyinType != null) {
			if (pinyinType == 9) {
				sql.append(" and pinyin_type > 0 ");
			} else if (pinyinType == 1) {
				sql.append(" and pinyin_type%10 = 1 ");
			} else if (pinyinType == 2) {
				sql.append(" and pinyin_type%100 = 10 ");
			} else if (pinyinType == 3) {
				sql.append(" and pinyin_type%1000 = 100 ");
			} else if (pinyinType == 4) {
				sql.append(" and pinyin_type%10000 = 1000 ");
			} 
		}
		
		
		if (!CollectionUtils.isEmpty(textType)) {
			sql.append(" and ( 1=1 ");
			if (!textType.contains("1")) {
				sql.append(" and text_type%10 < 1");
			}
			if (!textType.contains("2")) {
				sql.append(" and text_type%100 < 10");
			}
			if (!textType.contains("3")) {
				sql.append(" and text_type%1000 < 100");
			}
			sql.append(" ) ");
		}
		
		sql.append(" order by suffix asc, LENGTH(name) asc, name desc   ");
		
		Query query = em.createNativeQuery(sql.toString());
		List list = query.getResultList();
		for (Object obje : list) {
			Object[] obj = (Object[]) obje;
			String domainBody = String.valueOf(obj[4]);
			String suf = String.valueOf(obj[6]);
			DomainDto dto = new DomainDto();
			dto.setDomain(domainBody+"."+suf);
			dto.setName(domainBody);
			dto.setSuffix(suf);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
}
