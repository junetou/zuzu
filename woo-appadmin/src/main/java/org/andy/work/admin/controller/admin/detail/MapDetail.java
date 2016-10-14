package org.andy.work.admin.controller.admin.detail;

public class MapDetail {

	private Integer thingsId;
	private String thingsdesc;
	private String thingsname;
	private String thingsprice;
	private String thingskind;
	public Integer thingsoveranalyzed;
	public String thingspicturename;
	public String thingsaddr;
	public String thingsphone;
	public Integer judge;//1个物品，2为需求
	public Double lng;
	public Double lat;
	
	
	
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getThingsaddr() {
		return thingsaddr;
	}
	public void setThingsaddr(String thingsaddr) {
		this.thingsaddr = thingsaddr;
	}
	public String getThingsphone() {
		return thingsphone;
	}
	public void setThingsphone(String thingsphone) {
		this.thingsphone = thingsphone;
	}
	
	public Integer getJudge() {
		return judge;
	}
	public void setJudge(Integer judge) {
		this.judge = judge;
	}
	public String getThingspicturename() {
		return thingspicturename;
	}
	public void setThingspicturename(String thingspicturename) {
		this.thingspicturename = thingspicturename;
	}
	public Integer getThingsId() {
		return thingsId;
	}
	public void setThingsId(Integer thingsId) {
		this.thingsId = thingsId;
	}
	public String getThingsdesc() {
		return thingsdesc;
	}
	public void setThingsdesc(String thingsdesc) {
		this.thingsdesc = thingsdesc;
	}
	public String getThingsname() {
		return thingsname;
	}
	public void setThingsname(String thingsname) {
		this.thingsname = thingsname;
	}
	public String getThingsprice() {
		return thingsprice;
	}
	public void setThingsprice(String thingsprice) {
		this.thingsprice = thingsprice;
	}
	public void setThingskind(String kind){
		this.thingskind=kind;
	}
	public String getThingskind(){
		return this.thingskind;
	}
   
	public void setThingsoveranalyzed(Integer thingsoveranalyzed){
		this.thingsoveranalyzed=thingsoveranalyzed;
	}
	public Integer getThingsoveranalyzed(){
		return this.thingsoveranalyzed;
	}
	
}
