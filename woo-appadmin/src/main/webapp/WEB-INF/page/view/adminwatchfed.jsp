<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head><title>租一天</title>
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
   width:600px;}
</style>
</head>

<div class="panel panel-default"  style="background-color:#FFFFCC">
    <div class="panel-heading"  style="background-color:#FFFFCC">
        <ol class="breadcrumb"  style="background-color:#FFFFCC">
		  	<li class="active"><b><i>查看反馈</i></b></li>
		</ol>
    </div>
    <div class="panel-body"  style="background-color:#FFFFCC">
        
        <div class="row toolbar"  style="background-color:#FFFFCC">
        
			<div class="col-sm-7"  style="background-color:#FFFFCC">
				<div class="btn-group" role="group"  style="background-color:#FFFFCC" >
         			<a href="javascript:location.reload();" class="btn btn-outline btn-info">刷新</a>
				</div>
			</div>
        </div>
        
        <div class="table-responsive"  style="background-color:#FFFFCC">
		    <table class="table table-hover"  style="background-color:#FFFFCC">
		        <thead>
		            <tr>
	                    <th width="50%">用户名字</th>
                        <th width="50%">内容</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${grid.datas }" varStatus="index" var="data">
			            <tr class="success">
			                <td>${data.name}</td>
			                <td>
						     ${data.desc}
			                </td>
			            </tr>
			        
		            </c:forEach>
		            <c:if test="${grid.datas == null}">
						<tr>
							<td colspan="7">暂无人反馈！</td>
						</tr>
					</c:if>
		        </tbody>
		    </table>
		</div>
    </div>
    
    <div class="panel-footer"  style="background-color:#FFFFCC">
        <div class="row" style="background-color:#FFFFCC">
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
    	</div>
    </div>
    <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                     <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000" id="abc"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
                             </ul>
  </div>
</div>


<script>
	$(document).ready(function(){
		var oImg=document.getElementById('footer');
		oImg.style.width='100%';
	});
</script>
 

