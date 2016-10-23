package org.andy.work.admin.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.andy.work.admin.weixin.Article;
import org.andy.work.admin.weixin.NewsMessage;
import org.andy.work.admin.weixin.TextMessage;
import org.andy.work.admin.weixin.Constants;
import org.andy.work.admin.weixin.MessageUtil;

/**
 * 核心服务类
 * 
 * @author 宗潇帅
 * @修改日期 2014-7-14下午6:16:43
 */
public class CoreService {
	private Log log = LogFactory.getLog(getClass());

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "";
			String basePath = "";

			// 解析发送过来的xml信息
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 得到-公众帐号、发送方帐号（open_id）、消息类型
			String toUserName = requestMap.get("ToUserName");
			String fromUserName = requestMap.get("FromUserName");
			String msgType = requestMap.get("MsgType");
			log.info("===============fromUserName ==================="+fromUserName);
			// 公众号-默认回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 公众号-回复图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);
			List<Article> articleList = new ArrayList<Article>();
			if (!msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 非事件推送
				// 判断用户推送来的是何种消息或者何种事件，并作出相应的回复
				if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
					respContent = "谢谢您的留言，我们会尽快回复您!";
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
					respContent = "谢谢您的留言，我们会尽快回复您!";
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 地理位置消息
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
				} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 音频消息
					respContent = "谢谢您的留言，我们会尽快回复您!";
				}

			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 事件推送
				// 事件类型
				String eventType = requestMap.get("Event");
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
					respContent = "被关注的感觉真好~开始新鲜到家的体验~嘿，小伙伴，快来体验哦！";
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消订阅
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件

					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("APP_XIA_ZAI")) {
						StringBuffer sb = new StringBuffer();
						sb.append("下载手机客户端，更快更便捷!").append("\n\n");
						sb.append("Android用户请复制以下链接到您手机自带的浏览器下载：").append("\n");
						respContent = sb.toString();
					} else if (eventKey.equals("")) {
						
					} else if (eventKey.equals("")) {
						respContent ="按月查询";
					}
				}
			}
			if (!"".equals(respContent) && respContent != null) {//文字
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (articleList.size() > 0) {//图文
				newsMessage.setArticleCount(articleList.size());
				newsMessage.setArticles(articleList);
				respMessage = MessageUtil.newsMessageToXml(newsMessage);
			} else {
			}	
			
		} catch (Exception e) {
		}
		return respMessage;
	}
}
