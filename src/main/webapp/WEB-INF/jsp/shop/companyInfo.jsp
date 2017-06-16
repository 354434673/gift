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
   <title>JSTYLE-极简智品 精致生活</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="keywords" content="友宏科技,jstyle,智能手环,智能美容仪,leyoung,深圳市友宏科技有限公司" />
    <meta name="description" content="友宏科技是一家专注技术的高新科技公司，致力于“健康、美容、医疗”相关产品的研发、生产、销售，主要提供“智能健康计步系列产品”、“家用美容和按摩系列产品”、“便携式医疗雾化器和吸引器系列产品”以及针对这些产品按照顾客要求提供满足其需求的个性化解决方案。" />
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/img/shop/title.ico" />
    <link rel="icon" sizes="16x16" href="<%=path %>/img/shop/title.ico" />
    <!--[if IE 8]><script type="text/javascript" src="js/html5shiv.min.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=path %>/css/shop/global.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/about.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <style type="text/css">
    </style>
    <script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?df6c481ad0c635da7a0cc995f32e5bd3";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
	</script>
</head>
<body>
   
  <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe>

        <div class="about">
            <div class="about-nav">
                	当前位置 ： <a href="<%=path %>/shopPage/basic.html">首页</a> > <span>公司简介</span>
            </div><br/><br/>
            <div class="about-more">
                <img src="<%=path %>/img/shop/n1/1491448287.png">

            </div>
        </div>
        <div class="clear"></div>
        <div class="border-t"></div>
<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%"  style="height: 200px;"></iframe>
</body>
<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/shop/common.js?v=16032101"></script>
</html>