$(function() {

    var param={};

    var sort = "id";/*排序*/
    var sortOrder="asc";
    //************************start******************************//
    jQuery.extend({
        tableDetailRefresh:function(){
            console.debug(param);
            $('#employeedatas').bootstrapTable('refresh');
            $('#employeedatas').bootstrapTable({
                //请求方法
                method: 'post',
                //是否显示行间隔色
                striped: true,
                //设置post提交的编码格式
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                cache: false,
                //分页条显示中文
                locale:'zh-CN',
                toolbar: '#toolbar',                //工具按钮用哪个容器
                //是否启用排序
                sorttable: true,
                //排序方式
                sortOrder: "asc",
                //初始化加载第一页，默认第一页
                pageNumber:1,
                //每页的记录行数（*）
                pageSize: 6,
                //可供选择的每页的行数（*）
                pageList: [6],
                //是否显示分页
                pagination:true,
                showPaginationSwitch:false,
                //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
                url: "getEmployeeAll.do",
                //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
                queryParams : function(params) {
                    return {
                        pageNumber: params.offset,
                        pageSize: params.limit,
                        param:param,

                    };
                },
                //分页方式：client客户端分页，server服务端分页（*）
                sidePagination: "server",
                //是否显示搜索
                search: false,
                //指定主键列
                idField : "id",
                showExport: false,                     //是否显示导出
                exportDataType: "basic",              //basic', 'all', 'selected'.
                showColumns: true,
                showRefresh: true,                  //是否显示刷新按钮
                showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showPaginationSwitch:false,
                columns: [
                    // {
                    //     checkbox:true
                    // },
                    {
                        field: 'employeeName',
                        title: '员工名称',
                        align: 'center',
                        formatter:function (value,row,index) {
                            var op = "<a  target='_blank'  onclick=query('query-employee.do?id=" + row.id + "')>" + value + "</a>";
                            return op;
                        }
                    },
                    {
                        field: 'employeeNum',
                        title: '员工编号',
                        align: 'center',
                    },
                    {
                        field: 'employeeDate',
                        title: '员工入职时间',
                        align: 'center',
                    },
                    {
                        field: 'employeeOccupation',
                        title: '员工职业',
                        align: 'center',
                    },
                    {
                        field: 'employeeTel',
                        title: '员工电话',
                        align: 'center',
                    },
                    {
                        field: 'employeeEmail',
                        title: '员工邮箱',
                        align: 'center',
                    },
                    {
                        field: 'employeeSalary',
                        title: '员工薪资',
                        align: 'center',
                    },
                    {
                        field: 'departments.departmentsName',
                        title: '所属科室',
                        align: 'center',
                        formatter:function(value,row,index){
                            if(value==null){
                                return "-";
                            }
                            else{
                                return value;
                            }

                        }
                    },
                    {
                        field: '',
                        title: '操作',
                        align: 'center',
                        formatter:function(value,row,index){
                            if(row.employeeState == 1){
                                var a = '<button type="button" class="btn btn-sm btn-default" onclick="update('+row.id+')"><span class="glyphicon glyphicon-pencil" > </span></button>';
                                var d = '<button type="button" class="btn btn-sm btn-default" onclick="del('+row.id+')"><span class="glyphicon glyphicon-trash" ></button>';
                                return a+d
                            }
                            else {
                                var c = '<button type="button" class="btn btn-sm btn-default" onclick="recovery('+row.id+')"><span class="glyphicon glyphicon-share-alt" ></button>';
                                return c
                            }

                        }
                    }],

            });

        }
    });


    $("#search").click(function(){
            state =$("#state").val();
            textarea = $("#selectDepartmentId").val();
            param = {"sort":sort,"sortOrder":sortOrder,"textarea":textarea,"state":state};
            //alert(JSON.stringify(param));
            $.tableDetailRefresh();
    });
    $("#search").click();

});


function query(url) {
    var iWidth = 1000; //弹出窗口的宽度;
    var iHeight = 500; //弹出窗口的高度;
    var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
    myWindow = window.open(url, '', 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',scrollbars=yes');
    myWindow.focus();
}

/**
 * 对离职员工进行恢复
 * @param id
 */
function recovery(id) {
    swal({
        title: "",      //弹出框的title
        text: "确定对离职员工进行恢复吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定！",//确定按钮上面的文档
        closeOnConfirm: true
    },function(){
        $.ajax({
            type: "POST",
            url: "recovery.do",
            dataType: "json",
            data: {"id": id},
            success: function (result) {
                swal("",
                    "员工信息恢复成功！",
                    "success");
                $("#search").click();
            }
        });
    });


}

