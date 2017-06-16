<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
int currentPage = Integer.parseInt(new java.text.DecimalFormat("0").format((request.getAttribute("currentPage"))));
int totalPage = Integer.parseInt(new java.text.DecimalFormat("0").format((request.getAttribute("totalPage"))));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <title>玖玖深红-VERY red</title>
    <meta name="keywords" content="友宏科技,jstyle,智能手环,智能美容仪,leyoung,深圳市友宏科技有限公司" />
    <meta name="description" content="友宏科技是一家专注技术的高新科技公司，致力于“健康、美容、医疗”相关产品的研发、生产、销售，主要提供“智能健康计步系列产品”、“家用美容和按摩系列产品”、“便携式医疗雾化器和吸引器系列产品”以及针对这些产品按照顾客要求提供满足其需求的个性化解决方案。" />
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/img/shop/title.ico" />
    <link rel="icon" sizes="16x16" href="<%=path %>/img/shop/title.ico" />
    <!--[if IE 8]><script type="text/javascript" src="js/html5shiv.min.js"></script><![endif]-->
    <!--[if IE 8]><script type="text/javascript" src="js/css3-mediaqueries.js"></script><![endif]-->
	<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/shop/global.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/health.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/product.css?v=16032101"/>
	<link rel="stylesheet" href="<%=path %>/css/shop/classify.css">
	<link rel="stylesheet" href="<%=path %>/css/bootstrap.min.css">
	<link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<style type="text/css">
		#showImg{position:absolute;z-index:0;left:0;top:850px;}
		#navHang{position:absolute;z-index:0;left:-10%;top:750px;}
		.sh-content li {
            border: solid 2px #FFFAFA;
            border-bottom-width: 1px;
            margin-left: 6px;
			margin-right: 6px;
        }
       /*  .twoGroup:hover img{transform: scale(1.04);} */
        li:hover {box-shadow: 0 1px 15px rgba(0,0,0,0.15);transform: scale(1.03);}
        
        body{margin: 0;}
			div.search {padding: 30px 0}
			form {
			  position: relative;
			  width: 300px;
			  margin: 0 auto;
			}
			.d1 input {
			  width: 100%;
			  height: 42px;
			  padding-left: 10px;
			  border: 2px solid #F8F8FF;
			  border-radius: 5px;
			  outline: none;
			  background: #F0FFF0;
			  color: #337ab7;
			}
			.d1 button {
			  position: absolute; 
			  top: 0;
			  right: 0px;
			  width: 42px;
			  height: 42px;
			  border: none;
			  background: #F8F8FF;
			  border-radius: 0 5px 5px 0;
			  cursor: pointer;
			}
			.d1 button:before {
			  content: "\f002";
			  font-family: FontAwesome;
			  font-size: 16px;
			  color: #337ab7;
			}
	</style>
</head>
<body>
	 <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe>
	
	<div class="all">
		<div  class="search d1" style="margin-top: 7%;width:100%;margin-left: 33%;">
		  <form action="<%=path %>/item/searchByName.html" method="post">
			  <input type="text" name="itemName" placeholder="搜索从这里开始...">
			  <button type="submit" class="fa fa-search"></button>
		  </form>
		</div>
		 <div class="sh-content" onselectstart="return false;" style="margin-left: 0%;margin-top: 0%;">
	          	<ul style="clear:both;">
	          		<c:if test="${empty itemLists }">
						<li>暂时没有商品</li>	          		
	          		</c:if>
	          		<c:if test="${not empty itemLists}">
		          		<c:forEach items="${itemLists }" var="item">
		           		<li style="float:left;width:23%;height:300px;" class="twoGroup">
		            		<a href="<%=path%>/item/showItem/${item.id}" target="_blank"><img title="${item.sellPoint}" src="${item.pic }" width="200px;" height="200px;"></a>
		             	<br><br>
		             	<div>${item.titile }</div>
		             	<div style="width:200px;margin:0 auto;color: #999;">${item.sellPoint}</div>
		             	<%-- <a href="<%=path%>/item/showItem/${item.id}" target="_blank" class="series-href"><button class="">点击查看</button></a> --%>
		           		</li>
		          		</c:forEach>
	          		</c:if>
	          	</ul>
	          	
	     </div>
	     <br>
	     <div id="navHang" class="sh-content" onselectstart="return false;" style="width:100%;margin-top: 3%;">
	     	<ul class="pagination pagination-sm" style="margin-left: 50%;margin-top: 0%;">
	          		<%
						int maxPage = 1;
						int minpage = 1;
						if(totalPage<=5){
							minpage = 1;
							maxPage=totalPage;
						}else{
						if(currentPage>1){
							minpage=currentPage-4>0?currentPage-4:1;
						if(totalPage<=currentPage+4){
							maxPage=totalPage;
						}else{
							maxPage=currentPage+4;
						}
						}else{
							minpage = 1;
							maxPage = 5;
						}
						}
						%>
					<%
				   		if(currentPage != 1){
				   	%>
				    <li><a href="#" onclick="homePage(<%=currentPage%>)">首页</a></li>
				    <li><a href="#" onclick="prePage(<%=currentPage%>)">上一页</a></li>
				    <%
				   		}
				  	%>
				  	<%
						   	for(int i = minpage;i<= maxPage;i++){
							   	if( i == currentPage){
							   	%>	
							   		<li class="active"><a href="javascript:void(0)"  onclick="choosePage('<%=i %>')"><%=i %></a></li>
							   <%}else{%>
							   		<li><a href="javascript:void(0)"  onclick="choosePage('<%=i %>')"><%=i %></a></li>
							   	<%
							 	 }
							   	%>
						   	<%
						 	} 
						   	%>
				   	<%
				   		if(currentPage < totalPage){
				   	%>
					    <li><a href="#"  onclick="nextPage(<%=currentPage%>)">下一页</a></li>
				  	<%
				   		}
				  	%>
				</ul>
	     </div>
	<div class="clear"></div>
	<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" style="height: 200px;"></iframe>
	</div>
	
   
</body>
<script type="text/javascript" src="<%=path %>/js/shop/common.js?v=16032101"></script>
<script type="text/javascript" src="<%=path %>/js/shop/jquery.SuperSlide.2.1.2.js"></script>
<script type="text/javascript">
    $(function(){
        jQuery(".fullSlide").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"leftLoop", vis:"auto", autoPlay:true, autoPage:true, trigger:"click" });
    })
    
    /*跳转页码*/
    function choosePage(pageNum){
    	window.location.href='<%=path%>/item/showItemsByPageNum/'+pageNum;
    }
    /*下一页*/
    function nextPage(page){
    	window.location.href='<%=path%>/item/showItemsByPageNum/'+(page+1);
    }
    /*上一页*/
    function perPage(page){
    	window.location.href='<%=path%>/item/showItemsByPageNum/'+(page-1);
    }
    function homePage(page){
    	if(page != 1){
    		page = 1;
    	}
    	window.location.href='<%=path%>/item/showItemsByPageNum/'+page;
    }
</script>
</html>