<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>
<body>
   <div class="row">
		<h4 class="example-title" align="center">banner及首页大图</h4>
			
			<div class="col-xs-6">
				<div class="col-xs-3" align="center">
					<a class="btn btn-info" onclick="turnToAdd()">新增banner</a>
				</div>
				<div class="col-xs-3" align="center">
					<a class="btn btn-info" onclick="turnToAddHomePage()">新增首页大图</a>
				</div>
			</div>
			<!-- <div class="col-xs-2">
				<input type="text" id="name" name="name" class="form-control" placeholder="名称"/>
			</div>
			<button class="btn btn-success" onclick="search()">查询</button> -->
		<br>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%" class="table table-striped table-bordered table-hover" id="contentdata">
						<!-- 在这个TABLE填充数据 -->
					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		
		
		 <div class="modal inmodal" id="addbanner" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                           </button>
                           <i class="fa fa-laptop modal-icon"></i>
                           <h4 class="modal-title">首页banner</h4>
                           <small class="font-bold">请上传之前，确保banner的尺寸为1920*688；
                       </div>
                       <form action="<%=path%>/content/addBanner.html" method="post" id="bannerForm" enctype="multipart/form-data">
	                       <div class="modal-body">
	                           <div class="form-group" id="groupBanner">
	                           		<div class="bannerClass">
		                           		<input type="hidden" name="token" value="${token}" />
		                           		<label>图一</label> <input type="file" name="myfilesBanner" id="banner1"  class="form-control" accept="image/jpeg,image/jpg,image/png">
		                           		<label>图二</label> <input type="file" name="myfilesBanner" id="banner2"  class="form-control" accept="image/jpeg,image/jpg,image/png">
		                           		<label>图三</label> <input type="file" name="myfilesBanner" id="banner3"  class="form-control" accept="image/jpeg,image/jpg,image/png">
		                           		<label>图四</label> <input type="file" name="myfilesBanner" id="banner4"  class="form-control" accept="image/jpeg,image/jpg,image/png">
		                           		<label>图五</label> <input type="file" name="myfilesBanner" id="banner5"  class="form-control" accept="image/jpeg,image/jpg,image/png">
	                           		</div>
	                           </div>
	                       </div>
                      </form>
                       <div class="modal-footer">
                           <button type="button" class="btn btn-white" data-dismiss="modal" onclick="resetBannerForm()">关闭</button>
                           <button type="button" class="btn btn-primary" onclick="submitBannerForm()">保存</button>
                       </div>
                    </div>
                 </div>
          </div>
          
          
           <div class="modal inmodal" id="addHomePage" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                           </button>
                           <i class="fa fa-laptop modal-icon"></i>
                           <h4 class="modal-title">首页大图</h4>
                           <small class="font-bold">请上传之前，确保大图的尺寸为1920*668；注意图片的对应顺序！
                           </div>
                            <form action="<%=path %>/content/addHomePage.html" enctype="multipart/form-data" method="post" id="homePageForm">
	                           <div class="modal-body">
	                           	<div class="homePageClass">
	                               <div class="form-group">
	                               		<input type="hidden"  name="token" value="${token}" />
	                               		<label>公司介绍</label><input type="file" name="myfiles" class="form-control" accept="image/jpeg,image/jpg,image/png">
	                               		<label>产品中心</label><input type="file" name="myfiles" class="form-control" accept="image/jpeg,image/jpg,image/png">
	                               		<label>成功案例</label><input type="file" name="myfiles" class="form-control" accept="image/jpeg,image/jpg,image/png">
	                               		<label>电子产品</label><input type="file" name="myfiles" class="form-control" accept="image/jpeg,image/jpg,image/png">
	                               		<label>最新动态</label><input type="file" name="myfiles" class="form-control" accept="image/jpeg,image/jpg,image/png">
	                               </div>
	                           	</div>
	                           </div>
                           </form>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-white" data-dismiss="modal" onclick="resetHomePageForm()">关闭</button>
                               <button type="button" class="btn btn-primary" onclick="submitHomePageForm()">保存</button>
                           </div>
                       </div>
                 </div>
          </div>
		
		 <div class="modal fade" id="deleteBanner" tabindex="-1" role="dialog" aria-labelledby="del_hint_title" aria-hidden="true">
			   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal"  aria-hidden="true">× </button>
				            <h4 class="modal-title" id="del_hint_title">
				              	下架
				            </h4>
				         </div>
				         
				         <div class="modal-body">
				           	下架后，商城将下架这张图片，是否下架？
				           	<input type="hidden" id=bannerId name="bannerId" value=""/>
				           	<input type="hidden" id=categoryID name="categoryId" value=""/>
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
				            </button>
				            <button type="button" onclick="deleteBanner()" class="btn btn-danger">
				           		   下架
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
			   </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
		
          <div class="modal inmodal" id="modifyContent" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                           </button>
                           <i class="fa fa-laptop modal-icon"></i>
                           <h4 class="modal-title">首页大图</h4>
                           <small class="font-bold">如需修改页面URL请联系管理员
                           </div>
                            <form action="<%=path %>/content/modifyContent.html" method="post" id="modifyForm">
	                           <div class="modal-body">
	                               <div class="form-group">
	                               		<input type="hidden" id="id"  name="id"/>
	                               		<label>标题</label><input type="text" id="title" name="title" class="form-control">
	                               		<label>标题描述</label><input type="text" id="titledesc" name="titledesc" class="form-control">
	                               		<label>图片链接</label><input type="text" id="url" name="url" class="form-control">
	                               </div>
	                           	
	                           </div>
                           </form>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-white" data-dismiss="modal" onclick="resetModifyForm()">关闭</button>
                               <button type="button" class="btn btn-primary" onclick="submitModifyForm()">保存</button>
                           </div>
                       </div>
                 </div>
          </div>         
		
	</div>
   
   <script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=path %>/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=path %>/js/content.min.js?v=1.0.0"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="<%=path %>/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
    <script src="<%=path %>/js/basic/basic.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<script type="text/javascript">
	$(document).ready(function() {
    	iniTable();
    });
	
    
