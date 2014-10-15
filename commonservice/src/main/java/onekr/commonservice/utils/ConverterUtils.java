package onekr.commonservice.utils;

import org.apache.shiro.crypto.hash.Md5Hash;



public class ConverterUtils {
	
	public static String cvtPasswordStr(String str) {
		return new Md5Hash(str).toHex();
	}

}
