<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="shortcut icon" type="image/x-icon" href="<%=path %>/img/shop/title.ico" />
	<link rel="stylesheet" href="<%=path %>/css/shop/common.css" />
	<link rel="stylesheet" href="<%=path %>/css/shop/shopsManager.css" />
	<link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
	<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
	<title>个人中心</title>
	<style type="text/css">
   	.wrap{background:url("<%=path %>/img/shop/user_bg.png") repeat}
   </style>
   <script type="text/javascript">
   $(function(){
	   var html = null
	   if("${customer.type}"==0){
		   html = '企业用户'
	   }else{
		   html = '普通用户'
	   }
	   $('#type').html('用户类型 : '+html)
   })
	function exploit(){
		alert('开发中')
	}
   </script>
</head>
<body>
	 <div class="content" >
		 <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe>
		<!-- 内容  开始-->
		<div class="wrap" style="padding-top: 120px">
			<div class="vip_cont c100 clearfix">
				<!--左边列表导航  开始-->
				<div class="fl vip_left vip_magLeft">
					<iframe src="<%=path %>/magLeft.jsp" scrolling="no" frameborder="0" width="100%" height="900px"></iframe>
				</div>
				<!--左边列表导航  结束-->

				<!--右边列表内容  开始-->
				<div class="fr vip_right vip_magRight">
					<!--用户信息  开始 -->
					<div class="cus01">
						<div class="cusImg">
							<a href="javascript:void(0)" target="_blank">
							<img src="<%=path%>/img/shop/comeon.png" title="更换头像" onclick="alert('更换头像开发中')"/>
							</a>
						</div>
						<div class="cusName">
							<p title="用户名称">用户名称 : ${customer.name}</p>
							<p title="用户类型" id="type"></p>
							<span class="bdTell">账号绑定<i></i><em>${customer.phone}</em></span>
						</div>
					</div>
					<ul class="cus02">
						<li>
							<p><span>LOGO管理</span><a href="<%=path %>/PersonalCenterPage/myLogo.html?customerId=${customer.id}&state=0" target="_parent">进入管理</a></p>
						</li>
						<li>
							<p><span>我的LOGO</span><a href="<%=path %>/PersonalCenterPage/correlationLogo.html" target="_parent" >去上传</a></p>
						</li>
						<li>
							<p><span>个人资料</span><a href="javascript:void(0)" target="_blank" onclick="exploit()">去完善</a></p>
							<span class="numbers mystat">
								<div id="myStat" data-dimension="60" data-text="85%" data-info="New Clients" data-width="10" data-fontsize="12" data-percent="85" data-fgcolor="#ff6561" data-bgcolor="#eee" data-fill="#FFF" class="circliful" style="width: 60px;"></div>
							</span>
						</li>
					</ul>
				</div>
				<!--右边列表内容  结束-->
			</div>
		</div>
		<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" style="height: 200px;"></iframe>
	</div>
</body>
</html>