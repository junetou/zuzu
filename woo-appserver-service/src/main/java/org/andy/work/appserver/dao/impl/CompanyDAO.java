package org.andy.work.appserver.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.andy.work.appserver.dao.ICompanyDAO;
import org.andy.work.appserver.dao.IUserDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class CompanyDAO extends GenericDAO implements ICompanyDAO {

	@Override
	public ICompany findUserByusername(String username) {
		String hql = "from Company u where u.companyname=:companyname ";
		Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("companyname", username);
		return (ICompany) query.uniqueResult();
	}
	
	@Override
	public List<ICompany> findRegisterUser() {
		String hql = "from Company u where 1=1 and u.locked='Y' ";
		Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
		List<ICompany> company=query.list();
		return company;
	}


	@Override
	public void saveUser(ICompany user) {
	    
		super.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public void deleteUser(ICompany user) {
	    
		super.sessionFactory.getCurrentSession().delete(user);
	}
	
	@Override
	public void updateUser(ICompany user) {
		super.sessionFactory.getCurrentSession().update(user);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getGroupUsers(Integer groupId) {
		String hql = "select u.displayName from Company u where u.userGroup.id=:groupId";
		return this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("groupId", groupId).list();
	}

	@Override
	public IUserGroup getUserGroupByName(String name) {
		String hql = "from UserGroup g where g.name=:name";
		return (IUserGroup) this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", name).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<ICompany> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq) {
		SearchResponse<ICompany> searchResp = new SearchResponse<ICompany>();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		QueryHelper queryHelper = QueryHelper.getInstance();
		StringBuffer whereHql = this.buildSecureUserHql(criteria, queryHelper);
		StringBuffer hql = new StringBuffer("select count(u.id) from Company u");
		hql.append(whereHql);
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryHelper.setToQuery(query, null);
		
		Long count = (Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
		if (count > 0) {
    		if(StringUtil.hasValue(criteria.getKeyWord())){
    		String judgment=criteria.getKeyWord();
    		Pattern pattern=Pattern.compile("[0-9]*");
    		  if(!pattern.matcher(judgment).matches())
    		  {
			  hql=new StringBuffer("from Company w where 1=1 and w.displayName = :displayName ");
	  		  queryHelper.addParameter("displayName", criteria.getKeyWord());
    		  }
	  		} 
		    else{
		    hql = new StringBuffer("from Company u");
		    hql.append(whereHql);
	  		hql.append(" order by u.id");
		     }
			query = session.createQuery(hql.toString());
			queryHelper.setToQuery(query, pgm);
			
			List<ICompany> list = query.list();
			searchResp.setResults(list);
		}
		return searchResp;
	}

	private StringBuffer buildSecureUserHql(AcctUserSearchCriteria criteria, QueryHelper queryHelper) {
		StringBuffer hql = new StringBuffer();
		hql.append(" where 1=1");
		return hql;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IUserGroup> getUserGroup() {
		String hql = "from UserGroup u order by u.id";
		return this.sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public boolean isAccount(String account) {
		String hql = "select count(u.id) from Company u where u.companyname = :account";
		Long count = (Long) this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("account", account).uniqueResult();
		return count  > 0;
	}
	
	@Override
	public boolean isUserGroup(Integer id) {
		String hql = "select count(u.id) from Company u where u.userGroup.id = :id";
		Long count = (Long) this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id).uniqueResult();
		return count  > 0;
	}
	
	@Override
	public boolean hasusrname(String usrname){
		String hql=" from Company t where t.companyname=:companyname";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("companyname", usrname);
		if(query.uniqueResult()!=null)
		{return false;}
		else
		{return true;}
	}
	
	@Override
    public List<ICompany> SearchAllCompany(){
		StringBuffer hql=new StringBuffer("from Compnay u where 1=1");
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql.toString());
		List<ICompany> company=new ArrayList<ICompany>();
		company=query.list();
		return company;
	}
	
	@Override
	public List<ICompany> SearchCompany(Integer id){
		
		StringBuffer hql=new StringBuffer("from Company u where u.id=:id");
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql.toString()).setParameter("id",id);
		List<ICompany> company=new ArrayList<ICompany>();
		company=query.list();
		return company;
	}
	
}
