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
    <title>JSTYLE-极简智品 精致生活</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="keywords" content="友宏科技,jstyle,智能手环,智能美容仪,leyoung,深圳市友宏科技有限公司" />
    <meta name="description" content="友宏科技是一家专注技术的高新科技公司，致力于“健康、美容、医疗”相关产品的研发、生产、销售，主要提供“智能健康计步系列产品”、“家用美容和按摩系列产品”、“便携式医疗雾化器和吸引器系列产品”以及针对这些产品按照顾客要求提供满足其需求的个性化解决方案。" />
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/img/shop/title.ico" />
    <link rel="icon" sizes="16x16" href="<%=path %>/img/shop/title.ico" />
    <!--[if IE 8]><script type="text/javascript" src="js/html5shiv.min.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=path %>/css/shop/global.css?v=16032101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/register.css?v=16092101"/>
    <link rel="stylesheet" href="<%=path %>/css/shop/nav.css?v=16032101"/>
</head>
<body>
    <div class="footer">
        <div class="footer-nav">
            <ul>
               <!--  <li style="position: absolute;left: 0px;text-align: left;word-spacing:8px; letter-spacing: 1px;">
                    <div class="footer-title">购买渠道</div>
                    <div class="footer-a"><a href="https://j-stylegrhl.tmall.com" target="_blank">天猫旗舰店</a></div>
                    <div class="footer-a"><a href="http://search.jd.com/Search?keyword=J-STYLE&enc=utf-8&wq=J-STYLE&pvid=qgco9loi.k3sz96" target="_blank">京东商城</a></div>
                </li> -->
                <li style="position: absolute;left: 0px;text-align: left;word-spacing:8px; letter-spacing: 1px;">
                    <div class="footer-title">服务支持</div>
                    <div class="footer-a"><a href="sale.html" target="_blank" >售后政策</a></div>
                    <div class="footer-a"><a href="help.html" target="_blank" >帮助中心</a></div>
                </li>
                <li style="position: absolute;left: 500px;text-align: left;word-spacing:8px; letter-spacing: 1px;">
                    <div class="footer-title">法律与其他</div>
                    <div class="footer-a"><a href="agreement.html" target="_blank" >使用协议</a></div>
                </li>
                <!--<li style="position: absolute;left: 756px;word-spacing:8px; letter-spacing: 1px;">
                    <div class="footer-title">关注我们</div>
                    <div><img src="img/n1/j-style-qrcode.jpg" width="110" height="110"></div>
                </li>
				-->
                <li style="position: absolute;left: 1000px;text-align: right;word-spacing:8px; letter-spacing: 1px;">
                    <div class="footer-title">联系我们</div>
                    <div class="tel footer-a">010&nbsp;58720756</div>
                    <div class="teltime footer-a">周一至周五 9:00-18:30</div>
                   <%--  <div class="fonter-wangwang">
                        <span>嘻嘻</span><a target="_blank" href="http://www.taobao.com/webww/ww.php?ver=3&touid=jstyle%E4%B8%AA%E4%BA%BA%E6%8A%A4%E7%90%86%E6%97%97%E8%88%B0%E5%BA%97%3A%E5%98%BB%E5%98%BB&siteid=cntaobao&status=2&charset=utf-8"><img align="absmiddle" src="<%=path %>/img/shop/n1/wangwang.png?v=2&uid=jstyle%E4%B8%AA%E4%BA%BA%E6%8A%A4%E7%90%86%E6%97%97%E8%88%B0%E5%BA%97%3A%E5%98%BB%E5%98%BB&site=cntaobao&s=2&charset=utf-8" alt="亲，联系我就点我"/></a>
                        <span>斯斯</span><a target="_blank" href="http://www.taobao.com/webww/ww.php?ver=3&touid=jstyle%E4%B8%AA%E4%BA%BA%E6%8A%A4%E7%90%86%E6%97%97%E8%88%B0%E5%BA%97%3A%E6%96%AF%E6%96%AF&siteid=cntaobao&status=2&charset=utf-8"><img  align="absmiddle" src="<%=path %>/img/shop/n1/wangwang.png?v=2&uid=jstyle%E4%B8%AA%E4%BA%BA%E6%8A%A4%E7%90%86%E6%97%97%E8%88%B0%E5%BA%97%3A%E6%96%AF%E6%96%AF&site=cntaobao&s=2&charset=utf-8" alt="亲，联系我就点我"/></a>
                    </div> --%>
                </li>
            </ul>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=path %>/js/shop/jquery-1.8.3.min.js"></script>
<!--<script src="//captcha.luosimao.com/static/js/api.js"></script>-->
<script type="text/javascript" src="<%=path %>/js/shop/jquery.md5.js"></script>
<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/jquery.validate.min.js" ></script>
<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/additional-methods.js?v=16032102" ></script>
<script type="text/javascript" charset="UTF-8"  src="<%=path %>/js/shop/messages_zh.min.js" ></script>
<script src="<%=path %>/js/shop/common.js"></script>
<script src="<%=path %>/js/shop/jquery.placeholder.min.js"></script>
</script>
</html>