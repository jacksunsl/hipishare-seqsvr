package com.hipishare.seqsvr.controller;

import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;

import com.hipishare.seqsvr.controller.helper.SeqServerHelper;
import com.hipishare.seqsvr.controller.helper.UseridSeqHelper;
import com.hipishare.seqsvr.domain.request.RequestObject;
import com.hipishare.seqsvr.domain.request.UseridSeqReq;
import com.hipishare.seqsvr.domain.response.ResponseObject;
import com.hipishare.seqsvr.domain.response.UseridSeqRsp;
import com.hipishare.seqsvr.exception.SeqServerException;
import com.hipishare.seqsvr.service.UserIdPoolService;
import com.hipishare.seqsvr.utils.SpringContextUtil;

public class UseridSeqController extends SeqServerHelper {

	private static final Logger LOGGER = Logger.getLogger(UseridSeqController.class);
	
	private UserIdPoolService userIdPoolService;
	
	public UseridSeqController(){
		try {
			userIdPoolService = (UserIdPoolService)SpringContextUtil.getBean("userIdPoolService");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("[SeqServerController] userIdPoolService注入失败");
		}
	}
	
	/**
	 * 查询卡信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Object getUseridSeq(Request request, Response response) {
		long t1 = System.currentTimeMillis();
		LOGGER.info("[SeqServerController.getSeq][begin]");
		ResponseObject responseObj = new ResponseObject();
		try {
			// 业务预处理
			RequestObject requestObject = preDobusiness(request);
			
			// 解析业务请求数据
			LOGGER.info("[SeqServerController.getSeq][data]"+requestObject.getData());
			UseridSeqReq useridSeqReq = gson.fromJson(requestObject.getData(), UseridSeqReq.class);
			
			// 验证请求参数
			UseridSeqHelper.getUseridSeq(useridSeqReq);
			
			// 业务处理
			UseridSeqRsp userIdPO = userIdPoolService.getUserIdPO(useridSeqReq.getAccount());
			
			// 结果封装
			responseObj.setCode("00");
			responseObj.setResult(gson.toJson(userIdPO));
		} catch (SeqServerException e) {
			responseObj.setCode(e.getCode());
			responseObj.setMsg(e.getMessage());
			LOGGER.error(e);
		} catch (Exception e) {
			responseObj.setCode("99");
			responseObj.setMsg("系统异常，请检查seqsvr服务。");
			LOGGER.error(e);
		}
		long t2 = System.currentTimeMillis();
		LOGGER.info("[SeqServerController.getSeq][end] spend:" + (t2-t1));
		return responseObj;
	}
	
}
