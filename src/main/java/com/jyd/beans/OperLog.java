package com.jyd.beans;

import java.util.Date;

/**
 * 操作日志记录
 * 
 * @author Administrator
 * 
 */
public class OperLog extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7249481110910324098L;
	private String id;

	/**
	 * 日志操作人姓名
	 */
	private String operUserName;

	/**
	 * 日志操作人id
	 */
	private String operUserId;

	/**
	 * 日志操作人编码
	 */
	private String operUserNum;

	/**
	 * 日志操作人简码
	 */
	private String operShortCode;

	/**
	 * 日志操作功能名称
	 */
	private String operFuncName;

	/**
	 * 日志操作人登录电脑ip
	 */
	private String operIp;

	/**
	 * 日志操作人操作的订单号
	 */
	private String operOrderNum;

	private Date ctime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperUserName() {
		return operUserName;
	}

	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}

	public String getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}


	public String getOperUserNum() {
		return operUserNum;
	}

	public void setOperUserNum(String operUserNum) {
		this.operUserNum = operUserNum;
	}

	public String getOperShortCode() {
		return operShortCode;
	}

	public void setOperShortCode(String operShortCode) {
		this.operShortCode = operShortCode;
	}

	public String getOperFuncName() {
		return operFuncName;
	}

	public void setOperFuncName(String operFuncName) {
		this.operFuncName = operFuncName;
	}

	public String getOperIp() {
		return operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	public String getOperOrderNum() {
		return operOrderNum;
	}

	public void setOperOrderNum(String operOrderNum) {
		this.operOrderNum = operOrderNum;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
