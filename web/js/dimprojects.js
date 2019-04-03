$(function() {

    var dimparam={};
    var sort="id";
    var sortOrder="asc";
    //************************start******************************//
    jQuery.extend({
        tableprojectRefresh:function(){
            console.debug(dimparam);
            $('#projectsData').bootstrapTable('refresh');
            $('#projectsData').bootstrapTable({
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
                url: "project/projectBypage.do",
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
                //指定主键列
                idField : "id",
                columns: [
                    {
                        field: 'id',
                        title: '选择',
                        align: 'center',
                        width:'72px',
                        formatter:function(value,row,index){
                            return '<input type="radio" name="selectname" value="'+value+'" text="'+row.id+'">';
                        }
                    },
                    {
                        field:'id',
                        title:'编号',
                        align:'center',
                    },
                    {
                        field: 'projectsName',
                        title: '诊疗项目名称',
                        align: 'center'
                    },
                    {
                        field: 'projectsContent',
                        title: '诊疗项目概述',
                        align: 'center'
                    },
                    {
                        field: 'projectsRemarks',
                        title: '诊疗项目备注',
                        align: 'center'
                    },

                ],
            });

        }
    });

    $("#Selectprojects").click(function(){
        var name = $("#name").val();
        param = {"sort":sort,"sortOrder":sortOrder,"name":name};
        $.tableprojectRefresh();
    });
});

/**
 * 请求病人 诊疗项目检查
 */
$("#Projects").click(function(){
    var id = "";
    $('input[name="selectname"]:checked').each(function(){
        id = $(this).attr("text");
    });
    if(id=="") {
        swal("出错",
            "您还未选取任何一项",
            "error");
    }else {
        var patientid = $("#registeredid").val();
        var date = $("#date").val();
        $.ajax({
            async: false,
            type: "POST",
            url: "insertprojects.do", //ProgrammeController添加诊疗方案
            dataType: "json",
            data: {
                "patientid": patientid,
                "date": date,
                "id":id
            },
            success: function (result) {
                swal({
                    title: "",      //弹出框的title
                    text: "请病人前往收费员处缴费，并进行相关检查",   //弹出框里面的提示文本
                    type: "success",        //弹出框类型
                    confirmButtonColor: "#81ccee",//确定按钮颜色
                    confirmButtonText: "确定",//确定按钮上面的文档
                    closeOnConfirm: true
                })
            }
        });
    }
});

