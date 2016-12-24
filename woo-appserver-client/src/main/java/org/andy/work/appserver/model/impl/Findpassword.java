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
import org.andy.work.appserver.model.IFindpassword;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="findpassword")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Findpassword")
public class Findpassword implements Serializable,IFindpassword {

	Integer id;
	ICompany companyid;
	Integer shelve;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Version
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Company.class)
	@JoinColumn(name="companyid")
	public ICompany getCompanyid() {
		return companyid;
	}
	public void setCompanyid(ICompany companyid) {
		this.companyid = companyid;
	}
	@Column(name="shelve")
	public Integer getShelve() {
		return shelve;
	}
	public void setShelve(Integer shelve) {
		this.shelve = shelve;
	}
}
