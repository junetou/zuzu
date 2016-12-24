package org.andy.work.appserver.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IFedDAO;
import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.service.IFedMaintenanceService;
import org.springframework.stereotype.Service;


@Service
public class FedMaintenanceService implements IFedMaintenanceService {

	@Resource 
	private IFedDAO fedao;
	
    @Override
	public List<IFed> searchFed(){
    	return this.fedao.searchFed();
    }
	
    @Override
	public List<IFed> searchProFed(Integer id){
    	return this.fedao.searchProFed(id);
    }
    
    @Override
	public List<IFed> searchPurFed(Integer id){
    	return this.fedao.searchPurFed(id);
    }
    
    @Override
	public void updateFed(IFed fed){
    	this.fedao.updateFed(fed);
    }
	
    @Override
	public void addFed(IFed fed){
    	this.fedao.addFed(fed);
    }
	
}
