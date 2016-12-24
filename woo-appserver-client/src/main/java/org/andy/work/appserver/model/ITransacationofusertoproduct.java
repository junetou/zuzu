package org.andy.work.appserver.model;

public interface ITransacationofusertoproduct {

	Integer getId();
	
	void setId(Integer id);
	
	IProduct getProductid();
	
	void setProductid(IProduct productid);
	
	ICompany getCompanyid();
	
	void setCompanyid(ICompany companyid);
	
	IUser getUserid();
	
	void setUserid(IUser userid);
	
	String getCreattime();
	
	void setCreattime(String creattime);
	
	String getLasttime();
	
	void setLasttime(String lasttime);
	
	Double getMoney();
	
	void setMoney(Double money);
	
	Integer getSuccess();
	
	void setSuccess(Integer success);
	
}
