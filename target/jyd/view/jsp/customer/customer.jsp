<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common_base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Meta-->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<title>客户管理</title>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--[if lt IE 9]><script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script><script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script><![endif]-->
<!-- Bootstrap CSS-->
<link rel="stylesheet" href="view/css/bootstrap.css">
<!-- Vendor CSS-->
<link rel="stylesheet" href="view/css/font-awesome.min.css">
<link rel="stylesheet" href="view/css/animate-animo.css">
<link rel="stylesheet" href="view/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="view/css/dataTables.colVis.css">
<link rel="stylesheet" href="view/css/app.css">
<link rel="stylesheet" href="view/css/whirl.css">
<!-- Modernizr JS Script-->
<script src="view/js/modernizr.custom.js" type="application/javascript"></script>
<!-- FastClick for mobiles-->
<script src="view/js/fastclick.js" type="application/javascript"></script>
<script type="text/javascript">
function del(id){
	if(confirm("你确定删除吗？")){
		var url ="${basePath}cus/del.do?m=${m}";
		var data={"d":id};
		function callBack(data){
			if (data.code == -2){
				location.href = "${basePath}view/jsp/timer.jsp";
			}else if(data.code!=0){
				alert("操作失败");
			}else{
				$("#doSearchFromId").submit();
			}
		}
		doAjax(url,data,callBack);
	}
}
</script>

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
						<div class="row">
							<form action="${basePath}cus/list.do?m=${m}" method="post" id="doSearchFromId">
								<div class="col-lg-2">
									<div class="input-group" style="padding-left: 15px;">
										<input type="text"  name="keyWord" value="${keyWord}" maxlength="16"  placeholder="请输入搜索关键字"
											class="input-sm form-control"
											style="height: 35px; width: 300px;"> <span
											class="input-group-btn">
											<button type="submit" class="btn btn-info">搜索</button>
										</span>
									</div>
								</div>
								<input type="hidden" value="${pager.curPage-1}" name="start"
									id="currentPageIndexid">
							</form>
							<div class="col-lg-8"></div>
							<div class="col-lg-8 col-lg-2">
								<div class="input-group pull-right">
									<span class="input-group-btn"> <!-- <button class="btn btn-sm btn-default">新增客户</button> -->
										<button type="button"
											onclick="go('view/jsp/customer/addCustomer.jsp?m=${m}');"
											class="btn btn-info"
											style="float: right; margin-right: 18px;">新增客户</button>
									</span>
								</div>
							</div>
						</div>
						<div class="panel-body" style="text-align: center;">
							<table id="datatable1" class="table table-striped table-hover">
								<thead>
									<tr>
										<th style="text-align: center;">客户简码</th>
										<th style="text-align: center;">公司名称</th>
										<th style="text-align: center;">联系人</th>
										<th style="text-align: center;">手机</th>
										<th style="text-align: center;">地址</th>
										<th style="text-align: center;">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pager.datas}" var="data" varStatus="s">
											<tr <c:if test="${s.index%2==0}"> class="gradeA odd success" </c:if>
												<c:if test="${s.index%2!=0}"> class="gradeA even info" </c:if>
											role="row">
										<td class="sorting_1">${data.shortCode }</td>
										<td>${data.companyName }</td>
										<td>${data.name }</td>
										<td>${data.phone }</td>
										<td>${data.address }</td>
										<td>
											<button type="button" onclick="javascript:location.href='${basePath}cus/get.do?id=${data.id}&m=${m}'" class="mb-sm btn btn-success">修改</button>
											<button type="button"  onclick="del('${data.id}');"  class="mb-sm btn btn-danger">删除</button>
										</td>
									</tr>
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
		<!-- END Page content--> </section>
		<!-- END Main section-->
	</div>
	<div id="loading" class="panel-body whirl ringed loading" style="display: none;"></div>
	<!-- END Main wrapper-->
	<!-- START Scripts-->
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
	<!-- Data Table Scripts-->
	<script src="view/js/jquery.dataTables.min.js"></script>
	<script src="view/js/dataTables.bootstrap.min.js"></script>
	<script src="view/js/dataTables.colVis.js"></script>
	<!-- END Page Custom Script-->
	<!-- App Main-->
	<!--    <script src="view/js/app.js"></script> -->
	<!-- END Scripts-->
</body>
</html>