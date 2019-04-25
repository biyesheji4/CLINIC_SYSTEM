<%@ page language="java" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" pageEncoding="UTF-8"  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加项目 - 项目管理</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-add">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目名：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="项目名" maxplaceh="20" id="projectsName" name="projectsName">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目简介：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" value="" maxplaceh="800" placeholder="项目简介" id="projectsContent" name="projectsContent">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属科室：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<select style="width:240px" class="form-control" id="department">
	  				
			</select>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目价格：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" value="" maxplaceh="800" placeholder="项目价格" id="projectsMoney" name="projectsMoney">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>项目编码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" maxlength="6" placeholder="项目编码（6位数字）" id="projectsNum" name="projectsNum">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">备注：</label>
		<div class="formControls col-xs-8 col-sm-9">
		<!-- onKeyUp="$.Huitextarealength(this,800)" -->
			<textarea id="projectsRemarks" name="projectsRemarks" cols="" rows="" class="textarea"  placeholder="说点什么...800个字符以内" dragonfly="true" ></textarea>
			<!-- <p class="textarea-numberbar"><em class="textarea-length">0</em>/800</p> -->
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" id="projects_save_submit" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script src="${pageContext.request.contextPath}/vendor/sweet-alert.min.js"></script>
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
           url: "${pageContext.request.contextPath}/project/loadingupdateproject.action",
           dataType: 'json',
           contentType: "application/json;charset=utf-8",
           data: JSON.stringify(id),
           success: function (data) {
           		var data1=data.data1;
           		var data2=data.data2;
           	   	if(data1!=null){
           	   		for(var i=0;i<data1.length;i++){
           	   			if(data1[i].id==data2.departmentId){
           	   				$("#department").append('<option selected="selected" value='+data1[i].id+'>'+data1[i].name+'</option>');
           	   			}else{
           	   				$("#department").append('<option value='+data1[i].id+'>'+data1[i].name+'</option>');
           	   			}
           	   			
           	   		}
           	   	}
               	if(data2!=null){
               		$('#projectsName').val(data2.projectsName);
               		$('#projectsContent').val(data2.projectsContent);
               		$('#projectsNum').val(data2.projectsNum);
               		$('#projectsRemarks').val(data2.projectsRemarks);
               		$("#projectsMoney").val(data2.projectMoney);
               }
           },
           error: function (XMLHttpRequest, textStatus, errorThrown) {
               console.log(XMLHttpRequest.status);
               console.log(XMLHttpRequest.readyState);
               console.log(textStatus);
           }
       });
       
       
	}else{
		$.ajax({
           type: "post",
           url: "${pageContext.request.contextPath}/project/loadingaddproject.action",
           dataType: 'json',
           contentType: "application/json;charset=utf-8",
           data: JSON.stringify(""),
           success: function (data) {
           		var data1=data.data1;
           	   	if(data1!=null){
                    var text = "<option value=''>--请选择--</option>";
           	   		for(var i=0;i<data1.length;i++){
                        text+="<option value="+data1[i].id+">"+data1[i].name+"</option>"
           	   			// $("#department").append('<option value='+data1[i].id+'>'+data1[i].name+'</option>')
           	   		}
                    $("#department").html(text);
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
			projectsName:{
				required:true,
				minlength:1,
				maxlength:20
			},
			projectsContent:{
				required:true,
				minlength:1,
				maxlength:800
			},
			projectsNum:{
				required:true,
				minlength:6,
				maxlength:6
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid"
	});
})

$("#projectsRemarks").on("input propertychange", function () {
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
       $('#projects_save_submit').click(function(){
       		var projectsName=$('#projectsName').val();
       		var projectsContent=$('#projectsContent').val();
       		var projectsNum=$('#projectsNum').val();
       		var projectsRemarks=$('#projectsRemarks').val();
       		var projectsMoney=$('#projectsMoney').val();
       		var departmentId=$('#department').val();
       		
       		if(isEmpty(projectsName)){
       			alert("请输入项目名!");
       			return;
       		}else if(projectsName.lenght>20){
       			alert("项目名不能长于20!");
       			return;
       		}else if(isEmpty(projectsContent)){
       			alert("请输入项目简介!");
       			return;
       		}else if(projectsContent>800){
       			return;
       		}else if(isEmpty(projectsNum)){
       			alert("请输入项目编码!");
       			return;
       		}else if(!/^[0-9]{6}$/.test(projectsNum)){
       			alert("项目编码只能为六位数字!");
       			return;
       		}
       		else if(!/^(-?\d+)(\.\d+)?$/.test(projectsMoney)){
       			alert("请输入正确的金额!");
       			return;
       		}
       		
       		if(!isEmpty(projectsRemarks)&&projectsRemarks.length>800){
       			alert("备注不能超过800字!");
       			return;
       		}
       		
       		
			$.ajax({
		           type: "post",
		           url: "${pageContext.request.contextPath}/project/updateproject.action",
		           dataType: 'json',
		           data: JSON.stringify({"id":id,"projectsName":projectsName,"projectsContent":projectsContent,
		           "projectsNum":projectsNum,"projectsRemarks":projectsRemarks,"projectMoney":projectsMoney,"departmentId":departmentId}),
		           contentType: "application/json;charset=utf-8",
		           success: function (data1) {
		               if(data1==1){
		               		// layer.msg('已修改!',{icon:1,time:1000});
                           swal({
                               title: "",      //弹出框的title
                               text: "已修改!",   //弹出框里面的提示文本
                               type: "success",        //弹出框类型
                               showCancelButton: false, //是否显示取消按钮
                               confirmButtonColor: "#007bff",//确定按钮颜色
                               confirmButtonText: "确定",//确定按钮上面的文档
                               closeOnConfirm: false
                           },function(){
                               window.open("${pageContext.request.contextPath}/project/projectlist.do",target="mainFrame");
                           });

		               }else{
		              		// layer.msg('修改失败!',{icon:1,time:1000});
                           swal("",
                               "修改失败！",
                               "error");
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
		$('#projects_save_submit').click(function(){
       		var projectsName=$('#projectsName').val();
       		var projectsContent=$('#projectsContent').val();
       		var projectsNum=$('#projectsNum').val();
       		var projectsRemarks=$('#projectsRemarks').val();
       		var projectsMoney=$('#projectsMoney').val();
       		var departmentId=$('#department').val();
       		
       		if(isEmpty(projectsName)){
       			alert("请输入项目名!");
       			return;
       		}else if(projectsName.lenght>20){
       			alert("项目名不能长于20!");
       			return;
       		}else if(isEmpty(projectsContent)){
       			alert("请输入项目简介!");
       			return;
       		}else if(projectsContent>800){
       			return;
       		}else if(departmentId==""){
                alert("请选择所属科室");
                return;
            }else if(isEmpty(projectsMoney)){
                alert("请输入项目费用!");
                return;
            }else if(!/^(-?\d+)(\.\d+)?$/.test(projectsMoney)){
                alert("请输入正确的金额!");
                return;
            }else if(isEmpty(projectsNum)){
       			alert("请输入项目编码!");
       			return;
       		}else if(!/^[0-9]{6}$/.test(projectsNum)){
       			alert("项目编码只能为六位数字!");
       			return;
       		}

       		
       		if(!isEmpty(projectsRemarks)&&projectsRemarks.length>800){
       			alert("备注不能超过800字!");
       			return;
       		}
       		
       		
			$.ajax({
		           type: "post",
		           url: "${pageContext.request.contextPath}/project/addproject.action",
		           dataType: 'json',
		           data: JSON.stringify({"id":id,"projectsName":projectsName,"projectsContent":projectsContent,
		           "projectsNum":projectsNum,"projectsRemarks":projectsRemarks,"projectMoney":projectsMoney,"departmentId":departmentId}),
		           contentType: "application/json;charset=utf-8",
		           success: function (data1) {
		               if(data1==1){
		               		// layer.msg('已添加!',{icon:1,time:1000});
                           swal({
                               title: "",      //弹出框的title
                               text: "已添加!",   //弹出框里面的提示文本
                               type: "success",        //弹出框类型
                               showCancelButton: false, //是否显示取消按钮
                               confirmButtonColor: "#007bff",//确定按钮颜色
                               confirmButtonText: "确定",//确定按钮上面的文档
                               closeOnConfirm: false
                           },function(){
                               window.open("${pageContext.request.contextPath}/project/projectlist.do",target="mainFrame");
                           });

		               }else{
                           swal("",
                               "添加失败！",
                               "error");
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