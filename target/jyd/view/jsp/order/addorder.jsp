<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="/mytaglib" prefix="jyd"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common_base.jsp"></jsp:include>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
   <meta name="description" content="">
   <meta name="keywords" content="">
   <meta name="author" content="">
   <title>添加打印单</title>
   
   
   <!-- Bootstrap CSS-->
   <link rel="stylesheet" href="view/css/bootstrap.css">
   <link rel="stylesheet" href="view/css/font-awesome.min.css">
   <link rel="stylesheet" href="view/css/animate-animo.css">
   <link rel="stylesheet" href="view/css/app.css">
   <link rel="stylesheet" href="view/css/whirl.css">
   <!-- Modernizr JS Script-->
   <script src="view/js/modernizr.custom.js" type="application/javascript"></script>
   <!-- FastClick for mobiles-->
   <script src="view/js/fastclick.js" type="application/javascript"></script>
   <script src="view/js/jquery-1.9.1.min.js"></script>
	<script src="view/js/jQuery.print.js"></script>
   <script type="text/javascript">
   function getById(id){
	   return $("#"+id).val();
   }
   function getAttrById(id,attr){
	   return $("#"+id).attr(attr);
   }
   
   function getLengthByIName(name){
	   var nameObj= $("input[name='"+name+"']");
	   if(isNotEmpty(nameObj))
		   return nameObj.length;
	   else
		   return 0;
   }
   
   </script>
</head>
<body>
 <!-- START Main wrapper-->
   <div class="wrapper">
    <jsp:include page="../logo.jsp" /> 
      <aside class="aside">
         <nav class="sidebar">
           <jsp:include  page="../left.jsp"/> 
         </nav>
      </aside>
      <input type="hidden" id="orderNumId" value="${n}"><!-- 订单号 -->
      <input type="hidden" id="orderNameId" value="${u}"><!-- 开单员 -->
      <input type="hidden" id="orderDateId" value="${d}"><!-- 开单日期 -->
      <c:forEach items="${c}" var="data" varStatus="s">
      	<input type="hidden" id="orderCMPId_${s.index}" name="cmp" title="${data.id}" value=" ${data.name}">
      </c:forEach>
      
    <%--     <c:forEach items="${lx}" var="data" varStatus="s">
      	<input type="hidden" id="lx_${s.index}" name="lx" title="${data.id}" value=" ${data.name}">
      </c:forEach>
      
       <c:forEach items="${gg}" var="data" varStatus="s">
      	<input type="hidden" id="gg_${s.index}" name="gg" title="${data.id}" value=" ${data.name}">
      </c:forEach>
      
       <c:forEach items="${zk}" var="data" varStatus="s">
      	<input type="hidden" id="zk_${s.index}" name="zk" title="${data.id}" value=" ${data.name}">
      </c:forEach>
      
       <c:forEach items="${dw}" var="data" varStatus="s">
      	<input type="hidden" id="dw_${s.index}" name="dw" title="${data.id}" value=" ${data.name}">
      </c:forEach> --%>
     	<iframe src="${basePath}order/getord.do?id=${id}&m=${m}" width="80%" height="100%" style="margin-left: 200px;"></iframe>
   </div>
   <div id="loading" class="panel-body whirl ringed loading" style="display: none;"></div>
   <script src="view/js/bootstrap.min.js"></script>
   <script src="view/js/chosen.jquery.js"></script>
   <script src="view/js/bootstrap-slider.min.js"></script>
   <script src="view/js/bootstrap-filestyle.min.js"></script>
   <script src="view/js/animo.min.js"></script>
   <script src="view/js/index.js"></script>
   <script src="view/js/jquery.slimscroll.min.js"></script>
   <script src="view/js/json2.min.js"></script>
   <script src="view/js/screenfull.min.js"></script>
   <script src="view/js/app.js"></script>
</body>
</html>