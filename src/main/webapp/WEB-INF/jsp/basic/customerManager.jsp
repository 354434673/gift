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
<title>客户管理</title>
  	<link rel="shortcut icon" href="<%=path %>/favicon.ico"> 
  	<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%=path %>/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
    	<link rel="stylesheet" href="<%=path %>/css/plugins/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=path %>/js/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/plugins/ztree/jquery.ztree.excheck-3.5.min.js"></script>
    <style type="text/css">
    	.btn-group ul li a{
    		text-align: center;
    	}
    </style>
    <script type="text/javascript">
    
	var setting = {
		check: {
			enable: true,
			autoCheckTrigger: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		async: {
			enable: true,
			url: "<%=path %>/security/itemCategory.html",
			autoParam: ["id", "name"]
		},
	};
    </script>
</head>
<body>
	<div class="row" >
		<h4 class="example-title" align="center">客户管理</h4>
			<div class="col-xs-2" style="padding: 0px 0px 0px 30px;">
				<input type="text" id="condition" name="condition" class="form-control" placeholder="查询条件"/>
			</div>
			<button class="btn btn-success" onclick="search()">查询</button>
			<div class="btn-group" >
			  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			   选择查询类型 <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu">
			    <li><a href="#" onclick="reload(0)">可用用户</a></li>
			    <li><a href="#" onclick="reload(1)">不可用用户</a></li>
			  </ul>
			</div>

		<br>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%" class="table table-striped table-bordered table-hover" id="customerData">
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
		 <!-- begin -->
          <div class="modal inmodal" id="modifyItemDesc" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                    <div class="tabs-container">
                    <!-- 选项卡分类 -->
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true"> 商品权限</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">其他权限</a>
                        </li>
                    </ul>
                    <!-- 选项卡分类 end-->
                    <!-- 选项卡内容 -->
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                            <div class="modal-header">
                           <i class="fa fa-laptop modal-icon"></i>
                           <h4 class="modal-title">客户商品权限设置</h4>
                           <input type="hidden" id="customerId">
                           <div style="margin-left: 30%" align="right" id="treeDemo" class="ztree" ></div>
                           </div>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-white" data-dismiss="modal" onclick="resetModifyForm()">关闭</button>
                               <button type="button" class="btn btn-primary" onclick="submitModifyForm()">提交请求</button>
                           </div>
                            </div>
                        </div>
                        <div id="tab-2" class="tab-pane">
                            <div class="panel-body">
                                <strong>后续开发</strong>
                            </div>
                        </div>
                    </div>
                    <!-- 选项卡内容嗯end -->
                </div>
                       </div>
                 </div>
          </div> <!-- end -->
	</div>
 	
    <script src="<%=path %>/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=path %>/js/content.min.js?v=1.0.0"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="<%=path %>/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
    <script src="<%=path %>/js/plugins/treeview/bootstrap-treeview.js"></script>
    
    <script type="text/javascript">
	    <!--页面加载时候  加载这个方法-->
	    $(document).ready(function() {
	    	iniTable(0);
	    });
	    function iniTable(state){
			var requestUrl = "<%=path%>/customer/customerList.html?state="+state;
			$('#customerData').bootstrapTable({
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
		                  {title: '用户类型',field: 'type',align: 'center',valign: 'middle',width:'100px',
			               	   formatter:function(value, row, index){
			               		   if(row.type == 0){
				               	    return '企业用户';
			               		   }else{
			               			return '普通用户';
			               		   }
				               	}},
		                  {title: '用户状态',field: 'state',align: 'center',valign: 'middle',width:'100px',
			               	   formatter:function(value, row, index){
			               		   if(row.state == 0){
				               	    return '<font color = "blue">可用</font>';
			               		   }else{
			               			return '<font color = "red">不可用</font>';
			               		   }
				               	}},
		                  {field:'operate', title:'操作',align:'center',width:'80px',
			               	   formatter:function(value, row, index){
			               		var c = '<span>   </span>';
			               		if(row.state == 1){
				               		var a = '';
				               	   	var b = '<button class="btn btn-info" onclick="return recover(\''+row.id+'\')">恢复用户</button>';
			               		}else{
				               		var a = '<button class="btn btn-info" onclick="confirmDecs(\''+row.id+'\')">设置权限</button>';
				               	    var b = '<button class="btn btn-info" onclick="confirmNo(\''+row.id+'\')">删除用户</button>';
			               		}
			               	    return a+c+b;
			               	   }
			              }
		                  
		          ]
	          });
		}
	    /*打开设置配置参数的model*/
	    function confirmDecs(id){
	    	$("#customerId").val(id);
	    	$('#modifyItemDesc').modal({
		  	      keyboard: true
		    });
	    	var setting = {
	    		check: {
	    			enable: true,
	    			autoCheckTrigger: true
	    		},
	    		data: {
	    			simpleData: {
	    				enable: true
	    			}
	    		},
	    		async: {
	    			enable: true,
	    			url: "<%=path %>/security/itemCategory.html?customerId="+id,
	    			autoParam: ["id", "name"]
	    		},
	    	};
			$.fn.zTree.init($("#treeDemo"), setting);//加载树
	    }
	    function submitModifyForm(){
	    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	    	var nodes = treeObj.getCheckedNodes(true);
	    	var categoryId = new Array();
	    	var customerId = $("#customerId").val();
	    	for(var i=0;i<nodes.length;i++){
	    		if(nodes[i].level==1){//只获得子节点
	    			categoryId.push(nodes[i].id	)
	    		}
	    	} 
	    	layer.msg("提交中");
            $.ajax({
                type:"post",
                url:"<%=path %>/security/addItemCategory.html" ,
                dataType:'json',
                data:{customerId:customerId,categoryId:categoryId},
                success:function(data){
                    if(data == 0){
                    	window.location.reload();
                    }else if(data == 1){
                    	layer.msg("权限修改失败");
                    }
                },error:function(){
                	layer.msg("异常");
                }
            });
	    }
	    /*启用模态框*/
	    function turnToAddUser(id){
	    	layer.msg("功能待完善");
	    }
	    //刷新
	    function reload(state){
	    	$('#customerData').bootstrapTable('refresh', {url: '<%=path%>/customer/customerList.html?state='+state}); 
	    }
		function search(){
			var condition = $('#condition').val();
	    	$('#customerData').bootstrapTable('refresh', {url: '<%=path%>/customer/queryByNameOrTelOrEamil.html?condition='+encodeURI(encodeURI(condition))}); 
		}
	    /*验证并提交添加用户的申请*/
