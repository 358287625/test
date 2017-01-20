<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common_base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
<!-- Meta-->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--[if lt IE 9]><script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script><script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script><![endif]-->
<!-- Bootstrap CSS-->
<link rel="stylesheet" href="view/css/bootstrap.css">
<!-- Vendor CSS-->
<link rel="stylesheet" href="view/css/font-awesome.min.css">
<link rel="stylesheet" href="view/css/animate-animo.css">
<link rel="stylesheet" href="view/css/app.css">
<link rel="stylesheet" href="view/css/common.css">
<link rel="stylesheet" href="view/css/whirl.css">
<script src="view/js/modernizr.custom.js" type="application/javascript"></script>
<script src="view/js/fastclick.js" type="application/javascript"></script>
<script type="text/javascript">
	function login() {
		var pwd = $.trim($("#loginPwd").val());
		var name = $.trim($("#loginName").val());
		if (pwd == "") {
			alert("请输入登录密码");
			return;
		}

		if (name == "") {
			alert("请输入登录名称");
			return;
		}
		var data = {
			"n" : name,
			"p" : hex_md5(pwd)
		};
		var url = "${basePath}login/in.do?m=${m}";
		function callBack(data) {
			
			if (data.code == -2) {
				location.href = "${basePath}view/jsp/timer.jsp";
			} else if (data.code != 0) {
				alert(data.msg);
			} else {
				window.location.href = "${basePath}order/list.do?m=${m}";
			}
		}
		doAjax(url, data, callBack);
	}

	function readPrint() {
		$("#printHiddenId").hide();
		printWindow = window.open();
		var bdhtml = window.document.body.innerHTML; //获得body标签内的全部html代码
		printWindow.document.write(bdhtml);
		$("#printHiddenId").show();
		printWindow.print();
		return false;
	}
</script>
</head>
<body id="picprint">
	<!-- START wrapper-->
	<div class="row row-table page-wrapper">
		<div class="col-lg-4 col-md-6 col-sm-8 col-xs-12 align-middle">
			<!-- START panel-->
			<div data-toggle="play-animation" id="picprint" data-play="fadeIn"
				data-offset="0" class="panel widget b0">
				<img src="${basePath}view/images/lock-bg.jpg" alt="lock-bg"
					class="img-responsive">

				<!--remove_start-->
				<div class="panel-body" id="printHiddenId">
					<h3 class="text-center">Hello JYD!</h3>
					<h3>
						<p class="text-center">The world is wonderful because of you</p>
					</h3>
					<form role="form">
						<div class="form-group">
							<!-- <div class="pull-right">
                        <a href="#" class="text-muted">
                           <small>Forgot your password?</small>
                        </a>
                     </div> -->
							<label>登录名称：</label> <input type="text" id="loginName"
								placeholder="请输入登录名" maxlength="8" class="form-control">
						</div>
						<div class="form-group has-feedback">
							<label>登录密码：</label> <input type="password" id="loginPwd"
								placeholder="请输入登录密码" maxlength="8" class="form-control">
							<span class="fa fa-lock form-control-feedback text-muted"></span>
						</div>
						<div class="clearfix">
							<div class="pull-right">
								<button type="button" onclick="login()" class="btn btn-primary">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
								<!-- <button type="button" onclick="readPrint()" class="btn btn-primary">打印</button> -->
							</div>
						</div>
					</form>
				</div>
				<!--end-->
			</div>
			<!-- END panel-->
		</div>
	</div>

	<div id="loading" class="panel-body whirl ringed loading" style="display: none;"></div>
	<!-- END wrapper-->
	<!-- START Scripts-->
	<!-- Main vendor Scripts-->
	<script src="view/js/jquery.min.js"></script>
	<script src="view/js/bootstrap.min.js"></script>
	<!-- Animo-->
	<script src="view/js/animo.min.js"></script>
	<!-- Custom script for pages-->
	<script src="view/js/pages.js"></script>
	<!-- END Scripts-->
</body>
<script type="text/javascript">
	function go(url) {
		window.location.href = url;
	}
</script>
</html>