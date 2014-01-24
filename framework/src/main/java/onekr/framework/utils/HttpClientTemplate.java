/**
 * @Project: main-framework
 * @File: HttpClientTemplate.java
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.VersionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/** 
 * @ClassName: HttpClientTemplate 
 * @Description: HttpClient模板，基于Apache HttpClient4.01，负责设置默认超时参数，执行Http请求，断开连接
 * @author micwing
 * @date 2013-3-26 下午5:32:53 
 */ 
public class HttpClientTemplate implements InitializingBean {
    /** logger */
    private static final Logger logger                   = LoggerFactory
                                                                 .getLogger(HttpClientTemplate.class);

    private HttpClient          httpClient;

    private int                 maxConnPerRoute          = 32;

    private int                 maxTotalConn             = 128;

    /** 默认等待连接建立超时，单位:毫秒 */
    private int                 connectionTimeout        = 1000;

    /** 默认等待数据返回超时，单位:毫秒 */
    private int                 soTimeout                = 5000;

    /** 默认请求连接池连接超时,单位:毫秒 */
    private int                 connectionManagerTimeout = 1000;

    private static final String PARAMETER_SEPARATOR      = "&";

    private static final String NAME_VALUE_SEPARATOR     = "=";

    private static final String DEFAULT_CONTENT_ENCODING = "UTF-8";

