<%@ page language="java" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>员工</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-table.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrapvalidator.css">
</head>

<body>
<input type="hidden" id="type" value="<%=request.getSession().getAttribute("type")%>">
<input type="hidden" id="employee_num" value="<%=request.getSession().getAttribute("employee_num")%>">
<div class="container secondary ">
    <div>
        <img src="${pageContext.request.contextPath}/img/location.png">
        <span>当前位置：员工信息</span>
    </div>
    <div class="filter">
        <ul>
            <li>
                <label>员工在职&离职:</label>
                <select id="state">
                    <option value="1">在职</option>
                    <option value="2">离职</option>
                </select>

            </li>
            <li>

                <input id="defaultSelect" type="text" readonly="readonly" placeholder="选择科室" class="inpstyle" data-toggle="modal" data-target="#myModal" value="">
                <input id="selectDepartmentId" type="hidden">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content" style="min-width: 900px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">
                                </button>
                                <h5 class="modal-title" id="myModalLabel">
                                    科室选择
                                </h5>
                            </div>
                            <div class="modal-body">
                                <div class="config-search">
                                    <input id="queryName" type="text"  placeholder="输入科室名称"/>
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
            </li>

            <li class="search">
                <%--<input type="text" class="" id="textarea">--%>
                <button id="search" class="btn btn-blue">搜索</button>
            </li>



            <!-- 修改员工信息模态框（Modal） -->
            <div class="modal fade" id="updateemployeeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                <div class="modal-dialog">
                    <div class="modal-content" style="min-width: 800px">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">
                            </button>
                        </div>
                        <div class="modal-body1">
                            <div >
                                <div class="col-md-12 col-sm-6 col-xs-12">
                                    <div class="panel panel-info">
                                        <div class="panel-heading" style="text-align: center;background-image:linear-gradient(to bottom, #007bff 0%, #c4e3f3 100%)">
                                            更新员工信息
                                        </div>
                                        <form id="updateform" class="form-horizontal">
                                            <div class="panel-body">
                                                <div class="baseInfo">
                                                    <div class="form-group">
                                                        <input id="id" type="hidden">
                                                        <label class="col-sm-2 control-label">编号</label>
                                                        <div class="col-sm-10">
                                                            <input  maxlength="4" type="text" name="employeeNum" class="form-control" data-provide="typeahead" id="updateemployeeNum">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">姓名</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" name="employeeName" class="form-control" id="updateemployeeName">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">性别</label>
                                                        <div class="col-sm-10">
                                                        <input  type="radio" class="infoInput"  id="nan" value="男" name="sex">&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input  type="radio" class="infoInput"  id="nv" value="女" name="sex">&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">职业</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" name="employeeOccupation" class="form-control" id="updateemployeeOccupation">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">邮箱</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" name="employeeEmail" class="form-control" id="updateemployeeEmail">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">工资</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" name="employeeSalary" class="form-control" data-provide="typeahead" id="updateemployeeSalary">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">出生日期</label>
                                                        <div class="col-sm-10">
                                                        <div class=" input-group form_date date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                                            <input id="updateemployeeBirth" class="infoInput" type="text" value="" readonly>
                                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
                                                            </span>
                                                        </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">入职日期</label>
                                                        <div class="col-sm-10">
                                                        <div class=" input-group form_date date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                                            <input id="updateemployeeDate" class="infoInput" type="text" value="" readonly>
                                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
                                                            </span>
                                                        </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">联系方式</label>
                                                        <div class="col-sm-10">
                                                            <input  maxlength="11" type="text" class="form-control" name="employeeTel" id="updateemployeeTel">
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <label class="col-sm-2 control-label">所属科室</label>
                                                        <div class="col-sm-10">
                                                        <select id="updatedepartmentId">

                                                        </select>
                                                        </div>
                                                    </div>
                                                    <button id="update" type="button" class="btn btn-primary" style="float:right">确定</button>
                                                    <button type="button" class="btn btn-default"  data-dismiss="modal" style="float:right">关闭</button>

                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="modal-footer">

                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->



                <!-- 新增员工模态框（Modal） -->
                <div class="modal fade" id="inputModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content" style="min-width: 800px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">
                                </button>
                            </div>
                            <div class="modal-body1">
                                <div >
                                    <div class="col-md-12 col-sm-6 col-xs-12">
                                        <div class="panel panel-info">
                                            <div class="panel-heading" style="text-align: center;background-image:linear-gradient(to bottom, #007bff 0%, #c4e3f3 100%)">
                                                新增员工
                                            </div>
                                            <form id="form" class="form-horizontal">
                                            <div class="panel-body">
                                                <div class="baseInfo">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">编号</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" maxlength="4" name="employeeNum" class="form-control" data-provide="typeahead" id="employeeNum">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">姓名</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" name="employeeName" class="form-control" id="employeeName">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">性别</label>
                                                        <div class="col-sm-10">
                                                        <input  type="radio" class="infoInput" checked="checked" value="男" name="sex">&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input  type="radio" class="infoInput"  value="女" name="sex">&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">职业</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" name="employeeOccupation" class="form-control" id="employeeOccupation">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">邮箱</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" name="employeeEmail" class="form-control" id="employeeEmail">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">工资</label>
                                                        <div class="col-sm-10">
                                                            <input  type="text" name="employeeSalary" class="form-control" data-provide="typeahead" id="employeeSalary">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">出生日期</label>
                                                        <div class="col-sm-10">
                                                        <div class=" input-group form_date date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                                            <input id="employeeBirth" class="infoInput" type="text" value="" readonly>
                                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
                                                            </span>
                                                        </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">入职日期</label>
                                                        <div class="col-sm-10">
                                                            <div class=" input-group form_date date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                                            <input id="employeeDate" class="infoInput" type="text" value="" readonly>
                                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
                                                            </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">联系方式</label>
                                                        <div class="col-sm-10">
                                                        <input  type="text" class="form-control" name="employeeTel" id="employeeTel">
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <label class="col-sm-2 control-label">所属科室</label>
                                                        <div class="col-sm-10">
                                                        <select id="departmentId">
                                                            <option value="">--请选择--</option>
                                                                <%	List<Departments> departments = (List) request.getSession().getAttribute("departmentsAll");
                                                                    if(departments!=null){
                                                                        for (int i = 0; i < departments.size(); i++) {%>
                                                            <option value="<%=departments.get(i).getId() %>"><%=departments.get(i).getDepartmentsName() %></option>
                                                                <% }}%>
                                                        </select>
                                                        </div>
                                                   </div>
                                                    <button id="add" type="button" class="btn btn-primary" style="float:right">确定</button>
                                                    <button type="button" class="btn btn-default"  data-dismiss="modal" style="float:right">关闭</button>

                                                </div>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">

                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->

        </ul>
    </div>
</div>

<div class="container index-main">
    <div class="index-main-top">
        <span>员工信息明细</span>
        <hr  />
    </div>
    <div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
        <%--<button id="btn_delete" type="button" class="btn btn-default">--%>
            <%--<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除--%>
        <%--</button>--%>
    </div>
    <div id="employeeData">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 box-pad">
                <table  id="employeedatas">

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
<script src="${pageContext.request.contextPath}/vendor/bootstrapvalidator.js"></script>
<script src="${pageContext.request.contextPath}/vendor/zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/js/employee.js"></script>
<script src="${pageContext.request.contextPath}/js/dim.js"></script>
</html>
