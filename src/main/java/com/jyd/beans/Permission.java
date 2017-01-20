package com.jyd.beans;

/**
 * 权限表
 * 
 * @author Administrator
 * 
 */
public class Permission extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9159875774689865387L;
	private String id;
	/**
	 * 权限编号
	 */
	private String num;

	/**
	 * 权限名称
	 */
	private String name;

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

}
