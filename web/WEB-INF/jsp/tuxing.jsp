<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/6
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-table.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container secondary ">
<div class="filter">
    <ul>
        <li id="start">
            <label>选择起始年份:</label>
            <div class=" input-group form_date_year date " data-date="" data-date-format="yyyy" data-link-field="dtp_input" data-link-format="yyyy">
                <input id="starttime" class="form-control"  type="text" value="" readonly>
                <span class="input-group-addon">
							<i class="fa fa-calendar"></i>
						</span>
            </div>
        </li>
        <li id = "end">
            <label>选择结束年份:</label>
            <div class=" input-group form_date_year date " data-date="" data-date-format="yyyy" data-link-field="dtp_input" data-link-format="yyyy">
                <input id="endtime" class="form-control"  type="text" value="" readonly>
                <span class="input-group-addon">
							<i class="fa fa-calendar"></i>
						</span>
            </div>
        </li>
        <li id="choosemonth">
            <label>选择年份:</label>
            <div class=" input-group form_date_year date " data-date="" data-date-format="yyyy-mm" data-link-field="dtp_input" data-link-format="yyyy-mm">
                <input id="yeartime" class="form-control"  type="text" value="" readonly>
                <span class="input-group-addon">
							<i class="fa fa-calendar"></i>
						</span>
            </div>
        </li>
        <li class="search">
            <!-- <input type="text" class="" id="textarea"> -->
            <button class="btn btn-blue" id="year">按年统计</button>
        </li>
        <li class="search">
            <!-- <input type="text" class="" id="textarea"> -->
            <button class="btn btn-blue" id="month">按月统计</button>
        </li>
        <li class="search">
            <!-- <input type="text" class="" id="textarea"> -->
            <button class="btn btn-blue" id="search">搜索</button>
        </li>
    </ul>
</div>
</div>

<div class="container market-detail">
    <div class="row bg-white">


        <div class="col-xs-12 col-sm-12 col-md-12">
            <div id="line" style="width: 100%;height: 240px;"></div>
        </div>

    </div>
</div>


    <div class="container market-detail">

        <div class="row bg-white">
            <div class="col-xs-12 col-sm-12 col-md-12 borderLeft10">
                <div>&nbsp;</div>
                <span class="title" id="topFeeTitle">医生诊疗人数排名</span>
                <div class="row" id="topFeeRow">
                    <div class="col-xs-6 col-sm-6 col-md-6 ">
                        <table class="table rank" id="topStockTab1">

                            <tr class="one">
                                <th>排名</th>
                                <th style="width:200px">医生</th>
                                <th>诊断人数</th>
                                <th></th>
                            </tr>

                            <tbody id="topStockUser1">


                            </tbody>
                        </table>
                    </div>

                    <div class="col-xs-6 col-sm-6 col-md-6 ">
                        <table class="table rank">
                            <tr class="one">
                                <th>排名</th>
                                <th style="width:200px">医生</th>
                                <th>诊断人数</th>
                                <th></th>
                            </tr>
                            <tbody id="topStockUser2">


                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


<div class="container market-detail">
    <div class="row bg-white">


        <div class="col-xs-12 col-sm-12 col-md-12">
            <div id="bills" style="width: 100%;height: 240px;"></div>
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
<script src="${pageContext.request.contextPath}/vendor/moment/moment.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/echarts/echarts.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/vendor/data/common.js"></script>
<script src="js/tuxing.js"></script>
<script>
    $(function () {
        $("#start").css("display","none");
        $("#end").css("display","none");
        $("#choosemonth").css("display","none");
    });
    $("#year").click(function(){
        var start=document.getElementById('start');
        var end=document.getElementById('end');
        var choosemonth = document.getElementById('choosemonth');
        if(start.style.display=="none" && end.style.display=="none"){
            start.style.display="block";
            end.style.display="block";
        }else{
            start.style.display="none";
            end.style.display="none";
        }
            choosemonth.style.display="none";
            document.getElementById('yeartime').value="";
    });
    $("#month").click(function(){
        // $("#choosemonth").show();
        // $("#start").css("display","none");
        // $("#end").css("display","none");
        var start=document.getElementById('start');
        var end=document.getElementById('end');
        var choosemonth = document.getElementById('choosemonth');
        if(choosemonth.style.display=="none"){
            choosemonth.style.display="block";
        }else{
            choosemonth.style.display="none";
        }
        start.style.display="none";
        end.style.display="none";
        document.getElementById('starttime').value="";
        document.getElementById('endtime').value="";
    });
</script>
</html>
