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
	<link href="<%=path %>/css/shop/picture.css" type="text/css" rel="stylesheet"/>
	<link href="<%=path %>/css/shop/index.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/shop/uploadPreview.js"></script>
    <link href="<%=path %>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<title>上传LOGO</title>
	<style type="text/css">
   	.wrap{background:url("../img/shop/user_bg.png") repeat}
   	.submit{height: 30px;width: 90px;}
   </style>
   <script type="text/javascript">
   var img = "${logo}"
   $(function(){
	   if(img == null ||img == ''){
		 $('#img').html('<img width="200" height="200" style="margin-left: 15%;background-color:#FAFAFA" hidden/>')
	   }else{
	  	 $('#img').html('<img src="../img/shop/20170518161950.png" style="margin-left: 15%"/>'+
	  	 				'<img width="200" height="200" src="${logo}" style="margin-left: 15%"/>')
	   }
   })
	var delParent;
	var defaults = {
		fileType         : ["jpg","png","bmp","jpeg"],   // 上传文件的类型
		fileSize         : 1024 * 1024 * 5                  // 上传文件的大小 5M
	};
	function exploit(){
		alert('开发中')
	}
    window.onload = function () { 
         new uploadPreview({ 
        	 UpBtn: "up_img", 
        	 DivShow: "imgdiv", 
        	 ImgShow: "imgShow" 
         });
     }
    function uploadLogo(){
    	var file = $('#up_img').val()
		var dom = dom = document.getElementById("up_img");;  
    	if(file == ''||file == null){
    		alert('请选择文件')
    	}else if(dom.files[0].size > defaults.fileSize){
    		alert('图片过大,请上传小于5M的图片')
    	}else{
    		var customerId = "${customer.id}";
            $.ajax({
                type:"post",
                url:"<%=path %>/PersonalCenter/getLogoTitle.html" ,
                dataType:'json',
                data:{customerId:customerId},
                success:function(data){
                    if(data == 0){
			    		$('#logoForm').submit()
                    }else if(data == 1){
                    	alert('图片Logo最多为5张,请先删除')
                    }
                },error:function(){
                	alert('异常')
                }
            });
    	} 
    }
   </script>
</head>
<body>
	 <div class="content" >
		 <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe>
		<!-- 内容  开始-->
		<div class="wrap" style="padding-top: 120px">
			<div class="vip_cont c100 clearfix">
				<!--左边列表导航  开始 -->
				<div class="fl vip_left vip_magLeft">
					<iframe src="<%=path %>/magLeft.jsp" scrolling="no" frameborder="0" width="100%" height="900px"></iframe>
				</div>
				<!--左边列表导航  结束-->
				<!--右边列表内容  开始-->
				<div class="fr vip_right vip_magRight">
					<div style="padding: 40px">
						<h2>LOGO相关</h2>
						<div align="right" style="color: #999">
						<a href=”javascript:void(0)” onClick="javascript:history.back(-1)">返回上一页</a>
						</div>
						<form action="<%=path %>/PersonalCenter/uploadLogo.html" enctype="multipart/form-data" method="post" id="logoForm">
						<!-- 用于获取用户id -->	
							<input type="hidden" name ="customerid" value="${customer.id}" />
						    <label for="title">LOGO名称</label>
						    <input type="text" class="form-control" name = "title" id="title" placeholder="LOGO名称">
						  <div class="form-group">
						    <label for="content">LOGO描述</label>
						    <input type="text" class="form-control" name = "content" id="content" placeholder="LOGO描述">
						  </div>
						    <label for="exampleInputFile" >上传LOGO</label>
							<div id="imgdiv" >
							<img id="imgShow" width="200" height="200" src="<%=path %>/img/shop/a11.png" />
							<span id="img">
							</span>
							</div>	
   							<input type="file" name="fi" id="up_img" 
   							style="margin-top: 10px" accept="image/jpg,image/jpeg,image/png,image/bmp" multiple />
						 	<div align="right" style="margin-top: 0px" >
						 	<hr>
						 	<button type="button" class="btn btn-info" onclick="uploadLogo()">提交相关信息</button>
						 </div>
						</form>
					</div>
					<!--用户信息  开始 -->
				</div>
				<!--右边列表内容  结束-->
			</div>
		</div>
		<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" style="height: 200px;"></iframe>
	</div>
</body>
</html>