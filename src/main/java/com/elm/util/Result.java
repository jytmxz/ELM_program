package com.elm.util;

public class Result {

	private static Integer code;
	private static String message;
	private static Object data;
	public Result() {
	}
	
	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static Result ok(Object obj) {
		return new Result(200,"�ɹ�",obj);
	}
	
	public static Result no() {
		return new Result(400,"ʧ��");
	}
	
}
