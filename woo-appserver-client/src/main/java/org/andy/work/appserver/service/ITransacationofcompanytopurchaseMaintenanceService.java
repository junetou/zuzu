package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.ITransacationofcompanytopurchase;
import org.andy.work.appserver.model.impl.Transacationofcompanytopurchase;

public interface ITransacationofcompanytopurchaseMaintenanceService {
    
	
	List<ITransacationofcompanytopurchase> searchTrade(Integer productid,Integer mineid);
	
	List<ITransacationofcompanytopurchase> searchBuy(Integer mineid);
	
	boolean updateTrade(Transacationofcompanytopurchase trade);
	
	boolean addTrade(Transacationofcompanytopurchase trade);
}
