var mailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;// 验证邮箱
var picReg = /\.(bmp|BMP|jpg|png|JPG|PNG)$/;// 验证图片
var digtReg = /^[0-9]*$/;
var digtReg2 =  /^\+?[1-9]\d*$/;
var mobileReg=/^1[34578]\d{9}$/;   
var ex = /^\d+$/;
var urlReg = /(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/g;
var timeReg = /^([0-1]?[0-9]|2[0-3])[\/|\:]([0-5]?[0-9])$/;// 验证24小时制的时间,如：22:00
function getCookieVal(key) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(key + "=");
		if (c_start != -1) {
			c_start = c_start + key.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
	return ""
}
/**
 * 写cookie
 * 
 * @param key
 * @param value
 * @param response
 * @author susan
 */
function setCookie(key, value) {
	var exp = new Date();
	exp.setTime(exp.getTime() + 2 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
	document.cookie = key + "=" + value;
}
/*******************************************************************************
 * 发ajax请求，返回结果
 * 
 * @param url
 * @param data
 * @param callBack
 * @param showLoading
 * @param showMsg
 * @param refreshUrl
 */
function doAjax(url, data, callBack) {
	$("#loading").show();
	var doResult = function(data) {
		$("#loading").hide();
		if (isNotNull(callBack)) {
			callBack(data);
		}
	};

	$.ajax({
		type : "post",
		url : url,
		data : data,
		dataType : "json",
		xhrFields: {
            withCredentials: true
		},
		success : function(data) {
			doResult(data);
		},
		error : function(data) {
			doResult(data);
		}
	});
}

function submitFormAsAjax(data) {
	var url = $("#" + data.formId).attr("action");
	var param = getAllParamFromForm(data.formId, data.param);
	doAjax(url, param, data.callBack, data.showLoading, data.showMsg,
			data.reload);
}

function refreshPageWithResMsgKey(url, resMsgKey) {
	var resMsgKeyParam = "";
	if (isStrNotEmpty(resMsgKey) && url.indexOf("resMsgKey=") == -1) {
		var split = "&";
		if (url.indexOf("?") == -1) {
			split = "?";
		} else if (url.indexOf("?") == url.length - 1
				|| url.indexOf("&") == url.length - 1) {
			split = "";
		}
		resMsgKeyParam = split + "resMsgKey=" + resMsgKey;
	}
	self.location.href = url + resMsgKeyParam;
}

function getAllParamFromForm(formId, data) {
	if (isNull(data)) {
		data = {};
	}
	$("#" + formId).find("input").each(
			function() {
				if (isStrNotEmpty(this.name)) {
					if ((this.type == "radio" || this.type == "checkbox")
							&& this.checked != true) {
						return;
					}

					var preVal = "";
					if (isStrNotEmpty(data[this.name])) {
						preVal = data[this.name] + ",";
					}
					data[this.name] = preVal + this.value;
				}
			});
	$("#" + formId).find("textarea").each(function() {
		if (isStrNotEmpty(this.name)) {
			var preVal = "";
			if (isStrNotEmpty(data[this.name])) {
				preVal = data[this.name] + ",";
			}
			data[this.name] = preVal + this.value;
		}
	});
	return data;
}

/*******************************************************************************
 * 显示服务端返回的信息。
 * 
 * @param data
 * @param displayData
 *            如果为true，并且data为String类型，直接显示data.
 */
function showResultMsg(data, displayData) {
	var resMsg = $("#resMsg_id");
	if (isNotNull(resMsg)) {
		var code = data.code;
		if (isNotNull(code) && isNotNull(resMsgMap)
				&& isNotNull(resMsgMap[code])) {
			resMsg.html(resMsgMap[code]);
		} else if (displayData == true && isString(data)) {
			resMsg.html(data);
		}
	}
}

function isNull(data) {
	if (typeof (data) != "undefined" && data != null) {
		return false;
	}

	return true;
}

function isNotNull(data) {
	return !isNull(data);
}

function isStrEmpty(data) {
	if (isNull(data) || $.trim(data) == "") {
		return true;
	}

	return false;
}

function isStrNotEmpty(data) {
	return !isStrEmpty(data);
}

function clearResultMsg() {
	showResultMsg("", true);
}

function setReloadFlagVal(value) {
	if (isNotNull(reloadFlag)) {
		reloadFlag.val(value);
	}
}

/*******************************************************************************
 * 判断Ajax返回是否成功。
 * 
 * @param data
 */
function isSuccess(data) {
	var code = data.code;
	if (isNotNull(code) && code == "wxc00000") {
		return true;
	}

	return false;
}

/*******************************************************************************
 * 判断数据是否是String类型
 * 
 * @param str
 */
function isString(str) {
	return (typeof (str) == 'string') && str.constructor == String;
}

/*******************************************************************************
 * 字符非空
 * 
 * @param cont
 * @returns {Boolean}
 */
function isNotEmpty(cont) {
	if (cont == 'null' || cont == null || cont == "undefined"
			|| cont == undefined || $.trim(cont) == "")
		return false;
	else
		return true;
}

/*******************************************************************************
 * 字符为空
 * 
 * @param cont
 * @returns {Boolean}
 */
function isEmpty(cont) {
	if ($.trim(cont) == "" || cont == 'null' || cont == null
			|| cont == "undefined" || cont == undefined)
		return true;
	else
		return false;
}
/*******************************************************************************
 * 展示操作错误信息
 * 
 * @param divId
 * @param infor
 */
function showInfor(divId, infor) {
	$("#" + divId).html(infor);
}
/*******************************************************************************
 * 展示错误信息，mini 秒后跳转到url
 * 
 * @param divId
 * @param infor
 * @param mini
 * @param url
 */
function showInfor(divId, infor, mini, url) {
	$("#" + divId).html(infor);
	setTimeout(function() {
		window.location.href = "kwd/list.do?m=wechatMgt";
	}, 250);
}
/*******************************************************************************
 * 根据name赋值给input标签
 * 
 * @param data
 */
function cvt2Input(data) {
	for ( var index in data)
		if ($("[name='" + index + "']").length > 0)
			$("[name='" + index + "']").val(data[index]);
}

/*******************************************************************************
 * 显示图片
 * 
 * @param imgId
 * @param src
 */
function cvt2Img(imgId, src) {
	if ($("#" + imgId).length > 0)
		$("#" + imgId).attr("src", src);
}
/**
 * 显示一个标签
 * 
 * @param id
 */
function show(id) {
	$("#" + id).show();
}
/**
 * 隐藏一个标签
 * 
 * @param id
 */
function hide(id) {
	$("#" + id).hide();
}
/*******************************************************************************
 * 添加一个样式
 * 
 * @param id
 * @param className
 */
function addClass(id, className) {
	$("#" + id).addClass(className);
}
/**
 * 删除一个样式
 * 
 * @param id
 * @param className
 */
function removeClass(id, className) {
	$("#" + id).removeClass(className);
}

/**
 * 修改style的一个属性
 * 
 * @param id
 * @param className
 */
function addStyle(id, name, val) {
	$("#" + id).css(name, val);
}
/**
 * 返回value值
 * 
 * @param id
 * @returns
 */
function fileNum(id) {
	return $("#" + id).val();
}

/**
 * set value值
 * 
 * @param id
 * @returns
 */
function setVal(id, val) {
	return $("#" + id).val(val);
}

function getVal(id) {
	return $("#" + id).val();
}

function setAttr(id, attr, val) {
	return $("#" + id).attr(attr, val);
}

function getAttr(id, attr) {
	return $("#" + id).attr(attr);
}

function handleCallbackCode(code) {
	if("wxc0000-2"==code){
		alert("操作失败！");
	}else if("wxc00004"==code){
		alert("系统参数错误！");
	}else if("wxc00007"==code){
		alert("参数无效！");
	}else if("wxc00008"==code){
		alert("没有权限进行此操作！");
	}
}

/**
 * 过滤空的值
 * 
 * @param name
 * @returns {Array}
 */
function getValByName(name) {
	var array = new Array();
	$("input[name=" + name + "]").each(function() {
		var val = $(this).val();
		if (isNotEmpty(val))
			array.push(val);
	})
	return array;
}
/**
 * 不过滤空的值
 * 
 * @param name
 * @returns {Array}
 */
function getValsByName(name) {
	var array = new Array();
	$("input[name=" + name + "]").each(function() {
		var val = $(this).val();
		if (!isNotEmpty(val))
			val = "-";
		array.push(val);
	})
	return array;
}

function setHtml(id, html) {
	return $("#" + id).html(html);
}

function getHtml(id) {
	return $.trim($("#" + id).html());
}
function removeHtml(id) {
	$("#" + id).remove();
}

/**
 * 
 * @param checked
 */
function getCheck(id) {
	return $("#" + id).prop("checked");
}
/**
 * 
 * @param id
 * @param checked
 */
function setCheck(id, checked) {
	$("#" + id).attr("checked", checked);
}
/*******************************************************************************
 * 验证邮箱
 * 
 * @param mailStr
 * @returns {Boolean}
 */
function isMail(mailStr) {
	if (mailReg.test(mailStr))
		return true;
	else
		return false;
}

/**
 * 验证图片
 * 
 * @param picStr
 * @returns {Boolean}
 */
function isPic(picStr) {
	if (picReg.test(picStr))
		return true;
	else
		return false;
}

/**
 * 是否是数字
 * 
 * @param digit
 * @returns {Boolean}
 */
function isDigit(digit) {
	if (digtReg.test(digit))
		return true;
	else
		return false;
}

/**
 * 参数大于0
 * @param digit2
 * @returns {Boolean}
 */
function isDigit2(digit2){
	if (digtReg2.test(digit2))
		return true;
	else
		return false;
}

/**
 * 验证是否是url
 * 
 * @param url
 * @returns {Boolean}
 */
function isTime(time) {
	if (timeReg.test(time))
		return true;
	else
		return false;
}
/**
 * 跳转到url
 * 
 * @param url
 * @param queryStr
 */
function goUrl(url, queryStr) {
	window.location.href = url + (isNotEmpty(queryStr) ? "&" + queryStr : "");
}

function goUrlWithPara(url) {
	window.open(url + "&sid=" + getCookieVal("sid") + "&role="
			+ getCookieVal("role"));
}
/**
 * 交替显示class
 * 
 * @param id
 * @param className
 */
function toggleClass(id, className) {
	$("#" + id).toggleClass(className);
}
/**
 * 提交form表单
 * 
 * @param id
 */
function submitForm(id) {
	setVal("currentPageIndexid", 0);
	$("#" + id).submit();
}
/**
 * 定时跳转
 * 
 * @param jumpUrl
 * @param queryStr
 * @param time
 */
function timerJump(jumpUrl, queryStr, time) {
	setTimeout(function() {
		goUrl(jumpUrl, queryStr);
	}, time);
}
/**
 * 定时跳转
 * 
 * @param jumpUrl
 * @param queryStr
 * @param time
 */
function parentsTimerJump(jumpUrl, time) {
	setTimeout(function() {
		window.parent.location.href = jumpUrl;
	}, time);
}

function showLoding() {
	$('#loading').show();
}
function hideLoding() {
	$('#loading').hide();
}
/*******************************************************************************
 * 判断是否是数字
 * 
 * @returns {Boolean}
 */
function isNum(str) {
	if (ex.test(str))
		return true;
	else
		return false;
}

function setLableValue(parentId, paramId, id) {
	$("#" + parentId).find("a").each(function() {
		var className = $(this).attr("class");
		if (className == 'cur')
			$(this).removeClass("cur");
	});

	$("#" + id).addClass("cur");
	setVal(paramId, $("#" + id).attr("value"));
}

function selectAll(allSelectId, tableId, checkboxName) {
	var allSelect = $("#allSelectid");
	var table = $("table");
	if (isStrNotEmpty(allSelectId) && $("#" + allSelectId).length > 0) {
		allSelect = $("#" + allSelectId);
	}
	if (isStrNotEmpty(tableId) && $("#" + tableId).length > 0) {
		table = $("#" + tableId);
	}

	var checkboxNameLimit = "";
	if (isStrNotEmpty(checkboxName)) {
		checkboxNameLimit = "[name='" + checkboxName + "']";
	}
	var checked = allSelect.prop("checked");
	if (checked == "checked" || checked) {
		table.find(":checkbox" + checkboxNameLimit).prop("checked", true);
	} else {
		table.find(":checkbox" + checkboxNameLimit).prop("checked", false);
	}
}

function selectAll() {
	var checked = $("#allSelectid").prop("checked");
	if (checked == "checked" || checked) {
		$("input[type='checkbox']").attr("checked", true);
	} else {
		$("input[type='checkbox']").attr("checked", false);
	}
}

function selectRed(obj) {
	var check = $(obj).prop("checked");

	if (check == "checked" || check) {
		$(obj).attr("checked", true);
	} else {
		$("#allSelectid").attr("checked", false);
		$(obj).attr("checked", false);
	}
}

function clearSelect(allSelectId, tableId) {
	var allSelect = $("#allSelectid");
	var table = $("table");
	if (allSelectId != undefined && $("#" + allSelectId).length > 0) {
		allSelect = $("#" + allSelectId);
	}
	if (tableId != undefined && $("#" + tableId).length > 0) {
		table = $("#" + tableId);
	}

	allSelect.attr("checked", false);
	table.find(":checkbox").attr("checked", false);
}

function setInputStatus(inputId, status) {
	$("#" + inputId).attr("disabled", status);
}

/* 返回当前页码,用于页面操作后停在当前页 */
function getCurPage() {
	var cur = getVal("currentPageIndexid") - 1;
	return cur;
}

/* 回车事件 */
function getKey() {
	var event = arguments.callee.caller.arguments[0] || window.event;
	if (event.keyCode == 13) {
		$("#btnSearch").click();
	}
}

var qiniu_png_filters = {
//		prevent_duplicates : true,
		mime_types : [ {
			title : "Image files",
			extensions : "png"
		} 
		]
	};

var qiniu_pic_filters = {
	prevent_duplicates : true,
	mime_types : [ {
		title : "Image files",
		extensions : "jpg,gif,png"
	} // 只允许上传jpg，gif、png图片
	]
}

var qiniu_zip_apk_filters = {
	prevent_duplicates : true,
	mime_types : [ {
		title : "Zip files",
		extensions : "zip,apk"
	} ]
}

var qiniu_video_filters = {
	prevent_duplicates : true,
	mime_types : [ {
		title : "Video files",
		extensions : "flv,mp4"
	} ]
}
var qiniu_p12_filters = {
		prevent_duplicates : true,
		mime_types : [ {
			title : "p12 files",
			extensions : "p12"
		} ]
	}
function UploadProgress(file){
	$('#uploadProcessMsg_id').show();
	$('#uploadProcessMsg_id').html(file.percent + "%" +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+(file.speed/1024).toFixed(2)  +" KB/s");
}

/*判断是否包含中文*/
function isContainChinese(str){
	if(isEmpty(str)){
		return;
	}
	if (/[\u4E00-\u9FA5]/i.test(str)) {
	   return true;
	}else{
	   return false;
	}
}

/**创建并执行一个剩余时间显示
 * @param element 作用元素,用于显示
 * @param key 一个唯一值,用于定时请区分
 * @param initTime 初始时间
 * @param t 每次跳动间隔时间
 * 
 * @author dave
 */
function createTimer(element,key,initTime,t){
	/*debugger;*/
	initTimer(element,key, initTime);
	doTimingTasks(element,key,t);
}

/*定时器: 用户显示用户需要等待多少秒*/
//初始化定时器的时间  (key,id) 用户保存定时任务起始时间
function initTimer(element,key,initTime){
	$("#"+element).html(initTime/1000);
	$("#"+element).show();
	sessionStorage.setItem(key+"_taskInitTime",initTime);
}
//执行定时任务
function doTimingTasks(element,key,t){
	var tid = setInterval(function(){
		timingTasks(element,key,t);
	},t);
	sessionStorage.setItem(key+"_timeTaskId",tid);
}
//定时任务: element 更新元素id, key:保存剩余时长,t 每次减少的时间间隔 
function timingTasks(element,key,t){
	/*debugger;*/
	var remainingTime = sessionStorage.getItem(key+"_taskInitTime");
	if(isEmpty(remainingTime)){
		return;
	}
	remainingTime = Number(remainingTime) - Number(t);
	//如果超时清除定时任务
	if(remainingTime<=0){
		stopTimingTasks(element,key);
	}
	sessionStorage.setItem(key+"_taskInitTime",remainingTime);
	$("#"+element).html(remainingTime/1000);
}
//结束定时任务
function stopTimingTasks(element,key){
	var tid = sessionStorage.getItem(key+"_timeTaskId");
	clearTimeout(tid);
	//隐藏时间显示
	$("#"+element).hide();
}

////滚动到指定的id
//function scrollId() {
//  $('body,html').animate({scrollTop:0},500);
//    return false;
//}

//滚动到底部
var delay = 10;//in milliseconds
var scroll_amount = 10;// in pixels
var interval;
function scroller() {
    var old = document.body.scrollTop;//保存当前滚动条到顶端的距离
    document.body.scrollTop += scroll_amount;//让滚动条继续往下滚动
    if (document.body.scrollTop == old) {//到底部后就无法再增加scrollTop的值
        clearInterval(interval);
       
    }
}
function scrollToBottom()
{
  interval = setInterval("scroller()",delay);
}

Array.prototype.remove=function(dx)
{
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
            this[n++]=this[i]
        }
    }
    this.length-=1
} 


Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.removeVal = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

function delQiniuFile(filePath){
	if(isEmpty(filePath)){
		return;
	}
	var url = "http://" + window.location.host + "/server/comm/delQiniuFile.do";
	var data = {
			"filePath" : filePath
	};
	doAjax(url, data, null);
}