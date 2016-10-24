package com.hipishare.seqsvr.controller.helper;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.hipishare.seqsvr.domain.request.UseridSeqReq;
import com.hipishare.seqsvr.exception.SeqServerException;

public class UseridSeqHelper {

	private static Logger LOG = LogManager.getLogger(UseridSeqHelper.class.getName());
	
	/**
	 * 验证获取用户id
	 * @param useridSeqReq
	 * @throws SeqServerException
	 */
	public static void getUseridSeq(UseridSeqReq useridSeqReq) throws SeqServerException {
		LOG.info("[UseridSeqHelper.getUseridSeq][begin]获取用户id校验...");
		if (StringUtils.isEmpty(useridSeqReq.getAccount())) {
			SeqServerException.raise("2002");
		}
		LOG.info("[UseridSeqHelper.getUseridSeq][end]获取用户id校验...");
	}
	
}
