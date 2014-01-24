/**
 * @Project: main-framework
 * @File: CreateURLUtil.java
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

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/** 
 * @ClassName: CreateURLUtil 
 * @Description: 发送邮件生成UrL地址，验证URL地址
 * @author micwing
 * @date 2013-3-26 下午5:32:22 
 */ 
public class CreateURLUtil {

    /**
     * 
     * 生成邮件内容地址
     * 
     * @param oldURL 需要传进来的URL地址：注意带上参数
     * @param dessecret 为这个URL设置一个密钥
     * @return
     */
    public static String createEmailMessageURL(String oldURL, String dessecret) {

        String OURL = oldURL + "&adate=" + new Date().getTime();
        // 截取到要加密的参数
        String parameter = StringUtils.substringAfter(OURL, "?");
        // 排序
        String sortParameter = StringUtil.sortParameter(parameter, "&");
        // 加密
        String md5Paramerter = EncriptUtil.encript(sortParameter + dessecret);
        String URL = StringUtils.substringBefore(OURL, "?");
        String newURL = URL + "?" + sortParameter + "&sign=" + md5Paramerter;
        return newURL;
    }

    /**
     * 验证客户点击邮件内容中的地址是否是该平台的地址
     * 
     * @param emailMessageURL email内容地址
     * @param dessecret 密钥(注意与生成URL的密钥一致)
     * 
     * @return
     */
    public static boolean validateURL(String emailMessageURL, String dessecret) {

        // 截取参数字符串
        String parameter1 = StringUtils.substringAfter(emailMessageURL, "?");
        String parameter2 = StringUtils.substringBefore(parameter1, "&sign=");

        // 验证是否是排序的
        String sortParameter = StringUtil.sortParameter(parameter2, "&");
        if (!StringUtils.equals(parameter2, sortParameter)) {
            return false;
        }
        // 获取到加密的字符串
        String signStr = StringUtils.substringAfter(parameter1, "&sign=");
        String md5Paramerter = EncriptUtil.encript(sortParameter + dessecret);
        // 验证是否是MD5加密的参数
        if (!StringUtils.equals(md5Paramerter, signStr)) {
            return false;
        }
        // 验证时间
        String dateStr1 = StringUtils.substringAfter(sortParameter, "adate=");
        String dateStr2 = StringUtils.substringBefore(dateStr1, "&");
        Date currDate = new Date();
        Date date = new Date(NumberUtils.toLong(dateStr2));
        int hour = DateUtil.diffDateToHour(currDate, date);
        // 判断用户是否超出时间返回访问平台
        if (hour > 48) {
            return false;
        }
        return true;
    }
}
