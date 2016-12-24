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
					<h5><button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#mymodal">投诉</button></h5>
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
			 <div class="main buttonset"  style="text-align:right;">	
						<button id="showRightPush"><img src="<woo:url value='/static/images/menu-icon.png'/>" alt=""/></button>
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
		 <li><a href="index.html">产品信息</a></li>
	 </ol>
	 <div class="col-md-8 contact-top-right">
					<div>
						<label>
						    <p>联系人</p>
							<input type="text" value="${contact }" class="form-control" style="min-width:200px;" readonly="true" tabindex="1">
						</label>
					</div>
					<div>
						<label>
						    <p>联系人电话</p>
							<input type="text" value="${contactphone }" class="form-control" style="min-width:200px;" readonly="true" tabindex="2">
						</label>
					</div>
					<div>
						<label>
						    <p>公司email</p>
							<input type="text" value="${email }" class="form-control" style="min-width:200px;" readonly="true" tabindex="3">
						</label>
					</div>
	 </div>
	 <div class="col-md-4 contact-top-left">
					<div class="contact-top-one">
						<h2>如有问题联系我们</h2>
						</br>
					</div>
					<div class="contact-top-one">
						<h4>地址</h4>
						<h6>
						<span>广东省佛山市禅城区</span>
						张槎xx工业区xx号.
						</h6>
					</div>
					<div class="contact-top-one">
						<h4>电话:</h4>
						<p>固话: +86 822xxxxx 
						<span>手机: +86 136xxxxxxxx</span>
						<span>紧急联系电话:+86 15626287636</span>
						<soan>投诉电话:+86 822xxxxx</soan>
						</p>
						
					</div>
					<div class="contact-top-one">
						<h4>E-mail:</h4>
						<p>756332784@163.com</p>
					</div>
	      </div>
	      <div class="clearfix"> </div>
</div>


<div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	<form id="reset-form" method="post" action="/commodity/addFed">
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel">投诉</h4>
		      	</div>
		      	<div class="modal-body">
		      		<div class="form-group">
			            <label for="recipient-name" class="control-label">投诉内容<sup>*</sup></label>
			            <input type="text" name="userId" id="userId" value="${number }" style="display:none"  />
			            <input type="text" class="form-control" id="infomation" name="infomation" requried/>
			            <p class="help-block">投诉的内容不能为空</p> 
		          	</div>
		      	</div>
		      	<div class="modal-footer">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        	<button type="submit" class="btn btn-primary">提交</button>
		      	</div>
	      	</form>
	    </div>
  	</div>
</div>