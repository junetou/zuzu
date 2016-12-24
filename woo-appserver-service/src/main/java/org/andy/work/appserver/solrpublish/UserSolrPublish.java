package org.andy.work.appserver.solrpublish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IUserDAO;
import org.andy.work.appserver.doc.UserSolrSearchDocument;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.appserver.solrmanager.SolrManager;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

@Component
public class UserSolrPublish {

	@Resource
	private SolrManager solrmanager;
	
	@Resource
	private IUserDAO userdao;
	
	/*
	 * 启动引擎
	 */
	public void startSolr() throws SolrServerException, IOException{
		
		List<IUser> user=this.userdao.SearchAllUser();
		List<UserSolrSearchDocument> docs=new ArrayList<UserSolrSearchDocument>();
		for(IUser use:user){
			UserSolrSearchDocument doc=new UserSolrSearchDocument();
			doc.setDisplayName(use.getDisplayName());
			doc.setId(use.getId().toString());
			doc.setUsername(use.getUsername());
			docs.add(doc);
		}
		this.solrmanager.getUpdate().addBeans(docs);
		this.solrmanager.commint();
	}
	
	/*
	 * 清除引擎
	 */
	public void removeSolr(){
		try{
		this.solrmanager.getUpdate().deleteByQuery("*:*");
		}
		catch(Exception e){
			System.out.println("error delete");
		}
	}
	
	/*
	 * 搜索
	 */
	public List<IUser> keyworkSearch(String keywork){
		
		List<IUser> user=new ArrayList<IUser>();
		try{
			SolrQuery query=new SolrQuery();
			String search="id:"+keywork;
			query.setParam("q","id:*");
			query.setStart(0);
			query.setRows(10);
			QueryResponse querysolr=this.solrmanager.getHttpSolr().query(query);
			SolrDocumentList result= querysolr.getResults();
			user=this.getBeans(result);
		}catch(Exception e){
			System.out.println(e);
		}
		return user;
	}
	
	
	private List<IUser> getBeans(SolrDocumentList result) {
		
		List<IUser> user=new ArrayList<IUser>();
		for (SolrDocument doc : result) {
			IUser use=new User();
			use.setDisplayName(doc.get("displayName").toString().replace("[", "").replace("]", ""));
			use.setUsername(doc.get("username").toString().replace("[", "").replace("]", ""));
			user.add(use);
		}
		return user;
	}
	
}
