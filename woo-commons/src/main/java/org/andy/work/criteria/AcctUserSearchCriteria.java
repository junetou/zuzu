package org.andy.work.criteria;

import java.io.Serializable;

public class AcctUserSearchCriteria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3761475495003906908L;
	private String displayName;
	private Integer group;
	private String keyWord;
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Integer getGroup() {
		return group;
	}
	public void setGroup(Integer group) {
		this.group = group;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
