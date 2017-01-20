/**
 * 
 */
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
import com.jyd.beans.UserPermission;
import com.jyd.common.Constrant;
import com.jyd.common.PingYinUtil;
import com.jyd.common.Tools;
import com.jyd.repository.SysconfigRepository;
import com.jyd.repository.UserPermissionRepository;
import com.jyd.repository.UserRepository;
import com.jyd.vo.JsonBody;
import com.jyd.vo.PageModel;

/**
 * @author Administrator
 *
 */
@Service
public class UserService extends BaseService{
	@Autowired
	private UserRepository  userRepository;
	@Autowired
	private UserPermissionRepository userPermissionRepository;
	
	public UserPermissionRepository getUserPermissionRepository() {
		return userPermissionRepository;
	}

	public void setUserPermissionRepository(
			UserPermissionRepository userPermissionRepository) {
		this.userPermissionRepository = userPermissionRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserInfor getUserInforById(BaseData baseData){
		return userRepository.getUserInforById(baseData);
	}
	
	
	public  JsonBody signIn(String...str ){
		JsonBody json = new JsonBody();
		String name =str[0];
		String pwd =str[1];
		UserInfor vo = new UserInfor();
		vo.setLoginName(name.trim());
		vo.setPwd(pwd.trim());
		vo.setStart(0);
		vo.setPageSize(1);
		
		UserInfor user = userRepository.getUserByName(vo);
		if(null == user || StringUtils.isEmpty(user.getId())){
			json.setCode(-1);
			json.setMsg("用户名或者密码错误");
			return json;
		}
	/*	UserInfor user = users.get(0);
		if(!user.getPwd().equalsIgnoreCase(pwd)){
			json.setCode(-1);
			json.setMsg("用户名或者密码错误");
			return json;
		}*/
		//拉权限
		List permissions=userRepository.listPermissionByUserId(user);
		if(permissions ==null || permissions.size()<=0){
			json.setCode(-1);
			json.setMsg("用户尚未分配任何权限，禁止登录");
			return json;
		}
		
		json.setCode(0);
		json.setMsg("登录成功");
		String uuid = UUID.randomUUID().toString();
		
		Constrant.loginUserMap.put(uuid, user);
		Constrant.permissionsMap.put(uuid, permissions);
		Constrant.userLoginTime.put(uuid, System.currentTimeMillis());
		
		json.setMsg(uuid);
		return json;
	}
	
	public Object  signOut(Object obj, BaseData baseData){
		return null;
	}
	 
	public List<UserPermission> getPermissionsByUserId(UserPermission userPermission){
		return userPermissionRepository.list(userPermission);
	}
	@Override
	public Object add(BaseData baseData) {
		JsonBody json = new JsonBody();
		UserInfor saveData = (UserInfor) baseData;
		if(null == Constrant.loginUserMap || Constrant.loginUserMap.size()<1)
		{
			json.setCode(-1);
			json.setMsg("请求参数错误，请重新登录再操作");
			return json;
		}
		UserInfor loginUser = Constrant.loginUserMap.get(saveData.getUuid());
		if(null == loginUser)
		{
			json.setCode(-2);
			json.setMsg("请求参数错误，请重新登录再操作");
			return json;
		}
		
		saveData.setMwpwd(saveData.getPwd());
		saveData.setPwd(Tools.getMd5(saveData.getPwd()));
		
		saveData.setShortCode(PingYinUtil.getFirstSpell(saveData.getName()));//中文自动生成简码
		if(StringUtils.isEmpty(saveData.getId())){
			
			UserInfor vo = new UserInfor();
			vo.setLoginName(saveData.getLoginName());
			UserInfor user = userRepository.getUserByName(vo);
			if(null != user && StringUtils.isNotEmpty(user.getId())){
				json.setCode(-1);
				json.setMsg("登录名称已经存在，请重新选择");
				return json;
			}
			
			saveData.setCompanyId(loginUser.getCompanyId()); //根据创建者的设置
			saveData.setCompanyName(loginUser.getCompanyName());
			saveData.setId(UUID.randomUUID().toString());
			
			saveData.setNum(loginUser.getNum()+userRepository.countAll()+Constrant.NUM_SPLIT);//创建者的编码加上是系统的第几个人
			try {
				userRepository.add(saveData);
				json.setCode(0);
				json.setMsg("success");
			} catch (Exception e) {
				json.setCode(-1);
				json.setMsg("操作失败，请联系管理员");
				e.printStackTrace();
			}
		}else{
			saveData.setUuid(loginUser.getNum()); //借做权限控制一下
			try {
				userRepository.edit(saveData);
				UserPermission p = new UserPermission();
				p.setUserId(saveData.getId());
				userPermissionRepository.del(p);
				json.setCode(0);
				json.setMsg("success");
			} catch (SQLException e) {
				json.setCode(-1);
				json.setMsg("操作失败，请联系管理员");
				e.printStackTrace();
			}
		}
		UserPermission up = new UserPermission();
		up.setUserId(saveData.getId());
			try {
				if(StringUtils.isNoneEmpty(saveData.getAddOrder()) && "1".endsWith(saveData.getAddOrder().trim())){
					up.setId(UUID.randomUUID().toString());
					up.setPermissionId(Constrant.PERMISSION_ADD_RECEIVED_ORDER);
					userPermissionRepository.add(up);
				}
				if(StringUtils.isNoneEmpty(saveData.getEditOrder()) && "1".endsWith(saveData.getEditOrder().trim())){
					up.setId(UUID.randomUUID().toString());
					up.setPermissionId(Constrant.PERMISSION_EDIT_RECEIVED_ORDER);
					userPermissionRepository.add(up);
				}
				
				if(StringUtils.isNoneEmpty(saveData.getReceived()) && "1".endsWith(saveData.getReceived().trim())){
					up.setId(UUID.randomUUID().toString());
					up.setPermissionId(Constrant.PERMISSION_REWARD);
					userPermissionRepository.add(up);
				}
				
				if(StringUtils.isNoneEmpty(saveData.getPrintOrder()) && "1".endsWith(saveData.getPrintOrder().trim())){
					up.setId(UUID.randomUUID().toString());
					up.setPermissionId(Constrant.PERMISSION_PRINTER_ORDER);
					userPermissionRepository.add(up);
				}
			} catch (Exception e) {
				e.printStackTrace();
		}
		
		return json;
	}

	@Override
	public Object edit(BaseData baseData) {
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
			count = userRepository.count(baseData);
			if(count<=0)
				return pager;
			list= userRepository.list(baseData);
			pager.setDatas(list);
			pager.setCount(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pager;
	}

	@Override
	public Object del(BaseData baseData) {
		userRepository.del(baseData);
		UserPermission up = new UserPermission();
		up.setUserId(((UserInfor)baseData).getId());
		userPermissionRepository.del(up);
		JsonBody json = new JsonBody();
		json.setCode(0);
		json.setMsg("success");
		return json;
	}}
