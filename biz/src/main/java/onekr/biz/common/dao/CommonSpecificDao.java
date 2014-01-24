/**
 * @Project: biz-trading
 * @File: SpecificDao.java
 * @package onekr.biz.trading.site.dao
 * @Description:
 * @author micwing
 * @date 2013-7-25 下午5:19:52
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
package onekr.biz.common.dao;

import java.util.Collection;
import java.util.Map;



/** 
 * @ClassName: SpecificDao 
 * @Description: 
 * @author micwing
 * @date 2013-7-25 下午5:19:52 
 */
public interface CommonSpecificDao {

	Map<String, Long> countOwnerComments(String biz, Collection<String> owners);
}