<%-- 	    function submitAddUser(){
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
	    		return;
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
                data:{id:id,name:name,password:password,phone:phone,email:email,type:'0',address:address,info:info,state:'0'},
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
	    } --%>
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
			var id = $("#user_State").val();
			layer.msg("删除中,请稍后");
	    	$.ajax({
	    		   type: "POST",
	    		   url: "<%=path%>/customer/updateCustomerState.html",
	    		   async : false,
	    		   data:{id:id,state:'1'},
	    		   success: function(data){
	    			   	if(data == '0'){
	    			   		layer.msg("用户删除成功");
		    				window.location.reload();
	    			   	}else if(data == '1'){
	    			   		layer.msg("数据异常");
	    			   	}
	    		   }
	    	});
		}
	    function recover(id){
	        if(confirm("是否恢复该用户")){  
				layer.msg("恢复中,请稍后");
		    	$.ajax({
		    		   type: "POST",
		    		   url: "<%=path%>/customer/updateCustomerState.html",
		    		   async : false,
		    		   data:{id:id,state:'0'},
		    		   success: function(data){
		    			   	if(data == '0'){
		    			   		layer.msg("用户恢复成功");
			    				window.location.reload();
		    			   	}else if(data == '1'){
		    			   		layer.msg("数据异常");
		    			   	}
		    		   }
		    	});
	            return true;  
	        }  
	        return false;  
	    }
    </script>
</body>
</html>