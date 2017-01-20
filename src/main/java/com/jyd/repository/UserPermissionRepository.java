package com.jyd.repository;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jyd.beans.BaseData;
import com.jyd.beans.UserInfor;
import com.jyd.beans.UserPermission;
import com.jyd.controller.OrderController;
import com.jyd.vo.PageModel;

@Repository 
public class UserPermissionRepository extends BaseRepository{
	private static  Logger log = LoggerFactory.getLogger(UserPermissionRepository.class);  
	@Autowired
  	private SqlMapClient sqlMapClient;
	  
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	
	@Override
	public Object add(BaseData baseData) throws Exception {
		long s = System.currentTimeMillis();
		sqlMapClient.insert("insert_userpermission",baseData);
		log.info("insert_userpermission - cost "+(System.currentTimeMillis() -s ));
		return null;
	}

	@Override
	public List<UserPermission> list(BaseData baseData) {
		long s = System.currentTimeMillis();
		List<UserPermission> p=null;
		try {
			p = sqlMapClient.queryForList("list_userpermission", (UserPermission)baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("list_userpermission - cost "+(System.currentTimeMillis() -s ));
		return p;
	}

	@Override
	public Object del(BaseData baseData) {
		long s = System.currentTimeMillis();
		try {
			 sqlMapClient.delete("del_userpermission_",((UserPermission)baseData).getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("del_userpermission_ - cost "+(System.currentTimeMillis() -s ));
		return null;
	}

	@Override
	public Object edit(BaseData baseData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}}
