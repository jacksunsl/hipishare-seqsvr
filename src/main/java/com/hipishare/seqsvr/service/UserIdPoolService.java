package com.hipishare.seqsvr.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hipishare.seqsvr.dao.mapper.Np_useridMapper;
import com.hipishare.seqsvr.dao.po.Np_useridPO;
import com.hipishare.seqsvr.exception.SeqServerException;
import com.hipishare.seqsvr.utils.RandomCode;

@Service("userIdPoolService")
public class UserIdPoolService {

	private static final Logger LOGGER = Logger.getLogger(UserIdPoolService.class);
	
	@Autowired
	private Np_useridMapper useridMapper;
	
	/**
	 * 根据account获取userid
	 * @param account
	 * @return
	 * @throws SeqServerException
	 */
	@Transactional(readOnly = true)
	public Np_useridPO getUserIdPO(String account) throws SeqServerException {
		Np_useridPO np_useridPO = null;
		if (null == account || "".equals(account)) {
			SeqServerException.raise("2002");
		}
		np_useridPO = useridMapper.selectUseridPOByAccount(account);
		if (null == np_useridPO) {
			np_useridPO = new Np_useridPO();
			np_useridPO.setAccount(account);
			np_useridPO.setStatus(1);
			np_useridPO.setUse_time(new Date());
			int result = useridMapper.updateUseridPOByAccount(np_useridPO);// 先置为已使用
			if (1 != result) {
				SeqServerException.raise("2001");
			}
			np_useridPO = null;
			np_useridPO = useridMapper.selectUseridPOByAccount(account);
		}
		return np_useridPO;
	}
	
	/**
	 * 生成用户id
	 * @param genNum
	 * @throws SeqServerException
	 */
	public void genUserid(int genNum) throws SeqServerException {
		LOGGER.info("[UserIdPoolService.genUserid] genNum="+genNum);
		Np_useridPO np_useridPO = null;
		for (int i=0;i<genNum;i++) {
			if (i%200 == 0) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Integer randomUserid = RandomCode.getRandomNum(8);
			LOGGER.info("[UserIdPoolService.genUserid] randomUserid="+randomUserid);
			Np_useridPO useridPO = useridMapper.findUseridPOByUserid(randomUserid);
			if (null == useridPO) {
				np_useridPO = new Np_useridPO();
//				np_useridPO.setAccount(account);
				np_useridPO.setCreate_time(new Date());
//				np_useridPO.setOpenid(openid);
				np_useridPO.setSection(10);
				np_useridPO.setStatus(0);
				np_useridPO.setUserid(randomUserid);
				useridMapper.insert(np_useridPO);
			}
		}
	}
}
