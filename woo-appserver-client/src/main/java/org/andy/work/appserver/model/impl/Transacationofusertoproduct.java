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
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.IUser;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="transacationofusertoproduct")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Transacationofusertoproduct")
public class Transacationofusertoproduct implements Serializable,ITransacationofusertoproduct {
     
	private Integer id;
	private IProduct productid;
	private ICompany companyid;
	private IUser userid;
	private String creattime;
	private String lasttime;
	private Double money;
	private Integer success;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Version
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Product.class)
	@JoinColumn(name="productid")
	public IProduct getProductid() {
		return productid;
	}
	public void setProductid(IProduct productid) {
		this.productid = productid;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Company.class)
	@JoinColumn(name="companyid")
	public ICompany getCompanyid() {
		return companyid;
	}
	public void setCompanyid(ICompany companyid) {
		this.companyid = companyid;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name="userid")
	public IUser getUserid() {
		return userid;
	}
	public void setUserid(IUser userid) {
		this.userid = userid;
	}
	@Column(name="creattime",nullable=false)
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	@Column(name="lasttime",nullable=false)
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	@Column(name="money",nullable=false,scale=4)
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Column(name="success",nullable=false)
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}

}
