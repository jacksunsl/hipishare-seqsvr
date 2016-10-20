package com.hipishare.seqsvr.dao.po;

import java.util.Date;

/**
 * <b>np_userid[np_userid]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author sunlei
 * @date 2016-08-18 16:47:57
 */
public class Np_useridPO {

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 用户id
	 */
	private Integer userid;
	
	/**
	 * openid
	 */
	private String openid;
	
	/**
	 * 号段,userid的号段
	 */
	private Integer section;
	
	/**
	 * 状态（0：未使用 1：已使用 2：锁定）
	 */
	private Integer status;
	
	/**
	 * 创建时间，资源号产生时间
	 */
	private Date create_time;
	
	/**
	 * 使用时间
	 */
	private Date use_time;
	
	/**
	 * 用户账号
	 */
	private String account;
	

	/**
	 * id
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 用户id
	 * 
	 * @return userid
	 */
	public Integer getUserid() {
		return userid;
	}
	
	/**
	 * openid
	 * 
	 * @return openid
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**
	 * 号段,userid的号段
	 * 
	 * @return section
	 */
	public Integer getSection() {
		return section;
	}
	
	/**
	 * 状态（0：未使用 1：已使用 2：锁定）
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 创建时间，资源号产生时间
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 * 使用时间
	 * 
	 * @return use_time
	 */
	public Date getUse_time() {
		return use_time;
	}
	
	/**
	 * 用户账号
	 * 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 用户id
	 * 
	 * @param userid
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	/**
	 * openid
	 * 
	 * @param openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	/**
	 * 号段,userid的号段
	 * 
	 * @param section
	 */
	public void setSection(Integer section) {
		this.section = section;
	}
	
	/**
	 * 状态（0：未使用 1：已使用 2：锁定）
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 创建时间，资源号产生时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * 使用时间
	 * 
	 * @param use_time
	 */
	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}
	
	/**
	 * 用户账号
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	

}