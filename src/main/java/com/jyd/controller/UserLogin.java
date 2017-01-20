/**
 * 
 */
package com.jyd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyd.common.Constrant;
import com.jyd.common.Tools;
import com.jyd.service.SyLogService;
import com.jyd.service.UserService;
import com.jyd.vo.JsonBody;

/**
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = "/login")
public class UserLogin {
	@Autowired
	private UserService userService;
	@Autowired
	private SyLogService syLogService;
	public SyLogService getSyLogService() {
		return syLogService;
	}
	
	public void setSyLogService(SyLogService syLogService) {
		this.syLogService = syLogService;
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/index")
	public String index(){
		return "index";
	}
	/**
	 * 需要根据不同角色，跳转到不同界面
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/in")
	public JsonBody in(final HttpServletRequest request, HttpServletResponse response ,String n,String p) throws IOException {
		JsonBody json = new JsonBody();
		if(StringUtils.isEmpty(n) || StringUtils.isEmpty(p))
		{
			json.setCode(-1);
			json.setMsg("请输入用户名或者密码");
			return json;
		}
		
		json = (JsonBody)userService.signIn(n,p);
		if(json.getCode() == Constrant.SUCCESS){
			String uuid = Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
			
			if(StringUtils.isNotEmpty(uuid)){//把上次的清除
				if(Constrant.permissionsMap != null && Constrant.permissionsMap.size()>0)
					Constrant.permissionsMap.remove(uuid);//清理不及时可能会不断增长
				if(Constrant.loginUserMap != null && Constrant.loginUserMap.size()>0)
					Constrant.loginUserMap.remove(uuid);
				if(Constrant.userLoginTime != null && Constrant.userLoginTime.size()>0)
					Constrant.userLoginTime.remove(uuid);
			}
			
			//登录成功。看看是什么角色登录
			Tools.writeCookie(response, Constrant.ROLE_COOKIE_FLAG, json.getMsg(),Constrant.COOKIE_EXPIRES_TIME);//role的md5值
			final String loginUuid =  json.getMsg();
			Tools.writeCookie(response, Constrant.P_COOKIE_FLAG,StringUtils.join(Constrant.permissionsMap.get(loginUuid), ","),Constrant.COOKIE_EXPIRES_TIME);//role的md5值
			Tools.executorService.execute(new Runnable() {
				public void run() {
					syLogService.add("登录系统", Tools.getRequestIp(request), loginUuid, "");
				}
			});
		} 
		return json;
	}

	@RequestMapping(value = "/out")
	public String out(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
		if(StringUtils.isNotEmpty(uuid))//不然反复刷这个链接，就会有多条数据
			syLogService.add("退出登录", Tools.getRequestIp(request), uuid, "");
		else{
			response.sendRedirect("index.do");
			return null;			
		}
		
		Tools.writeCookie(response, Constrant.ROLE_COOKIE_FLAG, "",0);//role的md5值
		if(Constrant.permissionsMap != null && Constrant.permissionsMap.size()>0)
			Constrant.permissionsMap.remove(uuid);//清理不及时可能会不断增长
		if(Constrant.loginUserMap != null && Constrant.loginUserMap.size()>0)
			Constrant.loginUserMap.remove(uuid);
		if(Constrant.userLoginTime != null && Constrant.userLoginTime.size()>0)
			Constrant.userLoginTime.remove(uuid);
		response.sendRedirect("index.do");
		return null;
	}
}
