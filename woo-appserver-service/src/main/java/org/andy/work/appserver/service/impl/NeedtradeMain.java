package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.INeedTradeDAO;
import org.andy.work.appserver.model.INeedTrade;
import org.andy.work.appserver.model.impl.NeedTrade;
import org.andy.work.appserver.service.INeedtradeMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Service;


@Service
public class NeedtradeMain implements INeedtradeMain {
	
	@Resource 
	INeedTradeDAO needtrade;
	
	@Override
	public SearchResponse<INeedTrade> searchsuccessborrow(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid)
	{
		return this.needtrade.searchsuccessborrow(searchReq, userid);
	}
	 
	@Override
	public SearchResponse<INeedTrade> searchsuccessseller(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid){
		return this.needtrade.searchsuccessseller(searchReq, userid);
	}
	 
	@Override
	public String updatemessage(NeedTrade trade){
		String judge=this.needtrade.updatemessage(trade);
		return judge;
	}
	 
	@Override
	public String addmessage(NeedTrade trade){
		String judge=this.needtrade.addmessage(trade);
		return judge;
	}
	 
	@Override
	public  NeedTrade getTrade(Integer tradeid){
		return this.needtrade.getTrade(tradeid);
	}

}
