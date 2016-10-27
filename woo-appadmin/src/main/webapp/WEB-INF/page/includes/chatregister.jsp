<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*,java.net.*" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="icon" href="<woo:url value='/static/images/ico.ico'/>" type="image/x-icon">
	<title>聊天功能注册</title>
	<link href="<woo:url value='/static/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<woo:url value='/static/bootstrap/css/metisMenu.min.css'/>" rel="stylesheet">
    <link href="<woo:url value='/static/bootstrap/css/sb-admin-2.css'/>" rel="stylesheet">
    <link href="<woo:url value='/static/bootstrap/cssfont-awesome.min.css'/>" rel="stylesheet" type="text/css">
    <script src="<woo:url value='/static/sdk/strophe.js'/>"></script>
    <script src="<woo:url value='/static/sdk/easemob.im-1.1.1.js'/>" ></script>
    <script src="<woo:url value='/static/sdk/easemob.im.shim.js'/>" ></script>
    <script src="<woo:url value='/static/js/easemob.im.config.js'/>" ></script>
    <script src="<woo:url value='/static/js/jquery-1.11.1.js'/>" ></script>
    <script src="<woo:url value='/static/js/bootstrap.js'/>" ></script>
    <link rel="stylesheet" href="<woo:url value='/static/css/bootstrap.css'/>" />
    <link rel="stylesheet" href="<woo:url value='/static/css/webim.css'/>" />
<style>
body
{
    background-image:url('<woo:url value="/static/img/qiufeng.jpg"/>');
	background-repeat:no-repeat;
}
</style>
</head>


<body>
	<div class="container" style="width:auto; height:auto; top:1500px;" >
        <div class="row" >
            <div class="col-md-4 col-md-offset-4" >
                <div class="login-panel panel panel-default" style="background-color:#FFFFCC; " >
                    <div class="panel-heading"   >
                        <h3 class="panel-title">注册</h3>
                    </div>     
                </div>
            </div>
        </div>      
    </div>       
    <!-- 注册操作界面 -->
    <div id="regist-div-modal"  role="dialog" style="display:none;"
        aria-hidden="true" data-backdrop="static">
        <div >
            <h3>用户注册</h3>
        </div>
        <div >
            <div id="regist_div" style="overflow-y: auto">
                <table>
                    <tr>
                        <td width="10%"><label>用户名:</label> 
                        <input type="text" value="${usename }" id="regist_username" tabindex="1" /> 
                        <label>密码:</label>
                        <input type="password" value="123456" id="regist_password" tabindex="2" />
                        <label>昵称:</label> 
                        <input type="text" value="${displayname }"
                        id="regist_nickname" tabindex="3" />
                        </td>
                    </tr>
                </table>
                <a href="<c:url value='/portal/chat/registersuccess'/>" style="color:#000000" id="abc" ><b><i class="glyphicon glyphicon-cog">注册成功</i></b></a>
            </div>
        </div>
        <div >
        </div>
    </div>
    <button id="confirm-regist-confirmButton" class="btn btn-primary"
                onclick="regist()" >注册
    </button>
</body>
</html>

<script type="text/javascript">
var curUserId = null;
var curChatUserId = null;
var conn = null;
var curRoomId = null;
var curChatRoomId = null;
var msgCardDivId = "chat01";
var talkToDivId = "talkTo";
var talkInputId = "talkInputId";
var bothRoster = [];
var toRoster = [];
var maxWidth = 200;
var groupFlagMark = "groupchat";
var chatRoomMark = "chatroom";
var groupQuering = false;
var textSending = false;
var time = 0;
var flashFilename = '';
var audioDom = [];
var picshim;
var audioshim;
var fileshim;
var friendsSub = {};
var PAGELIMIT = 8;
var pageLimitKey = new Date().getTime();


var encode = function ( str ) {
	if ( !str || str.length === 0 ) return "";
	var s = '';
	s = str.replace(/&amp;/g, "&");
	s = s.replace(/<(?=[^o][^)])/g, "&lt;");
	s = s.replace(/>/g, "&gt;");
	//s = s.replace(/\'/g, "&#39;");
	s = s.replace(/\"/g, "&quot;");
	s = s.replace(/\n/g, "<br>");
	return s;
};

window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
var getLoginInfo = function () {
	return {
		isLogin : false
	};
};

//easemobwebim-sdk注册回调函数列表
$(document).ready(function() {
	if ( Easemob.im.Helper.getIEVersion && Easemob.im.Helper.getIEVersion < 10 ) {
		$('#em-cr').remove();
	}	
	conn = new Easemob.im.Connection({
		multiResources: Easemob.im.config.multiResources,
		https : Easemob.im.config.https,
		url: Easemob.im.config.xmppURL
	});
	//初始化连接
	conn.listen({
		//当连接成功时的回调方法
		onOpened : function() {
			handleOpen(conn);
		},
	});

	$('#confirm-block-div-modal').on('hidden.bs.modal', function(e) {
	});
	$('#option-room-div-modal').on('hidden.bs.modal', function(e) {
	});
	$('#notice-block-div').on('hidden.bs.modal', function(e) {
	});
	$('#regist-div-modall').on('hidden.bs.modal', function(e) {
	});
	//在 密码输入框时的回车登录
});



//注册新用户操作方法
var regist = function() {
	$(window).unbind('beforeunload');
	window.onbeforeunload = null;
	var user = '${usename}';
	var pass = "123456";
	var nickname = '${displayname}';
	if (user == '' || pass == '' || nickname == '') {
		return;
	}
	var options = {
		username : user,
		password : pass,
		nickname : nickname,
		appKey : Easemob.im.config.appkey,
		success : function(result) {
			alert("注册成功!");
			$('#regist-div-modal').modal('hide');
		},
		error : function(e) {
			alert(e.error);
		},
		apiUrl : Easemob.im.config.apiURL
	};
	Easemob.im.Helper.registerUser(options);
	alert("dd");
	window.location.href=document.getElementById("abc").href;
};

</script>
