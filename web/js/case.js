$(function() {

    var param={};

    var sort = "id";/*排序*/
    var sortOrder="asc";
    //************************start******************************//
    jQuery.extend({
        tableDetailRefresh:function(){
            console.debug(param);
            $('#casedatas').bootstrapTable('refresh');
            $('#casedatas').bootstrapTable({
                //请求方法
                method: 'post',
                toolbar: '#toolbar',                //工具按钮用哪个容器
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


                showExport: false,                     //是否显示导出
                exportDataType: "basic",              //basic', 'all', 'selected'.
                showColumns: true,
                showRefresh: true,                  //是否显示刷新按钮
                showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showPaginationSwitch:false,
                //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
                url: "CaseByPage.do",
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
                columns: [
                    // {
                    //     checkbox:true
                    // },
                    {
                        field:'id',
                        title:'编号',
                        align:'center',
                        sortable:true
                    },
                    {
                        field: 'programme.registered.patientName',
                        title: '患者姓名',
                        align: 'center',
                    },
                    {
                        field: 'programme.registered.patientIdcard',
                        title: '患者身份证号码',
                        align: 'center',
                    },
                    {
                        field:'casesContent',
                        title:'病例内容',
                        align:'center',
                    },
                    {
                        field: 'casesDate',
                        title: '病例书写时间',
                        align: 'center',
                    },
                    {
                        field: 'employee.employeeName',
                        title: '确诊医生',
                        align: 'center',
                    },
                    {
                        field: '',
                        title: '诊疗方案',
                        align: 'center',
                        formatter:function(value,row,index){
                           if(row.programme.programmeState != 0){
                               return row.programme.programmeMedicine
                           }else {
                               return "方案未缴费"
                           }
                        }
                    },
                    {
                        field: '',
                        title: '操作',
                        align: 'center',
                        formatter:function(value,row,index){
                            var d = '<button type="button" class="btn btn-sm btn-default" onclick="del('+row.id+')"><span class="glyphicon glyphicon-trash" ></button>';
                            return d;
                        }
                    }],
                onSort:function(name, order) {//排序判断

                    if (order == "asc") {
                        order = "升序排列"
                        sortOrder = "asc";
                    } else {
                        order = "降序排列"
                        sortOrder = "desc";
                    }

                    if (name == "id") {
                        sort = "id";
                    }

                    data = $("#calendar").val();
                    param = {
                        "sort": sort,
                        "sortOrder": sortOrder,
                        "data": data,
                    };
                }
            });

        }
    });
    $("#search").click(function(){
            data = $("#calendar").val();
            param = {"sort":sort,"sortOrder":sortOrder,"data":data};
            //alert(JSON.stringify(param));
            $.tableDetailRefresh();
    });
    $("#search").click();

});


/**
 * 执行删除
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
        confirmButtonText: "是的，确定！",//确定按钮上面的文档
        closeOnConfirm: false
    },function(){
        $.ajax({
            type : "post",
            url : "deletecase.do",
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

/**
 * 复选框实现多条删除
 */
$("#btn_delete").click(function () {
    var a= $("#casedatas").bootstrapTable('getSelections', function (row) {
        return row;
    });//取到选择框一行的数据。
    if(a.length<=0) {
        alert("请选中一行");
    }else{
        var str = "";
        for (var i=0;i<a.length;i++){
            if (str==""){
                str = a[i].id;
            } else {
                str+=","+a[i].id;
            }
        }
        if (str == "")
            alert("请选择您要删除的信息！");
        else {
            if (confirm("您确定要删除吗？请谨慎删除病例信息！！！！！")) {
                $.ajax({
                    type : "post",
                    url : "deletecase.do",
                    data : {
                        "id" : str
                    },
                    success : function(data) {
                        $("#search").click();     //删除过后需要跳转的页面URL.
                    }
                });
            }
        }
    }
});