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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=3be8235f1cb10f423e1fdbdd0c9be773"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<title>用一天</title>

</head>


<div class="panel panel-default" style="background-color:#FFFFCC">
   <div class="panel-heading" style="background-color:#FFFFCC">
    <ol class="breadcrumb" style="background-color:#FFFFCC">
     <li><input value="定位" onclick="javascript:Realtime()" type="button" ></li>
      <li><input value="取消定位" onclick="javascript:Canneltime()" type="button" ></li>
     </li>
    </ol>
   </div>
   <div id="container" style=" height:93%; width:auto; top:7%" ></div>
    <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                    <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>      </ul>
  </div>
</div>

<script>
var maker,map=new AMap.Map('container',{resizeEnable:true,
    zoom:10,
    center:[121.498586, 31.239637]});
//比例尺插件
map.plugin(["AMap.Scale"],function(){
    var scale = new AMap.Scale();
    map.addControl(scale);  
});
//循环遍历
var lat=${thingslng};
var lng=${thingslat};
addmarker(lng,lat);
//搜索控件
function addmarker(jingdu,weidu){
marker = new AMap.Marker({                           //丅自定义图标
icon:new AMap.Icon({size:new AMap.Size(40,50),image:"http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",imageOffset:new AMap.Pixel(0,-60)}),
position:[jingdu,weidu]
});
marker.setMap(map);
}
map.plugin(["AMap.Geolocation"],function(){
	geolocation123=new AMap.Geolocation(
	{
	enableHighAccuracy: true,
	timeout: 10000,  
	buttonOffset: new AMap.Pixel(5,5),
	zoomToAccuracy: true,  
	buttonPosition:'RB'
	});
	map.addControl(geolocation123);
	});
function Realtime(){
	geolocation123.watchPosition();
}
function Canneltime(){
	geolocation123.clearWatch();
}
$(document).ready(function(){
	var oImg=document.getElementById('footer');
	oImg.style.width='100%'; 

});
</script>


 