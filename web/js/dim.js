$(function() {

    var dimparam={};
    var sort="id";
    var sortOrder="asc";
    //************************start******************************//
    jQuery.extend({
        tableRefresh:function(){
            console.debug(dimparam);
            $('#tableData').bootstrapTable('refresh');
            $('#tableData').bootstrapTable({
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
                pageSize: 4,
                //可供选择的每页的行数（*）
                pageList: [4],
                //是否显示分页
                pagination:true,
                showPaginationSwitch:false,
                //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
                url: "departments/departmentId.do",
                //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
                queryParams : function(params) {
                    return {
                        pageNumber: params.offset,
                        pageSize: params.limit,
                        param:dimparam
                    };
                },
                //分页方式：client客户端分页，server服务端分页（*）
                sidePagination: "server",
                //是否显示搜索
                search: false,
                //指定主键列
                idField : "id",
                columns: [

                    {
                        field: 'id',
                        title: '选择',
                        align: 'center',
                        width:'72px',
                        formatter:function(value,row,index){
                            return '<input type="radio" name="selectname" value="'+value+'" text="'+row.departmentsName+'">';

                        }
                    },
                  {
                        field: 'departmentsName',
                        title: '科室名称',
                        align: 'center'
                    },{
                        field: 'departmentsContent',
                        title: '科室简介',
                        align: 'center',

                    }
                    ]
            });

        }
    });


    $("#defaultSelect").click(function(){
        var departmentsName="";
        dimparam={"sort":sort,"sortOrder":sortOrder,"departmentsName":departmentsName};
        $.tableRefresh();
    });



    $("#query").click(function(){
        var departmentsName=$("#queryName").val();
        // $("tableData").bootstrapTable('refreshOptions',{pageNumber:1});
        dimparam={"sort":sort,"sortOrder":sortOrder,"departmentsName":departmentsName};
        $.tableRefresh();
    });

    $("#selectID").click(function(){
        var id = "";
        var departmentsName = "";
        $('input[name="selectname"]:checked').each(function(){
            id = $(this).val();
            departmentsName = $(this).attr("text");
        });
        $("#defaultSelect").val(departmentsName);
        $("#selectDepartmentId").val(id);
    });
});




