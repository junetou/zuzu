<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<style>
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
   }
</style>


<div id="tex" class="panel panel-default">
<h1 style="text-align:center;"><b><i>声明</i></b></h1>
<textarea id="tex1">请使用者遵守互联网使用原则，不发布低俗图片和内容，文明用语，不得辱骂其他用户，或伤害其他用户的行为，如发现取消用户登陆资格，情节严重则报公安机关进行处理，本网站不用于商业用途！请交易双方在交易前确认好双方各自责任！如发生利益冲突，本人概不负责！本网站部分功能不是本人设计，如有侵权请为，请立即联系本人！感谢各位用户的配合！QQ:756332784;微信:junetou;</textarea>
<p class="date">2016年10月24日</p>
</div>
 <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
        </ul>
  </div>

<script type="text/javascript">
$(document).ready(function(){
	var oImg=document.getElementById('tex');
	oImg.style.width='100%';
	oImg.style.height='100%';
	var oImg=document.getElementById('tex1');
	oImg.style.width='100%';
	oImg.style.height=document.documentElement.clientHeight+'px'; 
	var oImg=document.getElementById('footer');
	oImg.style.width='100%';
});
</script>





 