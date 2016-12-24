package org.andy.work.appserver.model;


public interface IUser {
	
	Integer getId();

	void setId(Integer id);

	String getUsername();

	void setUsername(String username);

	String getDisplayName();

	void setDisplayName(String displayName);

	String getPassword();

	void setPassword(String password);
	
	String getLocked();
	
	void setLocked(String locked);
	
	String getHead();
	
	void setHead(String head);
	
	String getPhone();
	
	void setPhone(String phone);
	
	IUserGroup getUserGroup();

	void setUserGroup(IUserGroup userGroup);
	
	Integer getVersion();
	
	void setVersion(Integer version);
	
	Double getMoney();
	
	void setMoney(Double money);
	
}
