package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.IFed;

public interface IFedDAO extends IGenericDAO {

	List<IFed> searchFed();
	
	List<IFed> searchProFed(Integer id);
	
	List<IFed> searchPurFed(Integer id);
	
	void updateFed(IFed fed);
	
	void addFed(IFed fed);
	
}
