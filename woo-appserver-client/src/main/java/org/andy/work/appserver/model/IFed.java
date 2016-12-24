package org.andy.work.appserver.model;

public interface IFed {

	
	Integer getId();
	
	void setId(Integer id);
	
	String getInfomation();
	
	void setInfomation(String infomation);
	
	IUser getUserid();
	
	void setUserid(IUser userid);
	
	Integer getBelong();
	
	void setBelong(Integer belong);
	
	Integer getProductid();
	
	void setProductid(Integer productid);
	
	ICompany getCompanyid();
	
	void setCompanyid(ICompany companyid);
	
    Integer getShalve();
    
    void setShalve(Integer shalve);
}
