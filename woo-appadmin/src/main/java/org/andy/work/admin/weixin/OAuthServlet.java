package org.andy.work.admin.weixin;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.andy.work.admin.weixin.Constants;
import org.andy.work.admin.weixin.AccessToken;
import org.andy.work.admin.weixin.OAuthInfo;
import org.andy.work.admin.weixin.UserInfo;
import org.andy.work.admin.weixin.GetWeiXinCode;
import org.andy.work.admin.weixin.WeixinUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class OAuthServlet extends HttpServlet {

	private Log log = LogFactory.getLog(getClass());
	

	private static final long serialVersionUID = 1L;

	public OAuthServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接受参数
		String code = request.getParameter("code");
		String scope = request.getParameter("scope");
		log.info("==============[OAuthServlet]获取网页授权code="+code);
		log.info("==============[OAuthServlet]获取网页跳转权限="+scope);
		
		
		if(null != code && !"".equals(code)){
			log.info("==============[OAuthServlet]获取网页授权code不为空，code="+code);
			//根据code换取openId
			OAuthInfo oa = WeixinUtil.getOAuthOpenId(Constants.appId,Constants.appSecret,code);
			UserInfo info = WeixinUtil.getUserInfo(oa.getAccessToken(), oa.getOpenId());
			if(!"".equals(oa) && null != oa){
				 log.info("==============[OAuthServlet]获取网页授权openID="+oa.getOpenId());
				 request.setAttribute("openid", oa.getOpenId());
				 request.setAttribute("nickname", info.getNickname());
				 response.sendRedirect("index.jsp");
				 
			}else{
				log.info("==============[OAuthServlet]获取网页授权openId失败！");
			}
		}else{
			log.info("==============[OAuthServlet]获取网页授权code失败！");
		}
	}

	
	public void init() throws ServletException {
		
	}

}
