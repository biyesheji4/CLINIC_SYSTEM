$(function() {

    var param={};

    var sort = "id";/*排序*/
    var sortOrder="asc";
    //************************start******************************//
    jQuery.extend({
        tableDetailRefresh:function(){
            console.debug(param);
            $('#projectdatas').bootstrapTable('refresh');
            $('#projectdatas').bootstrapTable({
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
                        field:'programmeProjects',
                        title:'诊疗方案内容',
                        align:'center',
                    },
                    {
                        field: 'programmeDate',
                        title: '提交时间',
                        align: 'center',
                    },
                    {
                        field: '',
                        title: '操作',
                        align: 'center',
                        formatter:function(value,row,index){
                            var d = '<button type="button" class="btn btn-sm btn-default" onclick="result('+row.id+')"><span class="glyphicon glyphicon-share-alt" ></button>';
                            return d;
                        }
                    }],
            });

        }
    });
    $("#search").click(function(){
        data = $("#calendar").val();
        state = "1";
        programmetype="programme_project";
        param = {"sort":sort,"sortOrder":sortOrder,"data":data,"state":state,"programmetype":programmetype};
        //alert(JSON.stringify(param));
        $.tableDetailRefresh();
    });
    $("#search").click();
});

/**
 * 返回结果
 */
function result(id) {
    $("#projectresultModal").modal("toggle");
    var text ="";
    $.ajax({
        async:false,
        type: "POST",
        url: "queryprogrammeById.do",
        dataType: "json",
        data: {"id": id},
        success: function (result) {
            text +=
                "<div >" +
                    "<div class=\"col-md-12 col-sm-6 col-xs-12\">" +
                        "<div class=\"panel panel-info\">" +
                            "<div class=\"panel-heading\" style=\"text-align: center;background-image:linear-gradient(to bottom, #007bff 0%, #c4e3f3 100%)\">" +
                                    "病人："+result.programme.registered.patientName +" 检查结果" +
                            "</div>"+
                                "<div class=\"panel-body\">"+
                                    "<div class=\"baseInfo\">"+
                                        "<input  type='hidden' class=\"infoInput\" data-provide=\"typeahead\"  value="+result.programme.registered.id+" id='registeredid'>"+
                "<input  type='hidden' class=\"infoInput\" data-provide=\"typeahead\"  value="+result.programme.id+" id='programmeid'>"+
                                    "<div class=\"form-group\">"+
                                        "<label>检查结果</label>"+
                                        "<textarea class='form-control' rows='5' id='programme' name='programme'></textarea>"+
                                    "</div>"+
                                "</div>"+
                            "</div>"+
                        "</div>"+
                    "</div>"+
                "</div>";
            $("#result").html(text);
        }
    });
}


/**
 * 提交检查结果
 */
$("#projectresult").click(function () {
    var programme = $("#programme").val();
    var registeredid = $("#registeredid").val();
    var programmeid = $("#programmeid").val();
            $.ajax({
                async: false,
                type: "POST",
                url: "updateproject.do",
                dataType: "json",
                data: {"id": registeredid,"programme":programme,"programmeid":programmeid},
                success: function (result) {
                    swal("",
                        "提交成功！",
                        "success");
                    $("#search").click();      //出库确定后刷新页面.
                }
            });
});
