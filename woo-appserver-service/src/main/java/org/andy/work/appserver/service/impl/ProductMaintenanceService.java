package org.andy.work.appserver.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.impl.ProductDAO;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.andy.work.appserver.solrpublish.UserSolrPublish;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;


@Service
public class ProductMaintenanceService implements IProductMaintenanceService {

    @Resource
    private ProductDAO productdao;
    
    @Override
    public IProduct search(Integer id){
    	
    	return this.productdao.search(id);
    }
    
	
	@Override
    public SearchResponse<IProduct> searchPro(SearchRequest<AcctUserSearchCriteria> searchReq){
		
		return this.productdao.searchPro(searchReq);
	}
	
	@Override
	public List<IProduct> searchUserPro(Integer id){
		
		return this.productdao.searchUserPro(id);
	}
	
	@Override
	public List<IProduct> SearchAllProduct(){
		
		return this.productdao.SearchAllProduct();
	}
	
	@Override
	public Integer count(){
		return this.productdao.count();
	}

	
	@Override
	public void addPro(Product product){
		this.productdao.addPro(product);
	}
	
	@Override
	public void updatePro(Product product){
		this.productdao.updatePro(product);
	}
	
}
