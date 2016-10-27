<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>修改信息</title>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=3be8235f1cb10f423e1fdbdd0c9be773"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
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
</head>

<body>
<div class="panel panel-default" id="panel">
   <div class="panel-heading">
     <ol class="breadcrumb">
     <li>更新物品</li>
     <li><a href="javascript:history.go(-1)">返回</a></li>
     </ol>
   </div>
   <woo:permission operationType="THINGS_EDIT" roleType="ROLE_THINGS">
   <div class="panel-body" id="panelbody">
       <form action="<c:url value='/portal/things/updatethings'/>" method="post" enctype="multipart/form-data" >
        <input type="submit" id="post" name="post" class="btn btn-outline btn-info" onclick="uploadFile()"  value="更新物品"/>	
        <div class="row">
        
        <div class="col-md-6">
        <div class="form-gourp" style="position: absolute; top:-200px;" >
           <label>产品id<sup>*</sup></label>
           <input id="thingsid" type="text" name="thingsid" value="${thingsid}" readOnly="true"   required />
        </div>
        <div class="form-gourp">
           <label>物品名字<sup>*</sup></label>
           <input id="thingsname" type="text" name="thingsname" value="${thingsname }" class="form-control"  required />
        </div>
        <div class="form-gourp">
           <label>物品简单描述</label>
           <input id="briefdesc" type="text" name="briefdesc" value="${thingsdesc }" class="form-control"  required />
        </div>
        <div class="form-gourp">
          <label>物品地方</label>
          <input id="addr" type="text" name="addr" value="${thingsaddr }"  class="form-control"  required />
       </div>
       <div class="form-gourp">
           <label>物品可租借时间</label>
           <input id="thingsdate" type="text" name="thingsdate" value="${thingsdate }" class="form-control"  required />
        </div>
       </div>
       <div class="col-md-6">
       <div class="form-gourp">
           <label>物品价格<sup>*</sup></label>
           <input id="price" type="text" name="price" value="${thingsprice }" class="form-control" required />
        </div>
           <div class="form-gourp">
	       <lable>经度(点击地图即可获得)<sup>*</sup></lable>
	       <input id="lng" name="lng" type="text" value="${thingslng }"   class="form-control"   readonly="readonly" required />
           </div>
           <div class="form-gourp">
	       <lable>经度(点击地图即可获得)<sup>*</sup></lable>
	       <input id="lat" name="lat" type="text" value="${thingslat }"  class="form-control"  readonly="readonly" required />
           </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
          <label>上传图片</label>
          <input type="file"  name="file0" id="file0"  onchange="getPhotoSize0(this,0)" multiple="true" />
          <img src="<woo:url value="/static/thingspicture/${onepicturename }"/>" id="img0"  width="50px" height="50px"  ></th>
          </div>
         </div>
         <div class="col-md-6">
          <div class="form-group">
          <label>上传图片</label>
          <input type="file"  name="file1" id="file1" onchange="getPhotoSize1(this,1)" />
          <img src="<woo:url value="/static/thingspicture/${twopicturename }"/>" id="img1"  width="50px" height="50px"  ></th> 
          </div>
         </div>
         <div class="col-md-6">
          <div class="form-group">
          <label>上传图片</label>
          <input type="file" name="file2" id="file2"   onchange="getPhotoSize2(this,2)" />
          <img src="<woo:url value="/static/thingspicture/${threepicturename }"/>" id="img2"  width="50px" height="50px"  ></th>     
	      </div>
         </div>	        
        </div> 

       </form>        
   </div>
   </woo:permission>
     <div id="container"></div>
      <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                    <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
                            </ul>
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
//搜索控件
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
//获取经纬度
var clickEventListener=map.on('click', function(e){
var lng=e.lnglat.getLng();
var lat=e.lnglat.getLat();
showlnglat(lng,lat);
});

function showlnglat(lng,lat){
	document.getElementById("lng").value=lng;
	document.getElementById("lat").value=lat;
}

function getPhotoSize0(obj,i){
	photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
	if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif' && photoExt!='.png'){
		alert("请重新上传，图片不符合规格");
		$("#img0").attr("src","") ;
		return false;
	}
	var fileSize=0;
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
	if (isIE && !obj.files) {          
         var filePath = obj.value;            
         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
         var file = fileSystem.GetFile (filePath);               
         fileSize = file.Size;         
    }
	else {  
         fileSize = obj.files[0].size;     
    } 
	fileSize=Math.round(fileSize/1024*100)/100;
	var objUrl = getObjectURL(obj.files[0]);
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img0").attr("src", objUrl) ;
	}
}
function getPhotoSize1(obj,i){
	photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
	if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif' && photoExt!='.png'){
		alert("请重新上传，图片不符合规格");
		$("#img1").attr("src","") ;
		return false;
	}
	var fileSize=0;
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
	if (isIE && !obj.files) {          
         var filePath = obj.value;            
         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
         var file = fileSystem.GetFile (filePath);               
         fileSize = file.Size;         
    }
	else {  
         fileSize = obj.files[0].size;     
    } 
	fileSize=Math.round(fileSize/1024*100)/100;
	var objUrl = getObjectURL(obj.files[0]);
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img1").attr("src", objUrl) ;
	}
}
function getPhotoSize2(obj,i){
	photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
	if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif' && photoExt!='.png' ){
		alert("请重新上传，图片不符合规格");
		$("#img2").attr("src","") ;
		return false;
	}
	var fileSize=0;
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
	if (isIE && !obj.files) {          
         var filePath = obj.value;            
         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
         var file = fileSystem.GetFile (filePath);               
         fileSize = file.Size;         
    }
	else {  
         fileSize = obj.files[0].size;     
    } 
	fileSize=Math.round(fileSize/1024*100)/100;
	var objUrl = getObjectURL(obj.files[0]);
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img2").attr("src", objUrl) ;
	}
}
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}
//上传到后台
function uploadFile(){  
	if (window.File && window.FileList) {
	      var fd = new FormData();
	      var files = document.getElementById('file0').files;
	      var files1=document.getElementById('file1').files;
	      var files2=document.getElementById('file2').files;
	      if(files){
	      fd.append("file0", files);// 文件对象 
	      }
	      if(files1){
	    	  fd.append("file1",files1);
	      }
	      if(files2){
	    	  fd.append("file2",files2);
	      }
	      
	      var xhr = new XMLHttpRequest();
	      xhr.open("POST", "<c:url value='/portal/things/updatethings'/>",true);
	      xhr.send(fd);
	    } else {
	      document.getElementById('uploadForm').submit();   //no html5
	    }
}  

$(document).ready(function(){
var oImg=document.getElementById('container');
oImg.style.top=document.getElementById("panel").offsetHeight+'px'; 
var oImg=document.getElementById('footer');
oImg.style.width='100%'; 
});
</script>

</body>
