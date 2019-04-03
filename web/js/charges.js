$(function() {

    var param={};
    var sort = "id";/*排序*/
    var sortOrder="asc";
    //************************start******************************//
    jQuery.extend({
        tableDetailRefresh:function(){
            console.debug(param);
            $('#programmedatas').bootstrapTable('refresh');
            $('#programmedatas').bootstrapTable({
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
                url: "programme.do",
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
                    {
                        field: 'registered.id',
                        title: '挂号单号',
                        align: 'center',
                    },
                    {
                        field:'programmeMedicine',
                        title:'诊疗方案内容',
                        align:'center',
                        formatter:function(value,row,index){
                            if(row.programmeMedicine == null){
                                return row.programmeProjects;
                            }else {
                                return row.programmeMedicine;
                            }
                        }
                    },
                    {
                        field: 'programmeMony',
                        title: '诊疗方案价钱',
                        align: 'center'
                    },
                    {
                        field: 'programmeDate',
                        title: '上传时间',
                        align: 'center',
                    },
                    {
                        field: '',
                        title: '操作',
                        align: 'center',
                        formatter:function(value,row,index){
                            if(row.programmeState=="0"){
                                if(row.programmeMedicine != null) {
                                    var d = '<button type="button" class="btn btn-sm btn-default" onclick="insert(' + row.id + ')">提交至药房</button>';
                                    return d;
                                }else {
                                    var c = '<button type="button" class="btn btn-sm btn-default" onclick="update(' + row.id + ')">提交至检查科室</button>';
                                    return c;
                                }
                            }else {
                                return null;
                            }
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
        state = $("#state").val();
        programmetype = $("#programmetype").val();
        param = {"sort":sort,"sortOrder":sortOrder,"data":data,"state":state,"programmetype":programmetype};
        //alert(JSON.stringify(param));
        $.tableDetailRefresh();
    });
    $("#search").click();
});



/**
 * 用户缴费，提交至药品出库
 * @param id
 */
function insert(id)
{
    var state = "1";
    swal({
        title: "",      //弹出框的title
        text: "确定移交至药品管理员处？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定提交！",//确定按钮上面的文档
        closeOnConfirm: false
    },function(){
        $.ajax({
            type : "post",
            url : "updateProgramme.do",
            data : {
                "id" : id ,"state":state
            },
            success : function(data) {
                swal("",
                    "已完成缴费！提醒患者至药房取药",
                    "success");
                $("#search").click();      //删除过后需要跳转的页面URL.
            }
        });
    });
}

/**
 * 病人缴费，提交至检查科室
 * @param id
 */
function update(id)
{
    var state = "1";
    swal({
        title: "",      //弹出框的title
        text: "确定移交至检查科室处？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定提交！",//确定按钮上面的文档
        closeOnConfirm: false
    },function(){
        $.ajax({
            type : "post",
            url : "updateProgramme.do", //提交至ProgrammeController
            data : {
                "id" : id ,"state":state
            },
            success : function(data) {
                swal("",
                    "已完成缴费！提醒患者至对应科室检查",
                    "success");
                $("#search").click();      //删除过后需要跳转的页面URL.
            }
        });
    });
}
