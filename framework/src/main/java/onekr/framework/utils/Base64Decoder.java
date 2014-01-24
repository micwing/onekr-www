/**
 * @Project: main-framework
 * @File: Base64Decoder.java
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


/** 
 * @ClassName: Base64Decoder 
 * @Description: 用于对字符串进行base64解码的工具类
 * @author micwing
 * @date 2013-3-26 下午5:31:53 
 */ 
public class Base64Decoder {

	private Base64Decoder() {}

	/**
	 * 获取解码工具类的实例
	 * 
	 * @return 解码工具类的实例
	 */
	public static Base64Decoder getInstance() {
		return new Base64Decoder();
	}

//	/**
//	 * 对给定的字符串进行Base64解密操作
//	 * 
//	 * @param str 待解密的字符串
//	 * @return 解密后的字符串
//	 * @throws IOException
//	 */
//	public static String decode(String str) throws IOException {
//		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//		return new String(decoder.decodeBuffer(str));
//	}
}
