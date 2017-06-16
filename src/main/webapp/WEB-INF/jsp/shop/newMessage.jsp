<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
   <title>最新动态</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/img/shop/title.ico" />
    <link rel="icon" sizes="16x16" href="<%=path %>/img/shop/title.ico" />
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/news_notice.css">
    <style type="text/css">
    a{
		text-decoration:none;	
	}
    </style>
    <script>
	</script>
</head>
<body>
   
  <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe>

            <div id="introduce"class="introduce">
            	<div style="margin: 100px"></div>
            	 <div class='news_notice'>
                    <div class='centerbox'>
                        <div class='news load'>
                        <img src="<%=path %>/img/shop/asea213bvase123123va213e.jpg" height="700px" width="100%"/>
                        </div>
                        <div class='notice' >
                            <div class='noticebox' style="height: 700px">
                                <div class='title'>
                                    <div class='title_left'>
                                        <span class='title_left_cn'>最新文章</span>
                                        <span class='title_left_en'>New message</span>
                                    </div>
<!--                                     <div class='title_right'>
                                        <span>更多>></span>
                                    </div>     -->
                                </div>
                                <ul class='notice_info' >
                                <c:if test="${empty message }">
                                    <li >
                                  	  暂无文章
                                    </li>
				          		</c:if>
				          		<c:if test="${not empty message}">
					          		<c:forEach items="${message }" var="message" >
                                    <li><span>[<date:date value ="${message.updated}"/>]</span>
                                    <a href="<%=path %>/shopPage/articlePage.html?id=${message.id}">${message.title}</a></li>
					          		</c:forEach>
				          		</c:if>
                                </ul>    
                            </div>
                        </div>
                    </div>
                </div>   
            </div>
<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%"  style="height: 200px;"></iframe>
</body>
<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
</html>