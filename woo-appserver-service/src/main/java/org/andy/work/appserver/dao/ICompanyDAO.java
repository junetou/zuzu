package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.dao.impl.GenericDAO;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface ICompanyDAO extends IGenericDAO {

	ICompany findUserByusername(String username);
	
	List<ICompany> findRegisterUser();

	List<String> getGroupUsers(Integer groupId);

	IUserGroup getUserGroupByName(String name);

	SearchResponse<ICompany> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq);

	List<IUserGroup> getUserGroup();
	
	void saveUser(ICompany user);
	
	void updateUser(ICompany user);
	
	void deleteUser(ICompany user);

	boolean isAccount(String account);

	boolean isUserGroup(Integer id);

	boolean hasusrname(String usrname);
	
	List<ICompany> SearchAllCompany();
	
	List<ICompany> SearchCompany(Integer id);
	
}
