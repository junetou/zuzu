package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.impl.CompanyheadDAO;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.ICompanyhead;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.ICompanyheadMaintenanceService;
import org.springframework.stereotype.Service;


@Service
public class CompanyheadMaintenanceService implements ICompanyheadMaintenanceService {

	@Resource
	private CompanyheadDAO companyhead;
	
	@Override
	public ICompanyhead getHead(ICompany id){
		
		return this.companyhead.getHead(id);
	}
	
	@Override
	public void saveHead(ICompanyhead id){
		
		this.companyhead.saveHead(id);
	}
	
	@Override
	public void updateHead(ICompanyhead id){
		
		this.companyhead.updateHead(id);
	}
	
}
