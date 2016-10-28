<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>
<style>
input[type=checkbox], input[type=radio] {margin:0;}
    .form-group {
    margin-bottom: 5px;
    } 
    .panel {
	margin-bottom:0;
    }
    .info{
            border: solid 1px silver;
    }
    div.info-top{
            position: relative;
            background: none repeat scroll 0 0 #F9F9F9;
            border-bottom: 1px solid #CCC;
            border-radius: 5px 5px 0 0;
    }
    div.info-top div{
            display: inline-block;
            color: #333333;
            font-size: 14px;
            font-weight: bold;
            line-height: 31px;
            padding: 0 10px;
    }
    div.info-top img{
            position: absolute;
            top: 10px;
            right: 10px;
            width:10px;
            height:10px;
            transition-duration: 0.25s;
    }
    div.info-top img:hover{
            box-shadow: 0px 0px 5px #000;
    }
    div.info-middle{
            font-size: 12px;
            padding: 6px;
            line-height: 20px;
    }  
    div.info-bottom{
            height: 0px;
            width: 100%;
            clear: both;
            text-align: center;
    }   
    div.info-bottom img{
            position: relative;
            z-index: 104;
              width:50px;
            height:50px;
    }
    span{
            margin-left: 5px;
            font-size: 11px;
    }
    .info-middle img {
            float: left;
            margin-right: 6px;
            width:80px;
            height:50px;
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
}  
</style>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>地图</title>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=3be8235f1cb10f423e1fdbdd0c9be773"></script>
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="<woo:url value='/static/bootstrap/jquery/jquery.min.js'/>"></script>
</head>

<body>
 <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                    <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
	                   </ul>
  </div>
<div class="navbar navbar-default navbar-fixed-top" id="top" style="background-color:#FFFFCC;"   >
        <ol class="breadcrumb" style="background-color:#FFFFCC;text-align:center;">
           <li>
		  	 <div class="input-group">
		  	  <span class="input-group-addon"><img src="<woo:url value="/static/userpicture/${picture }"/>" alt="个人头像" id="lis" width="20px;" height="20px;" class="img-circle" /></span>
		  	<input type="text" class="form-control" name="keyWord" id="keyWord" placeholder="Search for..." style="background-color:#FFFFCC"  >
		  	 <span class="input-group-btn">
                <input type="button"  class="btn btn-primary"  onclick="javascript:seachMap()" value="搜索" >
             </span>
             
		  	</div>
		  	</li>
		</ol>
</div>
<div id="test">
 <div id="container" >
</div>
 </div>
 <div style="z-index:999;top:20px;"><input type="checkbox" id="satellite" onclick="satellite(this)"/>卫星地图</div>
<div style="display:none" >
<form action="<c:url value='/portal/things/mapmessage'/>" method="get" >
<input id="usrname" type="text" name="usrname" value="1" required >
<input type="submit" id="post" name="post" value="get"/>
</form>
</div>

<div style="display:none" >
<form action="<c:url value='/portal/needs/mapmessage'/>" method="get" >
<input id="usrname1" type="text" name="usrname1" value="1" required >
<input type="submit" id="postneed" name="postneed" value="get"/>
</form>
</div>


<script>
$(document).ready(function(){
	var oImg=document.getElementById('top');
	oImg.style.width='100%';
	oImg.style.height=document.documentElement.clientHeight/30+'px'; 
	var oImg=document.getElementById('container');
	oImg.style.top=document.getElementById("top").offsetHeight+'px';
	var oImg=document.getElementById('footer');
	oImg.style.width='100%'; 
});
var maker,map=new AMap.Map('container',{resizeEnable:true,
    zoom:8,
    center:[112.498586, 22.239637]});
