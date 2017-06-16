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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <title>玖玖深红-VERY red</title>
    <meta name="keywords" content="友宏科技,jstyle,智能手环,智能美容仪,leyoung,深圳市友宏科技有限公司" />
    <meta name="description" content="友宏科技是一家专注技术的高新科技公司，致力于“健康、美容、医疗”相关产品的研发、生产、销售，主要提供“智能健康计步系列产品”、“家用美容和按摩系列产品”、“便携式医疗雾化器和吸引器系列产品”以及针对这些产品按照顾客要求提供满足其需求的个性化解决方案。" />
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/img/shop/title.ico" />
    <link rel="icon" sizes="16x16" href="<%=path %>/img/shop/title.ico" />
    <!--[if IE 8]><script type="text/javascript" src="js/html5shiv.min.js"></script><![endif]-->
    <!--[if IE 8]><script type="text/javascript" src="js/css3-mediaqueries.js"></script><![endif]-->
	<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/shop/global.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/health.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <link href="<%=path %>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
    <style type="text/css">
    </style>
    <script type="text/javascript">
		var customer="${customer.name}"
    	$(function(){
    		//未登录
    		var reg = '<a href="<%=path%>/shopPage/registCheck.html" target="_parent">注册</a>'
    		var span = '<span>|</span>'
    		var login = '<a href="<%=path%>/shopPage/loginCheck.html" target="_parent">登录</a>'
    		//登陆后
    		var glyphicon = '<span class="glyphicon glyphicon-user" aria-hidden="true"></span>'
    		var user = '<a href="<%=path%>/PersonalCenterPage/mainPage.html" target="_blank">'+customer+'</a>'
    		var logon = '<a href="<%=path %>/customer/userLogout.html" onclick="return logout();" target="_parent">安全退出</a>'
    		
    		var regAndLogin = reg+span+login;
    		var userAndLogon = glyphicon+user+span+logon
    		if(customer==null || customer == ''){
    			$('#show-shop').html(regAndLogin)
    		}else{
    			$('#show-shop').html(userAndLogon)
    		}
    	})
    	//退出
	    function logout(){
	         if(confirm("是否注销该用户")){  
	            return true;  
	        }  
	        return false;  
	    }
    	function exploit(){
    		alert('开发中')
    	} 
    </script>
</head>
<body >
	<div class="header">
            <div class="nav" style="border: 0px">
            	<font face="微软雅黑">
                <nav class="top-nav">
                    <div class="clear"></div>
                    <ul class="nav_left_ul">
                        <li><div id="show-health" class="mark-div"><a href="#">OEM产品</a></div></li>
                        <li><div id="show-beauty" class="mark-div"><a href="#">代理品牌产品</a></div></li>
                    </ul>
                    <a class="nav_logo" href="<%=path%>/shopPage/basic.html" target="_parent"><img src="<%=path %>/img/shop/n1/logo.png" alt="玖玖深红" ></a>
                    <ul class="nav_right_ul">
                        <li><div id="show-about" class="mark-div"><a href="#">关于品牌</a></div></li>
                        <li><div id="show-app" class="mark-div"><a href="javascrpit:void(0);" onclick="exploit()">APP</a></div></li>
                        <li id="user-box">
                            <div id="show-shop" class="mark-div user-div">
                            </div>
                        </li>
                    </ul>
                    <div class="clear"></div>
                </nav>
                </font>
            </div>
        </div>
<script type="text/javascript" src="<%=path %>/js/shop/common.js?v=16032101"></script>
<script type="text/javascript" src="<%=path %>/js/shop/jquery.SuperSlide.2.1.2.js"></script>
<script type="text/javascript" src="<%=path %>/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript">
</script>
</body>
</html>