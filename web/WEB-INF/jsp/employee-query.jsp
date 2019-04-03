<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,com.yznu.clinic.beans.*,com.yznu.clinic.util.*" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>员工详细信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-table.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container index-main">
    <div class="index-main-top">
        <img src="${pageContext.request.contextPath}/img/accountList.png"  />
        <span>员工详细信息</span>

        <hr  />
    </div>

    <div class="row" id="bodyData1">
            <div class='col-xs-4 col-sm-12 col-md-12'>
            <div class="box">
                <div class="employee">
                    <img src="img/toux.jpg" />
                    <ul>
                       <li><p class="red">${employee.employeeName}</p>
                       </li>
                        <li>
                            <table>
                                <tr>
                                    <td>员工编号：${employee.employeeNum}</td>
                                    <td></td>
                                    <td>员工性别：${employee.employeeSex}</td>
                                    <td></td>
                                    <td>员工出生年月日：${employee.employeeBirth}</td>
                                    </tr>
                                <tr>
                                    <td><img src="img/tel.png" />：${employee.employeeTel}</td>
                                    <td></td>
                                    <td><img src="img/email.png"/>:${employee.employeeEmail}</td>
                                    <td></td>
                                    <td id="depname"></td>
                                </tr>

                                </table>
                            </li>
                        </ul>

                    <div class="innerbox-mid">

                        </div>
                    </div>
                <div class="innerbox-bottom">
                    <p>${employee.employeeOccupation}</p>
                    <span> 入职时间  :${employee.employeeDate}</span>
                    </div>
                </div>
        </div>

        <div class="col-xs-4 col-sm-4 col-md-4">



        </div>
        <div class="col-xs-4 col-sm-4 col-md-4">



        </div>
        <div class="col-xs-4 col-sm-4 col-md-4">


        </div>
    </div>
    <div class="config-detail">

        <div class="form-group">
            <button onclick="tuichu()" type="button" class="btn-search">退出</button>
        </div>
    </div>

</div>
</body>
<script src="${pageContext.request.contextPath}/vendor/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/tableExport.js"></script>

<script>
    function  tuichu(){
        window.close();
    }

    //下载文件
    $("#downLoadScore").click(function(){

        $('#scoreTable').tableExport({
            type:'excel'
        });
    });
    $(function () {
        var departmentid='${employee.departmentsId}';
        $.ajax({
            async:false,
            type: "POST",
            url: "getdepartmentbyId.do",
            dataType: "json",
            data: {"id": departmentid},
            success: function (result) {
                var text="隶属科室：";
                for(var i = 0 ;i<result.length;i++){
                    valuea=result[i].departmentsName;
                    text+=valuea;
                }
                $("#depname").html(text);
            }
        });
    })
</script>
</html>
