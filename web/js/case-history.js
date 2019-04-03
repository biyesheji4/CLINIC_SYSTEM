$(function() {
    var id = $("#registeredid").val();
    $.ajax({
        async: false,
        type: "POST",
        url: "beforbingli.do",
        dataType: "json",
        data: {"id": id},
        success: function (result) {
            var text = "";
            var bingli = result.bingli;
                for (var i = 0; i < bingli.length; i++) {
                    text +=
                        "<div class=\"col-md-6 col-sm-6 col-xs-12\" >" +
                        "<div class=\"panel panel-info\">" +
                        "<div class=\"panel-heading\" style=\"text-align: center\">" +
                        "病例详情" +
                        "</div>" +
                        "<div class=\"panel-body\">" +
                        "<form role=\"form\">" +
                        "<div class=\"baseInfo\">" +
                        "<div class=\"baseRow1\">" +
                        "<span class=\"number\">身份证号</span> <input  type=\"text\" class=\"infoInput\" data-provide=\"typeahead\" value=" + bingli[i].programme.registered.patientIdcard + ">" +
                        "<span class=\"name\">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span>  <input  type=\"text\" class=\"infoInput\" value=" + bingli[i].programme.registered.patientName + ">" +
                        "</div>" +
                        "<div class=\"baseRow1\">" +
                        "<span class=\"number\">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</span> <input  type=\"text\" class=\"infoInput\" value=" + bingli[i].programme.registered.patientSex + ">" +
                        "<span class=\"name\">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄</span> <input  type=\"text\" class=\"infoInput\" value=" + bingli[i].programme.registered.patientAge + ">" +
                        "</div>" +
                        "<div class=\"baseRow1\">" +
                        "<span class=\"number\">就诊时间</span> <input  type=\"text\" class=\"infoInput\" value=" + bingli[i].casesDate + ">" +
                        "<span class=\"name\">主治医生</span> <input  type=\"text\" class=\"infoInput\" value=" + bingli[i].employee.employeeName + ">" +
                        "</div>" +
                        "</div>" +
                        "<div class=\"form-group\">" +
                        "<label>现病史</label>" +
                        "<textarea class=\"form-control\" rows=\"5\">" + bingli[i].casesContent + "</textarea>" +
                        "</div>" +
                        "</form>" +
                        "</div>" +
                        "</div>" +
                        "</div>";
                    $("#beforbingli").html(text);
                }
            }
    });
});