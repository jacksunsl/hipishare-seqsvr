package com.hipishare.seqsvr.controller;

import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;

import com.hipishare.seqsvr.dao.po.Np_useridPO;
import com.hipishare.seqsvr.domain.SeqResponse;
import com.hipishare.seqsvr.exception.SeqServerException;
import com.hipishare.seqsvr.service.UserIdPoolService;
import com.hipishare.seqsvr.utils.SpringContextUtil;

public class SeqServerController {

	private static final Logger LOGGER = Logger.getLogger(SeqServerController.class);
	
	private UserIdPoolService userIdPoolService;
	
	public SeqServerController(){
		try {
			userIdPoolService = (UserIdPoolService)SpringContextUtil.getBean("userIdPoolService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询卡信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Object getSeq(Request request, Response response) {
		long t1 = System.currentTimeMillis();
		LOGGER.info("[SeqServerController.getSeq][begin]");
		SeqResponse seqResponse = new SeqResponse();
		try {
			String account = request.getHeader("account");
			Np_useridPO userIdPO = userIdPoolService.getUserIdPO(account);
			seqResponse.setFlag(true);
			seqResponse.setAccount(userIdPO.getAccount());
			seqResponse.setOpenid(userIdPO.getOpenid());
			seqResponse.setUserid(userIdPO.getUserid());
		} catch (SeqServerException e) {
			seqResponse.setFlag(false);
			seqResponse.setMsg(e.getMessage());
			LOGGER.error(e);
		} catch (Exception e) {
			seqResponse.setFlag(false);
			seqResponse.setMsg("系统异常，请检查seqsvr服务。");
			LOGGER.error(e);
		}
		long t2 = System.currentTimeMillis();
		LOGGER.info("[SeqServerController.getSeq][end] spend:" + (t2-t1));
		return seqResponse;
	}
	
}
