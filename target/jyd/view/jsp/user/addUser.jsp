<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>添加用户</title>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--[if lt IE 9]><script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script><script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script><![endif]-->
<!-- Bootstrap CSS-->
<link rel="stylesheet" href="view/css/bootstrap.css">
<!-- Vendor CSS-->
<link rel="stylesheet" href="view/css/font-awesome.min.css">
<link rel="stylesheet" href="view/css/animate-animo.css">
<link rel="stylesheet" href="view/css/app.css">
<link rel="stylesheet" href="view/css/whirl.css">
<script src="view/js/modernizr.custom.js" type="application/javascript"></script>
<script src="view/js/fastclick.js" type="application/javascript"></script>
<script type="text/javascript">
	var Regx = /^[A-Za-z0-9]*$/;
	function save() {
		var id = $("input[name='userinfor.id']").val();
		var loginName = $("input[name='userinfor.loginName']").val();
		var pwd = $("input[name='userinfor.pwd']").val();
		var name = $("input[name='userinfor.name']").val();
		var phone = $("input[name='userinfor.phone']").val();
		var identityCard = $("input[name='userinfor.identityCard']").val();
		var sex = $("input[name='userinfor.sex']:checked").val();
		var addOrder = $("input[name='userinfor.addOrder']:checked").val();
		var editOrder = $("input[name='userinfor.editOrder']:checked").val();
		var received = $("input[name='userinfor.received']:checked").val();
		var printOrder = $("input[name='userinfor.printOrder']:checked").val();

		if (isEmpty(loginName)) {
			alert("登录名不能为空");
			return;
		}

		if (!Regx.test(loginName)) {
			alert("登录名只能是字母或者数字");
			return;
		}

		if (isEmpty(pwd)) {
			alert("登录密码不能为空");
			return;
		}

		if (!Regx.test(pwd)) {
			alert("密码只能是字母或者数字");
			return;
		}

		if (pwd.length != 8) {
			alert("密码必须为八位");
			return;
		}

		if (isEmpty(name)) {
			alert("用户姓名不能为空");
			return;
		}

		if (isEmpty(phone)) {
			alert("用户手机不能为空");
			return;
		}

		if (isEmpty(identityCard)) {
			alert("身份证号码不能为空");
			return;
		}

		if (isEmpty(printOrder) && isEmpty(received) && isEmpty(editOrder)
				&& isEmpty(addOrder)) {
			alert("新建用户至少得拥有一个权限");
			return;
		}

		if (isEmpty(printOrder)) {
			printOrder = 0;
		}

		if (isEmpty(received)) {
			received = 0;
		}

		if (isEmpty(editOrder)) {
			editOrder = 0;
		}

		if (isEmpty(addOrder)) {
			addOrder = 0;
		}

		var url = "${basePath}user/add.do?m=${m}";
		var data = {
			"loginName" : loginName,
			"pwd" : pwd,
			"name" : name,
			"sex" : sex,
			"phone" : phone,
			"identityCard" : identityCard,
			"id" : id,
			"printOrder" : printOrder,
			"received" : received,
			"editOrder" : editOrder,
			"addOrder" : addOrder
		}
		function callBack(data) {
			if (data.code == -2){
				location.href = "${basePath}view/jsp/timer.jsp";
			}else if (data.code != 0) {
				alert(data.msg);
				return;
			} else {
				location.href = "${basePath}user/list.do?m=${m}";
			}
		}
		doAjax(url, data, callBack);
	}
