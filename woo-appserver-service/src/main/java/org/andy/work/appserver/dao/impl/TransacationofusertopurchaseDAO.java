package org.andy.work.appserver.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.andy.work.appserver.dao.ITransacationofcompanytoproductDAO;
import org.andy.work.appserver.dao.ITransacationofusertoproductDAO;
import org.andy.work.appserver.dao.ITransacationofusertopurchaseDAO;
import org.andy.work.appserver.model.ITransacationofcompanytoproduct;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.ITransacationofusertopurchase;
import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertopurchase;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class TransacationofusertopurchaseDAO extends GenericDAO implements ITransacationofusertopurchaseDAO {

	@Override
	public List<ITransacationofusertopurchase> searchTrade(Integer productid,Integer userid){
		
		String hql="from Transacationofusertopurchase t where t.purchaseid.id=:purchaseid and t.userid.id=:userid and t.success=1";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("purchaseid", productid).setParameter("userid", userid);
		List<ITransacationofusertopurchase> trade=query.list();
		return trade;
	}
	
	@Override
	public List<ITransacationofusertopurchase> searchBuy(Integer userid){
		
		String hql="from Transacationofusertopurchase t where t.userid.id=:userid and t.success=1";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("userid", userid);
		List<ITransacationofusertopurchase> trade=query.list();
		return trade;
	}
	
	@Override
	public List<ITransacationofusertopurchase> updateTrade(Integer productid){
		
		String hql="from Transacationofusertopurchase t where t.purchaseid.id=:purchaseid and t.success=1";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("purchaseid", productid);
		List<ITransacationofusertopurchase> trade=query.list();
		return trade;
	}
	
	@Override
	public boolean updateTrade(Transacationofusertopurchase trade){
		
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
	public boolean addTrade(Transacationofusertopurchase trade){
		
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
