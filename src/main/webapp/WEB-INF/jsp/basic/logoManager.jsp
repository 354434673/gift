<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
String imgPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>分类列表</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
   	<link rel="shortcut icon" href="<%=path %>/favicon.ico"> 
  	<link href="<%=path %>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <style type="text/css">
    </style>
</head>
<body>
   
   <div class="row">
		<h4 class="example-title" align="center">企业用户LOGO管理</h4>
		</div>
			<div class="col-xs-2" style="padding: 0px 0px 0px 15px;">
				<input type="text" id="condition" name="condition" class="form-control" placeholder="查询条件"/>
			</div>
			<button class="btn btn-success" onclick="search()">查询</button>
			<div class="btn-group" >
			  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			   选择查询类型 <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu">
			    <li><a href="#" onclick="reload(0)" >可用</a></li>
			    <li><a href="#" onclick="reload(1)" >不可用</a></li>
			    <li><a href="#" onclick="reload(2)" >用户删除</a></li>
			  </ul>
			</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%" class="table table-striped table-bordered table-hover" id="logoData">
						<!-- 在这个TABLE填充数据 -->
					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
          
          <!-- begin -->
          <div class="modal inmodal" id="modifyItemDesc" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                           </button>
                           <i class="fa fa-laptop modal-icon"></i>
                           <h4 class="modal-title">图片下架</h4>
                           </div>
                            <form action="<%=path %>/item/modifyItemDesc.html" method="post" id="modifyForm">
	                           <div class="modal-body">
	                               <div class="form-group">
	                               		<input type="hidden" id="logoId" />
	                               		<label>下架理由</label><input type="text" id=describe class="form-control" placeholder="下架理由"> 
	                               </div>
	                           </div>
                           </form>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-white" data-dismiss="modal" onclick="resetModifyForm()">关闭</button>
                               <button type="button" class="btn btn-primary" onclick="submitModifyForm()">提交请求</button>
                           </div>
                       </div>
                 </div>
          </div> <!-- end -->
          
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
	 	
		$(document).ready(function() {
	    	iniTable(0);
	    });
 	
	    
	    function iniTable(state){
			var requestUrl = "<%=path%>/PersonalCenter/queryAllLogo.html?state="+state;
			$('#logoData').bootstrapTable({
	    	    url: requestUrl, 
	    	    method: 'post',      //请求方式（*）
	    	    striped: true,      //是否显示行间隔色
	    	    cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    	    sortable: true,      //是否启用排序
	    	    sortOrder: "desc",          //排序方式 
	    	    pagination: true,          //是否显示分页（*）
	    	    sidePagination: "server",      //分页方式：client客户端分页，server服务端分页（*）
	            pageNumber:1,            //初始化加载第一页，默认第一页
	            pageSize: 4,            //每页的记录行数（*）
	            pageList: [4, 8, 16],    //可供选择的每页的行数（*）
	    	    minimumCountColumns: 2,    //最少允许的列数
	    	    clickToSelect: true,    //是否启用点击选中行
	    	    uniqueId: "id",      //每一行的唯一标识，一般为主键列
	    	    showToggle:false,     //是否显示详细视图和列表视图的切换按钮
	    	    columns: [
	    	              {title: 'id',field: 'id',align: 'center',valign: 'middle',visible:false}, 
	    	              {title: '顾客名称',field: 'customer.name',align: 'center',valign: 'middle',width:'200px'}, 
		                  {title: 'LOGO名称',field: 'title',align: 'center',valign: 'middle',width:'200px',
			               	   formatter:function(value, row, index){
			               		 	return row.title==''?'用户未填':row.title;
				               	}
	    	              }, 
		                  {title: 'LOGO描述',field: 'content',align: 'center',valign: 'middle',width:'200px',
			               	   formatter:function(value, row, index){
			               		 	return row.content==''?'用户未填':row.content;
				               	}
	    	              }, 
			              {title: '商品图片',field: 'url',align: 'center',valign: 'middle',width:'10px',
			            	  formatter:function(value, row, index){
				               		var b = '<img src=\''+row.url+'\' width=150px; height=150px;/>';
				               	    return b;
			               	   }
			              }, 
			              {title: 'LOGO状态',field: 'state',align: 'center',valign: 'middle',width:'100px',
			               	   formatter:function(value, row, index){
			               		 	return row.state==0?'正常':'异常';
				               	}
			              }, 
			              {title: '状态描述',field: 'statedescribe',align: 'center',valign: 'middle',width:'100px',
			               	   formatter:function(value, row, index){
			               			return (row.statedescribe==null||row.statedescribe=='')?'无状态描述':row.statedescribe;
				               	   }
			              }, 
		                  {field:'operate', title:'操作',align:'center',width:'100px',
			               	   formatter:function(value, row, index){
			               		var a = '<button class="btn btn-info" onclick="confirmYes(\''+row.id+'\')">编辑</button>';
			               		var c = '<span></span>';
			               		if(row.state == 1){
				               	    var b = '<button class="btn btn-warning" onclick="confirmTop(\''+row.id+'\',1)">上架</button>';
			               		}else if(row.state == 0){
			               			var b = '<button class="btn btn-warning" onclick="confirmDecs(\''+row.id+'\',0)">下架</button>';
			               		}else if(row.state == 2){
			               			var b = '<button class="btn btn-warning" onclick="confirmDecs(\''+row.id+'\',0)">待定</button>';
			               		}
			               	    return a+c+b+c;
			               	   }
			              } 
		                  
		          ]
	          });
		}
	    /*打开设置配置参数的model*/
	    function confirmDecs(id){
	    	$("#logoId").val(id);
	    	$('#modifyItemDesc').modal({
		  	      keyboard: true
		    });
	    }
	    /**/
	    function submitModifyForm(){
		    var describe = $('#describe').val();
	    	if(describe == null||describe==''){
	    		layer.msg("理由不能为空");
	    		return false;
	    	}else{
		    	var id = $('#logoId').val();
	    		layer.msg("提交中");
	            $.ajax({
	                type:"post",
	                url:"<%=path %>/PersonalCenter/updataState.html" ,
	                dataType:'json',
	                data:{id:id,stateDescribe:describe,state:'1'},
	                success:function(data){
	                    if(data == 0){
	                    	window.location.reload();
	                    }else if(data == 1){
	                    	layer.msg("提交失败");
	                    }
	                },error:function(){
	                	layer.msg("账号发放异常");
	                }
	            });
	    	}
	    }
	    function confirmTop(id){
            $.ajax({
                type:"post",
                url:"<%=path %>/PersonalCenter/updataState.html" ,
                dataType:'json',
                data:{id:id,stateDescribe:null,state:'0'},
                success:function(data){
                    if(data == 0){
                    	window.location.reload();
                    }else if(data == 1){
                    	layer.msg("提交失败");
                    }
                },error:function(){
                	layer.msg("账号发放异常");
                }
            });
	    }
		//刷新
	    function reload(state){
	    	$('#logoData').bootstrapTable('refresh', {url: '<%=path%>/PersonalCenter/queryAllLogo.html?state='+state}); 
	    }
		function search(){
			var condition = $('#condition').val();
	    	$('#logoData').bootstrapTable('refresh', {url: '<%=path%>/PersonalCenter/queryLogoAndCustomerByNameOrTitle.html?condition='+encodeURI(encodeURI(condition))}); 
		}
	</script>
	
	
</body>
</html>