package org.andy.work.admin.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.service.IUserMaintenanceService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/chat")
public class ChatController {
	
	@Resource
	private UserHelper userHelper;
	
	@RequestMapping(value="/wchat")
	@ResponseBody 
	public ModelAndView chat(ModelAndView model,HttpServletResponse response,HttpServletRequest request){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
		
	    request.setAttribute("usename",use.getUsername());
		request.setAttribute("password", "123456");		
		model.setViewName("tiles/includes/chat");
		return model;
	} 
	
	@Resource
	private UserHelper userhelper;
	
	@RequestMapping(value="/get")
	@ResponseBody 
	public ModelAndView thingschat(ModelAndView model,HttpServletResponse response,HttpServletRequest request){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	    request.setAttribute("usename",use.getUsername());
		request.setAttribute("password", "123456");
	    String thingnumber=request.getParameter("usrid");
	    IUser user=this.userhelper.findUserByUsername(thingnumber);
	    String name=user.getUsername();
        request.setAttribute("thingnumber", name);
        request.setAttribute("thingname", user.getDisplayName());
		model.setViewName("tiles/includes/wchat");
		return model;
	} 
	
	@Resource 
	private IUserMaintenanceService usermain;
	
	@RequestMapping(value="/register")
	@ResponseBody 
	public ModelAndView Register(ModelAndView model,HttpServletResponse response,HttpServletRequest request){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
		
	    request.setAttribute("usename",use.getUsername());
	    request.setAttribute("displayname", use.getDisplayName());
		request.setAttribute("password", "123456");
	    
		model.setViewName("tiles/includes/chatregister");
		return model;
	} 
	
	
	@RequestMapping(value="/registersuccess")
	@ResponseBody 
	public ModelAndView RegisterSuccess(ModelAndView model,HttpServletResponse response,HttpServletRequest request){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		IUser uses=this.userHelper.findUserByUsername(userDetails.getUsername());
		uses.setChatnumber(1);
	    this.usermain.saveUser(uses);
		model.setViewName("tiles/success");
		return model;
	} 

}
