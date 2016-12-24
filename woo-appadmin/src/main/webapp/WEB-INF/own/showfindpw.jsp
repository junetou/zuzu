<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<div class="header">
	 <div class="container">
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
		 <h3>确认修改账户信息</h3>
		 <woo:permission operationType="VIEW" roleType="ROLE_ADMIN">
		 <c:forEach items="${grid.datas }" var="data" >
		 <div class="about-info">
			 <div class="col-md-6 about-left">
			     <h4>" 产品名:${data.name }"</h4>
				 <h4>" 产品联系人:${data.contact }"</h4>
				 <h4>" 产品联系人电话:${data.contactphone }"</h4>
			 </div>
			 <div class="clearfix"></div>
			 <div class="top-grid-section" style="text-align:center;">
					 <div class="col-md-3 top-grids">
						 <a href="/admin/findpasswd/sendEmail/${data.id}"><h4>修改密码(谨慎！)</h4></a>
					 </div>		
					 <div class="col-md-3 top-grids">
						 <a href="/admin/findpasswd/ignore/${data.id}"><h4>忽略</h4></a>
					 </div>	
					 <div class="clearfix"></div>
			  </div>
	     </div>
	     </c:forEach>
	     </woo:permission>
	     <div id="pgm" style="text-align:center;">
	     <woo:pager pgm="${grid.pgm }"/> 
	     </div>
	 </div>
</div>

<script type="text/javascript">
var pgm=document.getElementById("pgm");
var lis=pgm.getElementsByTagName("li");
var width=document.documentElement.clientWidth;
if(width<420){
for(var i=1;i<lis.length-2;i++){
  var li=lis[i];
  li.style.display="none";
}
if(width<320){
var last=lis.length-1;
var lastli=lis[last];
lastli.style.display="none";
}
}
</script>

