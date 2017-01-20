<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common_base.jsp"></jsp:include>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
   <meta name="description" content="">
   <meta name="keywords" content="">
   <meta name="author" content="">
   <title>添加客户</title>
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
		var id = $("input[name='customer.id']").val();
		var companyName = $("input[name='customer.companyName']").val();
		var name = $("input[name='customer.name']").val();
		var phone = $("input[name='customer.phone']").val();
		var address = $("input[name='customer.address']").val();

		if (isEmpty(companyName)) {
			alert("公司名称不能为空");
			return;
		}

		if (isEmpty(name)) {
			alert("联系人不能为空");
			return;
		}

		if (isEmpty(phone)) {
			alert("联系人电话不能为空");
			return;
		}

	/* 	if (isEmpty(address)) {
			alert("联系人地址不能为空");
			return;
		} */

		var url = "${basePath}cus/add.do?m=${m}";
		var data = {
			"companyName" : companyName,
			"name" : name,
			"phone" : phone,
			"address" : address,
			"id" : id,
		}
		
		function callBack(data) {
			if (data.code == -2){
				location.href = "${basePath}view/jsp/timer.jsp";
			}else if (data.code != 0) {
				alert(data.msg);
				return;
			} else {
				location.href = "${basePath}cus/list.do?m=${m}";
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
      <aside class="aside">
         <!-- START Sidebar (left)-->
         <nav class="sidebar">
           <jsp:include  page="../left.jsp"/> 
         </nav>
         <!-- END Sidebar (left)-->
      </aside>
      <!-- End aside-->
      <!-- START aside-->
      <aside class="offsidebar">
         <!-- START Off Sidebar (right)-->
         <!-- END Off Sidebar (right)-->
      </aside>
      <!-- END aside-->
      <!-- START Main section-->
      <section>
         <!-- START Page content-->
         <div class="content-wrapper">
            <!-- START panel-->
            <!-- END panel-->
            <!-- START row-->
            <!-- END row-->
            <!-- START panel-->
            <div class="panel panel-default">
               <div class="panel-body">
                  	<input type="hidden" name="customer.id" value="${customer.id}">
                      <fieldset>
                        <div class="form-group">
                           <label for="input-id-1" class="col-sm-2 control-label">公司名称：</label>
                           <div class="col-sm-10">
                              <input type="text" class="form-control" maxlength="16" value="${customer.companyName}" name="customer.companyName">
                           </div>
                        </div>
                     </fieldset>
                      <fieldset>
                        <div class="form-group">
                           <label for="input-id-1" class="col-sm-2 control-label">联系人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                           <div class="col-sm-10">
                              <input type="text" class="form-control" maxlength="8" value="${customer.name}" name="customer.name">
                           </div>
                        </div>
                     </fieldset>
                      <fieldset>
                        <div class="form-group">
                           <label for="input-id-1" class="col-sm-2 control-label">手机：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                           <div class="col-sm-10">
                              <input type="text" class="form-control" maxlength="16" value="${customer.phone}" name="customer.phone">
                           </div>
                        </div>
                     </fieldset>
                      <fieldset>
                        <div class="form-group">
                           <label for="input-id-1" class="col-sm-2 control-label">地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                           <div class="col-sm-10">
                              <input type="text" class="form-control" maxlength="30" value="${customer.address}" name="customer.address">
                           </div>
                        </div>
                     </fieldset>
                      <div class="form-group" style="text-align: center;">
                           <button type="button" onclick="save();" class="btn btn-info">保&nbsp;&nbsp;存</button>
                        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
                        	<button type="button" onclick="javascript:location.href='${basePath}cus/list.do?m=${m}'" class="btn btn-danger">取&nbsp;&nbsp;消</button>
                        </div>
               </div>
            </div>
            <!-- END panel-->
         </div>
         <!-- END Page content-->
      </section>
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