package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.INeedTradeDAO;
import org.andy.work.appserver.dao.ITradeDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.INeedTrade;
import org.andy.work.appserver.model.ITrade;
import org.andy.work.appserver.model.impl.NeedTrade;
import org.andy.work.appserver.model.impl.Trade;
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
public class NeedTradeDAO extends GenericDAO implements INeedTradeDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<INeedTrade> searchsuccessseller(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid){
		SearchResponse<INeedTrade> searchResp = new SearchResponse<INeedTrade>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.trade) from NeedTrade u ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);	    
		if(count>0){
			  hql=new StringBuffer("from NeedTrade u where 1=1 and u.seller = :seller and u.success = :success");
		      queryhelper.addParameter("seller", userid);
		      queryhelper.addParameter("success", 1);
		      query = session.createQuery(hql.toString());
		  queryhelper.setToQuery(query, pgm);
		  int counts=query.list().size();
	      count=(long)counts;
		  List<INeedTrade> list=query.list();
		  searchResp.setTotalRecords(counts);
		  searchResp.setResults(list);
		}
        return searchResp;	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<INeedTrade> searchsuccessborrow(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid){
		SearchResponse<INeedTrade> searchResp = new SearchResponse<INeedTrade>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.trade) from NeedTrade u ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);	    
		if(count>0){
			  hql=new StringBuffer("from NeedTrade u where 1=1 and u.borrow = :borrow and u.success = :success");
		      queryhelper.addParameter("borrow", userid);
		      queryhelper.addParameter("success", 1);
		      query = session.createQuery(hql.toString());
		  queryhelper.setToQuery(query, pgm);
		  int counts=query.list().size();
	      count=(long)counts;
		  List<INeedTrade> list=query.list();
		  searchResp.setTotalRecords(counts);
		  searchResp.setResults(list);
		}
        return searchResp;	
	}
	
	@Override
	public String updatemessage(NeedTrade trade){
		sessionFactory.getCurrentSession().update(trade);
		return "success";
	}

	
	@Override
	public String addmessage(NeedTrade trade){
		sessionFactory.getCurrentSession().save(trade);
		return "success";
	}
	
	@Override
	public NeedTrade getTrade(Integer trade){
		String hql="from NeedTrade t where t.trade=:trade";
		Query query=super.sessionFactory.getCurrentSession().createQuery(hql).setParameter("trade", trade);	
		return (NeedTrade)query.uniqueResult();
	}
	
	
	
}
