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
    <script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/jquery.validate.min.js" ></script>
	<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/additional-methods.js?v=16032102" ></script>
	<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/messages_zh.min.js" ></script>
	<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/validate.js" ></script>
	<script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
    <link href="<%=path %>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<title>修改密码</title>
	<style type="text/css">
   	.submit{height: 30px;width: 90px;}
   </style>
   <script type="text/javascript">
	//--------------企业用户申请相关------------
	var regFlag = true;
   $.validator.setDefaults({
       submitHandler: function() {
           regFlag = false;
           $('#subBtn').val('提交中');
           var customerId = "${customer.id}"
           var userpwd = $('#userpwd').val();
           $.ajax({
               type:"post",
               url:"<%=path %>/customer/resetPwd.html" ,
               dataType:'json',
               data:{customerId:customerId,userpwd:userpwd},
               success:function(data){
                   if(data == 0){
                	alert('密码修改成功,请重新登陆')
                   	location.href="<%=path%>/shopPage/loginCheck.html";
                   }else if(data == 1){
                   	$('#subBtn').val('确认修改');
                   	layer.msg("该用户不存在");
                   }
               },error:function(){
               	$('#resetPwdBtn').val('重置');
               	layer.msg("数据异常,请稍后重试");
               }
           });
       }
   });
	$().ready(function() {
	        registerJS.register();
	}); 
	var registerJS = {
			register:function(){
	            $("#regPwdForm").validate({
	                rules: {
	                    userpwd: {
	                        required: true,
	                        minlength: 6,
	                        maxlength:18,
	                    },
	                    confirm_password: {
	                        required: true,
	                        minlength: 6,
	                        maxlength:18,
	                        equalTo: "#userpwd"
	                    }
	                },
	                messages: {
	                    userpwd: {
	                        required: "请输入密码",
	                        minlength: "最短6个字符",
	                        maxlength: "最长为18个字符"
	                    },
	                    confirm_password: {
	                        required: "请输入密码",
	                        minlength: "最短6个字符",
	                        maxlength: "最长为18个字符",
	                        equalTo: "两次密码不一致"
	                    }
	                }
	            });
			}
	}
   </script>
</head>
<body>
	 <div class="content" >
		<iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px"></iframe>
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
						<h3>修改密码
						<div align="right" style="color: #999">
						<a href=”javascript:void(0)” onClick="javascript:history.back(-1)">返回上一页</a>
						</div>
						</h3>	
						<form method="post" id="regPwdForm">
						<!-- 用于获取用户id -->	
							<div align="center" style="margin-top: 40px">
						    <input type="password" class="form-control" name = "userpwd" id="userpwd" placeholder="请输入密码" style="width: 300px" >
						    <div style="margin-top: 40px"></div>
						    <input type="password" class="form-control" name = "confirm_password" id="confirm_password" placeholder="请确认密码" style="width: 300px">
						    </div>
						  	<div align="center" style="margin-top: 50px" >
						 	<input type="submit" class="btn btn-info" style="height: 40px;width: 250px" id="subBtn" value="确认修改">
						  </div>
						</form>
						</div>
					<!--用户信息  开始 -->
				</div>
				<!--右边列表内容  结束-->
			</div>
		</div>
		<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" height="200px"></iframe>
	</div>
</body>
</html>