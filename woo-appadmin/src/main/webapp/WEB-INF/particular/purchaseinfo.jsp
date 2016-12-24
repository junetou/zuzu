<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div class="header">
	 <div class="container">
		 <div class="social">	
		    <form action="/purchase" method="post">
		  	 <div class="input-group" style=" margin:0;border:0;padding:0;" >
		  	 <span class="input-group-addon"><img src="<woo:url value='/static/images/menu-icon.png'/>" alt="个人头像" style="width:15px; height:15px;" 
			 class="img-circle" /></span>
		  	 <input type="text" class="form-control" name="keyWord" id="keyWord" placeholder="搜索..." style="background-color:#FFFFCC"  >
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
					<h1 style="color:#9F79EE;">采购信息</h1>
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
<div class="about">
	 <div class="container">				
		 <div class="product-price1">
			 <div class="top-sing">
				  <div class="col-md-7 single-top">	
					 <div class="flexslider">
							  <ul class="slides">
							    <c:if test="${onepic != 'null' }">
								<li data-thumb="<woo:url value='/static/purchasepicture/${onepic }'/>">
									<div class="thumb-image"> <img src="<woo:url value='/static/purchasepicture/${onepic }'/>" data-imagezoom="true" class="img-responsive" alt=""/> </div>
								</li>
								</c:if>
								<c:if test="${twopic != 'null' }">
								<li data-thumb="<woo:url value='/static/purchasepicture/${twopic }'/>">
									 <div class="thumb-image"> <img src="<woo:url value='/static/purchasepicture/${twopic }'/>" data-imagezoom="true" class="img-responsive"  alt=""/> </div>
								</li>
								</c:if>
								<c:if test="${threepic != 'null' }">
								<li data-thumb="<woo:url value='/static/purchasepicture/${threepic }'/>">
								   <div class="thumb-image"> <img src="<woo:url value='/static/purchasepicture/${threepic }'/>" data-imagezoom="true" class="img-responsive"  alt=""/> </div>
								</li>
								</c:if> 
								<c:if test="${onepic == 'null' }">
								  <c:if test="${twopic == 'null' }">
								    <c:if test="${threepic == 'null' }">
								     <p>该产品暂时没有图片</p>
								    </c:if>
								  </c:if>
								</c:if>
							  </ul>
					  </div>					 					 
					 <script src="<woo:url value='/static/js/imagezoom.js'/>"></script>
						<script defer src="<woo:url value='/static/js/jquery.flexslider.js'/>"></script>
						<script>
						// Can also be used with $(document).ready()
						$(window).load(function() {
						  $('.flexslider').flexslider({
							animation: "slide",
							controlNav: "thumbnails"
						  });
						});
						</script>
				 </div>	
			     <div class="col-md-5 single-top-in simpleCart_shelfItem">
					  <div class="single-para ">
						 <h3 style="text-align:center;color:#EE6AA7;">${name }</h3>							
							<h3 style="text-align:right;color:red;">￥:${price }</h3>							
							<h3 style="color:#FFA07A;">
							采购信息:</h3>
							<h3 style="color:#CD69C9;">${infomation }</h3>
							</br>
							</br>
							</br>
							<div>
								 <ul>
									 <h3 style="text-align:right;color:#EE9572;">生产地 :${addr }</h3>
									 
									 <h3 style="text-align:right;color:#EE9572;">商品公司 :${company }</h3>
									 
								 </ul>
							</div>
							<div class="check">
							      <woo:permission operationType="VIEW" roleType="ROLE_USER">
								  <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#mymodal">获取联系信息</button>
						          </woo:permission>
						    </div>							
					 </div>
				 </div>
				 <div class="clearfix"> </div>
			 </div>
	     </div>
	 </div>
</div>

<div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	<form action="/purchase/lineman" id="reset-form" method="post">
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel">确认支付</h4>
		      	</div>
		      	<div class="modal-body">
		      		<div class="form-group">
			            <label for="recipient-name" class="control-label">你确认花费0.05来查看此信息吗<sup>*</sup>(如已买此信息可直接按确认，系统不会扣取费用)</label>
			            <input type="text" id="number" name="number" value="${number }" style="display:none" required />
		          	</div>
		      	</div>
		      	<div class="modal-footer">
		      		<input type="hidden" name="userId" id="userId" value="">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        	<button type="submit" class="btn btn-primary">确认</button>
		      	</div>
	      	</form>
	    </div>
  	</div>
</div>