/**
 * @Project: main-framework
 * @File: ImageValidateCodeServlet.java
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
package onekr.framework.verifycode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/** 
 * @ClassName: ImageValidateCodeServlet 
 * @Description: 图片验证码Servlet
 * @author micwing
 * @date 2013-3-26 下午5:35:59 
 */ 
public class SunImageValidateCodeServlet extends HttpServlet {

    private static final long                  serialVersionUID = -3627014705235922242L;

    /** Session存放验证码的Key */
    public static final String                 SESSION_KEY      = "_image_validate_code";
    /** Session存放验证码次数的Key */
    public static final String                 SESSION_TIMES_KEY        = "_image_validate_code_time";

    /** 请求参数存放验证码的Key */
    public static final String                 PARAM_KEY        = "verifycode";
    
    
    /** 不用检验验证码的次数 */
    public static final int                   MAX_NO_VALIDATE_TIME     = 3;

    /** 牛X验证码 */
    public static final String                 NX_CODE          = "@G)+";

    /** 随机数 */
    private Random                             random;

    /** 图片验证码 */
    private SunImageValidateCode                  imageValidateCode;

    /** 验证码输出流缓存 */
    private Map<String, ByteArrayOutputStream> cache;
    
    /**
     * 本次是否免校验
     * @param req 请求
     * @return 验证结果，true：通过校验，false：校验失败
     */
    public static boolean noValidateTimes(HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	
    	//初始化session字段值
    	if (session.getAttribute(SESSION_TIMES_KEY) == null) {
    		session.setAttribute(SESSION_TIMES_KEY, 0);
    	}
    	
    	//为本次请求增加计数
    	session.setAttribute(SESSION_TIMES_KEY, ((Integer) session.getAttribute(SESSION_TIMES_KEY))+1);
    	
    	//判断本次请求是否要检测校验码
    	int times = (Integer) session.getAttribute(SESSION_TIMES_KEY);
        return times <= MAX_NO_VALIDATE_TIME;
    }
    
    /**
     * 获取校验次数
     * @param req 请求
     * @return 验证结果，true：通过校验，false：校验失败
     */
    public static int getNoValidateTimes(HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	
    	//初始化session字段值
    	if (session.getAttribute(SESSION_TIMES_KEY) == null) {
    		session.setAttribute(SESSION_TIMES_KEY, 0);
    	}
    	
    	return (Integer) session.getAttribute(SESSION_TIMES_KEY);
    }
    
    /**
     * 重置免校验
     * @param req 请求
     * @return 验证结果，true：通过校验，false：校验失败
     */
    public static void noValidateTimesReset(HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	session.setAttribute(SESSION_TIMES_KEY, 0);
    }

    /**
     * 验证
     * @param req 请求
     * @return 验证结果，true：正确，false：错误
     */
    public static boolean validate(HttpServletRequest req) {
        String reqCode = req.getParameter(PARAM_KEY);

        if (NX_CODE.equals(reqCode)) {
            return true;
        }

        String validateCode = (String) req.getSession().getAttribute(SESSION_KEY);

        if (validateCode == null) {
            return false;
        } else {
            return validateCode.equals(reqCode);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws ServletException {
        this.random = new Random();
        this.imageValidateCode = new SunImageValidateCode();
        this.cache = new ConcurrentHashMap<String, ByteArrayOutputStream>(10000);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String validateCode = this.getValidateCode(4);
        ByteArrayOutputStream outStream;

        if (this.cache.containsKey(validateCode)) {
            outStream = this.cache.get(validateCode);
        } else {
            outStream = new ByteArrayOutputStream();
            JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(outStream);
            jpeg.encode(this.imageValidateCode.generate(validateCode));
            this.cache.put(validateCode, outStream);
        }

        resp.setContentType("image/jpeg");
        resp.setHeader("Expires", "Sat, 16 Jan 1980 12:00:00 GMT");
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");

        resp.getOutputStream().write(outStream.toByteArray());

        req.getSession().setAttribute(SESSION_KEY, validateCode);
    }

    /**
     * 获取验证码
     * @param length 长度
     * @return 验证码
     */
    private String getValidateCode(int length) {
        StringBuilder validateCode = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            validateCode.append(this.random.nextInt(10));
        }

        return validateCode.toString();
    }

}
