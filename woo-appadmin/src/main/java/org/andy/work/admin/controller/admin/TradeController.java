package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.ITrade;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/portal/trade")
public class TradeController {
	
	@Resource
	private ITradeMain trademain;
	
	@Resource
	private UserHelper userhelper;
	
	@Resource
	private IDetailmessageMain detailmessage;
	
	@Resource
	private INeedMain need;
	
	//查看自己申请借的东西
	@RequestMapping(value="/borrow")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
    public ModelAndView searchborrow(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usename=userDetails.getUsername();
		IUser use=this.userhelper.findUserByUsername(usename);
		GridData<Trade> grid = new GridData<Trade>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<ITrade> searchResp=this.trademain.searchborrow(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		if (searchResp.getTotalRecords() > 0) {
			List<Trade> displays = new ArrayList<Trade>();		
			List<ITrade> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				ITrade user = userGroups.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setTrade(user.getTrade());
			    display.setAssign(user.getAssign());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/includes/myselftradeborrow");
		return model;
	}
	
	//别人有你的东西你要租或者你确认租给别人的物品
	@RequestMapping(value="/sellerensureborrow")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
    public ModelAndView sellerensureborrow(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usename=userDetails.getUsername();
		IUser use=this.userhelper.findUserByUsername(usename);
		GridData<Trade> grid = new GridData<Trade>();
		GridData<Trade> gridthings = new GridData<Trade>();
		GridData<Trade> gridneeds = new GridData<Trade>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<ITrade> searchResp1=this.trademain.searchseller1(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		SearchResponse<ITrade> searchResp=this.trademain.searchborrow1(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		if (searchResp.getTotalRecords() > 0 || searchResp1.getTotalRecords()>0 ) {
			List<Trade> displays = new ArrayList<Trade>();	
			List<Trade> displaythings = new ArrayList<Trade>();	
			List<Trade> displayneeds = new ArrayList<Trade>();	
            if(searchResp.getTotalRecords() > 0)
            {
			List<ITrade> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				ITrade user = userGroups.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setTrade(user.getTrade());
			    display.setAssign(user.getAssign());
				displays.add(display);
				displaythings.add(display);
			}
			gridthings.setDatas(displaythings);
            }
            if(searchResp1.getTotalRecords() > 0)
            {
			List<ITrade> userGroupss = searchResp1.getResults();
			for (int i = 0; i < userGroupss.size(); i++) {
					ITrade user = userGroupss.get(i);
					Trade display = new Trade();
				    display.setSuccess(user.getSuccess());
				    display.setSellername(user.getSellername());
				    display.setGoodsname(user.getGoodsname());
				    display.setTrade(user.getTrade());
				    display.setAssign(user.getAssign());
					displays.add(display);
					displayneeds.add(display);
			}
			gridneeds.setDatas(displayneeds);
            }
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords()+searchResp1.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid)
		     .addObject("gridthings", gridthings)
		     .addObject("gridneeds", gridneeds)
		     .setViewName("tiles/includes/myselftradeborrow1");
		return model;
	}
	
	
	
	//人家要租的物品并且你有此物品
	@RequestMapping(value="/need")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
    public ModelAndView searchseller(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usename=userDetails.getUsername();
		IUser use=this.userhelper.findUserByUsername(usename);
		GridData<Trade> grid = new GridData<Trade>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<ITrade> searchResp=this.trademain.searchseller(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		if (searchResp.getTotalRecords() > 0) {
			List<Trade> displays = new ArrayList<Trade>();		
			List<ITrade> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				ITrade user = userGroups.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setTrade(user.getTrade());
			    display.setBorrowname(user.getBorrowname());
			    display.setGoodsname(user.getGoodsname());
			    display.setAssign(user.getAssign());
			    display.setEnsure(user.getEnsure());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/includes/myselftradeseller");
		return model;
	}	
	
	//租借
	@RequestMapping(value="/buy")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView buy(ModelAndView model, HttpServletRequest request,HttpServletResponse response){
		AdminUserDetails userdetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String thingsid=request.getParameter("thingsid");
		Integer thingsnumber=Integer.valueOf(thingsid);
		IDetailmessage message=this.detailmessage.getmessage(thingsnumber);
	    Integer seller=message.getnumber();
	    IUser sellername=this.userhelper.getUserById(seller);
	    IUser borrowid=this.userhelper.findUserByUsername(userdetails.getUsername());
		Trade trade=new Trade();
		trade.setBorrow(borrowid.getId());
		trade.setBorrowname(userdetails.getName());
		trade.setSeller(sellername.getId());;
		trade.setSellername(sellername.getDisplayName());
		trade.setThing(message.getthingsId());
		trade.setGoodsname(message.getName());
		trade.setAssign(1);
		trade.setEnsure(0);
		trade.setSuccess(0);
		trade.setThingsorneeds(1);
		String judge=this.trademain.addmessage(trade);	
		request.setAttribute("judge", judge);
		model.setViewName("tiles/success");	
		return model;
	}
	
	//租借
	@RequestMapping(value="/chuzu")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView ChuZu(ModelAndView model, HttpServletRequest request,HttpServletResponse response){
		AdminUserDetails userdetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String thingsid=request.getParameter("thingsid");
		Integer thingsnumber=Integer.valueOf(thingsid);
		INeed message=this.need.Search(thingsnumber);
	    Integer seller=message.getNumber();
	    IUser sellername=this.userhelper.getUserById(seller);
	    IUser chuzuren=this.userhelper.findUserByUsername(userdetails.getUsername());
		Trade trade=new Trade();
		trade.setBorrow(sellername.getId());
		trade.setBorrowname(sellername.getDisplayName());
		trade.setSeller(chuzuren.getId());;
		trade.setSellername(chuzuren.getDisplayName());
		trade.setThing(message.getNeed());
		trade.setGoodsname(message.getName());
		trade.setAssign(0);
		trade.setEnsure(1);
		trade.setSuccess(0);
		trade.setThingsorneeds(0);
		String judge=this.trademain.addmessage(trade);	
		request.setAttribute("judge", judge);
		model.setViewName("tiles/success");	
		return model;
	}
	
	
	//卖者确认租借
	@RequestMapping(value="/borrowensure/{groupId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView BorrowEnsure(@PathVariable Integer groupId,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
		Trade trade=this.trademain.getTrade(groupId);
		if(trade!=null)
		{
		if(use.getId().equals(trade.getBorrow()) || use.getId().equals(trade.getSeller()))
		{
		trade.setEnsure(1);
		trade.setAssign(trade.getAssign());
	    trade.setBorrow(trade.getBorrow());
		trade.setBorrowname(trade.getBorrowname());
		trade.setGoodsname(trade.getGoodsname());
		trade.setSeller(trade.getSeller());
		trade.setSellername(trade.getSellername());
		trade.setSuccess(1);
		trade.setThing(trade.getThing());
		trade.setTrade(trade.getTrade());
		trade.setThingsorneeds(trade.getThingsorneeds());
		String judge=this.trademain.updatemessage(trade);	
		IDetailmessage mess=this.detailmessage.getmessage(trade.getThing());
		Detailmessage message=new Detailmessage();
		message.setaddr(mess.getaddr());
		message.setDate(mess.getDate());
		message.setkind(mess.getkind());
		message.setName(mess.getName());
		message.setnumber(mess.getnumber());
		message.setoveranalyzed(0);
		message.setphone(mess.getphone());
		message.setpicname(mess.getpicname());
		message.setonepicturename(mess.getonepicturename());
		message.settwopicturename(mess.gettwopicturename());
		message.setPrice(mess.getPrice());
		message.setThingsDesc(mess.getThingsDesc());
		message.setthingsId(mess.getthingsId());
		message.setthingsLat(mess.getthingsLat());
		message.setthingsLng(mess.getthingsLng());
		this.detailmessage.updatemessage(message);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/success");	
		}
		else{
			model.setViewName("tiles/filas");
		}
		}
		else{
			model.setViewName("tiles/filas");
		}
		return model;
	}
	
	//卖者不租借
	@RequestMapping(value="/borrownoensure/{groupId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView BorrowNoEnsure(@PathVariable Integer groupId,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
		Trade trade=this.trademain.getTrade(groupId);
		if(trade!=null)
		{
		if(use.getId().equals(trade.getBorrow())||use.getId().equals(trade.getSeller()))
		{
		trade.setEnsure(0);
		trade.setAssign(trade.getAssign());
		trade.setBorrow(trade.getBorrow());
		trade.setBorrowname(trade.getBorrowname());
		trade.setGoodsname(trade.getGoodsname());
		trade.setSeller(trade.getSeller());
		trade.setSellername(trade.getSellername());
		trade.setSuccess(3);
		trade.setThing(trade.getThing());
		trade.setTrade(trade.getTrade());
		trade.setThingsorneeds(trade.getThingsorneeds());
		String judge=this.trademain.updatemessage(trade);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/success");	
		}
		else{
			model.setViewName("tiles/filas");
		}
		}
		else{
			model.setViewName("tiles/filas");
		}
		return model;
	}
	
	//求租者确认租借
	@RequestMapping(value="/sellerensure/{groupId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView SellerEnsure(@PathVariable Integer groupId,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
		Trade trade=this.trademain.getTrade(groupId);
		if(trade!=null)
		{
		if(use.getId().equals(trade.getBorrow())||use.getId().equals(trade.getSeller()))
		{
		trade.setEnsure(trade.getEnsure());
		trade.setAssign(1);
		trade.setBorrow(trade.getBorrow());
		trade.setBorrowname(trade.getBorrowname());
		trade.setGoodsname(trade.getGoodsname());
		trade.setSeller(trade.getSeller());
		trade.setSellername(trade.getSellername());
		trade.setSuccess(1);
		trade.setThing(trade.getThing());
		trade.setTrade(trade.getTrade());
		trade.setThingsorneeds(trade.getThingsorneeds());
		String judge=this.trademain.updatemessage(trade);	
		INeed mess=this.need.Search(trade.getThing());
		Need message=new Need();
		message.setAddr(mess.getAddr());
		message.setDate(mess.getDate());
		message.setKind(mess.getKind());
		message.setName(mess.getName());
		message.setNumber(mess.getNumber());
		message.setOveranalyzed(0);
		message.setPhone(mess.getPhone());
		message.setOnepicture(mess.getOnepicture());
		message.setTwopicture(mess.getTwopicture());
		message.setThreepicture(mess.getThreepicture());
		message.setPrice(mess.getPrice());
		message.setDescs(mess.getDescs());
		message.setNeed(mess.getNeed());
		message.setLat(mess.getLat());
		message.setLng(mess.getLng());
		this.need.updatemessage(message);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/success");	
		}
		}
		return model;
	}
	
	//求租者不租借
	@RequestMapping(value="/sellernoesure/{groupId}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView SellerNoEnsure(@PathVariable Integer groupId,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
		Trade trade=this.trademain.getTrade(groupId);
		if(trade!=null)
		{
		if(use.getId().equals(trade.getBorrow())||use.getId().equals(trade.getSeller()))
		{
		trade.setEnsure(trade.getEnsure());
		trade.setAssign(3);
		trade.setBorrow(trade.getBorrow());
		trade.setBorrowname(trade.getBorrowname());
		trade.setGoodsname(trade.getGoodsname());
		trade.setSeller(trade.getSeller());
		trade.setSellername(trade.getSellername());
		trade.setSuccess(3);
		trade.setThing(trade.getThing());
		trade.setTrade(trade.getTrade());
		trade.setThingsorneeds(trade.getThingsorneeds());
		String judge=this.trademain.updatemessage(trade);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/success");	
		}
		else{
			model.setViewName("tiles/filas");
		}
		}
		else{
			model.setViewName("tiles/filas");
		}
		return model;
	}
	
	
	//租者取消租借
	@RequestMapping(value="/cancelborrow/{tradeid}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView cancelborrow(@PathVariable Integer tradeid,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
		Trade trade=this.trademain.getTrade(tradeid);
		if(trade!=null)
		{
		if(use.getId().equals(trade.getBorrow())||use.getId().equals(trade.getSeller()))
		{
		trade.setAssign(0);
		trade.setEnsure(trade.getEnsure());
		trade.setBorrow(trade.getBorrow());
		trade.setBorrowname(trade.getBorrowname());
		trade.setGoodsname(trade.getGoodsname());
		trade.setSeller(trade.getSeller());
		trade.setSellername(trade.getSellername());
		trade.setSuccess(trade.getSuccess());
		trade.setThing(trade.getThing());
		trade.setTrade(trade.getTrade());
		trade.setThingsorneeds(trade.getThingsorneeds());
		String judge=this.trademain.updatemessage(trade);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/success");	
		}
		else{
			model.setViewName("tiles/filas");
		}
		}
		else{
			model.setViewName("tiles/filas");
		}
		return model;
	}
	
	//有物品者取消出租
	@RequestMapping(value="/cancelseller/{tradeid}")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView cancelseller(@PathVariable Integer tradeid,ModelAndView model,HttpServletRequest request,HttpServletResponse response){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
		Trade trade=this.trademain.getTrade(tradeid);
		if(trade!=null)
		{
		if(use.getId().equals(trade.getBorrow())||use.getId().equals(trade.getSeller()))
		{
		trade.setAssign(trade.getAssign());
		trade.setEnsure(0);
		trade.setBorrow(trade.getBorrow());
		trade.setBorrowname(trade.getBorrowname());
		trade.setGoodsname(trade.getGoodsname());
		trade.setSeller(trade.getSeller());
		trade.setSellername(trade.getSellername());
		trade.setSuccess(trade.getSuccess());
		trade.setThing(trade.getThing());
		trade.setTrade(trade.getTrade());
		trade.setThingsorneeds(trade.getThingsorneeds());
		String judge=this.trademain.updatemessage(trade);
		request.setAttribute("judge", judge);
		model.setViewName("tiles/success");	
		}
		else{
			model.setViewName("tiles/filas");
		}
		}
		else{
			model.setViewName("tiles/filas");
		}
		return model;
	}
	
	//正在交易情况
	@RequestMapping(value="/tradeing")
	@ResponseBody
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView Tradeing(ModelAndView model,HttpServletRequest request,HttpServletResponse response,AcctUserSearchCriteria search){
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usename=userDetails.getUsername();
		IUser use=this.userhelper.findUserByUsername(usename);
		GridData<Trade> grid = new GridData<Trade>();
		GridData<Trade> gridthings = new GridData<Trade>();//物品的无论租借
		GridData<Trade> gridneeds = new GridData<Trade>();//需求的无论租借
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);//总页面
		//物品
		SearchResponse<ITrade> searchResp=this.trademain.searchsuccess(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		SearchResponse<ITrade> newsearchResp=this.trademain.searchsuccessSeller(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		//需求
		SearchResponse<ITrade> searchResp1=this.trademain.searchsuccess1(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		SearchResponse<ITrade> newsearchResp1=this.trademain.searchsuccessSeller1(new SearchRequest<AcctUserSearchCriteria>(search, pgm),use.getId());
		List<Trade> displays = new ArrayList<Trade>();	
		List<Trade> displaysthings = new ArrayList<Trade>();
		List<Trade> displaysneeds = new ArrayList<Trade>();
		List<ITrade> userGroupsthings = searchResp.getResults();
		List<ITrade> userGroupsneeds = newsearchResp.getResults();
		List<ITrade> userGroupsthings1 = searchResp1.getResults();
		List<ITrade> userGroupsneeds1 = newsearchResp1.getResults();
		//物品
		if (searchResp.getTotalRecords() > 0) {
			for (int i = 0; i < userGroupsthings.size(); i++) {
				ITrade user = userGroupsthings.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setThing(user.getThing());
			    display.setTrade(user.getTrade());
				displays.add(display);
				displaysthings.add(display);
			}
		}
        if(newsearchResp.getTotalRecords()>0)
		{
			for (int i = 0; i < userGroupsneeds.size(); i++) {
				ITrade user = userGroupsneeds.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setThing(user.getThing());
			    display.setTrade(user.getTrade());
				displays.add(display);
				displaysthings.add(display);
			}
		}
        //需求
		if (searchResp1.getTotalRecords() > 0) {
			for (int i = 0; i < userGroupsthings1.size(); i++) {
				ITrade user = userGroupsthings1.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setThing(user.getThing());
			    display.setTrade(user.getTrade());
				displays.add(display);
				displaysneeds.add(display);
			}
		}
        if(newsearchResp1.getTotalRecords()>0)
		{
			for (int i = 0; i < userGroupsneeds1.size(); i++) {
				ITrade user = userGroupsneeds1.get(i);
				Trade display = new Trade();
			    display.setSuccess(user.getSuccess());
			    display.setSellername(user.getSellername());
			    display.setGoodsname(user.getGoodsname());
			    display.setThing(user.getThing());
			    display.setTrade(user.getTrade());
				displays.add(display);
				displaysneeds.add(display);
			}
		}
		grid.setDatas(displays);
		if(displays.size()==0){
			request.setAttribute("judge", 0);
		}
		else{
			request.setAttribute("judge", 1);
		}
		gridthings.setDatas(displaysthings);
		gridneeds.setDatas(displaysneeds);
		pgm.setTotalRecord(searchResp.getTotalRecords()+newsearchResp.getTotalRecords()+searchResp1.getTotalRecords()+newsearchResp1.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("gridthings", gridthings)
		     .addObject("gridneeds", gridneeds)
		     .addObject("grid", grid).setViewName("tiles/includes/tradeing");
		return model;	
	}
	

	
	@RequestMapping(value="/thingsdetailmessage/{thing}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView ThingsGetMessage(@PathVariable Integer thing,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
	Trade id=this.trademain.getTrade(thing);
	if(id!=null){
	IUser borrow=this.userhelper.getUserById(id.getBorrow());
	IUser useweixin=this.userhelper.getUserById(id.getSeller());
	if(use.getId().equals(id.getBorrow())||use.getId().equals(id.getSeller()))
	{
	IDetailmessage things=this.detailmessage.getmessage(id.getThing());
    String thingsdesc=things.getThingsDesc();
    String thingsdate=things.getDate();
    Double thingslng=things.getthingsLng();
    Double thingslat=things.getthingsLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    Integer number=things.getnumber();
    IUser user=this.userhelper.getUserById(number);
    String usename=user.getUsername();
    String thingscomment="";
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingscomment", thingscomment);
    request.setAttribute("thingsonepicture",things.getpicname());
    request.setAttribute("thingstwopicture",things.getonepicturename());
    request.setAttribute("thingsthreepicture",things.gettwopicturename());
    request.setAttribute("thingsid", things.getthingsId());
    request.setAttribute("thingsphone", things.getphone());
    request.setAttribute("thingsnumber", usename);
    //自己
    request.setAttribute("borrowweixin",borrow.getWeixin());
    request.setAttribute("borrowname", borrow.getDisplayName());
    request.setAttribute("borrowqq", borrow.getQqNum());
    request.setAttribute("borrowphone", borrow.getPhone());
    request.setAttribute("borrowpicture",borrow.getPicture());
    //对方
    request.setAttribute("selleruseweixin",useweixin.getWeixin());
    request.setAttribute("sellername", useweixin.getDisplayName());
    request.setAttribute("sellerqq", useweixin.getQqNum());
    request.setAttribute("sellerphone", useweixin.getPhone());
    request.setAttribute("sellerpicture", useweixin.getPicture());
    model.setViewName("tiles/includes/tradethings");
	}
	else{
		model.setViewName("tiles/filas");
	}
	}
	else{
		model.setViewName("tiles/filas");
	}
    return model;
	
	}
	
	
	@RequestMapping(value="/needsdetailmessage/{thing}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView NeedGetMessage(@PathVariable Integer thing,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
	
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
	Trade id=this.trademain.getTrade(thing);
	if(id!=null){
	IUser borrow=this.userhelper.getUserById(id.getBorrow());
	IUser useweixin=this.userhelper.getUserById(id.getSeller());
	INeed things=this.need.Search(id.getThing());
	if(use.getId().equals(borrow.getId())||use.getId().equals(useweixin.getId()))
	{
    String thingsdesc=things.getDescs();
    String thingsdate=things.getDate();
    Double thingslng=things.getLng();
    Double thingslat=things.getLat();
    Double thingsprice=things.getPrice();
    String thingsname=things.getName();
    Integer number=things.getNumber();
    IUser user=this.userhelper.getUserById(number);
    String usename=user.getUsername();
    String thingscomment="";
    request.setAttribute("thingsdesc", thingsdesc);
    request.setAttribute("thingsdate", thingsdate);
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    request.setAttribute("thingsprice", thingsprice);
    request.setAttribute("thingsname", thingsname);
    request.setAttribute("thingscomment", thingscomment);
    request.setAttribute("thingsonepicture",things.getOnepicture());
    request.setAttribute("thingstwopicture",things.getTwopicture());
    request.setAttribute("thingsthreepicture",things.getThreepicture());
    request.setAttribute("thingsid", things.getNeed());
    request.setAttribute("thingsphone", things.getPhone());
    request.setAttribute("thingsnumber", usename);
    //自己
    request.setAttribute("borrowweixin",borrow.getWeixin());
    request.setAttribute("borrowname", borrow.getDisplayName());
    request.setAttribute("borrowqq", borrow.getQqNum());
    request.setAttribute("borrowphone", borrow.getPhone());
    request.setAttribute("borrowpicture",borrow.getPicture());
    //对方
    request.setAttribute("selleruseweixin",useweixin.getWeixin());
    request.setAttribute("sellername", useweixin.getDisplayName());
    request.setAttribute("sellerqq", useweixin.getQqNum());
    request.setAttribute("sellerphone", useweixin.getPhone());
    request.setAttribute("sellerpicture", useweixin.getPicture());
    model.setViewName("tiles/includes/tradeneeds");
	}
	else{
	model.setViewName("tiles/filas");	
	}
	}
	else{
	model.setViewName("tiles/filas");
	}
    return model;
	
	}
	
	@RequestMapping(value="/tradesuccess/{thing}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView tradesuccess(@PathVariable Integer thing,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
	
	AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	IUser use=this.userhelper.findUserByUsername(userDetails.getUsername());
	Trade trade=this.trademain.getTrade(thing);
	if(trade!=null)
	{
	if(use.getId().equals(trade.getBorrow())||use.getId().equals(trade.getSeller())){
	trade.setAssign(3);
	trade.setEnsure(3);
	trade.setBorrow(trade.getBorrow());
	trade.setBorrowname(trade.getBorrowname());
	trade.setGoodsname(trade.getGoodsname());
	trade.setSeller(trade.getSeller());
	trade.setSellername(trade.getSellername());
	trade.setSuccess(3);
	trade.setThing(trade.getThing());
	trade.setTrade(trade.getTrade());
	String judge=this.trademain.updatemessage(trade);
	request.setAttribute("judge", judge);
    model.setViewName("tiles/success");
	}
	else{
	model.setViewName("tiles/filas");
	}
	}
	else{
	model.setViewName("tiles/filas");
	}
    return model;
	
	}

	
	
	
	
	@RequestMapping(value="/position/{thing}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.TRADE, operationType=AuthOperationConfiguration.TRADE_VIEW)
	public ModelAndView Position(@PathVariable Integer thing,ModelAndView model,HttpServletResponse response,HttpServletRequest request)
	{
	Integer useid=thing;	
	IDetailmessage things=this.detailmessage.getmessage(useid);
    Double thingslng=things.getthingsLat();
    Double thingslat=things.getthingsLat();
    request.setAttribute("thingslng", thingslng);
    request.setAttribute("thingslat", thingslat);
    model.setViewName("tiles/includes/positionofmap");
    return model;
	}
	
	
}
