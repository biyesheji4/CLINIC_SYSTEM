<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
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
<title>账单列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 账单管理 <span class="c-gray en">&gt;</span> 账单列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<input type="hidden" id="offset" value="">
		<button onclick="queryusedata(1)" class="btn btn-primary radius">入库账单</button>
		<button onclick="queryusedata(2)" class="btn btn-primary radius">出库账单</button>
		<button onclick="querystopdata(1)" class="btn btn-primary radius">停用入库账单</button>
		<button onclick="querystopdata(2)" class="btn btn-primary radius">停用出库账单</button>
		</span> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
		<input type="text" name="" id="searchindex" placeholder="关键字查询" style="width:250px" class="input-text">
		<button name=""  id="" class="btn btn-success"  onclick="querysomedata(1,[0,1])" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜账单</button>
		
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="bill_add('添加账单','${pageContext.request.contextPath}/bill/billupdate.do','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加账单</a></span>
	 <span class="r">共有数据：<strong id="num"></strong> 条</span> </div>
		<div class="downLoad pull-right">
			<span onclick="downloadFile()">点击下载</span>
			<a><img src="${pageContext.request.contextPath}/img/download.png"></a>
		</div>
	<div class="mt-20">
	<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper no-footer">
		<table id="datatable" class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="50">ID</th>
					<th>账单名</th>
					<th width="150">编号</th>
					<th width="100">账单类型</th>
					<th width="120">项目</th>
					<th width="200">备注</th>
					<th width="110">创建日期</th>
					<th width="120">金额</th>
					<th width="120">状态</th>
					<th width="120">处理员工编号</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table>
		
		<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">显示<span id="span1"></span> 到 <span id="span2"></span> ，共 <span id="span3"></span> 条</div>
		<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate"><!-- 放页码 -->
			
		</div>
		</div>
	</div>
</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script src="${pageContext.request.contextPath}/vendor/sweet-alert.min.js"></script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script>

<script type="text/javascript">
//目标页码
var currpage=1;
//当前页
var offset=1;
//标记类型
var delflags=[0,1];

/*账单-增加*/
function bill_add(title,url,w,h){
	layer_show(title,url,w,h);
}


//账单-编辑
function bill_edit(title,url,id,w,h){
	// var index = layer.open({
	// 	type: 2,
	// 	title: title,
	// 	content: url+"?id="+id
	// });
	// layer.full(index);
    layer_show(title,url,w,h);
}


//账单-删除
function bill_del(obj,id){
    swal({
        title: "",      //弹出框的title
        text: "确定要删除吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定删除！",//确定按钮上面的文档
        closeOnConfirm: false
    },function(){
		$.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/bill/delbill.action",
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(id),
			success: function(data){
				if(data==1){
					// window.location.reload();
					// layer.msg('已删除!',{icon:1,time:1000});
                    swal("",
                        "已删除！",
                        "success");
                    querysomedata(currpage,[0,1]);
				}else{
					// layer.msg('删除失败!',{icon:1,time:1000});
                    swal("",
                        "删除失败！",
                        "error");
				}
				
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
               console.log(XMLHttpRequest.status);
               console.log(XMLHttpRequest.readyState);
               console.log(textStatus);
           },
		});		
	});
}

/* 账单-批量删除 */
function datadel(){
	
	var id_array=new Array();  
	$('input[name="id"]:checked').each(function(){  
	    id_array.push($(this).val());//向数组中添加元素  
	});  
	var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串 
	console.log(idstr);
    swal({
        title: "",      //弹出框的title
        text: "确定要删除吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定删除！",//确定按钮上面的文档
        closeOnConfirm: false
    },function(){
		$.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/bill/delallbill.action",
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(idstr),
			success: function(data){
				if(data==1){
					// window.location.reload();
					// layer.msg('已删除!',{icon:1,time:1000});
                    swal("",
                        "已删除！",
                        "success");
                    querysomedata(currpage,[0,1]);
				}else{
					// layer.msg('删除失败!',{icon:1,time:1000});
                    swal("",
                        "删除失败！",
                        "error");
				}
				
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
               console.log(XMLHttpRequest.status);
               console.log(XMLHttpRequest.readyState);
               console.log(textStatus);
           },
		});		
	});
}
/* 账单-初始化 查询并分页 */
$(document).ready(function(){
	querysomedata(1,[0,1]);
})

