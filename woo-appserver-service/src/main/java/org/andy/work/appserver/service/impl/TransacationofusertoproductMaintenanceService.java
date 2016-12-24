package org.andy.work.appserver.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.ITransacationofusertoproductDAO;
import org.andy.work.appserver.dao.impl.ProductDAO;
import org.andy.work.appserver.dao.impl.TransacationofcompanytoproductDAO;
import org.andy.work.appserver.dao.impl.TransacationofusertoproductDAO;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.ITransacationofcompanytoproduct;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertoproduct;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.andy.work.appserver.service.ITransacationofcompanytoproductMaintenanceService;
import org.andy.work.appserver.service.ITransacationofusertoproductMaintenanceService;
import org.andy.work.appserver.solrpublish.UserSolrPublish;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;


@Service
public class TransacationofusertoproductMaintenanceService implements ITransacationofusertoproductMaintenanceService {

    @Resource
    private TransacationofusertoproductDAO productdao;
    
    @Override
    public List<ITransacationofusertoproduct> searchTrade(Integer productid,Integer mineid){
    	return this.productdao.searchTrade(productid, mineid);
    }
	
    @Override
    public List<ITransacationofusertoproduct> searchBuy(Integer mineid){
    	return this.productdao.searchBuy(mineid);
    }
	
    @Override
    public List<ITransacationofusertoproduct> updateTrade(Integer productid){
    	return this.productdao.updateTrade(productid);
    }
    
    @Override
	public boolean updateTrade(Transacationofusertoproduct trade){
    	return this.productdao.updateTrade(trade);
    }
	
    @Override
	public boolean addTrade(Transacationofusertoproduct trade){
    	return this.productdao.addTrade(trade);
    }

}
