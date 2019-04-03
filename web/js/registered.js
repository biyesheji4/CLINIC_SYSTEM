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


                showExport: true,                     //是否显示导出
                exportDataType: "basic",              //basic', 'all', 'selected'.
                showColumns: true,
                showRefresh: true,                  //是否显示刷新按钮
                showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showPaginationSwitch:false,
                //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
                url: "registered.do",
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
                        sortable:true
                    },
                    {
                        field: 'patientName',
                        title: '患者姓名',
                        align: 'center',
                        // formatter:function (value,row,index) {
                        //     var op = "<a  target='_blank'  onclick=query('query-registered.do?id="+row.id+"')>"+value+"</a>";
                        //     return op;
                        // }
                    },
                    {
                        field: 'patientSex',
                        title: '患者性别',
                        align: 'center',
                    },
                    {
                        field:'patientAge',
                        title:'患者年龄',
                        align:'center',
                    },
                    {
                        field: 'patientTel',
                        title: '患者联系方式',
                        align: 'center'
                    },
                    {
                        field: 'registeredContent',
                        title: '病情简述',
                        align: 'center',
                    },
                    {
                        field: 'registeredState',
                        title: '挂号状态',
                        align: 'center',
                        formatter:function(value,row,index){
                            if(value==0){
                                return "<p class='text-danger'>未提交</p>";
                            }else if(value==1){
                                return "<p class='text-primary'>已提交</p>";
                            }else if(value==2){
                                return "<p class='text-success'>已确诊</p>";
                            }

                        }
                    },
                    {
                        field: 'registeredDate',
                        title: '登记日期',
                        align: 'center',
                    },
                    {
                        field: 'registeredMoney',
                        title: '挂号费用',
                        align: 'center',
                    },
                    {
                        field: 'departments.departmentsName',
                        title: '提交科室',
                        align: 'center',

                    },
                    {
                        field: '',
                        title: '操作',
                        align: 'center',
                        formatter:function(value,row,index){
                            if(row.registeredState==1){
                                var a = '<button type="button" class="btn btn-sm btn-default" onclick="update('+row.id+')"><span class="glyphicon glyphicon-pencil" > </span></button>';
                                var d = '<button type="button" class="btn btn-sm btn-default" onclick="del('+row.id+')"><span class="glyphicon glyphicon-trash" ></button>';
                                return a+d;
                            }else {
                                var d = '<button type="button" class="btn btn-sm btn-default" onclick="del('+row.id+')"><span class="glyphicon glyphicon-trash" ></button>';
                                return d;
                            }

                            // var b = '<button type="button" class="btn btn-sm btn-default" onclick="redirect('+row.id+')"><span class="glyphicon glyphicon-share-alt" ></button>';
                        }
                    }],


                onSort:function(name, order){//排序判断

                    if(order=="asc"){
                        order = "升序排列"
                        sortOrder="asc";
                    }else{
                        order = "降序排列"
                        sortOrder="desc";
                    }

                    if(name=="id"){
                        sort="id";
                    }

                    textarea = $("#selectDepartmentId").val();
                    data = $("#calendar").val();
                    states = $("#states").val();
                    param = {"sort":sort,"sortOrder":sortOrder,"textarea":textarea,"data":data,"states":states};
                }
            });

        }
    });
    jQuery.extend({
        tableDetailRefresh2:function(){
            console.debug(param);
            $('#datas').bootstrapTable('refresh');
            $('#datas').bootstrapTable({
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
                url: "registered.do",
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
                        checkbox:true
                    },
                    {
                        field:'id',
                        title:'编号',
                        align:'center',
                        sortable:true
                    },
                    {
                        field: 'patientName',
                        title: '患者姓名',
                        align: 'center',
                        // formatter:function (value,row,index) {
                        //     var op = "<a  target='_blank'  onclick=query('query-registered.do?id="+row.id+"')>"+value+"</a>";
                        //     return op;
                        // }
                    },
                    {
                        field: 'patientSex',
                        title: '患者性别',
                        align: 'center',
                    },
                    {
                        field:'patientAge',
                        title:'患者年龄',
                        align:'center',
                    },
                    {
                        field: 'patientTel',
                        title: '患者联系方式',
                        align: 'center'
                    },
                    {
                        field: 'registeredContent',
                        title: '病情简述',
                        align: 'center',
                    },
                    {
                        field: 'registeredState',
                        title: '挂号状态',
                        align: 'center',
                        formatter:function(value,row,index){
                            if(value==0){
                                return "<p class='text-danger'>未提交</p>";
                            }else if(value==1){
                                return "<p class='text-primary'>已提交</p>";
                            }else if(value==2){
                                return "<p class='text-success'>已确诊</p>";
                            }

                        }
                    },
                    {
                        field: 'registeredDate',
                        title: '登记日期',
                        align: 'center',
                    },
                    {
                        field: 'registeredMoney',
                        title: '挂号费用',
                        align: 'center',
                    },
                    {
                        field: 'departments.departmentsName',
                        title: '提交科室',
                        align: 'center',

                    },
                    {
                        field: '',
                        title: '操作',
                        align: 'center',
                        formatter:function(value,row,index){
                            if(row.registeredState=="2"){
                                return "";
                            }else{
                                var b = '<button type="button" class="btn btn-sm btn-default" onclick="redirect('+row.id+')"><span class="glyphicon glyphicon-share-alt" ></button>';
                                return b;
                            }

                        }
                    }],


                onSort:function(name, order){//排序判断

                    if(order=="asc"){
                        order = "升序排列"
                        sortOrder="asc";
                    }else{
                        order = "降序排列"
                        sortOrder="desc";
                    }

                    if(name=="id"){
                        sort="id";
                    }

                    textarea = $("#selectDepartmentId").val();
                    data = $("#calendar").val();
                    states = $("#states").val();
                    param = {"sort":sort,"sortOrder":sortOrder,"textarea":textarea,"data":data,"states":states};

                }
            });

        }
    });

    $("#search").click(function(){
        type = $("#type").val();
        if(type =="收费员"||type=="诊所负责人"){
            textarea = $("#selectDepartmentId").val();
            data = $("#calendar").val();
            states = $("#states").val();
            param = {"sort":sort,"sortOrder":sortOrder,"textarea":textarea,"data":data,"states":states};
            //alert(JSON.stringify(param));
            $.tableDetailRefresh();
        }else if(type=="医生"){
            /*为科室选择赋值，值为该登陆账号的科室名称和科室ID*/
            $("#selectDepartmentId").val($("#departmentsid").val());
            $("#defaultSelect").val($("#departments").val())

            /*获取查询条件的值进行查询*/
            textarea = $("#selectDepartmentId").val();
            data = $("#calendar").val();
            states = $("#states").val();
            param = {"sort":sort,"sortOrder":sortOrder,"textarea":textarea,"data":data,"states":states};
            //alert(JSON.stringify(param));
            $.tableDetailRefresh2();
        } else {
            alert("您不具备访问该页面的权限");
            window.open("redirect.do?view=main",target="mainFrame");
        }

    });
    $("#search").click();

});

