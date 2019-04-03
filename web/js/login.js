
$(function () {
    $("#loginform").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                }
            },
            password: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                }
            },
            veryCode: {
                message: '验证码验证失败',
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 4,
                        message: '验证码要求填写四位'
                    }
                }
            },
        }
    });


    $("#login").click(function () {
        $("#loginform").bootstrapValidator('validate');//提交验证
        if ($("#loginform").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var username = $("#username").val();
            var password = $("#password").val();
            var veryCode = $("#veryCode").val();
            $.ajax({
                async: false,
                type: "POST",
                url: "logins.do",
                dataType: "json",
                data: {"username": username, "password": password, "veryCode": veryCode},
                success: function (result) {
                    if (result.view == "false") {
                        swal("",
                            "验证码出错！",
                            "error");
                        changeImg();
                    } else if (result.view == "usernotexist") {
                        swal("",
                            "用户名，密码输入错误 或 该用户不存在！",
                            "error");
                        changeImg();
                        return false;
                    } else {
                        window.location.href = "redirect.do?view=" + result.view;
                    }
                }
            });
        }
    });

});
function changeImg(){
    var img=document.getElementById("imgObj");
    img.src="xuan/verifyCode.do?d="+Math.random();
}

/**
 * 回车响应事件
 */
$("body").keydown(function() {
    if (event.keyCode == "13") {
        $("#login").click();
        return false
    }
});