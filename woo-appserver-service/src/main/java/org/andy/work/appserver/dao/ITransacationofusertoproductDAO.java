package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.impl.Transacationofusertoproduct;
import org.andy.work.appserver.model.ITransacationofusertoproduct;


public interface ITransacationofusertoproductDAO {

	
	List<ITransacationofusertoproduct> searchTrade(Integer productid,Integer userid);
	
	List<ITransacationofusertoproduct> searchBuy(Integer userid);
	
	List<ITransacationofusertoproduct> updateTrade(Integer productid);
	
	boolean updateTrade(Transacationofusertoproduct trade);
	
	boolean addTrade(Transacationofusertoproduct trade);
	
}
