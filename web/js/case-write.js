/**
 * 跳转历史病例
 */
$("#before").click(function() {
    var patientid = $("#registeredid").val();
    $.ajax({
        async: false,
        type: "POST",
        url: "beforbingli.do",
        dataType: "json",
        data: {"id": patientid},
        success: function (result) {
            var bingli = result.bingli;
            if (bingli.length == 0) {
                swal("",
                    "该病人无历史病例记录！",
                    "warning");
            }else {
                window.open("redirect.do?view=case-history&registeredid="+patientid+"");
            }
        }
    });
});

/**
 * 病历单添加以及验证
 */
$(function () {
    $("#caseform").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            content: {
                message: '现病史验证失败',
                validators: {
                    notEmpty: {
                        message: '现病史不能为空'
                    },
                }
            },
            programme: {
                message: '治疗方案验证失败',
                validators: {
                    notEmpty: {
                        message: '治疗方案不能为空'
                    },
                }
            },
        }
    });

    $("#addcase").click(function () {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
        $("#caseform").bootstrapValidator('validate');//提交验证
        if ($("#caseform").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var patientid = $("#registeredid").val();
            var employeeid = $("#employee_id").val();
            var date = $("#date").val();
            var content = $("#content").val();
            var programme = $("#programme").val();
            var money = $("#money").val();
            var mid =  $("#mid").val();
            var mnum = $("#mnum").val();
            $.ajax({
                async: false,
                type: "POST",
                url: "addcase.do",
                dataType: "json",
                data: {
                    "patientid": patientid,
                    "date": date,
                    "content": content,
                    "programme": programme,
                    "employeeid": employeeid,
                    "money": money,
                    "mid":mid,
                    "mnum":mnum
                },
                success: function (result) {
                    swal({
                        title: "",      //弹出框的title
                        text: "病例提交成功",   //弹出框里面的提示文本
                        type: "success",        //弹出框类型
                        confirmButtonColor: "#81ccee",//确定按钮颜色
                        confirmButtonText: "确定",//确定按钮上面的文档
                        closeOnConfirm: true
                    });
                    $("#caseId").val(result.caseId);
                    $("#programmeId").val(result.programmeId);
                }
            });
        }
    });
});

$("#chehui").click(function () {
    var caseId = $("#caseId").val();
    var programmeId = $("#programmeId").val();
    if(caseId!=null&&caseId!=''&&programmeId!=null&&programmeId!=''){
        $.ajax({
            async: false,
            type: "POST",
            url: "recall.do",   //CasesController.java
            dataType: "json",
            data: {
                "caseId": caseId,
                "programmeId":programmeId
            },
            success: function (result) {
                if(result.msg=="撤回成功！"){
                    swal("",
                        "撤回成功！",
                        "success");
                }else {
                    swal("",
                        "撤回失败！该订单已缴费",
                        "error");
                }

            }
        });
    }else {
        swal("",
            "还未提交！",
            "error");
    }

});