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
import org.andy.work.appserver.model.ICompanyhead;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="companyhead")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Companyhead")
public class Companyhead implements Serializable,ICompanyhead {
    
	private Integer id;
	private String headone;
    private String headtwo;
    private String headthree;
    private ICompany companyid;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
    @Version
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHeadone() {
		return headone;
	}
	@Column(name="headone")
	public void setHeadone(String headone) {
		this.headone = headone;
	}
	public String getHeadtwo() {
		return headtwo;
	}
	@Column(name="headtwo")
	public void setHeadtwo(String headtwo) {
		this.headtwo = headtwo;
	}
	public String getHeadthree() {
		return headthree;
	}
	@Column(name="headthree")
	public void setHeadthree(String headthree) {
		this.headthree = headthree;
	}
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Company.class)
	@JoinColumn(name="compandid",unique=true, nullable=false)
	public ICompany getCompanyid() {
		return companyid;
	}
	public void setCompanyid(ICompany companyid) {
		this.companyid = companyid;
	}
    
    
	
}
