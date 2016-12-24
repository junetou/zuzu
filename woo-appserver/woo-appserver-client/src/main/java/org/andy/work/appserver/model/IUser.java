package org.andy.work.appserver.model;

import java.util.Date;

public interface IUser {
	
	Integer getId();

	void setId(Integer id);

	Integer getVersion();

	void setVersion(Integer version);

	String getUsername();

	void setUsername(String username);

	String getDisplayName();

	void setDisplayName(String displayName);

	String getPassword();

	void setPassword(String password);
	
	String getLocked();
	
	void setLocked(String locked);
	
	IUserGroup getUserGroup();

	void setUserGroup(IUserGroup userGroup);
	
}