// function query(url){
//     var iWidth=1000; //弹出窗口的宽度;
//     var iHeight=600; //弹出窗口的高度;
//     var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
//     var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
//     myWindow=window.open(url,'','height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',scrollbars=yes');
//     myWindow.focus();
// }

/**
 * 医生用户跳转至病历单书写页面
 * @param id
 */
function redirect(id) {
    swal({
        title: "",      //弹出框的title
        text:  "您确定要呼叫的是"+id+"号患者吗",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#81ccee",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "确定",//确定按钮上面的文档
        closeOnConfirm: true
    },function(){
        window.open("openbingli.do?id="+id+"",target="mainFrame");
    });
}


/**
 * 进入更新模态框并赋值
 * @param id
 */
function  update(id) {
    $("#Modal").modal("toggle");

        $.ajax({
            // async:false,
            type: "POST",
            url: "input-update.do",//RegisteredController
            dataType: "json",
            data: {"id": id},
            success: function (result) {
                //赋值
                $("#id").val(result.registereds.id);
                $("#name").val(result.registereds.patientName);
                $("#age").val(result.registereds.patientAge);
                $("#tel").val(result.registereds.patientTel);
                $("#content").val(result.registereds.registeredContent);
               $("#patientIdcard").val(result.registereds.patientIdcard);

                if(result.registereds.patientSex =="男"){
                    var jQ_a = $('#nan');
                    jQ_a.prop('checked','checked');
                }else {
                    var jQ_b = $("#nv");
                    jQ_b.prop('checked','checked');
                }
                var text=" <option value=''>--请选择--</option>";
                for (var i=0;i<result.departmentsAll.length;i++){
                    text+="<option value="+result.departmentsAll[i].id+">"+result.departmentsAll[i].departmentsName+"</option>"
                }
                $("#sele").html(text);

                /*以下代码是判断选中的下拉框*/
                if(result.one!=null){
                    for(var j=0;j<result.one.length;j++){
                        var id=result.one[j].id;
                    }
                    var options=$("#sele option")
                    for(var x=0;x<options.length;x++){
                        var option =options.eq(x);
                        if(option.val()==id){
                            option.attr("selected",true);
                        }
                    }
                }

            }
        });
}

