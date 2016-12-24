<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<!DOCTYPE HTML>
<html>
<head>
<title>欢迎使用壹平台</title>
<link href="<woo:url value='/static/css/bootstrap.css'/>" rel="stylesheet" type="text/css" media="all">
<link href="<woo:url value='/static/css/style.css'/>" rel="stylesheet" type="text/css" media="all" />
<link href="<woo:url value='/static/css/component.css'/>" rel="stylesheet" type="text/css"  />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 
addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="<woo:url value='/static/js/modernizr.custom.js'/>"></script>
<script src="<woo:url value='/static/js/jquery-1.11.1.min.js'/>"></script>
<link rel="stylesheet" href="<woo:url value='/static/css/lightbox.css'/>">

</head>
<body class="cbp-spmenu-push">	
<div id="home">
	 <div id="to-top" class="container">
		 <div class="top-header">
		     <div class="logo">
					<h1><a href="javascript:go(-1)">返回</a></h1>
			 </div>
			 <div class="top-nav">
				 <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s2">
					 <h3>菜单</h3>
						<a class="scroll" href="<c:url value='/index'/>">主页</a>
						<a class="scroll" href="<c:url value='/commodity'/>">产品信息</a>
						<a class="scroll" href="<c:url value='/purchase'/>">采购信息</a>
						<woo:permission operationType="VIEW" roleType="ROLE_COMPANY">
						<a class="scroll" href="<c:url value='/publish'/>">发布信息</a>
						</woo:permission>
						<a class="scroll" href="<c:url value='/own'/>">我的</a>
				 </nav>		
			 </div>
			 <div class="main buttonset" style="text-align:right;">	
					<button id="showRightPush" ><img src="<woo:url value='/static/images/menu-icon.png'/>" /></button>
		     </div>	 
			 <div class="banner-info">
			     </br>
			     </br>
			     </br>
			     </br>
			     <h2><span>对不起，找不到相应的页面</span></h2>
			     <p>你可以联系网站程序员来报错或提议新页面，我们感谢你的使用。</p>
			     <label></label>
			     <img src="<woo:url value='/static/images/404.jpg'/>" alt=""/>
		 </div>
		 </div>
	 </div>
</div>
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
	 </div>
</div>

<script src="<woo:url value='/static/js/classie.js'/>"></script>
<script>
	var menuRight = document.getElementById( 'cbp-spmenu-s2' ),
    showRightPush = document.getElementById( 'showRightPush' ),
	body = document.body;

	showRightPush.onclick = function() {
	classie.toggle( this, 'active' );
//	classie.toggle( body, 'cbp-spmenu-push-toleft' );
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