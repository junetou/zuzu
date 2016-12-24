package org.andy.work.admin.controller.one;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.andy.work.admin.helper.EmailCodeCookie;
import org.andy.work.admin.helper.PhoneCodeCookie;
import org.andy.work.admin.helper.VerifyCodeCookie;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.ICompanyhead;
import org.andy.work.appserver.model.impl.Company;
import org.andy.work.appserver.model.impl.Companyhead;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.ICompanyheadMaintenanceService;
import org.andy.work.form.AddForm;
import org.andy.work.form.RegisterForm;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;




@Controller
public class RegisterController {

	@Autowired 
	private PhoneCodeCookie phonecodecookie;
	
	@Autowired 
	private EmailCodeCookie emailcodecookie;
	
	@Autowired
	private ICompanyMaintenanceService company;

	
	@RequestMapping(value="/register")
	public ModelAndView register(){

		RegisterForm form=new RegisterForm();
		ModelAndView model=new ModelAndView();
		model.addObject("command", form);
		model.setViewName("tiles/form/register");
		return model;
	}
	
	@RequestMapping(value="/sendPhoneCode")
	public void sendPhoneCode(HttpServletResponse response,HttpServletRequest request)throws ServletException, IOException{
		
		response.setContentType("text/html;charset-uft-8");
		PrintWriter out = response.getWriter();
		InputStream inputStream = request.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			  buffer.append(str);
		}
		Random random=new Random();
		String one=String.valueOf(random.nextInt(10));
		String two=String.valueOf(random.nextInt(10));
		String three=String.valueOf(random.nextInt(10));
		String four=String.valueOf(random.nextInt(10));
		String five=String.valueOf(random.nextInt(10));
		String paramString=one+two+three+four+five;
		String sendParamString="{"+"number:"+"'"+paramString+"'"+"}";
		String url="http://gw.api.taobao.com/router/rest";
		String appkey="23564626";
		String secret="5e7b8aaeed6c01c152299f2694dfbe2d";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("hello");
		req.setSmsType("normal");
		req.setSmsFreeSignName("陈锦滔");
		req.setRecNum(buffer.toString());
		req.setSmsTemplateCode("SMS_33655704");
		req.setSmsParamString(sendParamString);
		try{
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
	    String success="true";
		boolean judge=rsp.getBody().contains(success);
		if(judge){
			out.write("true");
		}
		else{
			out.write("false");
		}
		}catch(ApiException e){
			System.out.println(e);
		}
		this.phonecodecookie.setPhoneCode(request,paramString);
	}
	
	@RequestMapping(value="/sendEmailCode")
	public void sendEmailCode(HttpServletResponse response,HttpServletRequest request)throws ServletException, IOException{
		
		response.setContentType("text/html;charset-uft-8");
		PrintWriter out = response.getWriter();
		InputStream inputStream = request.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		String code="";
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			  buffer.append(str);
		}
		Pattern emailpattern=Pattern.compile("^[a-zA-Z0-9.@]+$");
		if(emailpattern.matcher(buffer).matches()){
			SendEmailController send=new SendEmailController();
			code=send.sendEmail(buffer.toString());
			out.write("true");
		}
		else{
			out.write("false");
		}
		this.emailcodecookie.setEmailCode(request,code);
	}
	
	@RequestMapping(value="/checkCode",method=RequestMethod.POST)
	public void checkCode(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	  response.setContentType("text/html;charset-uft-8");
	  PrintWriter out = response.getWriter();
	  InputStream inputStream = request.getInputStream();
	  InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	  BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	  String str = null;
	  StringBuffer buffer = new StringBuffer();
	  while ((str = bufferedReader.readLine()) != null) {
		  buffer.append(str);
	  }
	  String code=buffer.toString();
	  StringBuffer phonecode=new StringBuffer();
	  StringBuffer emailcode=new StringBuffer();
	  int i=0;
	  for(;i<5;i++){
		  phonecode.append(code.charAt(i));
	  }
	  for(;i<10;i++){
		  emailcode.append(code.charAt(i));
	  }
	  this.phonecodecookie.setUseCode(request,phonecode.toString());
      this.emailcodecookie.setUseCode(request, emailcode.toString());
      boolean judge=false;
      if(this.phonecodecookie.isVerify(request)){
    	  if(this.emailcodecookie.isVerify(request)){
    		  judge=true;
    	  }
      }
	  bufferedReader.close();
	  inputStreamReader.close();
	  if(judge){
	  out.write("true");
	  }
	  else{
      out.write("false");  
	  }
	  out.flush();
	  out.close();
	}
	
	@RequestMapping(value="/register/register")
	public ModelAndView addCompany(HttpServletRequest request,RegisterForm form)throws ServletException, IOException{
	
		ModelAndView model=new ModelAndView();
		if(form.getUsrname()!=null){
		model.setViewName("403");
		MultipartFile file=null;
		if (request instanceof MultipartHttpServletRequest) {
		    file=((MultipartHttpServletRequest) request).getFile("file");
	    }
		Pattern pattern=Pattern.compile("^[a-zA-Z0-9_.]+$");
		ICompany companys=new Company();
		companys.setAddr("not");
		if(pattern.matcher(form.getUsrname()).matches()){
			companys=this.company.findUserByusername(form.getUsrname());
		}
		if(companys==null){
		if(this.emailcodecookie.isVerify(request) && this.phonecodecookie.isVerify(request)){
		Pattern comname=Pattern.compile("^[a-zA-Z0-9_.]+$");
		Pattern disname=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$");
		Pattern passwd=Pattern.compile("^[a-zA-Z0-9_.]+$");
		Pattern addr=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$");
		Pattern contact=Pattern.compile("^[\u4e00-\u9fa5]+$");
		Pattern contactphone=Pattern.compile("^[0-9]+$");
        Pattern email=Pattern.compile("^[a-zA-Z0-9.@]+$");
        String head="null";
        if(form.getUsrname()!=null){
        if(comname.matcher(form.getUsrname()).matches()){
   		 if(disname.matcher(form.getName()).matches()){
   		  if(passwd.matcher(form.getPassword()).matches()){
   		   if(addr.matcher(form.getAddr()).matches()){
   			 if(contact.matcher(form.getContact()).matches()){
              if(contactphone.matcher(form.getContactphone()).matches()){
               if(email.matcher(form.getEmail()).matches()){
            	   if(file!=null){
      			     //检测上传的文件
      			     CommonsMultipartFile checkfile= (CommonsMultipartFile)file;
      			     DiskFileItem fi=(DiskFileItem)checkfile.getFileItem();
      			     File f=fi.getStoreLocation();
      			     try{
      		         BufferedImage bi = ImageIO.read(f);//读取文件是否输入图片类型
      			     if(bi == null){
      			     //如何不是图片类型
      			      }
      			     else{//正式加载到数据库
                      int newwidth=600;
                      int newheight=335;
                      BufferedImage image=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
                      Graphics graphics = image.createGraphics();
                      graphics.drawImage(bi, 0, 0, newwidth, newheight, null);
      			      String pictureOneName=file.getOriginalFilename();//获取上传图片名字
      			      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
      			      String path="G:\\project\\staticResources\\static\\registerpicture\\"+form.getUsrname()+"."+suffix;
      			      head=form.getUsrname()+"."+suffix;
      			      DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
      			      ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
      			     }
      		         }
      			     catch(IOException e){
      			     }
      		         }
           		List<ICompany> companyss=this.company.SearchCompany(1);
            	ICompany company=new Company();
           		company.setAddr(form.getAddr());
           		company.setCompanyname(form.getUsrname());
           		company.setDisplayName(form.getName());
           		company.setEmail(form.getEmail());
           		company.setLocked("Y");
		        company.setMoney(Double.valueOf(0));
		        company.setContact(form.getContact());
		        company.setPhone(form.getContactphone());
		        company.setVersion(2);
		        company.setUserGroup(companyss.get(0).getUserGroup());
		        String pw=DigestUtils.md5Hex(form.getPassword());
				String pw1=DigestUtils.md5Hex(pw);
				String pw2=DigestUtils.md5Hex(pw1);
				company.setPassword(pw2);
				company.setHead(head);
				company.setInfomation("该商家还没完善信息");
				Date time=new Date();
				SimpleDateFormat times=new SimpleDateFormat("yyyy MM dd");
				company.setCreatetime(times.format(time));
				this.company.saveUser(company);
				model.setViewName("success");
               }
   		    }
   		   }
   		  }
   		  }
   		 }
        }
		}
		}
		}
		}
		return model;
	}
	
	@RequestMapping(value="/register/findUser",method=RequestMethod.POST)
	public void findUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
	
		  response.setContentType("text/html;charset-uft-8");
		  PrintWriter out = response.getWriter();
		  InputStream inputStream = request.getInputStream();
		  InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		  BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		  String str = null;
		  StringBuffer buffer = new StringBuffer();
		  while ((str = bufferedReader.readLine()) != null) {
			  buffer.append(str);
		  }
		  String name=buffer.toString();
		  boolean result = false;
		  Pattern pattern=Pattern.compile("^[a-zA-Z0-9_.]+$");
		  ICompany company=null;
		  if(pattern.matcher(name).matches()){
			  company=this.company.findUserByusername(name);  
			  if(company==null){
					 result=true;
		      }
		  }
		  else{
			 result=false;
		  }
		  bufferedReader.close();
		  inputStreamReader.close();
		  if(result){
		  out.write("true");
		  }
		  else{
	      out.write("false");  
		  }
		  out.flush();
		  out.close();
	}
	
}
