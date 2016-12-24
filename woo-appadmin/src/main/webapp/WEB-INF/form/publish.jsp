<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo" %>

<link href="<woo:url value='/static/css/bootstrapValidator.css'/>" rel="stylesheet" type="text/css" media="all">
<script src="<woo:url value='/static/js/jquery-1.11.1.min.js'/>"></script>
<script src="<woo:url value='/static/js/bootstrap.min.js'/>"></script>
<script src="<woo:url value='/static/js/bootstrapValidator.js'/>"></script>

<div id="contact" class="contact">
	 <div class="container">
		<h3>信息发布</h3>
	 </div>
	 
     <div class="contact-top">
         <div class="container">
		  <div class="col-md-8 contact-top-right">
            <form:form id="defaultForm" method="post" class="form-horizontal" action="/publish/add"  enctype="multipart/form-data" >
                <div class="form-group">
                    <label class="col-lg-3 control-label">产品名</label>
                    <div class="col-lg-5">
                        <form:input type="text" class="form-control" id="name" name="name" path="name" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">产品价格</label>
                    <div class="col-lg-5">
                        <form:input type="text" class="form-control" id="price" name="price" path="price" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">产品联系人</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="contact" name="contact" path="contact"  />
                    </div>
                </div>
                
				<div class="form-group">
                    <label class="col-lg-3 control-label">产品联系人电话</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="contactphone" name="contactphone" path="contactphone"  />
                    </div>
                    <label><button onclick="javascript:phoneSend()" class="btn btn-info">发送短信验证码</button></label>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">手机验证码</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="contactphonecode" name="contactphonecode" path="contactphonecode"  />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">产品生产地址</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="addr" name="addr" path="contact"  />
                    </div>
                </div>
                <div class="form-group">
	            <label for="remark" class="ui-label">商品信息:</label>
	            <form:textarea class="form-control"  style="max-width:600px; max-height:200px;"  id="textarea" name="textarea" path="textarea"/>
	            </div>
	            <div class="form-group">
		                <label>发布模式</label>
		                <form:select path="group" id="group" name="group" style="max-width:600px; " class="form-control">
		                    <option value=""></option>
						 	<option value="0">发布商品信息</option>
						 	<option value="1">发布采购信息</option>
						</form:select>
		        </div>
				<ul  class="container" >
						<h6 style="color:#FF8C69;">选择产品图片1</h6>
                        <a href="javascript:;" class="file">选择文件
                        <input type="file" name="file1" id="file1" onchange="javascript:getPhotoSizeOne(this)">
                        </a>
						<img src="" id="img1"  class="img-rounded " >
						</ul>
						<ul  class="container" >
						<h6 style="color:#FF8C69;">选择产品图片2</h6>
                        <a href="javascript:;" class="file">选择文件
                        <input type="file" name="file2" id="file2" onchange="javascript:getPhotoSizeTwo(this)">
                        </a>
						<img src="" id="img2"  class="img-rounded " >
						</ul>
						<ul  class="container" >
						<h6 style="color:#FF8C69;">选择产品图片3</h6>
                        <a href="javascript:;" class="file">选择文件
                        <input type="file" name="file3" id="file3" onchange="javascript:getPhotoSizeThree(this)">
                        </a>
						<img src="" id="img3"  class="img-rounded " >
				</ul>
                <div class="form-group">
                    <div class="col-lg-9 col-lg-offset-3">
                        <button type="submit" class="btn btn-primary" onclick="uploadFile()" >发布</button>
                    </div>
                </div>
            </form:form>
		 </div>
		 <div class="col-md-4 contact-top-left">
					<div class="contact-top-one">
						<h2>声明</h2>
						</br>
					</div>
					<div class="contact-top-one">
						<h6 style="color:red;">
						发布商品信息或采购信息将扣取0.1元的手续费(其中0.05为短信费剩下的0.05为服务器发布费用)！请谨慎发布信息！
						</h6>
						<h6 style="color:red;">
						请注意产品照片的第一张照片，在商品或采购栏中默认只显示第一张照片，如果第一张照片没有，我们会用默认照片代替第一张照片，即使有其余照片也不会显示，但用户查看详细信息时会显示上传的所有照片.
						</h6>
					</div>
					<div class="contact-top-one">
						<h2>联系我们</h2>
						</br>
					</div>
					<div class="contact-top-one">
						<h4>地址</h4>
						<h6>
						<span>广东省佛山市禅城区</span>
						张槎xx工业区xx号.
						</h6>
					</div>
					<div class="contact-top-one">
						<h4>电话:</h4>
						<p>固话: +86 822xxxxx 
						<span>手机: +86 136xxxxxxxx</span>
						</p>
					</div>
					<div class="contact-top-one">
						<h4>E-mail:</h4>
						<p>756332784@163.com</p>
					</div>
	      </div>
	      <div class="clearfix"> </div>
       </div>
