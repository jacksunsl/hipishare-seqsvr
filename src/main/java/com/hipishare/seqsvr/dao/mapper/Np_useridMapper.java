package com.hipishare.seqsvr.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.hipishare.seqsvr.dao.po.Np_useridPO;

/**
 * <b>np_userid[np_userid]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author sunlei
 * @date 2016-08-18 16:47:58
 */
public interface Np_useridMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param np_useridPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(Np_useridPO np_useridPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param np_useridPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(Np_useridPO np_useridPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param np_useridPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(Np_useridPO np_useridPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return Np_useridPO
	 */
	Np_useridPO selectByKey(@Param(value = "id") Integer id);


	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "id") Integer id);
	
	int updateUseridPOByAccount(Np_useridPO np_useridPO);
	
	Np_useridPO selectUseridPOByAccount(String account);
	
	Np_useridPO findUseridPOByUserid(@Param(value = "userid") Integer userid);
	
}
