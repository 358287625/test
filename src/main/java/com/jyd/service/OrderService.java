package com.jyd.service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyd.beans.BaseData;
import com.jyd.beans.Customer;
import com.jyd.beans.Order;
import com.jyd.beans.OrderItem;
import com.jyd.beans.Sysconfig;
import com.jyd.beans.UserInfor;
import com.jyd.common.Constrant;
import com.jyd.common.Tools;
import com.jyd.repository.OrderRepository;
import com.jyd.repository.SysconfigRepository;
import com.jyd.vo.JsonBody;
import com.jyd.vo.PageModel;

@Service
public class OrderService extends BaseService{
	private static  Logger log = LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private OrderRepository  orderRepository;
	@Autowired
	private SysconfigRepository  sysconfigRepository;
	@Autowired
	private CustomerService customerService;
	public SysconfigRepository getSysconfigRepository() {
		return sysconfigRepository;
	}

	public void setSysconfigRepository(SysconfigRepository sysconfigRepository) {
		this.sysconfigRepository = sysconfigRepository;
	}
	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Order getOrder(Order order) throws SQLException{
		Order reOrder=null;
		reOrder=orderRepository.getOrderById(order);
		if(reOrder==null)
			return reOrder;
		
		 order.setOrderNum(reOrder.getOrderNum());
		 List list =orderRepository.getOrderItemsByNum(order);
		 reOrder.setItems(list);
		
		return reOrder;
	}
	public List<Sysconfig> getSysconfigs(String num,String loginNum){
		Sysconfig baseData = new Sysconfig();
		baseData.setNum(num);
		baseData.setCreateUserNum(loginNum);
		return sysconfigRepository.getSysconfigs(baseData);
	}
	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	public int getOrderNum(){
		return orderRepository.getOrderNum();
	}
	public Object add(BaseData baseData,final UserInfor loginUser) throws Exception {
		JsonBody json = new JsonBody();
		Order order =(Order)baseData;
		if(order.getItems()==null || order.getItems().size()<=0)
		{
			json.setCode(-1);
			json.setMsg("单据至少得有一个明细项");
		}
		if(StringUtils.isEmpty(order.getId()) || Constrant.NORMAL.equals(order.getId())){
			order.setId(UUID.randomUUID().toString());
			int totalFee =0;//订单总金额
			//添加明细
			for(int i=0;i<order.getItems().size();i++){
				OrderItem item = order.getItems().get(i);
				item.setId(UUID.randomUUID().toString());
				item.setCode(order.getOrderNum());//订单编号
				item.setTotal(Math.round(item.getPrice()* item.getNum()*Float.valueOf(item.getDiscount())));//精确到分，不足的四舍五入
				item.setTotal(Integer.valueOf(""+Math.round(item.getTotal()/10.0)*10));//需求要求精确到角，不到分.分采用四舍五入
				totalFee= totalFee+item.getTotal();
				orderRepository.addItem(item);
				//客户需要自动加入客户接口
			}
			order.setTotal(totalFee);
			orderRepository.add(order);//添加主单
		}else{
			Order or = orderRepository.getOrderById(order);
			if(or==null){
				json.setCode(-1);
				json.setMsg("单据不存在");
				return json;
			}
			if(Constrant.HAS_PAY.equalsIgnoreCase(or.getPayStatus()))//作废或者已经结清的不能再编辑
			{
				json.setCode(-1);
				json.setMsg("已经结清的单据不能再编辑");
				return json;
			}
			
			if( Constrant.CANCEL.equalsIgnoreCase(or.getOrderStatus()))//作废或者已经结清的不能再编辑
			{
				json.setCode(-1);
				json.setMsg("已经作废的单据不能再编辑");
				return json;
			}
			
			edit(order); 
		}
		//自动增加客户
		final String CustomerCompanyName = order.getCustomerCompanyName();
		final String customerName = order.getCustomerName();
		final String customerPhone = order.getCustomerPhone();
		final Order or = order;
		Tools.executorService.execute(new Runnable() {
			
			public void run() {
				Customer customer = new Customer();
				customer.setCompanyName(CustomerCompanyName);
				customer.setName(customerName);
				customer.setPhone(customerPhone);
				customerService.add(customer,loginUser);
				
				Sysconfig cofig = new Sysconfig();
				cofig.setCreateUserId(loginUser.getId());
				cofig.setCreateUserName(loginUser.getName());
				cofig.setCreateUserNum(loginUser.getNum());
				cofig.setCreateUserShortCode(loginUser.getShortCode());
				
				for(int i=0;i<or.getItems().size();i++){
					OrderItem item = or.getItems().get(i);
					
					//创建开单的类型。
					cofig.setName(StringUtils.trim(item.getTp()));
					cofig.setNum(Constrant.sys_config_type_num);
					Sysconfig cof = sysconfigRepository.getSysconfigsByNameAndnum(cofig);
					if(null == cof){
						cofig.setId(UUID.randomUUID().toString());
						sysconfigRepository.add(cofig);
					}
					
					//创建尺寸规格
					cofig.setName(item.getSpec());
					cofig.setNum(Constrant.sys_config_size_num);
					cof = sysconfigRepository.getSysconfigsByNameAndnum(cofig);
					if(null == cof){
						cofig.setId(UUID.randomUUID().toString());
						sysconfigRepository.add(cofig);
					}
					
					//创建cpk
					cofig.setName(item.getKCP());
					cofig.setNum(Constrant.sys_config_kcp_num);
					cof = sysconfigRepository.getSysconfigsByNameAndnum(cofig);
					if(null == cof){
						cofig.setId(UUID.randomUUID().toString());
						sysconfigRepository.add(cofig);
					}
				}
			}
		});
		json.setCode(0);
		json.setMsg("保存成功");
		return json;
	}

