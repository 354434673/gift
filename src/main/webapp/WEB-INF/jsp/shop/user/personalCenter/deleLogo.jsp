<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="<%=path %>/js/shop/uploadPreview.js"></script>
	<title>已删除的LOGO</title>
	<style type="text/css">
   	.wrap{background:url("../img/shop/user_bg.png") repeat}
   </style>
   <script type="text/javascript">
   function recover(id){
       if(confirm("是否恢复该LOGO")){  
            $.ajax({
                type:"post",
                url:"<%=path %>/PersonalCenter/updataState.html" ,
                dataType:'json',
                data:{id:id,stateDescribe:'删除后恢复图片',state:'0'},
                success:function(data){
                    if(data == 0){
                    	alert('恢复成功')
                    	window.location.reload();
                    }else if(data == 1){
                    	alert('恢复失败')
                    }
                },error:function(){
                	alert('异常')
                }
            });
           return true;  
          
       }  
       return false;  
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
				<h2 style="margin-bottom: 10px">已删除的LOGO (<font color="blue">${title}</font>)</h2>
				<a href=”javascript:void(0)” onClick="javascript:history.back(-1)">返回上一页</a>
				<hr style="margin-bottom: 10px">
	          	<ul style="clear:both;">
	          		<c:if test="${empty logo }">
						<h3 style="margin-bottom: 10px;color: #999" >暂无已删除LOGO</h3>          		
	          		</c:if>
	          		<c:if test="${not empty logo}">
		          		<c:forEach items="${logo }" var="logo" >
		           		<li style="float:left;;width:130px;height:200px;margin-right: 15px" >	
		           			<div style="position:relative;" id="img">
		           			<input type="hidden" id="logoId" value="${logo.id}">
		            		<img alt="${logo.title}" src="${logo.url }" width="130px;" height="130px;" 
		            		onmouseover="width=135;height=135" onmouseout="width=130;height=130" >
		            		</div>
		            		<div align="right" >
		            		<a href="javascript:void(0)" onclick="recover('${logo.id}')">恢复LOGO</a> | 
		            		<a href="javascript:void(0)" onclick="alert('开发中')">确认删除</a>
		            		</div>
		            		<div align="center" >${logo.title}</div>
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