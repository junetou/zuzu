package org.andy.work.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.helper.SystemHelper;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.obj.MainMenuDisplay;
import org.andy.work.admin.obj.MenuNode;
import org.andy.work.admin.permission.MenuType;
import org.andy.work.admin.permission.OperationType;
import org.andy.work.admin.permission.Permission;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IUser;
import org.andy.work.utils.CommonUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PortalController {
	
	@Resource
	private SystemHelper systemHelper;
	

	@Resource
	private UserHelper userHelper;
	
	@RequestMapping(value="/operation/403")
	public String error() {
		return "tiles/403";
	}
	
	@RequestMapping(value="/portal", method=RequestMethod.GET)
	public void portal(ModelAndView model, HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		
		/*
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser userid=this.userHelper.findUserByUsername(userDetails.getUsername());
	
		List<MainMenuDisplay> menus = new ArrayList<MainMenuDisplay>();
		
		//系统管理
		if (this.hasRole(userDetails, RoleType.ADMIN)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.admin", request));
			menu.setType(MenuType.ADMIN_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.ADMIN_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-cog");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.THINGS)){
			MainMenuDisplay menu= new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.things", request));
			menu.setType(MenuType.THINGS_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.THINGS_MENU.intValue(), model, request));
			menu.setClassIcon("	glyphicon glyphicon-shopping-cart");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.NEEDS)){
			MainMenuDisplay menu= new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.needs", request));
			menu.setType(MenuType.NEEDS_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.NEEDS_MENU.intValue(), model, request));
			menu.setClassIcon("	glyphicon glyphicon-list-alt");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.TRADE)){
			MainMenuDisplay menu= new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.trade", request));
			menu.setType(MenuType.TRADE_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.TRADE_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-yen");
			menus.add(menu);
		}
		request.setAttribute("userid",userid.getId());
		request.setAttribute("name",userid.getDisplayName());
		request.setAttribute("picture", userid.getPicture());
		request.setAttribute("number", userid.getChatnumber());
		model.addObject("menus", menus).setViewName("tiles/mains");
		return model;
		*/
		String path=request.getContextPath();
		response.sendRedirect(path+"/portal/person");
		
	}
	
	@RequestMapping(value="/portal/person", method=RequestMethod.GET)
	public ModelAndView person(ModelAndView model, HttpServletRequest request) {
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser userid=this.userHelper.findUserByUsername(userDetails.getUsername());
		
		List<MainMenuDisplay> menus = new ArrayList<MainMenuDisplay>();
		
		//系统管理
		if (this.hasRole(userDetails, RoleType.ADMIN)) {
			MainMenuDisplay menu = new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.admin", request));
			menu.setType(MenuType.ADMIN_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.ADMIN_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-cog");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.THINGS)){
			MainMenuDisplay menu= new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.things", request));
			menu.setType(MenuType.THINGS_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.THINGS_MENU.intValue(), model, request));
			menu.setClassIcon("	glyphicon glyphicon-shopping-cart");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.NEEDS)){
			MainMenuDisplay menu= new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.needs", request));
			menu.setType(MenuType.NEEDS_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.NEEDS_MENU.intValue(), model, request));
			menu.setClassIcon("	glyphicon glyphicon-list-alt");
			menus.add(menu);
		}
		
		if(this.hasRole(userDetails, RoleType.TRADE)){
			MainMenuDisplay menu= new MainMenuDisplay();
			menu.setName(CommonUtils.getMessage("menu.trade", request));
			menu.setType(MenuType.TRADE_MENU.intValue());
			menu.setNodes(this.loadSubMenus(MenuType.TRADE_MENU.intValue(), model, request));
			menu.setClassIcon("glyphicon glyphicon-yen");
			menus.add(menu);
		}
		
		request.setAttribute("userid",userid.getId());
		request.setAttribute("name",userid.getDisplayName());
		request.setAttribute("picture", userid.getPicture());
		request.setAttribute("number", userid.getChatnumber());
		model.addObject("menus", menus).setViewName("tiles/main");
		return model;
	}
	
	private boolean hasRole(AdminUserDetails userDetails, String role) {
		for (GrantedAuthority g : userDetails.getAuthorities()) {
			if (g.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<MenuNode> loadSubMenus(Integer menuType, ModelAndView model, HttpServletRequest request) {
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();
		
		switch (menuType) {
			case 20:
				if (this.hasNode(userDetails, RoleType.ADMIN)) {
					menus.addAll(this.getAdminMenus(request, userDetails));
				}
				break;
			case 80:
				if(this.hasNode(userDetails, RoleType.THINGS)){
					menus.addAll(this.getThingsNode(request, userDetails));
				}
				break;
			case 90:
				if(this.hasNode(userDetails, RoleType.NEEDS)){
					menus.addAll(this.getNeedsNode(request, userDetails));
				}
				break;
			case 100:
				if(this.hasNode(userDetails, RoleType.TRADE)){
					menus.addAll(this.getTradeNode(request, userDetails));
				}
				break;
 		}
		
		return menus;
	}

	private boolean hasNode(AdminUserDetails userDetails, String role) {
		for (GrantedAuthority g : userDetails.getAuthorities()) {
			if (g.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	private String getUrl(String code, HttpServletRequest request) {
		Permission permission = Permission.getByCode(code);
		if (permission != null) {
			return permission.getUrl();
			//return "portal/"+permission.getUrl();
		}
		return null;
	}
	
	private String getClassIcon(String code) {
		Permission permission = Permission.getByCode(code);
		if (permission != null) {
			return permission.getClassIcon();
		}
		return null;
	}
	
	
	

	
	private LinkedList<MenuNode> getAdminMenus(HttpServletRequest request, AdminUserDetails userDetails) {
		LinkedList<MenuNode> menus = new LinkedList<MenuNode>();

		List<Permission> permissions = Permission.getMenus(MenuType.ADMIN_MENU);
		if (!permissions.isEmpty()) {
			for (Permission perm : permissions) {
				if (userDetails.getPermissions().contains(perm.getCode() + '_' + OperationType.VIEW)) {
					String url = this.getUrl(perm.getCode(), request);
					menus.add(new MenuNode(perm.getCode(), CommonUtils.getMessage("menu." + perm.getCode().toLowerCase(), request.getLocale(), request)
							, 1, MenuType.ADMIN_MENU.toString(), true, false, url, this.getClassIcon(perm.getCode())));
				}
			}
		}
		return menus;
	}
	
	private LinkedList<MenuNode> getThingsNode(HttpServletRequest request, AdminUserDetails userDetails){
		LinkedList<MenuNode> node=new LinkedList<MenuNode>();
		List<Permission> permissions = Permission.getMenus(MenuType.THINGS_MENU);
		if(!permissions.isEmpty()){
			for(Permission permission :permissions){
                    if(userDetails.getPermissions().contains("THINGS"+"_"+OperationType.VIEW)){
					String url=this.getUrl(permission.getCode(), request);
					node.add(new MenuNode(permission.getCode(),CommonUtils.getMessage("menu."+permission.getCode().toLowerCase(),request.getLocale(),request),
							1,MenuType.THINGS_MENU.toString(),true,false,url,this.getClassIcon(permission.getCode())));
			}
			}	
		}
		return node;
	}
	
	private LinkedList<MenuNode> getNeedsNode(HttpServletRequest request, AdminUserDetails userDetails){
		LinkedList<MenuNode> node=new LinkedList<MenuNode>();
		List<Permission> permissions = Permission.getMenus(MenuType.NEEDS_MENU);
		if(!permissions.isEmpty()){
			for(Permission permission :permissions){
                    if(userDetails.getPermissions().contains("NEEDS"+"_"+OperationType.VIEW)){
					String url=this.getUrl(permission.getCode(), request);
					node.add(new MenuNode(permission.getCode(),CommonUtils.getMessage("menu."+permission.getCode().toLowerCase(),request.getLocale(),request),
							1,MenuType.NEEDS_MENU.toString(),true,false,url,this.getClassIcon(permission.getCode())));
			}
			}	
		}
		return node;
	}
	
	private LinkedList<MenuNode> getTradeNode(HttpServletRequest request, AdminUserDetails userDetails){
		LinkedList<MenuNode> node=new LinkedList<MenuNode>();
		List<Permission> permissions = Permission.getMenus(MenuType.TRADE_MENU);
		if(!permissions.isEmpty()){
			for(Permission permission :permissions){
                    if(userDetails.getPermissions().contains("TRADE"+"_"+OperationType.VIEW)){
					String url=this.getUrl(permission.getCode(), request);
					node.add(new MenuNode(permission.getCode(),CommonUtils.getMessage("menu."+permission.getCode().toLowerCase(),request.getLocale(),request),
							1,MenuType.TRADE_MENU.toString(),true,false,url,this.getClassIcon(permission.getCode())));
			}
			}	
		}
		return node;
	}
	
	
	
}
