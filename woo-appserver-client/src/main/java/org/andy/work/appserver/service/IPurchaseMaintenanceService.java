package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.impl.Purchase;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IPurchaseMaintenanceService {

	IPurchase search(Integer id);
	
	SearchResponse<IPurchase> searchPur(SearchRequest<AcctUserSearchCriteria> searchReq);
	
	List<IPurchase> searchUserPur(Integer id);
	
	List<IPurchase> SearchAllPurchase();
	
	Integer count();
	
	void addPur(Purchase product);
	
	void updatePur(Purchase product);
}
