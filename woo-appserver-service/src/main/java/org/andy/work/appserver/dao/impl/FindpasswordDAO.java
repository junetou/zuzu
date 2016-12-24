package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.IFindpasswordDAO;
import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.IFindpassword;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class FindpasswordDAO extends GenericDAO implements IFindpasswordDAO {

	
	@Override
	public List<IFindpassword> searchAll(){
		
		String hql="from Findpassword u where 1=1 and u.shelve=1";
		Session session=this.sessionFactory.getCurrentSession();
		List<IFindpassword> findps=session.createQuery(hql).list();
		return findps;
	}
	
	@Override
	public List<IFindpassword> searchUser(Integer id){
		
		String hql="from Findpassword u where 1=1 and u.companyid.id=:id";
		Session session=this.sessionFactory.getCurrentSession();
		List<IFindpassword> findps=session.createQuery(hql).setParameter("id", id).list();
		return findps;
	}
	
	@Override
	public void saveObject(IFindpassword findps){
		
		super.sessionFactory.getCurrentSession().save(findps);
	}
	
	@Override
	public void updateObject(IFindpassword findps){
		
		super.sessionFactory.getCurrentSession().update(findps);
	}
	
}
