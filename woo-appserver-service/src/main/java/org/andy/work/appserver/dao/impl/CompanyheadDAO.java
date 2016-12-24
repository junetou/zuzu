package org.andy.work.appserver.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.andy.work.appserver.dao.ICompanyheadDAO;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.ICompanyhead;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class CompanyheadDAO extends GenericDAO implements ICompanyheadDAO {

	
	@Override
	public ICompanyhead getHead(ICompany id){
		Session session=super.sessionFactory.getCurrentSession();
	    String hql="from Companyhead u where u.companyid= :companyid";
	    Query query=session.createQuery(hql).setParameter("companyid", id);
	    return (ICompanyhead)query.uniqueResult();
	}
	
	@Override
	public void saveHead(ICompanyhead id){
		super.sessionFactory.getCurrentSession().save(id);
	}
	
	@Override
	public void updateHead(ICompanyhead id){
		super.sessionFactory.getCurrentSession().update(id);
	}
	
}
