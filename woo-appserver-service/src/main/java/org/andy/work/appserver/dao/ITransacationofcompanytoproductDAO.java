package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;
import org.andy.work.appserver.model.ITransacationofcompanytoproduct;


public interface ITransacationofcompanytoproductDAO {

	
	List<ITransacationofcompanytoproduct> searchTrade(Integer productid,Integer mineid);
	
	List<ITransacationofcompanytoproduct> searchBuy(Integer mineid);
	
	boolean updateTrade(Transacationofcompanytoproduct trade);
	
	boolean addTrade(Transacationofcompanytoproduct trade);
	
}
