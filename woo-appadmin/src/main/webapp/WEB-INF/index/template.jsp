<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>
<!DOCTYPE HTML>
<html>
<head>
<title>欢迎使用壹平台</title>
<link href="<woo:url value='/static/css/bootstrap.css'/>" rel="stylesheet" type="text/css" media="all">
<link href="<woo:url value='/static/css/style.css'/>" rel="stylesheet" type="text/css" media="all" />
<link href="<woo:url value='/static/css/component.css'/>" rel="stylesheet" type="text/css"  />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<script type="application/x-javascript"> 
addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="<woo:url value='/static/js/modernizr.custom.js'/>"></script>
<script src="<woo:url value='/static/js/jquery-1.11.1.min.js'/>"></script>
<link rel="stylesheet" href="<woo:url value='/static/css/lightbox.css'/>">

</head>
<body class="cbp-spmenu-push">	
<tiles:insertAttribute name="content"/>
<div class="footer">
	 <div class="container">
		 <h3><a href="#">壹加壹有限公司</a></h3>		 
		 <p>
		 <a href="http://www.miitbeian.gov.cn/" >粤ICP备16098357号-1</a>  
		 </p>
	     <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=44060402000178" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;">
		 <img src="" style="float:left;"/>
		 <p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">粤公网安备 44060402000178号</p>
		 </a>
		 <p>本网站不支持IE浏览器！！！强烈建议商家使用Google Chrome浏览器!支持最新版的360、Safari，搜狗、QQ浏览器。</p>
	 </div>
</div>


<script src="<woo:url value='/static/js/classie.js'/>"></script>
<script>
	var menuRight = document.getElementById( 'cbp-spmenu-s2' ),
    showRightPush = document.getElementById( 'showRightPush' ),
	body = document.body;

	showRightPush.onclick = function() {
	classie.toggle( this, 'active' );
	//classie.toggle( body, 'cbp-spmenu-push-toleft' );
	classie.toggle( menuRight, 'cbp-spmenu-open' );
	disableOther( 'showRightPush' );
	};

	function disableOther( button ) {
    if( button !== 'showRightPush' ) {
	classie.toggle( showRightPush, 'disabled' );
	 }
	}
</script>
<script type="text/javascript" src="<woo:url value='/static/js/move-top.js'/>"></script>
<script type="text/javascript" src="<woo:url value='/static/js/easing.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
$().UItoTop({ easingType: 'easeOutQuart' });
});
</script>
<a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</body>
</html>