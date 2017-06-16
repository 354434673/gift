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
    <link href="<%=path %>/css/jquery.mloading.css" rel="stylesheet">
    <style type="text/css">
        .dropdown-submenu {
            position: relative;
        }
        .dropdown-submenu > .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: -6px;
            margin-left: -1px;
            -webkit-border-radius: 0 6px 6px 6px;
            -moz-border-radius: 0 6px 6px;
            border-radius: 0 6px 6px 6px;
        }
        .dropdown-submenu:hover > .dropdown-menu {
            display: block;
        }
        .dropdown-submenu > a:after {
            display: block;
            content: " ";
            float: right;
            width: 0;
            height: 0;
            border-color: transparent;
            border-style: solid;
            border-width: 5px 0 5px 5px;
            border-left-color: #ccc;
            margin-top: 5px;
            margin-right: -10px;
        }
        .dropdown-submenu:hover > a:after {
            border-left-color: #fff;
        }
        .dropdown-submenu.pull-left {
            float: none;
        }
        .dropdown-submenu.pull-left > .dropdown-menu {
            left: -100%;
            margin-left: 10px;
            -webkit-border-radius: 6px 0 6px 6px;
            -moz-border-radius: 6px 0 6px 6px;
            border-radius: 6px 0 6px 6px;
        }
    </style>
