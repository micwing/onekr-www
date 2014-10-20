/**
 * @Project: main-framework
 * @File: AppException.java
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

import onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer;


/** 
 * @ClassName: AppException 
 * @Description: 
 * @author micwing
 * @date 2013-3-26 下午5:28:28 
 */ 
public class AppException extends RuntimeException implements ErrorCode {
	private static final long serialVersionUID = 4595549517180869921L;
	private int code = SERVER_ERROR;
	private Object args[];

	private String renderedMessage;
	
	public AppException() {
	}
	
	public AppException(String message) {
		super(message);
		setRenderedMessage(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
		setRenderedMessage(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public AppException(int code) {
		super();
		this.code = code;
		this.renderedMessage = CustomizedPropertyPlaceholderConfigurer.getContextProperty("errorcode."+code);
	}

    public AppException(int code, Throwable cause) {
        this(cause);
        this.code = code;
        this.renderedMessage = CustomizedPropertyPlaceholderConfigurer.getContextProperty("errorcode."+code);
    }

	public AppException(int code, String defaultMessage, Object... args) {
		super(defaultMessage);
		setRenderedMessage(defaultMessage);
		this.code = code;
		this.args = args;
	}

	public AppException(int code, String defaultMessage, Throwable cause, Object... args) {
		super(defaultMessage, cause);
		setRenderedMessage(defaultMessage);
		this.code = code;
		this.args = args;
	}

	public int getCode() {
		return code;
	}

	public Object[] getArgs() {
		return args;
	}
	
	@Override
	public String getMessage() {
		return renderedMessage;
	}

	@Override
	public String toString() {
		return getMessage();
	}

	public void setRenderedMessage(String renderedMessage) {
		this.renderedMessage = renderedMessage;
	}

}
