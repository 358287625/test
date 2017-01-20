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
<title>系统配置</title>
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
<script src="view/js/modernizr.custom.js" type="application/javascript"></script>
<script src="view/js/fastclick.js" type="application/javascript"></script>
<script type="text/javascript">
function del(id){
	if(confirm("你确定删除该系统配置参数吗？")){
		var url ="${basePath}sys/del.do?m=${m}"
		var data={"d":id};
		function callBack(data){
			if (data.code == -2){
				location.href = "${basePath}view/jsp/timer.jsp";
			}else if(data.code!=0){
				alert(data.msg);
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
	<jsp:include  page="../logo.jsp"/> 
		<aside class="aside">
			<nav class="sidebar">
				<jsp:include  page="../left.jsp"/> 
			</nav>
		</aside>
		<!-- End aside-->
		<!-- START Main section-->
		<section>
			<!-- START Page content-->
			<div class="content-wrapper">
				<!-- <h3>Data Tables
               <small>Tables, one step forward.</small>
            </h3> -->
				<!-- START DATATABLE 1 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="row">
                        <form action="${basePath}sys/list.do?m=${m}" id="doSearchFromId" method="post">
                     <div class="col-lg-2">
                        <div class="input-group" style=" padding-left: 15px;">
										<input type="text" placeholder="请输入搜索关键字" value="${keyWord}" name="keyWord" maxlength="8" class="input-sm form-control" style="height: 35px;width: 300px;">
                           <span class="input-group-btn">
                              <button type="submit" class="btn btn-info">搜索</button>
                           </span>
						   <input type="hidden" value="${pager.curPage-1}" name="start" id="currentPageIndexid">
                        </div>
                     </div>
                        </form>
                     <!-- <div class="col-lg-8"></div> -->
                    <%--  <div class="col-lg-8 col-lg-2">
                        <div class="input-group pull-right">
                           <span class="input-group-btn">
                              <!-- <button class="btn btn-sm btn-default">新增客户</button> -->
                              <button type="button" onclick="go('view/jsp/system/addSys.jsp?m=${m}');"  class="btn btn-info" style="float: right;margin-right: 18px;">新增系统配置</button>
                           </span>
                        </div>
                     </div> --%>
                  </div>
							<div class="panel-body" style="text-align: center;">
								<table id="datatable1" class="table table-striped table-hover">
									<thead>
										<tr>
											<th  style="text-align: center;/* width: 20%; */">系统配置名称</th>
											<th  style="text-align: center;">系统配置值</th>
											<th  style="text-align: center;">创建人</th>
											<th  style="text-align: center;">操作时间</th>
											<th  style="text-align: center;">操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${pager.datas}" var="data" varStatus="s">
											<tr <c:if test="${s.index%2==0}"> class="gradeA odd success" </c:if>
												<c:if test="${s.index%2!=0}"> class="gradeA even info" </c:if>
											role="row">
											<td class="sorting_1">
											<c:if test="${data.num=='d7290fa7128dbb0754fc78eac5a86ecb'}">折扣</c:if>
											<c:if test="${data.num=='671a2384d772eaf328bcc58f9e6c7050'}">公司</c:if>
											<c:if test="${data.num=='aca870d70e8b1e369403bf5a71c9f1fb'}">类型</c:if>
											<c:if test="${data.num=='53cd638b8e3adc0db32d3b5ab4aa45ff'}">单位名称</c:if>
											<c:if test="${data.num=='07b49af152f5d624e643c54a66b53cda'}">规格尺寸</c:if>
											<c:if test="${data.num=='71d65436a57b052c4143ddf1e524a1fd'}">KCP</c:if>
											</td>
											<td>${data.name}</td>
											<td>${data.createUserName }</td>
											<td> <fn:formatDate value="${data.ctime}" type="both" /> <td>   
                        					<c:if test="${data.num!='671a2384d772eaf328bcc58f9e6c7050'}">
                        						<button type="button" onclick="del('${data.id}')" class="mb-sm btn btn-danger">删除</button>
                        					</c:if>
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
			<!-- END Page content-->
		</section>
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