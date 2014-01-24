/**
 * @Project: main-framework
 * @File: HttpClientUtil.java
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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: HttpClientUtil 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:33:08 
 */ 
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	/** 
     * 执行一个HTTP GET请求，返回请求响应的HTML 
     * @author wanghaijun
     * @param url  请求的URL地址 
     * @param queryString 请求的查询参数,可以为null 
     * @return 返回请求响应的HTML 
     */ 
    public static String doGet(String url, String queryString) { 
            String response = null; 
            HttpClient client = new HttpClient(); 
            HttpMethod method = new GetMethod(url); 
            try { 
                    if (StringUtils.isNotBlank(queryString)) 
                            method.setQueryString(URIUtil.encodeQuery(queryString)); 
                    client.executeMethod(method); 
                    if (method.getStatusCode() == HttpStatus.SC_OK) { 
                            response = method.getResponseBodyAsString(); 
                    } 
            } catch (URIException e) {
            	logger.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "发生异常！");
            } catch (IOException e) {
            	logger.error("执行HTTP Get请求" + url + "时，发生异常！");
            } finally { 
                    method.releaseConnection(); 
            } 
            return response; 
    } 
	
	
	/** 
     * 执行一个HTTP POST请求，返回请求响应的HTML 
     * @author wanghaijun
     * @param url 请求的URL地址 
     * @param params 请求的参数,可以为null 
     * @return 返回请求响应的HTML 
     */ 
    public static String doPost(String url, Map<String, String> params,String charset, List<Header> requestHeader) { 
            String response = null; 
            HttpClient client = new HttpClient();
            HttpMethod method = new PostMethod(url); 
            
            HttpMethodParams p = new HttpMethodParams(); 
            p.setContentCharset(charset);
            method.setParams(p);
            
            //设置Http Post数据 
            if (params != null) {            		           	
                    NameValuePair[] pairs = new NameValuePair[params.size()];
                    int i = 0;
                    for (Map.Entry<String, String> entry : params.entrySet()) { 
                    	pairs[i] = new NameValuePair(entry.getKey(), entry.getValue());
                    	i++;
                    }                                        
                    method.setQueryString(pairs);
            } 
            
            //设置http请求头
            if(requestHeader!=null)
            {
                for(int i=0;i<requestHeader.size();i++){
                    Header h3 = (Header)requestHeader.get(i);
                     method.setRequestHeader(h3 );
                }  
            }

            
            try { 
                    client.executeMethod(method); 
                    if (method.getStatusCode() == HttpStatus.SC_OK) { 
                            response = method.getResponseBodyAsString(); 
                    } 
            } catch (IOException e) { 
                   logger.error("执行HTTP Post请求" + url + "时，发生异常！", e); 
            } finally { 
                    method.releaseConnection(); 
            } 
            
            return response; 
    } 
    
//    /**
//     * 执行加密的http post
//     * @param url
//     * @param params
//     * @param charset
//     * @param requestHeader
//     * @return
//     */
//    @SuppressWarnings("restriction")
//	public static String doEncodePost(String url, Map<String, String> params,String charset, List<Header> requestHeader) { 
//    	String response = null; 
//    	HttpClient client = new HttpClient();
//    	HttpMethod method = new PostMethod(url); 
//    	
//    	HttpMethodParams p = new HttpMethodParams(); 
//    	p.setContentCharset(charset);
//    	method.setParams(p);
//    	
//    	String sendStr = JSONObject.toString(params);
//    	BASE64Encoder base64Encoder = new BASE64Encoder();
//    	String pars = base64Encoder.encode(sendStr.getBytes());
//    	
//    	//设置Http Post数据 
//    	if (params != null) {            		           	
//    		NameValuePair[] pairs = new NameValuePair[1];
//    		pairs[0] =  new NameValuePair("pars", pars);                                      
//    		method.setQueryString(pairs);
//    	} 
//    	
//    	//设置http请求头
//    	if(requestHeader!=null)
//    	{
//    		for(int i=0;i<requestHeader.size();i++){
//    			Header h3 = (Header)requestHeader.get(i);
//    			method.setRequestHeader(h3);
//    		}  
//    	}
//    	
//    	
//    	try { 
//    		client.executeMethod(method); 
//    		if (method.getStatusCode() == HttpStatus.SC_OK) { 
//    			response = method.getResponseBodyAsString(); 
//    		} 
//    	} catch (IOException e) { 
//    		logger.error("执行HTTP Post请求" + url + "时，发生异常！", e); 
//    	} finally { 
//    		method.releaseConnection(); 
//    	} 
//    	
//    	return response; 
//    } 
	
}
