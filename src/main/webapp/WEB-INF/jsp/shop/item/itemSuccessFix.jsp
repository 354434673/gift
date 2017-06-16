<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/shop/global.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/health.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/product.css?v=16032101"/>
	<link rel="stylesheet" href="<%=path %>/css/shop/classify.css">
	<link rel="stylesheet" href="<%=path %>/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path %>/css/jquery-ui.css">
	
	 <script>
	 	var imgsrc = ' ${logos.url }';
  </script>
	
</head>
<body>
	 <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe> 
	<div class="all">
		<div style="margin-left: -15%;margin-top: 12%;width:100%;">
			<h2>拖动LOGO合成商品展示图</h2>
				<div>
				  <p><img id="img1" src="${item.pic2 }" width="300px;" height="300px;"/><span id="img"></span></p>
				</div>
				<div id="draggable" style="margin-left: 10%;">
				  <p><img id="img2" src="${logos.url }" width="100px;" height="50px;"/></p>
				</div> 
		</div>
		<br>
	</div>
	
	


			
	<script type="text/javascript" src="<%=path %>/js/shop/common.js?v=16032101"></script>
	<script type="text/javascript" src="<%=path %>/js/shop/jquery.SuperSlide.2.1.2.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-ui-1.10.4.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/plugins/layer/layer.min.js"></script>
	<script type="text/javascript">
	$(function(){
	    jQuery(".fullSlide").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"leftLoop", vis:"auto", autoPlay:true, autoPage:true, trigger:"click" });
	})
	
   var img = "${logoFix}"
   $(function(){
	   if(img == null ||img == ''){
		 $('#img').html('<img width="200" height="200" style="margin-left: 15%;background-color:#FAFAFA" hidden/>');
	   }else{
	  	 $('#img').html('<img src="../img/shop/20170518161950.png" style="margin-left: 15%"/>'+
	  	 				'<img width="300" height="300" src="${logoFix}" style="margin-left: 15%"/>');
	  	 //禁用拖动事件
	  	 $("#draggable").draggable("disable");
	   }
   })
	
</script>
</body>
</html>