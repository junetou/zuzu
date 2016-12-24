package org.andy.work.admin.security;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andy.work.admin.helper.CompanyHelper;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.helper.UserSessionHelper;
import org.andy.work.admin.helper.VerifyCodeCookie;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import static com.google.common.base.Preconditions.checkNotNull;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
	
	@Resource
	private UserHelper userHelper;
	
    @Resource
	private CompanyHelper companyHelper;
	
	@Resource
	private UserSessionHelper usersession;
	
	@Autowired  
    private ValidateAttemptService loginAttemptService;  
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String usernames = request.getParameter("usrname");
		String error="";
		Pattern pattern=Pattern.compile("^[a-zA-Z0-9_.]+$");
		String username="";
		if(pattern.matcher(usernames).matches()){
			username=usernames;
		}
		else{
			username="abc";
		}
		IUser user = this.userHelper.findUserByUsername(username);
		if(user != null) {
			if ("Y".equals(user.getLocked())) {
				error = "账号已被锁定";
			} else {
				error = "登录失败，密码错误";
			}
		} else {
			//在这里改 IUser users=this.userhelper.getUsername(username);
			error = "登录失败，账号不存在";
			ICompany comuser=this.companyHelper.findUserByUsername(username);
			if(comuser!=null){
				if ("Y".equals(comuser.getLocked())) {
					error = "账号已被锁定";
				} else {
					error = "登录失败，密码错误";
				}
			}else{
				error = "登录失败，账号不存在";
			}
		}
		loginAttemptService.loginFailed(username);
	    if(loginAttemptService.isLock(username)){
	    	error="账号被锁，请在1小时候登陆";
	    } 
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", error);
		String url = request.getContextPath() + "/secure/login";
		response.sendRedirect(url);
	}

}
