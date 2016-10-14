package org.andy.work.appserver.dao;

import org.andy.work.appserver.model.INeedTrade;
import org.andy.work.appserver.model.impl.NeedTrade;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface INeedTradeDAO extends IGenericDAO {
		 
	 SearchResponse<INeedTrade> searchsuccessborrow(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid);
	 
	 SearchResponse<INeedTrade> searchsuccessseller(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid);
	 
	 String updatemessage(NeedTrade trade);
	 
	 String addmessage(NeedTrade trade);
	 
	 NeedTrade getTrade(Integer tradeid);
	 
	

}
