package org.andy.work.appserver.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.ITransacationofusertoproductDAO;
import org.andy.work.appserver.dao.impl.ProductDAO;
import org.andy.work.appserver.dao.impl.TransacationofcompanytoproductDAO;
import org.andy.work.appserver.dao.impl.TransacationofusertoproductDAO;
import org.andy.work.appserver.dao.impl.TransacationofusertopurchaseDAO;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.ITransacationofcompanytoproduct;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.ITransacationofusertopurchase;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertopurchase;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.andy.work.appserver.service.ITransacationofcompanytoproductMaintenanceService;
import org.andy.work.appserver.service.ITransacationofusertoproductMaintenanceService;
import org.andy.work.appserver.service.ITransacationofusertopurchaseMaintenanceService;
import org.andy.work.appserver.solrpublish.UserSolrPublish;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;


@Service
public class TransacationofusertopurchaseMaintenanceService implements ITransacationofusertopurchaseMaintenanceService {

    @Resource
    private TransacationofusertopurchaseDAO purchasedao;
    
    @Override
    public List<ITransacationofusertopurchase> searchTrade(Integer productid,Integer mineid){
    	return this.purchasedao.searchTrade(productid, mineid);
    }
    
    @Override
    public List<ITransacationofusertopurchase> searchBuy(Integer mineid){
    	return this.purchasedao.searchBuy(mineid);
    }
	
    @Override
    public List<ITransacationofusertopurchase> updateTrade(Integer productid){
    	return this.purchasedao.updateTrade(productid);
    }
    
    @Override
	public boolean updateTrade(Transacationofusertopurchase trade){
    	return this.purchasedao.updateTrade(trade);
    }
	
    @Override
	public boolean addTrade(Transacationofusertopurchase trade){
    	return this.purchasedao.addTrade(trade);
    }

}
