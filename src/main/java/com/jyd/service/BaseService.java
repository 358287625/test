package com.jyd.service;

import java.sql.SQLException;

import com.jyd.beans.BaseData;
import com.jyd.vo.PageModel;

public abstract class BaseService {
	public abstract Object add( BaseData baseData)throws Exception;

	public abstract Object edit( BaseData baseData)throws Exception;

	public abstract PageModel list(BaseData baseData);

	public abstract Object del(BaseData baseData) throws SQLException ;
}
