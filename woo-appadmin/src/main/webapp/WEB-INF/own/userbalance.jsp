<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<style>
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
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>


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
					<h5><button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#mymodal">充值</button></h5>
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

<div class="container" style="text-align:center;">
	 <ol class="breadcrumb">
		 <li><a href="index.html">我的</a></li>
		 <li class="active">个人余额</li>
	 </ol>
	 <woo:permission operationType="VIEW" roleType="ROLE_USER">
	 <div class="registration">
		 <div class="registration_left">
			 <div class="registration_form">
				<form action="/own/edit/update" enctype="multipart/form-data"  method="post" class="form-horizontal" onsubmit="return reg()">
					<div>
						<label>
						    <p>用户名</p>
							<input placeholder="用户名" type="text" id="name" name="name" value="${name }" class="form-control" readOnly="true">
						</label>
					</div>
					<div>
						<label>
						    <p>账户余额</p>
							<input placeholder="账户余额" type="text" id="price" name="price" value="${cash }" class="form-control" readOnly="true" >
						</label>
					</div>
				</form>
			 </div>
		 </div>
	 </div>	 
	 </woo:permission>
</div>


<script>
</script>