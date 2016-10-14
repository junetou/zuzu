<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head>	
<link rel="icon" href="<woo:url value='/static/images/02.ico'/>" type="image/x-icon">
<link rel="shortcut icon" href="<woo:url value='/static/images/favicon.ico'/>" type="image/x-icon" />
<title>租一天</title> 
</head>

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
	* {margin:0; padding:0;} 
body {font-size:12px; color:#222; font-family:Verdana,Arial,Helvetica,sans-serif; background:#f0f0f0;} 
.clearfix:after {content: "."; display: block; height: 0; clear: both; visibility: hidden;} 
.clearfix {zoom:1;} 
ul,li {list-style:none;} 
img {border:0;} 
.wrapper {width:800px; margin:0 auto; padding-bottom:50px;} 

#focus {width:800px; height:280px; overflow:hidden; position:relative;} 
#focus ul {height:380px; position:absolute;} 
#focus ul li {float:left; width:800px; height:280px; overflow:hidden; position:relative; background:#000;} 
#focus ul li div {position:absolute; overflow:hidden;} 
#focus .btnBg {position:absolute; width:800px; height:20px; left:0; bottom:0; background:#FFCC33;} 
#focus .btn {position:absolute; width:780px; height:20px; padding:5px 10px; right:0; bottom:0; text-align:right;} 
#focus .btn span {display:inline-block; _display:inline; _zoom:1; width:30px; height:20px; _font-size:0; margin-left:5px; cursor:pointer; background:#fff;} 
#focus .btn span.on {background:#fff;} 
#focus .preNext {width:15px; height:28px; position:absolute; top:10px; background:#FFCC33 no-repeat 0 0; cursor:pointer;} 
#focus .pre {left:0;} 
#focus .next {right:0;} 
  #footer{  
   position: fixed;  
   bottom: 0px; /* 关键 */  
   left:0; /* IE下一定要记得 */  
   height: 35px;         /* footer的高度一定要是固定值*/
   z-index:999;  
   text-align:center;
   margin:0px auto;
   border:0;
   padding:0;
   background-color:#666;
   width:600px;
</style>


<div id="wrapper" style="background-color:#FFFFCC " >
  
     <nav class="navbar navbar-default navbar-static-top " role="navigation" style="margin-bottom: 0; background-color:#FFFFCC; ">
       
		
			<ul class="nav" id="side-menu">
               
               <li>
               <div style="background-image:url('<woo:url value="/static/images/c.jpg"/>'); text-align:center;">
               <img src="<woo:url value="/static/images/4.png"/>" alt="QQ商城焦点图效果下载" id="lis" width="50px;" height="45px;" class="img-circle" />
               <p class="text-primary" style="font-family: Helvetica,Hiragino Sans GB,Microsoft Yahei,微软雅黑, Arial, sans-serif"><b><i>用户名:admin</i></b></p>
               </div>
               </li>
            
			 <!--  <li class="dropdown"><img alt="" src="<woo:url value="/static/images/4.png"/>" id="bgImg" </li>-->
			<c:forEach var="menu" items="${menus}">
                   <li>
                   	<a href="#"><i class="${menu.classIcon}"></i> ${menu.name}<span class="fa arrow"></span></a>
                   	<ul class="nav nav-second-level">
						<c:forEach items="${menu.nodes }" var="m">
							<li>
							<a href="${m.likeUrl }"  ><i class="${m.classIcon}"></i> ${m.name }</a>
							</li>
						</c:forEach>
						
					</ul>
                   </li>
                </c:forEach>
                <li><a href="<c:url value='/portal/chat/wchat'/>"><i class="	glyphicon glyphicon-comment"></i>聊天(完善中)</a></li>
                <li><a href="<c:url value='/portal/admin/legal/showlegal'/>"><i class="glyphicon glyphicon-exclamation-sign"></i>法律声明</a></li>
                <li><a href="javascript:void(0);" data-id="${userid }" class="btnEdit" data-url="person/form"><i class="glyphicon glyphicon-edit"></i>修改个人信息</a></li>
                <li><a href="<c:url value='/logout'/>" > <i class="fa fa-user fa-fw"></i>注销账户</a></li>
		</ul>
		
	
      </nav>  
     <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                   <li class="btn btn-warning" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;"><a href="<c:url value='/portal/map/showmap'/>" ><b><i class="glyphicon glyphicon-gift">地图</i></b></a></li>
	                   <li class="btn btn-success" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%" ><a href="<c:url value='/portal/list/showlist'/>"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%" ><a href="<c:url value='/portal/person'/>" id="abc"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
        </ul>
  </div>
</div>



<script>
	$(document).ready(function(){
		var oImg=document.getElementById('footer');
		oImg.style.width='100%';
	});
</script>