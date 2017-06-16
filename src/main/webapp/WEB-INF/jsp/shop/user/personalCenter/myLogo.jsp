<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
String imgPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
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
    <script type="text/javascript" src="<%=path %>/js/shop/uploadPreview.js"></script>
	<title>我的LOGO</title>
	<style type="text/css">
   	.wrap{background:url("../img/shop/user_bg.png") repeat}
   	li:hover {box-shadow: 0 1px 10px rgba(0,0,0,0.15) ;transform: scale(1.01);}
   </style>
   <script type="text/javascript">
   function dele(id){
       if(confirm("是否删除该图片")){  
            $.ajax({
                type:"post",
                url:"<%=path %>/PersonalCenter/updataState.html" ,
                dataType:'json',
                data:{id:id,stateDescribe:'用户自主删除',state:'2'},
                success:function(data){
                    if(data == 0){
                    	alert('删除成功')
                    	window.location.reload();
                    }else if(data == 1){
                    	alert('删除失败')
                    }
                },error:function(){
                	alert('异常')
                }
            });
           return true;  
          
       }  
       return false;  
   }
   function addDele(id){
	   $('#'+id).show()
   }
   function hid(id){
	   $('#'+id).hide()
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
				<div style="padding: 40px">
				<font size="5" >我的LOGO(<font color="blue">${title}</font>)</font>
				<div align="right" style="color: #999">
				<a href=”javascript:void(0)” onClick="javascript:history.back(-1)">返回上一页</a> <span>|</span>
				<a href="<%=path %>/PersonalCenterPage/deleLogo.html?customerId=${customer.id}&state=2" >查看已经删除的LOGO</a>
				</div>
				<hr style="margin-bottom: 10px">
	          	<ul style="clear:both;">
	          		<c:if test="${empty logo }">
						<h3 style="margin-bottom: 10px">暂无LOGO  -- <a style="color: #999;" href="<%=path %>/PersonalCenterPage/correlationLogo.html" >现在去上传</a></h3>          		
	          		</c:if>
	          		<c:if test="${not empty logo}">
		          		<c:forEach items="${logo }" var="logo" >
		           		<li style="float:left;width:135px;height:195px;margin-right: 10px" onmouseover="addDele('${logo.id}')" onmouseout="hid('${logo.id}')">
		           			<div style="position:relative;">
		           			<input type="hidden" id="logoId" value="${logo.id}" >
		            		<img alt="${logo.title}" src="${logo.url }" width="135px;" height="130px;" 
		            		 >
		            		<div align="right" >
		            		<a href="javascript:void(0)" onclick="dele('${logo.id}')" hidden id="${logo.id}">删除LOGO</a>
		            		</div>
<%-- 		            		<span style="position:absolute; right:-10px; top:5px;" >
		            			<img src="../img/shop/a7.png" onclick="dele('${logo.id}')">
		            		</span> --%>
		            		</div>
		            		<div align="center" style="margin-top: 10px">${logo.title}</div>
		            		<div align="center" style="auto;color: #999;">${logo.content}</div>
		           		</li>
		          		</c:forEach>
	          		</c:if>
		          	</ul>
				</div>
				</div>
				<!--右边列表内容  结束-->
			</div>
		</div>
		<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" style="height: 200px;"></iframe>
	</div>
</body>
</html>