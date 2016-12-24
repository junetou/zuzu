package org.andy.work.wechat;

import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class CoreService {

	private Log log = LogFactory.getLog(getClass());
	
	public String processRequest(HttpServletRequest request) throws Exception{
		
		String repMessage="感谢你的留言，我们会尽快回复";
		 
		Map<String,String> map= new HashMap<String, String>(); 
		
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
	    {map.put(e.getName(), e.getText());}
	        // 释放资源  
	    inputStream.close();  
	    inputStream = null;
	    
	    String toUserName = map.get("ToUserName");
		String fromUserName = map.get("FromUserName");
		String msgType = map.get("MsgType");
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType("text");
		textMessage.setFuncFlag(0);
		textMessage.setContent(repMessage);
		xstream.alias("xml", textMessage.getClass());  
        repMessage=xstream.toXML(textMessage); 
        return repMessage;
	}
	
	private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
  
                @SuppressWarnings("unchecked")  
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }  
  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });  
	
	
}
