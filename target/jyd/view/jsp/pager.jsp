<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${pager.totalPage>1}">

<div class="dataTables_paginate paging_simple_numbers">
	<ul class="pagination" style="float: right;">
	<c:if test="${pager.curPage>1}">
		<li class="paginate_button active"
			id="datatable3_previous"><a href="javascript:prePage(${pager.curPage-2},${pager.totalPage});"
			aria-controls="datatable3" data-dt-idx="0" tabindex="0">上一页</a></li>
    </c:if>
    	
    	<li class="paginate_button "><a
			href="javascript:void(0);" aria-controls="datatable3"
			data-dt-idx="6" tabindex="0" ><font size="5" >${pager.curPage}</font>/<font size="5" >${pager.totalPage}</font></a></li>
											
		
	<c:if  test="${pager.curPage<pager.totalPage}">
	</c:if>
	<li class="paginate_button active" id="datatable3_next"><a
		href="javascript:nextPage(${pager.curPage},${pager.totalPage});" aria-controls="datatable3"
		data-dt-idx="7" tabindex="0">下一页</a></li>
	</ul>
</div>
</c:if>
<script>
function nextPage(goPage,totalPage){
	if(null != goPage && undefined != goPage
			&& $.trim(goPage) != "" && goPage>0 && goPage < totalPage ){
		$("#currentPageIndexid").val(goPage);
		//枫叶说明，formid 必须是searchFormId，同时该form中必须以隐藏域的方式保存当前显示第几页，每页显示多少页。且id统一且固定
		$("#doSearchFromId").submit();
	}
}
function prePage(goPage,totalPage){
	if(null != goPage && undefined != goPage
			&& $.trim(goPage) != "" && goPage>=0 && goPage < (totalPage-1)){
		$("#currentPageIndexid").val(goPage);
		$("#doSearchFromId").submit();
	}
}
function goPage(curPage,totalPage){
	var go_index = $("#goinputId").val();
	if(null != go_index && undefined != go_index
			&& $.trim(go_index) != "" && parseInt (go_index)> 0 && parseInt (go_index) <= totalPage && curPage != go_index){
		$("#currentPageIndexid").val(go_index-1);
		$("#doSearchFromId").submit();
	}
}
</script>