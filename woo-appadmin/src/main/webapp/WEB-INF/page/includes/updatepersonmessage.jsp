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
   bottom: 0; /* 关键 */  
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
     <li>本公司产品</li>
     <li>修改产品</li>
     <li>
     <a href="javascript:void(0);" data-id="${id}" class="btnReset" data-url="/person/resetpass">
		重置密码
	 </a>
	 </li>
     <li><a href="javascript:history.go(-1)">返回</a></li>
     </ol>
   </div>
   <woo:permission operationType="THINGS_EDIT" roleType="ROLE_THINGS">
   <div  id="main">
       <form action="<c:url value='/portal/person/update'/>" method="post" enctype="multipart/form-data" >
        <input type="submit" id="post" name="post" class="btn btn-default btn-sm" onclick="uploadFile()"  value="我要提交"/>	
        <div class="row">
        
        <div class="col-md-6">
        <div class="form-gourp" style="position: absolute; top:-200px;" >
           <label>产品id<sup>*</sup></label>
           <input id="userid" type="text" name="userid" value="${id}" readOnly="true"  required />
        </div>
        <div class="form-gourp">
           <label>用户名<sup>*</sup></label>
           <input id="username" type="text" name="username" value="${name }" class="form-control" required />
        </div>
        <div class="form-gourp">
           <label>email</label>
           <input id="email" type="text" name="email" value="${email}"  class="form-control" required />
        </div>
        <div class="form-gourp">
          <label>个人地址</label>
          <input id="addr" type="text" name="addr" value="${addr }"  class="form-control"  required />
       </div>
       <div class="form-gourp">
          <label>qq</label>
          <input id="qq" type="text" name="qq" value="${qq }"  class="form-control" required />
       </div> 
       </div>
       <div class="col-md-6">
       <div class="form-gourp">
          <label>联系电话<sup>*</sup></label>
          <input id="phone" type="text" name="phone" value="${phone }"  class="form-control" required />
       </div>
       <div class="form-gourp">
          <label>微信<sup>*</sup></label>
          <input id="wechat" type="text" name="wechat" value="${wechat }"  class="form-control" required />
       </div>
       </div>
        <div class="col-md-6">
          <div class="form-group">
          <label>上传头像</label>
          <input type="file"  name="file0" id="file0" class="file" onchange="getPhotoSize0(this,0)" multiple="true" />
          </div>
         </div>
         <div style="height:150px;">
         
         </div>
                 
        </div> 

       </form>        
   </div>
   <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>    </ul>
  </div>
   </woo:permission>
   

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  	<div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	<form id="reset-form" method="post">
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel">重置密码</h4>
		      	</div>
		      	<div class="modal-body">
		      		<div class="form-group">
			            <label for="recipient-name" class="control-label">登陆密码<sup>*</sup></label>
			            <input type="text" class="form-control" id="password" name="password">
			            <p class="help-block">密码是必需的，并且不能是空的，必需包含数字和字母</p> 
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

<script type="text/javascript">
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
	      if(files){
	      fd.append("file0", files);// 文件对象 
	      }	      
	      var xhr = new XMLHttpRequest();
	      xhr.open("POST", "<c:url value='/portal/person/update'/>",true);
	      xhr.send(fd);
	    }
}  
$(document).ready(function(){
	var oImg=document.getElementById('main');
	oImg.style.height='100%';
	var oImg=document.getElementById('footer');
	oImg.style.width='100%'; 
});
</script>

