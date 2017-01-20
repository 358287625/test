package com.jyd.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jyd.beans.Order;
import com.jyd.beans.OrderItem;
import com.jyd.beans.Sysconfig;
import com.jyd.beans.UserInfor;
import com.jyd.common.Constrant;
import com.jyd.common.Tools;
import com.jyd.service.OrderService;
import com.jyd.vo.JsonBody;
import com.jyd.vo.PageModel;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	private static  Logger log = LoggerFactory.getLogger(OrderController.class);  
	@Autowired
	private OrderService  orderService;
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@ResponseBody
	@RequestMapping(value = "/add")
	public JsonBody add(HttpServletRequest request,HttpServletResponse response) {
		JsonBody json = new JsonBody();
		json.setCode(-1);
		json.setMsg("操作失败");

		Order order = new Order();
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
		
		String p = request.getParameter("p");
		if(StringUtils.isEmpty(p))
		{
			json.setCode(-1);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}
		
		JSONObject jsonObject = Tools.formatStr2JSON(p);
		String cmpid = (String)jsonObject.get("cmpid");
		String cmpName = (String)jsonObject.get("cmpName");
		String orderId = (String)jsonObject.get("orderId");
		String cusComName = (String)jsonObject.get("cusComName");
		String cusName = (String)jsonObject.get("cusName");
		String cusPhone = (String)jsonObject.get("cusPhone");
		String orderNum = (String)jsonObject.get("orderNum");
//		String memo = (String)jsonObject.get("memo");
		orderNum = StringUtils.isNotEmpty(orderNum) && orderNum.indexOf("NO:")>-1? orderNum.substring(3):orderNum;
		log.info(cmpid+" -- "+ cmpName +" -- "+orderId+" -- "+ cusComName+" -- "+ cusName+" -- "+ cusPhone +" -- "+orderNum);
		JSONArray array = jsonObject.getJSONArray("items");
		List<OrderItem> items = new ArrayList<OrderItem>();
		if(array!=null && array.size()>0){
			for(int i =0;i<array.size();i++){
				JSONObject obj = (JSONObject)array.get(i);
				String id= (String)obj.get("id");
				String name= (String)obj.get("name");
				String tp= (String)obj.get("tp");
//				String unit=(String) obj.get("unit");
				String spec=(String) obj.get("spec");
				String KCP= (String)obj.get("KCP");
//				String file_size= (String)obj.get("file_size");
				String num= (String)obj.get("num");
				String price= (String)obj.get("price");
//				String discount= (String)obj.get("discount");
				String memo = (String)obj.get("memo");
				String total= (String)obj.get("total");
				log.info(id+" - "+ name +" - "+tp+" - "+ " - "+ spec+" - "+ KCP+" - "+  num +" - "+price +" - "+total);
				OrderItem orderItem = new OrderItem();
				orderItem.setId(id);
				/*if(StringUtils.isNotEmpty(discount) )
					orderItemk.setDiscount(discount);
				else*/
				orderItem.setDiscount("1");
//				orderItem.setFile_size(file_size);
				orderItem.setKCP(KCP);
//				orderItem.setUnit(unit);
				orderItem.setName(name);
				orderItem.setMemo(memo);
				if(StringUtils.isNotEmpty(num) && StringUtils.isNumeric(num))
					orderItem.setNum(Integer.valueOf(num));
				else
					orderItem.setNum(0);
				orderItem.setTp(tp);
				orderItem.setSpec(spec);
				if(StringUtils.isNotEmpty(price))
					orderItem.setPrice(Float.valueOf(Math.round(Float.valueOf(price)*100)).intValue());//分为单位
				else
					orderItem.setPrice(0);
				items.add(orderItem);
			}
		}
		
		order.setOrderNum(orderNum);
		order.setCompanyName(cmpName);
		order.setId(orderId);
		order.setCustomerName(cusName);
		order.setCustomerCompanyName(cusComName);
		order.setCustomerPhone(cusPhone);
		order.setItems(items);
		order.setLoginUserNum(user.getNum());
		
		if(StringUtils.isEmpty(orderId)){
			order.setOrderStatus(Constrant.NORMAL);
			order.setPayStatus(Constrant.NO_PAY);
			order.setUserId(user.getId());
			order.setUserShortCode(user.getShortCode());
			order.setUserNum(user.getNum());
			order.setUserName(user.getName());
		}
		try {
			json=(JsonBody) orderService.add(order,user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/get")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response,String d) {
		ModelAndView model =new ModelAndView();
		model.setViewName("order/addorder");
		final String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId)) {
			model.setViewName("timer");
			return model;
		}
		
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if (null == user) {
			model.setViewName("timer");
			return model;
		}
		Order order=new Order();
		order.setId(d);
		order.setLoginUserNum(user.getNum());
		try {
			order=orderService.getOrder(order);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		model.addObject("order", order);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	public JsonBody edit() {
		JsonBody json = new JsonBody();
		return json;
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,String a,@ModelAttribute Order queryOrder) {
		ModelAndView model =new ModelAndView();
		final String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId)) {
			model.setViewName("timer");
			return model;
		}
		
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if (null == user) {
			model.setViewName("timer");
			return model;
		}
		
		int curPage=0;
		if(null ==queryOrder)
			queryOrder = new Order();
//		queryOrder.setLoginUserNum(user.getNum());
		String start = request.getParameter("start");
		//客户名称  客户简码  产品名称   接件单号   下单时间从 收款情况  单据状态
		if (StringUtils.isEmpty(start) || !StringUtils.isNumeric(start))
			queryOrder.setStart(0);
		else{
			curPage=Integer.valueOf(start);			
			queryOrder.setStart(Integer.valueOf(start));
		}
		queryOrder.setPageSize(Constrant.MIN_PAGE_SIZE);
		PageModel pager = orderService.list(queryOrder);
		if(Constrant.ADMIN_ID.equalsIgnoreCase(user.getId()) && StringUtils.isNotEmpty(a))
			model.setViewName("list");
		else
			model.setViewName("order/order");
		model.addObject("order", queryOrder);
		model.addObject("pager", pager);
		pager.setCurPage(curPage);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/del")
	public JsonBody del(HttpServletRequest request,
			HttpServletResponse response, String t, String d) {
		JsonBody json = new JsonBody();
		if (StringUtils.isEmpty(d)) {
			json.setCode(-1);
			json.setMsg("参数错误");
			return json;
		}

		final String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId)) {
			json.setCode(-2);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if (null == user) {
			json.setCode(-2);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}

		try {
			if (StringUtils.isNoneEmpty(t)) {
				OrderItem baseData = new OrderItem();
				baseData.setId(d);
				baseData.setLoginUserNum(user.getNum());
				orderService.delItem(baseData);
			} else {
				Order baseData = new Order();
				baseData.setId(d);
				baseData.setLoginUserNum(user.getNum());
				orderService.del(baseData);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			json.setCode(-1);
			json.setMsg("操作失败，请联系管理员");
			return json;
		}
		json.setCode(0);
		json.setMsg("操作成功");
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/print")
	public JsonBody print() {
		JsonBody json = new JsonBody();
		return json;
	}
	@RequestMapping(value = "/getord")
	public ModelAndView getOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("order/print");
		String loginUserId= Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
		if(StringUtils.isEmpty(loginUserId))
		{
			mv.setViewName("timer");
			return mv;
		}
		
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if(null == user)
		{
			mv.setViewName("timer");
			return mv;
		}
		
		String  id= request.getParameter("id");
		if(StringUtils.isEmpty(id))
			return mv;
		
		Order order = new Order();
		order.setId(id);
		order.setLoginUserNum(user.getNum());
		order=orderService.getOrder(order);
		mv.addObject("id", id);
		mv.addObject("order", order);
		return mv;
	}
	
	/**
	 * 开单时，初始化一个单号
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/init")
	public ModelAndView initOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("order/addorder");
		String loginUserId= Tools.readCookie(request, Constrant.ROLE_COOKIE_FLAG);
		if(StringUtils.isEmpty(loginUserId))
		{
			mv.setViewName("timer");
			return mv;
		}
		
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if(null == user)
		{
			mv.setViewName("timer");
			return mv;
		}
		
		String dateStr =null;
		String orderTime =null;
	
		orderTime=Tools.nyrsf.format(new Date());
	
		
		List<Sysconfig> comp= orderService.getSysconfigs(Constrant.sys_config_company_num, user.getNum());
//		List<Sysconfig> dw= orderService.getSysconfigs(Constrant.sys_config_unit_num, user.getNum());
//		List<Sysconfig> lx= orderService.getSysconfigs(Constrant.sys_config_type_num, user.getNum());
//		List<Sysconfig> zk= orderService.getSysconfigs(Constrant.sys_config_discount_num, user.getNum());
//		List<Sysconfig> gg= orderService.getSysconfigs(Constrant.sys_config_size_num, user.getNum());
		
		String  id= request.getParameter("id");
		if(StringUtils.isEmpty(id))
		{
			dateStr = Tools.nyr.format(new Date());
			int num = orderService.getOrderNum();
			if((num+"").length()<3)
				dateStr = dateStr+"-"+String.format("%03d", num);
			else
				dateStr = dateStr+"-"+String.format("%0"+((num+"").length()+1)+"d", num);
			
			mv.addObject("n", "NO:"+dateStr);
			mv.addObject("u", user.getName());
			mv.addObject("d", orderTime);//分给这个用户的单号，如果一直没有用结束，就不应该再加1
		}else{
			mv.addObject("id", id);
		}
		
//		mv.addObject("lx", lx);//类型
//		mv.addObject("dw", dw);//单位
//		mv.addObject("gg",gg);//规格尺寸
//		mv.addObject("zk", zk);//折扣
		
		mv.addObject("c", comp);//公司
		return mv;
	}
	/**
	 * 导出报表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/export")
	public void export(HttpServletRequest request, HttpServletResponse response,String a,@ModelAttribute Order queryOrder) {
		final String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId)) {
			return ;
		}
		
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if (null == user) {
			return ;
		}
		
		if(null ==queryOrder)
			queryOrder = new Order();
		queryOrder.setLoginUserNum(user.getNum());
		queryOrder.setStart(0);
		queryOrder.setPageSize(Constrant.MIN_PAGE_SIZE);
		orderService.export(queryOrder,response);
		
	}
	@ResponseBody
	@RequestMapping(value = "/chg")
	public JsonBody chg(HttpServletRequest request, HttpServletResponse response,String t,String d) {
		JsonBody json = new JsonBody();
		if (StringUtils.isEmpty(d) ) {
			json.setCode(-1);
			json.setMsg("参数错误");
			return json;
		}

		final String loginUserId = Tools.readCookie(request,
				Constrant.ROLE_COOKIE_FLAG);
		if (StringUtils.isEmpty(loginUserId)) {
			json.setCode(-2);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}
		UserInfor user = Constrant.loginUserMap.get(loginUserId);
		if (null == user) {
			json.setCode(-2);
			json.setMsg("读取参数错误，请重新登录后再操作");
			return json;
		}
		Order order = new  Order();
		
		order.setId(d);
		order.setLoginUserNum(user.getNum());
		if (Constrant.HAS_PAY.equalsIgnoreCase(t)) {
			order.setOrderStatus(Constrant.NORMAL);
			order.setPayStatus(Constrant.HAS_PAY);
		} else if (Constrant.DELAY_PAY.equalsIgnoreCase(t)) {
			order.setOrderStatus(Constrant.NORMAL);
			order.setPayStatus(Constrant.DELAY_PAY);
		} else {
			order.setPayStatus(Constrant.NO_PAY);
			order.setOrderStatus(Constrant.CANCEL);
		}
		try {
			orderService.changeStatus(order, t);
		} catch (SQLException e) {
			log.error(e.getMessage());
			
			json.setCode(-1);
			json.setMsg("操作失败，请联系管理员");
			return json;
		}
		
		json.setCode(0);
		json.setMsg("操作成功");
		return json;
	} 
}
