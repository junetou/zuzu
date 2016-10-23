<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</style>

<div id="tex" class="panel panel-default">
<h1 style="text-align:center;"><b><i>使用说明</i></b></h1>
<textarea id="tex1">
1.用户可以在物品操作里添加物品，在需求操作里增加需求。
2.默认列表显示的是用户发布的最新物品，按物品按钮后，列表物品顺序按最久时间排序，需求物品顺序也是按最久时间排序
3.用户如果有看到合适自己的需求可以按我有需求，自己看中哪个物品可以按我要租凭
4.正在交易包括所有双方确认后的交易。
5.只有双方都按确认交易后才可看到双方的微信或者QQ等联系方式.
6.用户在更新自己资料时，如上传新头像后，请刷新一下页面。
</textarea>
<p class="date">2016年9月4日</p>
</div>

 <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                    <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
	                       </ul>
  </div>
  
  <script>
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