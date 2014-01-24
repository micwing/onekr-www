/**
 * @Project: main-framework
 * @File: ResultType.java
 * @package onekr.framework.result
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
package onekr.framework.result;

import onekr.framework.type.LabeledEnum;

/** 
 * @ClassName: ResultType 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午4:30:44 
 */ 
public enum ResultType implements LabeledEnum{
	/**
	 * 成功
	 */
	success("成功"),
	
	/**
	 * 信息
	 */
	info("信息"),
	
	/**
	 * 警告
	 */
	warning("警告"),
	
	/**
	 * 错误
	 */
	error("错误");
	
	String label;
	
	ResultType(String label){
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
