<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common_base.jsp"></jsp:include>
<script src="view/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>请求超时</title>
</head>
<body onload="init();">
<div style="margin-left:10rem ;margin-top: 20rem;">
	<h1>你的连续两次请求时间间隔超过半小时，<font color="#FF0000" id="timerCount"> 10 </font>   秒钟后将自动跳转到登录界面</h1>
</div>
<script type="text/javascript">
function init(){
	
	setInterval(function(){
		var count = $.trim($("#timerCount").html());
		if(count==0)
			window.location.href="${basePath}login/index.do";
		else{
			 $("#timerCount").html(count-1);
		}
	}, 1000);
}
</script>
</body>
</html>