//比例尺插件
map.plugin(["AMap.Scale"],function(){
    var scale = new AMap.Scale();
    map.addControl(scale);  
});
//地图操作工具条插件
map.plugin(["AMap.ToolBar"],function(){
    var tool = new AMap.ToolBar();
    map.addControl(tool);   
});
//浏览器定位插件
map.plugin(["AMap.Geolocation"],function(){
geolocation123=new AMap.Geolocation(
{
enableHighAccuracy: true,
timeout: 10000,  
buttonOffset: new AMap.Pixel(14,130),
zoomToAccuracy: true,  
buttonPosition:'RB'
});
map.addControl(geolocation123);
});
//移动
map.on('click',function(e){ 
var x=e.lnglat.getLng();
var y=e.lnglat.getLat();
map.panTo([x,y])});
//展示卫星地图
var Satellite= new AMap.TileLayer.Satellite({zIndex:10});
Satellite.setMap(map);
Satellite.hide();
function satellite(checkbox){
  if(checkbox.checked){
     Satellite.show();
}
 else{
     Satellite.hide();
}
}


//循环遍历
<c:forEach var="message" items="${usemessage}">
var panduan=${message.getJudge()};
if(panduan==1){
var lat=${message.getLng()};
var lng=${message.getLat()};
var usrid=${message.getThingsId()};
var price=${message.getThingsprice()};
var addr='${message.getThingsaddr()}';
var phone='${message.getThingsphone()}';
var name='${message.getThingsname()}';
var picnames='${message.getThingspicturename()}';
var judge='${message.getThingsoveranalyzed()}';
if(judge==1){
addmarker(lat,lng,usrid,price,phone,addr,name,picnames);
}
}
else{
	var lat=${message.getLng()};
	var lng=${message.getLat()};
	var usrid=${message.getThingsId()};
	var price=${message.getThingsprice()};
	var addr='${message.getThingsaddr()}';
	var phone='${message.getThingsphone()}';
	var name='${message.getThingsname()}';
	var picnames='${message.getThingspicturename()}';
	var judge='${message.getThingsoveranalyzed()}';
	if(judge==1){
	addneed(lng,lat,usrid,price,phone,addr,name,picnames);
	}
}
</c:forEach>

function addneed(jingdu,weidu,usrid,price,phone,addr,name,picnames){
	marker = new AMap.Marker({                           //丅自定义图标
	icon:new AMap.Icon({size:new AMap.Size(40,50),image:"<woo:url value='/static/images/xu.png'/>",imageOffset:new AMap.Pixel(0,0)}),
	position:[jingdu,weidu]
	});
	marker.setMap(map);
	//marker.on('click',openInfo());
	marker.setTitle("主要信息");
	AMap.event.addListener(marker, 'click', function(){openInfo1(jingdu,weidu,usrid,price,phone,addr,name,picnames);});
	}
	function openInfo1(lng1,lat1,useid,prices,phones,addrs,name,picnames){
	    //构建信息窗体中显示的内容
	    var maininformation="求租主要信息";
	    var style3='<span style="font-size:11px;color:blue;">';
	    var namestyle='求租名字:';
	    var names=name;
	    var style4='</span>';
	    var style1='<span style="font-size:11px;color:#F00;">';
	    var pricestyle='价格(￥/天):';
	    var price=prices;
	    var style2='</span>';
	    var sum=maininformation+style3+namestyle+names;
	    var content=[];
	    useidnumber1(useid);
	   //var img='<img src="<woo:url value="/static/picture/newfileName756332784.jpg"/>">'; 
	    var img1='<img src="<woo:url value="';
	    var img3='/static/needspicture/';
	    var img4=picnames;
	    var img5='"/>">';
	    var img=img1+img3+img4+img5;
	    var dizhi='地址:';
	    var addr=addrs;
	    var overlay=img+dizhi+addr;
	    var p0='<span style="font-size:11px;color:red;">';
	    var p1="价格:￥";
	    var p2=prices;
	    var p3='</span>';
	    var phone=p0+p1+p2+p3;
	    content.push(overlay);
	    content.push(phone);
	    content.push('<a href="#" onclick="postneed()" >详细信息</a>');
	    /*
	    var info = [];
	    info.push('<a href="#" onclick="post()" >详细信息</a>');
	    info.push('<a href="<c:url value="/portal/admin/mapmessage"/>" onclick="post()" >详细信息</a>');
	    */
	    infoWindow = new AMap.InfoWindow({
	    	isCustom:true,
	        content:createInfoWindow(sum,content.join("<br/>")),  //使用默认信息窗体框样式，显示信息内容
	        offset: new AMap.Pixel(25,-63)
	    });
	    infoWindow.open(map,[lng1,lat1]);
}

