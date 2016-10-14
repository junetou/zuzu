package org.andy.work.admin.helper;

import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.service.IUserMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Component;

@Component
public class UserHelper
{
	@Resource
	private IUserMaintenanceService userMaintenanceService;
	
	public IUser findUserByUsername(String username) {
		return this.userMaintenanceService.findUserByUsername(username);
	}
	
	public IUser createUser() {
		return this.userMaintenanceService.createUser();
	}
	
	public void saveUser(IUser user) {
		this.userMaintenanceService.saveUser(user);
	}
	
	public IUser getUserById(Integer id) {
		return this.userMaintenanceService.getUserById(id);
	}
	
	public void removeUser(Integer id) {
		this.userMaintenanceService.removeUser(id);
	}
	
	public void setUserMaintenanceService(IUserMaintenanceService userMaintenanceService) {
		this.userMaintenanceService = userMaintenanceService;
	}
	
	public IUserGroup createUserGroup() {
		return this.userMaintenanceService.createUserGroup();
	}
	
	public IUserGroup saveUserGroup(IUserGroup userGroup) {
		return this.userMaintenanceService.saveUserGroup(userGroup);
	}
	
	public List<IUserGroup> getAllUserGroups() {
		return this.userMaintenanceService.getUserGroup();
	}
	
	public void removeUserGroup(Integer id) {
		this.userMaintenanceService.removeUserGroup(id);
	}
	
	public boolean isUserGroupExists(String groupName) {
		IUserGroup userGroup = this.userMaintenanceService.getUserGroupByName(groupName);
		return userGroup != null;
	}
	
	public IUserGroup getUserGroup(Integer id) {
		return this.userMaintenanceService.getUserGroupById(id);
	}
	
	public List<String> getGroupUsers(Integer groupId) {
		return this.userMaintenanceService.getGroupUsers(groupId);
	}

	public SearchResponse<IUserGroup> searchSearchResponse(SearchRequest<String> searchReq) {
		return this.userMaintenanceService.searchSearchResponse(searchReq);
	}

	public SearchResponse<IUser> searchUser(SearchRequest<AcctUserSearchCriteria> searchReq) {
		return this.userMaintenanceService.searchUser(searchReq);
	}

	public boolean isAccount(String account) {
		return this.userMaintenanceService.isAccount(account);
	}

	public boolean isUserGroup(Integer id) {
		return this.userMaintenanceService.isUserGroup(id);
	}
}
