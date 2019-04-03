<%@ page language="java" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>病历单</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-table.min.css">
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/font-awesome.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/basic.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrapvalidator.css">
    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />--%>
</head>

<body >
<input type="hidden" value="${registered.id}" id="registeredid">
<input  id="employee_id" type="hidden" class="infoInput" value="<%=request.getSession().getAttribute("employee_id")%>">
<div>
    <div>
        <div class="col-md-12">
            <h1 class="page-head-line" style="text-align: center">病例书写</h1>
            <button id="before" class="btn btn-info ">历史病例</button>
            <button id="Selectprojects"  class="btn btn-info" data-toggle="modal" data-target="#projectsModal" value="">诊疗项目</button>
        </div>
    </div>
    <div id="beforbingli">
        <div class="col-md-6 col-sm-6 col-xs-12" style="left: 25%">
            <div class="panel panel-info">
                <div class="panel-heading" style="text-align: center">
                    病例详情
                </div>
                <form id="caseform">
                <div class="panel-body">
                        <div class="baseInfo">
                            <div class="baseRow1">
                                <span class="number">身份证号</span> <input  type="text" class="infoInput" data-provide="typeahead" value="${registered.patientIdcard}">
                                <span class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span>  <input  type="text" class="infoInput" value="${registered.patientName}">
                            </div>
                            <div class="baseRow1">
                                <span class="number">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</span> <input  type="text" class="infoInput" value="${registered.patientSex}">
                                <span class="name">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄</span> <input  type="text" class="infoInput" value="${registered.patientAge}">
                            </div>

                            <div class="baseRow1">
                                <span class="number">就诊时间</span> <input  id="date" type="text" class="infoInput" value="${date}">
                                <span class="name">主治医生</span> <input  type="text" class="infoInput"  value="<%=request.getSession().getAttribute("employee_name")%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>现病史</label>
                            <textarea class="form-control" rows="3" id="content" name="content">${registered.projectResult}</textarea>
                        </div>
                        <div class="form-group">
                            <label>治疗方案</label>
                            <textarea class="form-control" rows="5" id="programme" name="programme"></textarea>
                            <input  id="money" type="hidden" class="infoInput" value="0">
                            <input  id="mid" type="hidden" class="infoInput" value="0">
                            <input  id="mnum" type="hidden" class="infoInput" value="0">
                        </div>
                    <div class="form-group">
                    <input id="defaultSelect" type="text" readonly="readonly" placeholder="药品选择" class="inpstyle" data-toggle="modal" data-target="#myModal" value="">
                    <!-- 药品选择模态框（Modal） -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                        <div class="modal-dialog" >
                            <div class="modal-content" style="min-width: 900px">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">
                                    </button>
                                    <h5 class="modal-title" id="myModalLabel">
                                        药品选择
                                    </h5>
                                </div>
                                <div class="modal-body">
                                    <div class="config-search">
                                        <input id="queryName" type="text"  placeholder="输入药品名称"/>
                                        <button id="query" class="btn-search">查询</button>

                                    </div>

                                    <div class="search-detail">
                                        <table id="tableData">
                                        </table>


                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                    <button id="selectID" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->

                    </div>
                        <button id="addcase" type="button" class="btn btn-info ">确定</button>
                        <button id="chehui" type="button" class="btn btn-info ">撤回</button>
                        <input  id="programmeId" type="hidden" class="infoInput" value="">
                        <input  id="caseId" type="hidden" class="infoInput" value="">
                </div>
                </form>
            </div>
        </div>
    </div>

            <!-- 诊疗项目选择模态框（Modal） -->
            <div class="modal fade" id="projectsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                <div class="modal-dialog" >
                    <div class="modal-content" style="min-width: 900px">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">
                            </button>
                            <h5 class="modal-title" id="myModalProjects">
                                诊疗项目选择
                            </h5>
                        </div>
                        <div class="modal-body">
                            <div class="config-search">


                            </div>

                            <div class="search-detail">
                                <table id="projectsData">
                                </table>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                            <button id="Projects" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

</div>
</body>
<script src="${pageContext.request.contextPath}/vendor/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/typehead.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/jquery.3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-datetimepicker.js" type="text/javascript" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-datetimepicker.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-table.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-table-export.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap-table-zh-CN.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/tableExport.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/data/common.js"></script>
<script src="${pageContext.request.contextPath}/vendor/sweet-alert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/case-write.js"></script>
<script src="${pageContext.request.contextPath}/js/dimMedicins.js"></script>
<script src="${pageContext.request.contextPath}/js/dimprojects.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrapvalidator.js"></script>
<script src="${pageContext.request.contextPath}/vendor/zh_CN.js"></script>
</html>
