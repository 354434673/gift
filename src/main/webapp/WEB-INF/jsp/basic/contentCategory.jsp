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
		<h4 class="example-title" align="center">前台页面主要功能</h4>
		
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
	    	iniTable();
	    });
 	
	    
	    function iniTable(){
			var requestUrl = "<%=path%>/contentCategory/contents.html";
			$('#contentdata').bootstrapTable({
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
	    	    columns: [
	    	              {title: 'id',field: 'id',align: 'center',valign: 'middle',visible:false}, 
		                  {title: '分类名称',field: 'name',align: 'center',valign: 'middle',width:'100px'}, 
		                 {field:'operate', title:'操作',align:'center',width:'80px',
			               	   formatter:function(value, row, index){
			               		var a = '<button class="btn btn-info" onclick="confirmYes(\''+row.id+'\')">编辑</button>';
			               		var c = '<span>   </span>';
			               	    var b = '<button class="btn btn-info" onclick="confirmNo(\''+row.id+'\')">删除</button>';
			               	    return a+c+b;
			               	   }
			              } 
		                  
		          ]
	          });
		}
	</script>

	
</body>
</html>