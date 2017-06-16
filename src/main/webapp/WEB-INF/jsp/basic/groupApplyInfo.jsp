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
<title>企业用户申请管理</title>
  	<link rel="shortcut icon" href="<%=path %>/favicon.ico"> 
  	<link href="<%=path %>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <style type="text/css">
    	.btn-group ul li a{
    		text-align: center;
    	}
    </style>
</head>
<body>
	<div class="row" >
		<h4 class="example-title" align="center">企业用户申请管理</h4>
			<div class="col-xs-2" style="padding: 0px 0px 0px 30px;">
				<input type="text" id="condition" name="condition" class="form-control" placeholder="查询条件"/>
			</div>
			<button class="btn btn-success" onclick="search()">查询</button>
			<div class="btn-group" >
			  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			   选择查询类型 <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu">
			    <li><a href="#" onclick="reload(1)" >已发放用户</a></li>
			    <li><a href="#" onclick="reload(0)" >未发放用户</a></li>
			  </ul>
			</div>
		<br>
		<div class="col-lg-12">
		
			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%" class="table table-striped table-bordered table-hover" id="groupApplydata">
						<!-- 在这个TABLE填充数据 -->
					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		
		<div class="modal fade" id="addUser" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog" style="width:300px;margin-top:200px;">
			      <div class="modal-content">
			         <div class="modal-header">
			            <button type="button" class="close" 
			               data-dismiss="modal" aria-hidden="true">
			                  &times;
			            </button>
			            <h4 class="modal-title" id="myModalLabel">
			             		添加企业用户
			            </h4>
			         </div>
			         	<form method="post" id="addUserForm">
				         	<div class="modal-body">
				         		<!-- 用来获取该企业用户id的影藏域 -->
				         		<input type="hidden" class="form-control" id="id" name="id" placeholder="用户名" value=""/>
								用戶名：<input type="text" class="form-control" id="username" name="username" placeholder="用户名" value=""/>
								密码：<input type="text" class="form-control" id="password" name="password" placeholder="初始密码" value=""/>
								电话：<input type="text" class="form-control" id="phone" name="phone" placeholder="电话" value=""/>
								邮箱：<input type="text" class="form-control" id="email" name="email" placeholder="邮箱" value=""/>
								地址：<input type="text" class="form-control" id="address" name="address" placeholder="地址" value=""/>
								<div style="margin-top: 10px"></div>
								描述：<textarea name="info" id="info" style="height: 40px;width: 100%"></textarea>
					         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">关闭
				            </button>
				            <button type="button" class="btn btn-primary" onclick="submitAddUser()">
				             		添加
				            </button>
			         	</div>
			         	</form>
			      </div><!-- /.modal-content -->
			</div><!-- /.modal -->
			
			<div class="modal fade" id="deleteUser" tabindex="-1" role="dialog" aria-labelledby="del_hint_title" aria-hidden="true">
			   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal"  aria-hidden="true">× </button>
				            <h4 class="modal-title" id="del_hint_title">
				              	删除!!谨慎操作!!
				            </h4>
				         </div>
				         
				         <div class="modal-body">
				           	删除后，将不会看到此人的一些重要信息，是否删除？
				           	<input type="hidden" id=user_State name="user_State" value=""/>
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
				            </button>
				            <button type="button" onclick="deleteUser()" class="btn btn-danger">
				           		   删除
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
			   </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
			
		</div>
		<!-- /.col-lg-12 -->
	</div>
 	<script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=path %>/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=path %>/js/content.min.js?v=1.0.0"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="<%=path %>/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    
    <script type="text/javascript">
	    <!--页面加载时候  加载这个方法-->
	    $(document).ready(function() {
	    	iniTable(0);
	    });
	    function iniTable(state){
			var requestUrl = "<%=path%>/applyUser/groupApplyList.html?state="+state;
			$('#groupApplydata').bootstrapTable({
	    	    url: requestUrl, 
	    	    method: 'post',      //请求方式（*）
	    	    striped: true,      //是否显示行间隔色
	    	    cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    	    sortable: true,      //是否启用排序
	    	    sortOrder: "desc",          //排序方式 
	    	    pagination: true,          //是否显示分页（*）
	    	    sidePagination: "server",      //分页方式：client客户端分页，server服务端分页（*）
	            pageNumber:1,            //初始化加载第一页，默认第一页
	            pageSize: 15,            //每页的记录行数（*）
	            pageList: [5, 10, 15],    //可供选择的每页的行数（*）
	    	    minimumCountColumns: 2,    //最少允许的列数
	    	    clickToSelect: true,    //是否启用点击选中行
	    	    uniqueId: "id",      //每一行的唯一标识，一般为主键列
	    	    showToggle:false,     //是否显示详细视图和列表视图的切换按钮
	    	    height: 710, 
	    	    columns: [
	    	              {title: 'id',field: 'id',align: 'center',valign: 'middle',visible:false}, 
		                  {title: '用户姓名',field: 'name',align: 'center',valign: 'middle',width:'100px'}, 
		                  {title: '电话',field: 'phone',  align: 'center',valign: 'middle',width:'100px'},
		                  {title: '邮箱',field: 'email',align: 'center',valign: 'middle',width:'200px'},
		                  {title: '申请理由',field: 'applyreason',align: 'center',valign: 'middle',width:'200px'},
		                  {title: '地址',field: 'address',align: 'center',valign: 'middle',width:'200px'},
		                  {field:'operate', title:'操作',align:'center',width:'80px',
			               	   formatter:function(value, row, index){
			               		var a = '<button class="btn btn-info" onclick="confirmYes(\''+row.id+'\')">编辑</button>';
			               		var c = '<span>   </span>';
			               		if(row.state==0){
			               		 var b = '<button class="btn btn-info" onclick="turnToAddUser(\''+row.id+'\')">用户添加</button>';
			               		}else if(row.state==1){
			               	   	 var b = '';
			               		}
			               	    return a+c+b;
			               	   }
			              }
		                  
		          ]
	          });
		}
	    /*启用模态框*/
	    function turnToAddUser(id){
	    	$.ajax({
	    		   type: "POST",
	    		   async : false,
	    		   url: "<%=path%>/applyUser/queryGroupUserById.html",
	    		   data:{id:id},
	    		   success: function(groupUser){
	            	   	layer.msg("加载成功");
	    			   var data = jQuery.parseJSON(groupUser);
	    			   	$("#id").val(id)
	    			   	$("#username").val(data.name)
	    		    	$("#password").val('123456')
	    		    	$("#phone").val(data.phone)
	    		    	$("#email").val(data.email)
	    		    	$("#address").val(data.address)
	    		   },
	    		   error: function(){
	    			   layer.msg("数据异常");
	    		   }
	    	});
	    	$('#addUser').modal({
			      keyboard: true
		   });
	    }
	    /*验证并提交添加用户的申请*/
	    function submitAddUser(){
	    	var id = $("#id").val();
	    	var name = $("#username").val();  
	    	var password = $("#password").val(); 
	    	var phone = $("#phone").val(); 
	    	var email = $("#email").val(); 
	    	var address = $("#address").val(); 
	    	var info = $("#info").val();//
	    	if(name == "" || name == null || password == "" || password == null
	    			||phone == "" || phone == null || email == "" || email == null){
	    		layer.msg("不能为空");
	    		return false;
	    	}
	    	/*邮箱正则*/
	    	var emailarg="[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
	    	if(email.match(emailarg) == null){
	    		layer.msg("邮箱格式不正确");
	    		return;
	    	}
	    	layer.msg("提交中");
            $.ajax({
                type:"post",
                url:"<%=path %>/customer/addCustomer.html" ,
                dataType:'json',
                data:{id:id,name:name,pwd:password,phone:phone,email:email,type:'0',address:address,info:info,state:'0'},
                success:function(data){
                    if(data == 0){
                    	window.location.reload();
                    }else if(data == 1){
                    	layer.msg("该账号已存在");
                    }
                },error:function(){
                	layer.msg("账号发放异常");
                }
            });
	    }
	    /* 设置按钮 */
	    function confirmYes(id){
	    	layer.msg("功能待完善");
	    }
	    /*删除  逻辑删除*/
	    function confirmNo(id){
	    	$("#user_State").val(id);
	    	$('#deleteUser').modal({
			      keyboard: true
		   	});
	    }
	    //删除
		function deleteUser(){
			var userId = $("#user_State").val();
			if(userId == 1){
				layer.msg("禁止删除该系统用户");
				return;
			}
	    	var url='<%=path%>/user/deleteUser/'+userId;
	    	$.ajax({
	    		   type: "POST",
	    		   url: url,
	    		   async : false,
	    		   success: function(msg){
		    			var data = JSON.parse(msg);
		    			window.location.reload();
	    		   }
	    	});
		}
		//刷新
	    function reload(state){
	    	$('#groupApplydata').bootstrapTable('refresh', {url: '<%=path%>/applyUser/groupApplyList.html?state='+state}); 
	    }
		function search(){
			var condition = $('#condition').val();
	    	$('#groupApplydata').bootstrapTable('refresh', {url: '<%=path%>/applyUser/queryByNameOrTelOrEamil.html?condition='+encodeURI(encodeURI(condition))}); 
		}
    </script>
</body>
</html>