<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<div class="header">
	 <div class="container">
		 <div class="social">	
		    <form method="post">
		  	 <div class="input-group" style=" margin:0;border:0;padding:0;" >
		  	 <span class="input-group-addon"><img src="<woo:url value='/static/images/menu-icon.png'/>" alt="个人头像" style="width:15px; height:15px;" 
			 class="img-circle" /></span>
		  	 <input type="text" class="form-control" name="keyWord" id="keyWord" placeholder="搜索(全数字为搜索价格)..." style="background-color:#FFFFCC"  >
		  	 <span class="input-group-btn">
             <input type="submit"  class="btn btn-primary"   value="搜索" >
             </span>
		  	</div>
		  	</form>
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
		 </div>
		 </div>
	 </div>
</div>
<div id="about" class="about">
	 <div class="container">
		 <h3>采购信息</h3>
		 <c:forEach items="${grid.datas }" var="data" >
		 <div class="about-info">
			 <div class="col-md-6 about-left">
			     <h4>" 采购产品名称:${data.name }"</h4>
				 <h4>" 采购产品简介:${data.infomation }"</h4>
				 <h4 style="text-align:right;color:#EE6363;">￥:${data.price }元</h4>
				 <h4 style="text-align:right;color:#EE6363">公司名:${data.companyname }</h4>
			 </div>
			 <div class="col-md-6 about-right">
				 <img src="<woo:url value='/static/purchasepicture/${data.picone }'/>" class="img-responsive" alt=""/>
			 </div>
			 <div class="clearfix"></div>
			 <div class="top-grid-section" style="text-align:center;">
					 <div class="col-md-3 top-grids">
						 <a href="/purchase/${data.number}"><i class="glyphicon glyphicon-gift"></i><h4>产品详细信息</h4></a>
					 </div>		
					 <div class="col-md-3 top-grids">
						 <a href="/CoLtd/${data.companyid}"><i class="glyphicon glyphicon-gift"></i><h4>公司信息</h4></a>
					 </div>	
					 <div class="clearfix"></div>
			  </div>
	     </div>
	     </c:forEach>
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