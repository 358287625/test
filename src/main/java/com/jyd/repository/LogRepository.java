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
import com.jyd.vo.PageModel;

@Repository 
public class LogRepository extends BaseRepository{
	private static  Logger log = LoggerFactory.getLogger(UserRepository.class);
	@Autowired
  	private SqlMapClient sqlMapClient;
	  
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@Override
	public Object add(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.insert("insert_syslog",baseData);
		log.info("insert_syslog - cost "+(System.currentTimeMillis() -s ));
		return null;
	}

	@Override
	public Object edit(BaseData baseData) {
		// TODO Auto-generated method stub
		return null;
	}

	public int  count(BaseData baseData) throws SQLException{
		long s = System.currentTimeMillis();
		Object obj = sqlMapClient.queryForObject("listLogByPage_count",baseData);
		log.info("listLogByPage_count - cost "+(System.currentTimeMillis() -s ));
		return obj==null?0:(Integer)obj;
	}
	
	@Override
	public List list(BaseData baseData) {
		long s = System.currentTimeMillis();
		List<UserInfor> users=null;
		try {
			users = sqlMapClient.queryForList("listLogByPage", baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("listLogByPage - cost "+(System.currentTimeMillis() -s ));
		return users;
	}

	@Override
	public Object del(BaseData baseData) {
		// TODO Auto-generated method stub
		return null;
	}}
