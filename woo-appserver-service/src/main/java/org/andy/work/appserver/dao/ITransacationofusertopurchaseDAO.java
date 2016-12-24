package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.impl.Transacationofusertoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertopurchase;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.ITransacationofusertopurchase;


public interface ITransacationofusertopurchaseDAO {

	
	List<ITransacationofusertopurchase> searchTrade(Integer productid,Integer userid);
	
	List<ITransacationofusertopurchase> searchBuy(Integer userid);
	
	List<ITransacationofusertopurchase> updateTrade(Integer productid);
	
	boolean updateTrade(Transacationofusertopurchase trade);
	
	boolean addTrade(Transacationofusertopurchase trade);
	
}
