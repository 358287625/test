package com.jyd.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyd.beans.BaseData;
import com.jyd.beans.Customer;
import com.jyd.beans.OperLog;
import com.jyd.beans.UserInfor;
import com.jyd.common.Constrant;
import com.jyd.common.PingYinUtil;
import com.jyd.repository.LogRepository;
import com.jyd.vo.JsonBody;
import com.jyd.vo.PageModel;

@Service
public class SyLogService{
	@Autowired
	private LogRepository  logRepository;
	
	public LogRepository getLogRepository() {
		return logRepository;
	}

	public void setLogRepository(LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	public Object add(String operFuncName,String operIp,String operUserId,String operOrderNum) {
		JsonBody json = new JsonBody();
		OperLog saveData = new OperLog() ;
		if(null != Constrant.loginUserMap && Constrant.loginUserMap.size()>0)
		{
			UserInfor loginUser = Constrant.loginUserMap.get(operUserId);
			if(null != loginUser)
			{
				saveData.setOperShortCode(loginUser.getShortCode());
				saveData.setOperUserName(loginUser.getName());
				saveData.setOperUserNum(loginUser.getNum());
			}
		}
		
		saveData.setOperFuncName(operFuncName); //进来前就应该有设置
		saveData.setOperIp(operIp); //进来前就应该有设置
		saveData.setOperUserId(operUserId);//进来前就应该有设置
		saveData.setOperOrderNum(operOrderNum);//进来前就应该有设置
		saveData.setId(UUID.randomUUID().toString());
		try {
			logRepository.add(saveData);
			json.setCode(0);
			json.setMsg("success");
		} catch (SQLException e) {
			json.setCode(-1);
			json.setMsg("操作出错，请联系管理员");
			e.printStackTrace();
		}
		
		return json;
	}
	
	public PageModel list(BaseData baseData) {
		PageModel pager = new PageModel();
		pager.setPageSize(baseData.getPageSize());
		pager.setStart(baseData.getStart());
		
		int count=0;
		List list=null;
		try {
			count = logRepository.count(baseData);
			if(count<=0)
				return pager;
			list= logRepository.list(baseData);
			pager.setDatas(list);
			pager.setCount(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pager;
	}

	}
