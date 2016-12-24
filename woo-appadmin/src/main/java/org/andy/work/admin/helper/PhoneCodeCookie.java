package org.andy.work.admin.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class PhoneCodeCookie {
	
	private boolean judges=false;
	
	public void setPhoneCode(HttpServletRequest request,String code){
		
		HttpSession session=request.getSession();
		session.setAttribute("_Phonecode", code);
	}
	
	public void setUseCode(HttpServletRequest request,String usrname){
		
		HttpSession session = request.getSession();
		session.setAttribute("_Phoneusrname", usrname);
	}
	
	public void removeCode(HttpServletRequest request){
		
		HttpSession session=request.getSession();
		session.removeAttribute("_Phonecode");
		session.removeAttribute("_Phoneusrname");
	}
	
	public boolean isVerify(HttpServletRequest request){
		
		boolean judge=false;
		HttpSession session=request.getSession();
		String systemcode=(String)session.getAttribute("_Phonecode");
		String usrnamecode=(String)session.getAttribute("_Phoneusrname");
		Long createtime=session.getCreationTime();
		Date newdate=new Date();
		Long newtime=newdate.getTime();
		Long time=newtime-createtime;
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
