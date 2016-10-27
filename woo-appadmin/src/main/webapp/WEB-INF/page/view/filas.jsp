<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>


<img id="img" src="<woo:url value='/static/images/4041.jpg'/>">

<script>
  $(document).ready(function(){
		var oImg=document.getElementById("img");
		oImg.style.width='100%';
		oImg.style.height=document.documentElement.clientHeight+'px';   
  });
</script>