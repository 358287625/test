package com.jyd.beans;

import java.util.Date;

/**
 * 客户表
 * 
 * @author Administrator
 * 
 */

public class Customer extends BaseData {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2748219673947048510L;
	/**
	 * 
	 */
	private String id;
	/**
	 * 客户编码
	 */
	private String num;
	/**
	 * 客户简码
	 */
	private String shortCode;
	/**
	 * 客户名称
	 */
	private String name;
	/**
	 * 客户公司名称
	 */
	private String companyName;
	/**
	 * 客户联系电话
	 */
	private String phone;
	/**
	 * 客户地址
	 */
	private String address;
	/**
	 * 客户创建人姓名
	 */

	private String createUserName;
	/**
	 * 客户创建人编号
	 */
	private String createUserNum;
	/**
	 * 客户创建人简码
	 */
	private String createUserShortCode;

	/**
	 * 客户创建人id
	 */
	private String createUserId;

	/**
	 * 
	 */
	private Date utime;
	/**
	 * 
	 */
	private Date ctime;
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}


	public String getCreateUserNum() {
		return createUserNum;
	}

	public void setCreateUserNum(String createUserNum) {
		this.createUserNum = createUserNum;
	}

	public String getCreateUserShortCode() {
		return createUserShortCode;
	}

	public void setCreateUserShortCode(String createUserShortCode) {
		this.createUserShortCode = createUserShortCode;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}
