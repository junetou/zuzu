package org.andy.work.paging;

import java.io.Serializable;

public class PagingManagement implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6167392837563298604L;
	private long totalRecord;   //设置查询的总数目
	private int currentPageNum = 1; // 用户现在查看的页数
	private int numberPerPage = 20; // 最多可以多少条数目才到下页面
	private int totalPageNum;  //总的页数
	private String action;
	
	public int getCurrentPageNum() {
		return currentPageNum;
	}

	/*
	 * 设计用户当前的页面
	 */
	public void setCurrentPageNum(int currentPageNum) {
		if (currentPageNum < 1)
		{
			this.currentPageNum = 1;
		}
		else
		{
			this.currentPageNum = currentPageNum;
		}
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	/*
	 * 设计查询想总数据。即数据的条数
	 */
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) 
	{
		if (totalRecord < 0)
		{
			this.totalRecord = 0;
		}
		else
		{
			this.totalRecord = totalRecord;
		}
		
		this.calculateTotalPageNum();
	}
	
	/*
	 * 设计最大下一页页数
	 */
	public int getNumberPerPage() 
	{
		return numberPerPage;
	}
	public void setNumberPerPage(int numberPerPage) 
	{
		this.numberPerPage = numberPerPage;
		this.calculateTotalPageNum();
	}
	
	/*
	 * 设计总页数
	 */
	private void calculateTotalPageNum()
	{
		if (this.totalRecord > 0)
		{
			this.totalPageNum = ((Long) (this.totalRecord / this.numberPerPage)).intValue();
			if (this.totalRecord % this.numberPerPage > 0)
			{
				totalPageNum += 1;
			}
		}
		else
		{
			this.totalPageNum = 1;
		}
	}
	
	/*
	 * 设计下一页的数据开始地方
	 */
	public int getFirstResult()
	{
		int firstResult = (this.currentPageNum - 1) * this.numberPerPage;
		return firstResult;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}
}
