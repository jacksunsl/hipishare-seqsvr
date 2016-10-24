package com.hipishare.seqsvr.controller.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.restexpress.Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hipishare.seqsvr.domain.request.RequestObject;
import com.hipishare.seqsvr.exception.SeqServerException;
import com.hipishare.seqsvr.utils.MD5Util;
import com.hipishare.seqsvr.utils.PropertiesUtil;

public class SeqServerHelper {
	private static final Logger LOGGER = Logger.getLogger(SeqServerHelper.class);

	protected static PropertiesUtil properties4Sysconfig = null;
	protected static PropertiesUtil properties4Message = null;
	protected String requestBody;// 请求数据
	protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();;

	static {
		try {
			if (null == properties4Sysconfig) {
				properties4Sysconfig = new PropertiesUtil("sysconfig.properties");
			}
			if (null == properties4Message) {
				properties4Message = new PropertiesUtil("message.properties");
			}
		} catch (IOException e) {
			LOGGER.error("load properties is error in Component.", e);
		}
	}

	/**
	 * 解析请求参数
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	protected void paraseRequestBody(Request request)
			throws UnsupportedEncodingException {
		byte[] req = new byte[request.getBody().readableBytes()];
		request.getBody().readBytes(req);
		String body = new String(req, "UTF-8");
		body = URLDecoder.decode(body, "UTF-8");
		this.requestBody = body;
	}

	/**
	 * 获取请求参数
	 * 
	 * @param paramName
	 * @return
	 */
	protected String getParameterByName(String paramName) {
		if (null == this.requestBody) {
			return null;
		}
		String[] params = this.requestBody.split("&");
		for (String param : params) {
			if (paramName.equalsIgnoreCase(param.split("=", 2)[0])) {
				return param.split("=", 2)[1];
			}
		}
		return null;
	}

	/**
	 * 封装请求对象
	 * 
	 * @return
	 */
	protected RequestObject getRequestObject() {
		RequestObject requestObject = new RequestObject();
		String data = getParameterByName("data");
		String sign = getParameterByName("sign");
		String timestamp = getParameterByName("timestamp");
		if (null == data || "".equals(data)) {
			SeqServerException.raise("1010");
		}
		if (null == sign || "".equals(sign)) {
			SeqServerException.raise("1011");
		}
		if (null == timestamp || "".equals(timestamp)) {
			SeqServerException.raise("1012");
		}
		requestObject.setData(data);
		requestObject.setSign(sign);
		requestObject.setTimestamp(timestamp);
		LOGGER.info("[ProductServerHelper.getRequestObject][request]"
				+ gson.toJson(requestObject));
		return requestObject;
	}

	/**
	 * 签名校验
	 * 
	 * @param requestObject
	 * @throws Exception
	 */
	protected void validSign(RequestObject requestObject) throws Exception {
		Calendar c = Calendar.getInstance();
		c.getTimeInMillis();
		String scretkey = properties4Sysconfig.getProperty("secretkey");
		String sign = MD5Util.MD5Encode(scretkey + requestObject.getTimestamp()
				+ requestObject.getData());
		if (!sign.equalsIgnoreCase(requestObject.getSign())) {
			SeqServerException.raise("1014");
		}
	}

	/**
	 * 业务预处理
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected RequestObject preDobusiness(Request request) throws Exception {
		LOGGER.info("[ProductServerHelper.preDobusiness][begin]");
		// 解析请求数据
		paraseRequestBody(request);

		// 获取请求参数
		RequestObject requestObject = getRequestObject();

		// 验证签名
//		validSign(requestObject);
		LOGGER.info("[ProductServerHelper.preDobusiness][end]");
		return requestObject;
	}
}
