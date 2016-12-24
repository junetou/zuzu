<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<style>
.file {
position: relative;
display: inline-block;
background: #D0EEFF;
border: 1px solid #99D3F5;
border-radius: 4px;
padding: 4px 12px;
overflow: hidden;
color: #1E88C7;
text-decoration: none;
text-indent: 0;
line-height: 20px;
}
.file input {
position: absolute;
font-size: 100px;
right: 0;
top: 0;
opacity: 0;
}
.file:hover {
background: #AADFFD;
border-color: #78C3F3;
color: #004974;
text-decoration: none;
}
.img-rounded {
  border-radius: 10px;
  max-height: 300px;
  max-width: 200px;
}
</style>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<div class="header">
	 <div class="container">
		 <div class="social">	
		 </div>
		 <div class="details">
			<ul>
				<li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>756332784@qq.com</li>
				<li><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>13826589635</li>
			 </ul>
		 </div>
	 </div>
</div>

<div id="home">
	 <div id="to-top" class="container">
		 <div class="top-header">
		     <div class="logo">
					<h5><button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#mymodal">修改密码</button></h5>
			 </div>
			 <div class="top-nav">
				 <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s2">
					 <h3>菜单</h3>
					    <a class="scroll" href="<c:url value='/index'/>">主页</a>
						<a class="scroll" href="<c:url value='/commodity'/>">产品信息</a>
						<a class="scroll" href="<c:url value='/purchase'/>">采购信息</a>
						<woo:permission operationType="VIEW" roleType="ROLE_COMPANY">
						<a class="scroll" href="<c:url value='/publish'/>">发布信息</a>
						</woo:permission>
						<a class="scroll" href="<c:url value='/own'/>">我的</a>
				 </nav>	
			 </div>
			 <div class="main buttonset" style="text-align:right;">	
					<button id="showRightPush" ><img src="<woo:url value='/static/images/menu-icon.png'/>" /></button>
		     </div>
			 <div class="banner-info">
			     </br>
				 </br>
				 </br>
			     <h2><span>壹平台</span></h2>
			     <p>致力于服务广大商家</p>
			     <img src="<woo:url value='/static/userpicture/user1.jpg'/>" style="max-width:200px;max-height:200px; " alt=""/>
		 </div>
		 </div>
	 </div>
</div>

<div class="container" style="text-align:center;">
	 <ol class="breadcrumb">
		 <li><a href="index.html">我的</a></li>
		 <li class="active">修改个人资料</li>
	 </ol>
	 <div class="registration">
		 <div class="registration_left">
			 <div class="registration_form">
				<form action="/CoLtd/edit/update" enctype="multipart/form-data"  method="post" class="form-horizontal" onsubmit="return reg()">
					<div>
						<label>
						    <p>公司联系电话</p>
							<input placeholder="公司联系电话" type="text" id="phone" name="phone" value="${phone }" class="form-control" tabindex="1">
						</label>
					</div>
					<div>
						<label>
						    <p>公司email</p>
							<input placeholder="公司email" type="text" id="email" name="email" value="${email }" class="form-control" tabindex="2">
						</label>
					</div>
					<div>
						<label>
						    <p>公司简介</p>
							<textarea style="min-width:150px; min-height:150px; max-width:600px; max-height:200px;" id="infomation" name="infomation" class="form-control" >
							${infomation }
							</textarea>
						</label>
					</div>
					<div>
						<h2 style="color:#FF8C69;">公司图片1</h2>
                        <a href="javascript:;" class="file">选择文件
                        <input type="file" name="file1" id="file1" onchange="javascript:getPhotoSizeOne(this)">
                        </a>
                        </br>
                        <c:if test="${com1 != 'null' }">
						<img src="<woo:url value='/static/companyhead/${com1 }'/>" id="img1" style="max-width:200px;max-height:200px; " >
						</c:if>
						<c:if test="${com1 == 'null' }">
						<img src="" id="img1" style="max-width:200px;max-height:200px; " >
						</c:if>
						</br>
						</ul>
					</div>
					<div>
						<h2 style="color:#FF8C69;">公司图片2</h2>
                        <a href="javascript:;" class="file">选择文件
                        <input type="file" name="file2" id="file2" onchange="javascript:getPhotoSizeTwo(this)">
                        </a>
                        </br>
						<c:if test="${com2 != 'null' }">
						<img src="<woo:url value='/static/companyhead/${com2 }'/>" id="img2" style="max-width:200px;max-height:200px; " >
						</c:if>
						<c:if test="${com2 == 'null' }">
						<img src="" id="img2" style="max-width:200px;max-height:200px; " >
						</c:if>
						</br>
						</ul>
					</div>
					<div>
						<h2 style="color:#FF8C69;">公司图片3</h2>
                        <a href="javascript:;" class="file">选择文件
                        <input type="file" name="file3" id="file3" onchange="javascript:getPhotoSizeThree(this)">
                        </a>
                        </br>
						<c:if test="${com3 != 'null' }">
						<img src="<woo:url value='/static/companyhead/${com3 }'/>" id="img3" style="max-width:200px;max-height:200px; " >
						</c:if>
						<c:if test="${com3 == 'null' }">
						<img src="" id="img3" style="max-width:200px;max-height:200px; " >
						</c:if>
						</br>
						</ul>
					</div>
					<div>
					    </br>
						<input type="submit" class="btn btn-info" onclick="uploadFile()" value="修改" >
					</div>
				</form>
			 </div>
		 </div>
		</div>	 
