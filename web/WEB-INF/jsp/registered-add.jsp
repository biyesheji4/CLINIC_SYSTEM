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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrapvalidator.css">
    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />--%>
</head>
<body>

<div >
    <div class="col-xs-12 col-sm-12 col-md-12 rtab">
        <ul id="martaget-tab" class="nav nav-tabs">
            <%--<li class="active"><a href="#executed" data-toggle="tab">患者登记</a></li>--%>
            <%--<li><a href="#expired" data-toggle="tab">登记列表</a></li>--%>
        </ul>
        <form id="form" class="form-horizontal">
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="executed">
                <div class="tab-head">
                    <span class="listline">患者信息</span>
                    <ul>
                        <%--<li><img src="img/download.png">读取身份证</li>--%>
                        <%--<li><img src="img/download.png">健康商品</li>--%>
                        <%--<li><img src="img/download.png">预约待登记</li>--%>
                    </ul>
                    <div class="tab-body">
                        <form class="myInput-form">
                            <div class="b-block">
                                <div class="form-group">
                                    <label ><font>*</font>身份证号：</label>
                                        <input name="patientIdcard"  maxlength="18" type="text" class="search-txt noBlue-border search-ipt" data-provide="typeahead" id="patientIdcard" onChange="find()">
                                </div>
                                <%--<label><font>*</font>患者姓名：</label>--%>
                                <%--<input type="text" value="输入任务名称搜索" class="search-txt noBlue-border search-ipt" />--%>

                            </div>
                            <div class="b-block">
                                <div class="form-group">
                                <label class=""><font>*</font>患者姓名：</label>
                                    <input  type="text" class="search-txt noBlue-border search-ipt" id="patientName" name="patientName">
                                </div>
                            </div>
                            <div class="b-block radiolist">
                                <div class="form-group">
                                    <label>性别</label>
                                        <input  type="radio" class="infoInput" id="nan" value="男" name="sex">&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input  type="radio" class="infoInput" id="nv" value="女" name="sex">&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </div>
                            </div>
                            <div class="b-block">
                                <div class="form-group">
                                    <label><font>*</font>年龄：</label>
                                        <input  type="text" class="listname" id="patientAge" name="patientAge" >
                                </div>
                            </div>
                            <div class="b-block">
                                <div class="form-group">
                                    <label><font>*</font>联系方式：</label>
                                        <input  type="text" maxlength="11" class="listname" id="tel" name="patientTel">
                                </div>
                            </div>
                        </form>

                    </div><!--tab-body结束-->
                    <span class="listline">诊前信息</span>
                    <div class="tab-body zqinfo" style="height: 20%">
                        <label>病情简述</label>
                            <textarea class="form-control" rows="3" id="content" name="registeredContent"></textarea>

                    </div><!--tab-body结束-->
                    <span class="listline">就诊信息</span>
                    <div class="tab-body zqinfo">
                            <div class="b-block radiolist">
                                <label>就诊科室：</label>
                                <select id="departmentId" name="departmentsName" class="save-select">
                                    <option value="">--请选择--</option>
                                    <%	List<Departments> departments = (List) request.getSession().getAttribute("departmentsAll");
                                        if(departments!=null){
                                            for (int i = 0; i < departments.size(); i++) {%>
                                    <option value="<%=departments.get(i).getId() %>"><%=departments.get(i).getDepartmentsName() %></option>
                                    <% }}%>
                                </select>
                            </div>

                    </div><!--tab-body结束-->
                </div>

            </div>
        </div>
            <button type="button" class="btn btn-info " id="add" style="float:right">确定</button>
        </form>
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
