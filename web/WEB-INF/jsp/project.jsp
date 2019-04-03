<%@ page language="java" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>诊疗检查</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-table.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<input type="hidden" id="type" value="<%=request.getSession().getAttribute("type")%>">
<div class="container secondary ">
    <div>
        <img src="${pageContext.request.contextPath}/img/location.png">
        <span>当前位置：诊疗检查</span>
    </div>
    <div class="filter">
        <ul>
            <li>
                <label>选择时间:</label>
                <div class=" input-group form_date date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                    <input id="calendar" class="form-control" type="text" value="${data}" readonly>
                    <span class="input-group-addon">
				<i class="fa fa-calendar"></i>
          </span>
                </div>
            </li>

            <li class="search">
                <%--<input type="text" class="" id="textarea">--%>
                <button id="search" class="btn btn-blue">搜索</button>
            </li>

            <li>
                <!-- 出库模态框（Modal） -->
                <div class="modal fade" id="projectresultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content" style="min-width: 600px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">
                                </button>
                            </div>
                            <div class="modal-body1" id="result">

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                <button id="projectresult" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </li>
        </ul>
    </div>
</div>

<div class="container index-main">
    <div class="index-main-top">
        <span>诊疗方案明细</span>
        <hr  />
    </div>
    <div >
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 box-pad">
                <table  id="projectdatas">

                </table>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/vendor/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-datetimepicker.js" type="text/javascript" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-datetimepicker.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-table.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-table-export.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-table-zh-CN.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/tableExport.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/data/common.js"></script>
<script src="${pageContext.request.contextPath}/vendor/sweet-alert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dim.js"></script>
<script src="${pageContext.request.contextPath}/js/project.js"></script>
</html>
