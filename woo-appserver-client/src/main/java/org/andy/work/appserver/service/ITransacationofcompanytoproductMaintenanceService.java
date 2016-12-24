package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.ITransacationofcompanytoproduct;
import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;

public interface ITransacationofcompanytoproductMaintenanceService {
    
	
	List<ITransacationofcompanytoproduct> searchTrade(Integer productid,Integer mineid);
	
	List<ITransacationofcompanytoproduct> searchBuy(Integer mineid);
	
	boolean updateTrade(Transacationofcompanytoproduct trade);
	
	boolean addTrade(Transacationofcompanytoproduct trade);
}
