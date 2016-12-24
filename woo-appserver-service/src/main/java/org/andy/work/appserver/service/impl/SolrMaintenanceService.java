package org.andy.work.appserver.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.andy.work.appserver.solrpublish.ProductSolrPublish;
import org.andy.work.appserver.solrpublish.UserSolrPublish;
import org.andy.work.paging.PagingManagement;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;


@Service
public class SolrMaintenanceService implements ISolrMaintenanceService {

    @Resource
    private UserSolrPublish solrpublish;
    
    @Resource
    private ProductSolrPublish productpublish;
    
	
	@Override
	public void UserStartSolr(){
		
		try {
			this.solrpublish.startSolr();;
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void ProductStartSolr(){
		
		try {
			this.productpublish.startSolr();;
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void UserRemoveSolr(){
		
		this.solrpublish.removeSolr();
	}
	
	@Override
	public void ProductRemoveSolr(){
		
		this.productpublish.removeSolr();
	}
	
	
	@Override
	public List<IUser> Search(String keyword){
		
		List<IUser> user = this.solrpublish.keyworkSearch(keyword);
		return user;
	}
	
	@Override
	public Map<String,Object> keyworkProductSearch(String keywork,PagingManagement pgm){
		
		Map<String,Object> product=this.productpublish.keyworkProductSearch(keywork, pgm);
		return product;
	}
	
	@Override
	public Map<String,Object> keyworkPurchaseSearch(String keywork,PagingManagement pgm){
		
		Map<String,Object> purchase=this.productpublish.keyworkPurchaseSearch(keywork, pgm);
		return purchase;
	}
	
}
