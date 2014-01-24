/**
 * @Project: main-framework
 * @File: MoneyUtil.java
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
 * @ClassName: MoneyUtil 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:33:37 
 */ 
public class MoneyUtil {

	/**
	 * 格式"122.22"或者"122.2"转换为"壹仟陆佰叁拾伍元贰角贰分"
	 * 
	 * @param input
	 *            输入信息
	 * @return
	 */
	public static String numToChinese(String input) {
		String s1 = "零壹贰叁肆伍陆柒捌玖";
		String s4 = "分角整元拾佰仟万拾佰仟亿拾佰仟";
		String temp = "";
		String result = "";
		if (input == null)
			return "输入字串不是数字串只能包括以下字符('0'-'9')，输入字串最大只能精确到仟亿，小数点只能两位！";
		temp = input.trim();
		float f;
		try {
			f = Float.parseFloat(temp);
		} catch (Exception e) {
			return "输入字串不是数字串只能包括以下字符('0'-'9')，输入字串最大只能精确到仟亿，小数点只能两位！";
		}
		int len = 0;
		if (temp.indexOf(".") == -1)
			len = temp.length();
		else
			len = temp.indexOf(".");
		if (len > s4.length() - 3)
			return "输入字串最大只能精确到仟亿，小数点只能两位！";
		int n1, n2 = 0;
		String num = "";
		String unit = "";
		for (int i = 0; i < temp.length(); i++) {
			if (i > len + 2) {
				break;
			}
			if (i == len) {
				continue;
			}
			n1 = Integer.parseInt(String.valueOf(temp.charAt(i)));
			num = s1.substring(n1, n1 + 1);
			n1 = len - i + 2;
			unit = s4.substring(n1, n1 + 1);
			result = result.concat(num).concat(unit);
		}
		if ((len == temp.length()) || (len == temp.length() - 1))
			result = result.concat("整");
		if (len == temp.length() - 2)
			result = result.concat("零分");
		return result;
	}

	public static void main(String[] args) {
		String s = MoneyUtil.numToChinese("1635.22");
		System.out.println(s);
	}

}
