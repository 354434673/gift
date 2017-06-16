/*!
 * 2016-5-25 11:18:20
 * author xiao
 */
;
var loginFlag = true;
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
			url : "gift/shopFunction/userLogin.html",
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
};
