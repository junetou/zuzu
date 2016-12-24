package org.andy.work.appserver.model;

public interface ICompany {
	
	Integer getId();

	void setId(Integer id);

	String getCompanyname();

	void setCompanyname(String company);

	String getDisplayName();

	void setDisplayName(String displayName);

	String getPassword();

	void setPassword(String password);
	
	String getLocked();
	
	void setLocked(String locked);
	
	String getAddr();
	
	void setAddr(String addr);
	
	String getPhone();
	
	void setPhone(String phone);
	
	String getEmail();
	
	void setEmail(String email);
	
	String getHead();
	
	void setHead(String head);
	
    String getContact();
	
	void setContact(String contact);
	
	IUserGroup getUserGroup();

	void setUserGroup(IUserGroup userGroup);
	
	Integer getVersion();
	
	void setVersion(Integer version);
	
	Double getMoney();
	
	void setMoney(Double money);
	
	String getInfomation();
	
	void setInfomation(String infomation);
	
	String getCreatetime();
	
	void setCreatetime(String createtime);
}
