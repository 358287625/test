package com.jyd.beans;

import java.util.Date;

/**
 * 用户权限关联
 * 
 * @author Administrator
 * 
 */
public class UserPermission extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -19504758414844476L;
	private String id;
	/**
	 * 与权限关联的用户的id
	 */
	private String userId;
	/**
	 * 权限id
	 */
	private String permissionId;
	private Date ctime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
