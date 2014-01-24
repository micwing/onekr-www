/**
 * @Project: main-framework
 * @File: Md5Coder.java
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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
 * @ClassName: Md5Coder 
 * @Description: 用于对字符串进行md5编码码的工具类
 * @author micwing
 * @date 2013-3-26 下午5:33:30 
 */ 
public class Md5Coder {

	private Md5Coder() {}

	/**利用MD5进行加密
     * @param str  待加密的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
	 * @throws UnsupportedEncodingException 
     */
	public static String EncoderByMd5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	MessageDigest md = MessageDigest.getInstance("MD5");  
        md.update(text.getBytes("utf-8"));
        byte[] digest = md.digest(); 
        StringBuffer md5 = new StringBuffer();  
        for (int i = 0; i < digest.length; i++) {  
            md5.append(Character.forDigit((digest[i] & 0xF0) >> 4, 16));  
            md5.append(Character.forDigit((digest[i] & 0xF), 16));  
        }  
        return md5.toString(); 
    }
	public static void main(String[] args) {
		try {
			System.out.println(EncoderByMd5("XXXXXXXXXx"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
