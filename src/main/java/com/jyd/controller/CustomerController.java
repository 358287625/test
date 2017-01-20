package com.jyd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jyd.beans.Customer;
import com.jyd.beans.UserInfor;
import com.jyd.common.Constrant;
import com.jyd.common.Tools;
import com.jyd.service.CustomerService;
import com.jyd.service.SyLogService;
import com.jyd.vo.JsonBody;
import com.jyd.vo.PageModel;

@Controller
@RequestMapping(value="/cus")
public class CustomerController {
	@Autowired
	private CustomerService  customerService;
	
	@Autowired
	private SyLogService syLogService;
	public SyLogService getSyLogService() {
		return syLogService;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	@RequestMapping(value = "/get")
	public ModelAndView getCus(final HttpServletRequest request,HttpServletResponse response,final  String id){
		ModelAndView model = new ModelAndView();
		model.setViewName("customer/addCustomer");
		
		final String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId) || StringUtils.isEmpty(id)) {
			model.setViewName("timer");
			return model;
		}
		UserInfor loginUser = Constrant.loginUserMap.get(loginUserId);
		if (null == loginUser) {
			model.setViewName("timer");
			return model;
		}
		Customer infor = new Customer();
		infor.setId(id);
		infor.setNum(loginUser.getNum());
		infor =customerService.getCustomerById(infor);
		model.addObject("customer", infor);
//		if(infor!=null){
//			final String n = infor.getName();
//			Tools.executorService.execute(new Runnable() {
//				public void run() {
//					syLogService.add("查看"+n+"客户信息", Tools.getRequestIp(request), loginUserId, id);
//				}
//			});
//		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add")
	public JsonBody add(final HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute Customer customer) {
		JsonBody json = new JsonBody();
		if (customer == null) {
			json.setCode(-1);
			json.setMsg("请求参数错误");
			return json;
		}

		if (StringUtils.isEmpty(customer.getName())
				|| StringUtils.isEmpty(customer.getPhone())) {
			json.setCode(-1);
			json.setMsg("客户姓名和联系电话必填");
			return json;
		}

		final String uuid = Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);

		if (StringUtils.isEmpty(uuid)) {
			json.setCode(-2);
			json.setMsg("请求参数错误，请重新登录再操作");
			return json;
		}
		
		UserInfor loginUser = Constrant.loginUserMap.get(uuid);
		if(null == loginUser)
		{
			json.setCode(-2);
			json.setMsg("请求参数错误，请重新登录再操作");
			return json;
		}
		
		final String n=customer.getName();
		final String editId = customer.getId();
		customer.setUuid(uuid);
		json = (JsonBody) customerService.add(customer,loginUser);
		
		Tools.executorService.execute(new Runnable() {
			public void run() {
				if(StringUtils.isEmpty(editId))
					syLogService.add("添加客户"+n, Tools.getRequestIp(request), uuid, "");
				else
					syLogService.add("编辑客户"+n, Tools.getRequestIp(request), uuid, editId);
			}
		});
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	public JsonBody edit() {
		JsonBody json = new JsonBody();
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/all")
	public JsonBody all(HttpServletRequest request) {
		
		String name =request.getParameter("name");
		String val =request.getParameter("val");
		JsonBody json = new JsonBody();
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(val))
		{
			json.setCode(-1);
			json.setMsg("请求参数错误");
			return json;
		}
		
		Customer infor = new Customer();
		if(name.equalsIgnoreCase("customerCompanyName"))
			infor.setCompanyName(val);
		else if(name.equalsIgnoreCase("customerName"))
			infor.setName(val);
		else if(name.equalsIgnoreCase("customerPhone"))
			infor.setPhone(val);
		else{
			json.setCode(-1);
			json.setMsg("请求参数错误");
			return json;
		}
		
		List<Customer> list =customerService.getLikeCustomerByCustomer(infor);
		json.setCode(0);
		json.setObj(list);
		return json;
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView list(final HttpServletRequest request, String keyWord,
			String start) {
		int curPage=0;
		Customer customer = new Customer();
		customer.setName(keyWord);
		if (StringUtils.isEmpty(start) || !StringUtils.isNumeric(start))
			customer.setStart(0);
		else
		{
			curPage=Integer.valueOf(start);	
			customer.setStart(Integer.valueOf(start));
		}

		customer.setPageSize(Constrant.PAGE_SIZE);
		ModelAndView model = new ModelAndView();
		model.setViewName("customer/customer");

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
		customer.setNum(loginUser.getNum());// 数据权限控制
		PageModel pager = customerService.list(customer);
		model.addObject("pager", pager);
		model.addObject("keyWord", keyWord);
		pager.setCount(curPage);
		return model;
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

		Customer delData = new Customer();
		delData.setId(d);
		delData.setNum(loginUser.getNum());

		json = (JsonBody) customerService.del(delData);
		
		Tools.executorService.execute(new Runnable() {
			public void run() {
				syLogService.add("删除客户", Tools.getRequestIp(request), uuid, d);
			}
		});
		return json;
	}
}
