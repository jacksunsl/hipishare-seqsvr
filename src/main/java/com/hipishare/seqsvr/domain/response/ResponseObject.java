package com.hipishare.seqsvr.domain.response;

public class ResponseObject {
	
	private String code;// 返回编码
	private String msg;// 返回消息
	private String result;// 返回结果,json类型
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
