<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fn"%>
<%@ taglib uri="/mytaglib" prefix="jyd"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common_base.jsp"></jsp:include>
<meta charset="UTF-8">
<title>科卓图文设计制作中心</title>
<link rel="stylesheet" href="view/css/bootstrap.css">
<!-- <link rel="stylesheet" href="view/css/bootstrap-responsive.css"> -->
<link rel="stylesheet" href="view/css/font-awesome.min.css">
<link rel="stylesheet" href="view/css/animate-animo.css">
<link rel="stylesheet" href="view/css/app.css">
<link rel="stylesheet" href="view/css/whirl.css">
<script src="view/js/jquery-1.9.1.min.js"></script>
<script src="view/js/resize.js"></script>
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
<script src="view/js/modernizr.custom.js" type="application/javascript"></script>
<script src="view/js/fastclick.js" type="application/javascript"></script>
<script src="view/js/jQuery.print.js"></script>
<script src="view/js/bootstrap-typeahead.js"></script>


<style>
* {
	margin: 0;
	padding: 0;
}

.outbox {
	position: relative;
	padding: 1.2rem .9rem;
	height: 100%;
	background: #fff;
	color: #000;
	font-family: "宋体";
	font-size: .15rem;
}

.boxcont {
	margin-right: .4rem;
}

.boxcont p {
	text-align: center;
	font-size: .3rem;
	font-weight: normal;
}

.boxcont p b {
	display: inline-block;
	/* 	padding: 0 .2rem; */
	height: .83rem;
	line-height: .83rem;
	margin-right: .1rem;
	/* background: url(view/images/bg1.png) no-repeat; */
	background-size: 100% 100%;
	font-weight: normal;
}

.boxcont p span {
	margin-left: .64rem;
	font-size: .15rem;
}

.boxcont .tit {
	margin: .24rem 0 .1rem;
}

.boxcont table {
	width: 100%;
	border: 1px solid #000;
}

.boxcont table th,.boxcont table td {
	height: .56rem;
}

.boxcont em {
	font-style: normal;
}

.boxcont .em1 {
	display: inline-block;
	height: .47rem;
	line-height: .47rem;
	width: 1rem;
	background: url(view/images/bg2.png) no-repeat;
	background-size: 100% 100%;
	text-align: center;
}

.boxcont .em2 {
	display: inline-block;
	height: .47rem;
	line-height: .47rem;
	width: 1.42rem;
	background: url(view/images/bg3.png) no-repeat;
	background-size: 100% 100%;
	text-align: center;
}

.boxcont .tit input {
	/* margin-right: .2rem; */
	width: 1.56rem;
	font-size: .15rem;
	outline: none;
	border: none;
	border-bottom: 1px solid #666;
}

.instruct {
	position: absolute;
	right: .9rem;
	top: 2.68rem;
	width: .26rem;
	font-size: .14rem;
	text-align: center;
}

.tongji {
	height: .56rem;
	line-height: .56rem;
	border: 1px solid #000;
	border-top: 0;
}

.tongji div {
	display: inline-block;
	min-width: 3.2rem;
	padding: 0 .2rem;
}

.btnlist {
	margin: .35rem auto;
	text-align: center;
}

.btnlist button {
	display: inline-block;
	height: .52rem;
	width: 1.8rem;
	margin: 0 .12rem;
	background: url(view/images/bg4.png) no-repeat;
	background-size: 100% 100%;
	font-size: .28rem;
	border: none;
	outline: none;
	cursor: pointer;
}

.full_td {
	display: block;
	width: 100%;
	height: 100%;
	border: none;
	outline: none;
	text-align: center;
}

.textarea_full_td {
	display: block;
	width: 100%;
	height: 100%;
	border: none;
	outline: none;
	text-align: left;
}

