package com.jyd.repository;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jyd.beans.BaseData;
import com.jyd.beans.Sysconfig;
import com.jyd.controller.OrderController;
import com.jyd.vo.PageModel;

@Repository 
public class SysconfigRepository extends BaseRepository{
	private static  Logger log = LoggerFactory.getLogger(OrderController.class);  
	@Autowired
  	private SqlMapClient sqlMapClient;
	  
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	@Override
	public Object add(BaseData baseData) {
		long s = System.currentTimeMillis();
		try {
			sqlMapClient.insert("insert_sysconfig",(Sysconfig)baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("insert_sysconfig - cost "+(System.currentTimeMillis() -s ));
		return null;
	}

	@Override
	public Object edit(BaseData baseData) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Sysconfig> getSysconfigs(Sysconfig baseData ){
		long s = System.currentTimeMillis();
		 List<Sysconfig> list=null;
		try {
			list =sqlMapClient.queryForList("getSysconfigs",baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getSysconfigs - cost "+(System.currentTimeMillis() -s ));
		return list;
	}
	
	public Sysconfig getSysconfigsByNameAndnum(Sysconfig baseData ){
		long s = System.currentTimeMillis();
		Object object=null;
		try {
			object =sqlMapClient.queryForObject("getSysconfigsByNameAndnum",baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getSysconfigsByNameAndnum - cost "+(System.currentTimeMillis() -s ));
		return object==null?null:(Sysconfig)object;
	}
	
	
	
	public int count(BaseData baseData) {
		long s = System.currentTimeMillis();
		int count=0;
		try {
			Object obj =sqlMapClient.queryForObject("list_sysconfig_byPage_count",(Sysconfig)baseData);
			count = obj==null? 0:(Integer) obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("list_sysconfig_byPage_count - cost "+(System.currentTimeMillis() -s ));
		return count;
	}
	
	@Override
	public List list(BaseData baseData) {
		long s = System.currentTimeMillis();
		List list=null;
		try {
			list=sqlMapClient.queryForList("list_sysconfig_byPage",(Sysconfig)baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("list_sysconfig_byPage - cost "+(System.currentTimeMillis() -s ));
		return list;
	}
	
	public List associateType(Sysconfig cfg) {
		long s = System.currentTimeMillis();
		List list=null;
		try {
			list=sqlMapClient.queryForList("associateType",cfg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("associateType - cost "+(System.currentTimeMillis() -s ));
		return list;
	}
	@Override
	public Object del(BaseData baseData) {
		long s = System.currentTimeMillis();
		try {
			Object obj =sqlMapClient.delete("del_sysconfig_",(Sysconfig)baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("del_sysconfig_ - cost "+(System.currentTimeMillis() -s ));
		return null;
	}}
