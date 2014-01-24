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
package onekr.biz.sys.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import onekr.biz.sys.dao.SysSpecificDao;

import org.springframework.stereotype.Repository;

/** 
 * @ClassName: SpecificDaoImpl 
 * @Description: 
 * @author micwing
 * @date 2013-7-25 下午5:20:37 
 */
@Repository
public class SysSpecificDaoImpl implements SysSpecificDao {
	@PersistenceContext
	private EntityManager em;

}
