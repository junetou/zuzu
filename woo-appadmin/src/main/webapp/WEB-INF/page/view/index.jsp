<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<style type="text/css">
	#page-wrapper {padding:0;}
	.tips-btn{
	position: absolute;
    top: 2px;
    right: 0;
    width: 16px;
    height: 16px;
    border-radius: 50%;
    background: #ff0033;
    text-align: center;
    line-height: 16px;
    font-size: 12px;
    color: #fff;
    z-index:99999;
	}
</style>
<div id="wrapper">
     <!-- Navigation -->
     <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
         <div class="navbar-header">
             <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                 <span class="sr-only">Toggle navigation</span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
             </button> 
             <a href="<c:url value='/portal.htmls'/>"><img alt="" src="<woo:url value="/static/images/logo.png"/>"></a>
         </div>
         <!-- /.navbar-header -->

         <ul class="nav navbar-top-links navbar-right">
        		<c:if test="${!empty questionParam }">
          		 <li class="dropdown">
              	   <span id="redTips"></span>
                  <a class="dropdown-toggle" target="mainFrame" href="${questionParam }&isMenu=no" title="问答管理">
                      <i class="fa fa-envelope fa-fw"></i>
                  </a>
              </li>
         		</c:if>
            
            <c:if test="${!empty txMoneyParam }">
              <li class="dropdown">
              	   <span id="txRedTips"></span>
                  <a class="dropdown-toggle" target="mainFrame" href="${txMoneyParam }" title="提现管理">
                      <i class="fa fa-rmb fa-fw"></i>
                  </a>
              </li>  
            </c:if>
            
             <!-- /.dropdown -->
             <li class="dropdown">
                 <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                     <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                 </a>
                 <ul class="dropdown-menu dropdown-user">
                     <li>
						<a href="<c:url value='/user/setup'/>" target="mainFrame"><i class="fa fa-user fa-fw"></i>账户设置</a> 
					</li>
                     <li class="divider"></li>
                     <li>
                     	<a href="<c:url value='/logout'/>"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
                     </li>
                 </ul>
                 <!-- /.dropdown-user -->
             </li>
             <!-- /.dropdown -->
         </ul>
         <!-- /.navbar-top-links -->

         <div class="navbar-default sidebar main-menu" role="navigation">
         
             <div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
			<c:forEach var="menu" items="${menus}">
                   <li>
                   	<a href="#"><i class="${menu.classIcon}"></i> ${menu.name}<span class="fa arrow"></span></a>
                   	<ul class="nav nav-second-level">
						<c:forEach items="${menu.nodes }" var="m">
							<li>
								<a href="${m.likeUrl }" target="mainFrame"><i class="${m.classIcon}"></i> ${m.name }</a>
							</li>
						</c:forEach>
					</ul>
                   </li> 
                </c:forEach>
		</ul>
	</div>
	
	<div class="navbar navbar-default navbar-fixed-bottom"  style=" margin:0px auto;border:0;padding:0;background-color:#FFFFCC;">
        <div class="row" style=" margin:0px auto;border:0;padding:0;">
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate" style=" margin:0px auto;border:0;padding:0;">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
    	</div>
    </div>

         </div> 
         <!-- /.navbar-static-side -->
     </nav>

    <div id="page-wrapper">
		<iframe id="mainFrame" name="mainFrame" frameborder="0"  width="100%" ></iframe>
    </div> 

</div>

<script>
	$(document).ready(function(){
		var ifm = document.getElementById("mainFrame");
		var height = document.documentElement.clientHeight-60 || document.body.clientHeight-60;
		$(ifm).height(height);
		window.onresize = function () {
			var ifm= document.getElementById("mainFrame");
			var height = document.documentElement.clientHeight-60 || document.body.clientHeight-60;
			$(ifm).height(height);
		}
	});
</script>