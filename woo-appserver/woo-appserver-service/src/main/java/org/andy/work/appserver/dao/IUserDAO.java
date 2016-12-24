package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IUserDAO extends IGenericDAO {

	IUser findUserByusername(String username);

	List<String> getGroupUsers(Integer groupId);

	IUserGroup getUserGroupByName(String name);

	SearchResponse<IUser> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq);

	List<IUserGroup> getUserGroup();

	boolean isAccount(String account);

	boolean isUserGroup(Integer id);

	boolean hasusrname(String usrname);
	
}
