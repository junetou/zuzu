package org.andy.work.appserver.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.ICompanyDAO;
import org.andy.work.appserver.dao.IUserDAO;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.appserver.model.impl.UserGroup;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.IUserMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CompanyMaintenanceService implements ICompanyMaintenanceService {
	
	@Resource
	private ICompanyDAO companydao;
	
	@Override
	public ICompany findUserByusername(String username){
		
		return this.companydao.findUserByusername(username);
	}
	
	@Override
	public List<ICompany> findRegisterUsr(){
		
		return this.companydao.findRegisterUser();
	}

	@Override
	public List<String> getGroupUsers(Integer groupId){
		
		return this.companydao.getGroupUsers(groupId);
	}
    
	@Override
	public void saveUser(ICompany user){
		
		this.companydao.saveUser(user);
	}
	
	@Override
	public void updateUser(ICompany user){
		
		this.companydao.updateUser(user);;
	}
	
	@Override
	public void deleteUser(ICompany user){
		
		this.companydao.deleteUser(user);;
	}
	
	
	@Override
	public IUserGroup getUserGroupByName(String name){
		
        return this.companydao.getUserGroupByName(name);
	}

	@Override
	public SearchResponse<ICompany> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq){
		
		return this.companydao.searchUsers(searchReq);
	}

	@Override
	public List<IUserGroup> getUserGroup(){
		
		return this.companydao.getUserGroup();
	}

	@Override
	public boolean isAccount(String account){
		
		return this.companydao.isAccount(account);
	}
	
	@Override
	public boolean isUserGroup(Integer id){
		
		return this.companydao.isUserGroup(id);
	}

	@Override
	public boolean hasusrname(String usrname){
		
		return this.companydao.hasusrname(usrname);
	}
	
	@Override
	public List<ICompany> SearchAllCompany(){
		
		return this.companydao.SearchAllCompany();
	}
	
	@Override
	public List<ICompany> SearchCompany(Integer id){
		
		return this.companydao.SearchCompany(id);
	}
	
}