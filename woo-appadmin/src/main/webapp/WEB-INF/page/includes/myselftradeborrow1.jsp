<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>

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
   background-color:#FFFFCC;
} 
</style>


<div id="wrapper" style="background-color:#FFFFCC" >
        <div id="top"   style="background-color:#FFFFCC;" >
        <ol class="breadcrumb" style="background-color:#FFFFCC;text-align:center;height:20px;">
           <li>
           <form method="post">
		  	 <div class="input-group" style=" margin:0;border:0;padding:0;" >
		  	 <span class="input-group-addon"><img src="<woo:url value="/static/images/4.png"/>" alt="QQ商城焦点图效果下载" id="lis" width="20px;" height="20px;" class="img-circle" /></span>
		  	<input type="text" class="form-control" name="keyWord" id="keyWord" placeholder="Search for..." style="background-color:#FFFFCC"  >
		  	<span class="input-group-btn">
                <input type="submit"  class="btn btn-primary"   value="搜索" >
             </span>
		  	</div>
		  	</form>
		  	</li>
		</ol>
    </div>
     <div id="height">
    </div>
<div class="table-responsive">
<p style="font-family: Helvetica,Hiragino Sans GB,Microsoft Yahei,微软雅黑, Arial, sans-serif">别人有你的东西你要租或者你确认租给别人的物品</p>
<table class="table table-hover">
<thead>
<tr>
		<th width="25%">其他用户名</th>
		<th width="25%">物品名字</th>
		<th width="25%">操作</th>
</tr>
</thead>
	<c:forEach items="${grid.datas}" var="data" varStatus="index" >
		<tr height="80px;">
		<th>${data.sellername }</th>
		<th>${data.goodsname }</th>
		<th>
		<woo:permission operationType="TRADE_DELETE" roleType="ROLE_TRADE">
									<a href="javascript:void(0);" data-id="${data.trade }"  class="btnEdit" data-url="trade/borrowensure">
									[确认租借]
									</a>
		</woo:permission>
		<woo:permission operationType="TRADE_DELETE" roleType="ROLE_TRADE">
									<a href="javascript:void(0);" data-id="${data.trade }"  class="btnEdit" data-url="trade/borrownoensure">
									[取消租借]
									</a>
		</woo:permission>
		</th>
		</tr>
	</c:forEach>
	<c:if test="${grid.datas==null }">
    <tr>
    <th></th>
    <th style="color:red;">暂时没有相关信息！</th>
    <th></th>
    </tr>
    </c:if>
</table>
</div>	
<div class="panel-footer" >
        <div class="row">
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
    	</div>
    </div>
    <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>       </ul>
  </div>	
  
 </div>
 	

 
<script type="text/javascript">
$(document).ready(function(){
	var oImg=document.getElementById('footer');
	oImg.style.width='100%';
	var oImg=document.getElementById('top');
	oImg.style.width='100%';
	oImg.style.height=document.documentElement.clientHeight/30+'px'; 
	var oImg=document.getElementById('height');
	oImg.style.width='100%';
	oImg.style.height=document.getElementById("top").offsetHeight+'px'; 

});
</script>
