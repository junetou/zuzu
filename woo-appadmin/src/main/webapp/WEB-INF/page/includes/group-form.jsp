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
</style>

<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
		  	<li><a href="#">首页 </a></li>
		  	<li><a href="#">权限设置</a></li>
		  	<li class="active">编辑 ${command.gname }</li>
		</ol>
    </div>
    <!-- .panel-heading -->
    <div class="panel-body">
		
		<form:form id="group-form" method="post">
		
			<div class="btn-group" role="group" style="margin-bottom:20px;">
				<a href="javascript:history.go(-1)" class="btn btn-outline btn-info">返回</a>
		   		<button type="submit" class="btn btn-outline btn-info">保 存</button>
			</div>
			
            <div class="form-group">
                <label>名称<sup>*</sup></label>
				<form:input cssClass="form-control" path="gname"/>
				<p class="help-block">30个字以内</p> 
            </div>

			<!-- 系统管理 -->
			<div class="alert alert-info adminMenus">
				<label class="ui-label" for="adminMenus"><strong><spring:message code="menu.admin"/></strong></label><br> 
				<c:forEach var="menu" items="${adminMenus}" varStatus="indexs"> 
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
								<form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div>
			
		    <div class="alert alert-info thingsMenus">
				<label class="ui-label" for="thingsMenus"><strong><spring:message code="menu.things"/></strong></label>
				<c:forEach var="menu" items="${thingsMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="THINGS_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div>
			
			<div class="alert alert-info needsMenus">
				<label class="ui-label" for="needsMenus"><strong><spring:message code="menu.needs"/></strong></label>
				<c:forEach var="menu" items="${needsMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="NEEDS_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div>
			
			<div class="alert alert-info mycompanyMenus">
				<label class="ui-label" for="TRADEMenus"><strong><spring:message code="menu.trade"/></strong></label>
				<c:forEach var="menu" items="${tradeMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="TRADE_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div>
			
			<form:hidden path="id"/>
			<div class="btn-group" role="group" style="margin-bottom:20px;">
                <button type="submit" class="btn btn-outline btn-info">保 存</button>
                <a href="javascript:history.go(-1)" class="btn btn-outline btn-info">返回</a>
            </div>
			
		</form:form>
		
    </div>
    <!-- .panel-body -->
</div>
<script type="text/javascript">
$(document).ready(function() {
	
    $('#group-form').bootstrapValidator({
        message: '此值无效',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function(form) {
        	
        	var hasMenus = $('.adminMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.adminMenus').find('input[name=roles]').val('admin');
        	}
        	
        	hasMenus = $('.thingsMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.thingsMenus').find('input[name=roles]').val('things');
        	}
            
        	hasMenus = $('.needsMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.needsMenus').find('input[name=roles]').val('needs');
        	}
        	
        	hasMenus = $('.tradeMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.tradeMenus').find('input[name=roles]').val('trade');
        	}

        	var id = $("#id").val();
        	var methet = "";
        	if(id != null && id != "") {
        		methet = "update";
        	} else {
        		methet = "add";
        	}
         	var datas = $("#group-form").toJson();
         	$.post("<c:url value='/portal/admin/system-role/"+methet+"'/>", datas, function(resp){
         		if (resp.r == 1){
         			$.dialog({
         				title: "提示",
         			    content: "保存成功！"
         			});
        			setTimeout(function(){
        				self.location=document.referrer;
        			}, 1500); 
        		} else {
        			$.dialog({
         				title: "提示",
         			    content: "操作失败，" + resp.msgs
         			});
        		}
         	}, "json");
         	$('#register-form').slideUp('fast');
        },
        fields: {
        	gname: {
                message: '名称无效',
                validators: {
                    notEmpty: {
                        message: '名称是必需的，并且不能是空的'
                    },
                    stringLength: {
                        max: 30,
                        message: '名称必须小于30个字符长'
                    }
                }
            }
        }
    });
});
</script>
