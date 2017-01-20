<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mytaglib" prefix="jyd"%>
<style>
.sidebar .nav > li > a.active {
	color: #fff;
	background-color: #428bca;
}
.sidebar .nav > li.active,
.sidebar .nav > li:hover,
.sidebar .nav > li.active > a,
.sidebar .nav > li:hover > a,
.sidebar .nav > li.active > a > .item-text,
.sidebar .nav > li:hover > a > .item-text,
.sidebar .nav > li.active .nav,
.sidebar .nav > li:hover .nav {
  background-color: #428bca;
  color: #fff;
}
.sidebar .nav > li.active > a > em,
.sidebar .nav > li:hover > a > em {
  color: #fff;
}
</style>
<script type="text/javascript">
	function go(url) {
		window.location.href = url;
	}
</script>
<ul class="nav">

	<li><a href="javascript:void(0);" class="no-submenu "> <em></em>
			<span class="item-text"></span>
	</a></li>
	<li <c:if test="${m=='flist'}"> class="active" </c:if>  ><a href="${basePath}order/list.do?m=flist"  class="no-submenu" > <em class="fa fa-table"></em> <span
			class="item-text">前台清单</span>
	</a></li>
	
	<jyd:per hasPers="<%=request.getCookies() %>" needPer="35e2e5dbb41a0846e12c80bd138f28af">
	<li  <c:if test="${m=='all'}"> class="active" </c:if> ><a href="${basePath}order/list.do?a=1&m=all" class="no-submenu" >
			<em class="fa fa-bar-chart-o"></em> <span class="item-text">汇总报表</span>
	</a></li>
	</jyd:per>
	<jyd:per hasPers="<%=request.getCookies() %>" needPer="35e2e5dbb41a0846e12c80bd138f28af">
	<li  <c:if test="${m=='log'}"> class="active" </c:if>><a href="${basePath}log/list.do?m=log" class="no-submenu">
			<em class="fa fa-flask"></em> <span class="item-text">操作日志</span>
	</a></li>
	</jyd:per>
	<jyd:per hasPers="<%=request.getCookies() %>" needPer="35e2e5dbb41a0846e12c80bd138f28af">
	<li  <c:if test="${m=='cfg'}"> class="active" </c:if>  > <a href="${basePath}sys/list.do?m=cfg" class="no-submenu"> <em
			class="fa fa-edit"></em> <span class="item-text">系统配置</span>
	</a></li>
	</jyd:per>
	
	<li <c:if test="${m=='cmgt'}"> class="active" </c:if> ><a href="${basePath}cus/list.do?m=cmgt"  class="no-submenu"> <em
			class="fa fa-male"></em> <span class="item-text">客户管理</span>
	</a></li>
	
	<jyd:per hasPers="<%=request.getCookies() %>" needPer="35e2e5dbb41a0846e12c80bd138f28af">
	<li  <c:if test="${m=='umgt'}"> class="active" </c:if>><a href="${basePath}user/list.do?m=umgt"   class="no-submenu"> <em
			class="fa fa-cube"></em> <span class="item-text">用户管理</span>
	</a></li>
	</jyd:per>
	
	<li ><a href="${basePath}login/out.do" class="no-submenu"> <em
			class="fa fa-dot-circle-o"></em> <span class="item-text">退出系统</span>
	</a></li>
</ul>