function addmarker(jingdu,weidu,usrid,price,phone,addr,name,picnames){
marker = new AMap.Marker({                           //丅自定义图标
icon:new AMap.Icon({size:new AMap.Size(40,50),image:"<woo:url value='/static/images/zu.png'/>",imageOffset:new AMap.Pixel(0,0)}),
position:[jingdu,weidu]
});
marker.setMap(map);
//marker.on('click',openInfo());
marker.setTitle("主要信息");
AMap.event.addListener(marker, 'click', function(){openInfo(jingdu,weidu,usrid,price,phone,addr,name,picnames);});
}
function openInfo(lng1,lat1,useid,prices,phones,addrs,name,picnames){
    //构建信息窗体中显示的内容
    var maininformation="物品主要信息";
    var style3='<span style="font-size:11px;color:blue;">';
    var namestyle='物品名字:';
    var names=name;
    var style4='</span>';
    var style1='<span style="font-size:11px;color:#F00;">';
    var pricestyle='价格(￥/天):';
    var price=prices;
    var style2='</span>';
    var sum=maininformation+style3+namestyle+names;
    var content=[];
    useidnumber(useid);
   //var img='<img src="<woo:url value="/static/picture/newfileName756332784.jpg"/>">'; 
    var img1='<img src="<woo:url value="';
    var img3='/static/thingspicture/';
    var img4=picnames;
    var img5='"/>">';
    var img=img1+img3+img4+img5;
    var dizhi='地址:';
    var addr=addrs;
    var overlay=img+dizhi+addr;
    var p0='<span style="font-size:11px;color:red;">';
    var p1="价格:￥";
    var p2=prices;
    var p3='</span>';
    var phone=p0+p1+p2+p3;
    content.push(overlay);
    content.push(phone);
    content.push('<a href="#" onclick="post()" >详细信息</a>');
    /*
    var info = [];
    info.push('<a href="#" onclick="post()" >详细信息</a>');
    info.push('<a href="<c:url value="/portal/admin/mapmessage"/>" onclick="post()" >详细信息</a>');
    */
    infoWindow = new AMap.InfoWindow({
    	isCustom:true,
        content:createInfoWindow(sum,content.join("<br/>")),  //使用默认信息窗体框样式，显示信息内容
        offset: new AMap.Pixel(25,-63)
    });
    infoWindow.open(map,[lng1,lat1]);
}
function createInfoWindow(title, content) {
	var info = document.createElement("div");
	info.className = "info";
	var top = document.createElement("div");
	var titleD = document.createElement("div");
	var closeX = document.createElement("img");
	top.className = "info-top";
	titleD.innerHTML = title;
	closeX.src = "http://webapi.amap.com/images/close2.gif";
	closeX.onclick = closeInfoWindow;

	top.appendChild(titleD);
	top.appendChild(closeX);
	info.appendChild(top);
	// 定义中部内容
	var middle = document.createElement("div");
	middle.className = "info-middle";
	middle.style.backgroundColor = 'white';
	middle.innerHTML = content;
	info.appendChild(middle);
    // 定义底部内容
	var bottom = document.createElement("div");
	bottom.className = "info-bottom";
	bottom.style.position = 'relative';
	bottom.style.top = '0px';
	bottom.style.margin = '0 auto';
	var sharp = document.createElement("img");
	sharp.src = "http://webapi.amap.com/images/sharp.png";
	bottom.appendChild(sharp);
	info.appendChild(bottom);
	return info;
}
//关闭信息窗体
function closeInfoWindow() {
map.clearInfoWindow();
}

function useidnumber(usrid){
	$("#usrname").val(usrid);
}
function useidnumber1(usrid){
	$("#usrname1").val(usrid);
}
function post(){
	$('#post').trigger("click");
}
function postneed(){
	$('#postneed').trigger("click");
}

//搜索功能
function seachMap(){
	var mapaddr=$("#keyWord").val();
	AMap.service(["AMap.PlaceSearch"],function()
	{
	var placeSearch=new AMap.PlaceSearch({
	pageSize:1,
	pageIndex:0,
	city:"010",
	map:map                                     
	});
	placeSearch.search(mapaddr,function(status,result){});
	});
}



</script>

</body>


