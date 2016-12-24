package org.andy.work.appserver.dao;

import java.io.Serializable;

import org.andy.work.appserver.dao.obj.QueryHelper;

public interface IGenericDAO {
	
	Object makePersist(Object obj);
	
	void makeUpdate(Object obj);
	
	void makeSaveOrUpdate(Object obj);
	
	Object getEntityById(Class<?> clazz, Serializable id);
	
	void deleteEntity(Object obj);
	
}
