<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	request.setAttribute("basePath", basePath);
%>
<base href="${basePath}"/>
<script src="view/js/tools.js" type="application/javascript"></script>
<script src="view/js/md5.js" type="application/javascript"></script>
