package org.andy.work.admin.permission;

import java.util.ArrayList;
import java.util.List;

public enum Permission {
	
	//系统管理
	ADMIN_ACCOUNT("ADMIN_ACCOUNT", "admin/admin-account", MenuType.ADMIN_MENU, "", new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.RESET, OperationType.FREEZE}),
	SYSTEM_ROLE("SYSTEM_ROLE", "admin/system-role", MenuType.ADMIN_MENU, "", new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE}),
	
	//内容管理
	ARTICLE("ARTICLE", "web/article", MenuType.WEB_MENU, "", new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE, OperationType.EXAMINE, OperationType.RELEASE, OperationType.EXPORT}),
	ARTICLE_CATEGORY("ARTICLE_CATEGORY", "web/article/category", MenuType.WEB_MENU, "", new String[]{OperationType.VIEW, OperationType.ADD, OperationType.EDIT, OperationType.DELETE}),
	
	//物品操作
	ADDTHINGS("ADDTHINGS","things/showaddview",MenuType.THINGS_MENU,"",new String[]{OperationType.ADD}),
	EDITTHINGS("EDITTHINGS","things/editthings",MenuType.THINGS_MENU,"",new String[]{OperationType.EDIT}),
	
	//需求操作
	ADDNEEDS("ADDNEEDS","needs/showaddview",MenuType.NEEDS_MENU,"",new String[]{OperationType.ADD}),
	EDITNEEDS("EDITNEEDS","needs/editthings",MenuType.NEEDS_MENU,"",new String[]{OperationType.EDIT}),
	
	//交易操作
	NEEDSTRADE("NEEDSTRADE","trade/need",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW}),
	THINGSTRADE("THINGSTRADE","trade/borrow",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW}),
	OTHERTHINGSTRADE("OTHERTHINGSTRADE","trade/sellerensureborrow",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW}),
	TRADEING("TRADEING","trade/tradeing",MenuType.TRADE_MENU,"",new String[]{OperationType.VIEW});
	
	
	
	private String code;
	private String url;
	private int type;
	private String classIcon;
	private Integer[] projects;
	private String[] opts;
	
	private Permission(String code, String url, int type, String classIcon, String[] opts) {
		this.code = code;
		this.url = url;
		this.type = type;
		this.opts = opts;
		this.classIcon = classIcon;
	}
	
	private Permission(String code, String url, int type, String classIcon, Integer[] projects, String[] opts) {
		this.code = code;
		this.url = url;
		this.type = type;
		this.projects = projects;
		this.opts = opts;
		this.classIcon = classIcon;
	}
	
	public static List<Permission> getMenus(int menuType) {
		return getMenus(menuType, null);
	}
	
	public static List<Permission> getMenus(int menuType, Integer projectType) {
		List<Permission> permissions = new ArrayList<Permission>();
		for (Permission p : Permission.values()) {
			if (p.type == menuType && p.containsProjectType(projectType)) {
				permissions.add(p);
			}
		}
		
		return permissions;
	}
	
	private boolean containsProjectType(Integer projectType) {
		if (this.projects == null) {
			return true;
		}
		
		if (projectType == null) {
			return false;
		}
		
		for (Integer type: this.projects) {
			if (type.intValue() == projectType.intValue()) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Permission getByCode(String code) {
		for (Permission p : Permission.values()) {
			if (p.getCode().equals(code)) {
				return p;
			}
		}
		
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getUrl() {
		return url;
	}

	public int getType() {
		return type;
	}

	public String[] getOpts() {
		return opts;
	}

	public Integer[] getProjects() {
		return projects;
	}

	public String getClassIcon() {
		return classIcon;
	}

	public void setClassIcon(String classIcon) {
		this.classIcon = classIcon;
	}
}
