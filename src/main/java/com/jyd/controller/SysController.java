package com.jyd.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jyd.beans.Sysconfig;
import com.jyd.beans.UserInfor;
import com.jyd.common.Constrant;
import com.jyd.common.Tools;
import com.jyd.common.checkPermission;
import com.jyd.service.SyLogService;
import com.jyd.service.SysCofigService;
import com.jyd.vo.JsonBody;
import com.jyd.vo.PageModel;

@Controller
@RequestMapping(value="/sys")
public class SysController {
	@Autowired
	private SysCofigService  sysCofigService;
	@Autowired
	private SyLogService syLogService;
	public SyLogService getSyLogService() {
		return syLogService;
	}
	
	public void setSyLogService(SyLogService syLogService) {
		this.syLogService = syLogService;
	}
	public SysCofigService getSysCofigService() {
		return sysCofigService;
	}

	public void setSysCofigService(SysCofigService sysCofigService) {
		this.sysCofigService = sysCofigService;
	}

	@ResponseBody
	@RequestMapping(value = "/add")
	public JsonBody add(final HttpServletRequest request, HttpServletResponse response) {
		final String sysconfig_name = request.getParameter("a");
		String sysconfig_num = request.getParameter("n");
		
		JsonBody json = new JsonBody();
		if(StringUtils.isEmpty(sysconfig_name) || StringUtils.isEmpty(sysconfig_num))
		{
			json.setCode(-1);
			json.setMsg("系统配置参数名称不能为空");
			return json;
		}
		final	String loginUserId= Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
		if(StringUtils.isEmpty(loginUserId))
		{
			json.setCode(-2);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if(null == user)
		{
			json.setCode(-2);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}
		
		Sysconfig config=new Sysconfig();
		config.setName(sysconfig_name);
		config.setNum(sysconfig_num);
		config.setId(UUID.randomUUID().toString());
		
		config.setCreateUserId(loginUserId);
		config.setCreateUserName(user.getName());
		config.setCreateUserNum(user.getNum());
		config.setCreateUserShortCode(user.getShortCode());
		
		sysCofigService.add(config);
		
	/*	Tools.executorService.execute(new Runnable() {
			public void run() {
				syLogService.add("添加系统配置"+sysconfig_name, Tools.getRequestIp(request), loginUserId, "");
			}
		});*/
		return json;
	}
	
	@RequestMapping(value = "/all")
	@ResponseBody
	public JsonBody all(final HttpServletRequest request) throws NoSuchMethodException, SecurityException {
		int curPage=0;
		
		String num = request.getParameter("num");
		String val = request.getParameter("val");
		
		JsonBody json = new JsonBody();
		if(StringUtils.isEmpty(num) ||StringUtils.isEmpty(val) )
		{
			json.setCode(-1);
			json.setMsg("请求参数错误");
			return json;	
		}
		
		Sysconfig cfg = new Sysconfig();
		cfg.setName(val);
		cfg.setNum(num);
		
		List list  = sysCofigService.associateType(cfg);
		json.setCode(0);
		json.setObj(list);
		return json;
	}
	@RequestMapping(value = "/list")
	public ModelAndView list(final HttpServletRequest request, String keyWord,String start) throws NoSuchMethodException, SecurityException {
		int curPage=0;
	/*	StackTraceElement[] stack = new Throwable().getStackTrace();
	   	System.out.println(stack[0].getMethodName());
        */
		
		Sysconfig cfg = new Sysconfig();
		cfg.setName(keyWord);
		if(StringUtils.isEmpty(start) ||!StringUtils.isNumeric(start) )
			cfg.setStart(0);
		else
		{
			curPage=Integer.valueOf(start);		
			cfg.setStart(Integer.valueOf(start));
		}
		
		cfg.setPageSize(Constrant.PAGE_SIZE);
		ModelAndView model = new ModelAndView();
		model.setViewName("system/system");
		
		final String loginUserId= Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
		if(StringUtils.isEmpty(loginUserId))
		{
			model.setViewName("timer");
			return model;
		}
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if(null == user)
		{
			model.setViewName("timer");
			return model;
		}
		cfg.setCreateUserNum(user.getNum());//数据权限控制
		
		PageModel pager = sysCofigService.list(cfg);
		model.addObject("pager", pager);
		model.addObject("keyWord", keyWord);
		pager.setCurPage(curPage);
		/*Tools.executorService.execute(new Runnable() {
			public void run() {
				syLogService.add("查看系统配置", Tools.getRequestIp(request), loginUserId, "");
			}
		});*/
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/del")
	public JsonBody del(final HttpServletRequest request ,final String d) {
		JsonBody json = new JsonBody();
		if(StringUtils.isEmpty(d)){
			json.setCode(-1);
			json.setMsg("参数不能为空");
		}
		
		final String loginUserId= Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
		if(StringUtils.isEmpty(loginUserId))
		{
			json.setCode(-2);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if(null == user)
		{
			json.setCode(-2);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}
		
		Sysconfig cfg = new Sysconfig();
		cfg.setId(d);
		cfg.setCreateUserNum(user.getNum());
		json.setCode(0);
		sysCofigService.del(cfg);
		
		Tools.executorService.execute(new Runnable() {
			public void run() {
				syLogService.add("删除系统配置", Tools.getRequestIp(request), loginUserId, d);
			}
		});
		return json;
	}
}
