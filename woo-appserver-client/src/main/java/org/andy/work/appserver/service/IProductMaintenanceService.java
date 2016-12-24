package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IProductMaintenanceService {
    
	IProduct search(Integer id);
	
	SearchResponse<IProduct> searchPro(SearchRequest<AcctUserSearchCriteria> searchReq);
	
	List<IProduct> searchUserPro(Integer id);
	
	List<IProduct> SearchAllProduct();
	
	Integer count();
	
	void addPro(Product product);
	
	void updatePro(Product product);
	
}
