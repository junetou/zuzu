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
import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.model.impl.Need;
import org.andy.work.appserver.model.impl.Trade;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.appserver.service.INeedMain;
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
@RequestMapping("/portal/needs")
public class NeedsController {

	@Resource
	private UserHelper userHelper;
	
	@Resource
	private IDetailmessageMain message;
	
	@Resource
	private INeedMain need;
	
	@Resource
	private ITradeMain trademain;
	
	//地图转到详细信息
	@RequestMapping(value="/mapmessage")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView GetMessage(ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	String username=userDetails.getName();
	String name=request.getParameter("usrname1");
	Integer useid=Integer.valueOf(name);	
	INeed things=this.need.Search(useid);
	if(things!=null){
	Trade trade=this.trademain.searchmyself2(use.getId(), things.getNeed());
    String thingsdesc=things.getDescs();
    String thingsdate=things.getDate();
    Double thingslng=things.getLng();
    Double thingslat=things.getLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    if(trade != null){
    	request.setAttribute("show", "1");
    }
    else{
    	request.setAttribute("show", "0");
    }
    IUser user=this.userHelper.getUserById(things.getNumber());
    String usename=user.getUsername();
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsusername", username);
    request.setAttribute("thingsid", things.getNeed());
    request.setAttribute("thingsnumber", usename);
    request.setAttribute("picname",things.getOnepicture());
    request.setAttribute("onepicture", things.getTwopicture());
    request.setAttribute("twopicture", things.getThreepicture());
    model.setViewName("tiles/includes/needsmessage");
	}
	else{
		model.setViewName("tiles/filas");
	}
    return model;
	}
	
	
	//从列表信息转到详细信息
	@RequestMapping(value="/detailmessage/{id}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView GetDetailMessage(@PathVariable Integer id,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	String username=userDetails.getName();
	Need things=this.need.Search(id);
	if(things!=null){
	Trade trade=this.trademain.searchmyself2(use.getId(), things.getNeed());
    String thingsdesc=things.getDescs();
    String thingsdate=things.getDate();
    Double thingslng=things.getLng();
    Double thingslat=things.getLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    if(trade != null){
    	request.setAttribute("show", "1");
    }
    else{
    	request.setAttribute("show", "0");
    }
    IUser user=this.userHelper.getUserById(things.getNumber());
    String usename=user.getUsername();
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsusername", username);
    request.setAttribute("thingsonepicture",things.getOnepicture());
    request.setAttribute("thingstwopicture",things.getTwopicture());
    request.setAttribute("thingsthreepicture",things.getThreepicture());
    request.setAttribute("thingsid", things.getNeed());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/myselfneedmessage");
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
	String username=userDetails.getName();
	Need things=this.need.Search(id);
	if(things!=null){
    String thingsdesc=things.getDescs();
    String thingsdate=things.getDate();
    Double thingslng=things.getLng();
    Double thingslat=things.getLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    IUser user=this.userHelper.getUserById(things.getNumber());
    String usename=user.getUsername();
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsusername", username);
    request.setAttribute("thingsonepicture",things.getOnepicture());
    request.setAttribute("thingstwopicture",things.getTwopicture());
    request.setAttribute("thingsthreepicture",things.getThreepicture());
    request.setAttribute("thingsid", things.getNeed());
    request.setAttribute("thingsnumber", usename);
    model.setViewName("tiles/includes/myselfneedmessage1");
	}
	else{
		model.setViewName("tiles/filas");
	}
    return model;
	}
	
	
	//列表信息
	@RequestMapping(value="/listmessage")
	@ResponseBody
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView ListMessage(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search,@RequestParam(required=false) String keyWord){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GridData<MessageDetail> grid = new GridData<MessageDetail>();
		search.setKeyWork(keyWord);
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<INeed> searchResp = this.need.SearchCustomerNeed(new SearchRequest<AcctUserSearchCriteria>(search, pgm));
		if (searchResp.getTotalRecords() > 0) {
			List<MessageDetail> displays = new ArrayList<MessageDetail>();
			List<INeed> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				INeed user = userGroups.get(i);
				MessageDetail display = new MessageDetail();
				IUser detail=this.userHelper.getUserById(user.getNumber());
				display.setThingsId(user.getNeed());
				display.setThingsname(user.getName());
				display.setThingsdesc(user.getDescs());
				display.setThingsprice(user.getPrice().toString());
				display.setThingsdate(user.getDate());
				display.setThingskind(user.getKind());
				display.setThingsoveranalyzed(user.getOveranalyzed());
			    display.setThingspicturename(detail.getPicture());
			    display.setOnepicture(user.getOnepicture());
			    display.setTwopicture(user.getTwopicture());
			    display.setThreepicture(user.getThreepicture());
			    display.setUsername(detail.getDisplayName());
			    displays.add(display);
			}
			grid.setDatas(displays);
		}
		
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		IUser pic=this.userHelper.findUserByUsername(userDetails.getUsername());
		request.setAttribute("pic", pic.getPicture());
		model.addObject("grid", grid).setViewName("tiles/includes/listneedmessage");
		return model;
	}
		
	
	//转到增加需求视图
	@RequestMapping(value="/showaddview")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView ShowAddView (ModelAndView model){
		
		model.setViewName("tiles/includes/addneeds");
		return model;
	}
	
