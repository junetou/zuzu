package org.andy.work.appserver.manager;

import org.aspectj.lang.JoinPoint;

public class DataSourceInterceptor {

	public void setdataSource(JoinPoint jp) {
		DatabaseContextHolder.setCustomerType("dataSource");
	}
	
	public void setdataSourceTwo(JoinPoint jp) {
		DatabaseContextHolder.setCustomerType("dataSourceTwo");
	}
}