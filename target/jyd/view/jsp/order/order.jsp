<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fn" %>
<%@ taglib uri="/mytaglib" prefix="jyd"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common_base.jsp"></jsp:include>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<title>前台清单</title>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--[if lt IE 9]><script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script><script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script><![endif]-->
<!-- Bootstrap CSS-->
<link rel="stylesheet" href="view/css/bootstrap.css">
<!-- Vendor CSS-->
<link rel="stylesheet" href="view/css/font-awesome.min.css">
<link rel="stylesheet" href="view/css/animate-animo.css">
<!-- START Page Custom CSS-->
<link rel="stylesheet" href="view/css/bootstrap-slider.min.css">
<link rel="stylesheet" href="view/css/chosen.css">
<link rel="stylesheet" href="view/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="view/css/codemirror.css">
<link rel="stylesheet" href="view/css/bootstrap-tagsinput.css">
<link rel="stylesheet" href="view/css/app.css">
<link rel="stylesheet" href="view/css/whirl.css">
<script src="view/js/modernizr.custom.js"></script>
<script src="view/js/fastclick.js"></script>
</head>
<body>
	<!-- START Main wrapper-->
	<div class="wrapper">
		<!-- START Top Navbar-->
		 <jsp:include page="../logo.jsp" /> 
		<aside class="aside"> <nav class="sidebar"> <jsp:include
			page="../left.jsp" /> </nav> </aside>
		<!-- End aside-->
		<!-- START Main section-->
		<section> <!-- START Page content-->
		<div class="content-wrapper">
			<!-- <h3>Data Tables
               <small>Tables, one step forward.</small>
            </h3> -->
			<!-- START DATATABLE 1 -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
						
							<form role="form" method="post" action="${basePath}order/list.do?m=${m}" class="form-inline" id="doSearchFromId">
								<div class="form-group">
									客户名称： <input id="input-email" type="text" name="customerName" value="${order.customerName}" placeholder="客户名称"
										class="form-control">
								</div>
