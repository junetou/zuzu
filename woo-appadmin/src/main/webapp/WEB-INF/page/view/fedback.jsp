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
.hei{
    height:100px;
    width:1080px;
    maxlength:300;
}
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
   width:600px;}
</style>

<head><title>租一天</title></head>

<script type="text/javascript">
function change()
{
    $(":text").each(function() {
        $("<textarea rows='3' clos='5' />").val($(this).val()).insertAfter($(this));
        $(this).remove();
    });
}
change();
</script>

<div class="panel panel-default" id="panel" style="background-color:#FFFFCC; width:420px;" >
   <div class="panel-heading" style="background-color:#FFFFCC;">
    <ol class="breadcrumb" style="background-color:#FFFFCC; ">
     <li><b><i>反馈</i></b></li>
     <li><a href="javascript:history.go(-1)" class="btn btn-outline btn-info" >返回</a></li>   
    </ol>
   </div>
   <div style="background-color:#FFFFCC">
     <table class="table table-striped" style="background-color:#FFFFCC;">
     <form action="<c:url value='/portal/fed/fed'/>" method="post" >
      <thead>
      <tr>
      <th>请在下面填写反馈信息<span><input type="submit" id="post" name="post" class="btn btn-outline btn-info" value="提交"/></span></th>
      </tr>
      </thead>
      <tr style="background-color:#FFFFCC" >
      <th id="test"><textarea id="thingsname" cols="5" rows="2" name="thingsname" class="hei" style="background-color:#FFFFCC; width:400px; height:500px; " ></textarea></th>
 <!--   <input id="thingsname" type="text" name="thingsname"  class="hei" required /></th> -->   
      </tr>      
      <tr>
      </tr>
     </form>
     </table>
   </div>
</div>
<div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                     <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000" id="abc"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
                             </ul>
  </div>

<script>
	$(document).ready(function(){
		var oImg=document.getElementById('footer');
		oImg.style.width='100%';
		var oImg=document.getElementById('panel');
		oImg.style.width='100%';
		var oImg=document.getElementById('thingsname');
		oImg.style.width='100%';
	});
</script>
 