package org.andy.work.admin.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.helper.CookieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	@Resource
	private CookieHelper cookieHelper;
	
	@Autowired  
    private ValidateAttemptService loginAttemptService;  
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		String username = request.getParameter("usrname");
		loginAttemptService.loginSucceeded(username);
		String targetUrl = this.getTargetUrl(request);
	    response.sendRedirect(targetUrl);
	}

	private String getTargetUrl(HttpServletRequest request) {
		
		String targetUrl;
		targetUrl = request.getContextPath()+"/index";
		return targetUrl;
	}

}
