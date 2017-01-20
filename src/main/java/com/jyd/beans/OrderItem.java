package com.jyd.beans;

import java.util.Date;

public class OrderItem extends BaseData {

	private static final long serialVersionUID = 4045970105116544382L;
	/**
	 * 订单明细表的
	 */
	private String id;
	private String tp;
	private String unit;
	private String spec;
	private String KCP;
	/**
	 * 订单明细表对应的title订单编号
	 */
	private String code;
	/**
	 * 文件大小
	 */
	private String file_size;
	/**
	 * 订单明细表的商品名称
	 */
	private String name;

	/**
	 * 订单明细表的单价
	 */
	private int price;

	/**
	 * 订单明细表的数量
	 */
	private int num;
	/**
	 * 订单明细表的折扣
	 */
	private String discount;

	/**
	 * 订单明细表的小计金额
	 */
	private int total;

	private Date utime;
	private Date ctime;
	
	private String loginUserNum;
	/**
	 * 订单备注
	 */
	private String memo;
	
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
	
	
	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getKCP() {
		return KCP;
	}

	public void setKCP(String kCP) {
		KCP = kCP;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
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

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