	@Override
	public Object edit(BaseData baseData) throws Exception {
		JsonBody json = new JsonBody();
		Order order =(Order)baseData;
		

		int totalFee =0;//订单总金额
		//添加明细
		for(int i=0;i<order.getItems().size();i++){
			OrderItem item = order.getItems().get(i);
			item.setTotal(Math.round(item.getPrice()* item.getNum()*Float.valueOf(item.getDiscount())));//精确到分，不足的四舍五入
			item.setTotal(Integer.valueOf(""+Math.round(item.getTotal()/10.0)*10));//需求要求精确到角，不到分.分采用四舍五入
			totalFee= totalFee+item.getTotal();
			if(StringUtils.isEmpty(item.getId()) || "0".equalsIgnoreCase(item.getId())){
				item.setId(UUID.randomUUID().toString());
				item.setCode(order.getOrderNum());//订单编号
				orderRepository.addItem(item);
			}else{
				item.setLoginUserNum(order.getLoginUserNum());
				orderRepository.updateItem(item);
			}
			//客户需要自动加入客户接口
		}
		order.setTotal(totalFee);
		orderRepository.edit(order);//添加主单
		return json;
	}

	public void export(Order order,HttpServletResponse response) {

		PageModel pager = new PageModel();
		pager.setPageSize(order.getPageSize());
		pager.setStart(order.getStart());
		pager.setCurPage(order.getStart());
		String fileName = Tools.nyrsfm.format(new Date())+".xls";
		
		int count=0;
		List list=null;
		try {
			if(StringUtils.isNotEmpty(order.getStartTime()))
				order.setStartTime(order.getStartTime()+Constrant.DAY_START_TIME);
			
			if(StringUtils.isNotEmpty(order.getEndTime()))
				order.setEndTime(order.getEndTime()+Constrant.DAY_END_TIME);
			
			list= orderRepository.exportOrder(order);
			for(int i=0;i<list.size();i++){
				Order or = (Order)list.get(i);
				or.setItems(orderRepository.getOrderItemsByNum(or));
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");

			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			HSSFSheet sheet = wb.createSheet("jyd汇总表");
			
			sheet.setColumnWidth(0, 16*256);
			sheet.setColumnWidth(1, 16*256);
			sheet.setColumnWidth(2, 16*256);
			sheet.setColumnWidth(3, 16*256);
			sheet.setColumnWidth(4, 16*256);
			sheet.setColumnWidth(5, 16*256);
			sheet.setColumnWidth(6, 16*256);
			sheet.setColumnWidth(7, 16*256);
			sheet.setColumnWidth(8, 64*256);
			sheet.setColumnWidth(9, 64*256);
			
			
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeight((short)(200*3));
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFont(font);
			
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("客户名称");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("联系电话");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("接件单号");
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("产品名称");
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue("数量 ");
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("单价 ");
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue("折扣");
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue("小计(元)");
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue("收款情况");
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue("订单状态");

			
			HSSFFont font1 = wb.createFont();
			font1.setFontName("宋体");
			font1.setFontHeightInPoints((short) 12);
			HSSFCellStyle style1 = wb.createCellStyle();
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
			style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style1.setFont(font1);
			int startMergeLine=0;
			int endMergeLine=0;
			int allCount=0;//总计
			for (int i = 0; i < list.size(); i++)
			{
				startMergeLine++;
				Order xslOrder =(Order)list.get(i);
				allCount =allCount+xslOrder.getTotal();
				for(int j=0;j<xslOrder.getItems().size();j++){
					endMergeLine++;
					HSSFRow row1 = sheet.createRow(endMergeLine);
					row1.setHeight((short)(200*3));
				
					HSSFCell cell1 =row1.createCell(0);
					cell1.setCellStyle(style1);
					cell1.setCellValue(xslOrder.getCustomerName());//客户名称
					
					cell1=row1.createCell(1);
					cell1.setCellStyle(style1);
					cell1.setCellValue(xslOrder.getCustomerPhone());//联系电话
					cell1=row1.createCell(2);
					cell1.setCellStyle(style1);
					cell1.setCellValue(xslOrder.getOrderNum());//接件单号
					//-------------------------------------------
					
					cell1=row1.createCell(3);
					cell1.setCellStyle(style1);
					cell1.setCellValue(xslOrder.getItems().get(j).getName());//产品名称
					cell1=row1.createCell(4);
					cell1.setCellStyle(style1);
					cell1.setCellValue(xslOrder.getItems().get(j).getNum());//数量
					cell1=row1.createCell(5);
					cell1.setCellStyle(style1);
					cell1.setCellValue(Tools.CURRENCY_FORMAT.format(new BigDecimal(xslOrder.getItems().get(j).getPrice()/100.0)));//单价
					cell1=row1.createCell(6);
					cell1.setCellStyle(style1);
					cell1.setCellValue(xslOrder.getItems().get(j).getDiscount());//折扣
					cell1=row1.createCell(7);
					cell1.setCellStyle(style1);
					cell1.setCellValue(Tools.CURRENCY_FORMAT.format(new BigDecimal(xslOrder.getItems().get(j).getTotal()/100.0)));//小计(元)
				
					
					//-------------------------------------------
					if(Constrant.NO_PAY.equalsIgnoreCase(xslOrder.getPayStatus()))
					{
						cell1=row1.createCell(8);
						cell1.setCellStyle(style1);
						cell1.setCellValue("未付款");//收款情况
					}
					
					else 
					{
						cell1=row1.createCell(8);
						cell1.setCellStyle(style1);
						cell1.setCellValue(xslOrder.getPayMemo());//收款情况
					}
						
					if(Constrant.CANCEL.equalsIgnoreCase(xslOrder.getOrderStatus()))
					{
						cell1=row1.createCell(9);
						cell1.setCellStyle(style1);
						cell1.setCellValue(xslOrder.getCancelMemo());//订单状态
					}
					
					else{
						cell1=row1.createCell(9);
						cell1.setCellStyle(style1);
						cell1.setCellValue("正常");//订单状态
					}	
				}
				if(startMergeLine<endMergeLine){
					//每个内循环结束，合并行对应的列
					sheet.addMergedRegion(new CellRangeAddress(startMergeLine,endMergeLine,0,0));
					sheet.addMergedRegion(new CellRangeAddress(startMergeLine,endMergeLine,1,1));
					sheet.addMergedRegion(new CellRangeAddress(startMergeLine,endMergeLine,2,2));
					
					sheet.addMergedRegion(new CellRangeAddress(startMergeLine,endMergeLine,8,8));
					sheet.addMergedRegion(new CellRangeAddress(startMergeLine,endMergeLine,9,9));
					sheet.addMergedRegion(new CellRangeAddress(startMergeLine,endMergeLine,10,10));
				}
				
				startMergeLine = endMergeLine;
			}
			HSSFRow row1 = sheet.createRow(endMergeLine+1);
			row1.setHeight((short)(200*3));
		
			HSSFCell cell1 =row1.createCell(0);
			cell1.setCellStyle(style1);
			cell1.setCellValue("总计："+Tools.CURRENCY_FORMAT.format(new BigDecimal(allCount/100.0)));
			
			try
			{
				OutputStream out = response.getOutputStream();
				wb.write(out);
				out.close();
			}
			catch (Exception e)
			{
				log.info("=====导出excel异常====");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			if(StringUtils.isNotEmpty(baseData.getStartTime()))
				baseData.setStartTime(baseData.getStartTime()+Constrant.DAY_START_TIME);
			
			if(StringUtils.isNotEmpty(baseData.getEndTime()))
				baseData.setEndTime(baseData.getEndTime()+Constrant.DAY_END_TIME);
			
			count = orderRepository.count(baseData);
			if(count<=0)
				return pager;
			list= orderRepository.list(baseData);
			for(int i=0;i<list.size();i++){
				Order or = (Order)list.get(i);
				or.setItems(orderRepository.getOrderItemsByNum(or));
			}
			pager.setDatas(list);
			pager.setCount(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pager;
	
	}

	@Override
	public Object del(BaseData baseData) throws SQLException {
		Order o = (Order)baseData;
		Order delOrder = orderRepository.getOrderById(o);
		orderRepository.del(baseData);
		if(null != delOrder && StringUtils.isNotEmpty(delOrder.getOrderNum())){
			o.setOrderNum(delOrder.getOrderNum());			
			orderRepository.delItemByOrderNum(o);
		}
		return null;
	}
	public Object delItem(BaseData baseData) throws SQLException {
		orderRepository.delOrderItem(baseData);
		return null;
	}
	
	public void changeStatus(Order baseData,String type) throws SQLException{
		Order c = orderRepository.getOrderById(baseData);
		if(null == c)
			return;
		if( !Constrant.NORMAL.equalsIgnoreCase(c.getOrderStatus())  ||  Constrant.HAS_PAY.equalsIgnoreCase(c.getPayStatus()))//只有单据状态正常，没有结清的才能作废和结清
			return;
		
		if(Constrant.HAS_PAY.equalsIgnoreCase(type) ){
			baseData.setPayMemo(c.getOrderNum()+"于"+Tools.nyrsfm.format(new Date())+"现金结清");
			orderRepository.changePayStaus(baseData);
		}else if(Constrant.DELAY_PAY.equalsIgnoreCase(type) ){
			baseData.setPayMemo(c.getOrderNum()+"于"+Tools.nyrsfm.format(new Date())+"记账");
			orderRepository.changePayStaus(baseData);
		} else {
			baseData.setCancelMemo(c.getOrderNum()+"于"+Tools.nyrsfm.format(new Date())+"作废");
			orderRepository.changeStaus(baseData);
		}
	}

	@Override
	public Object add(BaseData baseData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
