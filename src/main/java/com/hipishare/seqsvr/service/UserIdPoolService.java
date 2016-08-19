package com.hipishare.seqsvr.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hipishare.seqsvr.dao.mapper.Np_useridMapper;
import com.hipishare.seqsvr.dao.po.Np_useridPO;
import com.hipishare.seqsvr.exception.SeqServerException;

@Service("userIdPoolService")
public class UserIdPoolService {

	@Autowired
	private Np_useridMapper useridMapper;
	
	/**
	 * 根据account获取userid
	 * @param account
	 * @return
	 * @throws SeqServerException
	 */
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
}