@page {
	size: auto;
	margin: 0mm;
}
</style>
<script type="text/javascript">
	//设置选择的公司id和名称
	function setcmp(id,name){
		$("#cmpId").html(name);
		$("#cmpId_id").val(id);
	}
	
	function setlx(id,name){
		if(table_obj!=null && table_obj!="" && table_obj!=undefined && table_obj!="undefined")
			$(table_obj).val(name);
		$("#lx_id").val(id);
	}
	
	function setgg(id,name){
		if(table_obj!=null && table_obj!="" && table_obj!=undefined && table_obj!="undefined")
			$(table_obj).val(name);
		$("#gg_id").val(id);
	}
	
	function setdw(id,name){
		if(table_obj!=null && table_obj!="" && table_obj!=undefined && table_obj!="undefined")
			$(table_obj).val(name);
		$("#dw_id").val(id);
	}
	
	function setzk(id,name){
		if(table_obj!=null && table_obj!="" && table_obj!=undefined && table_obj!="undefined")
			$(table_obj).val(name);
		$("#zk_id").val(id);
    	countTotal(table_obj);
	}
	
	
	 function syscofigTypeahead(selector,num){
		 $(selector).typeahead({
			 items:8,//显示在下拉菜单中的列表数量的最大值
			 minLength:2,//触发autocomplete功能所需的最少输入字符个数
			 source: function (query, process){
				 return $.ajax({
			            url: '${basePath}sys/all.do?m=${m}',
			            type: 'post',
			            data: {
			            	"num" : num,
			    			"val" : $(selector).val()
			    		},
			            dataType: 'json',
			            success: function (data) { 
			            	if(data.code != 0){
			            		 console.log(result.msg);
			            	}
			            	var array=new Array();
		    				var length = data.obj.length;
		    				for(var index=0;index<length;index++){
		    					var name=data.obj[index].name;
		    				 	array.push("{\"name\":\""+name+"\"}");
		    				}
		    				
		                   return process(array);  
			            }
			        });
			 },
			 matcher: function (obj) {
			        return true;//查询出来的一定都是匹配的
			    },
		    highlighter: function (obj) {
		    	var json = JSON.parse(obj);
		        return '<strong>' + json.name + '</strong>';
		    },
	
		    updater: function (obj) {
		    	var json = JSON.parse(obj); 
				$(selector).val(json.name);
				return json.name; 
		    }
		 });
	}
 
	function process(data) {
			if (data.code == 0 && isNotEmpty(data.obj)){
				var array=new Array();
				var length = data.obj.length;
				for(var index=0;index<length;index++){
				 	array.push(data.obj[index].companyName);
				}
				console.log(array);
				$("#"+id).typeahead({source: array});
			}
		}
	function regTypeahead(regId){
		 $('#'+regId).typeahead({
			 items:8,//显示在下拉菜单中的列表数量的最大值
			 minLength:2,//触发autocomplete功能所需的最少输入字符个数
			 source: function (query, process){
				 return $.ajax({
			            url: '${basePath}cus/all.do?m=${m}',
			            type: 'post',
			            data: {
			            	"name" : regId,
			    			"val" : $("#"+regId).val()
			    		},
			            dataType: 'json',
			            success: function (data) { 
			               if(data.code == 0) {
			               		var array=new Array();
			    				var length = data.obj.length;
			    				for(var index=0;index<length;index++){
			    					var obj=new Object();
			    					companyName=data.obj[index].companyName;
			    					name=data.obj[index].name;
			    					phone=data.obj[index].phone;
			    				 	array.push("{\"companyName\":\""+companyName+"\",\"name\":\""+name+"\",\"phone\":\""+phone+"\"}");
			    				}
			    				
			                   return process(array);                               
			               } else {
			            	   console.log(result.msg);
			               }  
			            }
			        });
			 },
			 matcher: function (obj) {
			        return true;//查询出来的一定都是匹配的
			    },
		    highlighter: function (obj) {
		    	var json = JSON.parse(obj);
		    	if("customerCompanyName"==regId)
		        	return '<strong>' + json.companyName + '</strong>';
		        else if("customerName"==regId)
		        	return '<strong>' + json.name + '</strong>';
		        else 
		        	return '<strong>' + json.phone + '</strong>';
		    },

		    updater: function (obj) {
		    	var json = JSON.parse(obj); 
		        $('#customerCompanyName').val(json.companyName);
		        setLength('customerCompanyName',8,13);
				$("#customerName").val(json.name);
				$("#customerPhone").val(json.phone);
				
				if("customerCompanyName"==regId)
					return json.companyName; 
		        else if("customerName"==regId)
		        	return json.name; 
		        else 
		        	return json.phone; 
		    }
			 
		 });
	}
	function winOnLoad() {
		var canEdit = $("#canEditId").val();
		if(isEmpty("${id}") || (canEdit=="true")){
			regTypeahead("customerCompanyName");
			regTypeahead("customerName");
			regTypeahead("customerPhone");
			 
			$("#tbody_id tr").each(function(){
				
				syscofigTypeahead("#"+$(this).find("td").eq(1).find("input").attr("id"),"aca870d70e8b1e369403bf5a71c9f1fb");
				syscofigTypeahead("#"+$(this).find("td").eq(2).find("input").attr("id"),"07b49af152f5d624e643c54a66b53cda");
				syscofigTypeahead("#"+$(this).find("td").eq(3).find("input").attr("id"),"71d65436a57b052c4143ddf1e524a1fd");
			});
		}else if(isNotEmpty("${id}") && canEdit=="false"){
			$("#customerCompanyName").attr("readonly","readonly");
			$("#customerName").attr("readonly","readonly");
			$("#customerPhone").attr("readonly","readonly");
		}
		
		var orderNum = window.parent.getById("orderNumId");
		var orderName = window.parent.getById("orderNameId");
		var orderDate = window.parent.getById("orderDateId");
		
		var length = window.parent.getLengthByIName("cmp");
		
		if(isEmpty("${id}")){
			document.getElementById("orderNumId").innerHTML = orderNum;
			document.getElementById("orderDateId").value = orderDate;
			document.getElementById("orderNameId").value = orderName;
			if (length > 0) {
				for (var i = 0; i < length; i++) {
					var orderCMP = window.parent.getById("orderCMPId_" + i);
					$("#cmpcontentId").append('<div class="modal-body"><a onclick="javascript:setcmp(\''+window.parent.getAttrById("orderCMPId_" + i, "title")+'\',\''+orderCMP+'\');" > '+orderCMP+'</a> </div>');
					if(i == 0)//默认展示第一个
						setcmp(window.parent.getAttrById("orderCMPId_" + i, "title"),orderCMP);
				}
			} else {
				$("#cmpcontentId").append('<div class="modal-body"><a onclick="javascript:setcmp(\'0d4b65e9-3be0-4566-8a15-bfe5e8c8f7e1\',\'科卓图文设计制作中心\');" > 科卓图文设计制作中心</a> </div>');
				setcmp('0d4b65e9-3be0-4566-8a15-bfe5e8c8f7e1','科卓图文设计制作中心');
			}
	 	}else{
	 		if (length > 0) {
				for (var i = 0; i < length; i++) {
					var orderCMP = window.parent.getById("orderCMPId_" + i);
					$("#cmpcontentId").append('<div class="modal-body"><a onclick="javascript:setcmp(\''+window.parent.getAttrById("orderCMPId_" + i, "title")+'\',\''+orderCMP+'\');" > '+orderCMP+'</a> </div>');
				}
			} else {
				$("#cmpcontentId").append('<div class="modal-body"><a onclick="javascript:setcmp(\'0d4b65e9-3be0-4566-8a15-bfe5e8c8f7e1\',\'科卓图文设计制作中心\');" > 科卓图文设计制作中心</a> </div>');
			}
	 		setTotal();
	 	}
		
		setLength('customerCompanyName',8,13);
	}
	
	function addLine(obj){
		var val = $(obj).val();
		if(val=="" ||val==null || val==undefined || val=="undefined")
			return;
		
		var num =6;/* 一个表单，最多6行 */
		var siblingLength=$(obj).parent().parent().siblings().length;//得到tr的行数
		var trNum = $("#tbody_id").children().length;
		if(siblingLength>=(num-1))//除去自己
			return;
		
		siblingLength = $(obj).parent().parent().nextAll().length;
		
		if(siblingLength>0)//已经有多个兄弟节点了
			return;
		var tr='<tr>';
		tr=tr+'<td><input type="hidden" value="" id="_id"><input onblur="addLine(this);" maxlength="10" class="full_td" /></td>';
		tr=tr+'<td><input  maxlength="8" id="tp_'+trNum+'" class="full_td"/></td>';
		tr=tr+'<td><input  maxlength="8" id="spec_'+trNum+'" class="full_td"/></td>';
		tr=tr+'<td><input  maxlength="16" id="KCP_'+trNum+'" class="full_td" /></td>';
		tr=tr+'<td><input  maxlength="8"  onchange="countTotal(this)" class="full_td" /></td>';
		tr=tr+'<td><input  maxlength="8"  onchange="countTotal(this)" class="full_td"/></td>';
		tr=tr+'<td><input  maxlength="8"  onchange="countTotal(this)" class="full_td" /></td>';
		tr=tr+'<td><textarea rows="2" cols="32" class="textarea_full_td" style="resize:none;font-size: 15px;"></textarea></td>';
		tr=tr+'</tr>';
		
		$("#tbody_id").append(tr);
		setTimeout(function(){
			syscofigTypeahead("#tp_"+trNum,"aca870d70e8b1e369403bf5a71c9f1fb"); //注册控件
			syscofigTypeahead("#spec_"+trNum,"07b49af152f5d624e643c54a66b53cda");
			syscofigTypeahead("#KCP_"+trNum,"71d65436a57b052c4143ddf1e524a1fd");
			
		}, 200);
	}
	function countTotal(obj,flag){
		
		var num=$(obj).parent().parent().children().siblings().eq(4).find("input").val();//数量
		var unit=$(obj).parent().parent().children().siblings().eq(5).find("input").val();//单价
		var total=$(obj).parent().parent().children().siblings().eq(6).find("input").val();//小计
		if(isEmpty(num)){
			return;
		}
		
		if(isEmpty(unit)){
			return;
		}
		  
		var reg = new RegExp("^[0-9]+$");
	    if(!reg.test(num)){
	        alert("数量请输入数字!");
	        $(obj).parent().parent().children().siblings().eq(4).find("input").val("");
	        return;
	    }
	    
	    reg = new RegExp("^[0-9]+(.[0-9]{1,2})?$");
	    if(!reg.test(unit)){
	        alert("单价请输入数字，支持两位小数!!");
	        $(obj).parent().parent().children().siblings().eq(5).find("input").val("");
	        return;
	    }
	    

	    if((unit * num)==total)
	    	return;
	    console.log( " +++ "+(unit * num));
		$(obj).parent().parent().children().siblings().eq(6).find("input").val(setLineTotal(num,unit));
		setTotal();	
	}
	
	function countPrice(obj,flag){
			
			var num=$(obj).parent().parent().children().siblings().eq(4).find("input").val();//数量
			var unit=$(obj).parent().parent().children().siblings().eq(5).find("input").val();//单价
			var total=$(obj).parent().parent().children().siblings().eq(6).find("input").val();//小计
			
			if(isEmpty(num)){
				return;
			}
			
			if(isEmpty(total)){
				return;
			}
			  
			var reg = new RegExp("^[0-9]+$");
		    if(!reg.test(num)){
		        alert("数量请输入数字!");
		        $(obj).parent().parent().children().siblings().eq(4).find("input").val("");
		        return;
		    }
		    
		    reg = new RegExp("^[0-9]+(.[0-9]{1,2})?$");
		    if(!reg.test(total)){
		        alert("小计请输入数字，支持两位小数!!");
		        $(obj).parent().parent().children().siblings().eq(6).find("input").val("");
		        return;
		    }
		    
		    if((total/num)==unit)
		    	return;
		    
		    console.log((total/num) +" -- "+(total%num));
		    
			$(obj).parent().parent().children().siblings().eq(5).find("input").val(total/num);
			setTotal();	
		}
	
	function  setLineTotal(num,unit,disscunt){//更新总计
		if(num=="" ||num==null || num==undefined || num=="undefined")
			return 0;
	
		if(unit=="" ||unit==null || unit==undefined || unit=="undefined")
			return 0;
		
		if(disscunt=="" ||disscunt==null || disscunt==undefined || disscunt=="undefined")
			return forDight(num*unit,1);
		
		return forDight(num*unit*disscunt,1);
		
	}
	
	function  setTotal(){//更新总计
		var count=0;
		$("#tbody_id").children().each(function(){ //tr
			var num=$(this).children().eq(4).find("input").val();//数量
			var unit=$(this).children().eq(5).find("input").val();//单价
			//var disscunt=$(this).children().eq(8).find("input").val();//折扣
			
			count =count+ setLineTotal(num,unit);
		});
		var t = forDight(count,1);
		$("#totalcount").html(t);
		$("#digitUppercase").html(digitUppercase(t));
	}
	//四舍五入，保留How位小数
	function forDight(Dight,How){ 
		Dight = Math.round(Dight*Math.pow(10,How))/Math.pow(10,How); 
		return Dight; 
	} 
	 /** 数字金额大写转换(可以处理整数,小数,负数) */
  var digitUppercase = function(n) {
        var fraction = ['角', '分'];
        var digit = [
            '零', '壹', '贰', '叁', '肆',
            '伍', '陆', '柒', '捌', '玖'
        ];
        var unit = [
            ['元', '万', '亿'],
            ['', '拾', '佰', '仟']
        ];
        var head = n < 0 ? '欠' : '';
        n = Math.abs(n);
        var s = '';
        for (var i = 0; i < fraction.length; i++) {
            s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
        }
        s = s || '整';
        n = Math.floor(n);
        for (var i = 0; i < unit[0].length && n > 0; i++) {
            var p = '';
            for (var j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[n % 10] + unit[1][j] + p;
                n = Math.floor(n / 10);
            }
            s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
        }
        return head + s.replace(/(零.)*零元/, '元')
            .replace(/(零.)+/g, '零')
            .replace(/^整$/, '零元整');
    }
    var table_obj=null;
    function showDiv(obj,target){
    	table_obj=obj;
    	$(obj).attr("data-toggle","modal");
    	$(obj).attr("data-target","#"+target);
    	$(obj).click();
    }
    
    function hasPay(){
    	
    	if (confirm("你确认现金结清该订单吗？")) {
			var url = "${basePath}order/chg.do?m=${m}";
			var data = {
				"t" : 1,
				"d" : '${id}'
			}

			function callBack(data) {
				if (data.code == -2){
					location.href = "${basePath}view/jsp/timer.jsp";
				}else if (data.code != 0) {
					alert(data.msg);
					return;
				} else {
					window.parent.location.href="${basePath}order/init.do?id=${id}&m=${m}";
				}
			}
			doAjax(url, data, callBack);
		}
    	
    }
    
	function noPay(){
		if (confirm("你确认将该订单记账吗？")) {
			var url = "${basePath}order/chg.do?m=${m}";
			var data = {
				"t" : 2,
				"d" : '${id}'
			}

			function callBack(data) {
				if (data.code == -2){
					location.href = "${basePath}view/jsp/timer.jsp";
				}else if (data.code != 0) {
					alert(data.msg);
					return;
				} else {
					window.parent.location.href="${basePath}order/init.do?id=${id}&m=${m}";
				}
			}
			doAjax(url, data, callBack);
		}
    }
	
	function printOrder(){
		$("#btnlistid").hide();
		$("#tbody_id").children().each(function(){//tr
			var inputTag= $(this).children().eq(0).find("input");
			if( isNotEmpty(inputTag) && inputTag.length>0){
				var name = inputTag.eq(1).val();//印刷品名
				if( isEmpty(name))
				{
					 $(this).hide();
				}
			}
		});
		
		setTimeout(function(){
			$("#memoId").hide();
			$("#getManId").hide();
			$("#btnlistid").show();
			$("#tbody_id").children().each(function(){//tr
				var inputTag= $(this).children().eq(0).find("input");
				if( isNotEmpty(inputTag) && inputTag.length>0){
					var name = inputTag.eq(1).val();//印刷品名
					if( isEmpty(name))
					{
						 $(this).show();
					}
				}
			});
		}, 100);
		
	 if(getExplorer() == "IE"){
            pagesetup_null();
       }
	 $("#memoId").show();
	 $("#getManId").show();
     window.print();
	}
	function pagesetup_null(){                
	    var hkey_root,hkey_path,hkey_key;
	    hkey_root="HKEY_CURRENT_USER";
	    hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	    try{
	        var RegWsh = new ActiveXObject("WScript.Shell");
	        hkey_key="header";
	        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
	        hkey_key="footer";
	        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
	    }catch(e){}
	}

	function getExplorer() {
	    var explorer = window.navigator.userAgent ;
	    if (explorer.indexOf("MSIE") >= 0) {
	        return "IE";
	    }
	    else if (explorer.indexOf("Firefox") >= 0) {
	        return "Firefox";
	    }
	    else if(explorer.indexOf("Chrome") >= 0){
	        return "Chrome";
	    }
	    else if(explorer.indexOf("Opera") >= 0){
	        return "Opera";
	    }
	    else if(explorer.indexOf("Safari") >= 0){
	        return "Safari";
	    }
	}     
	function sub(){
		if(confirm("您确定提交吗?")){
			
			var cmpId_id= $.trim($("#cmpId_id").val());
			var cmpName=$.trim($("#cmpId").html());
			var order_id= $.trim($("#order_id").val());
			var customerCompanyName= $.trim($("#customerCompanyName").val());
			var customerName= $.trim($("#customerName").val());
			var customerPhone= $.trim($("#customerPhone").val());
			var orderNum= $.trim($("#orderNumId").html());
			
			if(isEmpty($.trim(customerCompanyName))){
				alert("客户名称不能为空");
				return;
			}
			if(isEmpty($.trim(customerName))){
				alert("联系人不能为空");
				return;
			}
			if(isEmpty($.trim(customerPhone))){
				alert("联系电话不能为空");
				return;
			}
			var itemJson="[";
			var count=0;
			$("#tbody_id").children().each(function(){//tr
				
				var id = $(this).children().eq(0).find("input").eq(0).val();//id
				var name = $(this).children().eq(0).find("input").eq(1).val();//印刷品名
				var tp = $(this).children().eq(1).find("input").val();//类型
				var spec = $(this).children().eq(2).find("input").val();//规格尺寸
				var KCP = $(this).children().eq(3).find("input").val();//kcp
				var num = $(this).children().eq(4).find("input").val();//数量
				var price = $(this).children().eq(5).find("input").val();//单价
				var total = $(this).children().eq(6).find("input").val();//小计
				var memo = $(this).children().eq(7).find("textarea").val();//小计
				if(isNotEmpty(name))
				{
					count++;
					if(isEmpty(id))
						id=0;
					
					if(isNotEmpty(memo) && memo.length>128)
					{
						alert("备注过长，现在长度为 "+memo.length+"，连带标点符号不能超过128个汉字");
						return;
					}
					
					var json ="{";
					json = json+"\"id\":\""+$.trim(id)+"\",";
					json = json+"\"name\":\""+$.trim(name)+"\",";
					json = json+"\"tp\":\""+$.trim(tp)+"\",";
					json = json+"\"spec\":\""+$.trim(spec)+"\",";
					json = json+"\"KCP\":\""+$.trim(KCP)+"\",";
					json = json+"\"num\":\""+$.trim(num)+"\",";
					json = json+"\"price\":\""+$.trim(price)+"\",";
					json = json+"\"total\":\""+$.trim(total)+"\",";
					json = json+"\"memo\":\""+$.trim(memo)+"\"}";
					itemJson = itemJson+json+",";	
				}
			});
			if(count<=0){
				alert("单据至少得有一条记录");
				return;
			}
			
			if(count>0){
				itemJson = itemJson.substring(0,itemJson.length-1);//去掉最后一个逗号
			}
			 itemJson=itemJson+"]";
			 
			 var json="{\"cmpid\":\""+cmpId_id+"\",\"cmpName\":\""+cmpName+"\",\"orderId\":\""+order_id+"\",\"cusComName\":\""+customerCompanyName
				 	+"\",\"cusName\":\""+customerName+"\",\"cusPhone\":\""+customerPhone+"\",\"orderNum\":\""+orderNum+"\",\"items\":"+itemJson+"}";
		 
			 function callBack(data){
				 if (data.code == -2){
					location.href = "${basePath}view/jsp/timer.jsp";
				}else if(data.code==0){
					window.parent.location.href="${basePath}order/list.do?m=${m}";
				}else if(data.code==-1){
					alert(data.msg);
				}else{
					alert("返回数据异常");
				}
			}
		  var url="${basePath}order/add.do?m=${m}";
		  var data={"p":json};
		  doAjax(url, data, callBack);
		}
	}
	function setLength(id,defaultLength,base){
		var length = $("#"+id).val().length;
		 
		 if(length>defaultLength)
		 {
		 	$("#"+id).css("width",(length*base)+"px");
		 }else{
		 	$("#"+id).css("width",(defaultLength*base)+"px");
		 }  
	}
