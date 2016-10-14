package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.INeedTrade;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="needtrade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.NeedTrade")
public class NeedTrade implements Serializable,INeedTrade {
	
	private static final long serialVersionUID = 3777655431317415578L;
	private Integer trade;
	private Integer need;
	private Integer borrow;
	private Integer seller;
	private Integer success;
	private String borrowname;
	private String sellername;
	private String goodsname;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getTrade() {
		return trade;
	}
	public void setTrade(Integer trade) {
		this.trade = trade;
	}
	
	@Column(name="need")
	public Integer getNeed() {
		return need;
	}
	public void setNeed(Integer need) {
		this.need = need;
	}
	
	@Column(name="borrow")
	public Integer getBorrow() {
		return borrow;
	}
	public void setBorrow(Integer borrowid) {
		this.borrow = borrowid;
	}
	
	@Column(name="seller")
	public Integer getSeller() {
		return seller;
	}
	public void setSeller(Integer sellerid) {
		this.seller = sellerid;
	}

	
	@Column(name="success")
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	@Column(name="borrowname")
	public String getBorrowname() {
		return borrowname;
	}
	public void setBorrowname(String borrowname) {
		this.borrowname = borrowname;
	}
	
	@Column(name="sellername")
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	
	@Column(name="goodsname")
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

}
