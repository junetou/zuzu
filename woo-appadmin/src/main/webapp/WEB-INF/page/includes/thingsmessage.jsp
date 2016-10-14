<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>
<style>
.form-group {
    margin-bottom: 5px;
} 
.panel {
	margin-bottom:0;
}
    #footer{  
   position: fixed;  
   bottom: 10px; /* 关键 */  
   left:0; /* IE下一定要记得 */  
   height: 30px;         /* footer的高度一定要是固定值*/
   z-index:999;  
   text-align:center;
   margin:0px auto;
   border:0;
   padding:0;
   background-color:#666;
   width:600px;
}  
</style>
<title>租一天</title>

<div class="panel panel-default">
   <div class="panel-heading">
     <ol class="breadcrumb">
     <li>物品信息</li>
     <li>物品名字：${thingsname }</li>
     <li><a href="javascript:history.go(-1)">返回</a></li>
     </ol>
   </div>
   <woo:permission operationType="THINGS_VIEW" roleType="ROLE_THINGS">
   <div  id="main">
   <!--  
         <div style="display:none" >
         <form action="<c:url value='/portal/admin/chat/get'/>" method="get" >
         <input id="usrid" type="text" name="usrid" value='${thingsnumber}' required >
         <input type="submit" id="post" name="post" value="get"/>
         </form>
         </div>
         <div style="display:none" >
         <form action="<c:url value='/portal/admin/trade/buy'/>" method="get" >
         <input id="thingsid" type="text" name="thingsid" value='${thingsid}' required >
         <input type="submit" id="tijiao" name="tijiao" value="get"/>
         </form>
     	<form:form id="group-form" method="post">
			<div class="btn-group" role="group" style="margin-bottom:20px;">
				<a href="#" class="btn btn-outline btn-info" onclick="post()">我要与卖家联系</a>
				<a href="#" class="btn btn-outline btn-info" onclick="tijiao()">我要租借</a>
		</div>
        </form:form>
        -->
        <div class="row">
        
        <div class="col-md-6">
        <div class="form-gourp">
           <label>出售者用户名</label>
           <input  type="text"  value="${thingsusername }" class="form-control" />
        </div>
        <div class="form-gourp">
           <label>产品名字</label>
           <input  type="text"  value="${thingsname }" class="form-control" />
        </div>
        <div class="form-gourp">
           <label>产品详细信息</label>
           <input type="text" value="${thingsdesc}"  class="form-control"  />
        </div>
        <div class="form-gourp">
          <label>产品价格</label>
          <input  type="text" value="${thingsprice }"  class="form-control" />
       </div> 
       </div>
       </form>        
   </div>
   <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                   <li class="btn btn-warning" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;"><a href="<c:url value='/portal/map/showmap'/>" ><b><i class="glyphicon glyphicon-gift">地图</i></b></a></li>
	                   <li class="btn btn-success" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%" ><a href="<c:url value='/portal/list/showlist'/>"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%" ><a href="<c:url value='/portal/person'/>"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
        </ul>
  </div>
   </woo:permission>
   


<script type="text/javascript">
function post(){
	$('#post').trigger("click");
	}
function tijiao(){
		alert("租借成功");
		$('#tijiao').trigger("click");
}
$(document).ready(function(){
	var oImg=document.getElementById('main');
	oImg.style.height='100%';
	var oImg=document.getElementById('footer');
	oImg.style.width='100%'; 
});
</script>

