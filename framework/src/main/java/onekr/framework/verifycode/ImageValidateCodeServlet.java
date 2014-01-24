package onekr.framework.verifycode;
///**
// * @Project: main-framework
// * @File: ImageValidateCodeServlet.java
// * @package onekr.framework.utils
// * @Description:
// * @author micwing
// * @date 2013-3-26 下午4:52:45
// * @version V1.0
// *
// * Copyright (c) 2013 OneKr Soft Studio. All Rights Reserved.
// *
// * Copying of this document or code and giving it to others and the
// * use or communication of the contents thereof, are forbidden without
// * expressed authority. Offenders are liable to the payment of damages.
// * All rights reserved in the event of the grant of a invention patent or the
// * registration of a utility model, design or code.
// */
//package onekr.framework.web;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Random;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
///** 
// * @ClassName: ImageValidateCodeServlet 
// * @Description: 图片验证码Servlet
// * @author micwing
// * @date 2013-3-26 下午5:35:59 
// */ 
//public class ImageValidateCodeServlet extends HttpServlet {
//
//    private static final long                  serialVersionUID = -3627014705235922242L;
//
//    /** Session存放验证码的Key */
//    public static final String                 SESSION_KEY      = "_image_validate_code";
//
//    /** 请求参数存放验证码的Key */
//    public static final String                 PARAM_KEY        = "paptcha";
//
//    /** 牛X验证码 */
//    public static final String                 NX_CODE          = "@G)+";
//
//    /** 随机数 */
//    private Random                             random;
//
//    /** 图片验证码 */
//    private ImageValidateCode                  imageValidateCode;
//
//    /** 验证码输出流缓存 */
//    private Map<String, ByteArrayOutputStream> cache;
//
//    /**
//     * 验证
//     * @param req 请求
//     * @return 验证结果，true：正确，false：错误
//     */
//    public static boolean validate(HttpServletRequest req) {
//        String reqCode = req.getParameter(PARAM_KEY);
//
//        if (NX_CODE.equals(reqCode)) {
//            return true;
//        }
//
//        String validateCode = (String) req.getSession().getAttribute(SESSION_KEY);
//
//        if (validateCode == null) {
//            return false;
//        } else {
//            return validateCode.equals(reqCode);
//        }
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void init() throws ServletException {
//        this.random = new Random();
//        this.imageValidateCode = new ImageValidateCode();
//        this.cache = new ConcurrentHashMap<String, ByteArrayOutputStream>(10000);
//
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
//            IOException {
//        String validateCode = this.getValidateCode(4);
//        ByteArrayOutputStream outStream;
//
//        if (this.cache.containsKey(validateCode)) {
//            outStream = this.cache.get(validateCode);
//        } else {
//            outStream = new ByteArrayOutputStream();
//            JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(outStream);
//            jpeg.encode(this.imageValidateCode.generate(validateCode));
//            this.cache.put(validateCode, outStream);
//        }
//
//        resp.setContentType("image/jpeg");
//        resp.setHeader("Expires", "Sat, 16 Jan 1980 12:00:00 GMT");
//        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        resp.setHeader("Pragma", "no-cache");
//
//        resp.getOutputStream().write(outStream.toByteArray());
//
//        req.getSession().setAttribute(SESSION_KEY, validateCode);
//    }
//
//    /**
//     * 获取验证码
//     * @param length 长度
//     * @return 验证码
//     */
//    private String getValidateCode(int length) {
//        StringBuilder validateCode = new StringBuilder(length);
//
//        for (int i = 0; i < length; i++) {
//            validateCode.append(this.random.nextInt(10));
//        }
//
//        return validateCode.toString();
//    }
//
//}
