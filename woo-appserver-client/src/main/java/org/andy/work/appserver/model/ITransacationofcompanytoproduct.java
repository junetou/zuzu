package org.andy.work.appserver.model;

public interface ITransacationofcompanytoproduct {

	Integer getId();
	
	void setId(Integer id);
	
	IProduct getProductid();
	
	void setProductid(IProduct productid);
	
	ICompany getOtherid();
	
	void setOtherid(ICompany otherid);
	
	ICompany getMineid();
	
	void setMineid(ICompany mineid);
	
	String getCreattime();
	
	void setCreattime(String creattime);
	
	String getLasttime();
	
	void setLasttime(String lasttime);
	
	Double getMoney();
	
	void setMoney(Double money);
	
	Integer getSuccess();
	
	void setSuccess(Integer money);
	
	
}
