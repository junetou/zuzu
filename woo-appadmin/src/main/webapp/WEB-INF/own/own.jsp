<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<link href="<woo:url value='/static/css/lightGallery.css'/>" rel="stylesheet" type="text/css"  />

<div class="header">
	 <div class="container">
		 <div class="social">	
		 </div>
		 <div class="details">
			<ul>
				<li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>756332784@qq.com</li>
				<li><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>13826589635</li>
			 </ul>
		 </div>
	 </div>
</div>
<div id="home">
	 <div id="to-top" class="container">
		 <div class="top-header">
		     <div class="logo" >
			    <h1><a href="<c:url value='/index'/>">logo</a></h1>
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
						<button id="showRightPush"><img src="<woo:url value='/static/images/menu-icon.png'/>" alt=""/></button>
		     </div>	
			 <div class="banner-info">
			     </br>
			     </br>
			     </br>
			     <h2>
				 <ul id="auto-loop" class="gallery">
				 <li data-src="<woo:url value='/static/userpicture/${pic }'/>" style="list-style-type:none;"> 
        	     <a href="#">
                 <img src="<woo:url value='/static/userpicture/${pic }'/>" style="max-width:200px;max-height:200px;" />
                 </a> 
                 </li>
				 </ul>
				 </h2>
			     <p>用户名:<span style="color:#8B658B">${name }<span></p>
			     <label></label>
		 </div>
		 </div>
	 </div>
</div>
<script src="<woo:url value='/static/js/lightGallery.js'/>"></script>
<div class="login_sec" style="text-align:center;">
	 <div class="container">
	   <c:forEach var="menu" items="${menus}">
		 <ol class="breadcrumb">
		  <li><a href="${menu.url }">${menu.name }</a></li>
		 </ol>
		</c:forEach>
		<div class="clearfix"></div>
	 </div>
</div>