package org.andy.work.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.controller.admin.detail.LoginDetail;
import org.andy.work.admin.helper.CookieHelper;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.security.HmacPasswordEncoderImpl;
import org.andy.work.admin.weixin.OAuthInfo;
import org.andy.work.admin.weixin.UserInfo;
import org.andy.work.admin.weixin.WeixinUtil;
import org.andy.work.constant.WebConstants;
import org.andy.work.admin.weixin.Constants;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.appserver.model.impl.UserGroup;
import org.andy.work.appserver.service.IUserMaintenanceService;
import org.andy.work.utils.StringUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserLoginController {
	
	@Resource
	private CookieHelper cookieHelper;
	
	@Resource
	private UserHelper userHelper;

	@Resource 
	private IUserMaintenanceService usermain;
	
	private String user="";
	private String usesname="";
	private String ps="";
	
	@RequestMapping(value="/secure/login", method=RequestMethod.GET)
	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userNameValue = this.cookieHelper.getCookieValue(request, WebConstants.USERNAME_COOKIE);
		try {
			if (StringUtil.hasValue(userNameValue)) {
				JSONObject json = new JSONObject(userNameValue);
				request.setAttribute("username", json.get("username"));
				request.setAttribute("checkusername", json.get("checkusername"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		String code=request.getParameter("code");
		String scope=request.getParameter("scope");
		
		
		if(null!=code && !code.equals("")){
			OAuthInfo oa=WeixinUtil.getOAuthOpenId(Constants.appId,Constants.appSecret,code);
			String name=new String("");
			String pass=new String("");
			if(oa!=null){
			String test=oa.getOpenId();
			IUser users = this.userHelper.findUserByUsername(test);
			if(users == null){
				UserInfo info=WeixinUtil.getUserInfo(oa.getAccessToken(), oa.getOpenId());
				User use=new User();
				Date date=new Date();
				HmacPasswordEncoderImpl pw=new HmacPasswordEncoderImpl();
				UserGroup usrgroup=new UserGroup();
				usrgroup.setId(2);
				usrgroup.setName("普通用户");
				usrgroup.setPermission("THINGS_VIEW,THINGS_EDIT,THINGS_DELETE,NEEDS_VIEW,NEEDS_EDIT,NEEDS_DELETE,TRADE_VIEW,TRADE_EDIT,TRADE_DELETE");
		        usrgroup.setRole("ROLE_USER,ROLE_THINGS,ROLE_NEEDS,ROLE_TRADE");
				String password="d548102de97e35352222caa977ab5a1e";
				use.setPassword(password);
				use.setUsername(oa.getOpenId());
				use.setLocked("N");
				use.setDisplayName(info.getNickname());
				use.setCreatedDate(date);
				use.setStaffNum("0635008");
				use.setUserGroup(usrgroup);
				use.setPicture("usepicutre.jpg");
				use.setChatnumber(0);
				this.userHelper.saveUser(use);
			    name=oa.getOpenId();
			    pass=password;
			}
			else{
				IUser user=this.userHelper.findUserByUsername(oa.getOpenId());
			    name=user.getUsername();
			    pass="pass1234567";
			}
            this.usesname=name;
            this.ps=pass;
			request.setAttribute("username", name);
			request.setAttribute("pass", pass);
		    }    
		}
		else{
			request.setAttribute("username","");
			request.setAttribute("pass", "");
		}
		
		return "login";
	}
	
	
	
}
