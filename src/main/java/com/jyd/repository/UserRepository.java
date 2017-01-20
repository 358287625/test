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
import com.jyd.controller.OrderController;
import com.jyd.vo.PageModel;

@Repository 
public class UserRepository extends BaseRepository{
	private static  Logger log = LoggerFactory.getLogger(UserRepository.class);  
	@Autowired
  	private SqlMapClient sqlMapClient;
	  
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	
	public UserInfor getUserByName(BaseData baseData){
		long s = System.currentTimeMillis();
		Object obj =null;
		try {
			obj = sqlMapClient.queryForObject("getUserByName",baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getUserByName - cost "+(System.currentTimeMillis() -s ));
		return obj==null?null:(UserInfor)obj;
	}
	
	public UserInfor getUserInforById(BaseData baseData){
		long s = System.currentTimeMillis();
		Object obj =null;
		try {
			obj = sqlMapClient.queryForObject("getUserInforById",baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getUserInforById - cost "+(System.currentTimeMillis() -s ));
		return obj==null?null:(UserInfor)obj;
	}
	public int countAll(){
		long s = System.currentTimeMillis();
		int v=0;
		try {
			v = (Integer)sqlMapClient.queryForObject("countallUser");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("countallUser - cost "+(System.currentTimeMillis() -s ));
		return  v ;
	}
	
	@Override
	public Object add(BaseData baseData) throws Exception {
		long s = System.currentTimeMillis();
		sqlMapClient.insert("insert_user",baseData);
		log.info("insert_user - cost "+(System.currentTimeMillis() -s ));
		return null;
	}

	@Override
	public Object edit(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.update("edit_user",baseData);
		log.info("edit_user - cost "+(System.currentTimeMillis() -s ));
		return null;
	}

	public List<String> listPermissionByUserId(UserInfor userInfor){

		long s = System.currentTimeMillis();
		List<String> list =null;
		try {
			list = sqlMapClient.queryForList("listPermissionByUserId",userInfor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("listPermissionByUserId - cost "+(System.currentTimeMillis() -s ));
		return list;
	
	}
	public int  count(BaseData baseData) throws SQLException{
		long s = System.currentTimeMillis();
		Object obj = sqlMapClient.queryForObject("listUserInforByPage_count",baseData);
		log.info("listUserInforByPage_count - cost "+(System.currentTimeMillis() -s ));
		return obj==null?0:(Integer)obj;
	}
	
	@Override
	public List<UserInfor> list(BaseData baseData) {
		long s = System.currentTimeMillis();
		List<UserInfor> users=null;
		try {
			users = sqlMapClient.queryForList("listUserInforByPage", (UserInfor)baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("insert_sysconfig - cost "+(System.currentTimeMillis() -s ));
		return users;
	}

	@Override
	public Object del(BaseData baseData) {
		long s = System.currentTimeMillis();
		try {
			 sqlMapClient.delete("del_user",baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("del_user - cost "+(System.currentTimeMillis() -s ));
		return null;
	}}
