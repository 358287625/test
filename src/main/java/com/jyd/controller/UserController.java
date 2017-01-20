package com.jyd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jyd.beans.UserInfor;
import com.jyd.beans.UserPermission;
import com.jyd.common.Constrant;
import com.jyd.common.Tools;
import com.jyd.common.checkPermission;
import com.jyd.service.SyLogService;
import com.jyd.service.UserService;
import com.jyd.vo.JsonBody;
import com.jyd.vo.PageModel;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
@RequestMapping(value = "/user")
public class UserController {
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
	@RequestMapping(value = "/get")
	public ModelAndView getUserInfor(final HttpServletRequest request,HttpServletResponse response,String id){
		ModelAndView model = new ModelAndView();
		model.setViewName("user/addUser");
		
		final String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId)  || StringUtils.isEmpty(id)) {
			model.setViewName("timer");
			return model;
		}
		UserInfor loginUser = Constrant.loginUserMap.get(loginUserId);
		if (null == loginUser) {
			model.setViewName("timer");
			return model;
		}
		UserInfor infor = new UserInfor();
		infor.setId(id);
		infor.setNum(loginUser.getNum());
		infor =userService.getUserInforById(infor);
		if(null == infor)
			return model;
		
		UserPermission up = new UserPermission();
		up.setUserId(id);
		List<UserPermission> ups =userService.getPermissionsByUserId(up);
		
		if(null!=ups){
			int size = ups.size();
			for(int i =0;i<size;i++){
				//				打印单据
				if(Constrant.PERMISSION_PRINTER_ORDER.equalsIgnoreCase(ups.get(i ).getPermissionId())){
					infor.setPrintOrder("1");		
				}
				//				开接件单
				if(Constrant.PERMISSION_ADD_RECEIVED_ORDER.equalsIgnoreCase(ups.get(i ).getPermissionId())){
					infor.setAddOrder("1");					
				}
				//				修改接件单
				if(Constrant.PERMISSION_EDIT_RECEIVED_ORDER.equalsIgnoreCase(ups.get(i ).getPermissionId())){
					infor.setEditOrder("1");
				}
				//				 收款
				if(Constrant.PERMISSION_REWARD.equalsIgnoreCase(ups.get(i ).getPermissionId())){
					infor.setReceived("1");
				}
			}
		}
		model.addObject("userinfor", infor);
//		final String  n = infor.getName();
//		Tools.executorService.execute(new Runnable() {
//			public void run() {
//				syLogService.add("查看"+n+"用户信息", Tools.getRequestIp(request), loginUserId, "");
//			}
//		});
//		
		return model;
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView list(final HttpServletRequest request, String keyWord,
			String start) {
		int curPage=0;
		UserInfor userInfor = new UserInfor();
		userInfor.setName(keyWord);
		if (StringUtils.isEmpty(start) || !StringUtils.isNumeric(start))
			userInfor.setStart(0);
		else{
			curPage=Integer.valueOf(start);			
			userInfor.setStart(Integer.valueOf(start));
		}

		userInfor.setPageSize(Constrant.PAGE_SIZE);
		ModelAndView model = new ModelAndView();
		model.setViewName("user/user");

		final String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId)) {
			model.setViewName("timer");
			return model;
		}
		UserInfor loginUser = Constrant.loginUserMap.get(loginUserId);
		if (null == loginUser) {
			model.setViewName("timer");
			return model;
		}
		userInfor.setNum(loginUser.getNum());// 数据权限控制

		PageModel pager = userService.list(userInfor);
		model.addObject("pager", pager);
		model.addObject("keyWord", keyWord);
		pager.setCurPage(curPage);
		/*Tools.executorService.execute(new Runnable() {
			public void run() {
				syLogService.add("查看用户列表", Tools.getRequestIp(request), loginUserId, "");
			}
		});*/
		
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/add")
	public JsonBody add(final HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute UserInfor userinfor) {
		JsonBody json = new JsonBody();
		if (userinfor == null) {
			json.setCode(-1);
			json.setMsg("请求参数错误");
			return json;
		}

		if (StringUtils.isEmpty(userinfor.getLoginName())
				|| StringUtils.isEmpty(userinfor.getPwd())) {
			json.setCode(-1);
			json.setMsg("用户登录名 和 密码 必填");
			return json;
		}

		if (StringUtils.isEmpty(userinfor.getName())
				|| StringUtils.isEmpty(userinfor.getPhone())) {
			json.setCode(-1);
			json.setMsg("用户姓名联系方式必填");
			return json;
		}

		if (StringUtils.isEmpty(userinfor.getAddOrder())
				|| StringUtils.isEmpty(userinfor.getEditOrder())
				|| StringUtils.isEmpty(userinfor.getReceived())
				|| StringUtils.isEmpty(userinfor.getPrintOrder())) {
			json.setCode(-1);
			json.setMsg("新建用户至少得拥有一个权限");
			return json;
		}

		final String uuid = Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);

		if (StringUtils.isEmpty(uuid)) {
			json.setCode(-2);
			json.setMsg("请求参数错误，请重新登录再操作");
			return json;
		}

		userinfor.setUuid(uuid);
		final String editId = userinfor.getId();
		json = (JsonBody) userService.add(userinfor);
		final String n = userinfor.getName();
		Tools.executorService.execute(new Runnable() {
			public void run() {
				if(StringUtils.isEmpty(editId))
					syLogService.add("添加用户"+n, Tools.getRequestIp(request), uuid, "");
				else
					syLogService.add("编辑用户"+n, Tools.getRequestIp(request), uuid, editId);
			}
		});
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/del")
	public JsonBody del(final HttpServletRequest request,final String d) {
		JsonBody json = new JsonBody();

		final String uuid = Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(d)) {
			json.setCode(-2);
			json.setMsg("请求参数错误，请重新登录再操作");
			return json;
		}

		if (Constrant.loginUserMap == null || Constrant.loginUserMap.size() < 1) {
			json.setCode(-2);
			json.setMsg("请求参数错误，请重新登录再操作");
			return json;
		}

		UserInfor loginUser = Constrant.loginUserMap.get(uuid);
		if (null == loginUser) {
			json.setCode(-1);
			json.setMsg("请求参数错误，请重新登录再操作");
			return json;
		}

		UserInfor delData = new UserInfor();
		delData.setId(d);
		delData.setNum(loginUser.getNum());

		json = (JsonBody) userService.del(delData);
		
		Tools.executorService.execute(new Runnable() {
			public void run() {
				syLogService.add("删除用户", Tools.getRequestIp(request), uuid, d);
			}
		});
		
		return json;
	}
}



