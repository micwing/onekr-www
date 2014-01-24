/**
 * @Project: main-framework
 * @File: Result.java
 * @package onekr.framework.result
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
package onekr.framework.result;

import java.util.HashMap;
import java.util.Map;

import onekr.framework.exception.AppException;

/**
 * @ClassName: Result
 * @Description:
 * @author micwing
 * @date 2013-3-26 下午4:52:29
 */
public class Result {

	private int code = 0;

	private ResultType resultType;

	private String message;
	
	private Object value;

	public Result() {
		this(0, "操作成功", ResultType.success);
	}

	public Result(int code) {
		this(code, 
				code == 0 ? "操作成功" : "操作失败", 
						code == 0 ? ResultType.success : ResultType.error);
	}

	public Result(String message) {
		this(0, message, ResultType.success);
	}

	public Result(int code, String message) {
		this(code, message, code == 0 ? ResultType.success : ResultType.error);
	}

	public Result(String message, ResultType resultType) {
		this(0, message, resultType);
	}

	public Result(int code, String message, ResultType resultType) {
		super();
		this.code = code;
		this.message = message;
		this.resultType = resultType;
	}

	public Result(AppException e) {
		this.code = e.getCode();
		this.message = e.getMessage();
		this.resultType = ResultType.error;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("code", code);
		map.put("message", message);
		map.put("resultType", resultType);
		return map;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
