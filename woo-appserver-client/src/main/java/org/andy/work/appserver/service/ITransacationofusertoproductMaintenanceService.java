package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertoproduct;



public interface ITransacationofusertoproductMaintenanceService {
    
	
	List<ITransacationofusertoproduct> searchTrade(Integer productid,Integer userid);
	
	List<ITransacationofusertoproduct> searchBuy(Integer userid);
	
	List<ITransacationofusertoproduct> updateTrade(Integer productid);
	
	boolean updateTrade(Transacationofusertoproduct trade);
	
	boolean addTrade(Transacationofusertoproduct trade);
}
