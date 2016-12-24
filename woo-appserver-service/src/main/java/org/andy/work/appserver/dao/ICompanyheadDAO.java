package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.ICompanyhead;

public interface ICompanyheadDAO {

	ICompanyhead getHead(ICompany id);
	
	void saveHead(ICompanyhead head);

	void updateHead(ICompanyhead head);
	
}
