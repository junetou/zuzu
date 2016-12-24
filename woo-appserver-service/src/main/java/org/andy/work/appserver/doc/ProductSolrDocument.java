package org.andy.work.appserver.doc;

import org.andy.work.appserver.model.ICompany;
import org.apache.solr.client.solrj.beans.Field;

public class ProductSolrDocument {

	@Field
	private int id;
	@Field
	private Double price;
	@Field
	private String productname;
	@Field
	private String creattime;
	@Field
	private String infomation;
	@Field
	private String addr;
	@Field
	private String contact;
	@Field
	private String contactphone;
	@Field
	private String companyname;
	@Field
	private int companyid;
	@Field
	private String picone;
	@Field
	private String pictwo;
	@Field
	private String picthree;
    @Field
    private int versions;
	
	public String getPicone() {
		return picone;
	}
	public void setPicone(String picone) {
		this.picone = picone;
	}
	public String getPictwo() {
		return pictwo;
	}
	public void setPictwo(String pictwo) {
		this.pictwo = pictwo;
	}
	public String getPicthree() {
		return picthree;
	}
	public void setPicthree(String picthree) {
		this.picthree = picthree;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String name) {
		this.productname = name;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getInfomation() {
		return infomation;
	}
	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactphone() {
		return contactphone;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public int getVersions() {
		return versions;
	}
	public void setVersions(int versions) {
		this.versions = versions;
	}
	
}
