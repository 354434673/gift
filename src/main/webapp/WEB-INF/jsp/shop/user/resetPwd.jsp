<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="zh-CN">
    <meta charset="utf-8">
    <title>重置密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="keywords" content="友宏科技,jstyle,智能手环,智能美容仪,leyoung,深圳市友宏科技有限公司" />
    <meta name="description" content="友宏科技是一家专注技术的高新科技公司，致力于“健康、美容、医疗”相关产品的研发、生产、销售，主要提供“智能健康计步系列产品”、“家用美容和按摩系列产品”、“便携式医疗雾化器和吸引器系列产品”以及针对这些产品按照顾客要求提供满足其需求的个性化解决方案。" />
    <link rel="shortcut icon" type="<%=path %>/image/shop/x-icon" href="<%=path %>/img/shop/title.ico" />
    <link rel="icon" sizes="16x16" href="<%=path %>/image/shop/title.ico" />
    <!--[if IE 8]><script type="text/javascript" src="js/html5shiv.min.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=path %>/css/shop/global.css?v=16032101"/>
   	<link rel="stylesheet" href="<%=path %>/css/shop/register.css?v=16092101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
    <script src="<%=path %>/js/shop/jQuery.rTabs.js"></script>
    <style type="text/css">
    .content{background:url("<%=path %>/img/shop/user_bg.png") repeat}
    </style>
</head>
<body>
<div class="page">
    <div></div>
    <div class="content">
			<div class="regdiv" style="top: 90px">
				<div style="padding-top:60px;">
              <a href="<%=path%>/shopPage/basic.html"><img src="<%=path %>/img/shop/logo.png" style="margin-left: 85px;vertical-align:middle" width="120" height="32"></a><span class="regtitle">密码重置</span>
            	</div>
            <form id="regPwdForm" style="padding-top:30px;">
				<div>
                    <input type="text" id="email" name="email" placeholder="邮箱" class="info-input"/><i class="resetval"><img src="<%=path %>/img/shop/reset.png"></i>
                    <label for="email" class="error" id="email-error"></label>
                </div>
                <div>
                    <input type="text" id="phone" name="phone" placeholder="联系方式"  class="info-input"/>
                    <label for="phone" class="error" id="phone-error"></label>
                </div>
                <div>
		            <input type="password" id="userpwd" name="userpwd" placeholder="密码"  class="info-input"  />
		            <label for="userpwd" class="error" id="userpwd-error"></label>
		        </div>
		       	<div>
		            <input type="password" id="confirm_password" name="confirm_password" placeholder="确认密码" class="info-input"  />
		            <label for="confirm_password" class="error" id="confirm_password-error"></label>
		        </div>
				<br/>
                <div class="reg-btn">
                    <input type="submit" value="重置" class="reg-submit" id="resetPwdBtn">
                    <div class="topage">
                    <a href="<%=path%>/shopPage/loginCheck.html" class="tologin">返回登陆</a><a href="<%=path%>/shopPage/basic.html" class="toindex" >返回首页</a></div>
                </div>
            </form>
        </div>

    </div>
    <div class="clear"></div>
    <div class="border-t"></div>
	<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" height="200px"></iframe>
    <div class="overspread"></div>
</div>
</body>
<script src="//captcha.luosimao.com/static/js/api.js"></script>
<script type="text/javascript" src="<%=path %>/js/shop/jquery.md5.js"></script>
<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/jquery.validate.min.js" ></script>
<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/additional-methods.js?v=16032102" ></script>
<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/messages_zh.min.js" ></script>
<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/validate.js" ></script>
<script src="<%=path %>/js/shop/common.js"></script>
<script src="<%=path %>/js/shop/jquery.placeholder.min.js"></script>
<script src="<%=path %>/js/plugins/layer/layer.min.js"></script>