</div>

<div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	<form action="/CoLtd/updatePasswd" id="reset-form" method="post" onsubmit="return check()">
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel">重置密码</h4>
		      	</div>
		      	<div class="modal-body">
		      		<div class="form-group">
			            <label for="recipient-name" class="control-label">登陆密码<sup>*</sup></label>
			            <input type="password" class="form-control" id="password" name="password">
			            <input type="password" class="form-control" id="repassword" name="repassword">
			            <p class="help-block">密码是必需的，并且不能是空的</p> 
		          	</div>
		      	</div>
		      	<div class="modal-footer">
		      		<input type="hidden" name="userId" id="userId" value="">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        	<button type="submit" class="btn btn-primary">提交</button>
		      	</div>
	      	</form>
	    </div>
  	</div>
</div>

<script>
function reg(){
   var emailreg=new RegExp(/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/);
   var phonereg=new RegExp(/^((\d3)|(\d{3}\-))?13[456789]\d{8}|15[456789]\d{8}/);
   var infomationreg=new RegExp(/^[\u4e00-\u9fa5\sa-zA-Z0-9_，。！!.,？、?]+$/);
   var phone=$("#phone").val();
   var email=$("#email").val();
   var infomation=$("#infomation").val();
   if(!emailreg.test(email)){
     alert("邮箱格式不对!");
     return false;
   }
   if(!phonereg.test(phone)){
     alert("电话格式不对！");
     return false;
   }
   if(!infomationreg.test(infomation)){
     alert("不能出现冒号等特殊字符！");
     return false;
   }
    return true;
}
function getPhotoSizeOne(obj){
photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif' && photoExt !='.png'){
alert("请重新上传，图片不符合规格");
$("#img1").attr("src","") ;
return false;
}
if(photoExt =='.png'){
alert("不建议上传Png格式，图片中的透明部分将用黑色代替");
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
function getPhotoSizeTwo(obj){
photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif' && photoExt !='.png' ){
alert("请重新上传，图片不符合规格");
$("#img2").attr("src","") ;
return false;
}
if(photoExt =='.png'){
alert("不建议上传Png格式，图片中的透明部分将用黑色代替");
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
function getPhotoSizeThree(obj){
photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif' && photoExt !='.png' ){
alert("请重新上传，图片不符合规格");
$("#img3").attr("src","") ;
return false;
}
if(photoExt =='.png'){
alert("不建议上传Png格式，图片中的透明部分将用黑色代替");
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
$("#img3").attr("src", objUrl) ;
}
}
function getObjectURL(file) {
var url = null ; 
if (window.createObjectURL!=undefined) {
url = window.createObjectURL(file) ;
} 
else if (window.URL!=undefined) {
url = window.URL.createObjectURL(file) ;
}
else if (window.webkitURL!=undefined) {
url = window.webkitURL.createObjectURL(file) ;
}
return url ;
}
function uploadFile(){  
	if (window.File && window.FileList) {
	      var fd = new FormData();
	      var files1=document.getElementById('file1').files;
	      var files2=document.getElementById('file2').files;
	      var files3=document.getElementById('file3').files;
	      if(files1){
	      fd.append("file1", files1);// 文件对象 
	      }
	      if(files2){
	    	  fd.append("file2",files2);
	      }
	      if(files3){
	    	  fd.append("file3",files3);
	      }
	      
	      var xhr = new XMLHttpRequest();
	      xhr.open("POST", "<c:url value='/CoLtd/edit/update'/>",true);
	      xhr.send(fd);
	    }
}  
</script>

<script type="text/javascript">
function check(){
   var checks=false;
   var pass=$("#password").val();
   var repass=$("#repassword").val();
   if(pass!=repass){
     alert("两次密码不同!");
   }
   else{
     check=true;
   }
    return checks;
}
</script>
