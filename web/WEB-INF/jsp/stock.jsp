<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>药品库存管理</title>
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
<input type="hidden" id="employee_type" value="<%=request.getSession().getAttribute("type")%>">
<input type="hidden" id="employee_num" value="<%=request.getSession().getAttribute("employee_num")%>">
<input type="hidden" id="employee_id" value="<%=request.getSession().getAttribute("employee_id")%>">
<div class="container secondary ">
    <div>
        <img src="${pageContext.request.contextPath}/img/location.png">
        <span>当前位置：药品库存管理</span>
    </div>
    <div class="filter">
        <ul>
            <li>
                <label>药品名称查询：</label>
                <input id="name" type="text"  placeholder="输入药品名称"/>
            </li>
            <li>
                <label>药品进库时间:</label>
                <div class=" input-group form_date date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                    <input id="calendar" class="form-control" type="text" value="" readonly>
                    <span class="input-group-addon">
				            <i class="fa fa-calendar"></i>
                    </span>
                </div>
            </li>

            <li>
                <label>药品性状类别:</label>
                <select id="type">
                    <option value="">--请选择--</option>
                    <option value="片剂">片剂</option>
                    <option value="胶囊剂">胶囊剂</option>
                    <option value="口服酊膏剂">口服酊膏剂</option>
                    <option value="口服酊膏剂">口服丸剂</option>
                    <option value="口服颗粒、粉、散剂">口服颗粒、粉、散剂</option>
                    <option value="外用酊、膏、贴、粉剂">外用酊、膏、贴、粉剂</option>
                    <option value="外用涂剂、栓剂">外用涂剂、栓剂</option>
                    <option value="注射剂">注射剂</option>
                </select>

            </li>
            <li class="search">
                <%--<input type="text" class="" id="textarea">--%>
                <button id="search" class="btn btn-blue">搜索</button>
            </li>
            <li>
                <!-- 药品进库模态框（Modal） -->
                <div class="modal fade" id="medicinesinputModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
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
                                                药品进库
                                            </div>


                                            <form id="form" class="form-horizontal">
                                            <div class="panel-body">
                                                <div class="baseInfo">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">药品名称</label>
                                                        <div class="col-sm-3">
                                                            <input  type="text" class="form-control" data-provide="typeahead" name="medicinesName" id='medicinesName' style="width: 100%">
                                                        </div>
                                                        <label class="col-sm-2 control-label">进价（元）</label>
                                                        <div class="col-sm-3">
                                                            <input  type="text" class="form-control" id="medicinesBid" name="medicinesBid" oninput="yun()" style="width: 100%">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">进货数量</label>
                                                        <div class="col-sm-3">
                                                            <input  type="text" class="form-control" id="medicinesNum" name="medicinesNum" oninput="yun()" style="width: 100%">
                                                        </div>
                                                        <label class="col-sm-2 control-label">售价（元）</label>
                                                        <div class="col-sm-3">
                                                            <input  type="text" class="form-control" id="medicinesMoney" name="medicinesMoney" style="width: 100%">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">库存告警量</label>
                                                        <div class="col-sm-3">
                                                            <input  type="text" class="form-control" data-provide="typeahead" name="medicinesLimit" id="medicinesLimit">
                                                        </div>
                                                        <label class="col-sm-2 control-label">类别</label>
                                                        <div class="col-sm-3">
                                                            <select id='medicinesType'>
                                                                <option value=''>--请选择--</option>
                                                                <option value='片剂'>片剂</option>
                                                                <option value='胶囊剂'>胶囊剂</option>
                                                                <option value='口服酊膏剂'>口服酊膏剂</option>
                                                                <option value='口服丸剂'>口服丸剂</option>
                                                                <option value='口服颗粒、粉、散剂'>口服颗粒、粉、散剂</option>
                                                                <option value='外用酊、膏、贴、粉剂'>外用酊、膏、贴、粉剂</option>
                                                                <option value='外用涂剂、栓剂'>外用涂剂、栓剂</option>
                                                                <option value='注射剂'>注射剂</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">过期日期</label>
                                                            <div class=" input-group form_date date " data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                                                <input id="medicinesDate" class="infoInput" type="text" value="" readonly>
                                                                <span class="input-group-addon">
				                                                    <i class="fa fa-calendar"></i>
                                                                </span>
                                                            </div>
                                                        <span class="name"></span>
                                                            <label id = 'mon'></label>
                                                    </div>
                                                </div>
                                                <button type="button" class="btn btn-info " id="insertmedicines" style="float:right">确定</button>
                                                <button type="button" class="btn btn-default"  data-dismiss="modal" style="float:right">关闭</button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <%--<button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>--%>
                                <%--<button id="insertmedicines" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>--%>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </li>

            <li>
                <!-- 出库模态框（Modal） -->
                <div class="modal fade" id="medicinesshareModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content" style="min-width: 300px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">
                                </button>
                            </div>
                            <div class="modal-body1" id="share">

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                <button id="medicineshare" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
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
        <span>药品库存管理</span>
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
    <div id="bodyData">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 box-pad">
                <table  id="datas">

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
<script src="${pageContext.request.contextPath}/js/stock.js"></script>
<script src="${pageContext.request.contextPath}/js/dim.js"></script>
</html>
