/**
 * @Project: main-framework
 * @File: ErrorCode.java
 * @package onekr.framework.exception
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
package onekr.framework.exception;

/** 
 * @ClassName: ErrorCode 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:28:56 
 */ 
public interface ErrorCode {
    /**
     * 自定义错误起始值
     */
    static final int _CUSTOM_ERROR_START = 100;

    /**
     * 请求成功执行
     */
    static final int SUCCEED = 0;

    /**
     * 服务器内部错误
     */
    static final int SERVER_ERROR = 1;

    /**
     * 非法参数
     */
    static final int ILLEGAL_PARAM = 2;

    /**
     * 必须参数不存在
     */
    static final int MISS_PARAM = 3;

    /**
     * 状态错误
     */
    static final int ILLEGAL_STATE = 4;

    /**
     * 不支持此操作
     */
    static final int UN_SUPPORTED = 5;

    /**
     * 没有权限
     */
    static final int NO_PERMISSON = 6;
    
    /**
	 * 验证错误
	 */
	static final int VALIDATE_ERROR = 7;
    
    /**
	 * 参数不在指定范围内
	 */
	static final int RANGE_ERROR = 8;
	
	/**
	 * 不匹配正则表达式
	 */
	static final int PATTERN_NOT_MATCH = 9;
	
	/**
	 * 参数格式错误
	 */
	static final int PARAM_FORMAT_ERROR = 10;
	
	/**
	 * 验证码不匹配
	 */
	static final int PAPTCHA_NOT_MATCH = 11;
	
	/**
	 * 登录凭证与密码不匹配
	 */
	static final int USER_PSW_NOT_MATCH = 12;

    /**
     * 对象不存在
     */
    static final int ENTITY_NOT_FOUND = 50;

    /**
     * 对象已存在
     */
    static final int ENTITY_ALREADY_EXIST = 51;

    /**
     * 子数据仍存在
     */
    static final int SUB_ENTITY_EXIST = 52;
    
    /**
     * 用户不存在
     */
    static final int USER_NOT_EXIST = 101;
    
    /**
     * 该目录下有文件，不能删除，请先删除文件后再试
     */
    static final int PATH_HAVE_FILE_CANNOT_DELETE = 102;
}
