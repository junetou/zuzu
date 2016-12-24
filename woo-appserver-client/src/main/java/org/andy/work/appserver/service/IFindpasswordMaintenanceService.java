package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.IFindpassword;

public interface IFindpasswordMaintenanceService {

	List<IFindpassword> searchAll();
	
	List<IFindpassword> searchUser(Integer id);
	
	void saveObject(IFindpassword company);
	
	void updateObject(IFindpassword company);
	
}
