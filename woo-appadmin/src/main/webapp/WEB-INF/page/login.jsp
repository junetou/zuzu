<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="icon" href="<woo:url value='/static/images/ico.ico'/>" type="image/x-icon">
	<title>欢迎登陆</title>
	<link href="<woo:url value='/static/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<woo:url value='/static/bootstrap/css/metisMenu.min.css'/>" rel="stylesheet">
    <link href="<woo:url value='/static/bootstrap/css/sb-admin-2.css'/>" rel="stylesheet">
    <link href="<woo:url value='/static/bootstrap/cssfont-awesome.min.css'/>" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">请登录</h3>
                    </div>
                    <div class="panel-body">
                        <form action="<c:url value='/secure/check'/>" method="post">
                            <fieldset>
                                <div class="form-group">
                                	<input id="usrname" type="text" class="form-control" name="usrname" placeholder="Username" value="${username }" required autofocus/>
                                </div>
                                <div class="form-group">
                                    <input id="password" type="password" class="form-control" name="password" placeholder="Password" required>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" ${checkusername } value="remember">记住账号
                                    </label> 
                                </div> 
                                <div class="form-group" style="color:#F00;">
									<span id="message">${SPRING_SECURITY_LAST_EXCEPTION }</span> 
								</div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" tabindex="3" value="登录"/>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>