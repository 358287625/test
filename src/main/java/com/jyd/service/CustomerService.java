package com.jyd.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyd.beans.BaseData;
import com.jyd.beans.Customer;
import com.jyd.beans.UserInfor;
import com.jyd.common.Constrant;
import com.jyd.common.PingYinUtil;
import com.jyd.repository.CustomerRepository;
import com.jyd.vo.JsonBody;
import com.jyd.vo.PageModel;

@Service
public class CustomerService extends BaseService{
	@Autowired
	private CustomerRepository  customerRepository;
	
	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	public Customer getCustomerById(BaseData baseData){
		return customerRepository.getCustomerById(baseData);
	}
	/**
	 * 模糊查询客户
	 * @param baseData
	 * @return
	 */
	public List<Customer> getLikeCustomerByCustomer(Customer baseData){
		return customerRepository.getLikeCustomerByCustomer(baseData);
	}
	
	public Object add(BaseData baseData,UserInfor loginUser) {
		JsonBody json = new JsonBody();
		Customer saveData = (Customer) baseData;
	
		//先差有没有同名客户
		if(StringUtils.isNotEmpty(saveData.getCompanyName())){
			Customer cus = new Customer();
			cus.setCompanyName(saveData.getCompanyName());
			List<Customer> list = customerRepository.getCustomerByCustomer(cus);
			if(list!=null && list.size()>0){
				saveData.setId(list.get(0).getId());//如果存在就直接修改之前的
			}
		}
		if(StringUtils.isNotEmpty(saveData.getName()))
			saveData.setShortCode(PingYinUtil.getFirstSpell(saveData.getName()));//中文自动生成简码
		
		if(StringUtils.isEmpty(saveData.getId())){
			saveData.setId(UUID.randomUUID().toString());
			saveData.setNum(loginUser.getNum()+customerRepository.countAll()+Constrant.NUM_SPLIT);//创建者的编码加上是系统的第几个人
			saveData.setCreateUserId(loginUser.getId());
			saveData.setCreateUserName(loginUser.getName());
			saveData.setCreateUserNum(loginUser.getNum());
			saveData.setCreateUserShortCode(loginUser.getShortCode());
			try {
				customerRepository.add(saveData);
				json.setCode(0);
				json.setMsg("success");
			} catch (SQLException e) {
				json.setCode(-1);
				json.setMsg("操作出错，请联系管理员");
				e.printStackTrace();
			}
		}else{
			saveData.setUuid(loginUser.getNum()); //借做权限控制一下
			try {
				customerRepository.edit(saveData);
				json.setCode(0);
				json.setMsg("success");
			} catch (SQLException e) {
				json.setCode(-1);
				json.setMsg("操作出错，请联系管理员");
				e.printStackTrace();
			}
		}
		return json;
	}

	@Override
	public Object edit(BaseData baseData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel list(BaseData baseData) {
		PageModel pager = new PageModel();
		pager.setPageSize(baseData.getPageSize());
		pager.setStart(baseData.getStart());
		pager.setCurPage(baseData.getStart());
		
		int count=0;
		List list=null;
		try {
			count = customerRepository.count(baseData);
			if(count<=0)
				return pager;
			list= customerRepository.list(baseData);
			pager.setDatas(list);
			pager.setCount(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pager;
	}

	@Override
	public Object del(BaseData baseData) {
		customerRepository.del(baseData);
		
		JsonBody json = new JsonBody();
		json.setCode(0);
		json.setMsg("success");
		return json;
	}

	@Override
	public Object add(BaseData baseData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}}
