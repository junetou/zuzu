package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.IFindpassword;

public interface IFindpasswordDAO {

	List<IFindpassword> searchAll();
	
	List<IFindpassword> searchUser(Integer id);
	
	void saveObject(IFindpassword company);
	
	void updateObject(IFindpassword company);
	
}
