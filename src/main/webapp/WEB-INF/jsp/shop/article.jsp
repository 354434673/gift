<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags" prefix="date"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>文章</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/img/shop/title.ico" />
    <link rel="icon" sizes="16x16" href="<%=path %>/img/shop/title.ico" />
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/news_notice.css">
    <style type="text/css">
    .introduce{background:url("<%=path %>/img/shop/ec0a9b9d59e3e98e2783680ec21e1a62.jpg");background-size:1920px 700px;}
        a{
		text-decoration:none;	
	}
    </style>
    <script>
	</script>
</head>
<body>
   
  <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe>

            <div id="introduce"class="introduce" style="height:100%">
            	<div style="margin: 100px"/>
            	<a href=”javascript:void(0)” onClick="javascript:history.back(-1)" style="margin-left: 70%">返回上一页</a>
            	<div align="center" style="padding-top: 50px;">
            	<h1><strong style="font-family: '微软雅黑';">${article.title}</strong></h1>
            	<span style="margin-left: 40%">更新时间:<date:date value ="${article.updated}"/></span>
            	<hr>
            	<div style="height: 350px">
				${article.content}
            	</div>
            	</div>
			</div>
<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%"  style="height: 200px;padding-top: 5%"></iframe>
</body>
<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
</html>