package org.andy.work.admin.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.andy.work.admin.helper.UserSessionHelper;
import org.andy.work.admin.security.AdminUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class PermissionTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2317512100352719974L;
	
	private String roleType;
	private String operationType;
	
	@Autowired
	private UserSessionHelper userSessionHelper;
	
	
	@Override
	public int doStartTag() throws JspException {
		
		boolean falg = false;
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
	     Object detail=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(detail instanceof AdminUserDetails){
			AdminUserDetails details=(AdminUserDetails)detail;
			if(this.isAuthoritie(details.getAuthorities(), roleType) && details.getPermissions().contains(operationType) ) {
			    falg = true;
		    }
		}
		}
		if (falg) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}
	
	private boolean isAuthoritie(Collection<? extends GrantedAuthority> collection, String operationType) {
		for (GrantedAuthority grantedAuthority : collection) {
			if (grantedAuthority.toString().equals(operationType)) {
				return true;
			}
		}
		return false;
	}
	
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
}
