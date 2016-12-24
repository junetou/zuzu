package org.andy.work.appserver.service;

import java.util.List;
import java.util.Map;

import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.IUser;
import org.andy.work.paging.PagingManagement;

public interface ISolrMaintenanceService {

	void UserStartSolr();
	
	void ProductStartSolr();
	
	void UserRemoveSolr();
	
	void ProductRemoveSolr();
	
	List<IUser> Search(String keyword);
	
	Map<String,Object> keyworkProductSearch(String keywork,PagingManagement pgm);
	
	Map<String,Object> keyworkPurchaseSearch(String keywork,PagingManagement pgm);

}