	//正式增加需求
	@RequestMapping(value="/addthings")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView AddThings (@RequestParam("file0") MultipartFile file0,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,HttpServletResponse response,HttpServletRequest request,ModelAndView model)
	throws ServletException, IOException{
	
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user=this.userHelper.findUserByUsername(userDetails.getUsername());
		Integer number=this.need.checkcount();
		number=number+1;
		String username=userDetails.getUsername();
		String picturename1=new String("");
		String picturename2=new String("");
		String picturename3=new String("");
		if(!file0.isEmpty()){
			String pricture0=file0.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture0, ".");
			//String pricturepath="H:\\new2\\staticResources\\static\\needspicture\\needspicture";
			String pricturepath="C:\\Program Files\\staticResources\\static\\needspicture\\needspicture";
			File upload0=new File(pricturepath+username+number+"1"+"."+suffix);
			picturename1="needspicture"+username+number+"1"+"."+suffix;
			file0.transferTo(upload0);	
		}
		if(!file1.isEmpty()){
			String pricture1=file1.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture1, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\needspicture\\needspicture";
			File upload1=new File(pricturepath+username+number+"2"+"."+suffix);
			picturename2="needspicture"+username+number+"2"+"."+suffix;
			file1.transferTo(upload1);	
		}
		if(!file2.isEmpty()){
			String pricture2=file2.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture2, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\needspicture\\needspicture";
			File upload2=new File(pricturepath+username+number+"3"+"."+suffix);
			picturename3="needspicture"+username+number+"3"+"."+suffix;
			file2.transferTo(upload2);	
		}
		String name=request.getParameter("thingsname");
		String briefdesc=request.getParameter("briefdesc");
		String addr=request.getParameter("addr");
		String price=request.getParameter("price");
		String date=request.getParameter("date");
		Double lng=Double.valueOf(request.getParameter("lng"));
		Double lat=Double.valueOf(request.getParameter("lat"));
		Need message=new Need();
		message.setAddr(addr);
		message.setDate(date);
		message.setKind("");
		message.setName(name);
		message.setNumber(user.getId());
		message.setOveranalyzed(1);
		message.setPhone(user.getPhone());
		message.setOnepicture(picturename1);
		message.setLat(lat);
		message.setLng(lng);
		message.setPrice(Double.valueOf(price));
		message.setTwopicture(picturename2);
		message.setThreepicture(picturename3);
		message.setDescs(briefdesc);;
		this.need.addmessage(message);
		model.setViewName("tiles/success");
		return model;
	}
	
	
	//查看自己发布的需求
	@RequestMapping(value="/editthings")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView SearchMySelf(HttpServletRequest request,AcctUserSearchCriteria search){
		
		AdminUserDetails myself=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user=this.userHelper.findUserByUsername(myself.getUsername());
		GridData<MessageDetail> grid = new GridData<MessageDetail>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<INeed> searchResp = this.need.SearchMy(new SearchRequest<AcctUserSearchCriteria>(search, pgm),user.getId());
		if(searchResp.getTotalRecords()>0){
			List<INeed> messages=searchResp.getResults();
			List<MessageDetail> displays=new ArrayList<MessageDetail>();
			for(int i=0;i<messages.size();i++){
				MessageDetail display=new MessageDetail();
			    INeed detail=messages.get(i);
				display.setThingsId(detail.getNeed());
				display.setThingsname(detail.getName());
				display.setThingsdesc(detail.getDescs());
				display.setThingskind(detail.getKind());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
        pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		ModelAndView model=new ModelAndView();
		model.addObject("grid", grid).setViewName("tiles/includes/myselfneeds");
		return model;
	}
	
	
	//转入修改自己需求的视图
	@RequestMapping(value="/updatemyselfthings/{id}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView WatchUpdateThings(@PathVariable Integer id,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
		
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
	String username=userDetails.getName();
	INeed things=this.need.Search(id);
	if(things.getNumber().equals(use.getId())){
    String thingsdesc=things.getDescs();
    String thingsdate=things.getDate();
    Double thingslng=things.getLng();
    Double thingslat=things.getLat();
    Double thingsprice=things.getPrice();
    String thingsaddr=things.getAddr();
    String thingsname=things.getName();
    IUser user=this.userHelper.getUserById(things.getNumber());
    String usename=user.getUsername();
    String onepicturename=things.getOnepicture();
    String twopicturename=things.getTwopicture();
    String threepicturename=things.getThreepicture();
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingsusername", username);
    request.setAttribute("picname","");
    request.setAttribute("thingsid", things.getNeed());
    request.setAttribute("thingsnumber", usename);
    request.setAttribute("thingsaddr", thingsaddr);
    request.setAttribute("onepicturename",onepicturename);
    request.setAttribute("twopicturename",twopicturename);
    request.setAttribute("threepicturename",threepicturename); 
    model.setViewName("tiles/includes/editmyselfneeds");
	}
	else{
		model.setViewName("tiles/filas");
	}
    return model;
	}
	
	//正式修改自己的需求
	@RequestMapping(value="/updatethings")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_EDIT)
	public ModelAndView UpdateThings (@RequestParam("file0") MultipartFile file0,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,HttpServletResponse response,HttpServletRequest request,ModelAndView model)
	throws ServletException, IOException{
	
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user=this.userHelper.findUserByUsername(userDetails.getUsername());
		Integer number=Integer.valueOf(request.getParameter("thingsid"));
		Need needs=this.need.Search(number);
		String username=userDetails.getUsername();
		String picturename1=new String(needs.getOnepicture());
		String picturename2=new String(needs.getTwopicture());
		String picturename3=new String(needs.getThreepicture());
		if(!file0.isEmpty()){
			String pricture0=file0.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture0, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\needspicture\\needspicture";
			File upload0=new File(pricturepath+username+number+"1"+"."+suffix);
			picturename1="needspicture"+username+number+"1"+"."+suffix;
			file0.transferTo(upload0);	
		}
		if(!file1.isEmpty()){
			String pricture1=file1.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture1, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\thingspicture\\needspicture";
			File upload1=new File(pricturepath+username+number+"2"+"."+suffix);
			picturename2="needspicture"+username+number+"2"+"."+suffix;
			file1.transferTo(upload1);	
		}
		if(!file2.isEmpty()){
			String pricture2=file2.getOriginalFilename();
			String suffix=StringUtils.substringAfterLast(pricture2, ".");
			String pricturepath="C:\\Program Files\\staticResources\\static\\thingspicture\\needspicture";
			File upload2=new File(pricturepath+username+number+"3"+"."+suffix);
			picturename3="needspicture"+username+number+"3"+"."+suffix;
			file2.transferTo(upload2);	
		}
		String name=request.getParameter("thingsname");
		String briefdesc=request.getParameter("briefdesc");
		String addr=request.getParameter("addr");
		String price=request.getParameter("price");
		String date=request.getParameter("thingsdate");
		Double lng=Double.valueOf(request.getParameter("lng"));
		Double lat=Double.valueOf(request.getParameter("lat"));
		needs.setAddr(addr);
		needs.setDate(date);
		needs.setKind("");
		needs.setName(name);
		needs.setNumber(user.getId());
		needs.setOveranalyzed(1);
		needs.setPhone(user.getPhone());
		needs.setOnepicture(picturename1);
		needs.setLat(lat);
		needs.setLng(lng);
		needs.setPrice(Double.valueOf(price));
		needs.setTwopicture(picturename2);
		needs.setThreepicture(picturename3);
		needs.setDescs(briefdesc);;
		this.need.updatemessage(needs);
		model.setViewName("tiles/success");
		return model;
	}
	
	
	//下架自己的需求
	@RequestMapping(value="/deletethings/{id}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_DELETE)
	public AjaxResponse DeleteThings(@PathVariable Integer id){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userHelper.findUserByUsername(userDetails.getUsername());
		Need messages=this.need.Search(id);
		Integer a=use.getId();
		Integer b=messages.getNumber();
        if(a.equals(b)){
		if(messages!=null){
		messages.setOveranalyzed(0);
		String judge=this.need.updatemessage(messages);
		if(judge.equals("success")){
			return AjaxResponse.success("操作成功");
		}
		}
		}
        return AjaxResponse.fail("操作失败");
        }
		
	
	
}
