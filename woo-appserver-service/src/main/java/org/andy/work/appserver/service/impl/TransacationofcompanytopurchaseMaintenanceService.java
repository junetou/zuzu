package org.andy.work.appserver.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.impl.ProductDAO;
import org.andy.work.appserver.dao.impl.TransacationofcompanytoproductDAO;
import org.andy.work.appserver.dao.impl.TransacationofcompanytopurchaseDAO;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.ITransacationofcompanytoproduct;
import org.andy.work.appserver.model.ITransacationofcompanytopurchase;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;
import org.andy.work.appserver.model.impl.Transacationofcompanytopurchase;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.andy.work.appserver.service.ITransacationofcompanytoproductMaintenanceService;
import org.andy.work.appserver.service.ITransacationofcompanytopurchaseMaintenanceService;
import org.andy.work.appserver.solrpublish.UserSolrPublish;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;


@Service
public class TransacationofcompanytopurchaseMaintenanceService implements ITransacationofcompanytopurchaseMaintenanceService {

    @Resource
    private TransacationofcompanytopurchaseDAO purchasedao;
    
    @Override
    public List<ITransacationofcompanytopurchase> searchTrade(Integer productid,Integer mineid){
    	return this.purchasedao.searchTrade(productid, mineid);
    }
    
    @Override
    public List<ITransacationofcompanytopurchase> searchBuy(Integer mineid){
    	return this.purchasedao.searchBuy(mineid);
    }
	
    @Override
	public boolean updateTrade(Transacationofcompanytopurchase trade){
    	return this.purchasedao.updateTrade(trade);
    }
	
    @Override
	public boolean addTrade(Transacationofcompanytopurchase trade){
    	return this.purchasedao.addTrade(trade);
    }

}
