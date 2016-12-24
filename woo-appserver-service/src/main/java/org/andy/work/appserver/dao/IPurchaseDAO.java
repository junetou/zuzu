package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.appserver.model.impl.Purchase;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IPurchaseDAO extends IGenericDAO {

	IPurchase search(Integer id);
	
	SearchResponse<IPurchase> searchPur(SearchRequest<AcctUserSearchCriteria> searchReq);
	
	List<IPurchase> searchUserPur(Integer id);
	
	List<IPurchase> SearchAllPurchase();
	
	Integer count();
	
	void addPur(Purchase product);
	
	void updatePur(Purchase product);
	
}
