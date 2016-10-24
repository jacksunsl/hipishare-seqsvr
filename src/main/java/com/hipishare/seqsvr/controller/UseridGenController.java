package com.hipishare.seqsvr.controller;

import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;
import org.springframework.util.StringUtils;

import com.hipishare.seqsvr.domain.response.ResponseObject;
import com.hipishare.seqsvr.exception.SeqServerException;
import com.hipishare.seqsvr.service.UserIdPoolService;
import com.hipishare.seqsvr.utils.SpringContextUtil;

/**
 * 用户id生产器
 * @author sunlei
 * @date 2016年10月20日
 */
public class UseridGenController {
	private static final Logger LOGGER = Logger.getLogger(UseridGenController.class);
	private UserIdPoolService userIdPoolService;
	
	public UseridGenController() {
		try {
			userIdPoolService = (UserIdPoolService)SpringContextUtil.getBean("userIdPoolService");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("[UseridGenController] userIdPoolService注入失败");
		}
	}

	/**
	 * 生成用户id
	 * @param request
	 * @param response
	 * @return
	 */
	public Object genUserid(Request request, Response response) {
		long t1 = System.currentTimeMillis();
		LOGGER.info("[UseridGenController.genUserid][begin]");
		ResponseObject responseObj = new ResponseObject();
		try {
			int genNum = 100;
			if (!StringUtils.isEmpty(request.getHeader("genNum"))) {
				genNum = Integer.parseInt(request.getHeader("genNum"));
			}
			userIdPoolService.genUserid(genNum);
			responseObj.setCode("00");
			responseObj.setMsg("生成成功");
		} catch (SeqServerException e) {
			responseObj.setCode("99");
			responseObj.setMsg(e.getMessage());
			LOGGER.error(e);
		} catch (Exception e) {
			responseObj.setCode("99");
			responseObj.setMsg("系统异常，请检查seqsvr服务。");
			LOGGER.error(e);
		}
		long t2 = System.currentTimeMillis();
		LOGGER.info("[UseridGenController.genUserid][end] spend:" + (t2-t1));
		return responseObj;
	}
}