</script>
</head>
<body onload="winOnLoad()">
	<script type="text/javascript">
		thissize();
	</script>
	<div class="outbox" id="printcontentid">
		<div class="boxcont">
			<p>
				<b id="cmpId"
					<c:if test="${order==null || (order.payStatus=='0' && order.orderStatus=='0')}"> data-toggle="modal" data-target="#mycmp"</c:if>>${order.companyName}</b>制版输出合同单
					<%-- <span id="orderNumId"> <c:if test="${order.orderNum!=''}"> NO:${order.orderNum} </c:if>  </span> --%>
			</p>
			<div class="tit">
				<label>客户名称</label> <input id="customerCompanyName"   maxlength="16" value="${order.customerCompanyName}" autocomplete="off"
					data-provide="typeahead" style="width: 120px;" onkeyup="setLength('customerCompanyName',8,13)" />
				<label>联系人:</label> <input id="customerName" maxlength="6" value="${order.customerName}"
					style="width: 60px;" autocomplete="off" data-provide="typeahead" />
				<label>电话:</label> <input id="customerPhone" maxlength="16"
					value="${order.customerPhone}" style="width: 1.0rem;"
					autocomplete="off" data-provide="typeahead" /> <label>开单日期:</label>

				<input readonly="readonly" style="width: 1.5rem;"
					value="<fn:formatDate value="${order.ctime}"  pattern="yyyy-MM-dd HH:mm"/>"
					id="orderDateId" /> <label>开单:</label> <input readonly="readonly"
					style="width: 1.0rem;" value="${order.userName}" id="orderNameId" />
					
				<label id="orderNumId"> <c:if test="${order.orderNum!=''}"> NO:${order.orderNum} </c:if>  </label>
			</div>
			<table border="" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th width="16%" style="text-align: center;">品名</th>
						<th style="text-align: center;">类型</th>
						<th style="text-align: center;">规格尺寸</th>
						<th style="text-align: center;">K/C/P</th>
						<th style="text-align: center;">数量</th>
						<th style="text-align: center;">单价(元)</th>
						<th style="text-align: center;">小计(元)</th>
						<th style="text-align: center;">备注</th>
					</tr>
				</thead>
				<tbody id="tbody_id">
					<c:if test="${order.items!=null}">
						<c:forEach items="${order.items}" var="s" varStatus="count">
							<tr>
								<td><input type="hidden" value="${s.id}"> <input
									<c:if test="${order.payStatus=='0' && order.orderStatus=='0'}">onblur="addLine(this);" </c:if>
									<c:if test="${order.payStatus!='0' || order.orderStatus!='0'}"> readonly="readonly"</c:if>
									maxlength="10" value="${s.name }" class="full_td" /></td>
								<td><input value="${s.tp}" maxlength="8" id="tp_${s.id}"
									<c:if test="${order.payStatus!='0' || order.orderStatus!='0'}"> readonly="readonly"</c:if>
									class="full_td" /></td>
								<!-- 类型 -->
								<td><input value="${s.spec }" id="spec_${s.id}"
									<c:if test="${order.payStatus!='0' || order.orderStatus!='0'}">  readonly="readonly"</c:if>
									maxlength="8" class="full_td" /></td>
								<!-- 规格尺寸 -->
								<td><input value="${s.KCP }" id="KCP_${s.id}"
									<c:if test="${order.payStatus!='0' || order.orderStatus!='0'}"> readonly="readonly"</c:if>
									maxlength="16" class="full_td" /></td>
								<td><input value="${s.num }"
									<c:if test="${order.payStatus!='0' || order.orderStatus!='0'}"> readonly="readonly"</c:if>
									maxlength="8"
									<c:if test="${order.payStatus!='0' || order.orderStatus!='0'}"> readonly="readonly"</c:if>
									<c:if test="${order.payStatus=='0' && order.orderStatus=='0'}">onchange="countTotal(this)"</c:if>
									class="full_td" /></td>
								<td><input
									value="<fn:formatNumber value="${s.price/100}" maxIntegerDigits="14" pattern="#0.##"></fn:formatNumber>"
									maxlength="8"
									<c:if test="${order.payStatus!='0' || order.orderStatus!='0'}"> readonly="readonly"</c:if>
									<c:if test="${order.payStatus=='0' && order.orderStatus=='0'}">onchange="countTotal(this)"</c:if>
									class="full_td" /></td>
								<td><input
									value="<fn:formatNumber value="${s.total/100}" maxIntegerDigits="14" pattern="#0.##"></fn:formatNumber>"
									<c:if test="${order.payStatus!='0' || order.orderStatus!='0'}"> readonly="readonly"</c:if>
									maxlength="8"
									<c:if test="${order.payStatus=='0' && order.orderStatus=='0'}">onchange="countTotal(this)"</c:if>
									class="full_td" /></td>
								<td><textarea rows="2" cols="32" class="textarea_full_td"
										style="resize: none; font-size: 15px;" <c:if test="${order.payStatus!='0' || order.orderStatus!='0'}"> readonly="readonly"</c:if>>${s.memo}</textarea></td>
							</tr>

							<c:if test="${order.length==(count.index+1) && order.length<6}">
								<!-- 最后一个，如果不足6个，自动补充一个 ，如果状态是已经结清或者作废，不能再添加了-->
								<c:if test="${order.payStatus=='0' && order.orderStatus=='0'}">
									<tr>
										<td><input type="hidden"> <input
											onblur="addLine(this);" maxlength="10" class="full_td" /></td>
										<!-- 类型 -->
										<td><input maxlength="8" class="full_td"  id="tp_${count.index+1}" /></td>
										<!-- 规格尺寸 -->
										<td><input maxlength="8" class="full_td"  id="spec_${count.index+1}"/></td>
										<!-- kcp -->
										<td><input maxlength="16" class="full_td" id="KCP_${count.index+1}" /></td>
										<td><input maxlength="8" onchange="countTotal(this)"
											class="full_td" /></td>
										<td><input maxlength="8" onchange="countTotal(this)"
											class="full_td" /></td>
										<td><input maxlength="8" onchange="countTotal(this)"
											class="full_td" /></td>
										<td><textarea rows="2" cols="32" class="textarea_full_td"
												style="resize: none; font-size: 15px;">${memo}</textarea></td>
									</tr>
								</c:if>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${order.items==null}">
						<tr>
							<td><input type="hidden" value="${s.id}"> <input
								onblur="addLine(this);" maxlength="10" value="${s.name }"
								class="full_td" /></td>
							<td><input value="${s.tp}" maxlength="8" id="tp_0" class="full_td" /></td>
							<!-- 类型 -->
							<td><input value="${s.spec }" maxlength="8"  id="spec_0" class="full_td" /></td>
							<!-- 规格尺寸 -->
							<td><input value="${s.KCP }"  id="KCP_0" maxlength="16" class="full_td" /></td>
							<td><input value="${s.num }" maxlength="8"
								onchange="countTotal(this)" class="full_td" /></td>
							<td><input value="${s.price }" maxlength="8"
								onchange="countTotal(this)" class="full_td" /></td>
							<td><input value="${s.total }" maxlength="8"
								onchange="countTotal(this)" class="full_td" /></td>
							<td><textarea rows="2" cols="32" class="textarea_full_td"
									style="resize: none; font-size: 15px;">${s.memo}</textarea></td>
						</tr>
					</c:if>
				</tbody>
			</table>

			<div class="tongji">
				<div>
					合计￥：<span id="totalcount"><fn:formatNumber
							value="${order.total/100}" maxIntegerDigits="14" pattern="#0.##"></fn:formatNumber></span>
				</div>

				<div>
					大写：<span id="digitUppercase"></span>
				</div>
				<div>${order.payMemo}</div>
				<div>${order.cancelMemo}</div>
			</div>
			<div id="memoId" style="display: none;">
				说明： <br>
				&nbsp;&nbsp;交货产品的图、文以校对样为准，因客户校对出现的错误，由客户负责；客户提供的原稿所涉及的内容，应无知识产权纠纷或违法内容，
				若发生有此类侵权行为或纠纷与本公司无关；为保证您的产品质量不出差错，最大限度避免您的经济损失，敬请打样确认无误后再投入印刷。若不打样就投入印刷，
				本公司不负责除胶片、CTP版以外的其它损失。 <br>
			</div>
			<div style="float: right;display: none;" id="getManId" >收货人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				签收人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>

			<div class="btnlist" id="btnlistid">
				<c:if test="${id==''|| id ==null }">
					<jyd:per hasPers="<%=request.getCookies()%>"
						needPer="c85b5056068c1f39a089f718fec077ba">
						<button onclick="sub()" class="no-print">提交保存</button>
					</jyd:per>
					<button
						onclick="javascript:window.parent.location.href='${basePath}order/list.do?m=${m}'"
						class="no-print">返回</button>
				</c:if>
				<c:if test="${id!=''&& id !=null }">
					<c:if test="${order.payStatus=='0' && order.orderStatus=='0'}">
						<jyd:per hasPers="<%=request.getCookies()%>"
							needPer="f7ae5b27126b3a1e4302b73b87ea6400">
							<button onclick="sub()" class="no-print">保存修改</button>
						</jyd:per>
					</c:if>
					<!-- 状态全部正常情况下，已经保存过了，才可能进行如下操作，需要判断每个子项是否都有id，有才能做如下操作 -->
					<jyd:per hasPers="<%=request.getCookies()%>"
						needPer="26b68a76e59b57d0d6370eaf6a5974de">
						<button id="btn_has_pay_id" onclick="hasPay()"
							<c:if test="${order.payStatus=='1' || order.orderStatus=='1'}"> style="display: none;" </c:if>
							class="no-print">现金结清</button>
						<button id="btn_no_pay_id" onclick="noPay()"
							<c:if test="${order.payStatus=='2' || order.payStatus=='1' || order.orderStatus=='1'}"> style="display: none;" </c:if>
							class="no-print">记账</button>
					</jyd:per>

					<jyd:per hasPers="<%=request.getCookies()%>"
						needPer="5f096c3952a7ef54041c5ac33fdf56b0">
						<button id="btn_print_id" onclick="printOrder()"
							<c:if test="${order.orderStatus=='1'}"> style="display: none;" </c:if>
							class="no-print">打印</button>
					</jyd:per>
				</c:if>
			</div>
		</div>
		<div class="instruct">第一联 ：存根 第二联 ：客户 第三联 ：财务</div>
	</div>
	<!-- 公司 -->
	<div id="mycmp" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" class="modal fade"
		style="display: none; padding-top: 2rem;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" id="cmpcontentId">
					<button type="button" data-dismiss="modal" aria-hidden="true"
						class="close">×</button>
					<h4 id="myModalLabel" class="modal-title">请点击选择</h4>
				</div>

				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-primary">关&nbsp;&nbsp;闭</button>
				</div>
			</div>
		</div>
	</div>
	<input id="cmpId_id" type="hidden">
	<input id="lx_id" type="hidden">
	<input id="dw_id" type="hidden">
	<input id="zk_id" type="hidden">
	<input id="gg_id" type="hidden">
	<input id="canEditId" type="hidden" value="${order.payStatus=='0' && order.orderStatus=='0'}" >
	<input id="order_id" type="hidden" value="${id}">
	<div id="loading" class="panel-body whirl ringed loading"
		style="display: none;"></div>
</body>
</html>