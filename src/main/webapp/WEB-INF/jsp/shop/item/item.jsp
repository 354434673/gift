<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="keywords" content="友宏科技,jstyle,智能手环,智能美容仪,leyoung,深圳市友宏科技有限公司" />
    <meta name="description" content="友宏科技是一家专注技术的高新科技公司，致力于“健康、美容、医疗”相关产品的研发、生产、销售，主要提供“智能健康计步系列产品”、“家用美容和按摩系列产品”、“便携式医疗雾化器和吸引器系列产品”以及针对这些产品按照顾客要求提供满足其需求的个性化解决方案。" />
    <!--[if IE 8]><script type="text/javascript" src="js/html5shiv.min.js"></script><![endif]-->
    <!--[if IE 8]><script type="text/javascript" src="js/css3-mediaqueries.js"></script><![endif]-->
	<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/shop/global.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/health.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/product.css?v=16032101"/>
	<link rel="stylesheet" href="<%=path %>/css/shop/classify.css">
	<link rel="stylesheet" href="<%=path %>/css/shop/style.css">
    <title>玖玖深红商品</title>
    <script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?df6c481ad0c635da7a0cc995f32e5bd3";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
	</script>
	<style type="text/css">
	body{ margin:0 auto;} 
	.allbtn{float:left;margin-top:95px;margin-left:90px}
	.product-nav{margin-top:100px;}
	</style>
</head>
<body>
		 <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe>
	 <div class="all">
        <div class="product">
            <div class="product-nav">
                当前位置 ： <a href="<%=path %>/shopPage/showItemsPage.html">首页</a> > <span>精美物品系列</span>
            </div>	
            <div class="show">
                <div class="show-left">
                    <img id="pro-img" src="${item.pic }" width="400px;" height="400px;">
                </div>
                <div class="show-right">
                    <div id="pro-name">${item.titile }</div>
                    <div id="pro-exp">${item.sellPoint }</div>
                   <!--  <div id="pro-type">JC-2901</div> -->
                   <br/>
                    <div class="pro-thumb">
                        <ul>
							<li><a href="javascript:void(0)" id="oneMinImg"><div class="unimgchked"><img id="pic" src="${item.pic }" width="90px" height="90px"></div></a></li>
                            <li><a href="javascript:void(0)" id="twoMinImg"><div  class="unimgchked"><img id="pic2" src="${item.pic2 }" width="90px" height="90px"></div></a></li>
                            <li><a href="javascript:void(0)" id="threeMinImg"><div  class="unimgchked"><img id="pic3" src="${item.pic3 }" width="90px" height="90px"></div></a></li>
							<li><a href="javascript:void(0)" id="fourMinImg"><div class="unimgchked"><img id="pic4" src="${item.pic4 }" width="90px" height="90px"></div></a></li>
                        </ul>
                    </div>
                  <a href="<%=path %>/picture/checkCustomer/${item.id }" id="buyUrl" class="buy"><div>匹配LOGO</div></a>
                     <!-- <a id="prev" onclick="prevClick()" href=""><img src="<%=path %>/img/shop/left.png"></a>
                    <a id="next" onclick="next()" href=""><img src="<%=path %>/img/shop/right.png"></a>-->
                </div>
            </div>
        </div>
        <div class="fun">
            <div class="fun-title"><div>功能介绍</div></div>
            <div class="fun-exp" id="funexp">
                <ul>
                	 <c:if test="${empty itemDescs }">
                		 <li>暂无商品描述信息</li>
                	</c:if>
                	<c:if test="${not empty itemDescs }">
                		<c:forEach items="${itemDescs }" var="desc">
		                    <li>${desc.content }</li>
                		</c:forEach>
                	</c:if>
                </ul>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <div class="border-t"></div>
	<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" height="200px"></iframe>
	<script type="text/javascript" src="<%=path %>js/shop/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>js/shop/common.js?v=16032101"></script>
	<script type="text/javascript" src="<%=path %>js/shop/beauty.js?v=16032101"></script>
	<script type="text/javascript">
		$("#pic").click(function(){
		  	var path = $("#pic")[0].src;
		  	$("#pro-img").attr('src',path); 
		});
		$("#pic2").click(function(){
		  	var path = $("#pic2")[0].src;
		  	$("#pro-img").attr('src',path); 
		});
		$("#pic3").click(function(){
		  	var path = $("#pic3")[0].src;
		  	$("#pro-img").attr('src',path); 
		});
		$("#pic4").click(function(){
		  	var path = $("#pic4")[0].src;
		  	$("#pro-img").attr('src',path); 
		});
	</script>
</body>
</html>