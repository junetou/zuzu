
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo" %>

<link href="<woo:url value='/static/css/bootstrapValidator.css'/>" rel="stylesheet" type="text/css" media="all">
<link href="<woo:url value='/static/css/fileinput.css'/>" rel="stylesheet" type="text/css">
<script src="<woo:url value='/static/js/jquery-1.11.1.min.js'/>"></script>
<script src="<woo:url value='/static/js/bootstrap.min.js'/>"></script>
<script src="<woo:url value='/static/js/bootstrapValidator.js'/>"></script>
<script src="<woo:url value='/static/js/fileinput.js'/>"></script>
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

<div id="contact" class="contact">
	 <div class="container">
		<h3>注册账号</h3>
	 </div>
	 
     <div class="contact-top">
         <div class="container">
		  <div class="col-md-8 contact-top-right">
            <form:form id="defaultForm" method="post" class="form-horizontal" onsubmit="return check()" action="/register/register" enctype="multipart/form-data" >
                <div class="form-group">
                    <label class="col-lg-3 control-label">公司账户</label>
                    <div class="col-lg-5">
                        <form:input type="text" class="form-control" id="usrname" name="usrname" path="usrname" />
                    </div>
                    <label><button onclick="javascript:usrnameSend()" class="btn btn-info">检测账号是否已注册</button></label>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">密码</label>
                    <div class="col-lg-5">
                        <form:input type="password" class="form-control" id="password" name="password" path="password" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">确认密码</label>
                    <div class="col-lg-5">
                        <form:input type="password" class="form-control" id="repassword" name="repassword" path="repassword" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">公司名称</label>
                    <div class="col-lg-5">
                        <form:input type="text" class="form-control" id="name" name="name" path="name" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">公司地址</label>
                    <div class="col-lg-5">
                        <form:input type="text" class="form-control" id="addr" name="addr" path="addr" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">公司email</label>
                    <div class="col-lg-5">
                        <form:input type="text" class="form-control" id="email" name="email" path="email" />
                    </div>
                    <label>
                      <button class="btn btn-info" onclick="javascript:emailSend()">发送邮箱验证码</button>
                    </label>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">邮箱验证码</label>
                    <div class="col-lg-5">
                        <form:input type="text" class="form-control" id="emailcode" name="emailcode" path="emailcode" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">公司联系人</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="contact" name="contact" path="contact"  />
                    </div>
                </div>
				<div class="form-group">
                    <label class="col-lg-3 control-label">公司联系人电话</label>
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
                    <label class="col-lg-3 control-label">工商营业执照(建议上传，加快审核速度)</label>
                    <a href="javascript:;" class="file">选择文件
                        <input type="file" name="file" id="file" onchange="javascript:getPhotoSizeOne(this)">
                    </a>
                </div>
                <div class="form-group" style="text-align:left;">
				    <img src=""  id="img" style="max-width:200px;max-height:200px;" >
                </div>
                <div class="form-group">
                    <div class="col-lg-9 col-lg-offset-3">
                        <button type="submit" onclick="uploadFile()" class="btn btn-primary">注册</button>
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
						<h4>操作相关事宜:</h4>
						<p style="color:red;">本网站不支持IE浏览器！强烈建议商家使用Google Chrome浏览器!支持最新版的360、Safari，搜狗、QQ浏览器。
						</p>
					</div>
					<div class="contact-top-one">
						<h4>注册相关事宜:</h4>
						<p style="color:red;">感谢贵公司对本网站的支持，贵公司在注册后请耐心等待审核结果，我们会每缝每月的1号、10号、15号，25号对申请的公司进行统一的审核
						</p>
						<p>注册后请对你的账号和公司联系人，联系人电话进行保密！可能会在后期你的账号需要此信息进行操作！</p>
					</div>
					<div class="contact-top-one">
						<h4>联系人电话:</h4>
						<p>+86 82219958</p>
					</div>
					<div class="contact-top-one">
						<h4>紧急联系人电话:</h4>
						<p>15626287636</p>
					</div>
					<div class="contact-top-one">
						<h4>联系E-mail:</h4>
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
                usrname: {
                    message: '登陆账号',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: '只能是数字和字母_.'
                        },
                        stringLength: {
                            min: 6,
                            max: 15,
                            message: '用户名长度必须6到15之间'
                        }
                    }
                },
                password: {
                    message: '请将密码设得尽量复杂，黑客对简单的密码可在1小时内进行暴力破解!!!',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: '只能是英文数字_.'
                        },
                        stringLength: {
                            min: 8,
                            max: 20,
                            message: '密码长度必须在8到20之间'
                        },
                        identical: {//相同
                         field: 'repassword', //需要进行比较的input name值
                         message: '两次密码不一致'
                         },
                         different: {//不能和用户名相同
                         field: 'usrname',//需要进行比较的input name值
                         message: '不能和用户名相同'
                         },
                    }
                },
                repassword: {
                    message: '请将密码设得尽量复杂，黑客对简单的密码可在1小时内进行暴力破解!!!',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: '只能是数字和字母_.'
                        },
                        stringLength: {
                            min: 8,
                            max: 20,
                            message: '用户名长度必须在8到20之间'
                        },
                        identical: {//相同
                         field: 'password', //需要进行比较的input name值
                         message: '两次密码不一致'
                         },
                        different: {//不能和用户名相同
                         field: 'usrname',//需要进行比较的input name值
                         message: '不能和用户名相同'
                          }
                    }
                },
                name: {
                    message: '公司名，不能修改，建议使用中英文，搜索引擎在搜索公司时不支持全数字',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4e00-\u9fa5a-zA-Z0-9_\.]+$/,
                            message: '只能是汉字,数字和字母_.'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名长度必须在6到30之间'
                        }
                    }
                },
                email:{
                    message:'请输入email',
                    validators: {
                      notEmpty: {
                        message: 'Email不能为空'
                      },
                      regexp: {
                            regexp: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                            message: '邮箱格式不对'
                        }
                    }
                },
                emailcode:{
                    validators: {
                      notEmpty: {
                        message: '验证码不能为空'
                      },
                      regexp: {
                            regexp: /^[+-]?\d+(\.\d+)?$/,
                            message: '只能是数字.'
                       }
                    }
                },
               contact:{
                    validators: {
                      notEmpty: {
                        message: '公司联系人不能为空'
                      },
                      regexp: {
                            regexp: /^[\u4e00-\u9fa5]+$/,
                            message: '只能为汉字'
                      },
                    }
                },
                addr:{
                    validators: {
                      notEmpty: {
                        message: '公司联系人不能为空'
                      },
                      regexp: {
                            regexp: /^[\u4e00-\u9fa5a-zA-Z0-9]+$/,
                            message: '只能为汉字,英文和数字'
                      },
                    }
                },
               contactphone:{
                    validators: {
                      notEmpty: {
                        message: '此信息不能为空'
                      },
                      regexp: {
                            regexp: /^[0-9]+$/,
                            message: '手机格式不对'
                      },
                    }
                },
                contactphonecode: {
                    validators: {
                        notEmpty: {
                            message: '验证码不能为空'
                        },
                        regexp: {
                            regexp: /^[+-]?\d+(\.\d+)?$/,
                            message: '只能是数字.'
                        }
                    }
                }
            }
        });
});
</script>
<script type="text/javascript">
function usrnameSend(){
   var usrreg=new RegExp(/^[a-zA-Z0-9_/.]/);
   var urname=$("#usrname").val();
   if(!usrreg.test(urname)){
     alert("账号格式有误，只能输入英文数字和._!");
     return false;
   }
   else{
   var xmlusr;
   if(window.XMLHttpRequest) {
					//针对 Firefox, Chrome, Opera, Safari,IE7,IE8
					xmlusr = new XMLHttpRequest();
	} else if(window.ActiveXObject) {
					//针对IE6,IE5
					xmlusr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlusr.onreadystatechange = function(){//最后才执行的函数
      if(xmlusr.readyState == 4) {
					if(xmlusr.status == 200) {
						var responseText = xmlusr.responseText;
						if(responseText=='true'){
						alert("账号还没注册");
						}
						else{
						alert("账号已注册，请更换")
						}
					}
				}
    }
    var usr=$("#usrname").val();
    xmlusr.open("POST","/register/findUser",true);
    xmlusr.send(usr);
    }
}
</script>
<script type="text/javascript">
function emailSend(){
   var emailreg=new RegExp(/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/);
   var email=$("#email").val();
   if(!emailreg.test(email)){
     alert("email格式错误!");
     return false;
   }
   else{
   var xml;
   if(window.XMLHttpRequest) {
					//针对 Firefox, Chrome, Opera, Safari,IE7,IE8
					xml = new XMLHttpRequest();
	} else if(window.ActiveXObject) {
					//针对IE6,IE5
					xml = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xml.onreadystatechange = function(){//最后才执行的函数
      if(xml.readyState == 4) {
					if(xml.status == 200) {
						var responseText = xml.responseText;
						if(responseText=='true'){
						alert("邮件发送成功，请注意查收!");
						}
						else{
						alert("发送失败")
						}
					}
				}
    }
    var emails=$("#email").val();
    xml.open("POST","/sendEmailCode",true);
    xml.send(emails);
    }
}
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
						alert("发送失败!");
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
   var emailcode=$("#emailcode").val();
   if(phonecode!='')
   {
   if(emailcode!=''){
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
						alert("验证码错误!");
						ajaxpost=false;
						}
					}
				}
    }
    var verify=$("#contactphonecode ").val()+$("#emailcode").val();
    xmlss.open("POST","/checkCode",false);
    xmlss.send(verify);
    return ajaxpost;
    }
    }
}
</script>
<script type="text/javascript">
function getPhotoSizeOne(obj){
photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif' && photoExt !='.png'){
alert("请重新上传，图片不符合规格");
$("#img").attr("src","") ;
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
$("#img").attr("src", objUrl) ;
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
	      var files=document.getElementById('file').files;
	      if(files){
	      fd.append("file", files);// 文件对象 
	      }   
	      var xhr = new XMLHttpRequest();
	      xhr.open("POST", "<c:url value='/register/register'/>",true);
	      xhr.send(fd);
	    }
}  
</script>
