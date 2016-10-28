package org.andy.work.appserver.model;

public interface INeedTrade {
	
	public void setTrade(Integer trade);
	public void setNeed(Integer need);
	public void setBorrow(Integer borrowid);//需要借的的人id
	public void setSeller(Integer sellerid);//货主的id即本人
	public void setSuccess(Integer success);//交易成功
	public void setGoodsname(String goodsname);//需求物品的名字
	public void setBorrowname(String borrowname);//需求者名字
	public void setSellername(String sellername);//货主名字
	
	public Integer getTrade();
	public Integer getNeed();
	public Integer getBorrow();
	public Integer getSeller();
	public Integer getSuccess();
	public String getGoodsname();
	public String getBorrowname();
	public String getSellername();
	
	
	
	
	
	
}
