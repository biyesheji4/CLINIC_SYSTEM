<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/3
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>药品类别管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-table.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">
</head>

<body>
<input type="hidden" id="employee_type" value="<%=request.getSession().getAttribute("type")%>">
<input type="hidden" id="employee_num" value="<%=request.getSession().getAttribute("employee_num")%>">
<div class="container secondary ">
    <div>
        <img src="${pageContext.request.contextPath}/img/location.png">
        <span>当前位置：药品类别管理</span>
    </div>
    <div class="filter">
        <ul>
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
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content" style="min-width: 900px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">
                                </button>
                                <h5 class="modal-title" id="ModalLabel">
                                    修改药品类别
                                </h5>
                            </div>
                            <div class="modal-body">

                            <div class="search-detail">
                                <table id="data">
                                    <input id="id" type="hidden">
                                    <tr>
                                        <td  style='width:72px'>选择</td><td>类别</td>    <td style="width:72px">选择</td><td>类别</td>
                                    </tr>
                                    <tr>
                                    <td><input type='radio' class='sontelxb' style='width: 60px;height: 13px' value='片剂' name='type'id="pianji" />
                                        <td>片剂</td>
                                    <td><input type='radio' class='sontelxb' style='width: 60px;height: 13px' value='胶囊剂' name='type' id="jiaonang"/>
                                        <td>胶囊剂</td>
                                    </tr>
                                    <tr>
                                    <td><input type='radio' class='sontelxb' style='width: 60px;height: 13px' value='口服酊膏剂' name='type' id="dinggao"/>
                                        <td>口服酊膏剂</td>
                                    <td><input type='radio' class='sontelxb' style='width: 60px;height: 13px' value='口服丸剂' name='type' id="wan"/>
                                        <td>口服丸剂</td>
                                    </tr>
                                    <tr>
                                    <td><input type='radio' class='sontelxb' style='width: 60px;height: 13px' value='口服颗粒、粉、散剂' name='type' id="keli"/>
                                        <td>口服颗粒、粉、散剂</td>
                                    <td><input type='radio' class='sontelxb' style='width: 60px;height: 13px' value='外用酊、膏、贴、粉剂' name='type' id="waiyong"/>
                                        <td>外用酊、膏、贴、粉剂</td>
                                    </tr>
                                    <tr>
                                    <td><input type='radio' class='sontelxb' style='width: 60px;height: 13px' value='外用涂剂、栓剂' name='type' id="tuji"/>
                                        <td>外用涂剂、栓剂</td>
                                    <td><input type='radio' class='sontelxb' style='width: 60px;height: 13px' value='注射剂' name='type' id="zhushe"/>
                                        <td>注射剂</td>
                                    </tr>
                                </table>
                            </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                <button id="medicines" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
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
        <span>药品类别管理</span>
        <hr  />
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
<script src="${pageContext.request.contextPath}/js/medicines.js"></script>
<script src="${pageContext.request.contextPath}/js/dim.js"></script>
</html>
