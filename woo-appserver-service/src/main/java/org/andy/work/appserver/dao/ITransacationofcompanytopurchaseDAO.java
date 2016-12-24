package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.impl.Transacationofcompanytopurchase;
import org.andy.work.appserver.model.ITransacationofcompanytopurchase;


public interface ITransacationofcompanytopurchaseDAO {

	
	List<ITransacationofcompanytopurchase> searchTrade(Integer productid,Integer mineid);
	
	List<ITransacationofcompanytopurchase> searchBuy(Integer mineid);
	
	boolean updateTrade(Transacationofcompanytopurchase trade);
	
	boolean addTrade(Transacationofcompanytopurchase trade);
	
}
