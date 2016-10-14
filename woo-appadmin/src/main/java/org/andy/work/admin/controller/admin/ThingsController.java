package org.andy.work.admin.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.MessageDetail;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
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
@RequestMapping("/portal/things")
public class ThingsController {

	@Resource
	private UserHelper userHelper;
	
	@Resource
	private IDetailmessageMain message;
	
	
	@RequestMapping(value="/mapmessage")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView GetMessage(ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String username=userDetails.getName();
	String name=request.getParameter("usrname");
	Integer useid=Integer.valueOf(name);	
	IDetailmessage things=this.message.getmessage(useid);
    String thingsdesc=things.getthingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLat();
    Double thingslat=things.getthingsLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    IUser user=this.userHelper.getUserById(things.getnumber());
    String usename=user.getUsername();
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsusername", username);
    request.setAttribute("picname",things.getpicname());
    request.setAttribute("thingsid", things.getthingsId());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/thingsmessage");
    return model;
	}
	
	@RequestMapping(value="/detailmessage/{id}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView GetDetailMessage(@PathVariable Integer id,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String username=userDetails.getName();
	IDetailmessage things=this.message.getmessage(id);
    String thingsdesc=things.getthingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLat();
    Double thingslat=things.getthingsLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    IUser user=this.userHelper.getUserById(things.getnumber());
    String usename=user.getUsername();
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsusername", username);
    request.setAttribute("picname",things.getpicname());
    request.setAttribute("thingsid", things.getthingsId());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/thingsmessage");
    return model;
	}
	
	
	@RequestMapping(value="/listmessage")
	@ResponseBody
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView ListMessage(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search,@RequestParam(required=false) String keyWord){
		GridData<MessageDetail> grid = new GridData<MessageDetail>();
		search.setKeyWork(keyWord);
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<IDetailmessage> searchResp = this.message.searchUser(new SearchRequest<AcctUserSearchCriteria>(search, pgm));
		if (searchResp.getTotalRecords() > 0) {
			List<MessageDetail> displays = new ArrayList<MessageDetail>();
			List<IDetailmessage> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				IDetailmessage user = userGroups.get(i);
				MessageDetail display = new MessageDetail();
				display.setThingsId(user.getthingsId());
				display.setThingsname(user.getName());
				display.setThingsdesc(user.getthingsDesc());
				display.setThingsprice(user.getPrice().toString());
				display.setThingsdate(user.getDate());
				display.setThingskind(user.getkind());
				display.setThingsoveranalyzed(user.getoveranalyzed());
			    display.setThingspicturename(user.getpicname());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/includes/listmessage");
		return model;
	}
	
	
	
	@RequestMapping(value="/showaddview")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView ShowAddView (ModelAndView model){
		
		model.setViewName("tiles/includes/addthings");
		return model;
	}
	
	
	@RequestMapping(value="/addthings")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView AddThings (@RequestParam("file") MultipartFile file0,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,HttpServletResponse response,HttpServletRequest request,ModelAndView model)
	throws ServletException, IOException{
	
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user=this.userHelper.findUserByUsername(userDetails.getUsername());
		String username=userDetails.getUsername();
		String picturename1=new String("");
		String picturename2=new String("");
		String picturename3=new String("");
		Date nowtime=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy mm dd hh mm ss");
		if(!file0.isEmpty()){
			String pricture0=file0.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture0, ".");
			String pricturepath="H:\\new2\\staticResources\\static\\thingspicture\\picture";
			File upload0=new File(pricturepath+username+"1"+"."+suffix);
			picturename1=pricturepath+username+"1"+"."+suffix;
			file0.transferTo(upload0);	
		}
		if(!file1.isEmpty()){
			String pricture1=file1.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture1, ".");
			String pricturepath="H:\\new2\\staticResources\\static\\thingspicture\\picture";
			File upload1=new File(pricturepath+username+"2"+"."+suffix);
			picturename2=pricturepath+username+"2"+"."+suffix;
			file1.transferTo(upload1);	
		}
		if(!file2.isEmpty()){
			String pricture2=file2.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture2, ".");
			String pricturepath="H:\\new2\\staticResources\\static\\thingspicture\\picture";
			File upload2=new File(pricturepath+username+"3"+"."+suffix);
			picturename3=pricturepath+username+"3"+"."+suffix;
			file2.transferTo(upload2);	
		}
		String name=request.getParameter("thingsname");
		String briefdesc=request.getParameter("briefdesc");
		String addr=request.getParameter("addr");
		String price=request.getParameter("price");
		String wechat=request.getParameter("wechat");
		Double lng=Double.valueOf(request.getParameter("lng"));
		Double lat=Double.valueOf(request.getParameter("lat"));
		Detailmessage message=new Detailmessage();
		message.setaddr(addr);
		message.setDate(time.format(nowtime));
		message.setkind("");
		message.setName(name);
		message.setnumber(user.getId());
		message.setoveranalyzed(1);
		message.setphone(user.getPhone());
		message.setpicname(picturename1);
		message.setthingsLat(lat);
		message.setthingsLng(lng);
		message.setPrice(Double.valueOf(price));
		message.setonepicturename(picturename2);
		message.settwopicturename(picturename3);
		message.setthingsDesc(briefdesc);;
		this.message.addmessage(message);
		model.setViewName("tiles/success");
		return model;
	}
	
	
}
