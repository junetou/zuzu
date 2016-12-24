package org.andy.work.appserver.model;


public interface IPurchase {
   
	Integer getId();
	
	void setId(Integer id);
	
	String getName();
	
	void setName(String name);
	
	String getCreattime();
	
	void setCreattime(String createtime);
	
	String getInfomation();
	
	void setInfomation(String infomation);
	
	ICompany getBelong();
	
	void setBelong(ICompany belong);
	
	Double getPrice();
	
	void setPrice(Double price);
	
	Integer getShelve();
	
	void setShelve(Integer shelve);
	
	String getAddr();
	
	void setAddr(String addr);
	
	String getContact();
	
	void setContact(String contact);
	
	String getContactphone();
	
	void setContactphone(String contactphone);
	
	String getPicone();
	
	void setPicone(String picone);
	
	String getPictwo();
	
	void setPictwo(String pictwo);
	
	String getPicthree();
	
	void setPicthree(String picthree);
}
