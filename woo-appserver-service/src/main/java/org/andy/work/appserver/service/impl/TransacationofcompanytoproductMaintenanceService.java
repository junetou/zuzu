package org.andy.work.appserver.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.impl.ProductDAO;
import org.andy.work.appserver.dao.impl.TransacationofcompanytoproductDAO;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.ITransacationofcompanytoproduct;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.andy.work.appserver.service.ITransacationofcompanytoproductMaintenanceService;
import org.andy.work.appserver.solrpublish.UserSolrPublish;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;


@Service
public class TransacationofcompanytoproductMaintenanceService implements ITransacationofcompanytoproductMaintenanceService {

    @Resource
    private TransacationofcompanytoproductDAO productdao;
    
    @Override
    public List<ITransacationofcompanytoproduct> searchTrade(Integer productid,Integer mineid){
    	return this.productdao.searchTrade(productid, mineid);
    }
    
    @Override
    public List<ITransacationofcompanytoproduct> searchBuy(Integer mineid){
    	return this.productdao.searchBuy(mineid);
    }
	
    @Override
	public boolean updateTrade(Transacationofcompanytoproduct trade){
    	return this.productdao.updateTrade(trade);
    }
	
    @Override
	public boolean addTrade(Transacationofcompanytoproduct trade){
    	return this.productdao.addTrade(trade);
    }

}
