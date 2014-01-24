/**
 * @Project: main-framework
 * @File: StringUtil.java
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

import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/** 
 * @ClassName: StringUtil 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:34:41 
 */ 
public class StringUtil {

    // 默认的分隔符
    public final static String DEFAULT_SPLIT = "&";

    /**
     * url查询参数a~z 排序
     * @param parameter
     * @return
     */
    public static String sortParameter(String parameter, String split) {
        String[] parameters = parameter.split(split);
        Arrays.sort(parameters);
        return StringUtils.join(parameters, split);
    }

    /**
     * url的queryString 自然排序
     * @param queryString
     * @return
     */
    public static String sortQueryString(String queryString) {
        return sortParameter(queryString, DEFAULT_SPLIT);
    }

    /**
     * md5 签名
     * @param queryString a=123&b=456
     * @param md5Key
     * @return
     */
    public static String md5Sign(String queryString, String md5Key) {
        StringBuilder sb = new StringBuilder();
        sb.append(sortQueryString(queryString));
        sb.append(md5Key);
        return EncriptUtil.encript(sb.toString());
    }

    /**
     * md5签名验证工具类(只支持开放平台md5签名验证)
     * @param queryString 待签名数据
     * @param md5Key 密钥
     * @param sign 签名
     * @return
     */
    public static boolean validateMD5Sign(String queryString, String md5Key, String sign) {
        return md5Sign(queryString, md5Key).equals(sign);
    }

    
    /**
     * 截取字符串
     * @param message 信息
     * @param start 开始 如<NewDataSet>
     * @param end 结束 </NewDataSet>
     * @return
     */
    public static String splitString(String message,String start,String end){
        Assert.notNull(message);
        int startPostion = message.indexOf(start) ;
        int endPostion = message.indexOf(end);
        Assert.isTrue(startPostion >=0);
        Assert.isTrue(endPostion >=0);
        return message.substring(startPostion,endPostion+end.length());
    }
    
    /**
     * 如果字符的首个字母大写则改成小写
     * @param string    Apple--->apple
     * @return
     */
    public static String lowerCapitalCharacter(String string){
        if(null == string){
            return null;
        }
        
        if(string.length() == 0){
            return string;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(string.substring(0,1).toLowerCase()).append(string.substring(1,string.length()));
        return sb.toString();
    }

    /**
     * 256位流水号
     * @return
     */
    public static String generateUUID() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            sb.append(UUID.randomUUID().toString().split("-")[0]);
            sb.append(UUID.randomUUID().toString().split("-")[1]);
            sb.append(UUID.randomUUID().toString().split("-")[2]);
            sb.append(UUID.randomUUID().toString().split("-")[3]);
            sb.append(UUID.randomUUID().toString().split("-")[4]);
        }
        return sb.toString();
    }

    
    public static void main(String [] args){
        System.out.println(lowerCapitalCharacter(""));
        System.out.println(lowerCapitalCharacter(" "));
        System.out.println(lowerCapitalCharacter(" Abbccc"));
        System.out.println(lowerCapitalCharacter("A"));
        System.out.println(lowerCapitalCharacter("ABV"));
    }
}
