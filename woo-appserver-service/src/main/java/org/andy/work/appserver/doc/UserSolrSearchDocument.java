package org.andy.work.appserver.doc;

import org.apache.solr.client.solrj.beans.Field;

public class UserSolrSearchDocument {

	@Field
	private String id;
	@Field
	private String username;
	@Field
	private String displayName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
