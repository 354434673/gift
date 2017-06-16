<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   	<meta charset="utf-8">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->


    <title>玖玖后台管理</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

  <link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" >
    <link href="<%=path%>/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="<%=path%>/css/font-awesome.min93e3.css" rel="stylesheet">
    <link href="<%=path%>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/css/style.min862f.css" rel="stylesheet">
    <link href="<%=path%>/css/unlock.css" rel="stylesheet">
    <style type="text/css">
		.bar {
			height: 40px;
			width: 300px;
		}
    </style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">玖玖</h1>
            </div>
            <h3>欢迎使用 玖玖后台系统</h3>

            <form class="m-t" role="form" action="<%=path%>/user/loginCheck.html" method="post" id="loginForm">
                <div class="form-group">
                    <input type="text" name="name" class="form-control" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required="">
                </div>
                <div class="form-group">
                	<div class="bar1 bar"></div>
                </div>
               <!--  <button type="submit" class="btn btn-primary block full-width m-b">登 录</button> -->


               <!--  <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a> 
                </p>-->

            </form>
        </div>
    </div>
    <script src="<%=path%>/js/jquery.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/basic/unlock.js"></script>
    <script type="text/javascript">
    	$('.bar1').slideToUnlock({
    		bgColor : '#20B2AA',
    		progressColor:'#20B2AA',
    		text : '滑动到右侧登录',
			succText : '登录成功'
    	});
    </script>
</body>
</html>
