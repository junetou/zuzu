package org.andy.work.appserver.model.impl;

import java.io.Serializable;

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
import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IUser;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="Fed")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Fed")
public class Fed implements Serializable, IFed {
	
	
	private Integer id;
	private String infomation;
	private IUser userid;
	private Integer belong;
	private Integer productid;
	private ICompany companyid;
	private Integer shalve;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Version
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="infomation",length=250,nullable=false)
	public String getInfomation() {
		return infomation;
	}
	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name="userid")
	public IUser getUserid() {
		return userid;
	}
	public void setUserid(IUser userid) {
		this.userid = userid;
	}
	@Column(name="productid",nullable=false)
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	@Column(name="belong",nullable=false)
	public Integer getBelong() {
		return belong;
	}
	public void setBelong(Integer belong) {
		this.belong = belong;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Company.class)
	@JoinColumn(name="company")
	public ICompany getCompanyid() {
		return companyid;
	}
	public void setCompanyid(ICompany companyid) {
		this.companyid = companyid;
	}
	@Column(name="shalve",nullable=false)
	public Integer getShalve() {
		return shalve;
	}
	public void setShalve(Integer shalve) {
		this.shalve = shalve;
	}

}
