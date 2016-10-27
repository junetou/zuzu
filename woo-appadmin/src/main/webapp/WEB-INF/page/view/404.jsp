<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="icon" href="<woo:url value='/static/images/ico.ico'/>" type="image/x-icon">
	<title>用一天</title>
	<link href="<woo:url value='/static/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/metisMenu.min.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/timeline.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/sb-admin-2.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/morris.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
	<link href="<woo:url value='/static/bootstrap/css/style.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/bootstrap-multiselect.css'/>" rel="stylesheet" type="text/css">
	<link href="<woo:url value='/static/bootstrap/css/fileinput.css'/>" rel="stylesheet" type="text/css">
	<link href="<woo:url value='/static/bootstrap/css/jquery-confirm.css'/>" rel="stylesheet" type="text/css"/>
	<script src="<woo:url value='/static/bootstrap/jquery/jquery.min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/jquery/jquery.form.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/bootstrap.min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/bootstrap3-validation.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/bootstrapValidator.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/bootstrap-multiselect.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/metisMenu.min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/raphael-min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/morris.min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/sb-admin-2.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/handlebars-1.0.0.beta.6.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/fileinput.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/fileinput_locale_zh.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/common.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/jquery-confirm.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/jquery.autocomplete.js'/>" type="text/javascript"></script>
</head>


<img id="img" src="<woo:url value='/static/images/404.jpg'/>">

<script>
$(document).ready(function(){
	var oImg=document.getElementById('img');
	oImg.style.width='100%';
	oImg.style.height='100%';
});
</script>