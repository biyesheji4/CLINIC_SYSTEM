<%@ page language="java" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title></title>
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/font-awesome.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/basic.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrapvalidator.css">
    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />--%>
</head>
<body>

<div >
    <div >
        <div class="col-md-12">
            <h1 class="page-head-line"  style="text-align: center">挂号</h1>
        </div>
    </div>
    <div >
        <div class="col-md-6 col-sm-6 col-xs-12" style="left: 25%">
            <div class="panel panel-info">
                <div class="panel-heading" style="text-align: center">
                    挂号
                </div>
                <form id="form" class="form-horizontal">
                    <div class="panel-body">
                        <div class="baseInfo">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号</label>
                                <div class="col-sm-10">
                                    <input name="patientIdcard"  maxlength="18" type="text" class="form-control" data-provide="typeahead" id="patientIdcard" onChange="find()">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-10">
                                    <input  type="text" class="form-control" id="patientName" name="patientName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-10">
                                    <input  type="radio" class="infoInput" id="nan" value="男" name="sex">&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input  type="radio" class="infoInput" id="nv" value="女" name="sex">&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>
                                <div class="col-sm-10">
                                <input  type="text" class="form-control" id="patientAge" name="patientAge">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系方式</label>
                                <div class="col-sm-10">
                                    <input  type="text" maxlength="11" class="form-control" id="tel" name="patientTel">
                                </div>
                            </div>
                            <div  class="form-group">
                                <label class="col-sm-2 control-label">提交科室</label>
                                <div class="col-sm-10">
                                    <select id="departmentId" name="departmentsName" class="form-control">
                                            <option value="">--请选择--</option>
                                        <%	List<Departments> departments = (List) request.getSession().getAttribute("departmentsAll");
                                            if(departments!=null){
                                                for (int i = 0; i < departments.size(); i++) {%>
                                            <option value="<%=departments.get(i).getId() %>"><%=departments.get(i).getDepartmentsName() %></option>
                                            <% }}%>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">病情简述</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="3" id="content" name="registeredContent"></textarea>
                            </div>
                        </div>
                        <button type="button" class="btn btn-info " id="add" style="float:right">确定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/vendor/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/typehead.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/jquery.3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/sweet-alert.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrapvalidator.js"></script>
<script src="${pageContext.request.contextPath}/js/registered-add.js"></script>
<script src="${pageContext.request.contextPath}/vendor/zh_CN.js"></script>
<script>


</script>
</html>
