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
    <title>最新动态</title>
   	<link rel="shortcut icon" href="<%=path %>/favicon.ico"> 
  	<link href="<%=path %>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="<%=path %>/js/plugins/layer/skin/layer.css" rel="stylesheet">
    
</head>
<body>
	
	<script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=path %>/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=path %>/js/content.min.js?v=1.0.0"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="<%=path %>/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="<%=path %>/js/demo/bootstrap-table-demo.min.js"></script>
    <script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/editor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/editor/ueditor.all.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/editor/lang/zh-cn.js"></script>

	
	<div class="row">
		<h4 class="example-title" align="center">最新动态管理</h4>
		
		<div class="col-xs-1">
				<a class="btn btn-info" onclick="turnToAddNews()">新建新闻</a>
			</div>
			<!-- <div class="col-xs-2">
				<input type="text" id="name" name="name" class="form-control" placeholder="姓名"/>
			</div>
			<button class="btn btn-success" onclick="searchUser()">查询</button> -->
		<br>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%" class="table table-striped table-bordered table-hover" id="userdata">
						<!-- 在这个TABLE填充数据 -->
					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		
		<div class="modal fade" id="addNews" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog" style="width:70%;margin-top:10%;">
			      <div class="modal-content">
			         <div class="modal-header">
			            <button type="button" class="close" 
			               data-dismiss="modal" aria-hidden="true">
			                  &times;
			            </button>
			            <h4 class="modal-title" id="myModalLabel">
			             		添加最新动态(如若关闭，点击关闭按钮)
			            </h4>
			         </div>
			         	<form action="<%=path%>/news/addNews.html" method="post" id="addNewsForm">
				         	<div class="modal-body" align="center">
				         		<div>
									<input type="text" class="form-control" id="title" name="title" placeholder="文章标题" value="" style="width: 91%;"/>
				         		</div>
				         		<div style="margin-top: 10px;">
									<textarea style="width: 91%;resize:none;" rows="3"  class="form-control" id="content" name="content" placeholder="文章内容" value="" onclick="createEditor()"></textarea>
				         		</div>
					         </div>
				         	<div align="center">
							    <script id="editor" type="text/plain" style="width:1024px;"></script>
							</div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-primary" 
				               data-dismiss="modal" onclick="shundowModal()">关闭
				            </button>
				            <button type="button" class="btn btn-primary" onclick="submitAddNews()">
				             		保存
				            </button>
			         	</div>
			         	</form>
			      </div><!-- /.modal-content -->
			</div><!-- /.modal -->
	</div>
	
	<div class="modal fade" id="modifyNews" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog" style="width:70%;margin-top:10%;">
			      <div class="modal-content">
			         <div class="modal-header">
			            <button type="button" class="close" 
			               data-dismiss="modal" aria-hidden="true">
			                  &times;
			            </button>
			            <h4 class="modal-title" id="myModalLabel">
			             		修改最新动态(如若关闭，点击关闭按钮)
			            </h4>
			         </div>
			         	<form action="<%=path%>/news/addNews.html" method="post" id="modifyNewsForm">
				         	<div class="modal-body" align="center">
				         		<input type="hidden" id="newsId" name="id" value=""/>
				         		<div>
									<input type="text" class="form-control" id="newsTitle" name="title" placeholder="文章标题" value="" style="width: 91%;"/>
				         		</div>
				         		<div style="margin-top: 10px;">
									<textarea style="width: 91%;resize:none;" rows="3"  class="form-control" id="newsContent" name="content" placeholder="文章内容" value="" onclick="createEditor()"></textarea>
				         		</div>
					         </div>
				         	<div align="center">
							    <script id="editor2" type="text/plain" style="width:1024px;"></script>
							</div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-primary" 
				               data-dismiss="modal" onclick="shundowModal()">关闭
				            </button>
				            <button type="button" class="btn btn-primary" onclick="submitModifyNews()">
				             		保存
				            </button>
			         	</div>
			         	</form>
			      </div><!-- /.modal-content -->
			</div><!-- /.modal -->
	</div>
</div>


