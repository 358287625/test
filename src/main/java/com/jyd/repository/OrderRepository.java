package com.jyd.repository;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jyd.beans.BaseData;
import com.jyd.beans.Order;
import com.jyd.beans.OrderItem;
import com.jyd.vo.PageModel;

@Repository 
public class OrderRepository extends BaseRepository{
	private static  Logger log = LoggerFactory.getLogger(OrderRepository.class);
	@Autowired
  	private SqlMapClient sqlMapClient;
	  
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public int getOrderNum() {
		long s = System.currentTimeMillis();
		Object obj =null;
		try {
			obj = sqlMapClient.queryForObject("getOrderNum");
			if(obj == null){
				insertOrderNum();
				obj=1;
			}else{
				incOrderNum();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getOrderNum_cost_"+( System.currentTimeMillis()-s));
		return (Integer)obj;
	}
	
	public void insertOrderNum() throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.insert("insertOrderNum");
		log.info("insertOrderNum_cost_"+( System.currentTimeMillis()-s));
	}
	public void incOrderNum() throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.update("incOrderNum");
		log.info("incOrderNum_cost_"+( System.currentTimeMillis()-s));
	}
	
	
	public Object addItem(OrderItem item) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.insert("addOrderItem",item);
		log.info("addOrderItem_cost_"+( System.currentTimeMillis()-s));
		return null;
	}

	public Object updateItem(OrderItem item) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.update("updateOrderItem",item);
		log.info("updateOrderItem_cost_"+( System.currentTimeMillis()-s));
		return null;
	}
	
	@Override
	public Object add(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.insert("addOrder",baseData);
		log.info("addOrder_cost_"+( System.currentTimeMillis()-s));
		return null;
	}

	@Override
	public Object edit(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.update("updateOrder",baseData);
		log.info("updateOrder_cost_"+( System.currentTimeMillis()-s));
		return null;
	}

	public int count(BaseData baseData) throws SQLException{
		long s = System.currentTimeMillis();
		Object obj =sqlMapClient.queryForObject("listOrderByPage_count", baseData);
		log.info("listOrderByPage_count_cost_"+( System.currentTimeMillis()-s));
		return obj==null?0:(Integer)obj;
	}
	
	@Override
	public List list(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		List obj =sqlMapClient.queryForList("listOrderByPage", baseData);
		log.info("listOrderByPage_cost_"+( System.currentTimeMillis()-s));
		return obj;
	}
	
	public List exportOrder(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		List obj =sqlMapClient.queryForList("exportOrder", baseData);
		log.info("exportOrder_cost_"+( System.currentTimeMillis()-s));
		return obj;
	}
	
	
	@Override
	public Object del(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.delete("deleteOrder",baseData);
		log.info("deleteOrder_cost_"+( System.currentTimeMillis()-s));
		return null;
	}
	public Object delItemByOrderNum(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.delete("delItemByOrderNum",baseData);
		log.info("delItemByOrderNum_cost_"+( System.currentTimeMillis()-s));
		return null;
	}
	
	public Object delOrderItem(BaseData baseData) throws SQLException {
		long s = System.currentTimeMillis();
		sqlMapClient.delete("deleteOrderItem",baseData);
		log.info("deleteOrderItem_cost_"+( System.currentTimeMillis()-s));
		return null;
	}
	
	public void changePayStaus(BaseData baseData) throws SQLException{
		long s = System.currentTimeMillis();
		sqlMapClient.update("updateOrderPayStatus",baseData);
		log.info("updateOrderPayStatus_cost_"+( System.currentTimeMillis()-s));
	}
	
	public void changeStaus(BaseData baseData) throws SQLException{
		long s = System.currentTimeMillis();
		sqlMapClient.update("updateOrderStatus",baseData);
		log.info("updateOrderStatus_cost_"+( System.currentTimeMillis()-s));
	}
	
	
	public Order getOrderById(Order order) throws SQLException{
		long s = System.currentTimeMillis();
		Object obj = sqlMapClient.queryForObject("getOrderById",order);
		log.info("getOrderById_cost_"+( System.currentTimeMillis()-s));
		return obj==null?null:(Order)obj;
	}
	
	public List<OrderItem> getOrderItemsByNum(Order order) throws SQLException{
		long s = System.currentTimeMillis();
		List list = sqlMapClient.queryForList("getOrderItemByNum",order);
		log.info("insertOrderNum_cost_"+( System.currentTimeMillis()-s));
		return list;
	}
	
}
