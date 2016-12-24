package org.andy.work.admin.helper;

import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.IUserMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyHelper
{
	@Autowired
	private ICompanyMaintenanceService userMaintenanceService;
	
	public ICompany findUserByUsername(String username) {
		return this.userMaintenanceService.findUserByusername(username);
	}
	
	public List<IUserGroup> getAllUserGroups() {
		return this.userMaintenanceService.getUserGroup();
	}
	

	public boolean isUserGroupExists(String groupName) {
		IUserGroup userGroup = this.userMaintenanceService.getUserGroupByName(groupName);
		return userGroup != null;
	}
	
	public List<String> getGroupUsers(Integer groupId) {
		return this.userMaintenanceService.getGroupUsers(groupId);
	}

	public boolean isAccount(String account) {
		return this.userMaintenanceService.isAccount(account);
	}

	public boolean isUserGroup(Integer id) {
		return this.userMaintenanceService.isUserGroup(id);
	}
	
	public List<ICompany> SearchCompany(Integer id){
		return this.userMaintenanceService.SearchCompany(id);
	}
	
	public List<ICompany> SearchAllCompany(){
		
		return this.userMaintenanceService.SearchAllCompany();
	}
}
