<%@ page language="java" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" pageEncoding="UTF-8"  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" href="${pageContext.request.contextPath}/lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加账单 - 账单管理</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-add">
	<input type="hidden" id="employeeId" value="${sessionScope.employee_id}">
	<input type="hidden" id="employeeNum" value="${sessionScope.employee_num}">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账单名：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="账单名" maxplaceh="20" id="billName" name="billName">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账单类型：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" maxlength="6" placeholder="账单类型" id="billType" name="billType">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账单状态：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<select id="state">
				<option value="0">入库</option>
				<option value="1">出库</option>
				<option value="-1">停用入库</option>
				<option value="-2">停用出库</option>
			</select>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账单项目：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" maxlength="6" placeholder="账单项目" id="billOption" name="billOption">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账单金额：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" maxlength="6" placeholder="账单金额" id="billMon" name="billMon">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">备注：</label>
		<div class="formControls col-xs-8 col-sm-9">
		<!-- onKeyUp="$.Huitextarealength(this,800)" -->
			<textarea id="billRemarks" name="billRemarks" cols="" rows="" class="textarea"  placeholder="说点什么...800个字符以内" dragonfly="true" ></textarea>
			<!-- <p class="textarea-numberbar"><em class="textarea-length">0</em>/800</p> -->
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" id="bill_save_submit" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	var id=GetQueryString('id');
	if(id!=null){
	$.ajax({
           type: "post",
           url: "${pageContext.request.contextPath}/bill/loadingupdatebill.action",
           dataType: 'json',
           contentType: "application/json;charset=utf-8",
           data: JSON.stringify(id),
           success: function (data1) {
               if(data1!=null){
               		$('#billName').val(data1.billName);
               		$('#billOption').val(data1.billOption);
               		$('#billType').val(data1.billType);
               		$('#billMon').val(data1.billMon);
               		$('#billRemarks').val(data1.billRemarks);
               		$("#state option[value='"+data1.billState+"']").attr("selected","selected");
               }
           },
           error: function (XMLHttpRequest, textStatus, errorThrown) {
               console.log(XMLHttpRequest.status);
               console.log(XMLHttpRequest.readyState);
               console.log(textStatus);
           }
       });
       
       
	}
	
	
$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-add").validate({
		rules:{
			billName:{
				required:true,
				minlength:1,
				maxlength:20
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid"
	});
})

$("#billRemarks").on("input propertychange", function () {
        var $this = $(this),
                _val = $this.val(),
                count = "";
        if (_val.length > 800) {
            $this.val(_val.substring(0, 800));
        }
        count = 800 - $this.val().length;
        $("#text-count").text(count);
    });


window.onload=function(){
	var id=GetQueryString('id');
	if(id!=null){       
       $('#bill_save_submit').click(function(){
       		var billName=$('#billName').val();
       		var billOption=$('#billOption').val();
       		var billType=$('#billType').val();
       		var billMon=$('#billMon').val();
       		var billRemarks=$('#billRemarks').val();
       		var billState=$('#state').val();
       		var employeeId=$('#employeeId').val();
       		var employeeNum=$('#employeeNum').val();
       		
       		if(isEmpty(billName)){
       			alert("请输入账单名!");
       			return;
       		}else if(billName.lenght>20){
       			alert("账单名不能长于20!");
       			return;
       		}else if(isEmpty(billOption)){
       			alert("请输入账单项目!");
       			return;
       		}else if(isEmpty(billType)){
       			alert("请输入账单类型!");
       			return;
       		}else if(isEmpty(billMon)){
       			alert("请输入账单金额!");
       			return;
       		}else if(!/^(-?\d+)(\.\d+)?$/.test(billMon)){
       			alert("请输入正确金额!");
       			return;
       		}
       		
       		if(!isEmpty(billRemarks)&&billRemarks.length>800){
       			alert("备注不能超过800字!");
       			return;
       		}
       		
       		
			$.ajax({
		           type: "post",
		           url: "${pageContext.request.contextPath}/bill/updatebill.action",
		           dataType: 'json',
		           data: JSON.stringify({"id":id,"billName":billName,"billOption":billOption,
		           "billType":billType,"billMon":billMon,"billRemarks":billRemarks,
		           "employeeId":employeeId,"employeeNum":employeeNum,"billState":billState}),
		           contentType: "application/json;charset=utf-8",
		           success: function (data1) {
		               if(data1==1){
		               		layer.msg('已修改!',{icon:1,time:1000});
		               		window.open("${pageContext.request.contextPath}/bill/billList.do",target="mainFrame");
		               }else{
		              		layer.msg('修改失败!',{icon:1,time:1000});
		               }
		           },
		           error: function (XMLHttpRequest, textStatus, errorThrown) {
		               console.log(XMLHttpRequest.status);
		               console.log(XMLHttpRequest.readyState);
		               console.log(textStatus);
		           }
		       });
       });
       
	}else{
		$('#bill_save_submit').click(function(){
       		var billName=$('#billName').val();
       		var billOption=$('#billOption').val();
       		var billType=$('#billType').val();
       		var billMon=$('#billMon').val();
       		var billRemarks=$('#billRemarks').val();
       		var billState=$('#state').val();
       		var employeeId=$('#employeeId').val();
       		var employeeNum=$('#employeeNum').val();
       		
       		if(isEmpty(billName)){
       			alert("请输入账单名!");
       			return;
       		}else if(billName.lenght>20){
       			alert("账单名不能长于20!");
       			return;
       		}else if(isEmpty(billOption)){
       			alert("请输入账单项目!");
       			return;
       		}else if(isEmpty(billType)){
       			alert("请输入账单类型!");
       			return;
       		}else if(isEmpty(billMon)){
       			alert("请输入账单金额!");
       			return;
       		}else if(!/^(-?\d+)(\.\d+)?$/.test(billMon)){
       			alert("请输入正确金额!");
       			return;
       		}
       		
       		if(!isEmpty(billRemarks)&&billRemarks.length>800){
       			alert("备注不能超过800字!");
       			return;
       		}
       		
       		
			$.ajax({
		           type: "post",
		           url: "${pageContext.request.contextPath}/bill/addbill.action",
		           dataType: 'json',
		           data: JSON.stringify({"id":id,"billName":billName,"billOption":billOption,
		           "billType":billType,"billMon":billMon,"billRemarks":billRemarks,
		           "employeeId":employeeId,"employeeNum":employeeNum,"billState":billState}),
		           contentType: "application/json;charset=utf-8",
		           success: function (data1) {
		               if(data1==1){
		               		layer.msg('已添加!',{icon:1,time:1000});
		               		window.open("${pageContext.request.contextPath}/bill/billList.do",target="mainFrame");
		               }else{
		              		layer.msg('添加失败!',{icon:1,time:1000});
		               }
		           },
		           error: function (XMLHttpRequest, textStatus, errorThrown) {
		               console.log(XMLHttpRequest.status);
		               console.log(XMLHttpRequest.readyState);
		               console.log(textStatus);
		           }
		       });
       });
	}
	
}
//判断是否为空,为空返回true
function isEmpty(s){
	if(!s){
		return true;
	}
	if(s.replace(/\s+/g,"") == ''){
		return true;
	}
	if(s == null){
		return true;
	}
	return false;
}
/* 封装GetQueryString获取url */
function GetQueryString(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null)
                    return decodeURI(r[2]);
                return null;
}


</script> 

<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>