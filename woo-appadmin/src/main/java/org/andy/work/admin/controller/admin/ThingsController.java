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
import org.andy.work.appserver.model.impl.Trade;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.appserver.service.ITradeMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.AjaxResponse;
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
	
	@Resource
	private ITradeMain trademain;
	
	@RequestMapping(value="/mapmessage")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView GetMessage(ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	String username=userDetails.getName();
	String name=request.getParameter("usrname");
	Integer useid=Integer.valueOf(name);	
	IDetailmessage things=this.message.getmessage(useid);
	if(things!=null){
	Trade trade=this.trademain.searchmyself1(use.getId(), things.getthingsId());
    String thingsdesc=things.getThingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLng();
    Double thingslat=things.getthingsLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    IUser user=this.userHelper.getUserById(things.getnumber());
    String usename=user.getUsername();
    if(trade != null){
    	request.setAttribute("show", "1");
    }
    else{
    	request.setAttribute("show", "0");
    }
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsusername", username);
    request.setAttribute("picname",things.getpicname());
    request.setAttribute("onepicture", things.getonepicturename());
    request.setAttribute("twopicture", things.gettwopicturename());
    request.setAttribute("thingsid", things.getthingsId());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/thingsmessage");
	}
	else{
		model.setViewName("tiles/filas");
	}
    return model;
	}
	
	@RequestMapping(value="/detailmessage/{id}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView GetDetailMessage(@PathVariable Integer id,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	String username=userDetails.getName();
	IDetailmessage things=this.message.getmessage(id);
	if(things!=null){
	Trade trade=this.trademain.searchmyself1(use.getId(), things.getthingsId());
    String thingsdesc=things.getThingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLat();
    Double thingslat=things.getthingsLng();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    IUser user=this.userHelper.getUserById(things.getnumber());
    String usename=user.getUsername();
    if(trade != null){
    	request.setAttribute("show", "1");
    }
    else{
    	request.setAttribute("show", "0");
    }
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsusername", username);
    request.setAttribute("thingsonepicture",things.getpicname());
    request.setAttribute("thingstwopicture",things.getonepicturename());
    request.setAttribute("thingsthreepicture",things.gettwopicturename());
    request.setAttribute("thingsid", things.getthingsId());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/myselfmessage");
	}
	else{
		model.setViewName("tiles/filas");
	}
    return model;
	}
	
	@RequestMapping(value="/detailmessages/{id}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView GetDetailMessages(@PathVariable Integer id,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	String username=userDetails.getName();
	IDetailmessage things=this.message.getmessage(id);
	if(things != null){
    String thingsdesc=things.getThingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLng();
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
    request.setAttribute("thingsonepicture",things.getpicname());
    request.setAttribute("thingstwopicture",things.getonepicturename());
    request.setAttribute("thingsthreepicture",things.gettwopicturename());
    request.setAttribute("thingsid", things.getthingsId());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/myselfmessage1");
	}
	else{
		model.setViewName("tiles/filas");
	}
    return model;
	}
	
	
	@RequestMapping(value="/listmessage")
	@ResponseBody
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView ListMessage(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search,@RequestParam(required=false) String keyWord){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
				IUser detail=this.userHelper.getUserById(user.getnumber());
				display.setThingsId(user.getthingsId());
				display.setThingsname(user.getName());
				display.setThingsdesc(user.getThingsDesc());
				display.setThingsprice(user.getPrice().toString());
				display.setThingsdate(user.getDate());
				display.setThingskind(user.getkind());
				display.setThingsoveranalyzed(user.getoveranalyzed());
			    display.setThingspicturename(detail.getPicture());
			    display.setOnepicture(user.getpicname());
			    display.setTwopicture(user.getonepicturename());
			    display.setThreepicture(user.gettwopicturename());
			    display.setUsername(detail.getDisplayName());
			    displays.add(display);
			}
			grid.setDatas(displays);
		}
		
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		IUser pic=this.userHelper.findUserByUsername(userDetails.getUsername());
		request.setAttribute("pic", pic.getPicture());
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
	public ModelAndView AddThings (@RequestParam("file0") MultipartFile file0,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,HttpServletResponse response,HttpServletRequest request,ModelAndView model)
	throws ServletException, IOException{
	
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user=this.userHelper.findUserByUsername(userDetails.getUsername());
		String username=userDetails.getUsername();
		String picturename1=new String("");
		String picturename2=new String("");
		String picturename3=new String("");
		int number=this.message.checkcount();
		number=number+1;
		if(!file0.isEmpty()){
			String pricture0=file0.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture0, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\thingspicture\\thingspicture";
			File upload0=new File(pricturepath+username+number+"1"+"."+suffix);
			picturename1="thingspicture"+username+number+"1"+"."+suffix;
			file0.transferTo(upload0);	
		}
		if(!file1.isEmpty()){
			String pricture1=file1.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture1, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\thingspicture\\thingspicture";
			File upload1=new File(pricturepath+username+number+"2"+"."+suffix);
			picturename2="thingspicture"+username+number+"2"+"."+suffix;
			file1.transferTo(upload1);	
		}
		if(!file2.isEmpty()){
			String pricture2=file2.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture2, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\thingspicture\\thingspicture";
			File upload2=new File(pricturepath+username+number+"3"+"."+suffix);
			picturename3="thingspicture"+username+number+"3"+"."+suffix;
			file2.transferTo(upload2);	
		}
		String name=request.getParameter("thingsname");
		String briefdesc=request.getParameter("briefdesc");
		String addr=request.getParameter("addr");
		String price=request.getParameter("price");
		String date=request.getParameter("date");
		Double lng=Double.valueOf(request.getParameter("lat"));
		Double lat=Double.valueOf(request.getParameter("lng"));
		Detailmessage message=new Detailmessage();
		message.setaddr(addr);
		message.setDate(date);
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
		message.setThingsDesc(briefdesc);;
		this.message.addmessage(message);
		model.setViewName("tiles/success");
		return model;
	}
	
	@RequestMapping(value="/editthings")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView SearchMySelf(HttpServletRequest request,AcctUserSearchCriteria search){
		
		AdminUserDetails myself=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user=this.userHelper.findUserByUsername(myself.getUsername());
		GridData<MessageDetail> grid = new GridData<MessageDetail>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<IDetailmessage> searchResp = this.message.searchmessage(new SearchRequest<AcctUserSearchCriteria>(search, pgm),user.getId(),null);
		if(searchResp.getTotalRecords()>0){
			List<IDetailmessage> messages=searchResp.getResults();
			List<MessageDetail> displays=new ArrayList<MessageDetail>();
			for(int i=0;i<messages.size();i++){
				MessageDetail display=new MessageDetail();
			    IDetailmessage detail=messages.get(i);
				display.setThingsId(detail.getthingsId());
				display.setThingsname(detail.getName());
				display.setThingsdesc(detail.getThingsDesc());
				display.setThingskind(detail.getkind());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
        pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		ModelAndView model=new ModelAndView();
		model.addObject("grid", grid).setViewName("tiles/includes/myselfthings");
		return model;
	}
	
	@RequestMapping(value="/updatemyselfthings/{id}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView WatchUpdateThings(@PathVariable Integer id,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	String username=userDetails.getName();
	IDetailmessage things=this.message.getmessage(id);
	if(things.getnumber().equals(use.getId())){
    String thingsdesc=things.getThingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLng();
    Double thingslat=things.getthingsLat();
    Double thingsprice=things.getPrice();
    String thingsaddr=things.getaddr();
    String thingsname=things.getName();
    IUser user=this.userHelper.getUserById(things.getnumber());
    String usename=user.getUsername();
    String onepicturename=things.getpicname();
    String twopicturename=things.getonepicturename();
    String threepicturename=things.gettwopicturename();
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
    request.setAttribute("thingsaddr", thingsaddr);
    request.setAttribute("onepicturename",onepicturename);
    request.setAttribute("twopicturename",twopicturename);
    request.setAttribute("threepicturename",threepicturename); 
    model.setViewName("tiles/includes/editmyselfthings");
	}
	else{
		model.setViewName("tiles/filas");
	}
    return model;
	}
	
	
	@RequestMapping(value="/updatethings")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView UpdateThings (@RequestParam("file0") MultipartFile file0,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,HttpServletResponse response,HttpServletRequest request,ModelAndView model)
	throws ServletException, IOException{
	
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user=this.userHelper.findUserByUsername(userDetails.getUsername());
		Integer number=Integer.valueOf(request.getParameter("thingsid"));
		Detailmessage message=this.message.getmessage(number);
		String username=userDetails.getUsername();
		String picturename1=new String(message.getpicname());
		String picturename2=new String(message.getonepicturename());
		String picturename3=new String(message.gettwopicturename());
		if(!file0.isEmpty()){
			String pricture0=file0.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture0, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\thingspicture\\thingspicture";
			File upload0=new File(pricturepath+username+number+"1"+"."+suffix);
			picturename1="thingspicture"+username+number+"1"+"."+suffix;
			file0.transferTo(upload0);	
		}
		if(!file1.isEmpty()){
			String pricture1=file1.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture1, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\thingspicture\\thingspicture";
			File upload1=new File(pricturepath+username+number+"2"+"."+suffix);
			picturename2="thingspicture"+username+number+"2"+"."+suffix;
			file1.transferTo(upload1);	
		}
		if(!file2.isEmpty()){
			String pricture2=file2.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture2, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\thingspicture\\thingspicture";
			File upload2=new File(pricturepath+username+number+"3"+"."+suffix);
			picturename3="thingspicture"+username+number+"3"+"."+suffix;
			file2.transferTo(upload2);	
		}
		String name=request.getParameter("thingsname");
		String briefdesc=request.getParameter("briefdesc");
		String addr=request.getParameter("addr");
		String price=request.getParameter("price");
		String date=request.getParameter("thingsdate");
		Double lng=Double.valueOf(request.getParameter("lng"));
		Double lat=Double.valueOf(request.getParameter("lat"));
		message.setaddr(addr);
		message.setDate(date);
		message.setkind("");
		message.setName(name);
		message.setnumber(user.getId());
		message.setoveranalyzed(1);
		message.setphone(user.getPhone());
		message.setpicname(picturename1);
		message.setthingsLat(lng);
		message.setthingsLng(lat);
		message.setPrice(Double.valueOf(price));
		message.setonepicturename(picturename2);
		message.settwopicturename(picturename3);
		message.setThingsDesc(briefdesc);;
		String judge=this.message.updatemessage(message);
		if(judge.equals("success")){
		model.setViewName("tiles/success");
		}
		return model;
	}
	
	@RequestMapping(value="/deletethings/{id}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_DELETE)
	public AjaxResponse DeleteThings(@PathVariable Integer id){

		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
		Detailmessage message=this.message.getmessage(id);
		Integer a=use.getId();
		Integer b=message.getnumber();
		if(message!=null)
		{
		if(a.equals(b)){
			message.setoveranalyzed(0);
			this.message.updatemessage(message);
			return AjaxResponse.success("操作成功");
		}
//		else{
//		return AjaxResponse.fail("操作失败");
//		}
		}
		return AjaxResponse.fail("操作失败");
	}
	
}
