package org.andy.work.appserver.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.andy.work.appserver.dao.ITransacationofcompanytoproductDAO;
import org.andy.work.appserver.dao.ITransacationofusertoproductDAO;
import org.andy.work.appserver.model.ITransacationofcompanytoproduct;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.impl.Transacationofcompanytoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertoproduct;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class TransacationofusertoproductDAO extends GenericDAO implements ITransacationofusertoproductDAO {

	@Override
	public List<ITransacationofusertoproduct> searchTrade(Integer productid,Integer userid){
		
		String hql="from Transacationofusertoproduct t where t.productid.id=:productid and t.userid.id=:userid and t.success=1";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("productid", productid).setParameter("userid", userid);
		List<ITransacationofusertoproduct> trade=query.list();
		return trade;
	}
	
	@Override
	public List<ITransacationofusertoproduct> searchBuy(Integer userid){
		
		String hql="from Transacationofusertoproduct t where  t.userid.id=:userid and t.success=1";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("userid", userid);
		List<ITransacationofusertoproduct> trade=query.list();
		return trade;
	}
	
	@Override
	public List<ITransacationofusertoproduct> updateTrade(Integer productid){
		
		String hql="from Transacationofusertoproduct t where  t.productid.id=:productid and t.success=1";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("productid", productid);
		List<ITransacationofusertoproduct> trade=query.list();
		return trade;
	}
	
	@Override
	public boolean updateTrade(Transacationofusertoproduct trade){
		
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
	public boolean addTrade(Transacationofusertoproduct trade){
		
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
