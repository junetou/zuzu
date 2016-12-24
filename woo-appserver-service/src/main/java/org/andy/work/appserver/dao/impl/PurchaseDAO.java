package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.IProductDAO;
import org.andy.work.appserver.dao.IPurchaseDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.appserver.model.impl.Purchase;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class PurchaseDAO extends GenericDAO implements IPurchaseDAO {

	@Override
	public IPurchase search(Integer id){
		
		String hql="from Purchase t where t.id=:id and t.shelve=1 ";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("id", id);
		return (IPurchase)query.uniqueResult();
	}
	
	
	@Override
	public SearchResponse<IPurchase> searchPur(SearchRequest<AcctUserSearchCriteria> searchReq){
		
		SearchResponse<IPurchase> response=new SearchResponse<IPurchase>();
		PagingManagement pgm=searchReq.getPgm();
		StringBuffer sql=new StringBuffer("from Purchase t where 1=1 and t.shelve=1 ");
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(sql.toString());
		QueryHelper queryhelper=QueryHelper.getInstance();
		queryhelper.setQuery(query, pgm);
		int count=query.list().size();
		response.setTotalRecords(count);
		List<IPurchase> product=query.list();
		response.setResults(product);
		return response;
		
	}
	
	@Override
	public List<IPurchase> searchUserPur(Integer id){
		
		StringBuffer hql=new StringBuffer("from Purchase t where t.belong.id=:id and t.shelve=1  ");
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql.toString()).setParameter("id", id);
		List<IPurchase> purchase=query.list();
		return purchase;
	}
	
	@Override
	public List<IPurchase> SearchAllPurchase(){
		
		StringBuffer hql=new StringBuffer("from Purchase t where 1=1 and t.shelve=1 ");
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql.toString());
		List<IPurchase> product=query.list();
		return product;
		
	}
	
	@Override
	public Integer count(){
		
		StringBuffer hql=new StringBuffer("from Purchase t where 1=1 and t.shelve=1 ");
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql.toString());
		return query.list().size();
	}
	
	
	@Override
	public void addPur(Purchase product){
		
		super.sessionFactory.getCurrentSession().save(product);
	}
	
	@Override
	public void updatePur(Purchase product){
		
		super.sessionFactory.getCurrentSession().update(product);
	}
}