/**
 * 进入员工信息更新模态框
 * @param id
 */
function  update(id) {
    $("#updateemployeeModal").modal("toggle");
    $.ajax({
        // async:false,
        type: "POST",
        url: "input-employeeupdate.do",
        dataType: "json",
        data: {"id": id},
        success: function (result) {
            //赋值
            $("#id").val(result.employee.id);
            $("#updateemployeeNum").val(result.employee.employeeNum);
            $("#updateemployeeName").val(result.employee.employeeName);
            $("#updateemployeeOccupation").val(result.employee.employeeOccupation);
            $("#updateemployeeBirth").val(result.employee.employeeBirth);
            $("#updateemployeeTel").val(result.employee.employeeTel);
            $("#updateemployeeEmail").val(result.employee.employeeEmail);
            $("#updateemployeeSalary").val(result.employee.employeeSalary);
            $("#updateemployeeDate").val(result.employee.employeeDate);

            if(result.employee.employeeSex =="男"){
                var jQ_a = $('#nan');
                jQ_a.prop('checked','checked');
            }else {
                var jQ_b = $("#nv");
                jQ_b.prop('checked','checked');
            }
            var select = " <option value=''>--请选择--</option>";
            for (var i = 0; i < result.departmentsAll.length; i++) {
                select += "<option value=" + result.departmentsAll[i].id + ">" + result.departmentsAll[i].departmentsName + "</option>"
            }
            $("#updatedepartmentId").html(select);

            /*以下代码是判断选中的下拉框*/
            if (result.one != null) {
                for (var j = 0; j < result.one.length; j++) {
                    var id = result.one[j].id;
                }
                var options = $("#updatedepartmentId option")
                for (var x = 0; x < options.length; x++) {
                    var option = options.eq(x);
                    if (option.val() == id) {
                        option.attr("selected", true);
                    }
                }
            }
        }
    });
}


/**
 * 更新员工信息验证
 */
function formValidator() {
    $("#updateform").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            employeeNum: {
                message: '编号验证失败',
                validators: {
                    notEmpty: {
                        message: '编号不能为空'
                    },
                    regexp: {
                        regexp: /^\d{4}$/,
                        message: '编号由4位数字组成'
                    }
                }
            },
            employeeName: {
                message: '姓名验证失败',
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    regexp: {
                        regexp: /^[\u4E00-\u9FFF]+$/,
                        message: '姓名不符合规范'
                    }
                }
            },
            employeeEmail: {
                message: '邮箱验证失败',
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            },
            employeeSalary: {
                message: '工资验证失败',
                validators: {
                    notEmpty: {
                        message: '工资不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '工资只能有数字'
                    }
                }
            },
            employeeTel: {
                message: '联系方式验证失败',
                validators: {
                    notEmpty: {
                        message: '联系方式不能为空'
                    },
                    regexp: {
                        regexp: /^1[34578]\d{9}$/,
                        message: '联系方式不符合规范'
                    }
                }
            },
            employeeOccupation: {
                message: '员工职位验证失败',
                validators: {
                    notEmpty: {
                        message: '职位不能为空'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5]{0,}$/,
                        message: '联系方式不符合规范'
                    }
                }
            }
        }
    });
}

/**
 * 更新员工信息提交
 */
    $("#update").click(function () {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
        $("#updateform").bootstrapValidator('validate');//提交验证
        if ($("#updateform").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var id = $("#id").val();
            var employeeName = $("#updateemployeeName").val();
            var employeeNum = $("#updateemployeeNum").val();
            var employeeOccupation = $("#updateemployeeOccupation").val();
            var employeeBirth = $("#updateemployeeBirth").val();
            var employeeTel  = $("#updateemployeeTel").val();
            var employeeEmail = $("#updateemployeeEmail").val();
            var employeeSalary = $("#updateemployeeSalary").val();
            var departmentsName = $("#updatedepartmentId").val();
            if(departmentsName==''){
                swal({
                    title: "",      //弹出框的title
                    text: "请选择一个科室！否则无法提交！",   //弹出框里面的提示文本
                    type: "error",        //弹出框类型
                    button: "确定",
                });
                return false;
            }else{
                    $('input[name="sex"]:checked').each(function(){
                        var employeeSex = $(this).val();
                        $.ajax({
                            // async:false,
                            type: "POST",
                            url: "updateEmployee.do",
                            dataType: "json",
                            data: {"id": id,"employeeName": employeeName,"employeeNum": employeeNum,"employeeOccupation": employeeOccupation,"employeeBirth": employeeBirth,"employeeTel":employeeTel,"employeeEmail": employeeEmail,"employeeSalary": employeeSalary,"departmentsName": departmentsName,"employeeSex": employeeSex},
                            success: function (result) {
                                swal("",
                                    "更新成功！",
                                    "success");
                                $('#updateemployeeModal').modal('hide');
                                $("#search").click();
                            }
                        });
                    });
                }
        }
    });

