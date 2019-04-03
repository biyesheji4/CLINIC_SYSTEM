<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" language="java" %>
<%@ page import="org.apache.ibatis.annotations.Case" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>病历单</title>
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/font-awesome.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/basic.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />--%>
</head>
<body>
<input class="hidden" value="${registeredid}" id="registeredid">
<div>
    <div>
        <div class="col-md-12">
            <h1 class="page-head-line" style="text-align: center">患者历史病例</h1>
        </div>
    </div>
    <div id="beforbingli">

    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/vendor/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/typehead.js"></script>
<script src="${pageContext.request.contextPath}/vendor/sweet-alert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/case-history.js"></script>
</html>
