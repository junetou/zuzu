package org.andy.work.admin.weixin;
/**
 * 微信通用接口凭证
 * @author 宗潇帅
 * @修改日期 2014-7-14上午10:23:30
 */
public class AccessToken {
	//获取到的凭证
	private String token;
	//凭证有效时间   单位 ：秒
	private int expiresIn;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
