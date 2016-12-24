package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 管理员
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.User")
public class User implements Serializable, IUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3777655431317415578L;
	private Integer id;
	private Integer version;
	private String username;
	private String displayName;
	private String password;
	private String locked;
	private IUserGroup userGroup;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Version
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name="username", length=30, unique=true, nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="displayname", length=30, nullable=false)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(length=60, nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="locked",length=60, nullable=false)
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=UserGroup.class)
	@JoinColumn(name="group_id")
	public IUserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(IUserGroup userGroup) {
		this.userGroup = userGroup;
	}
}
