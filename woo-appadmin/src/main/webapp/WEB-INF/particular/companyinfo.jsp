<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

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
		     <div class="logo">
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
					<button id="showRightPush" ><img src="<woo:url value='/static/images/menu-icon.png'/>" /></button>
		     </div>
			 <div class="banner-info">
			     </br>
			     </br>
			     </br>
			     <h2><span>壹平台</span></h2>
			     <p>致力于服务广大商家</p>
			     <label></label>
		 </div>
		 </div>
	 </div>
</div>

<div id="about" class="about">
	 <div class="container">
		 <h3>公司简介</h3>
		 </br>
		 <h4 style="color:#43CD80">公司名:${name }.</h4>
		 <p>公司简介:${infomation }.</p>
		 <div class="clearfix"></div>
	 </div>
</div>
<!--gallery-->
<div id="gallery" class="gallery">
		<div class="container">
			<h3>公司图片</h3>
			<div class="gallery-bottom">
				<div class="gallery-1">
				    <c:if test="${picone != 'null' }">
					<div class="col-md-4 gallery-grid">
						<a class="example-image-link" href="<woo:url value='/static/companyhead/${picone }'/>" data-lightbox="example-set" data-title=""><img class="example-image" src="<woo:url value='/static/companyhead/${picone }'/>" alt="" style="max-width:200px;max-height:200px;"/></a>
					</div>
					</c:if>
					<c:if test="${pictwo != 'null' }">
					<div class="col-md-4 gallery-grid">
						<a class="example-image-link" href="<woo:url value='/static/companyhead/${pictwo }'/>" data-lightbox="example-set" data-title=""><img class="example-image" src="<woo:url value='/static/companyhead/${pictwo }'/>" alt="" style="max-width:200px;max-height:200px;"/></a>
					</div>
					</c:if>
					<c:if test="${picthree != 'null' }">
					<div class="col-md-4 gallery-grid">
						<a class="example-image-link" href="<woo:url value='/static/companyhead/${picthree }'/>" data-lightbox="example-set" data-title=""><img class="example-image" src="<woo:url value='/static/companyhead/${picthree }'/>" alt="" style="max-width:200px;max-height:200px;"/></a>
					</div>
					</c:if>			
					<c:if test="${picthree == 'null' }">
					   <c:if test="${pictwo == 'null' }">
					      <c:if test="${picone == 'null' }">
					      <p>该公司暂时没有图片</p>
					      </c:if>
					   </c:if>
					</c:if>			
					<div class="clearfix"></div>
				</div>				
		 </div>
	 </div>
</div>	
<script src="<woo:url value='/static/js/lightbox-plus-jquery.min.js'/>"></script>