<script type="text/javascript">
	var regFlag = true;
    $.validator.setDefaults({
        submitHandler: function() {
            regFlag = false;
            $('#resetPwdBtn').val('重置中...');
            var phone = $('#phone').val();
            var email = $('#email').val();
            var userpwd = $('#userpwd').val();
            $.ajax({
                type:"post",
                url:"<%=path %>/customer/resetPwd.html" ,
                dataType:'json',
                data:{phone:phone,email:email,userpwd:userpwd},
                success:function(data){
                    if(data == 0){
                    	alert('密码修改成功,确认后为你跳转到登陆页面')
                    	location.href="<%=path%>/shopPage/loginCheck.html";
                    }else if(data == 1){
                    	$('#resetPwdBtn').val('重置');
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
	                	phone: {
	                        required: true,
	                        isMobile:true,
	                    },
	                    email: {
	                        required: true,
	                        email:true,
	                    },
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
	                	phone: {
	                        required: "请输入11位电话号码" ,
	                    },
	                    email: {
	                        required: "请输入邮箱",
	                    },
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
	//-----------------普通用户注册------------------------
/*     $(function(){
        $('input, textarea').placeholder();
        $("input[type='text']").val('');
        $("input[type='password']").val('');
        $('#smsCodeBtn').attr('disabled',false);
    });
    var regFlag = true;
    $.validator.setDefaults({
        submitHandler: function() {
            if(!$("#agree").is(':checked')){
                $('#agrinfo').html("同意该协议后才能注册");
                return false;
            }
            if(!regFlag){
                return false;
            }
            regFlag = false;
            $('#regBtn').val('注册中...');
            var phone = $('#phone').val();
            var smsCode = $('#smsCode').val();
            var pwd = $('#userpwd').val();
            var userpwd = $.md5(pwd);
            $.ajax({
                type:"post",
                url:"/shop/api/user" ,
                dataType:'json',
                data:{phone:phone,smsCode:smsCode,userpwd:userpwd},
                success:function(data){
                	LUOCAPTCHA.reset();  //重置验证
                    if(data.status == 1){
                        alert("注册成功，正在跳转到登陆页面...");
                        window.location.href="login.html";
                    }else if(data.status == 1368) {
                    	$('#agrinfo').html("短信验证码错误");
                    } else if(data.status == 1369){
                    	$('#agrinfo').html("验证码不存在或已过期");
                    } else {
                    	$('#agrinfo').html("系统繁忙，请稍后再试");
                    }
                },error:function(){
                	LUOCAPTCHA.reset();  //重置验证
                	$('#agrinfo').html("系统繁忙，请稍后再试");
                }
            });
        }
    });
    $().ready(function() {
        registerJS.init();
        registerJS.register();
        $('#agree').on('change',function(){
            if(!$("#agree").is(':checked')){
                $('#agrinfo').html("同意该协议后才能注册");
            } else {
                $('#agrinfo').html("");
            }
        });
    });
    var registerJS = {
        init:function(){
            $("#regForm").on("keyup",function(event){
                if(event.keyCode == 13){
                    registerJS.register();
                }
            });
            //短信验证码按钮
            $('#smsCodeBtn').on('click',function(){
            	var phone = $.trim($('#phone').val());
                if(phone == ''){
                	$('#phone-error').html('请先输入手机号码');
                	return;
                }
                if($('#phone-error').html() != '' ) return;
                $('.overspread').css("display","block");
                $('.sms-box').css("display","block");
            });
            $('#closeBox').on('click',function(){
                $('.overspread').css("display","none");
                $('.sms-box').css("display","none");
            });
            smsSendInterval:undefined,
            $('#smsBtn').on('click',function(){
                var phone = $.trim($('#phone').val());
                if(phone == ''){
                	$('#smsCode-error').html('请输入手机号码');
                	return;
                }
                $('#smsCode-error').html('');
                var luotest_response = $('#lc-captcha-response').val();
                $.ajax({
                    type:'get',
                    url:"/shop/api/sms/send",
                    data:{luotest_response:luotest_response,codetype:'sms',reqType:'zc',phone:phone},
                    dataType:'json',
                    success:function(data){
                    	LUOCAPTCHA.reset();  //重置验证
						if(data.status == 1){
							$('.overspread').css("display","none");
			                $('.sms-box').css("display","none");
							//使按钮失效
			                $('#smsCodeBtn').attr('disabled',true);
							//倒计时  smsCodeBtn
							registerJS.smsSendInterval = setInterval(registerJS.countDown,1000);
			                
						}else if(data.status == 2){
                        	$('#smsmsg').html('系统繁忙，请稍后再试');
                        } else if(data.status == 1224){
                        	$('#smsmsg').html('手机号码不正确');
                        } else{
                        	$('#smsmsg').html('发送短信验证码失败，请稍后再试；每天不能超过5条噢~~');
                        }
                    },error:function(info){
                    	LUOCAPTCHA.reset();  //重置验证
                    }
                });
            });
        },
        i:60,
        countDown:function(){
        	registerJS.i--;
            var smsBtn = $('#smsCodeBtn');
			if(registerJS.i < 0){
				$(smsBtn).attr('disabled',false);
				$(smsBtn).val('获取验证码');
				clearInterval(registerJS.smsSendInterval);
				registerJS.i = 20;
				return;
			}
			$(smsBtn).val('重新发送('+registerJS.i+')');
        },
        register:function(){
            $("#regForm").validate({
                rules: {
                    phone: {
                        required: true,
                        isPhone: true ,//调用验证
                        remote: {
                            type: "get",               //数据发送方式
                            url: "/shop/api/user/validate",     //后台处理程序
                            data: {                     //要传递的数据
                                phone: function() {return $("#phone").val();
                                }
                            }
                        }
                    },
                    userpwd: {
                        required: true,
                        minlength: 6
                    },
                    smsCode: {
                        required: true
                    },
                    confirm_password: {
                        required: true,
                        minlength: 6,
                        equalTo: "#userpwd"
                    }
                },
                messages: {
                    phone: {
                        required: "请输入11位电话号码" ,
                        remote: "该手机号已经注册"
                    },
                    userpwd: {
                        required: "请输入密码",
                        minlength: "最短6个字符"
                    },
                    smsCode: {
                        required: "请输入短信验证码"
                    },
                    confirm_password: {
                        required: "请输入密码",
                        minlength: "最短6个字符",
                        equalTo: "两次密码不一致"
                    }
                }
            });
        }
    } */
</script>
</html>