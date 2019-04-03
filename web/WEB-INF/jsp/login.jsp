<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>诊所管家登陆系统</title>

    <!-- CSS -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-57-precomposed.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweet-alert.css">

</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>诊所管家</strong> 智能系统</h1>
                    <div class="description">

                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>新的一天新的开始</h3>
                            <p>输入您的用户名和密码，祝您工作愉快:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form id="loginform" class="form-horizontal">
                            <div class="form-group">
                                <label class="sr-only" >用户名</label>
                                <div class="col-sm-12">
                                    <input type="text" name="username" placeholder="用户名" class="form-control" id="username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" >密码</label>
                                <div class="col-sm-12">
                                    <input type="password" name="password" placeholder="密码" class="form-control" id="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">验证码</label>
                                <div class="col-sm-6">
                                    <input id="veryCode" name="veryCode" value="" placeholder="验证码" type="text" class="form-control" />
                                </div>
                                <div class="col-sm-6">
                                    <img id="imgObj"  alt="" src="/xuan/verifyCode.do"  onclick="return changeImg();" style="height: 50px;"/>
                                </div>
                            </div>
                            <button id="login" type="button" class="btn">登陆!</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>


<!-- Javascript -->
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.backstretch.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrapvalidator.js"></script>
<script src="${pageContext.request.contextPath}/vendor/zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/vendor/sweet-alert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
<!--[if lt IE 10]>
<script src="${pageContext.request.contextPath}/assets/js/placeholder.js"></script>
<![endif]-->

</body>
<script>

</script>
</html>