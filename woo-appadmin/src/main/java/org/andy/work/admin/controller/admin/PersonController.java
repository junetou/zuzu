package org.andy.work.admin.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.form.UserForm;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.log.LogsType;
import org.andy.work.admin.log.SystemLog;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.service.ISolrPublishMaintenanceService;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.RegularExpressUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/portal/person")
public class PersonController {
        
	@Resource
	private UserHelper userHelper;
	
	@Resource
	private ISolrPublishMaintenanceService solrPublishService;
	
	@RequestMapping(value="/form/{userId}")
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView show(@PathVariable Integer userId, ModelAndView model,HttpServletRequest request) {
	
		IUser user=this.userHelper.getUserById(userId);
		
		request.setAttribute("name", user.getDisplayName());
		request.setAttribute("id",user.getId());
		request.setAttribute("onepath",user.getExt1());
		request.setAttribute("email", user.getEmail());
		request.setAttribute("phone", user.getPhone());
		request.setAttribute("wechat", user.getWeixin());
		request.setAttribute("addr",user.getAddress());
		request.setAttribute("qq", user.getQqNum());
		
		model.setViewName("tiles/includes/updatepersonmessage");
		
		return model;
	}
	
	@RequestMapping(value="/update")
	@AuthOperation(roleType=RoleType.THINGS,operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView UpdateProduct(@RequestParam("file0") MultipartFile file0,HttpServletResponse response,HttpServletRequest request)
	 throws ServletException, IOException{
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userid=request.getParameter("userid");
		IUser user=this.userHelper.getUserById(Integer.valueOf(userid));
		String username=userDetails.getUsername();
		String picturename=user.getPicture();
		if(!file0.isEmpty()){
			String pricture0=file0.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture0, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\userpicture\\userpicture";
			File upload0=new File(pricturepath+username+"1"+"."+suffix);
			file0.transferTo(upload0);	
			picturename="userpicture"+username+"1"+"."+suffix;
		}
		String name=request.getParameter("username");
		String email=request.getParameter("email");
		String addr=request.getParameter("addr");
		String qq=request.getParameter("qq");
		String phone=request.getParameter("phone");
		String wechat=request.getParameter("wechat");
	    user.setAddress(addr);
	    user.setDisplayName(name);
	    user.setQqNum(qq);
	    user.setPhone(phone);
        user.setWeixin(wechat);
        user.setEmail(email);
        user.setPicture(picturename);
        this.userHelper.saveUser(user);
	    ModelAndView model=new ModelAndView();
		model.setViewName("tiles/success");
	    return model;
	}
	
	@RequestMapping(value="/direction")
	@ResponseBody
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView Direction(ModelAndView model) {
		
		model.setViewName("tiles/direction");
		return model;
	}
	
	@RequestMapping(value="resetpass")
	@ResponseBody
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public AjaxResponse updatePassword(@RequestParam Integer userId, @RequestParam String password) {
		if (RegularExpressUtil.isCorrectPassword(password, 8, 16)) {
			IUser user = this.userHelper.getUserById(userId);
			String encpwd = DigestUtils.md5Hex(password);
			user.setPassword(encpwd);
			this.userHelper.saveUser(user);
			return AjaxResponse.success("密码重设成功！");
		}
		
		return AjaxResponse.fail("密码格式错误！请输入8~16位的数字和字母的字符组合");
	}
	
}
