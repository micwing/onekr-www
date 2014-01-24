/**
 * @Project: main-framework
 * @File: EncriptUtil.java
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
package onekr.framework.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/** 
 * @ClassName: EncriptUtil 
 * @Description: 加密工具
 * @author micwing
 * @date 2013-3-26 下午5:32:38 
 */ 
public abstract class EncriptUtil {
	private static PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	/**
	 * 
	 * @param painTxt
	 * @return
	 */
	public static String encript(String painTxt) {
			return passwordEncoder.encodePassword(painTxt,null);
	}

}
