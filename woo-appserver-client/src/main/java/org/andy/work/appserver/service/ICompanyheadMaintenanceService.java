package org.andy.work.appserver.service;

import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.ICompanyhead;

public interface ICompanyheadMaintenanceService {

	ICompanyhead getHead(ICompany  id);
	
	void saveHead(ICompanyhead id);
	
	void updateHead(ICompanyhead id);
}
