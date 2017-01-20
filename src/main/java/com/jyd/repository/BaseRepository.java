package com.jyd.repository;

import java.util.List;

import com.jyd.beans.BaseData;
import com.jyd.vo.PageModel;

public abstract class BaseRepository {
	public abstract Object add(BaseData baseData)throws Exception;

	public abstract Object edit(BaseData baseData)throws Exception;

	public abstract List list(BaseData baseData)throws Exception;

	public abstract Object del(BaseData baseData)throws Exception;
	
}