    /**
     * 以Get方式执行http请求
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String executeGet(final String url) throws ClientProtocolException, IOException {
        return execute(new HttpGet(url));
    }

    /**
     * 以Post方式执行http请求
     * @param url
     * @param params
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String executePost(final String url, final List<? extends NameValuePair> params,
            final String encoding, final boolean isUrlEncode) throws ClientProtocolException,
            IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity reqEntity;
        if (isUrlEncode) {
            reqEntity = new UrlEncodedFormEntity(params, encoding);
        } else {
            reqEntity = createRequestStringEntity(params, encoding);
        }
        httpPost.setEntity(reqEntity);
        return execute(httpPost);
    }

    /**
     * 以Post方式执行http请求
     * @param url
     * @param params 示例:name1=value1&name2=value2&name3=value3
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String executePost(final String url, final String params, final String encoding)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity reqEntity = new StringEntity(params, encoding);
        httpPost.setEntity(reqEntity);
        return execute(httpPost);
    }

    /**
     * 创建不进行url encode的请求具体内容
     * @param params
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     */
    private StringEntity createRequestStringEntity(final List<? extends NameValuePair> params,
            final String encoding) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for (final NameValuePair parameter : params) {
            final String encodedName = parameter.getName();
            final String value = parameter.getValue();
            final String encodedValue = value != null ? value : "";
            if (result.length() > 0)
                result.append(PARAMETER_SEPARATOR);
            result.append(encodedName);
            result.append(NAME_VALUE_SEPARATOR);
            result.append(encodedValue);
        }
        StringEntity reqEntity = new StringEntity(result.toString(), encoding);
        return reqEntity;
    }

    /**
     * 传入HttpRequest以执行http请求
     * @param httpReq
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String execute(HttpRequestBase httpReq) throws ClientProtocolException, IOException {
        HttpResponse response = httpClient.execute(httpReq);
        HttpEntity entity = response.getEntity();

        logger.debug("----------------------------------------");
        logger.debug(response.getStatusLine().toString());
        if (entity != null) {
            logger.debug("Response content length: " + entity.getContentLength());
        }
        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),
                DEFAULT_CONTENT_ENCODING));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        if (entity != null) {
            entity.consumeContent();
        }
        // 释放HttpClient连接
        if (httpReq != null) {
            httpReq.abort();
        }
        logger.debug(sb.toString());
        return sb.toString();

    }

    protected HttpClient createHttpClient() {
        HttpParams params = createHttpParams();
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
        this.httpClient = new DefaultHttpClient(cm, params);

        // HTTP 协议的版本,1.1/1.0/0.9
        // HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        // 字符集
        // HttpProtocolParams.setContentCharset(params, "UTF-8");

        HttpConnectionParams.setSoTimeout(params, soTimeout);
        HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);

        ConnManagerParams.setTimeout(params, connectionManagerTimeout);
        ConnManagerParams.setMaxTotalConnections(params, maxTotalConn);
        ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRouteBean(maxConnPerRoute));

        // 设置http代理服务器
        // HttpHost proxy = new HttpHost("192.168.121.32", 80);
        // ConnRouteParams.setDefaultProxy(params, proxy);

        return httpClient;
    }

    protected HttpParams createHttpParams() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, DEFAULT_CONTENT_ENCODING);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpConnectionParams.setTcpNoDelay(params, true);
        HttpConnectionParams.setSocketBufferSize(params, 8192);

        // determine the release version from packaged version info
        final VersionInfo vi = VersionInfo.loadVersionInfo("org.apache.http.client", getClass()
                .getClassLoader());
        final String release = (vi != null) ? vi.getRelease() : VersionInfo.UNAVAILABLE;
        HttpProtocolParams.setUserAgent(params, "Apache-HttpClient/" + release + " (java 1.5)");

        return params;
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        this.httpClient = createHttpClient();
    }

    /**
     * @return the maxConnPerRoute
     */
    public int getMaxConnPerRoute() {
        return maxConnPerRoute;
    }

    /**
     * @param maxConnPerRoute the maxConnPerRoute to set
     */
    public void setMaxConnPerRoute(int maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
    }

    /**
     * @return the maxTotalConn
     */
    public int getMaxTotalConn() {
        return maxTotalConn;
    }

    /**
     * @param maxTotalConn the maxTotalConn to set
     */
    public void setMaxTotalConn(int maxTotalConn) {
        this.maxTotalConn = maxTotalConn;
    }

    /**
     * @return the connectionTimeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * @param connectionTimeout the connectionTimeout to set
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * @return the soTimeout
     */
    public int getSoTimeout() {
        return soTimeout;
    }

    /**
     * @param soTimeout the soTimeout to set
     */
    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    /**
     * @return the connectionManagerTimeout
     */
    public int getConnectionManagerTimeout() {
        return connectionManagerTimeout;
    }

    /**
     * @param connectionManagerTimeout the connectionManagerTimeout to set
     */
    public void setConnectionManagerTimeout(int connectionManagerTimeout) {
        this.connectionManagerTimeout = connectionManagerTimeout;
    }

    public static void main(String[] args) throws Exception {
        HttpClientTemplate httpclient = new HttpClientTemplate();
        httpclient.createHttpClient();
//        List<BasicNameValuePair> nameValues = new ArrayList<BasicNameValuePair>();
//        BasicNameValuePair nameValue1 = new BasicNameValuePair("key", "650");
//        BasicNameValuePair nameValue2 = new BasicNameValuePair("bank", "4109");
//        nameValues.add(nameValue1);
//        nameValues.add(nameValue2);
        
        String url = "http://hotelservice.17u.cn/hotel_api/HotelUnionAPI.svc/HotelUnion";
        String postContent = "<RequestXML><Type value='HotelList'><CityId>53</CityId><CurrentPage>1</CurrentPage><PerCount>10</PerCount><Refid>3825977</Refid><AcsKey>744E3D9DA052AC2FB7BE</AcsKey></Type></RequestXML>";
        System.out.println(httpclient.executePost(url, postContent, "utf-8"));
        
        
        // httpclient.executePost("http://localhost:9000/epp-portal/test.jsp",
        // nameValues, "UTF-8");
        // httpclient
//        URI uri = URI
//                .create("http://192.168.116.62:9003/epp-open/openService/gate-way.action?_input_charset=UTF-8&body=买手机&buyer_id=45000000&it_b_pay=1000&notify_url=http://192.168.116.62:9005/epp-service-test/config/notice-info!notifyUrl.action&out_order_no=1000006&partner=SNDQ&pay_amount=10000&pay_channel=10&pay_channel_amount=10000&payment_type=0001&return_url=http://192.168.116.62:9005/epp-service-test/config/notice-info!returnUrl.action&service=test_page_service&subject=手机&total_fee=20000&sign=7b6b4467e3eabc2b07e214b66011a7e3&sign_type=md5");
//        if (uri != null) {
//            // System.out.println(uri.toASCIIString());
//            System.out.println(URLEncoder.encode(uri.toString(), "utf-8"));
//            ;
//        }
    }
}
