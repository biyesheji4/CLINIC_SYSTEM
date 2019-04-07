$(function() {

    var param={};

    var sort = "id";/*排序*/
    var sortOrder="asc";
    //************************start******************************//
    jQuery.extend({
        tableDetailRefresh:function(){
            console.debug(param);
            $('#datas').bootstrapTable('refresh');
            $('#datas').bootstrapTable({
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
                pageSize: 8,
                //可供选择的每页的行数（*）
                pageList: [8],
                //是否显示分页
                pagination:true,
                showPaginationSwitch:false,
                //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
                url: "medicines.do",
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
                showColumns: true,
                showRefresh: true,                  //是否显示刷新按钮
                showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
                //是否显示搜索
                search: false,
                //指定主键列
                idField : "id",
                columns: [
                    {
                        field:'id',
                        title:'编号',
                        align:'center',
                    },
                    {
                        field: 'medicinesName',
                        title: '药品名称',
                        align: 'center'
                    },
                    {
                        field: 'medicinesType',
                        title: '药品类别',
                        align: 'center'
                    },
                    {
                        field: 'medicinesInputdate',
                        title: '药品进库时间',
                        align: 'center'
                    },
                    {
                        field: 'medicinesDate',
                        title: '药品状态',
                        align: 'center',
                        formatter:function(value,row,index){
                            var guoqi;
                            $.ajax({
                                async:false,
                                type: "POST",
                                url: "Datetime.do",
                                dataType: "json",
                                data: {"medicinesDate": value},
                                success:function (result) {
                                    if(result.b<=result.a){
                                        guoqi = '<span style="color:#3e8f3e;">可正常使用</span>';
                                    }else {
                                        if(result.c <= result.d){
                                            guoqi ='<span style="color:#c12e2a;">已经过期，请及时处理</span>';
                                        }else {
                                            guoqi ='<span style="color:#ffc107;">即将过期，请注意使用</span>';
                                        }
                                    }
                                }
                            });
                           return guoqi;
                        }
                    },
                    {
                        field: 'medicinesNum',
                        title: '药品库存数量',
                        align: 'center'
                    },
                    // {
                    //     field: 'medicinesLimit',
                    //     title: '药品库存下限',
                    //     align: 'center',
                    //     formatter:function(value,row,index){
                    //         if(value>=row.medicinesNum){
                    //             return "<p class='text-danger'>库存不足</p>";
                    //         }else if(value<row.medicinesNum){
                    //             return "<p class='text-primary'>库存充足</p>";
                    //         }
                    //     }
                    // },
                    {
                        field: 'medicinesMoney',
                        title: '药品售价（元）',
                        align: 'center'
                    },
                    {
                        field: 'medicinesBid',
                        title: '药品进价（元）',
                        align: 'center'
                    },
                    {
                        field: '',
                        title: '操作',
                        align: 'center',
                        formatter:function(value,row,index){
                            var ccc;
                            $.ajax({
                                async:false,
                                type: "POST",
                                url: "Datetime.do",
                                dataType: "json",
                                data: {"medicinesDate": row.medicinesDate},
                                success:function (result) {
                                    if(result.b<=result.a){
                                        ccc = '<button type="button" class="btn btn-sm btn-default" onclick="share('+row.id+')">出库</button>';
                                    }else {
                                        if(result.c <= result.d){
                                            ccc = '<button type="button" class="btn btn-sm btn-default" onclick="past()">出库</button>';
                                        }else {
                                            ccc = '<button type="button" class="btn btn-sm btn-default" onclick="share('+row.id+')">出库</button>';
                                        }
                                    }
                                }
                            });
                            var d = '<button type="button" class="btn btn-sm btn-default" onclick="del('+row.id+')"><span class="glyphicon glyphicon-trash" ></button>';
                            return ccc+d;
                        }
                    }
                ],
            });

        }
    });

    /**
     * 进入该页面响应&&点击搜索按钮响应
     */

    $("#search").click(function(){
            data = $("#calendar").val();
            type = $("#type").val();
            var name = $("#name").val();
            param = {"sort":sort,"sortOrder":sortOrder,"data":data,"type":type,"name":name};
            //alert(JSON.stringify(param));
            $.tableDetailRefresh();
            querymedicinesSum();
    });
    $("#search").click();

    /**
     * 判断是否有过期
     */
    function querymedicinesSum() {
        var text="<font  face='隶书'  color='#FFFFFF'  size='5' >库存不足药品：</font>";
        $.ajax({
            async: false,
            type: "POST",
            url: "querymedicinesSum.do",
            dataType: "json",
            success: function (result) {
              for(var i=0;i<result.medicines.length;i++){
                  if(result.medicines[i].medicinesNum<result.medicines[i].medicinesLimit){
                        text+= "<font  face='隶书'  color='#FFFFFF'  size='5' >"+result.medicines[i].medicinesType+" 的 "+result.medicines[i].medicinesName+"      ；</font>";
                  }
              }
              $("#marquee").html(text);
            }
        });
    }
});

