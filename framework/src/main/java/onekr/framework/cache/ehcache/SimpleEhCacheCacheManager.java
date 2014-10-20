/**
 * @Project: main-framework
 * @File: SimpleEhCacheCacheManager.java
 * @package onekr.framework.cache
 * @Description:
 * @author micwing
 * @date 2013-3-26 下午4:52:45
 * @version V1.0
 *
 * Copyright (c) 2013 OneKr Soft Studio. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package onekr.framework.cache.ehcache;

import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/** 
 * @ClassName: SimpleEhCacheCacheManager 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:28:21 
 */ 
public class SimpleEhCacheCacheManager extends EhCacheCacheManager{

	@Override
	public Cache getCache(String name) {
		getCacheManager().addCacheIfAbsent(name);		
		return super.getCache(name);
	}
}
