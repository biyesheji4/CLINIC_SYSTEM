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
                        field: '',
                        title: '修改分类',
                        align: 'center',
                        formatter:function(value,row,index){
                            var a = '<button type="button" class="btn btn-sm btn-default" onclick="update('+row.id+')"><span class="glyphicon glyphicon-pencil" > </span></button>';
                            return a;
                        }
                    }
                    ],
            });

        }
    });

    $("#search").click(function(){
            data = $("#calendar").val();
            type = $("#type").val();
            param = {"sort":sort,"sortOrder":sortOrder,"data":data,"type":type};
            //alert(JSON.stringify(param));
            $.tableDetailRefresh();
    });
    $("#search").click();

});




/**
 * 点击更新按钮，对模态框进行数据插入（选中当前的类别）
 * @param id
 */
function update(id) {
    $("#Modal").modal("toggle");
    $.ajax({
        type: "POST",
        url: "querymedicinesByid.do",
        dataType: "json",
        data: {"id": id},
        success: function (result) {
           $("#id").val(result.medicines.id);
           if(result.medicines.medicinesType=="片剂"){
               var jQ_a = $('#pianji');
               jQ_a.prop('checked','checked');
           }else  if(result.medicines.medicinesType=="胶囊剂"){
               var jQ_b = $('#jiaonang');
               jQ_b.prop('checked','checked');
           }else  if(result.medicines.medicinesType=="口服酊膏剂"){
               var jQ_c = $('#dinggao');
               jQ_c.prop('checked','checked');
           }else  if(result.medicines.medicinesType=="口服丸剂"){
               var jQ_d = $('#wan');
               jQ_d.prop('checked','checked');
           }else  if(result.medicines.medicinesType=="口服颗粒、粉、散剂"){
               var jQ_e = $('#keli');
               jQ_e.prop('checked','checked');
           }else  if(result.medicines.medicinesType=="外用酊、膏、贴、粉剂"){
               var jQ_f = $('#waiyong');
               jQ_f.prop('checked','checked');
           }else  if(result.medicines.medicinesType=="外用涂剂、栓剂"){
               var jQ_g = $('#tuji');
               jQ_g.prop('checked','checked');
           }else  if(result.medicines.medicinesType=="注射剂"){
               var jQ_h = $('#zhushe');
               jQ_h.prop('checked','checked');
           }
        }
    });
}


/**
 * 模态框点击确定按钮，更新该类药品的类别为选中值
 */
$("#medicines").click(function () {
    var id = $("#id").val();
    $('input[name="type"]:checked').each(function(){
        var type = $(this).val();
        swal({
            title: "",      //弹出框的title
            text: "确定将该药品类别更新为"+type+"吗？",   //弹出框里面的提示文本
            type: "warning",        //弹出框类型
            showCancelButton: true, //是否显示取消按钮
            confirmButtonColor: "#DD6B55",//确定按钮颜色
            cancelButtonText: "取消",//取消按钮文本
            confirmButtonText: "是的，确定提交！",//确定按钮上面的文档
            closeOnConfirm: false
        },function(){
                $.ajax({
                    // async:false,
                    type: "POST",
                    url: "updatemedicinesType.do",
                    dataType: "json",
                    data: {"type": type,"id": id},
                    success: function (result) {
                        swal("",
                            "更新成功！",
                            "success");
                        $("#search").click();
                    }
                });
            });
        });
});