//进入药品进库模态框
$("#btn_add").click(function () {
    $("#medicinesinputModal").modal("toggle");
});



//实时监听
function yun() {
    var num = $("#medicinesNum").val();
    var mo = $("#medicinesBid").val();
    var a = num * mo ;
    $("#mon").html("该药品支出："+a+"元");
}

/**
 * 药品进库模态框确定按钮响应
 */

function formValidator() {
    $("#form").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            medicinesName: {
                message: '药品名称验证失败',
                validators: {
                    notEmpty: {
                        message: '药品名称不能为空'
                    }
                }
            },
            medicinesBid: {
                message: '进价（元）验证失败',
                validators: {
                    notEmpty: {
                        message: '进价不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9]d*.d*|0.d*[1-9]d*$/,
                        message: '进价不符合规范'
                    }
                }
            },
            medicinesNum: {
                message: '进货数量验证失败',
                validators: {
                    notEmpty: {
                        message: '进货数量不能为空'
                    },
                    regexp: {
                        regexp: /^\d{1,10000}$/,
                        message: '进货数量不符合规范'
                    }
                }
            },
            medicinesMoney: {
                message: '售价验证失败',
                validators: {
                    notEmpty: {
                        message: '售价不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9]d*.d*|0.d*[1-9]d*$/,
                        message: '售价不符合规范'
                    }
                }
            },
            medicinesLimit: {
                message: '库存告警量验证失败',
                validators: {
                    notEmpty: {
                        message: '库存告警量不能为空'
                    },
                    regexp: {
                        regexp: /^\d{1,10000}$/,
                        message: '库存告警量不符合规范'
                    }
                }
            }
        }
    });
}

//初始化表单验证
$(document).ready(function() {
    formValidator();
});

$('#medicinesinputModal').on('hidden.bs.modal', function() {
    $("#form").data('bootstrapValidator').destroy();
    $("#form").data('bootstrapValidator', null);
    document.getElementById("form").reset();
    formValidator();
});

