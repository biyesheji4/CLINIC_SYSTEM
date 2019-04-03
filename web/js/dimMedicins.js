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
                //指定主键列
                idField : "id",
                columns: [
                    {
                        checkbox:true
                    },
                    {
                        field:'id',
                        title:'编号',
                        align:'center'
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
                    // {
                    //     field: 'medicinesDate',
                    //     title: '药品状态',
                    //     align: 'center',
                    //     formatter:function(value,row,index){
                    //         var guoqi;
                    //         $.ajax({
                    //             async:false,
                    //             type: "POST",
                    //             url: "Datetime.do",
                    //             dataType: "json",
                    //             data: {"medicinesDate": value},
                    //             success:function (result) {
                    //                 if(result.b<=result.a){
                    //                     guoqi = '<span style="color:#3e8f3e;">可正常使用</span>';
                    //                 }else {
                    //                     if(result.c <= result.d){
                    //                         guoqi ='<span style="color:#c12e2a;">已经过期，请及时处理</span>';
                    //                     }else {
                    //                         guoqi ='<span style="color:#ffc107;">即将过期，请注意使用</span>';
                    //                     }
                    //                 }
                    //             }
                    //         });
                    //         return guoqi;
                    //     }
                    // },
                    // {
                    //     field: 'medicinesNum',
                    //     title: '药品库存数量',
                    //     align: 'center'
                    // },
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
                        field: '',
                        title: '药品选择数量',
                        align: 'center',
                        formatter:function(value,row,index){
                            return '<input  id="'+row.id+'" type="text" class="infoInput" style="width:90%">'
                        }
                    },

                ],
            });

        }
    });


    $("#defaultSelect").click(function(){
        data = $("#calendar").val();
        type = $("#type").val();
        var name = $("#name").val();
        var dimtype = "dimmedicins";
        param = {"sort":sort,"sortOrder":sortOrder,"data":data,"type":type,"name":name,"dimtype":dimtype};
        $.tableRefresh();
    });



    $("#query").click(function(){
        var dimtype = "dimmedicins";
        var name=$("#queryName").val();
        // $("tableData").bootstrapTable('refreshOptions',{pageNumber:1});
        param = {"sort":sort,"sortOrder":sortOrder,"data":data,"type":type,"name":name,"dimtype":dimtype};
        $.tableRefresh();
    });


    /**
     * 药品添加
     */
    $("#selectID").click(function(){
        var a= $("#tableData").bootstrapTable('getSelections', function (row) {
            return row;
        });//取到选择框一行的数据。
        if(a.length<=0) {
            swal("出错",
                "您还未选取任何一项",
                "error");
        }else{
            var str =$("#programme").val();
            var medicinsmony = $("#money").val()*1;
            var mid ="";
            var mnum="";
            for (var i=0;i<a.length;i++){
                if (str==""&&medicinsmony==0){
                    var num = $("#"+a[i].id+"").val();
                    if(num==""||num==0){
                        swal("出错",
                            "您选取 编号为"+a[i].id+"的 "+a[i].medicinesName+"   数量未输入",
                            "error");
                    }else {
                        if(num<a[i].medicinesNum){
                            str = "编号为"+a[i].id+"的 "+a[i].medicinesName +"的数量为"+num;
                            medicinsmony = a[i].medicinesMoney*num;
                            mid=a[i].id;
                            mnum= a[i].medicinesNum-num;
                        }else {
                            swal("",
                                "您选取的 编号为"+a[i].id+"的 "+a[i].medicinesName+"出库的数量大于库存数量，无法选取",
                                "warning");
                        }
                    }

                } else {
                    var num = $("#"+a[i].id+"").val();
                    if(num ==""||num == 0){
                        swal("出错",
                            "您选取 编号为"+a[i].id+"的 "+a[i].medicinesName+"出库的出库数量未输入",
                            "error");
                    }else {
                        if(num<a[i].medicinesNum){
                            str+=",编号为"+a[i].id+"的 "+a[i].medicinesName+"的数量为"+num;
                            medicinsmony += a[i].medicinesMoney*num;
                            mid+=a[i].id+",";
                            var mnuma=a[i].medicinesNum-num;
                            mnum +=mnuma+",";
                        }else {
                            swal("",
                                "您选取的 编号为"+a[i].id+"的 "+a[i].medicinesName+"出库的数量大于库存数量，无法选取",
                                "warning");
                        }
                    }
                }
            }
        }
        $("#programme").val(str);
        $("#money").val(medicinsmony);
        $("#mid").val(mid);
        $("#mnum").val(mnum);
    });
});




