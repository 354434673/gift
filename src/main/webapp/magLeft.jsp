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
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="shortcut icon" href="http://www.yingmoo.com/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="<%=path %>/css/shop/common.css" />
	<link rel="stylesheet" href="<%=path %>/css/shop/shopsManager.css" />
    <script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/shop/common.js?v=16032101"></script>
	<script type="text/javascript" src="<%=path %>/js/shop/jquery.SuperSlide.2.1.2.js"></script>
	<title>个人中心</title>
	<style type="text/css">
   	.wrap{background:url("<%=path %>/img/shop/user_bg.png") repeat}
   </style>
   <script type="text/javascript">
	function exploit(){
		alert('开发中')
	}
   </script>
</head>
<body>
<div class="fl vip_left vip_magLeft">
<!-- 	<dl>
		<dt>标签1</dt>
		<dd>
			<p><a href="#" target="_blank">功能1</a></p>
			<p><a href="#" target="_blank">功能2</a></p>
			<p><a href="#" target="_blank">功能3</a></p>
			<p><a href="#" target="_blank">功能4</a></p>
			<p><a href="#" target="_blank">功能5</a></p>
		</dd>
	</dl>
	<hr>
	<dl>
		<dt>标签2</dt>
		<dd>
			<p><a href="#" target="_blank">功能1</a></p>
			<p><a href="#" target="_blank">功能2</a></p>
			<p><a href="#" target="_blank">功能3</a></p>
		</dd>
	</dl>
	<hr>
	<dl>
		<dt>标签3</dt>
				<dd>
			<p><a href="#" target="_blank">功能1</a></p>
			<p><a href="#" target="_blank">功能2</a></p>
		</dd>
	</dl>
	<hr> -->
	<dl>
		<dt>账号相关</dt>
		<dd>
			<p><a href="<%=path%>/PersonalCenterPage/mainPage.html" target="_parent">基本资料</a></p>
			<p><a href="<%=path %>/PersonalCenterPage/updatePwd.html" target="_parent">修改密码</a></p>
			<p><a href="<%=path %>/PersonalCenterPage/myLogo.html?customerId=${customer.id}&state=0" target="_parent" >我的LOGO</a></p>
			<p><a href="<%=path %>/PersonalCenterPage/myLogoFix.html?customerId=${customer.id}&state=0" target="_parent" >合成LOGO管理</a></p>
		</dd>
	</dl>
</div>
<script type="text/javascript">
</script>
</body>
</html>