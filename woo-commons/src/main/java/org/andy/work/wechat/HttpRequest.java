package org.andy.work.wechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



public class HttpRequest {

	public static JSONObject httpRequest(String requesturl,String requestmod,String putstr){
		
		JSONObject jsonobject=null;
		StringBuffer buff=new StringBuffer();
		try{
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager tm[] = { new MyX509TrustManager() };
			SSLContext ssl=SSLContext.getInstance("SSL", "SunJSSE");
			ssl.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf=ssl.getSocketFactory();
			URL url=new URL(null,requesturl,new sun.net.www.protocol.https.Handler());
			HttpsURLConnection httpurlconn=(HttpsURLConnection) url.openConnection();
			httpurlconn.setSSLSocketFactory(ssf);
			httpurlconn.setDoOutput(true);
			httpurlconn.setDoInput(true);
			httpurlconn.setUseCaches(false);
			httpurlconn.setRequestMethod(requestmod);
			if ("GET".equalsIgnoreCase(requestmod)){
				httpurlconn.connect();
			}
			if (null != putstr) {
				OutputStream outputStream = httpurlconn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(putstr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputstream=httpurlconn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputstream, "utf-8");
			BufferedReader buffs=new BufferedReader(inputStreamReader);
			String str=null;
			while((str=buffs.readLine())!=null){
				buff.append(str);
			}
			buffs.close();
			inputStreamReader.close();
			inputstream.close();
			inputstream=null;
			httpurlconn.disconnect();
			jsonobject = JSONObject.fromObject(buff);	
		}
		catch (ConnectException ce) {
		} 
		catch (Exception e) {
		}
		return jsonobject;
	}
	
	public static String o_auth_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	public static OAuthInfo getOAuthOpenId(String appid, String secret, String code ) {
		OAuthInfo oAuthInfo = null;
		String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
	

		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        UserLogin user=new UserLogin();
		
		// 如果请求成功
		if (null != jsonObject) {
			try {
				oAuthInfo = new OAuthInfo();
				oAuthInfo.setAccessToken(jsonObject.getString("access_token"));
				oAuthInfo.setExpiresIn(jsonObject.getInt("expires_in"));
				oAuthInfo.setRefreshToken(jsonObject.getString("refresh_token"));
				oAuthInfo.setOpenId(jsonObject.getString("openid"));
				oAuthInfo.setScope(jsonObject.getString("scope"));
			} catch (JSONException e) {
				oAuthInfo = null;
				// 获取token失败
			}
		}
		return oAuthInfo;
	}
	
	//获取token
	public static AccessToken getAccessToken(){
		AccessToken token=null;
		String requesturl=Constant.access_token_url;
		requesturl.replace("APPID", Constant.appid).replace("APPSECRET", Constant.appSecret);
		JSONObject object=httpRequest(requesturl, "GET", null);
		if(object!=null){
			try{
				token = new AccessToken();
				token.setToken(object.getString("access_token"));
				token.setTime(object.getInt("expires_in"));
			}
			catch (JSONException e) {
				token = null;
			}
		}
		return token;
	}
	
	//获取access_token，目的根据token获取用户信息
	public static  OAuthInfo getOpenId(String code){
		OAuthInfo auth=null;
		String requesturl=Constant.o_auth_openid_url.replaceAll("APPID", Constant.appid).replaceAll("SECRET", Constant.appSecret).replaceAll("CODE", code);
        JSONObject object=httpRequest(requesturl, "GET", null);
        if(object!=null){
        	try{
        	auth=new OAuthInfo();
        	auth.setAccessToken(object.getString("access_token"));
        	auth.setExpiresIn(object.getInt("expires_in"));
        	auth.setRefreshToken(object.getString("refresh_token"));
			auth.setOpenId(object.getString("openid"));
			auth.setScope(object.getString("scope"));
        	}
        	catch(JSONException e){
        		auth=null;
        	}
        }
        return auth;
	}
	
	//获取用户信息
	public static UserInfo getUserInfo(String token,String openid){
		UserInfo user=null;
		String url=Constant.userinfo_url.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		JSONObject object=httpRequest(url,"GET",null);
		if(object!=null){
			try{
				user=new UserInfo();
				user.setNickname(object.getString("nickname"));
			}
			catch(JSONException e){
				user=null;
			}
		}
		return user;
	}
	
}