/**
 * 更新验证
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
            patientIdcard: {
                message: '身份证验证失败',
                validators: {
                    notEmpty: {
                        message: '身份证号不能为空'
                    },
                    stringLength: {
                        min: 18,
                        max: 18,
                        message: '身份证号不符合要求'
                    }
                }
            },
            patientName: {
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
            patientAge: {
                message: '年龄验证失败',
                validators: {
                    notEmpty: {
                        message: '年龄不能为空'
                    },
                    regexp: {
                        regexp: /^\d{1,2}$/,
                        message: '年龄不符合规范'
                    }
                }
            },
            patientTel: {
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
            }
        }
    });
}

/**
 * 提交更新信息
 */
    $("#department").click(function () {
        $("#form").bootstrapValidator('validate');//提交验证
        if ($("#form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var id = $("#id").val();
            var patientName = $("#name").val();
            var patientAge = $("#age").val();
            var patientTel = $("#tel").val();
            var registeredContent = $("#content").val();
            var patientIdcard = $("#patientIdcard").val();
            var registeredMoney = $("#money").val();
            var departmentsName = $("#sele").val();
            if (departmentsName == '') {
                swal({
                    title: "",      //弹出框的title
                    text: "请选择一个科室！否则无法提交！",   //弹出框里面的提示文本
                    type: "error",        //弹出框类型
                    button: "确定",
                });
                return false;
            } else {

                    $('input[name="sex"]:checked').each(function () {
                        var patientSex = $(this).val();
                        $.ajax({
                            // async:false,
                            type: "POST",
                            url: "updateRegistered.do",//RegisteredController
                            dataType: "json",
                            data: {
                                "id": id,
                                "patientName": patientName,
                                "patientIdcard": patientIdcard,
                                "patientAge": patientAge,
                                "patientTel": patientTel,
                                "registeredContent": registeredContent,
                                "registeredMoney": registeredMoney,
                                "departmentsName": departmentsName,
                                "patientSex": patientSex
                            },
                            success: function (result) {
                                swal("",
                                    "更新成功！",
                                    "success");
                                $('#Modal').modal('hide');
                                $("#search").click();
                            }
                        });
                    });
                }
        }
    });

//初始化表单验证
    $(document).ready(function() {
        formValidator();
    });

$('#Modal').on('hidden.bs.modal', function() {
    $("#form").data('bootstrapValidator').destroy();
    $("#form").data('bootstrapValidator', null);
    formValidator();
});


/**
 * 删除挂号单
 * @param id
 */
    function del(id) {
        swal({
            title: "",      //弹出框的title
            text: "确定删除吗？",   //弹出框里面的提示文本
            type: "warning",        //弹出框类型
            showCancelButton: true, //是否显示取消按钮
            confirmButtonColor: "#DD6B55",//确定按钮颜色
            cancelButtonText: "取消",//取消按钮文本
            confirmButtonText: "是的，确定删除！",//确定按钮上面的文档
            closeOnConfirm: false
        }, function () {
            $.ajax({
                type: "post",
                url: "deleteregistered.do",//RegisteredController
                data: {
                    "id": id
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

