package com.jyd.beans;

import java.util.Date;

/**
 * 系统参数配置表
 * 
 * @author Administrator
 * 
 */
public class Sysconfig extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7089815421393360898L;
	private String id;
	/**
	 * 系统参数配置类型
	 */
	private String num;

	/**
	 * 系统参数配置名称
	 */
	private String name;
	/**
	 * 系统参数配置创建人姓名
	 */
	private String createUserName;
	/**
	 * 系统参数配置创建人简码
	 */
	private String createUserShortCode;
	/**
	 * 系统参数配置创建人编码
	 */
	private String createUserNum;
	/**
	 * 系统参数配置创建人id
	 */
	private String createUserId;
	private Date ctime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserShortCode() {
		return createUserShortCode;
	}

	public void setCreateUserShortCode(String createUserShortCode) {
		this.createUserShortCode = createUserShortCode;
	}


	public String getCreateUserNum() {
		return createUserNum;
	}

	public void setCreateUserNum(String createUserNum) {
		this.createUserNum = createUserNum;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