</div>
       
<script type="text/javascript">
$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4e00-\u9fa5a-zA-Z0-9_\.]+$/,
                            message: '只能是汉字,数字和字母_.'
                        },
                        stringLength: {
                            min: 2,
                            max: 30,
                            message: '用户名长度必须在2到30之间'
                        }
                    }
                },
                textarea:{
                    message:'请输入相应的文字',
                    validators: {
                      notEmpty: {
                        message: '此信息不能为空'
                      },
                      regexp: {
                            regexp: /^[\u4e00-\u9fa5\sa-zA-Z0-9_.，。！!,？、?]+$/,
                            message: '只能是汉字,数字和字母_.'
                        },
                      stringLength: {
                            min: 2,
                            max: 300,
                            message: '用户名长度必须在2字以上'
                      }
                    }
                },
                group:{
                    validators: {
                      notEmpty: {
                        message: '此信息不能为空'
                      }
                    }
                },
                contactphonecode:{
                    validators: {
                      notEmpty: {
                        message: '此信息不能为空'
                      }
                    }
                },
               addr:{
                    validators: {
                      notEmpty: {
                        message: '此信息不能为空'
                      },
                      regexp: {
                            regexp: /^[\u4e00-\u9fa5a-zA-Z0-9_\.]+$/,
                            message: '只能是汉字,数字和字母_.'
                      },
                    }
                },
                price: {
                    message:'价格无效',
                    validators: {
                        notEmpty: {
                            message: '价格不能为空'
                        },
                        regexp: {
                            regexp: /^[+-]?\d+(\.\d+)?$/,
                            message: '只能是数字.'
                        }
                    }
                },
                contact: {
                    message: '联系人名字',
                    validators: {
                        notEmpty: {
                            message: '联系人名字不能为空'
                        },
                        stringLength: {
                            min: 2,
                            message: '用户名长度必须在2字以上'
                        }
                    }
                },
				contactphone: {
                    message: '联系人电话',
                    validators: {
                        notEmpty: {
                            message: '联系人名字不能为空'
                        },
                        stringLength: {
                            min: 11,
							max: 11,
                            message: '手机长度为11个数字'
                        },
						regexp: {
						 regexp: /^((\d3)|(\d{3}\-))?13[456789]\d{8}|15[456789]\d{8}/,
                         message: '只能是数字.'
						}
                    }
                }
            }
        });
});
</script>
<script type="text/javascript">
function phoneSend(){
   var phonereg=new RegExp(/^[0-9]+$/);
   var phone=$("#contactphone").val();
   if(!phonereg.test(phone)){
     alert("手机格式格式错误!");
     return false;
   }
   else{
   var xmls;
   if(window.XMLHttpRequest) {
					//针对 Firefox, Chrome, Opera, Safari,IE7,IE8
					xmls = new XMLHttpRequest();
	} else if(window.ActiveXObject) {
					//针对IE6,IE5
					xmls = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmls.onreadystatechange = function(){//最后才执行的函数
      if(xmls.readyState == 4) {
					if(xmls.status == 200) {
						var responseText = xmls.responseText;
						if(responseText=='true'){
						alert("短信发送成功，请注意查收");
						}
						else{
						alert("发送失败");
						}
					}
				}
    }
    var phones=$("#contactphone").val();
    xmls.open("POST","/sendPhoneCode",true);
    xmls.send(phones);
    }
}
</script>
<script type="text/javascript">
var ajaxpost=false;
function check(){

   var phonecode=$("#contactphonecode").val();
   if(phonecode!='')
   {
   var xmlss;
   if(window.XMLHttpRequest) {
					//针对 Firefox, Chrome, Opera, Safari,IE7,IE8
					xmlss = new XMLHttpRequest();
	} else if(window.ActiveXObject) {
					//针对IE6,IE5
					xmlss = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlss.onreadystatechange = function(){//最后才执行的函数
      if(xmlss.readyState == 4) {
					if(xmlss.status == 200) {
						var responseText = xmlss.responseText;
						if(responseText=='true'){
						ajaxpost=true;
						}
						else{
						alert("验证码错误");
						ajaxpost=false;
						}
					}
				}
    }
    var verify=$("#contactphonecode").val();
    xmlss.open("POST","/publish/checkCode",false);
    xmlss.send(verify);
    return ajaxpost;
    }
}
</script>
<script>
function getPhotoSizeOne(obj){
photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif' && photoExt !='.png' ){
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
	      xhr.open("POST", "<c:url value='/add'/>",true);
	      xhr.send(fd);
	    }
}  
</script>