package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.IFedDAO;
import org.andy.work.appserver.model.IFed;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class FedDAO extends GenericDAO implements IFedDAO{

	
	@Override
    public List<IFed> searchFed(){
		
		String hql="from Fed u where 1=1 and u.shalve=1";
		Session session=this.sessionFactory.getCurrentSession();
		List<IFed> fed=session.createQuery(hql).list();
		return fed;
	}
	
	@Override
	public List<IFed> searchProFed(Integer id){
		
		String hql="from Fed u where 1=1 and u.productid=:productid and u.belong = 0";
		Session session=this.sessionFactory.getCurrentSession();
		List<IFed> fed=session.createQuery(hql).setParameter("productid", id).list();
		return fed;
	}
	
	@Override
	public List<IFed> searchPurFed(Integer id){
		
		String hql="from Fed u where 1=1 and u.productid=:productid and u.belong = 1";
		Session session=this.sessionFactory.getCurrentSession();
		List<IFed> fed=session.createQuery(hql).setParameter("productid", id).list();
		return fed;
	}
	
	@Override
	public void updateFed(IFed fed){
		
		super.sessionFactory.getCurrentSession().update(fed);
	}
	
	@Override
	public void addFed(IFed fed){
		
		super.sessionFactory.getCurrentSession().save(fed);
	}
	
}
