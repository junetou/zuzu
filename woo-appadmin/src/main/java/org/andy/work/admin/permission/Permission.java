package org.andy.work.admin.permission;

import java.util.ArrayList;
import java.util.List;

/*
枚举，用于设置导航栏
*/

public enum Permission {
    
	//个人
	PERSION("个人资料","/own/edit","1"),
	PERSIONCOMMODITY("我已经购买的商品信息","/own/commodity","1"),
	PERSIONPURCHASE("我已经购买的采购信息","/own/purchase","1"),
	PERSIONMONEY("个人余额","/own/cash","1"),
	PERSIONCANEL("注销账号","/logout","1"),
	//公司
	COMPANY("公司资料","/CoLtd/edit","2"),
	COMPANYBUY("本公司发布的商品信息","/CoLtd/commodity","2"),
	COMPANYPURCHASE("本公司发布的采购信息","/CoLtd/purchase","2"),
	COMPANYMONEY("本公司余额","/CoLtd/cash","2"),
	COMPANYCANEL("注销账号","/logout","2"),
	
	ADMIN("管理注册账号","/admin/auth","3"),
	ADMINFED("投诉","/admin/show/fed","3"),
	ADMINFINDPASSWD("客户找回密码","/admin/findpasswd/show","3"),
	ADMINCANEL("注销账号","/logout","3");
	
	private String name;
	private String url;
    private String versions;//用户为1,公司为2
    
	private Permission(String names,String url,String version){
		
		this.name=names;
		this.url=url;
		this.versions=version;
	}
	
	public static List<Permission> getMenu(String vs){
		
		List<Permission> persions=new ArrayList<Permission>();
		for(Permission persion:Permission.values()){
			if(persion.getVersion().equals(vs)){
				persions.add(persion);
			}
		}
		return persions;
	}
	
	public String getName(){
		
		return name;
	}
	
	public String getUrl(){
		
		return url;
	}
	
	public String getVersion(){
		
		return versions;
	}
	
}
