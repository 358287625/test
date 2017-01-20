package com.jyd.beans;

import java.util.Date;
import java.util.List;

/**
 * 公司用户
 * 
 * @author Administrator
 * 
 */
public class UserInfor extends BaseData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4718444006158341829L;
	private String id;
	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 性别 0:男性  1：女性
	 */
	private int sex;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 身份证号
	 */
	private String identityCard;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 登录密码
	 */
	private String pwd;
	/**
	 * 用户编码
	 */
	private String num;

	/**
	 * 用户简码
	 */
	private String shortCode;
	/**
	 * 所属公司名称
	 */
	private String companyName;
	/**
	 * 所属公司id
	 */
	private String companyId;
	/**
	 * 用户关联的权限
	 */
	private List<String> permissions;
	private Date utime;
	private Date ctime;
	private String uuid;
	/**
	 *  打印单据
	 */
	private String printOrder;
	/**
	 * 收款
	 */
	private String received;
	/**
	 *修改接件单
	 */

	private String editOrder;
	/**
	 * 开接件单
	 */
	private String addOrder;
	/**
	 * 用户登录密码的明文
	 */
	private String mwpwd;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public String getPrintOrder() {
		return printOrder;
	}

	public void setPrintOrder(String printOrder) {
		this.printOrder = printOrder;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public String getEditOrder() {
		return editOrder;
	}

	public void setEditOrder(String editOrder) {
		this.editOrder = editOrder;
	}

	public String getAddOrder() {
		return addOrder;
	}

	public void setAddOrder(String addOrder) {
		this.addOrder = addOrder;
	}

	public String getMwpwd() {
		return mwpwd;
	}

	public void setMwpwd(String mwpwd) {
		this.mwpwd = mwpwd;
	}


}