<%-- 								<div class="form-group">
									客户简码： <input id="input-email" type="text"  name="customerShortCode" value="${item.customerShortCode}" placeholder="客户简码"
										class="form-control">
								</div>
 --%>
								<div class="form-group">
									产品名称： <input id="input-email" type="text"  name="userName" value="${order.userName}" placeholder="产品名称"
										class="form-control">
								</div>
								<div class="form-group">
									接件单号： <input id="input-email" type="text"  name="orderNum" value="${order.orderNum}" placeholder="接件单号"
										class="form-control">
								</div>
								<br>

								<div class="form-group">
									<label  class="col-sm-4 control-label"
										style="width: 73px; line-height: 37px;">下单时间从</label>
									<div class="col-sm-8">
										<!--input.datepicker.form-control(size='16', type='text', value='12-02-2013', data-date-format='dd-mm-yyyy')-->
										<div data-format="YYYY-MM-DD"  class="datetimepicker input-group date input-md">
											<input type="text" placeholder="下单时间起始时间"  name="startTime" value="${order.startTime}" class="form-control"> <span
												class="input-group-addon"> <span
												class="fa fa-calendar"></span>
											</span>
										</div>

									</div>
								</div>

									<div class="form-group">
									<label  class="col-sm-4 control-label"
										style="width: 73px; line-height: 37px;">到</label>
									<div class="col-sm-8">
										<!--input.datepicker.form-control(size='16', type='text', value='12-02-2013', data-date-format='dd-mm-yyyy')-->
										<div data-format="YYYY-MM-DD" class="datetimepicker input-group date input-md">
											<input type="text"  name="endTime" value="${order.endTime}" placeholder="下单时间结束时间" class="form-control"> <span
												class="input-group-addon"> <span
												class="fa fa-calendar"></span>
											</span>
										</div>

									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label"
										style="width: 73px; line-height: 37px;">收款情况：</label>
									<div class="col-sm-8">
										<select class="chosen-select input-md"  name="customerId">
											<option value="">全部</option>
											<option <c:if test="${order.customerId=='1'}">selected="selected"</c:if>  value="1">已收款</option>
											<option <c:if test="${order.customerId=='0'}">selected="selected"</c:if>  value="0">未收款</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-4 control-label"
										style="width: 73px; line-height: 37px;">单据状态：</label>
									<div class="col-sm-8" />
									<select class="chosen-select input-md"  name="userNum">
										<option value="">全部</option>
										<option value="0" <c:if test="${order.userNum=='0'}">selected="selected"</c:if> >正常</option>
										<option value="1" <c:if test="${order.userNum=='1'}">selected="selected"</c:if>>作废</option>
									</select>
								</div>
						</div>
						 <input type="hidden" value="${pager.curPage-1}" name="start" id="currentPageIndexid">
						<button type="submit" class="btn btn-info">搜索</button>
						</form>
						<jyd:per hasPers="<%=request.getCookies() %>" needPer="c85b5056068c1f39a089f718fec077ba">
						<button type="button" onclick="javascript:location.href='${basePath}order/init.do?m=${m}'" class="btn btn-primary"
							style="float: right; margin-right: 18px;">开打印单</button>
						</jyd:per>
					</div>
					<div class="panel-body" style="text-align: center;">
						<table id="datatable1" class="table table-striped table-hover">
							<thead>
								<tr>
									<th style="text-align: center;">客户名称</th>
									<th style="text-align: center;">联系电话</th>
									<th style="text-align: center;">接件单号</th>
									<th style="text-align: center;">状态</th>
									<th style="text-align: center;">产品名称</th>
									<th style="text-align: center;">数量</th>
									<th style="text-align: center;">操作</th>
								</tr>
							</thead>
							<tbody>
							

							<c:forEach items="${pager.datas}" var="data" varStatus="s">
								<c:forEach items="${data.items}" var="item" varStatus="count">
									<tr <c:if test="${s.index%2==0}"> class="success" </c:if>
														<c:if test="${s.index%2!=0}"> class="info" </c:if>
														role="row">
										
										<c:if test="${count.index%data.length==0 }">
											<td style="width:10%;" rowspan="${data.length}" >${data.customerName}</td>
											<td style="width:15%;"rowspan="${data.length}" >${data.customerPhone}</td>
											<td style="width:15%;"rowspan="${data.length}" >${data.orderNum}</td>
											<td style="width:15%;"rowspan="${data.length}"><c:if test="${data.orderStatus=='0'}">正常</c:if><c:if test="${data.orderStatus=='1' }">作废</c:if> -- <c:if test="${data.payStatus=='0'}">未付款</c:if><c:if test="${data.payStatus=='1' }">结清</c:if><c:if test="${data.payStatus=='2' }">记账</c:if> </td>
										</c:if>
											<td style="width:15%;">${item.name}</td>
											<td style="width:15%;">${item.num}</td>	
												
										<c:if test="${count.index%data.length==0 }">
											<td  rowspan="${data.length}" ><button type="button" onclick="javascript:location.href='${basePath}order/init.do?id=${data.id}&m=${m}'" class="btn btn-info">查看</button>
											</td>				
										</c:if>
									</tr>
								</c:forEach>
							</c:forEach>
							</tbody>
						</table>
						<jsp:include page="../pager.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<!-- END DATATABLE 1 -->
	</div>
	<!-- END Page content-->
	</section>
	<!-- END Main section-->
	</div>
	<div id="loading" class="panel-body whirl ringed loading" style="display: none;"></div>
	<!-- Main vendor Scripts-->
	<script src="view/js/jquery.min.js"></script>
	<script src="view/js/bootstrap.min.js"></script>
	<!-- Plugins-->
	<script src="view/js/chosen.jquery.js"></script>
	<script src="view/js/bootstrap-slider.min.js"></script>
	<script src="view/js/bootstrap-filestyle.min.js"></script>
	<!-- Animo-->
	<script src="view/js/animo.min.js"></script>
	<!-- Sparklines-->
	<script src="view/js/index.js"></script>
	<!-- Slimscroll-->
	<script src="view/js/jquery.slimscroll.min.js"></script>
	<!-- Store + JSON-->
	<script src="view/js/json2.min.js"></script>
	<!-- ScreenFull-->
	<script src="view/js/screenfull.min.js"></script>
	<!-- START Page Custom Script-->
	<!-- Markdown Area Codemirror and dependencies-->
	<script src="view/js/codemirror.js"></script>
	<script src="view/js/overlay.js"></script>
	<script src="view/js/markdown.js"></script>
	<script src="view/js/xml.js"></script>
	<script src="view/js/gfm.js"></script>
	<script src="view/js/marked.js"></script>
	<!-- MomentJs and Datepicker-->
	<script src="view/js/moment-with-locales.min.js"></script>
	<script src="view/js/bootstrap-datetimepicker.min.js"></script>
	<!-- Tags input-->
	<script src="view/js/bootstrap-tagsinput.js"></script>
	<!-- Input Mask-->
	<script src="view/js/jquery.inputmask.bundle.min.js"></script>
	<!-- END Page Custom Script-->
	<!-- App Main-->
	<script src="view/js/app.js"></script>
</body>
</html>