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
                        "<div  style='border: 1px solid #428bca;    margin-top: 50px;'>"+
                        "<div id=\"myTabContent\" class=\"tab-content\">\n" +
                        "                <div class=\"tab-pane fade in active\" id=\"executed\">\n" +
                        "                    <div class=\"tab-head\">\n" +
                        "                        <span class=\"listline\">患者信息</span>\n" +
                        "                        <div class=\"tab-body\">\n" +
                        "                            <form class=\"myInput-form\">\n" +
                        "                                <div class=\"b-block\">\n" +
                        "                                    <div class=\"form-group\">\n" +
                        "                                        <label ><font>*</font>身份证号：</label>\n" +
                        "                                        <input  type=\"text\" class=\"infoInput\" data-provide=\"typeahead\" value=" + bingli[i].programme.registered.patientIdcard + ">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"b-block\">\n" +
                        "                                    <div class=\"form-group\">\n" +
                        "                                        <label class=\"\"><font>*</font>患者姓名：</label>\n" +
                        "                                        <input  type=\"text\" class=\"infoInput\" data-provide=\"typeahead\" value=" + bingli[i].programme.registered.patientName + ">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"b-block\">\n" +
                        "                                    <div class=\"form-group\">\n" +
                        "                                        <label class=\"\"><font>*</font>患者性别：</label>\n" +
                        "                                        <input  type=\"text\" class=\"infoInput\" data-provide=\"typeahead\"  value=" + bingli[i].programme.registered.patientSex + ">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"b-block\">\n" +
                        "                                    <div class=\"form-group\">\n" +
                        "                                        <label><font>*</font>年龄：</label>\n" +
                        "                                        <input  type=\"text\" class=\"infoInput\" data-provide=\"typeahead\" value=" + bingli[i].programme.registered.patientAge + " >\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"b-block\">\n" +
                        "                                    <div class=\"form-group\">\n" +
                        "                                        <label><font>*</font>就诊时间：</label>\n" +
                        "                                        <input  type=\"text\" class=\"infoInput\" value=" + bingli[i].casesDate + ">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"b-block\">\n" +
                        "                                    <div class=\"form-group\">\n" +
                        "                                        <label><font>*</font>主治医生：</label>\n" +
                        "                                        <input  type=\"text\"class=\"infoInput\" data-provide=\"typeahead\"  value=" + bingli[i].employee.employeeName + ">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </form>\n" +
                        "                        </div><!--tab-body结束-->\n" +
                        "                        <span class=\"listline\">现病史</span>\n" +
                        "                        <div class=\"tab-body zqinfo\" style=\"height: 20%\">\n" +
                        "                                <textarea class=\"form-control\" rows=\"5\" id=\"content\" name=\"content\">"+ bingli[i].casesContent +"</textarea>\n" +
                        "                        </div><!--tab-body结束-->"+
                        " <span class=\"listline\">诊疗方案</span>\n" +
                        " <div class=\"tab-body zqinfo\">\n";
                                            if(bingli[i].programme.programmeProjects==null){
                                                text+="<textarea class=\"form-control\" rows=\"5\" id=\"programme\" name=\"programme\">"+ bingli[i].programme.programmeMedicine+"</textarea>\n" ;
                                            }else {
                                                text+="<textarea class=\"form-control\" rows=\"5\" id=\"programme\" name=\"programme\">"+ bingli[i].programme.programmeMedicine+";"+ bingli[i].programme.programmeProjects+"</textarea>\n" ;
                                            }
                    text+= "</div><!--tab-body结束-->"+
                            "</div>";
                    $("#beforbingli").html(text);
                }
            }
    });
});