<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    /* var ue = UE.getEditor('editor'); */
	/*跳转到添加页面*/
    function turnToAddNews(){
    	$('#addNews').modal({
		      keyboard: true
	   });
    }
    /*提交表单数据*/
    function submitAddNews(){
    	//将editor中的内容  赋值给文本域
    	var content = UE.getEditor('editor').getContent();
    	//var content = UE.getEditor('editor').getAllHtml();
    	$("#content").val(content);
    	var title = $("#title").val();
    	if(title == null || title == ""){
    		layer.msg("请输入文章标题");
    		return false;
    	}
    	$("#addNewsForm").submit();
    }
    /*更新*/
    function submitModifyNews(){
    	//将editor中的内容  赋值给文本域
    	var content = UE.getEditor('editor2').getContent();
    	$("#newsContent").val(content);
    	var title = $("#newsTitle").val();
    	if(title == null || title == ""){
    		layer.msg("请输入文章标题");
    		return false;
    	}
    	$("#modifyNewsForm").submit();
    }
   
    $(document).ready(function() {
    	iniTable();
    });
	
    
    function iniTable(){
		var requestUrl = "<%=path%>/news/contents.html";
		$('#userdata').bootstrapTable({
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
	                  {title: '文章标题',field: 'title',align: 'center',valign: 'middle',width:'100px'}, 
	                  {field:'operate', title:'操作',align:'center',width:'80px',
		               	   formatter:function(value, row, index){
		               		var a = '<button class="btn btn-info" onclick="confirmYes(\''+row.id+'\')">编辑</button>&nbsp;';
		               		var c = '<button class="btn btn-info" onclick="viewCode(\''+row.id+'\')">预览</button>&nbsp;';
		               		if( row.state == 0){
			               	    var b = '<button class="btn btn-warning" onclick="confirmNo(\''+row.id+'\',1)">下架</button>';
		               		}else if(row.state == 1){
			               	    var b = '<button class="btn btn-info" onclick="confirmNo(\''+row.id+'\',0)">发布</button>';
		               		}
		               	    return a+c+b;
		               	   }
		              }
	                  
	          ]
          });
	}
    
    function confirmYes(id){
    	var url='<%=path%>/news/getContent/'+id;
    	$.ajax({
    		   type: "POST",
    		   url: url,
    		   async : false,
    		   success: function(msg){
	    			var data = JSON.parse(msg);
	    			$("#newsTitle").val(data.msg.title);
	    			$("#newsId").val(data.msg.id);
	    			
	    			$("#newsContent").hide();
	    			$("#newsContent").val(data.msg.content);
	    			//UE.getEditor('editor').execCommand('insertHtml', $('#content').val());
	    			$('#editor2').html($('#newsContent').val());
	    			UE.getEditor('editor2',{
	    				toolbars: [
	    				           [
	    				            'anchor', //锚点
	    				            'undo', //撤销
	    				            'redo', //重做
	    				            'bold', //加粗
	    				            'indent', //首行缩进
	    				            //'snapscreen', //截图
	    				            'italic', //斜体
	    				            'underline', //下划线
	    				            'strikethrough', //删除线
	    				            'subscript', //下标
	    				            'fontborder', //字符边框
	    				            'superscript', //上标
	    				            'formatmatch', //格式刷
	    				            'source', //源代码
	    				            'blockquote', //引用
	    				            'pasteplain', //纯文本粘贴模式
	    				            'selectall', //全选
	    				            //'print', //打印
	    				            //'preview', //预览
	    				            'horizontal', //分隔线
	    				            'removeformat', //清除格式
	    				            'time', //时间
	    				            'date', //日期
	    				            'unlink', //取消链接
	    				            'insertrow', //前插入行
	    				            'insertcol', //前插入列
	    				            'mergeright', //右合并单元格
	    				            'mergedown', //下合并单元格
	    				            'deleterow', //删除行
	    				            'deletecol', //删除列
	    				            'splittorows', //拆分成行
	    				            'splittocols', //拆分成列
	    				            'splittocells', //完全拆分单元格
	    				            'deletecaption', //删除表格标题
	    				            'inserttitle', //插入标题
	    				            'mergecells', //合并多个单元格
	    				            'deletetable', //删除表格
	    				            'cleardoc', //清空文档
	    				            'insertparagraphbeforetable', //"表格前插入行"
	    				            'insertcode', //代码语言
	    				            'fontfamily', //字体
	    				            'fontsize', //字号
	    				            'paragraph', //段落格式
	    				            //'simpleupload', //单图上传
	    				            //'insertimage', //多图上传
	    				            'edittable', //表格属性
	    				            'edittd', //单元格属性
	    				            'link', //超链接
	    				            'emotion', //表情
	    				            'spechars', //特殊字符
	    				            'searchreplace', //查询替换
	    				            //'map', //Baidu地图
	    				            //'gmap', //Google地图
	    				           // 'insertvideo', //视频
	    				            'help', //帮助
	    				            'justifyleft', //居左对齐
	    				            'justifyright', //居右对齐
	    				            'justifycenter', //居中对齐
	    				            'justifyjustify', //两端对齐
	    				            'forecolor', //字体颜色
	    				            'backcolor', //背景色
	    				            'insertorderedlist', //有序列表
	    				            'insertunorderedlist', //无序列表
	    				            'fullscreen', //全屏
	    				            'directionalityltr', //从左向右输入
	    				            'directionalityrtl', //从右向左输入
	    				            'rowspacingtop', //段前距
	    				            'rowspacingbottom', //段后距
	    				            //'pagebreak', //分页
	    				            //'insertframe', //插入Iframe
	    				            'imagenone', //默认
	    				            'imageleft', //左浮动
	    				            'imageright', //右浮动
	    				            'attachment', //附件
	    				            'imagecenter', //居中
	    				           // 'wordimage', //图片转存
	    				            'lineheight', //行间距
	    				            'edittip ', //编辑提示
	    				            'customstyle', //自定义标题
	    				            'autotypeset', //自动排版
	    				           // 'webapp', //百度应用
	    				            'touppercase', //字母大写
	    				            'tolowercase', //字母小写
	    				           // 'background', //背景
	    				            //'template', //模板
	    				           // 'scrawl', //涂鸦
	    				           // 'music', //音乐
	    				           // 'inserttable', //插入表格
	    				            'drafts', // 从草稿箱加载
	    				            //'charts', // 图表
	    				        ]
	    				       ],
	    			});
	    			$('#modifyNews').modal({
	    			      keyboard: true
	    		  	});
    		   }
    	});
    }
	/*关闭modal对话框*/
    function shundowModal(){
    	window.location.href='<%=path%>/news/basic.html';
    }
    /*删除*/
    function confirmNo(id,state){
    	window.location.href='<%=path%>/news/deleteNew/'+id+'/'+state;
    }
	
    function viewCode(id){
    	var url = '<%=path%>/news/getContentByView/'+id
    	$.ajax({
 		   type: "POST",
 		   url: url,
 		   async : false,
 		   success: function(msg){
	    			var data = JSON.parse(msg);
			    	layer.open({
			      	  type: 1,
			      	  closeBtn: 1,
			      	  title: '预览页面(注意:如一行内容达到最大边距，则没有进行换行，否则会影响商城页面)',
			      	  skin: 'layui-layer-rim', //加上边框
			      	  area: ['100%', '80%'], //宽高
			      	  shade: 0.6, //遮罩透明度
			          anim: 2, //0-6的动画形式，-1不开启
			      	  content: data.msg
			      	});
 		   }
    	});
    }
    
    function createEditor() {
    	$("#content").hide();
        UE.getEditor('editor', {
			initialFrameWidth : '100%',
			initialFrameHeight :500,
			toolbars: [
			           [
			            'anchor', //锚点
			            'undo', //撤销
			            'redo', //重做
			            'bold', //加粗
			            'indent', //首行缩进
			            //'snapscreen', //截图
			            'italic', //斜体
			            'underline', //下划线
			            'strikethrough', //删除线
			            'subscript', //下标
			            'fontborder', //字符边框
			            'superscript', //上标
			            'formatmatch', //格式刷
			            'source', //源代码
			            'blockquote', //引用
			            'pasteplain', //纯文本粘贴模式
			            'selectall', //全选
			            //'print', //打印
			            //'preview', //预览
			            'horizontal', //分隔线
			            'removeformat', //清除格式
			            'time', //时间
			            'date', //日期
			            'unlink', //取消链接
			            'insertrow', //前插入行
			            'insertcol', //前插入列
			            'mergeright', //右合并单元格
			            'mergedown', //下合并单元格
			            'deleterow', //删除行
			            'deletecol', //删除列
			            'splittorows', //拆分成行
			            'splittocols', //拆分成列
			            'splittocells', //完全拆分单元格
			            'deletecaption', //删除表格标题
			            'inserttitle', //插入标题
			            'mergecells', //合并多个单元格
			            'deletetable', //删除表格
			            'cleardoc', //清空文档
			            'insertparagraphbeforetable', //"表格前插入行"
			            'insertcode', //代码语言
			            'fontfamily', //字体
			            'fontsize', //字号
			            'paragraph', //段落格式
			            //'simpleupload', //单图上传
			            //'insertimage', //多图上传
			            'edittable', //表格属性
			            'edittd', //单元格属性
			            'link', //超链接
			            'emotion', //表情
			            'spechars', //特殊字符
			            'searchreplace', //查询替换
			            //'map', //Baidu地图
			            //'gmap', //Google地图
			           // 'insertvideo', //视频
			            'help', //帮助
			            'justifyleft', //居左对齐
			            'justifyright', //居右对齐
			            'justifycenter', //居中对齐
			            'justifyjustify', //两端对齐
			            'forecolor', //字体颜色
			            'backcolor', //背景色
			            'insertorderedlist', //有序列表
			            'insertunorderedlist', //无序列表
			            'fullscreen', //全屏
			            'directionalityltr', //从左向右输入
			            'directionalityrtl', //从右向左输入
			            'rowspacingtop', //段前距
			            'rowspacingbottom', //段后距
			            //'pagebreak', //分页
			            //'insertframe', //插入Iframe
			            'imagenone', //默认
			            'imageleft', //左浮动
			            'imageright', //右浮动
			            'attachment', //附件
			            'imagecenter', //居中
			           // 'wordimage', //图片转存
			            'lineheight', //行间距
			            'edittip ', //编辑提示
			            'customstyle', //自定义标题
			            'autotypeset', //自动排版
			           // 'webapp', //百度应用
			            'touppercase', //字母大写
			            'tolowercase', //字母小写
			           // 'background', //背景
			            //'template', //模板
			           // 'scrawl', //涂鸦
			           // 'music', //音乐
			           // 'inserttable', //插入表格
			            'drafts', // 从草稿箱加载
			            //'charts', // 图表
			        ]
			       ],
		});
    }
    
    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
    
</script>
</body>
</html>