</head>
<body>
   
   <div class="row">
		<h4 class="example-title" align="center">商品管理</h4>
		<div class="col-xs-12">
			<div class="col-xs-2" align="center">
				<a class="btn btn-info" onclick="turnToAdd()">新增商品</a>
			</div>
			<div class="col-xs-2" align="center">
				<a class="btn btn-info" onclick="turnToCache()">同步缓存</a>
			</div>
			<div class="col-xs-1">
	               <div class="dropdown" style="margin-left: -75px;margin-top: -0px;">
		            <button type="button" class="btn btn-info dropdown-toggle" id="dropdownMenu1" 
				      data-toggle="dropdown">
				    	请选择分类
				     <span class="caret"></span>
				   </button>
		            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
		                <c:forEach items="${categoryList}" var="listTemp">
					      	<c:forEach items="${listTemp }" var="mapTemp">
				                <li class="dropdown-submenu" style="text-align: left;">
				                    <a tabindex="-1" href="javascript:;">${mapTemp.key}</a>
					                    <ul class="dropdown-menu">
						                    <c:forEach items="${mapTemp.value }" var="project">
							                        <li><a tabindex="-1" href="javascript:;" onclick="serachCategory('${project.name}','${project.id }')">${project.name }</a></li>
						                     </c:forEach> 
					                    </ul>
				                </li>
					      	</c:forEach>
				      </c:forEach>    
		            </ul>
		       </div>
			</div>
	       <div class="col-xs-2">
		       <input class="form-control" type="text" readonly="readonly" style="margin-left: -110px;margin-top: 0px;" id="searchInput"  placeholder="选择要查询的分类"/>
			   <input type="hidden" id="searchItemId" name="categoryid"/>
	       </div>
			<div class="col-xs-2">
				<input type="text" id="searchTitle" name="searchTitle" style="margin-left: -130px;display: inline" class="form-control" placeholder="名称"/>
				<button class="btn btn-info" onclick="searchItem()">查询</button>
				<a onclick="resetSearchItem()"><button class="btn btn-info">重置</button></a>
			</div>
		</div>
		
		
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
		
		<div class="modal inmodal" id="addItemCategory" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                           </button>
                           <i class="fa fa-laptop modal-icon"></i>
                           <h4 class="modal-title">新增商品</h4>
                       </div>
                        <form action="<%=path %>/item/addItem.html" enctype="multipart/form-data" method="post" id="addItemForm" >
	                        <div class="modal-body">
	                            <div class="form-group">
			                       <label>选择商品分类</label>
			                       <div class="row">
				                       	<div class="col-xs-2">
					                          <div class="form-control" style="margin-top:12px;">
						                        <div class="dropdown" style="margin-left: -12px;margin-top: -8px;">
										            <button type="button" class="btn btn-default dropdown-toggle" id="dropdownMenu1" 
												      data-toggle="dropdown">
												    	请选择
												     <span class="caret"></span>
												   </button>
										            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
										                <c:forEach items="${categoryList}" var="listTemp">
													      	<c:forEach items="${listTemp }" var="mapTemp">
												                <li class="dropdown-submenu" style="text-align: left;">
												                    <a tabindex="-1" href="javascript:;">${mapTemp.key}</a>
													                    <ul class="dropdown-menu">
														                    <c:forEach items="${mapTemp.value }" var="project">
															                        <li><a tabindex="-1" href="javascript:;" onclick="selectCategory('${project.name}','${project.id }')">${project.name }</a></li>
														                     </c:forEach> 
													                    </ul>
												                </li>
													      	</c:forEach>
												      </c:forEach>    
										            </ul>
										       </div>
									       </div>
				                       </div>
				                       	<div class="col-xs-10">
				                       		<input class="form-control" type="text" readonly="readonly" style="margin-left: -12px;margin-top: 12px;" id="cateInput"/>
				                       		<input type="hidden" id="hiddenItemId" name="categoryId"/>
				                       	</div>
			                       	</div>
							        <label>商品标题</label><input type="text" id="title" name="titile" class="form-control">
							        <label>商品卖点</label><input type="text" id="sellpoint" name="sellPoint" class="form-control">
							      <!--   <label>商品数量</label><input type="text" id="titledesc" name="titledesc" class="form-control">
							        <label>条形码</label><input type="text" id="titledesc" name="titledesc" class="form-control"> -->
							        <label>图一</label><input type="file" id="titledesc" name="myfileItem" class="form-control">
							        <label>图二</label><input type="file" id="titledesc" name="myfileItem" class="form-control">
							        <label>图三</label><input type="file" id="titledesc" name="myfileItem" class="form-control">
							        <label>图四</label><input type="file" id="titledesc" name="myfileItem" class="form-control">
				        		</div>
	                        </div>
                       </form>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                               <button type="button" class="btn btn-primary" onclick="submitItemForm()">保存</button>
                           </div>
                       </div>
                 </div>
          </div><!-- end -->
          
          <!-- begin -->
          <div class="modal inmodal" id="modifyItemDesc" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                           </button>
                           <i class="fa fa-laptop modal-icon"></i>
                           <h4 class="modal-title">新增或修改参数</h4>
                           <small class="font-bold">如果商品项较少，可选填
                           </div>
                            <form action="<%=path %>/item/modifyItemDesc.html" method="post" id="modifyForm">
	                           <div class="modal-body">
	                               <div class="form-group">
	                               		<input type="hidden" id="itemId"  name="itemDescs[0].itemId"/>
	                               		<label>描述一</label><input type="text" id="content" name="itemDescs[0].content" class="form-control">
	                               		<label>描述二</label><input type="text" id="content1" name="itemDescs[1].content" class="form-control">
	                               		<label>描述三</label><input type="text" id="content2" name="itemDescs[2].content" class="form-control">
	                               		<label>描述四</label><input type="text" id="content3" name="itemDescs[3].content" class="form-control">
	                               		<label>描述五</label><input type="text" id="content4" name="itemDescs[4].content" class="form-control">
	                               		<label>描述六</label><input type="text" id="content5" name="itemDescs[5].content" class="form-control">
	                               </div>
	                           	
	                           </div>
                           </form>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-white" data-dismiss="modal" onclick="resetModifyForm()">关闭</button>
                               <button type="button" class="btn btn-primary" onclick="submitModifyForm()">保存</button>
                           </div>
                       </div>
                 </div>
          </div> <!-- end -->
          
          
           <!-- begin -->
          <div class="modal inmodal" id="modifyItem" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog">
                   <div class="modal-content animated bounceInRight">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                           </button>
                           <i class="fa fa-laptop modal-icon"></i>
                           <h4 class="modal-title">修改商品参数</h4>
                           </div>
                            <form action="<%=path %>/item/modifyItem.html" method="post" id="modifyItemForm">
	                           <div class="modal-body">
	                               <div class="form-group">
	                               		<input type="hidden" id="modifyItemId"  name="id"/>
	                               		<label>商品标题</label><input type="text" id="modifytitile" name="titile" class="form-control">
	                               		<label>商品卖点</label><input type="text" id="modifysellpoint" name="sellPoint" class="form-control">
	                               </div>
	                           </div>
                           </form>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                               <button type="button" class="btn btn-primary" onclick="submitModifyItemForm()">保存</button>
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
    <script src="<%=path %>/js/jquery.mloading.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<script type="text/javascript">
	 	
		$(document).ready(function() {
	    	iniTable();
	    });
 	
	    
	    function iniTable(){
			var requestUrl = "<%=path%>/item/contents.html";
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
	            pageSize: 4,            //每页的记录行数（*）
	            pageList: [4, 8, 16],    //可供选择的每页的行数（*）
	    	    minimumCountColumns: 2,    //最少允许的列数
	    	    clickToSelect: true,    //是否启用点击选中行
	    	    uniqueId: "id",      //每一行的唯一标识，一般为主键列
	    	    showToggle:false,     //是否显示详细视图和列表视图的切换按钮
	    	    columns: [
	    	              {title: 'id',field: 'id',align: 'center',valign: 'middle',visible:false}, 
		                  {title: '商品名称',field: 'titile',align: 'center',valign: 'middle',width:'100px'}, 
		                  {title: '商品卖点',field: 'sellPoint',align: 'center',valign: 'middle',width:'100px'}, 
		                  {title: '商品图片',field: 'operate',align: 'center',valign: 'middle',width:'150px',
			            	  formatter:function(value, row, index){
				               		var a = '<img src=\'<%=imgPath%>'+row.pic+'\' width=150px; height=150px;/>';
				               	    return a;
			               	   }
			              }, 
			              {title: '商品图片',field: 'operate',align: 'center',valign: 'middle',width:'150px',
			            	  formatter:function(value, row, index){
				               		var b = '<img src=\'<%=imgPath%>'+row.pic2+'\' width=150px; height=150px;/>';
				               	    return b;
			               	   }
			              }, 
			              {title: '商品图片',field: 'operate',align: 'center',valign: 'middle',width:'150px',
			            	  formatter:function(value, row, index){
				               		var c = '<img src=\'<%=imgPath%>'+row.pic3+'\' width=150px; height=150px;/>';
				               	    return c;
			               	   }
			              }, 
			              {title: '商品图片',field: 'operate',align: 'center',valign: 'middle',width:'150px',
			            	  formatter:function(value, row, index){
				               		var d = '<img src=\'<%=imgPath%>'+row.pic4+'\' width=150px; height=150px;/>';
				               	    return d;
			               	   }
			              }, 
		                  {field:'operate', title:'操作',align:'center',width:'180px',
			               	   formatter:function(value, row, index){
			               		var a = '<button class="btn btn-info" onclick="confirmYes(\''+row.id+'\')">编辑</button>';
			               		var c = '<span>   </span>';
			               		if(row.state == 0){
				               	    var b = '<button class="btn btn-info" onclick="confirmTop(\''+row.id+'\',1)">上架</button>';
			               		}else if(row.state == 1){
			               			var b = '<button class="btn btn-warning" onclick="confirmUnder(\''+row.id+'\',0)">下架</button>';
			               		}
			               	    var d = '<button class="btn btn-info" onclick="confirmDecs(\''+row.id+'\')">配置参数</button>';
			               	    return a+c+b+c+d;
			               	   }
			              } ,
			              {field:'operate', title:'设置无权限查看',align:'center',width:'150px',
			               	   formatter:function(value, row, index){
			               		var c = '<button class="btn btn-info" onclick="confirmCommon(\''+row.id+'\',2)">配置无权限商品</button>';
			               	    return c;
			               	   }
			              } 
		                  
		          ]
	          });
		}
	    
	    function turnToAdd(){
	    	$('#addItemCategory').modal({
	  	      keyboard: true
	     	});
	    }
	    /*编辑商品详情*/
	    function confirmYes(id){
	    	var url = '<%=path%>/item/getItem/'+id;
	    	$.ajax({
	    		   type: "POST",
	    		   url: url,
	    		   async : false,
	    		   success: function(msg){
		    			var data = JSON.parse(msg);
				    	$("#modifyItemId").val(id);
				    	$("#modifytitile").val(data.titile);
				    	$("#modifysellpoint").val(data.sellPoint);
				    	$('#modifyItem').modal({
					  	      keyboard: true
					     });
	    				
	    		   }
	    	});
	    }
	    /*提交修改的商品参数*/
	    function submitModifyItemForm(){
	    	$("#modifyItemForm").submit();
	    }
	    /*打开设置配置参数的model*/
	    function confirmDecs(itemId){
	    	var url = '<%=path%>/item/getItemDescs/'+itemId;
	    	$.ajax({
	    		   type: "POST",
	    		   url: url,
	    		   async : false,
	    		   success: function(msg){
		    			var data = JSON.parse(msg);
		    			//var data=JSON.stringify(msg);
		    			//var data = eval('(' + msg + ')');
		    			$.each(data,function(n,value){
    						if(typeof(value.itemDescs[0]) != "undefined"){
    							$("#content").val(value.itemDescs[0].content);
    						}else{
    							$("#content").val("");
    						}
    						if(typeof(value.itemDescs[1]) != "undefined"){
    							$("#content1").val(value.itemDescs[1].content);
    						}else{
    							$("#content1").val("");
    						}
    						if(typeof(value.itemDescs[2]) != "undefined"){
    							$("#content2").val(value.itemDescs[2].content);
    						}else{
    							$("#content2").val("");
    						}
    						if(typeof(value.itemDescs[3]) != "undefined"){
    							$("#content3").val(value.itemDescs[3].content);
    						}else{
    							$("#content3").val("");
    						}
    						if(typeof(value.itemDescs[4]) != "undefined"){
    							$("#content4").val(value.itemDescs[4].content);
    						}else{
    							$("#content4").val("");
    						}
    						if(typeof(value.itemDescs[5]) != "undefined"){
    							$("#content5").val(value.itemDescs[5].content);
    						}else{
    							$("#content5").val("");
    						}
		    			 })
		    		
				    	$("#itemId").val(itemId);
				    	$('#modifyItemDesc').modal({
					  	      keyboard: true
					    });
	    		   }
	    	});
	    }
	    
	    /*提交商品分类*/
	    function submitItemForm(){
	    	var cateInput = $('#cateInput').val();
	    	if(cateInput == null || cateInput == ""){
	    		layer.msg("请选择分类");
	    		return;
	    	}
	    	var title = $("#title").val();
	    	if(title == null || title == ""){
	    		layer.msg("请输入商品标题");
	    		return;
	    	}
	    	var title = $("#title").val();
	    	if(title == null || title == ""){
	    		layer.msg("请输入商品标题");
	    		return;
	    	}
	    	var sellPoint = $("#sellpoint").val();
	    	if(sellPoint == null || sellPoint == ""){
	    		layer.msg("请输入商品卖点");
	    		return;
	    	}
	    	var fileIsBlank = false;
	    	$("input[type='file']").each(function(){
	    		if(this.value == null || this.value == ""){
	    			fileIsBlank = true;
	    			return true;
	    		}
	    	})
	    	if(fileIsBlank){
	    		layer.msg("请选择上传的文件，不得缺少");
    			return false;
	    	}
	    	$("#addItemForm").submit();
	    }
	    /**/
	    function submitModifyForm(){
	    	$("#modifyForm").submit();
	    }
	    /*给input 赋值*/
	    function selectCategory(obj,id){
	    	$("#cateInput").val(obj);
	    	$("#hiddenItemId").val(id);
	    }
	    /*给搜索input 赋值*/
	    function serachCategory(name,id){
	    	$("#searchInput").val(name);
	    	$("#searchItemId").val(id);
	    }
	    /*重置input框*/
	    function resetSearchItem(){
	    	$("#searchInput").val("");
	    	$("#searchItemId").val("");
	    	$("#searchTitle").val("");
	    }
	    function searchItem(){
	    	var searchTitle = $("#searchTitle").val();
	    	var searchItemCategoryId = $("#searchItemId").val();
	    	if(searchTitle == null || searchTitle == ""){
	    		searchTitle = 0;
	    	}else{
	    		searchTitle = encodeURI(encodeURI(searchTitle));
	    	}
	    	if(searchItemCategoryId == null || searchItemCategoryId == ""){
	    		searchItemCategoryId = 0;
	    	}
	    	var url = '<%=path%>/item/getItemListByCondition/'+searchTitle+'/'+searchItemCategoryId;
	    	$('#contentdata').bootstrapTable('refresh',{url:url});
	    }
	    
	    $(function(){
	    	$("input[type='file']").bind("change", function () {
	    		   /*判断文件上传类型*/
	    		   var photoExt =  this.value.substr(this.value.lastIndexOf(".")).toLowerCase();
	    		     if(mod(photoExt)){
	    		    	layer.msg("请上传支持的文件格式");
	    		    	$(this).val("");
	    			    return false;
	    			 } 
	    	});
	    })
	    
	    function mod(obj){
		  if(obj != ".jpg" && obj != ".jpeg" && obj != ".png"){
			  return true;
		  }else{
			  return false;
		  }
		}
	    /*上架*/
	    function confirmTop(id,obj){
	    	var url = '<%=path%>/item/modifyState/'+id+'/'+obj;
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
	    /*下架*/
	    function confirmUnder(id,obj){
	    	var url = '<%=path%>/item/modifyState/'+id+'/'+obj;
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
	    /*配置权限*/
	    function confirmCommon(id,obj){
	    	var url = '<%=path%>/item/modifyState/'+id+'/'+obj;
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
	    /*更新缓存*/
	    function turnToCache(){
	    	$("body").mLoading({
				text:"正在同步缓存，请稍后",//加载文字，默认值：加载中...
				//icon:"",加载图标，默认值：一个小型的base64的gif图片
				html:false,//设置加载内容是否是html格式，默认值是false
				//content:"",忽略icon和text的值，直接在加载框中显示此值
				mask:true//是否显示遮罩效果，默认显示
			});
		    var url =  '<%=path%>/item/syncCache.html';
		     $.ajax({
		 		   type: "POST",
		 		   url: url,
		 		   async : true,
		 		   success: function(msg){
		 			  	var data = JSON.parse(msg);
		 			  	if(data.msg == 200){
		 				 	layer.msg("同步缓存成功");
		 					 $("body").mLoading("hide");
		 			  	}
		 		   }
		     });
	    }
	</script>
	
	
</body>
</html>