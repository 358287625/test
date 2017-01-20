package com.jyd.repository;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jyd.beans.BaseData;
import com.jyd.beans.Customer;
import com.jyd.beans.UserInfor;
import com.jyd.vo.PageModel;
@Repository 
public class CustomerRepository extends BaseRepository{
	private static  Logger log = LoggerFactory.getLogger(CustomerRepository.class);
	@Autowired
  	private SqlMapClient sqlMapClient;
	  
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	public Customer getCustomerById(BaseData baseData){
		long s = System.currentTimeMillis();
		Object obj =null;
		try {
			obj = sqlMapClient.queryForObject("getCustomerById",baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getCustomerById - cost "+(System.currentTimeMillis() -s ));
		return obj==null?null:(Customer)obj;
	}
	
	
	public List<Customer> getLikeCustomerByCustomer(Customer  customer){
		long s = System.currentTimeMillis();
		Object obj =null;
		try {
			obj = sqlMapClient.queryForList("getLikeCustomerByCustomer",customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getLikeCustomerByCustomer - cost "+(System.currentTimeMillis() -s ));
		return obj==null?null:(List<Customer>)obj;
	}
	
	public List<Customer> getCustomerByCustomer(Customer  customer){
		long s = System.currentTimeMillis();
		Object obj =null;
		try {
			obj = sqlMapClient.queryForList("getCustomerByCustomer",customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getCustomerByCustomer - cost "+(System.currentTimeMillis() -s ));
		return obj==null?null:(List<Customer>)obj;
	}
	public int countAll(){
		long s = System.currentTimeMillis();
		int v=0;
		try {
			v = (Integer)sqlMapClient.queryForObject("countallCustomer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("countallCustomer - cost "+(System.currentTimeMillis() -s ));
		return  v ;
	}
	@Override
	public Object add(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.insert("insert_customer",baseData);
		log.info("insert_customer - cost "+(System.currentTimeMillis() -s ));
		return null;
	}

	@Override
	public Object edit(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.update("edit_customer",baseData);
		log.info("edit_customer - cost "+(System.currentTimeMillis() -s ));
		return null;
	}

	public int  count(BaseData baseData) throws SQLException{
		long s = System.currentTimeMillis();
		Object obj = sqlMapClient.queryForObject("listCustomerByPage_count",baseData);
		log.info("listCustomerByPage_count - cost "+(System.currentTimeMillis() -s ));
		return obj==null?0:(Integer)obj;
	
	}
	
	@Override
	public List list(BaseData baseData) {
		long s = System.currentTimeMillis();
		List<Customer> customers=null;
		try {
			customers = sqlMapClient.queryForList("listCustomerByPage", (Customer)baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("listCustomerByPage - cost "+(System.currentTimeMillis() -s ));
		return customers;
	}

	@Override
	public Object del(BaseData baseData) {
		long s = System.currentTimeMillis();
		try {
			 sqlMapClient.update("del_customer",baseData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("del_customer - cost "+(System.currentTimeMillis() -s ));
		return null;
	}}
