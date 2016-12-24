package org.andy.work.wechat;

public class AccessToken {
	//获取到的凭证
	private String token;
	//凭证有效时间   单位 ：秒
	private int time;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int times) {
		this.time = times;
	}
	
}
