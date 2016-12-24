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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Product")
public class Product implements Serializable,IProduct {
	
	private Integer id;
	private ICompany belong;
	private Double price;
	private Integer shelve;
	private String name;
	private String creattime;
	private String infomation;
	private String addr;
	private String contact;
	private String contactphone;
	private String picone;
	private String pictwo;
	private String picthree;
	
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
	@JoinColumn(name="belong",nullable=false,unique=false)
	public ICompany getBelong() {
		return belong;
	}
	public void setBelong(ICompany belong) {
		this.belong = belong;
	}
	@Column(name="price",scale=2,nullable=false,unique=false)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name="shelve",nullable=false,unique=false)
	public Integer getShelve() {
		return shelve;
	}
	public void setShelve(Integer shelve) {
		this.shelve = shelve;
	}
	@Column(name="name",length=10,nullable=false,unique=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="creattime",nullable=false,unique=false)
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	@Column(name="infomation",length=305,nullable=false,unique=false)
	public String getInfomation() {
		return infomation;
	}
	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}
	@Column(name="addr",nullable=false,unique=false)
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Column(name="contact",nullable=false,unique=false)
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name="contactphone",length=20,nullable=false,unique=false)
	public String getContactphone() {
		return contactphone;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	@Column(name="picone")
	public String getPicone() {
		return picone;
	}
	public void setPicone(String picone) {
		this.picone = picone;
	}
	@Column(name="pictwo")
	public String getPictwo() {
		return pictwo;
	}
	public void setPictwo(String pictwo) {
		this.pictwo = pictwo;
	}
	@Column(name="picthree")
	public String getPicthree() {
		return picthree;
	}
	public void setPicthree(String picthree) {
		this.picthree = picthree;
	}


}
