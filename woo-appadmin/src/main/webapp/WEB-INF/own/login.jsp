<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>

	
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
<div id="contact" class="contact" >
	 <div class="container">
		 <ol class="breadcrumb">
		  <li><a href="index.html">我的</a></li>
		  <li class="active">Login</li>
		 </ol>
		 <h3 style="color:#BA55D3">登陆</h3>
		 <p>欢迎使用本平台
		 </p>
		 <p>如果是商家还没有账号?<span><a href="/register" style="color:red;">点击我</a></span>
		 </p>
	 </div>
		 <div class="col-md-6 log" >	
		    <div class="container">
		      <div class="col-md-8 contact-top-right">
				<div class="panel-body" >
                        <form id="test" action="/secure/check" method="post" onsubmit="return ajax()" >
                            <fieldset>
                                <div class="form-group" >
                                	<input id="usrname" type="text" class="form-control" name="usrname" placeholder="商家账号" value="" required />
                                </div>
                                <div class="form-group">
                                    <input id="password" type="password" class="form-control" name="password" value="" placeholder="商家密码" required />
                                </div>
                                <div class="form-group" >
                                    <input id="verify" type="text" class="form-control" name="verify" value="" placeholder="验证码"  required />
                                    <span><img id="verifycode" name="verifycode" alt="验证码" src="<c:url value='/verifycode'/>" onclick="javascript:this.src='/verifycode?'+Math.random()" ></span>
                                </div>
                                <div class="form-group" style="color:#F00;text-align:center">
									<span id="message">${SPRING_SECURITY_LAST_EXCEPTION}</span> 
								</div>
                                <input type="submit" id="sub" class="btn btn-lg btn-success btn-block"  tabindex="3" value="登录"/>
                            </fieldset>
                        </form>
                    </div>
                    <h6 style="text-align:center;">验证码看不清点击图片更换</h6>
					<p style="text-align:center;"><a href="/findPass">忘记密码</a></p>
		       </div>
			 </div>
		 </div>			
		 <div class="clearfix"></div>
</div>


<script type="text/javascript">

var ajaxpost=false;

function ajax(){

   var reg=new RegExp(/^[a-zA-Z0-9_\.]+$/);
   var usrname=$("#usrname").val();
   var pass=$("#password").val();
   if(!reg.test(usrname)){
     alert("账户只能输入字母、数字、点和下划线!");
     return false;
   }
   if(!reg.test(pass)){
     alert("密码只能输入字母、数字、点和下划线！");
     return false;
   }
  
   var xml;
   if(window.XMLHttpRequest) {
					//针对 Firefox, Chrome, Opera, Safari,IE7,IE8
					xml = new XMLHttpRequest();
	} else if(window.ActiveXObject) {
					//针对IE6,IE5
					xml = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xml.onreadystatechange = function(){//最后才执行的函数
      if(xml.readyState == 4) {
					if(xml.status == 200) {
						var responseText = xml.responseText;
						if(responseText=='true'){
						ajaxpost=true;
						}
						else{
						alert("验证码错误");
						ajaxpost=false;
						}
					}
				}
    }
    var verify=$("#verify").val();
    xml.open("POST","/ajaxcode",false);
    xml.send(verify);
    return ajaxpost;
}

</script>

