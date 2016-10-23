package org.andy.work.admin.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.andy.work.admin.weixin.SignUtil;
import org.andy.work.admin.weixin.CoreService;


public class CoreServlet extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(CoreServlet.class);
	/**
	 * 确认请求来自微信服务器
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//微信加密签名
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp"); 
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		//通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败 
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		log.info("======================解析请求开始===========================");
		// 调用核心业务类接收消息、处理消息
		CoreService coreService = new CoreService();
		String respMessage = coreService.processRequest(request);
		log.info("======================回复的信息为："+respMessage+"===========================");
		log.info("======================解析请求结束===========================");
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
	/**
	 * 解析微信发来的请求(XML)
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception{
		// 将解析结果存储在HashMap中  
	    Map<String, String> map = new HashMap<String, String>();  
	  
	    // 从request中取得输入流  
	    InputStream inputStream = request.getInputStream();  
	    // 读取输入流  
	    SAXReader reader = new SAXReader();  
	    Document document = reader.read(inputStream);  
	    // 得到xml根元素  
	    Element root = document.getRootElement();  
	    // 得到根元素的所有子节点  
	    List<Element> elementList = root.elements();  
	  
	    // 遍历所有子节点  
	    for (Element e : elementList)  
	        map.put(e.getName(), e.getText());  
	  
	    // 释放资源  
	    inputStream.close();  
	    inputStream = null;  
		return map;
		
	}

}