/**
 * 更新员工信息模态框关闭时刷新验证
 */
$('#updateemployeeModal').on('hidden.bs.modal', function() {
    $("#updateform").data('bootstrapValidator').destroy();
    $("#updateform").data('bootstrapValidator', null);
    document.getElementById("updateform").reset();
    formValidator();
});



/**
 * 点击employee.jsp页面的 新增 按钮 模态框显示
 */
$("#btn_add").click(function () {
    $("#inputModal").modal("toggle");
});




/**
 * 新增员工验证
 */
function forminsertValidator() {
    $("#form").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            employeeNum: {
                message: '编号验证失败',
                validators: {
                    notEmpty: {
                        message: '编号不能为空'
                    },
                    regexp: {
                        regexp: /^\d{4}$/,
                        message: '编号由4位数字组成'
                    }
                }
            },
            employeeName: {
                message: '姓名验证失败',
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    regexp: {
                        regexp: /^[\u4E00-\u9FFF]+$/,
                        message: '姓名不符合规范'
                    }
                }
            },
            employeeEmail: {
                message: '邮箱验证失败',
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            },
            employeeSalary: {
                message: '工资验证失败',
                validators: {
                    notEmpty: {
                        message: '工资不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '工资只能有数字'
                    }
                }
            },
            employeeTel: {
                message: '联系方式验证失败',
                validators: {
                    notEmpty: {
                        message: '联系方式不能为空'
                    },
                    regexp: {
                        regexp: /^1[34578]\d{9}$/,
                        message: '联系方式不符合规范'
                    }
                }
            },
            employeeOccupation: {
                message: '员工职位验证失败',
                validators: {
                    notEmpty: {
                        message: '职位不能为空'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5]{0,}$/,
                        message: '联系方式不符合规范'
                    }
                }
            }
        }
    });
}

/**
 * 新增员工信息提交
 */
    $("#add").click(function ()  {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
        $("#form").bootstrapValidator('validate');//提交验证
        if ($("#form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var employeeNum = $("#employeeNum").val();
            var employeeName = $("#employeeName").val();
            var employeeBirth = $("#employeeBirth").val();
            var employeeEmail = $("#employeeEmail").val();
            var employeeSalary = $("#employeeSalary").val();
            var employeeDate = $("#employeeDate").val();
            var employeeTel = $("#employeeTel").val();
            var departmentId = $("#departmentId").val();
            var employeeOccupation = $("#employeeOccupation").val();
            if (departmentId == ""){
                swal("",
                    "请选择一个科室进行提交！",
                    "error");
        }else {
                    $('input[name="sex"]:checked').each(function () {
                        var employeeSex = $(this).val();
                        $.ajax({
                            // async:false,
                            type: "POST",
                            url: "insertEmployee.do",
                            dataType: "json",
                            data: {
                                "employeeNum": employeeNum,
                                "employeeName": employeeName,
                                "employeeBirth": employeeBirth,
                                "employeeEmail": employeeEmail,
                                "employeeSalary": employeeSalary,
                                "employeeDate": employeeDate,
                                "employeeTel": employeeTel,
                                "departmentId": departmentId,
                                "employeeSex": employeeSex,
                                "employeeOccupation": employeeOccupation
                            },
                            success: function (result) {
                                swal("",
                                    "提交成功！",
                                    "success");
                                $('#inputModal').modal('hide');
                                $("#search").click();
                            }
                        });
                    });
                }
        }
});

/**
 * 初始化表单验证
 */
$(document).ready(function() {
    formValidator();
    forminsertValidator();
});

/**新增员工信息模态框关闭时刷新验证
*/
$('#inputModal').on('hidden.bs.modal', function() {
    $(this).removeData('bs.modal');
    $("#form").data('bootstrapValidator').destroy();
    $("#form").data('bootstrapValidator', null);
    document.getElementById("form").reset();
    forminsertValidator();
});


/**
 *  删除start
 * @param id
 */
function del(id)
{
    swal({
        title: "",      //弹出框的title
        text: "确定删除吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定删除！",//确定按钮上面的文档
        closeOnConfirm: false
    },function(){
        $.ajax({
            type : "post",
            url : "deleteemployee.do",
            data : {
                "id" : id
            },
            success : function(data) {
                swal("",
                    "删除成功！",
                    "success");
                $("#search").click();      //删除过后需要跳转的页面URL.
            }
        });
    });
}