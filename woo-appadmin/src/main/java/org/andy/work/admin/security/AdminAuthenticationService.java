package org.andy.work.admin.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.helper.CompanyHelper;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.helper.VerifyCodeCookie;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.model.impl.Company;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.commons.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationService implements UserDetailsService {
	
	@Resource
	private UserHelper userHelper;
	
    @Autowired   
    private ValidateAttemptService loginAttemptService;
    
    @Autowired   
	private ICompanyMaintenanceService companyHelper;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Object users=this.getUser(username);
    	List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
    	List<String> pers = new ArrayList<String>();
    	AdminUserDetails userDetails = new AdminUserDetails("", "", "", "",true, grants, pers);
        if(users instanceof ICompany){
        ICompany user=(ICompany)users;
        if(!"Y".equals(user.getLocked())){
        List<GrantedAuthority> grantedAuthorities = this.getCompanyAuthoritys(user);
		List<String>  permissions = new ArrayList<String>();
		if (user.getUserGroup() != null && StringUtil.hasValue(user.getUserGroup().getPermission())) {
				permissions.addAll(Arrays.asList(user.getUserGroup().getPermission().split(",")));
		}
		userDetails = new AdminUserDetails(user.getCompanyname(), user.getPassword(), user.getVersion().toString(), user.getDisplayName()
					,  true, grantedAuthorities, permissions);
        }
        else{
        	 userDetails = new AdminUserDetails("", "", "", "",  true, grants, pers);
        }
        }
        else if(users instanceof IUser){
		IUser user=(IUser)users;
		if(!"Y".equals(user.getLocked())){
		List<GrantedAuthority> grantedAuthorities = this.getUserAuthoritys(user);
		List<String> permissions = new ArrayList<String>();
		if (user.getUserGroup() != null && StringUtil.hasValue(user.getUserGroup().getPermission())) {
			permissions.addAll(Arrays.asList(user.getUserGroup().getPermission().split(",")));
		}
		userDetails = new AdminUserDetails(user.getUsername(), user.getPassword(), user.getVersion().toString(), user.getDisplayName()
				,  true, grantedAuthorities, permissions);
		}
		else{
       	 userDetails = new AdminUserDetails("", "", "", "",  true, grants, pers);
        }
		}
		//查看是否锁
        if(loginAttemptService.isLock(username)){
           userDetails = new AdminUserDetails("", "", "", "",  true, grants, pers);
        }
		return userDetails;
	}
	
	private List<GrantedAuthority> getUserAuthoritys(IUser user) {
		
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		
		IUserGroup group = user.getUserGroup();
		if (group != null && StringUtil.hasValue(group.getRole())) {
			for (String role : group.getRole().split(",")) {
				gas.add(new SimpleGrantedAuthority(role));
			}
		}
		
		return gas;
	}
	
	private List<GrantedAuthority> getCompanyAuthoritys(ICompany company) {
		
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		IUserGroup group = company.getUserGroup();
		if (group != null && StringUtil.hasValue(group.getRole())) {
			for (String role : group.getRole().split(",")) {
				gas.add(new SimpleGrantedAuthority(role));
			}
		}

		return gas;
	}
	
	
	private Object getUser(String username){
		
		Pattern pattern=Pattern.compile("^[a-zA-Z0-9_.]+$");
		if(pattern.matcher(username).matches()){
		ICompany company=this.companyHelper.findUserByusername(username);
		if(company!=null){
			return company;
		}
		else{
			IUser user=this.userHelper.findUserByUsername(username);
			return user;
		}
		}
		else{
			IUser user=this.userHelper.findUserByUsername("abc");
			return user;
		}
	}

}
