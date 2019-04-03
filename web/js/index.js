$(function () {
    var type = $("#type").val();
    var text = "";
    if(type=="诊所负责人"){
       text+=
           "        <li class=\"nav-item\">\n" +
           "          <a id=\"registered\" href=\"#\">\n" +
           "            <i class=\"la la-fonticons\"></i>\n" +
           "            <p>挂号单详情</p>\n" +
           "          </a>\n" +
           "        </li>\n" +
           "        <li class=\"nav-item\">\n" +
           "          <a href=\"#\" id=\"employee\">\n" +
           "            <i class=\"la la-table\"></i>\n" +
           "            <p>员工信息</p>\n" +
           "          </a>\n" +
           "        </li>\n" +
           "        <li class=\"nav-item\">\n" +
           "          <a href=\"#\" id=\"medicines\">\n" +
           "            <i class=\"la la-th\"></i>\n" +
           "            <p>药品类别管理</p>\n" +
           "          </a>\n" +
           "        </li>\n" +
           "        <li class=\"nav-item\">\n" +
           "          <a href=\"#\" id=\"stock\" >\n" +
           "            <i class=\"la la-th\"></i>\n" +
           "            <p>药品库存管理</p>\n" +
           "          </a>\n" +
           "        </li>\n" +
           // "        <li class=\"nav-item\" >\n" +
           // "          <a id=\"case\" href=\"#\" >\n" +
           // "            <i class=\"la la-keyboard-o\"></i>\n" +
           // "            <p>病例管理</p>\n" +
           // "          </a>\n" +
           // "        </li>\n" +
           "        <li class=\"nav-item\" >\n" +
           "          <a id=\"departments_list\" href=\"#\" >\n" +
           "            <i class=\"la la-keyboard-o\"></i>\n" +
           "            <p>科室管理</p>\n" +
           "          </a>\n" +
           "        </li>\n" +
           "        <li class=\"nav-item\">\n" +
           "          <a href=\"#\" id=\"equipment_list\" >\n" +
           "            <i class=\"la la-bell\"></i>\n" +
           "            <p>设备管理</p>\n" +
           "          </a>\n" +
           "        </li>\n" +
           "        <li class=\"nav-item\">\n" +
           "          <a href=\"#\" id=\"project_list\">\n" +
           "            <i class=\"la la-font\"></i>\n" +
           "            <p>诊疗项目</p>\n" +
           "          </a>\n" +
           "        </li>\n" +
           "        <li class=\"nav-item\">\n" +
           "          <a href=\"redirect.do?view=tuxing\"  target=\"mainFrame\">\n" +
           "            <i class=\"la la-fonticons\"></i>\n" +
           "            <p>统计</p>\n" +
           "          </a>\n" +
           "        </li>"+
           "        <li class=\"nav-item\">\n" +
           "          <a href=\"#\" id=\"bill_list\">\n" +
           "            <i class=\"la la-font\"></i>\n" +
           "            <p>账单</p>\n" +
           "          </a>\n" +
           "        </li>\n";
       $("#nav").html(text);
    }
    else if(type=="收费员"){
        text+=
            "<li><img src='assets/img/gh.png' class=\"img1\"><a id=\"registeredadd\" href=\"#\">挂号</a></li>\n" +
            "<li><img src='assets/img/gh.png' class=\"img1\"><a id=\"registered\" href=\"#\">挂号单详情</a></li>\n" +
            "<li><img src='assets/img/gh.png' class=\"img1\"><a href=\"#\" id=\"charges\">收费</a></li>";
        $("#nav_menu").html(text);

        document.getElementById("sidebar").style.visibility="hidden";//隐藏
        var div = document.getElementById("div");
        div.style.width = "100%";
    }
    else if(type=="医生"){
        text+=
            "<li><img src='assets/img/gh.png' class=\"img1\"><a id=\"patient\" href=\"#\">门诊</a></li>\n" +
            "<li><img src='assets/img/gh.png' class=\"img1\"><a id=\"case\" href=\"#\">病例管理</a></li>\n" +
            "<li><img src='assets/img/gh.png' class=\"img1\"><a href=\"#\" id=\"project\">诊疗检查名单</a></li>";
        $("#nav_menu").html(text);

        document.getElementById("sidebar").style.visibility="hidden";//隐藏
        var div = document.getElementById("div");
        div.style.width = "100%";
    }
    else if(type=="药品管理员"){
        text+=
            "<li><img src='assets/img/gh.png' class=\"img1\"><a id=\"stock\" href=\"#\">药品库存管理</a></li>\n" +
            "<li><img src='assets/img/gh.png' class=\"img1\"><a id=\"payment\" href=\"#\">已缴费药品出库</a></li>\n";
        $("#nav_menu").html(text);

        document.getElementById("sidebar").style.visibility="hidden";//隐藏
        var div = document.getElementById("div");
        div.style.width = "100%";
    }

function query(url) {
    var iWidth = 1000; //弹出窗口的宽度;
    var iHeight = 500; //弹出窗口的高度;
    var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
    myWindow = window.open(url, '', 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',scrollbars=yes');
    myWindow.focus();
}

    /*挂号页面权限判定*/
    $("#registeredadd").click(function () {
        window.open("redirect.do?view=registered-add",target="mainFrame");
    });

    /*诊断页面权限判定*/
    $("#patient").click(function () {
        window.open("redirect.do?view=registered",target="mainFrame");
    });

    /*挂号详情页面权限判定*/
    $("#registered").click(function () {
        window.open("redirect.do?view=registered",target="mainFrame");
    });

/*员工页面权限判定*/
    $("#employee").click(function () {
        window.open("redirect.do?view=employee",target="mainFrame");
    });

    /*药品类别管理页面权限判定*/
    $("#medicines").click(function () {
        window.open("redirect.do?view=medicines",target="mainFrame");
    });

    /*药品库存管理页面权限判定*/
    $("#stock").click(function () {
        window.open("redirect.do?view=stock",target="mainFrame");
    });

    /*病例管理页面权限判定*/
    $("#case").click(function () {
        window.open("redirect.do?view=case",target="mainFrame");
    });

    /*科室管理页面权限判定*/
    $("#departments_list").click(function () {
        window.open("redirect.do?view=departments_list",target="mainFrame");
    });

    /*设备管理页面权限判定*/
    $("#equipment_list").click(function () {
        window.open("redirect.do?view=equipment_list",target="mainFrame");
    });

    /*诊疗项目页面权限判定*/
    $("#project_list").click(function () {
        window.open("redirect.do?view=project_list",target="mainFrame");
    });

    /*账单页面权限判定*/
    $("#bill_list").click(function () {
        window.open("redirect.do?view=bill_list",target="mainFrame");
    });
    /*收费*/
    $("#charges").click(function () {
        window.open("redirect.do?view=charges",target="mainFrame");
    });
    /*已缴费药品出库*/
    $("#payment").click(function () {
        window.open("redirect.do?view=payment",target="mainFrame");
    });
    /*已缴费诊疗项目*/
    $("#project").click(function () {
        window.open("redirect.do?view=project",target="mainFrame");
    });
});