package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.ITransacationofusertopurchase;
import org.andy.work.appserver.model.impl.Transacationofusertopurchase;



public interface ITransacationofusertopurchaseMaintenanceService {
    
	
	
	List<ITransacationofusertopurchase> searchTrade(Integer productid,Integer userid);
	
	List<ITransacationofusertopurchase> searchBuy(Integer userid);
	
	List<ITransacationofusertopurchase> updateTrade(Integer productid);
	
	boolean updateTrade(Transacationofusertopurchase trade);
	
	boolean addTrade(Transacationofusertopurchase trade);
}
