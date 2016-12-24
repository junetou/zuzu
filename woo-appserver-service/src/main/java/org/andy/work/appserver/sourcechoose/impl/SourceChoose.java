package org.andy.work.appserver.sourcechoose.impl;

import org.andy.work.appserver.manager.DatabaseContextHolder;
import org.andy.work.appserver.sourcechoose.ISourceChoose;
import org.springframework.stereotype.Service;

@Service
public class SourceChoose implements ISourceChoose {

	@Override
	public void setSource(){
		DatabaseContextHolder.setCustomerType("dataSource");
	}
	
	@Override
	public void setSourceTwo(){
		DatabaseContextHolder.setCustomerType("dataSourceTwo");
	}
	
}
