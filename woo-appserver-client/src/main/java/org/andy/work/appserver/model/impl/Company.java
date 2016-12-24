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

import org.andy.work.appserver.model.ICompany;
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
@Table(name="company")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Company")
public class Company implements Serializable, ICompany {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3777655431317415578L;
	private Integer id;
	private Integer version;
	private String companyname;
	private String displayName;
	private String password;
	private String locked;
	private String addr;
	private String phone;
	private String email;
	private String head;
	private String contact;
	private IUserGroup userGroup;
	private Double money;
	private String infomation;
	private String createtime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Version
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="companyname", length=30, unique=true, nullable=false)
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String username) {
		this.companyname = username;
	}
	@Column(name="displayname", length=30, nullable=false)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(name="password",length=60, nullable=false)
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
	@Column(name="addr",nullable=false)
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Column(name="phone",length=15, nullable=false)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="email",nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="head",length=250)
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	@Column(name="contact",length=250)
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name="version",nullable=false)
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=UserGroup.class)
	@JoinColumn(name="group_id")
	public IUserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(IUserGroup userGroup) {
		this.userGroup = userGroup;
	}
	@Column(name="money",scale=4,nullable=false)
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Column(name="infomation",nullable=false,length=300)
	public String getInfomation() {
		return infomation;
	}
	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}
	@Column(name="createtime")
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
}
