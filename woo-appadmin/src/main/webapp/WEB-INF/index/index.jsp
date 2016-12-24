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
					<h1><a href="index.html">logo</a></h1>
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
			     <img src="<woo:url value='/static/images/bulid.jpg'/>" alt=""/>
		 </div>
		 </div>
	 </div>
</div>

<div id="testi" class="testi">
		<div class="container">
			<h3>introduction</h3>					
			<script src="<woo:url value='/static/js/responsiveslides.min.js'/>"></script>
			<script>
				// You can also use "$(window).load(function() {"
					$(function () {
					// Slideshow 3
						$("#slider3").responsiveSlides({
						auto: true,
						pager:true,
						nav:false,
						speed: 500,
						namespace: "callbacks",
						before: function () {
						$('.events').append("<li>before event fired.</li>");
						},
						after: function () {
							$('.events').append("<li>after event fired.</li>");
						}
					});	
				});
			</script>
			<div  id="top" class="callbacks_container">
				<ul class="rslides" id="slider3">
					<li>						 
						<p>" 壹平台致力于服务广大商家,使广大商家更好地推销自己的商品."</p>
						<h4>平台简介 </h4>
					</li>
					<li>					
						<p>" 本公司坐落与广东第三大城市佛山，主营服务，宗旨是服务于广大的商家."</p>
						<h4>公司情况 </h4>
					</li>
				</ul>
			</div>
		</div>
</div>

<div id="blog" class="blog">
	 <div class="container">
		 <h3><a href="<c:url value='/commodity'/>" style="color:#8B658B">商品信息</a></h3>
		 <c:forEach items="${productgird.datas }" var="data" >
		 <div class="blog-grids">
			 <div class="col-md-6 blog1">
				 <h4>${data.name }</h4>
				 <p>${data.infomation }</p>
				 <span><a href="/commodity/${data.number}">查看信息</a> | <a href="/CoLtd/${data.companyid}" style="color:#FF6A6A">公司信息</a> </span>
			 </div>
			 <div class="col-md-6 blog-pic">
				  <img src="<woo:url value='/static/commoditypicture/${data.picone }'/>" class="img-responsive" alt=""/>
			 </div>
			 <div class="clearfix"></div>
		 </div>
         </c:forEach>
         <c:forEach items="${productgirdtwo.datas }" var="data" >
		 <div class="blog-grids">
			 <div class="col-md-6 blog-pic">
				  <img src="<woo:url value='/static/commoditypicture/${data.picone }'/>" class="img-responsive" alt=""/>
			 </div>
			 <div class="class=col-md-6 blog1 grid2">
				 <h4>${data.name }</h4>
				 <p>${data.infomation }</p>
				 <span><a href="/commodity/${data.number}">查看信息</a> | <a href="/CoLtd/${data.companyid}" style="color:#FF6A6A">公司信息</a> </span>
			 </div>
			 <div class="clearfix"></div>
		 </div>
         </c:forEach>
         <c:forEach items="${productgirdthree.datas }" var="data" >
		 <div class="blog-grids">
			 <div class="col-md-6 blog1">
				 <h4>${data.name }</h4>
				 <p>${data.infomation }</p>
				 <span><a href="/commodity/${data.number}">查看信息</a> | <a href="/CoLtd/${data.companyid}" style="color:#FF6A6A">公司信息</a> </span>
			 </div>
			 <div class="col-md-6 blog-pic">
				  <img src="<woo:url value='/static/commoditypicture/${data.picone }'/>" class="img-responsive" alt=""/>
			 </div>
			 <div class="clearfix"></div>
		 </div>
         </c:forEach>
	 </div>
</div>

<div id="blog" class="blog">
	 <div class="container">
		 <h3><a href="<c:url value='/purchase'/>" style="color:#EE7942">采购信息</a></h3>
		 <c:forEach items="${purchasegird.datas }" var="data" >
		 <div class="blog-grids">
			 <div class="col-md-6 blog-pic">
				  <img src="<woo:url value='/static/purchasepicture/${data.picone }'/>" class="img-responsive" alt=""/>
			 </div>
			 <div class="class=col-md-6 blog1 grid2">
				 <h4>${data.name }</h4>
				 <p>${data.infomation }</p>
				 <span><a href="/purchase/${data.number}">查看信息</a> | <a href="/CoLtd/${data.companyid}" style="color:#FF6A6A">公司信息</a> </span>
			 </div>
			 <div class="clearfix"></div>
		 </div>
         </c:forEach>
         <c:forEach items="${purchasegirdtwo.datas }" var="data" >
		 <div class="blog-grids">
			 <div class="col-md-6 blog1">
				 <h4>${data.name }</h4>
				 <p>${data.infomation }</p>
				 <span><a href="/purchase/${data.number}">查看信息</a> | <a href="/CoLtd/${data.companyid}" style="color:#FF6A6A">公司信息</a> </span>
			 </div>
			 <div class="col-md-6 blog-pic">
				  <img src="<woo:url value='/static/purchasepicture/${data.picone }'/>" class="img-responsive" alt=""/>
			 </div>
			 <div class="clearfix"></div>
		 </div>
         </c:forEach>
         <c:forEach items="${purchasegirdthree.datas }" var="data" >
		 <div class="blog-grids">
			 <div class="col-md-6 blog-pic">
				 <img src="<woo:url value='/static/purchasepicture/${data.picone }'/>" class="img-responsive" alt=""/>
			 </div>
			 <div class="class=col-md-6 blog1 grid2">
				 <h4>${data.name }</h4>
				 <p>${data.infomation }</p>
				 <span><a href="/purchase/${data.number}">查看信息</a> | <a href="/CoLtd/${data.companyid}" style="color:#FF6A6A">公司信息</a> </span>
			 </div>
			 <div class="clearfix"></div>
		 </div>
         </c:forEach>
	 </div>
</div>
