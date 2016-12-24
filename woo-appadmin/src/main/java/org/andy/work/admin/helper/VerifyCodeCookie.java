package org.andy.work.admin.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class VerifyCodeCookie {
	
	private boolean judges=false;
	
	public void setSystemCode(HttpServletRequest request,String code){
		
		HttpSession session=request.getSession();
		session.setAttribute("_Systemcode", code);
	}
	
	public void setUseCode(HttpServletRequest request,String usrname){
		
		HttpSession session = request.getSession();
		session.setAttribute("_usrname", usrname);
	}
	
	public void removeCode(HttpServletRequest request){
		
		HttpSession session=request.getSession();
		session.removeAttribute("_Systemcode");
		session.removeAttribute("_usrname");
	}
	
	public boolean isVerify(HttpServletRequest request){
		
		boolean judge=false;
		HttpSession session=request.getSession();
		String systemcode=(String)session.getAttribute("_Systemcode");
		String usrnamecode=(String)session.getAttribute("_usrname");
		
		if(systemcode!=null && usrnamecode!=null){
		if(systemcode.equalsIgnoreCase(usrnamecode)){
             judge=true;
		}
		else{
			judge=false;
		}
		
		}
		else{
		   judge=false;
		}
        return judge;
	} 
	
	
}
