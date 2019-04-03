<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>首页</title>
  <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
  <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/logo.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
  <%--<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">--%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ready.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/demo.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
</head>
<style type="text/css">
  body {overflow: hidden;}
</style>
<body>
<input type="hidden" id="type" value="<%=request.getSession().getAttribute("type")%>">
<div class="wrapper">
  <div class="main-header">
    <div class="logo-header">
      <div class="img_logo"><img src="${pageContext.request.contextPath}/assets/img/logo.ico" alt=" " height="50"></div>
      <span style="display:inline-block;padding-top:3px;">诊所管家智能系统
					</span>
    </div>
    <div id="bar_menu">
      <ul class="nav_menu" id="nav_menu">
        <%--<li><img src="${pageContext.request.contextPath}/assets/img/gh.png" class="img1"><a id="registeredadd" href="#">挂号</a></li>--%>
        <%--<li><img src="${pageContext.request.contextPath}/assets/img/gh.png" class="img1"><a id="patient" href="#">门诊</a></li>--%>
        <%--<li><img src="${pageContext.request.contextPath}/assets/img/gh.png" class="img1"><a href="#" id="bill_list">账单</a></li>--%>
        <%--<li><img src="${pageContext.request.contextPath}/assets/img/gh.png" class="img1"><a href="#" id="charges">收费</a></li>--%>
        <%--<li><img src="${pageContext.request.contextPath}/assets/img/gh.png" class="img1"><a href="#" id="payment">已缴费药品出库</a></li>--%>
        <%--<li><img src="${pageContext.request.contextPath}/assets/img/gh.png" class="img1"><a href="#" id="project">已缴费诊疗项目</a></li>--%>
      </ul>
    </div>
    <div id="info_menu">
      <ul class="nav_menu">
        <li><img src="assets/img/login.png" class="img1"><a href="login.do">退出登录</a></li>
        <li><img src="assets/img/ltime.png" class="img1"><a>${data}</a></li>
        <li class="nav-item dropdown">
          <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#" aria-expanded="false"><span >欢迎您：<%=request.getSession().getAttribute("employee_name")%></span></span> </a>
          <!-- /.dropdown-user -->
        </li>

      </ul>
    </div>
  </div>
  <div class="sidebar" id="sidebar">
    <div class="scrollbar-inner sidebar-wrapper">
      <ul class="nav" id="nav">

      </ul>
    </div>
  </div>
  <div class="main-panel" id="div" style="margin-top: 60px">
    <iframe src="redirect.do?view=tuxing"   name="mainFrame" frameborder="0" marginheight="0" marginwidth="0"  height="100%" width="100%"></iframe>
  </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/assets/js/core/jquery.3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/chartist/chartist.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/chartist/plugin/chartist-plugin-tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/jquery-mapael/jquery.mapael.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/jquery-mapael/maps/world_countries.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/chart-circle/circles.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/ready.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/sweet-alert.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/demo.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</html>
