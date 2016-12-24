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
		<h3>找回密码</h3>
	 </div>
	 
     <div class="contact-top">
         <div class="container">
		  <div class="col-md-8 contact-top-right">
            <form id="defaultForm" method="post" class="form-horizontal" action="/findPass/check" >
                <div class="form-group">
                    <label class="col-lg-3 control-label">公司账号</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="username" name="username" value="" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">公司联系人姓名</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="name" name="name" value="" />
                    </div>
                </div>
				<div class="form-group">
                    <label class="col-lg-3 control-label">公司联系人电话</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="contactphone" name="contactphone" value="" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">账号创建的月份(假如账号创建在2016-01-28日，请填01)</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="createtimemonth" name="createtimemonth" value="" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">账号创建的天数(假如账号创建在2016-01-28日，请填28)</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="createtimeday" name="createtimeday" value="" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-9 col-lg-offset-3">
                        <button type="submit" class="btn btn-primary" >提交</button>
                    </div>
                </div>
            </form>
		 </div>
		 <div class="col-md-4 contact-top-left">
					<div class="contact-top-one">
						<h2>声明</h2>
						</br>
					</div>
					<div class="contact-top-one">
						<h6 style="color:red;">
						 我们会在找回密码期间对公司联系人进行确认，<span style="color:red;">公司联系人</span>请填写注册期间的联系人，请保持好电话的畅通！
						</h6>
						<h6>
						 确认后，我们会发新的密码至你的邮箱，请留意你的邮箱信息！
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
                username: {
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
                createtimemonth: {
                    validators: {
                        notEmpty: {
                            message: '月数不能为空'
                        },
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: '只能是数字.'
                        },
                        stringLength: {
                            min: 2,
                            max: 2,
                            message: '请填2位数'
                        }
                    }
                },
                createtimeday: {
                    validators: {
                        notEmpty: {
                            message: '天数不能为空'
                        },
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: '只能是数字.'
                        },
                        stringLength: {
                            min: 2,
                            max: 2,
                            message: '请填2位数'
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
