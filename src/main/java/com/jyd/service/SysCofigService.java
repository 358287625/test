package com.jyd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyd.beans.BaseData;
import com.jyd.beans.Sysconfig;
import com.jyd.repository.LogRepository;
import com.jyd.repository.SysconfigRepository;
import com.jyd.vo.PageModel;

@Service
public class SysCofigService extends BaseService{
	@Autowired
	private SysconfigRepository  sysconfigRepository;
	
	public SysconfigRepository getSysconfigRepository() {
		return sysconfigRepository;
	}

	public void setSysconfigRepository(SysconfigRepository sysconfigRepository) {
		this.sysconfigRepository = sysconfigRepository;
	}
	@Override
	public Object add(BaseData baseData) {
		sysconfigRepository.add(baseData);
		return null;
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
		
		int count = sysconfigRepository.count(baseData);
		if(count<=0)
			return pager;

		List list= sysconfigRepository.list(baseData);
		pager.setDatas(list);
		pager.setCount(count);
		return pager;
	}
	/**
	 * 关联查询类型
	 * @param baseData
	 * @return
	 */
	public List associateType(Sysconfig cfg) {
		return  sysconfigRepository.associateType(cfg);
	}
	
	@Override
	public Object del(BaseData baseData) {
		sysconfigRepository.del(baseData);
		return null;
	}}
