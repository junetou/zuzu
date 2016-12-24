package org.andy.work.appserver.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IFedDAO;
import org.andy.work.appserver.dao.IFindpasswordDAO;
import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.IFindpassword;
import org.andy.work.appserver.service.IFedMaintenanceService;
import org.andy.work.appserver.service.IFindpasswordMaintenanceService;
import org.springframework.stereotype.Service;


@Service
public class FindpasswordMaintenanceService implements IFindpasswordMaintenanceService {

	@Resource 
	private IFindpasswordDAO find;
	
	@Override
    public List<IFindpassword> searchAll(){
		
		return this.find.searchAll();
	}
	
	@Override
	public List<IFindpassword> searchUser(Integer id){
		
		return this.find.searchUser(id);
	}
	
	@Override
	public void saveObject(IFindpassword company){
		
		this.find.saveObject(company);
	}
	
	@Override
	public void updateObject(IFindpassword company){
		this.find.updateObject(company);
	}
	

	
}
