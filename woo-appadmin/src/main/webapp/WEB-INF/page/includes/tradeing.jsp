<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head>
<title>正在交易</title>
<style type="text/css">
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
</head>

<div class="panel panel-default"  style="background-color:#FFFFCC">
    <div class="panel-heading"  style="background-color:#FFFFCC">
        <ol class="breadcrumb"  style="background-color:#FFFFCC">
		  	<li class="active"><b><i>物品正在交易</i></b></li>
		</ol>
    </div>
    <div class="panel-body"  style="background-color:#FFFFCC">
        
        <div class="row toolbar"  style="background-color:#FFFFCC">
        
			<div class="col-sm-7"  style="background-color:#FFFFCC">
				<div class="btn-group" role="group"  style="background-color:#FFFFCC" >
         		
				</div>
			</div>
			
   			
        </div>
        
        <div class="table-responsive"  style="background-color:#FFFFCC">
		    <table class="table table-hover"  style="background-color:#FFFFCC">
		        <thead>
		            <tr>
	                    <th width="33%">名称</th>
	                    <th width="33%">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${gridthings.datas }" varStatus="index" var="data">
			        <c:if test="${gridthings.datas != null}">
			            <c:if test="${data.success == 1}">
			            <tr class="success">
			                <td>${data.goodsname}</td>
			                <td>
			                <a href="#" data-id="${data.trade}" class="btnEdit" data-url="trade/thingsdetailmessage" >                            
							[查看详细信息]
							</a>
							<a href="#" data-id="${data.trade}" class="btnEdit" data-url="trade/tradesuccess" >
							[交易完成]
							</a>
							<a href="#" data-id="${data.thing}" class="btnEdit" data-url="trade/position" >
							[物品位置]
							</a>
			                </td>
			            </tr>
			           </c:if>
			        </c:if>
		            </c:forEach>
		            <c:forEach items="${gridneeds.datas }" varStatus="index" var="date">
			        <c:if test="${gridneeds.datas != null}">
			            <c:if test="${date.success == 1}">
			            <tr class="success">
			                <td>${date.goodsname}</td>
			                <td>
			                <a href="#" data-id="${date.trade}" class="btnEdit" data-url="trade/needsdetailmessage" >                            
							[查看详细信息]
							</a>
							<a href="#" data-id="${date.trade}" class="btnEdit" data-url="trade/tradesuccess" >
							[交易完成]
							</a>
							<a href="#" data-id="${date.thing}" class="btnEdit" data-url="trade/position" >
							[物品位置]
							</a>
			                </td>
			            </tr>
			           </c:if>
			           </c:if>
		            </c:forEach>
		   <c:if test="${judge == 0 }">
		    <tr>
           <th></th>
             <th style="color:red;" text-align="left;">暂时没有相关信息！</th>
              <th></th>
                   </tr>
               </c:if>
		        </tbody>
		    </table>
		    
		</div>
    </div>
  
    <div class="panel-footer"  >
        <div class="row" >
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
    	</div>
    </div>
     <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                    <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>      </ul>
  </div>
    
</div>

<script type="text/javascript">
$(document).ready(function(){
	var oImg=document.getElementById('footer');
	oImg.style.width='100%'; 

});
</script>
