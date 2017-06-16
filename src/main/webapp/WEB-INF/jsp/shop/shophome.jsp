<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style type="text/css">
        .fullSlide{ position:relative; background:#000; margin:0 auto; }
        .fullSlide .bd{ position:relative; z-index:0; }
        .fullSlide .bd li img{width:100%; vertical-align:top;  }
        .fullSlide .hd{position:relative; z-index:1; margin-top:-30px; height:30px; line-height:30px;  text-align:center;background:#000; filter:alpha(opacity=60);opacity:0.6;}
        .fullSlide .hd ul{ text-align:center;  padding-top:5px;  }
        .fullSlide .hd ul li{ cursor:pointer; display:inline-block; *display:inline; zoom:1; width:8px; height:8px; margin:5px; background:url('<%=path%>/img/shop/n1/left_arrow.png') -18px 0; overflow:hidden;font-size:0;}
        .fullSlide .hd ul .on{ background-position:0 0; }
        .fullSlide .next{ z-index:1; display:block; width:100px; height:100px; position:relative; margin:-20% 0 0 3%; float:left;  background:url('<%=path%>/img/shop/n1/right_arrow.png') 0 0 no-repeat; filter:alpha(opacity=40);opacity:0.4  }
        .fullSlide .prev{ z-index:1; display:block; width:100px; height:100px; position:relative; margin:-20% 0 0 3%; float:left;  background:url('<%=path%>/img/shop/n1/left_arrow.png') 0 0 no-repeat; filter:alpha(opacity=40);opacity:0.4  }
        .fullSlide .next{  background-position:right 0; float:right; margin-right:3%  }
        .fullSlide .prev:hover,.fullSlide .next:hover{filter:alpha(opacity=80);opacity:0.8 }
		.introduce ul li {margin: -5px}
		.introduce ul li img{width:100%}
    </style>
</head>
<body>
      <iframe id="topnav" src="<%=path %>/shopnav.jsp" scrolling="no" frameborder="0" width="100%" style="height: 100px;"></iframe>
            <div class="fullSlide" style="padding-top: 5%;">
                <div class="bd">
                    <ul>
	                    <c:forEach items="${bannerContents }" var="banner">
	                    	<li><a href="javascript:void(0)"><img src="<%=imgPath %>${banner.pic }"/></a></li>
	                    </c:forEach>
                       <%--  <li><a href="buyhealth.html?pid=J080" target="_blank"><img src="<%=path %>/img/shop/n1/1.jpg"/></a></li>
                        <li><a href="buyhealth.html?pid=J064" target="_blank"><img src="<%=path %>/img/shop/n1/2.jpg"/></a></li>
						<li><a href="buyhealth.html?pid=J055" target="_blank"><img src="<%=path %>/img/shop/n1/banner3.jpg"/></a></li>
                        <li><a href="buyhealth.html?pid=B018" target="_blank"><img src="<%=path %>/img/shop/n1/banner4.jpg"/></a></li>
                        <li><a href="buyhealth.html?pid=B005" target="_blank"><img src="<%=path %>/img/shop/n1/banner5.jpg"/></a></li> --%>
                    </ul>
                </div>
                <!--<div class="hd"><ul></ul></div>-->
                <a class="prev" href="javascript:void(0)"></a>
                <a class="next" href="javascript:void(0)"></a>
            </div>
			<div style="margin: 40px"></div>
             <div class="sh" onselectstart="return false;">
                <div class="sh-title">介绍</div>
                </div>
            <div class="border-t"></div>
            <div class="clear"></div>
            <div id="introduce"class="introduce">
            	<div style="margin: 20px"></div>
            	<ul>
            		<c:forEach items="${homePageContents }" var="homePage">
            			<li><a href="${homePage.url }"><img src="<%=imgPath %>${homePage.pic }" /></a></li>
            		</c:forEach>

					<%-- <li id="a" ><a href="<%=path%>/shopPage/companyInfo.html"><img src="<%=path %>/img/shop/introduce/20170401152331.jpg" /></a></li>

					<li id="b" ><img src="<%=path %>/img/shop/introduce/20170401152339.jpg" /></li>
					<li id="c"><img src="<%=path %>/img/shop/introduce/20170401152347.jpg"/></li>
					<li id="d"><img src="<%=path %>/img/shop/introduce/20170401152350.jpg" /></li>
					<li id="e"><img src="<%=path %>/img/shop/introduce/20170401152353.jpg" /></li>  --%>
				</ul>
            </div>
		
            <div class="sh" onselectstart="return false;">
			
                 <!--<div class="sh-title">精品礼物系列</div>
				
                <div class="sh-content" onselectstart="return false;">
                    <div class="series " style="float: left">
                        <div style="height: 6px;background-color: #fbb93f"></div>
                        <div class="shbg1 runner1 series-left" onclick="window.open('buyhealth.html?pid=J064')">
                            <div class="series-title ">RunnerⅠ 智能运动手环</div>
                            <div class="series-info">无感佩戴 开启健康新生活</div>
                            <a class="series-href">点击了解</a>
                        </div>

                    </div>
                    <div class="series">
                        <div style="height: 6px;background-color: #c2dfa5"></div>
                        <div class="shbg1 runner2 series-left" onclick="window.open('buyhealth.html?pid=J055')">
                            <div class="series-title">RunnerⅡ 智能运动手环</div>
                            <div class="series-info">撞色混搭 个性设计</div>
                            <a class="series-href">点击了解</a>
                        </div>
                    </div>
                    <div class="series series-i">
                        <div style="height: 6px;background-color: #8ec8f6"></div>
                        <div class="shbg1 care series-left" onclick="window.open('buyhealth.html?pid=B005')">
                            <div class="series-title">Care 心率心电图健康手环</div>
                            <div class="series-info">随时关爱心脏</div>
                            <a class="series-href">点击了解</a>
                        </div>
                    </div>
                    <div class="series">
                        <div style="height: 6px;background-color: #e45a57"></div>
                        <div class="shbg1 heart series-left" onclick="window.open('buyhealth.html?pid=B018')">
                            <div class="series-title">Heart 运动心率手环</div>
                            <div class="series-info">开启有氧运动生活</div>
                            <a class="series-href">点击了解</a>
                        </div>
                    </div>
                    <div class="series " style="float: right">
                        <div style="height: 6px;background-color: #2bc7b2"></div>
                        <div class="shbg1 gogo series-left" onclick="window.open('buyhealth.html?pid=J080')">
                            <div class="series-title gogo-tt">GoGo 智能运动手环</div>
                            <div class="series-info">给自己一个运动的理由</div>
                            <a class="series-href">点击了解</a>
                        </div>
                    </div>
                </div>
            </div>
			-->
        </div>
        <div class="clear"></div>
        <div class="border-t"></div>
<iframe id="showImg" src="<%=path %>/footer.jsp" scrolling="no" frameborder="0" width="100%" height="200px"></iframe>
    </div>
</body>
<script type="text/javascript" src="<%=path %>/js/shop/common.js?v=16032101"></script>
<script type="text/javascript" src="<%=path %>/js/shop/jquery.SuperSlide.2.1.2.js"></script>
<script type="text/javascript" src="<%=path %>/js/shop/scrolltopcontrol.js"></script>
<script type="text/javascript">
    $(function(){
        jQuery(".fullSlide").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"leftLoop", vis:"auto", autoPlay:true, autoPage:true, trigger:"click" });
    })
</script>
</html>