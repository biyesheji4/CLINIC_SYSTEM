 $(function () {
            $("#form").bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    patientIdcard: {
                        message: '身份证验证失败',
                        validators: {
                            notEmpty: {
                                message: '身份证号不能为空'
                            },
                            stringLength: {
                                min: 18,
                                max: 18,
                                message: '身份证号不符合要求'
                            }
                        }
                    },
                    patientName: {
                        message: '姓名验证失败',
                        validators: {
                            notEmpty: {
                                message: '姓名不能为空'
                            },
                            regexp: {
                                regexp:/^[\u4E00-\u9FFF]+$/,
                                message: '姓名不符合规范'
                            }
                        }
                    },
                    patientAge: {
                        message: '年龄验证失败',
                        validators: {
                            notEmpty: {
                                message: '年龄不能为空'
                            },
                            regexp: {
                                regexp:/^\d{1,2}$/,
                                message: '年龄不符合规范'
                            }
                        }
                    },
                    patientTel: {
                        message: '联系方式验证失败',
                        validators: {
                            notEmpty: {
                                message: '联系方式不能为空'
                            },
                            regexp: {
                                regexp:/^1[34578]\d{9}$/,
                                message: '联系方式不符合规范'
                            }
                        }
                    }
                }
            });
            $("#add").click(function () {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
                $("#form").bootstrapValidator('validate');//提交验证
                if ($("#form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
                    var patientIdcard = $("#patientIdcard").val();
                    var patientName = $("#patientName").val();
                    var patientAge = $("#patientAge").val();
                    var patientTel = $("#tel").val();
                    var registeredContent = $("#content").val();
                    var departmentsName = $("#departmentId").val();
                    if (departmentsName == ""){
                        swal("",
                            "请选择一个科室进行提交！",
                            "error");
                    }else {
                        $('input[name="sex"]:checked').each(function(){
                            var patientSex = $(this).val();
                            $.ajax({
                                async:false,
                                type: "POST",
                                url: "addRegistered.do",
                                dataType: "json",
                                data: {"patientIdcard":patientIdcard,"patientName": patientName,"patientAge": patientAge,"patientTel": patientTel,"registeredContent": registeredContent,"departmentsName": departmentsName,"patientSex": patientSex},
                                success: function (result) {
                                    swal({
                                        title: "",      //弹出框的title
                                        text: result.id+"号挂号单提交成功",   //弹出框里面的提示文本
                                        type: "success",        //弹出框类型
                                        confirmButtonColor: "#81ccee",//确定按钮颜色
                                        confirmButtonText: "确定",//确定按钮上面的文档
                                        closeOnConfirm: true
                                    },function(){
                                        window.open("redirect.do?view=registered-add",target="mainFrame");
                                    });
                                }
                            });
                        });
                    }

                }
            });
        });

function find() {
    var patientIdcard = $("#patientIdcard").val();
    $.ajax({
        async:false,
        type: "POST",
        url: "query-registeredByIdcard.do",//进入RegisteredController执行query-registeredByIdcard.do进行根据身份证查询
        dataType: "json",
        data: {"patientIdcard":patientIdcard},
        success: function (result) {
            $("#patientName").val(result.registered[0].patientName);
            $("#patientAge").val(result.registered[0].patientAge);
            $("#tel").val(result.registered[0].patientTel);
            if(result.registered[0].patientSex =="男"){
                var jQ_a = $('#nan');
                jQ_a.prop('checked','checked');
            }else {
                var jQ_b = $("#nv");
                jQ_b.prop('checked','checked');
            }
        }
    });
}