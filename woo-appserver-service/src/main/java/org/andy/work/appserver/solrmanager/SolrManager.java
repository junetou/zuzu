package org.andy.work.appserver.solrmanager;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.stereotype.Component;


@Component
public class SolrManager {

	private HttpSolrServer httpserver;
	
	private ConcurrentUpdateSolrServer updateserver; //实例化solr
	
	public SolrManager(){
		//String url="http://admin:admin@localhost:80/solrs/collection1/";
		String url="http://localhost:80/solrs/collection1/";
        this.httpserver=this.initHttpSolr(url);//连接
        
        this.updateserver=this.updateSolr(url);//实例化
	}
	
	public HttpSolrServer initHttpSolr(String url){
		
		HttpSolrServer server=new HttpSolrServer(url);
		server.setAllowCompression(true);//运行压缩
		server.setConnectionTimeout(5000);//设置连接超时
		server.setMaxRetries(1);//solr最大重试次数
		server.setSoTimeout(5000);//设置读取数据超时
		server.setMaxTotalConnections(1000);//设置连接池最大连接数
		server.setFollowRedirects(false);
		return server;
	}
	
	public ConcurrentUpdateSolrServer updateSolr(String url){
		
		ConcurrentUpdateSolrServer updateServer = new ConcurrentUpdateSolrServer(url, 1000, 1);
		updateServer.setConnectionTimeout(5000);//连接超时
		updateServer.setSoTimeout(10000);
        return updateServer;
	}
	
	public HttpSolrServer getHttpSolr(){
		return this.httpserver;
	}
	
	public ConcurrentUpdateSolrServer getUpdate(){
		return this.updateserver;
	}
	
	public void commint() throws SolrServerException, IOException {
		this.updateserver.commit(false, false);
	}
	
	
}