//显示正常账单
function queryusedata(state){
	switch(state){
		case 1:querysomedata(currpage,[0,0]);break;//查询入库记录
		case 2:querysomedata(currpage,[1,1]);break;//查询出库记录
		default:alert("error!");
	}
	
} 
//显示停用账单
function querystopdata(state){
	switch(state){
		case 1:querysomedata(currpage,[-1,-1]);break;//查询已删除入库记录
		case 2:querysomedata(currpage,[-2,-2]);break;//查询已删除出库记录
		default:alert("error!");
	}
} 

/* 去指定页码 */
function gotopage(currpage,delflag){
	querysomedata(currpage,delflag);
}

 /* 查询 */
function querysomedata(currpage,delflags){
	$("#tbody").empty();
	$("#DataTables_Table_0_paginate").empty();
	console.log("delflags==="+delflags[0]+"！"+delflags[1]);
	var jsonData="";
	var offset=$("#offset").val();
	var date1=$("#logmin").val();
	var date2=$("#logmax").val();
	var searchindex=$("#searchindex").val();
		$.ajax({
           type: "post",
           url: "${pageContext.request.contextPath}/bill/querysomedata.action?date1="
           +date1+"&date2="+date2+"&searchindex="+searchindex+"&currpage="+currpage+"&delflag1="+delflags[0]+"&delflag2="+delflags[1],
           dataType: 'json',
           contentType: "application/json;charset=utf-8",
           data: JSON.stringify(jsonData),
           success: function (page) {
          	 var data1=null;
           		if(page!=null){
           		console.log("page----"+page);
           			//page=JSON.parse(page);
           			data1=page.pagedata;
           			console.log(data1)
           			//总页数
           			var totalsize=page.totalsize;
           			//当前页
           			var currpage=page.currpage;
           			//一页的数据条数
           			var pagesize=page.pagesize;
           			var num=page.allnum;
           		}
           		
               if(totalsize!=0){
               	   $("#num").text(num);
	               $('#span1').text((currpage-1)*pagesize+1);
	               $('#span2').text((currpage-1)*pagesize+data1.length);
	               $('#span3').text(data1.length);
               	for(var i=0;i<data1.length;i++){
               	 	var state;
						if(data1[i].billState=='0'){
							state="收入&正常";
						}else if(data1[i].billState=='1'){
							state="支出&正常";
						}else if(data1[i].billState=='-1'){
							state="收入&停用";
						}else if(data1[i].billState=='-2'){
							state="支出&停用";
						}
               		var lie=$('<tr class="text-c"></tr>');
	               		var hang1=$('<td><input type="checkbox" name="id" value="'+data1[i].id+'" name=""></td>');
	               		var hang2=$('<td>'+data1[i].id+'</td>');
	               		var hang3=$('<td>'+data1[i].billName+'</td>');
	               		var hang4=$('<td>'+data1[i].billNum+'</td>');
	               		var hang41=$('<td>'+data1[i].billType+'</td>');
	               		var hang5=$('<td>'+data1[i].billOption+'</td>');
	               		var hang6=$('<td>'+data1[i].billRemarks+'</td>');
	               		var hang7=$('<td>'+data1[i].billDate+'</td>');
	               		var hang8=$('<td>'+data1[i].billMon+'</td>');
	               		var hang9=$('<td>'+state+'</td>');
	               		var hang0=$('<td>'+data1[i].employeeNum+'</td>');
	               		var hang11=$('<td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="bill_edit(\'账单编辑\',\'${pageContext.request.contextPath}/bill/billupdate.do?id='+data1[i].id+'\',\''+data1[i].id+'\',\'800\',\'500\')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="bill_del(this,\''+data1[i].id+'\')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>');
	               		$(lie).append(hang1);
	               		$(lie).append(hang2);
	               		$(lie).append(hang3);
	               		$(lie).append(hang4);
	               		$(lie).append(hang41);
	               		$(lie).append(hang5);
	               		$(lie).append(hang6);
	               		$(lie).append(hang7);
	               		$(lie).append(hang8);
	               		$(lie).append(hang9);
	               		$(lie).append(hang0);
	               		$(lie).append(hang11);
	               		$('#tbody').append(lie);
               	}
               		//上一页按钮
	               	var previous;
	               	//下一页按钮
	               	var next;
	               	
	               	if(currpage==1){
	               		previous=currpage;
	               	}else{
	               		previous=currpage-1;
	               	}
	               
	               	if(currpage==totalsize){
	               		next=currpage
	               	}else{
	               		next=currpage+1;
	               	}
	               	var apgup=$('<a class="paginate_button previous disabled" onclick="gotopage('+previous+',['+delflags+'])" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" id="DataTables_Table_0_previous">上一页</a>');
	    			var apgdn=$('<a class="paginate_button next disabled" onclick="gotopage('+next+',['+delflags+'])" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" id="DataTables_Table_0_next">下一页</a>');
	    			$("#DataTables_Table_0_paginate").append(apgup);
	    			
	    			//插入页码
	    			if(totalsize<=15){
	    				for(var i=0;i<totalsize;i++){
		               		var pagelable;
		               		if((i+1)==currpage){
			               		pagelable=$('<span><a class="paginate_button current" onclick="gotopage('+(i+1)+',['+delflags+'])" aria-controls="DataTables_Table_0" data-dt-idx="'+(i+1)+'" tabindex="0">'+(i+1)+'</a></span>');
			               		$("#DataTables_Table_0_paginate").append(pagelable);
		               		}else{
		               			pagelable=$('<span><a class="paginate_button" onclick="gotopage('+(i+1)+',['+delflags+'])" aria-controls="DataTables_Table_0" data-dt-idx="'+(i+1)+'" tabindex="0">'+(i+1)+'</a></span>');
			               		$("#DataTables_Table_0_paginate").append(pagelable);
		               		}
		               	}
	    			}else{
	    				if(Math.floor((currpage-1)/15)<Math.floor((totalsize-1)/15)){
	    					var pageflag=Math.floor((currpage-1)/15)+1;
	    					if(pageflag!=1){
				               		$("#DataTables_Table_0_paginate").append('<span>……</span>');
	    					}
	    					for(var i=(pageflag-1)*15;i<pageflag*15;i++){
			               		var pagelable;
			               		if((i+1)==currpage){
				               		pagelable=$('<span><a class="paginate_button current" onclick="gotopage('+(i+1)+',['+delflags+'])" aria-controls="DataTables_Table_0" data-dt-idx="'+(i+1)+'" tabindex="0">'+(i+1)+'</a></span>');
				               		$("#DataTables_Table_0_paginate").append(pagelable);
			               		}else{
			               			pagelable=$('<span><a class="paginate_button" onclick="gotopage('+(i+1)+',['+delflags+'])" aria-controls="DataTables_Table_0" data-dt-idx="'+(i+1)+'" tabindex="0">'+(i+1)+'</a></span>');
				               		$("#DataTables_Table_0_paginate").append(pagelable);
			               		}
			               	}
			               	$("#DataTables_Table_0_paginate").append('<span>……</span>');
	    				}else{
		    				var pageflag=(Math.floor(currpage-1)/15)+1;
					        $("#DataTables_Table_0_paginate").append('<span>……</span>');
	    					for(var i=(pageflag-1)*15;i<totalsize;i++){
			               		var pagelable;
			               		if((i+1)==currpage){
				               		pagelable=$('<span><a class="paginate_button current" onclick="gotopage('+(i+1)+',['+delflags+'])" aria-controls="DataTables_Table_0" data-dt-idx="'+(i+1)+'" tabindex="0">'+(i+1)+'</a></span>');
				               		$("#DataTables_Table_0_paginate").append(pagelable);
			               		}else{
			               			pagelable=$('<span><a class="paginate_button" onclick="gotopage('+(i+1)+',['+delflags+'])" aria-controls="DataTables_Table_0" data-dt-idx="'+(i+1)+'" tabindex="0">'+(i+1)+'</a></span>');
				               		$("#DataTables_Table_0_paginate").append(pagelable);
			               		}
			               	}
	    				}
		    				
	    			}
	               	
	               	$("#DataTables_Table_0_paginate").append(apgdn);
               }else{//当没有数据时置为零
               	   $("#num").text(0);
	               $('#span1').text(0);
	               $('#span2').text(0);
	               $('#span3').text(0);
               }
           },
           error: function (XMLHttpRequest, textStatus, errorThrown) {
               console.log(XMLHttpRequest.status);
               console.log(XMLHttpRequest.readyState);
               console.log(textStatus);
           }
       });
}

/**
* 下载文件
*/
function downloadFile() {
    var fileTitle ="账单列表";
    var date1=$("#logmin").val();
    var date2=$("#logmax").val();
    var searchindex=$("#searchindex").val();
    location.href="${pageContext.request.contextPath}/bill/toExportFile.do?fileTitle="+fileTitle+"&date1="
                   +date1+"&date2="+date2+"&searchindex="+searchindex;
    <%--$.ajax({--%>
            <%--type:"post",--%>
            <%--url:"${pageContext.request.contextPath}/bill/toExportFile.do?fileTitle="+fileTitle+"&date1="--%>
            <%--+date1+"&date2="+date2+"&searchindex="+searchindex,--%>
            <%--dataType: "json",--%>
            <%--success:function (result) {--%>

            <%--}--%>
        <%--})--%>
}


</script> 
</body>
</html>