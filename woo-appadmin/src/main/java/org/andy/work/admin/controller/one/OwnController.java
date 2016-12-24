package org.andy.work.admin.controller.one;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.detail.MenuMessage;
import org.andy.work.admin.detail.TradeMessage;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.helper.UserSessionHelper;
import org.andy.work.admin.permission.OperationType;
import org.andy.work.admin.permission.Permission;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.ITransacationofusertopurchase;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.ITransacationofusertoproductMaintenanceService;
import org.andy.work.appserver.service.ITransacationofusertopurchaseMaintenanceService;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.secure.Encrypt;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OwnController {

	@Resource
	private UserSessionHelper userSessionHelper;

	@Resource
	private UserHelper userHelper;
	
	@Autowired
	private ICompanyMaintenanceService companyHelper;
	
	@Resource 
	private ITransacationofusertoproductMaintenanceService productoftrande;
	
	@Resource 
	private ITransacationofusertopurchaseMaintenanceService purchaseoftrande;
	
	@RequestMapping(value="/own")
	public ModelAndView Index(HttpServletRequest request){
		
		List<MenuMessage> menus=this.getMenu();
		ModelAndView model=new ModelAndView();
		String name="";
		String pic="";
		if(userSessionHelper.isUserLogined()){
			AdminUserDetails user=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			IUser use=this.userHelper.findUserByUsername(user.getUsername());
		    if(use!=null){
			name=use.getDisplayName();
			if(use.getHead().equals("null")){
				pic="user.jpg";
			}
			else{
				pic=use.getHead();
			}
		    }
		    else{
		    ICompany company=this.companyHelper.findUserByusername(user.getUsername());
		    name=company.getDisplayName();
			pic="user.jpg";
		    }
		}
		else{
			name="未登陆";
			pic="user.jpg";
		}
		model.addObject("menus", menus).addObject("name",name).addObject("pic",pic).setViewName("tiles/own/own");
		return model;
	}
	
	public List<MenuMessage> getMenu(){
		
		List<MenuMessage> menus=new ArrayList<MenuMessage>();
		if(userSessionHelper.isUserLogined()){
		AdminUserDetails userdetail=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<Permission> permissions = Permission.getMenu(userdetail.getUser_group());
			for(Permission permission:permissions){
				MenuMessage menu=new MenuMessage();
                menu.setName(permission.getName());
                menu.setUrl(permission.getUrl());
                menus.add(menu);
		   }
		}
		else{
			MenuMessage menu=new MenuMessage();
			menu.setName("账号登陆");
			menu.setUrl("/secure/login");
			menus.add(menu);
		}
		return menus;
	}
	
	@RequestMapping(value="/own/edit")
	@AuthOperation(roleType=RoleType.USER,operationType=OperationType.VIEW)
	public ModelAndView editMyself(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userHelper.findUserByUsername(admin.getUsername());
		request.setAttribute("name", use.getDisplayName());
		request.setAttribute("pic", use.getHead());
		request.setAttribute("phone", use.getPhone());
		ModelAndView model=new ModelAndView();
		model.setViewName("tiles/own/editmyselfinfo");
		return model;
	}
	
	@RequestMapping(value="/own/edit/update")
	@AuthOperation(roleType=RoleType.USER,operationType=OperationType.VIEW)
	public ModelAndView updateMyself(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView model=new ModelAndView();
		IUser user=this.userHelper.findUserByUsername(admin.getUsername());
		String head=user.getHead();
		Encrypt encry=new Encrypt();
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		Pattern namepattern=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9_.]+$");
		Pattern phonepattern=Pattern.compile("^[0-9]+$");
		if(name!=null){
		if(namepattern.matcher(name).matches()){
			if(phonepattern.matcher(phone).matches()){
					user.setHead(head);
					user.setDisplayName(name);
					user.setPhone(phone);
					this.userHelper.saveUser(user);
					model.setViewName("success");
			}
			else{
				model.setViewName("404");
			}
		}
		else{
			model.setViewName("404");
		}
		}
		return model;
	}
	
	
	@RequestMapping(value="/own/commodity")
	@AuthOperation(roleType=RoleType.USER,operationType=OperationType.VIEW)
	public ModelAndView searchProduct(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView model=new ModelAndView();
		IUser user=this.userHelper.findUserByUsername(admin.getUsername());
		List<ITransacationofusertoproduct> productoftrade=this.productoftrande.searchBuy(user.getId()); 
		GridData<TradeMessage> productgird=new GridData<TradeMessage>();
		PagingManagement pgm=PagingHelper.buildPagingManagement(request);
		List<TradeMessage> listmessage=new ArrayList<TradeMessage>();
		List<TradeMessage> listmessages=new ArrayList<TradeMessage>();
		Encrypt encry=new Encrypt();
		if(productoftrade!=null){
			for(ITransacationofusertoproduct trade:productoftrade){
				IProduct product=trade.getProductid();
				TradeMessage message=new TradeMessage();
				message.setName(product.getName());
				message.setAddr(product.getAddr());
				message.setInfomation(product.getInfomation());
				String id=encry.Encrupt(Integer.valueOf(product.getId()));
				message.setPrice(String.valueOf(product.getPrice()));
				message.setThingsid(id);
				message.setThingsurl("commodity");
				message.setLineid(id);
				message.setLineurl("line");
				message.setPicone(product.getPicone());
				listmessage.add(message);
			}
		}
		int y=pgm.getFirstResult();
		int z=listmessage.size()-y;
		if(z>20){
		   for(int i=0;i<20;i++){
			TradeMessage product=listmessage.get(y);
			listmessages.add(product);
			y++;
		   }
		}
		else if(z<=20&&z>=0){
		   for(int i=0;i<=z-1;i++){
			 TradeMessage product=listmessage.get(y);
			 listmessages.add(product);
			 y++;
		   }
		}
		else{
			if(listmessage.size()<20){
			listmessages=listmessage;}
		}
		productgird.setDatas(listmessages);
		pgm.setTotalRecord(listmessage.size());
		productgird.setPgm(pgm);
		model.addObject("grid", productgird).setViewName("tiles/own/commodity");
		return model;
	}
	
	
	@RequestMapping(value="/own/purchase")
	@AuthOperation(roleType=RoleType.USER,operationType=OperationType.VIEW)
	public ModelAndView searchPurchase(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView model=new ModelAndView();
		IUser user=this.userHelper.findUserByUsername(admin.getUsername());
		List<ITransacationofusertopurchase> productoftrade=this.purchaseoftrande.searchBuy(user.getId()); 
		GridData<TradeMessage> productgird=new GridData<TradeMessage>();
		PagingManagement pgm=PagingHelper.buildPagingManagement(request);
		List<TradeMessage> listmessage=new ArrayList<TradeMessage>();
		List<TradeMessage> listmessages=new ArrayList<TradeMessage>();
		Encrypt encry=new Encrypt();
		if(productoftrade!=null){
			for(ITransacationofusertopurchase trade:productoftrade){
				IPurchase product=trade.getPurchaseid();
				TradeMessage message=new TradeMessage();
				message.setName(product.getName());
				message.setAddr(product.getAddr());
				message.setInfomation(product.getInfomation());
				String id=encry.Encrupt(Integer.valueOf(product.getId()*(-1)));
				message.setPrice(String.valueOf(product.getPrice()));
				message.setThingsid(id);
				message.setThingsurl("commodity");
				message.setLineid(id);
				message.setLineurl("line");
				message.setPicone(product.getPicone());
				listmessage.add(message);
			}
		}
		int y=pgm.getFirstResult();
		int z=listmessage.size()-y;
		if(z>20){
		   for(int i=0;i<20;i++){
			TradeMessage product=listmessage.get(y);
			listmessages.add(product);
			y++;
		   }
		}
		else if(z<=20&&z>=0){
		   for(int i=0;i<=z-1;i++){
			 TradeMessage product=listmessage.get(y);
			 listmessages.add(product);
			 y++;
		   }
		}
		else{
			if(listmessage.size()<20){
			listmessages=listmessage;}
		}
		productgird.setDatas(listmessages);
		pgm.setTotalRecord(listmessage.size());
		productgird.setPgm(pgm);
		model.addObject("grid", productgird).setViewName("tiles/own/purchase");
		return model;
	}
	
	@RequestMapping(value="/own/cash")
	@AuthOperation(roleType=RoleType.USER,operationType=OperationType.VIEW)
	public ModelAndView showMoney(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser use=this.userHelper.findUserByUsername(admin.getUsername());
		request.setAttribute("name", use.getDisplayName());
		if(use.getHead().equals("null")){
		request.setAttribute("pic", "user.jpg");
		}else{
		request.setAttribute("pic", use.getHead());
		}
		request.setAttribute("phone", use.getPhone());
		request.setAttribute("cash", use.getMoney());
		ModelAndView model=new ModelAndView();
		model.setViewName("tiles/own/userbalance");
		RegisterController emailss=new RegisterController();
		return model;
	}
	
}
