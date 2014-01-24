/**
 * @Project: main-framework
 * @File: SpringContextUtil.java
 * @package onekr.framework.utils
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
package onekr.framework.springframework.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: SpringContextUtil 
 * @Description: 访问Spring上下文的工具类
 * @author micwing
 * @date 2013-3-26 下午5:34:24 
 */ 
@Component
public class SpringContextUtil implements ApplicationContextAware{

	private static ApplicationContext context;
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		context = ctx;
		
	}
	
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	
	/**
	 * 根据Bean名称返回String类型的BEAN
	 * @param beanName
	 * @return
	 */
	public static String getStringBean(String beanName){
		return context.getBean(beanName, String.class);
	}
	
}
