package com.jyd.beans;

import java.util.Date;
import java.util.List;

/**
 * 订单列表
 */
public class Order extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4785528600212952379L;
	
	private String id;
	/**
	 * 订单编号
	 */
	private String orderNum;
	/**
	 * 总计
	 */
	private int total;
	/**
	 * 订单状态
	 */
	private String orderStatus;
	/**
	 * 订单付款状态
	 */
	private String payStatus;

	/**
	 * 订单创建人的姓名
	 */
	private String userName;
	/**
	 * 订单创建人的编码
	 */
	private String userNum;
	/**
	 * 订单创建人的简码
	 */
	private String userShortCode;

	/**
	 * 订单创建人的id
	 */
	private String userId;
	/**
	 * 订单关联客户的公司
	 */
	private String companyName;
	private Date utime;
	private Date ctime;
	/**
	 * 订单明细项
	 */
	private List<OrderItem> items;
	/**
	 * 子明细的条数
	 */
	private int length;
	
	private String loginUserNum;
	/**
	 * 订单客户名
	 */
	private String customerName;

	/**
	 * 订单客户编码
	 */
	private String customerNum;
	/**
	 * 订单客户简码
	 */
	private String customerShortCode;
	/**
	 * 订单客户id
	 */
	private String customerId;
	/**
	 *  订单客户单位
	 */
	private String customerCompanyName;
	/***
	 * 订单客户联系电话
	 */
	private String customerPhone;
	/***
	 * 结清备注
	 */
	private String payMemo;
	/***
	 * 作废备注
	 */
	private String cancelMemo;
	
	public int getLength() {
		if(null == items)
			length=0;
		else
			length = items.size();
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getLoginUserNum() {
		return loginUserNum;
	}

	public void setLoginUserNum(String loginUserNum) {
		this.loginUserNum = loginUserNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserShortCode() {
		return userShortCode;
	}

	public void setUserShortCode(String userShortCode) {
		this.userShortCode = userShortCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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


	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCustomerShortCode() {
		return customerShortCode;
	}

	public void setCustomerShortCode(String customerShortCode) {
		this.customerShortCode = customerShortCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCompanyName() {
		return customerCompanyName;
	}

	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getPayMemo() {
		return payMemo;
	}

	public void setPayMemo(String payMemo) {
		this.payMemo = payMemo;
	}

	public String getCancelMemo() {
		return cancelMemo;
	}

	public void setCancelMemo(String cancelMemo) {
		this.cancelMemo = cancelMemo;
	}
}
