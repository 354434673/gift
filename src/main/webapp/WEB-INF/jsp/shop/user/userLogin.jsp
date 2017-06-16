<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="zh-CN">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="keywords" content="友宏科技,jstyle,智能手环,智能美容仪,leyoung,深圳市友宏科技有限公司" />
    <meta name="description" content="友宏科技是一家专注技术的高新科技公司，致力于“健康、美容、医疗”相关产品的研发、生产、销售，主要提供“智能健康计步系列产品”、“家用美容和按摩系列产品”、“便携式医疗雾化器和吸引器系列产品”以及针对这些产品按照顾客要求提供满足其需求的个性化解决方案。" />
    <link rel="shortcut icon" type="<%=path %>/image/shop/x-icon" href="<%=path %>/img/shop/title.ico" />
    <link rel="icon" sizes="16x16" href="<%=path %>/img/shop/title.ico" />
    <!--[if IE 8]><script type="text/javascript" src="js/html5shiv.min.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=path %>/css/shop/global.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/login.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <title>用户登录</title>
    <style type="text/css">
    .content{background:url("<%=path %>/img/shop/user_bg.png") repeat}
    </style>
</head>
<body>
<div class="page">
    <div></div>
    <div class="content">
        <div class="regdiv">
            <a href="<%=path%>/shopPage/basic.html"><img src="<%=path %>/img/shop/logo.png" style="margin-left: 65px;vertical-align:middle" width="120" height="32"></a><span class="regtitle">用户登陆</span>
            <form id="loginForm">
                <div>
                    <input type="text" id="username" name="username" placeholder="用户账户" class="info-input"/><i class="resetval" id="resetname"><img src="<%=path %>/img/shop/reset.png"></i>
                </div>
                <div>
                    <input type="password" id="userpwd" name="userpwd" placeholder="密码"  class="info-input"/>
                </div>
				 <div>
                    <input type="text" id="valicode" name="userpwd" placeholder="验证码" class="info-input" style="width:40%;height: 5px"/>
                    <img id="captchaImage" class="captchaImage" src="<%=path %>/customer/image.pag"
					onclick="document.getElementById('captchaImage').src='<%=path %>/customer/image.pag?clientCode=${sessionScope.code}'+(new Date()).getTime()" 
					title="点击更换验证码"  style="vertical-align: middle"/>
                </div>
                <div id="errinfo"></div><font size="2"></font>
				<br/>
                <!--  <div>
					<div class="l-captcha" data-site-key="b8046b8e57a4a2d19977128bc8c995c6"></div>
                </div>-->
                <div class="reg-btn">
                    <input type="button" value="登陆" class="reg-submit" id="logBtn">
                  <div class="loginother"><a href="<%=path%>/shopPage/registCheck.html">注册相关</a>
				  <c:if test="${not empty itemId }">
					  <input type="hidden" id="itemId" name="itemId" value="${itemId }"/>
				  </c:if>
				  <a href="<%=path%>/shopPage/customerResetPwd.html" class="fgpwd">重置密码</a>
				  </div> 
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
    <div class="border-t"></div>
	<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" height="200px"></iframe>
</div>
</body>
<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
<script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#logBtn').click(function(){
		$("#logBtn").val("正在登陆...");
 		var username = $.trim($("#username").val());
		var pwd = $.trim($("#userpwd").val());
		var valicode = $.trim($("#valicode").val());
 		if (username == "") {
 			$("#errinfo").html("<font size='2'>请输入用户名</font>!");
			$("#logBtn").val("登陆");
			return false
		}
		if (pwd == "") {
			$("#errinfo").html("<font size='2'>请输入密码</font>!");
			$("#logBtn").val("登陆");
			return false
		}else if(pwd.length<6){
			$("#errinfo").html("<font size='2'>密码最少为6位数</font>!");
			$("#logBtn").val("登陆");
			return false
		}else if(pwd.length>18){
			$("#errinfo").html("<font size='2'>密码最多为18位</font>!");
			$("#logBtn").val("登陆");
			return false
		}
		if (valicode == "") {
			$("#errinfo").html("<font size='2'>请输入验证码</font>!");
			$("#logBtn").val("登陆");
			return false
		}else if(valicode.length!=4){
			$("#errinfo").html("<font size='2'>验证码为4位数</font>!");
			$("#logBtn").val("登陆");
			return false
		} 
		$.ajax({
			type : "post",
			url : "<%=path %>/customer/userLogin.html",
			dataType:'json',
			data : {username:username,pwd:pwd,valicode:valicode},
			success : function(data) {
				if (data == 0) {
					layer.msg("登陆成功");
					var itemId = $("#itemId").val();
					if(typeof(itemId)!= "undefined"){
						location.href="<%=path%>/item/showItem/"+itemId;
					}else{
						location.href="<%=path%>/shopPage/basic.html";
					}
				}else {
					if (data == 1) {
						$("#logBtn").val("登陆");
						layer.msg("账户或密码错误");
					}else if(data == 2){
						$("#logBtn").val("登陆");
						layer.msg("验证码错误");
					}else if(data == 3){
						$("#logBtn").val("登陆");
						layer.msg("该用户不可用,请联系管理员");
					}
				}
			},
		})
	})
})
<%-- var loginFlag = true;
var loginJS = {
	login : function(ele) {
		if (!loginFlag) {
			return false
		}
		$("#logBtn").val("正在登陆...");
		loginFlag = false;
		var data = $.trim($("#data").val());
		var pwd = $.trim($("#userpwd").val());
		var valicode = $.trim($("#valicode").val());
		console.log(pwd.length)
		if (data == "") {
			$("#errinfo").html("<font size='2'>请输入用户名</font>!");
			loginFlag = true;
			$("#logBtn").val("登陆");
			return false
		}
		if (pwd == "") {
			$("#errinfo").html("<font size='2'>请输入密码</font>!");
			loginFlag = true;
			$("#logBtn").val("登陆");
			return false
		}else if(pwd.length<6){
			$("#errinfo").html("<font size='2'>密码最少为6位数</font>!");
			loginFlag = true;
			$("#logBtn").val("登陆");
			return false
		}else if(pwd.length>18){
			$("#errinfo").html("<font size='2'>密码最多为18位</font>!");
			loginFlag = true;
			$("#logBtn").val("登陆");
			return false
		}
		if (valicode == "") {
			$("#errinfo").html("<font size='2'>请输入验证码</font>!");
			loginFlag = true;
			$("#logBtn").val("登陆");
			return false
		}else if(valicode.length!=4){
			$("#errinfo").html("<font size='2'>验证码为4位数</font>!");
			loginFlag = true;
			$("#logBtn").val("登陆");
			return false
		}
		$.ajax({
			type : "POST",
			url : "<%=path %>/shopFunction/userLogin.html",
			dataType:'json',
			data : {data:data,userpwd:userpwd,valicode:valicode},
			success : function(data) {
				if (data == 0) {
				} else {
					if (data == 1) {
						loginFlag = true;
						$("#logBtn").val("登陆");
						$("#errinfo").html("账号或密码错误")
					}
				}
			},
			error : function() {
				loginFlag = true;
				$("#logBtn").val("登陆");
				$("#errinfo").html("请求失败，请稍后再试")
			}
		})
	}
}; --%>

</script>
</html>