function iniTable(){
	var requestUrl = "<%=path%>/content/contents.html";
	$('#contentdata').bootstrapTable({
	    url: requestUrl, 
	    method: 'post',      //请求方式（*）
	    striped: true,      //是否显示行间隔色
	    cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    sortable: true,      //是否启用排序
	    showRefresh: true,  //显示刷新按钮  
	    striped: true,        //是否显示行间隔色
	    sortOrder: "desc",          //排序方式 
	    pagination: true,          //是否显示分页（*）
	    sidePagination: "server",      //分页方式：client客户端分页，server服务端分页（*）
	    pageNumber:1,            //初始化加载第一页，默认第一页
	    pageSize: 5,            //每页的记录行数（*）
	    pageList: [5, 10, 15],    //可供选择的每页的行数（*）
	    minimumCountColumns: 2,    //最少允许的列数
	    clickToSelect: true,    //是否启用点击选中行
	    uniqueId: "id",      //每一行的唯一标识，一般为主键列
	    showToggle:true,     //是否显示详细视图和列表视图的切换按钮
	    columns: [
	              {title: 'id',field: 'id',align: 'center',valign: 'middle',visible:false}, 
	              {title: 'categoryid',field: 'categoryid',align: 'center',valign: 'middle',visible:false}, 
	              {title: '名称',field: 'title',align: 'center',valign: 'middle',width:'100px'}, 
	              {title: '图片',field: 'operate',align: 'center',valign: 'middle',width:'150px',
	            	  formatter:function(value, row, index){
		               		var b = '<img src=\'<%=imgPath%>'+row.pic+'\' width=300px; height=150px;/>';
		               	    return b;
	               	   }
	              }, 
	             {field:'operate', title:'操作',align:'center',width:'80px',
	               	   formatter:function(value, row, index){
	               		var a = '<button class="btn btn-info" onclick="modifyContet(\''+row.id+'\')">编辑</button>';
	               		var c = '<span>   </span>';
	               	    var b = '<button class="btn btn-info" onclick="removeContent(\''+row.id+'\',\''+row.categoryid+'\')">下架</button>';
	               	    return a+c+b;
	               	   }
	              } 
	              
	      ]
	  });
}

/*编辑banner*/
function modifyContet(obj){
	var url = '<%=path%>/content/getContent/'+obj;
	$.ajax({
		   type: "POST",
		   url: url,
		   async : false,
		   success: function(msg){
			   //这两种方式，哪个都可以+
 			var data = JSON.parse(msg);
 			//var data1 = jQuery.parseJSON(msg);
 			$("#id").val(data.id);
 			$("#title").val(data.title);
 			$("#titledesc").val(data.titledesc);
 			$("#url").val(data.url);
 			$('#modifyContent').modal({
 			      keyboard: true
 		 	});
		   }
	});
	
}
/*提交修改的URL等content信息*/
function submitModifyForm(){
	$("#modifyForm").submit();
}
/*移除banner*/
function removeContent(obj,obj1){
	$("#bannerId").val(obj);
	$("#categoryID").val(obj1);
	$('#deleteBanner').modal({
	      keyboard: true
   	});
}
/*逻辑删除banner*/
function deleteBanner(){
	var categoryId = $("#categoryID").val();
	if(categoryId == 2){
		layer.msg("请先上传一个替换的图片");
		return;
	}
	var bannerId = $("#bannerId").val();
	var url='<%=path%>/content/deleteBanner/'+bannerId;
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
	</script>
</body>
</html>