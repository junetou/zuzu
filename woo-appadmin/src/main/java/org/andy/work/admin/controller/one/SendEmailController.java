package org.andy.work.admin.controller.one;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.andy.work.property.SystemProperiesConfigure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SendEmailController {

	 private String host = "smtp.163.com";  //smtp服务器
	 private String from = "15626287636@163.com";  //发件人地址
	 private String to = "756332784@qq.com";    //收件人地址
	 private String user = "15626287636@163.com";  //用户名
	 private String pwd = "abc123";   //密码
	 private String subject = "一平台发出的信息"; //邮件标题
	
    public String  sendEmail(String tos) {
           
            Properties props = new Properties();
            
          //设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", 25);
            //需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            props.put("mail.smtp.auth", "true");
            //用刚刚设置好的props对象构建一个session
            Session session = Session.getDefaultInstance(props);
          
            //有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
            //用（你可以在控制台（console)上看到发送邮件的过程）
          
            //用session为参数定义消息对象
            MimeMessage message = new MimeMessage(session);
            try{
             //加载发件人地址
                message.setFrom(new InternetAddress(from));
               //加载收件人地址
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(tos));
               //加载标题
                message.setSubject(subject);
                // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
                Multipart multipart = new MimeMultipart();         
                //   设置邮件的文本内容
                Random random=new Random();
        		String one=String.valueOf(random.nextInt(10));
        		String two=String.valueOf(random.nextInt(10));
        		String three=String.valueOf(random.nextInt(10));
        		String four=String.valueOf(random.nextInt(10));
        		String five=String.valueOf(random.nextInt(10));
        		String emailString=one+two+three+four+five;
                BodyPart contentPart = new MimeBodyPart();
                contentPart.setText("你好，感谢贵公司对本网站的支持，你收到的邮箱验证码为:"+emailString);
                multipart.addBodyPart(contentPart);
                //将multipart对象放到message中
                message.setContent(multipart);
                //保存邮件
                message.saveChanges();
                //发送邮件
                Transport transport = session.getTransport("smtp");
                //连接服务器的邮箱
                transport.connect(host, user, pwd);
                //把邮件发送出去
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                return emailString;
            }catch(Exception e){
                e.printStackTrace();
            }
        	return null;
        }  
    
    public void  sendRegister(String tos,String infomation) {
        
        Properties props = new Properties();
        
      //设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 25);
        //需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");
        //用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);
      
        //有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        //用（你可以在控制台（console)上看到发送邮件的过程）
      
        //用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try{
         //加载发件人地址
            message.setFrom(new InternetAddress(from));
           //加载收件人地址
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(tos));
           //加载标题
            message.setSubject(subject);
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();         
            //   设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText("你好，感谢贵公司对本网站的支持，贵公司申请的账号"+infomation);
            multipart.addBodyPart(contentPart);
            //将multipart对象放到message中
            message.setContent(multipart);
            //保存邮件
            message.saveChanges();
            //发送邮件
            Transport transport = session.getTransport("smtp");
            //连接服务器的邮箱
            transport.connect(host, user, pwd);
            //把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }  
    
    
}