$("#insertmedicines").click(function () {
    $("#form").bootstrapValidator('validate');//提交验证
    if ($("#form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
        var medicinesName = $("#medicinesName").val();
        var medicinesType = $("#medicinesType").val();
        var medicinesNum = $("#medicinesNum").val();
        var medicinesLimit = $("#medicinesLimit").val();
        var medicinesMoney = $("#medicinesMoney").val();
        var medicinesBid = $("#medicinesBid").val();
        var medicinesDate = $("#medicinesDate").val();
        var employee_num = $("#employee_num").val();
        var employee_id = $("#employee_id").val();
        var medicinesBatch = $("#medicinesBatch").val();
        var mon = medicinesNum * medicinesBid;
            $.ajax({
                async: false,
                type: "POST",
                url: "addmedicines.do",
                dataType: "json",
                data: {
                    "medicinesName": medicinesName,
                    "medicinesType": medicinesType,
                    "medicinesNum": medicinesNum,
                    "medicinesLimit": medicinesLimit,
                    "medicinesMoney": medicinesMoney,
                    "medicinesBid": medicinesBid,
                    "medicinesDate": medicinesDate,
                    "bill_mon": mon,
                    "employee_num": employee_num,
                    "employee_id": employee_id,
                    "medicinesBatch":medicinesBatch
                },
                success: function (result) {
                    swal("",
                        "提交成功！",
                        "success");
                    $('#medicinesinputModal').modal('hide');
                    $("#search").click();
                }
            });
        }
});

function queryIsExistence() {
    var medicinesName = $("#medicinesName").val();
    var medicinesType = $("#medicinesType").val();
    $.ajax({
        async: false,
        type: "POST",
        url: "querymedicines.do",
        dataType: "json",
        data: {
            "medicinesName": medicinesName,
            "medicinesType": medicinesType
        },
        success: function (result) {
            if(result.medicines.length>0){
                var querym=document.getElementById('querym');
                if(querym.style.display=="block"){
                    querym.style.display="none";
                }
                $("#medicinesLimit").val(result.medicines[0].medicinesLimit);
                $("#medicinesBatch").val(result.medicines[result.medicines.length-1].medicinesBatch+1);
            }else {
                var querym=document.getElementById('querym');
                if(querym.style.display=="none"){
                    querym.style.display="block";
                }
                $("#medicinesLimit").val("");

            }

        }
    });
}

/**
 * 删除单个过期药品
 * @param id
 */
function del(id) {
    var deltype = "1";/*表示执行的对过期药品的删除*/
    swal({
        title: "",      //弹出框的title
        text: "确定删除 "+id+" 号药品吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，删除！",//确定按钮上面的文档
        closeOnConfirm: false
    },function() {
        $.ajax({
            type: "post",
            url: "deletemedicine.do",
            data: {
                "id": id,"deltype":deltype
            },
            success: function (data) {
                swal("",
                    "删除成功！",
                    "success");
                $("#search").click();      //删除过后需要跳转的页面URL.
            }
        });
    });
}

// //复选框实现多条删除
// $("#btn_delete").click(function () {
//     var a= $("#datas").bootstrapTable('getSelections', function (row) {
//         return row;
//     });//取到选择框一行的数据。
//     if(a.length<=0) {
//         alert("请选中一行");
//     }else{
//         var str = "";
//         for (var i=0;i<a.length;i++){
//             if (str==""){
//                 str = a[i].id;
//             } else {
//                 str+=","+a[i].id;
//             }
//         }
//         if (str == "")
//             alert("请选择您要删除的信息！");
//         else {
//             if (confirm("您确定要删除吗？")) {
//                 $.ajax({
//                     type : "post",
//                     url : "deletemedicine.do",
//                     data : {
//                         "id" : str
//                     },
//                     success : function(data) {
//                         $("#search").click();     //删除过后需要跳转的页面URL.
//                     }
//                 });
//             }
//         }
//     }
// });

//过期药品出库按钮响应
function past() {
    swal("",
        "药品已经过期，无法出库，请及时处理！",
        "error");
}

/**
 * 进入出库模态框
 * @param id
 */
function share(id) {
    $("#medicinesshareModal").modal("toggle");
    var text ="";
    $.ajax({
        async:false,
        type: "POST",
        url: "querymedicinesByid.do",
        dataType: "json",
        data: {"id": id},
        success: function (result) {
            text +=
        "<div >" +
            "<div class=\"col-md-12 col-sm-6 col-xs-12\">" +
                "<div class=\"panel panel-info\">" +
                    "<div class=\"panel-heading\" style=\"text-align: center;background-image:linear-gradient(to bottom, #007bff 0%, #c4e3f3 100%)\">" +
                        "编号："+result.medicines.id+"  "+result.medicines.medicinesName +"出库" +
                    "</div>"+
                    "<div class=\"panel-body\">"+
                        "<div class=\"baseInfo\">"+
                                "<input  type='hidden' class=\"infoInput\" data-provide=\"typeahead\"  value="+result.medicines.medicinesBid+" id='medicinesbid'>"+
                                "<input  type='hidden' class=\"infoInput\" value="+result.medicines.medicinesMoney+" id='medicinesmoney'>"+
                            "<div class=\"baseRow1\">"+
                                "<span class=\"number\">出库数量</span> <input  type=\"text\" class=\"infoInput\" data-provide=\"typeahead\"  id=\"sharenum\">"+
                                "<span class=\"name\"></span> <input  type='hidden' class=\"infoInput\" id='medicinesid' value="+result.medicines.id+"> <input  type='hidden' class=\"infoInput\" id='bill_mon'>"+
                            "</div>"+
                            "<input  type='hidden' class=\"infoInput\" id='medicinesnum' value="+result.medicines.medicinesNum+">"+
                            "<input  type='hidden' class=\"infoInput\" id='medicinesname' value="+result.medicines.medicinesName+">"+
                        "</div>"+
                    "</div>"+
                "</div>"+
            "</div>"+
        "</div>";
            $("#share").html(text);
        }
    });
}

/**
 * 对药品进行出库（在模态框进行的操作）
 */
$("#medicineshare").click(function () {
    var employee_num = $("#employee_num").val();
    var employee_id = $("#employee_id").val();
    var id = $("#medicinesid").val();
    var medicinesname = $("#medicinesname").val();
    var Num = $("#medicinesnum").val();
    var sharenum = $("#sharenum").val();
    var medicinesNum = Num-sharenum;
    if(sharenum ==""||sharenum<=0){
        swal("警告",
            "出库数量有误，请重新操作！",
            "error");
    }else {
        if(medicinesNum<0){
            swal("",
                "出库数量多于库存数量，请结合其他批次药品出库！",
                "error");
        }else if(medicinesNum ==0){
            var deltype = "2";
            $.ajax({
                type : "post",
                url : "deletemedicine.do",
                data : {
                    "id" : id,"deltype":deltype
                },
                success : function(data) {
                    swal("",
                        "出库成功！",
                        "success");
                    $("#search").click();      //删除过后需要跳转的页面URL.
                }
            });
        }else {
            var medicinesBid = $("#medicinesbid").val();
            var medicinesMoney = $("#medicinesmoney").val();
            var bill_mon = sharenum*(medicinesMoney-medicinesBid);
            $.ajax({
                async: false,
                type: "POST",
                url: "share.do",
                dataType: "json",
                data: {"id": id,"medicinesNum":medicinesNum,"bill_mon":bill_mon,"medicinesname":medicinesname,"employee_id":employee_id,"employee_num":employee_num},
                success: function (result) {
                    swal("",
                        "出库成功！",
                        "success");
                    $("#search").click();      //出库确定后刷新页面.
                }
            });
        }
    }
});