</script>
</head>
<body>
	<!-- START Main wrapper-->
	<div class="wrapper">
		<jsp:include page="../logo.jsp" /> 
		<!-- START aside-->
		<aside class="aside"> <!-- START Sidebar (left)--> <nav
			class="sidebar"> <jsp:include page="../left.jsp" /> </nav> <!-- END Sidebar (left)-->
		</aside>
		<!-- End aside-->
		<!-- START aside-->
		<aside class="offsidebar"> <!-- START Off Sidebar (right)--> <!-- END Off Sidebar (right)-->
		</aside>
		<!-- END aside-->
		<!-- START Main section-->
		<section> <!-- START Page content-->
		<div class="content-wrapper">
			<!-- START panel-->
			<!-- END panel-->
			<!-- START row-->
			<!-- END row-->
			<!-- START panel-->
			<div class="panel panel-default">
				<div class="panel-body">
					<input name="userinfor.id" value="${userinfor.id}" type="hidden">
					<fieldset>
						<div class="form-group">
							<label for="input-id-1" class="col-sm-2 control-label">登录名称：</label>
							<div class="col-sm-10">
								<input type="text" maxlength="8"
									<c:if test="${userinfor.id!='' && userinfor.id!=null}">disabled="disabled"</c:if>
								  value="${userinfor.loginName}"
									name="userinfor.loginName" class="form-control">
							</div>
						</div>
					</fieldset>
					<fieldset>
						<div class="form-group">
							<label for="input-id-1" class="col-sm-2 control-label">登录密码：</label>
							<div class="col-sm-10">
								<input type="password" maxlength="8" value="${userinfor.mwpwd}"
									name="userinfor.pwd" class="form-control">
							</div>
						</div>
					</fieldset>
					<fieldset>
						<div class="form-group">
							<label for="input-id-1" class="col-sm-2 control-label">用户姓名：</label>
							<div class="col-sm-10">
								<input type="text"<c:if test="${userinfor.id!='' && userinfor.id!=null }">disabled="disabled"</c:if>  maxlength="8" value="${userinfor.name}"
									name="userinfor.name" class="form-control">
							</div>
						</div>
					</fieldset>
					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label">用户性别：</label>
							<div class="col-sm-10">
								<label class="radio-inline c-radio"> <input type="radio"
									name="userinfor.sex" value="0" checked> <span
									class="fa fa-circle"></span>男
								</label> <label class="radio-inline c-radio"> <input
									type="radio" name="userinfor.sex" value="1"
									<c:if test="${userinfor.sex==1}"> checked </c:if>> <span
									class="fa fa-circle"></span>女
								</label>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<div class="form-group">
							<label for="input-id-1" class="col-sm-2 control-label">用户手机：</label>
							<div class="col-sm-10">
								<input type="text" maxlength="16" value="${userinfor.phone}"
									name="userinfor.phone" class="form-control">
							</div>
						</div>
					</fieldset>
					<fieldset>
						<div class="form-group">
							<label for="input-id-1" class="col-sm-2 control-label">身份证号：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" maxlength="32"
									value="${userinfor.identityCard}" name="userinfor.identityCard">
							</div>
						</div>
					</fieldset>
					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label">用户权限：</label>
							<div class="col-sm-10">
								<label class="checkbox-inline c-checkbox"> <input
									type="checkbox" value="1"
									<c:if test="${userinfor.addOrder==1}">
									checked="checked"
                                 </c:if>
									name="userinfor.addOrder"> <span class="fa fa-check"></span>开接件单
								</label> <label class="checkbox-inline c-checkbox"> <input
									type="checkbox" value="1"
									<c:if test="${userinfor.editOrder==1}">
									checked="checked"
                                 </c:if>
									name="userinfor.editOrder"> <span class="fa fa-check"></span>修改接件单
								</label> <label class="checkbox-inline c-checkbox"> <input
									type="checkbox" value="1"
									<c:if test="${userinfor.received==1}">
									checked="checked"
                                 </c:if>
									name="userinfor.received"> <span class="fa fa-check"></span>收款
								</label> <label class="checkbox-inline c-checkbox"> <input
									type="checkbox" value="1"
									<c:if test="${userinfor.printOrder==1}">
									checked="checked"
                                 </c:if>
									name="userinfor.printOrder"> <span class="fa fa-check"></span>打印单据
								</label>
							</div>
						</div>
					</fieldset>
					<div class="form-group" style="text-align: center;">
						<button type="button" class="btn btn-info" onclick="save()">保&nbsp;&nbsp;存</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"
							onclick="javascript:location.href='${basePath}user/list.do?m=${m}'"
							class="btn btn-danger">取&nbsp;&nbsp;消</button>
					</div>

				</div>
			</div>
			<!-- END panel-->
		</div>
		<!-- END Page content--> </section>
		<!-- END Main section-->
	</div>
	<div id="loading" class="panel-body whirl ringed loading" style="display: none;"></div>
	<!-- END Main wrapper-->
	<!-- START Scripts-->
	<!-- Main vendor Scripts-->
	<script src="view/js//jquery.min.js"></script>
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
	<!-- END Page Custom Script-->
	<!-- App Main-->
	<script src="view/js/app.js"></script>
	<!-- END Scripts-->
</body>
</html>