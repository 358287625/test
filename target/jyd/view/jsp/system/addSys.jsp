<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>新建系统化配置</title>
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
function add(key){
	var val= $("#"+key).val();
	if($.trim(val)==""){
		alert("系统配置参数不能为空");
		return;
	}
	
	if("d7290fa7128dbb0754fc78eac5a86ecb"==key){
		  var reg = new RegExp("^[0-9]+(.[0-9]{1,2})?$");
	    if(!reg.test(val)){
	        alert("请输入0到1之前的数字，支持两位小数!");
	        return;
	    }
		if(val>1 || val<0){
			alert("折扣值必须大于等于0,并且小于等于1");
			return;
		}
		
	}
	function callBack(data){
		if (data.code == -2){
			location.href = "${basePath}view/jsp/timer.jsp";
		}else if(data.code==0){
			location.href="${basePath}sys/list.do?m=${m}";
		}else if(data.code==-1){
			alert(data.msg);
		}else{
			alert("返回数据异常");
		}
	}
	 var url="${basePath}sys/add.do?m=${m}";
	 var data={"n":key,"a":val};
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
			<div class="panel panel-default">
				<div class="panel-body">

					<div class="form-group">
						<label>公司名称</label> <input type="text"
							id="671a2384d772eaf328bcc58f9e6c7050" maxlength="16"
							placeholder="请输入新建的公司名称" class="form-control">
					</div>
					<button type="button" class="mb-sm btn btn-primary"
						onclick="add('671a2384d772eaf328bcc58f9e6c7050');">保&nbsp;&nbsp;存</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="mb-sm btn btn-danger" onclick="javascript:location.href='${basePath}sys/list.do?m=${m}'">取&nbsp;&nbsp;消</button>


				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-body">

					<div class="form-group">
						<label>类&nbsp;&nbsp;型</label> <input type="text"
							id="aca870d70e8b1e369403bf5a71c9f1fb" maxlength="8"
							placeholder="请输入新建的类型名称" class="form-control">
					</div>
					<button type="button" class="mb-sm btn btn-primary"
						onclick="add('aca870d70e8b1e369403bf5a71c9f1fb');">保&nbsp;&nbsp;存</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="mb-sm btn btn-danger" onclick="javascript:location.href='${basePath}sys/list.do?m=${m}'">取&nbsp;&nbsp;消</button>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-body">

					<div class="form-group">
						<label>单&nbsp;&nbsp;位</label> <input type="text"
							id="53cd638b8e3adc0db32d3b5ab4aa45ff" maxlength="8"
							placeholder="请输入新建的单位名称" class="form-control">
					</div>
					<button type="button" class="mb-sm btn btn-primary"
						onclick="add('53cd638b8e3adc0db32d3b5ab4aa45ff');">保&nbsp;&nbsp;存</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="mb-sm btn btn-danger"  onclick="javascript:location.href='${basePath}sys/list.do?m=${m}'">取&nbsp;&nbsp;消</button>


				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-body">

					<div class="form-group">
						<label>规格尺寸</label> <input type="text"
							id="07b49af152f5d624e643c54a66b53cda" maxlength="8"
							placeholder="请输入新建的规格尺寸" class="form-control">
					</div>
					<button type="button" class="mb-sm btn btn-primary"
						onclick="add('07b49af152f5d624e643c54a66b53cda');">保&nbsp;&nbsp;存</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="mb-sm btn btn-danger"  onclick="javascript:location.href='${basePath}sys/list.do?m=${m}'">取&nbsp;&nbsp;消</button>


				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">

					<div class="form-group">
						<label>折&nbsp;&nbsp;扣</label> <input type="text"
							id="d7290fa7128dbb0754fc78eac5a86ecb" maxlength="8"
							placeholder="请输入新建的系统折扣" class="form-control">
					</div>
					<button type="button" class="mb-sm btn btn-primary"
						onclick="add('d7290fa7128dbb0754fc78eac5a86ecb');">保&nbsp;&nbsp;存</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="mb-sm btn btn-danger"  onclick="javascript:location.href='${basePath}sys/list.do?m=${m}'">取&nbsp;&nbsp;消</button>
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