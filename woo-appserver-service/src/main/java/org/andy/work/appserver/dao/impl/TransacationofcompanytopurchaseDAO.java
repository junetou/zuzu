package org.andy.work.appserver.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.andy.work.appserver.dao.ITransacationofcompanytoproductDAO;
import org.andy.work.appserver.dao.ITransacationofcompanytopurchaseDAO;
import org.andy.work.appserver.model.ITransacationofcompanytoproduct;
import org.andy.work.appserver.model.ITransacationofcompanytopurchase;
import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;
import org.andy.work.appserver.model.impl.Transacationofcompanytopurchase;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class TransacationofcompanytopurchaseDAO extends GenericDAO implements ITransacationofcompanytopurchaseDAO {

	@Override
	public List<ITransacationofcompanytopurchase> searchTrade(Integer productid,Integer mineid){
		
		String hql="from Transacationofcompanytopurchase t where t.productid=:productid and t.mineid=:mineid and t.success=0";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("productid", productid).setParameter("mineid", mineid);
		List<ITransacationofcompanytopurchase> trade=new ArrayList<ITransacationofcompanytopurchase>();
		trade=query.list();
		return trade;
	}
	
	@Override
	public List<ITransacationofcompanytopurchase> searchBuy(Integer mineid){
		
		String hql="from Transacationofcompanytopurchase t where t.mineid.id=:mineid and t.success=1";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("mineid", mineid);
		List<ITransacationofcompanytopurchase> trade=new ArrayList<ITransacationofcompanytopurchase>();
		trade=query.list();
		return trade;
	}
	
	@Override
	public boolean updateTrade(Transacationofcompanytopurchase trade){
		
		boolean bool=false;
		try{
			super.sessionFactory.getCurrentSession().update(trade);
			bool=true;
		}catch(Exception e){
			bool=false;
		}
		return bool;
	}
	
	@Override
	public boolean addTrade(Transacationofcompanytopurchase trade){
		
		boolean bool=false;
		try{
			super.sessionFactory.getCurrentSession().save(trade);
			bool=true;
		}catch(Exception e){
			bool=false;
		}
		return bool;
	}
	
}
