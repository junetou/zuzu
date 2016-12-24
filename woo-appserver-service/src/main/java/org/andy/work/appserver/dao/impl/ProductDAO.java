package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.IProductDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.impl.Product;
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
public class ProductDAO extends GenericDAO implements IProductDAO {

	
	@Override
	public IProduct search(Integer id){
		
		String hql="from Product t where t.id=:id and t.shelve=1 ";
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql).setParameter("id", id);
		return (IProduct)query.uniqueResult();
	}
	
	@Override
	public SearchResponse<IProduct> searchPro(SearchRequest<AcctUserSearchCriteria> searchReq){
		
		SearchResponse<IProduct> response=new SearchResponse<IProduct>();
		PagingManagement pgm=searchReq.getPgm();
		StringBuffer sql=new StringBuffer("from Product t where 1=1 and t.shelve=1 ");
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(sql.toString());
		QueryHelper queryhelper=QueryHelper.getInstance();
		queryhelper.setQuery(query, pgm);
		int count=query.list().size();
		response.setTotalRecords(count);
		List<IProduct> product=query.list();
		response.setResults(product);
		return response;
	}
	
	@Override
	public List<IProduct> searchUserPro(Integer id){
		
		StringBuffer hql=new StringBuffer("from Product t where t.belong.id=:id and t.shelve=1  ");
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql.toString()).setParameter("id", id);
		List<IProduct> product=query.list();
		return product;
	}
	
	@Override
	public List<IProduct> SearchAllProduct(){
		
		StringBuffer hql=new StringBuffer("from Product t where 1=1 and t.shelve=1 ");
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql.toString());
		List<IProduct> product=query.list();
		return product;
	}
	
	@Override
	public Integer count(){
		StringBuffer hql=new StringBuffer("from Product t where 1=1 and t.shelve=1 ");
		Session session=super.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql.toString());
		return query.list().size();
	}
	
	@Override
	public void addPro(Product product){
		
		super.sessionFactory.getCurrentSession().save(product);
	}
	
	@Override
	public void updatePro(Product product){
		
		super.sessionFactory.getCurrentSession().update(product);
	}
}
