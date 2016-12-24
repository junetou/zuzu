package org.andy.work.appserver.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.impl.PurchaseDAO;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.impl.Purchase;
import org.andy.work.appserver.service.IPurchaseMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Service;


@Service
public class PurchaseMaintenanceService implements IPurchaseMaintenanceService {

	
	@Resource
	private PurchaseDAO purchasedao;
	
	@Override
	public IPurchase search(Integer id){
		
		return this.purchasedao.search(id);
	}
	
	@Override
    public SearchResponse<IPurchase> searchPur(SearchRequest<AcctUserSearchCriteria> searchReq){
		
		return this.purchasedao.searchPur(searchReq);
	}
	
	@Override
	public List<IPurchase> searchUserPur(Integer id){
		
		return this.purchasedao.searchUserPur(id);
	}
	
	@Override
	public List<IPurchase> SearchAllPurchase(){
		
		return this.purchasedao.SearchAllPurchase();
	}
	
	@Override
	public Integer count(){
		
		return this.purchasedao.count();
	}
	
	@Override
	public void addPur(Purchase product){
		this.purchasedao.addPur(product);
	}
	
	@Override
	public void updatePur(Purchase product){
		this.purchasedao.updatePur(product);
	}
}
