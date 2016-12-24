package org.andy.work.appserver.model;

public interface ITransacationofcompanytopurchase {

    Integer getId();
	
	void setId(Integer id);
	
	IPurchase getPurchaseid();
	
	void setPurchaseid(IPurchase purchaseid);
	
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
