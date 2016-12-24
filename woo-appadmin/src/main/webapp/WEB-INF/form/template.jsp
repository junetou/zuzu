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
<script type="application/x-javascript"> 
addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script type="text/javascript" src="<woo:url value='/static/js/modernizr.custom.js'/> "></script>
<style>
.radio-btn {
width: 20px;
height: 20px;
display: inline-block;
float: left;
margin: 3px 7px 0 0;
cursor: pointer;
position: relative;
-webkit-border-radius: 100%;
-moz-border-radius: 100%;
border-radius: 100%;
border: 1px solid #ccc;
box-shadow: 0 0 1px #ccc;
background: rgb(255, 255, 255);
background: -moz-linear-gradient(top, rgba(255, 255, 255, 1) 0%, rgba(246, 246, 246, 1) 47%, rgba(237, 237, 237, 1) 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(255, 255, 255, 1)), color-stop(47%, rgba(246, 246, 246, 1)), color-stop(100%, rgba(237, 237, 237, 1)));
background: -webkit-linear-gradient(top, rgba(255, 255, 255, 1) 0%, rgba(246, 246, 246, 1) 47%, rgba(237, 237, 237, 1) 100%);
background: -o-linear-gradient(top, rgba(255, 255, 255, 1) 0%, rgba(246, 246, 246, 1) 47%, rgba(237, 237, 237, 1) 100%);
background: -ms-linear-gradient(top, rgba(255, 255, 255, 1) 0%, rgba(246, 246, 246, 1) 47%, rgba(237, 237, 237, 1) 100%);
background: linear-gradient(to bottom, rgba(255, 255, 255, 1) 0%, rgba(246, 246, 246, 1) 47%, rgba(237, 237, 237, 1) 100%);
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#ededed', GradientType=0);
}
.radio-btn i {
border: 1px solid #E1E2E4;
width: 10px;
height: 10px;
position: absolute;
left: 4px;
top: 4px;
-webkit-border-radius: 100%;
-moz-border-radius: 100%;
border-radius: 100%;
}
.checkedRadio i {
background-color: #898A8C;
}
.checkedRadio {
-moz-box-shadow: inset 0 0 5px 1px #ccc;
-webkit-box-shadow: inset 0 0 5px 1px #ccc;
box-shadow: inset 0 0 5px 1px #ccc;
}
.imgdiv1{
height                   : 41px;
background               : #f5696c;
width                    : 144px;
}
.imgdiv2{
text-align               : center;
padding-top              : 12px;
font-size                : 15px;
font-weight              : 800
}
#inputstyle{
width                    : 144px;
height                   : 41px;
cursor                   : pointer;
font-size                : 30px;
outline                  : medium none;
position                 : absolute;
filter:alpha(opacity=0);
-moz-opacity:0;
opacity                  : 0; 
left                     : 0px;
top                      : 0px;
}
.file {
position: relative;
display: inline-block;
background: #D0EEFF;
border: 1px solid #99D3F5;
border-radius: 4px;
padding: 4px 12px;
overflow: hidden;
color: #1E88C7;
text-decoration: none;
text-indent: 0;
line-height: 20px;
}
.file input {
position: absolute;
font-size: 100px;
right: 0;
top: 0;
opacity: 0;
}
.file:hover {
background: #AADFFD;
border-color: #78C3F3;
color: #004974;
text-decoration: none;
}
.img-rounded {
  border-radius: 10px;
  max-height: 300px;
  max-width: 200px;
}
</style>
<script src="<woo:url value='/static/js/modernizr.custom.js'/>"></script>
<link rel="stylesheet" href="<woo:url value='/static/css/lightbox.css'/>">
</head>
<body class="cbp-spmenu-push">
<div class="header">
	 <div class="container">
		 <h3>壹平台</h3>
	 </div>
</div>
<div id="home">
	 <div id="to-top" class="container">
		 <div class="top-header">
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
		 </div>
	 </div>
</div>
<tiles:insertAttribute name="content" />
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