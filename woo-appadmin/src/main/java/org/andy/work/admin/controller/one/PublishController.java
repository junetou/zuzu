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
import java.util.Random;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.helper.PhoneCodeCookie;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.OperationType;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Company;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.appserver.model.impl.Purchase;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.IPurchaseMaintenanceService;
import org.andy.work.appserver.service.IUserMaintenanceService;
import org.andy.work.form.AddForm;
import org.andy.work.secure.CompanyEncrypt;
import org.andy.work.secure.Encrypt;
import org.andy.work.utils.AjaxResponse;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublishController {

	private static Logger log = LoggerFactory.getLogger(PublishController.class);
	
	@Resource
	private IProductMaintenanceService product; 
	
	@Resource
	private IPurchaseMaintenanceService purchase; 
	
	@Autowired
	private ICompanyMaintenanceService userHelper;
	
	@Autowired 
	private PhoneCodeCookie phonecodecookie;
	
	@RequestMapping(value="/publish")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView Publish(){
		
		AddForm form=new AddForm();
		ModelAndView model=new ModelAndView();
		model.addObject("command", form);
		model.setViewName("tiles/form/publish");
		return model;
		
	}
	
	@RequestMapping(value="/publish/add")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView PublishInfo(AddForm form,HttpServletResponse response,HttpServletRequest request){
		
		MultipartFile file1=null;
		MultipartFile file2=null;
		MultipartFile file3=null;
		if(request instanceof MultipartHttpServletRequest){
			file1=((MultipartHttpServletRequest) request).getFile("file1");
			file2=((MultipartHttpServletRequest) request).getFile("file2");
			file3=((MultipartHttpServletRequest) request).getFile("file3");
		}
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ICompany user=this.userHelper.findUserByusername(admin.getUsername());
		Pattern namepattern=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$");
		Pattern pricepattern=Pattern.compile("^[0-9.]+$");
		Pattern contactpattern=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9_.]+$");
		Pattern phonepattern=Pattern.compile("^[0-9]+$");
		Pattern infomationpattern=Pattern.compile("^[\u4e00-\u9fa5\\sa-zA-Z0-9_.。，,？?]+$");
		Pattern addrpattern=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9_.]+$");
		String name=request.getParameter("name");
		String price=request.getParameter("price");
		String contact=request.getParameter("contact");
		String contactphone=request.getParameter("contactphone");
		String infomation=request.getParameter("textarea");
		String addr=request.getParameter("addr");
		String select=request.getParameter("group");
	    Product pro=new Product();
	    Purchase pur=new Purchase();
	    ModelAndView model=new ModelAndView();
	    model.setViewName("403");
	    CompanyEncrypt encry=new CompanyEncrypt();
		Double money=user.getMoney()-0.05;
		if(select!=null){
		if(money>0){
		if(select.equals("0")){
		if(namepattern.matcher(name).matches()){
		 if(pricepattern.matcher(price).matches()){
		  if(contactpattern.matcher(contact).matches()){
		   if(phonepattern.matcher(contactphone).matches()){
			 if(infomationpattern.matcher(infomation).matches()){
              if(addrpattern.matcher(addr).matches()){
 			     String picone="null";
 			     String pictwo="null";
 			     String picthree="null";
            	 if(file1!=null){
			     //检测上传的文件
			     CommonsMultipartFile checkfile= (CommonsMultipartFile)file1;
			     DiskFileItem fi=(DiskFileItem)checkfile.getFileItem();
			     File f=fi.getStoreLocation();
			     try{
		         BufferedImage bi = ImageIO.read(f);//读取文件是否输入图片类型
			     if(bi == null){
			     //如何不是图片类型
                  log.info("上传的不是图片,嫌疑Ip:"+request.getRemoteAddr());
			      }
			     else{//正式加载到数据库
                  int newwidth=600;
                  int newheight=335;
                  BufferedImage image=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
                  Graphics graphics = image.createGraphics();
                  graphics.drawImage(bi, 0, 0, newwidth, newheight, null);
			      String pictureOneName=file1.getOriginalFilename();//获取上传图片名字
			      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
			      Random random=new Random();
			      String num=encry.Encrupt(user.getId());
			      picone=String.valueOf(random.nextInt(10000000))+num+"1"+"."+suffix;
			      //File transerto1=new File("G:\\project\\staticResources\\static\\commoditypicture\\"+picone+"."+suffix);
			      //file1.transferTo(transerto1);
			      String path="G:\\project\\staticResources\\static\\commoditypicture\\"+picone;
			      DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
			      ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
			     }
		         }
			     catch(IOException e){
				 log.info("图片转换过程有问题");
			     }
		         }
		        if(file2!=null){
		          CommonsMultipartFile checkfile2= (CommonsMultipartFile)file2;
				  DiskFileItem fi=(DiskFileItem)checkfile2.getFileItem();
				  File f2=fi.getStoreLocation();
				  try{
			      BufferedImage bi = ImageIO.read(f2);//读取文件是否输入图片类型
				  if(bi == null){
				  //如何不是图片类型
	              log.info("上传的不是图片,嫌疑Ip:"+request.getRemoteAddr());
				  }
				  else{//正式加载到数据库
	                  int newwidth=600;
	                  int newheight=335;
	                  BufferedImage image=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
	                  Graphics graphics = image.createGraphics();
	                  graphics.drawImage(bi, 0, 0, newwidth, newheight, null);
				      String pictureOneName=file2.getOriginalFilename();//获取上传图片名字
				      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
				      Random random=new Random();
				      String num=encry.Encrupt(user.getId());
				      pictwo=String.valueOf(random.nextInt(10000000))+num+"2"+"."+suffix;;
				      String path="G:\\project\\staticResources\\static\\commoditypicture\\"+pictwo;
				      DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
				      ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
				  }
			      }
				  catch(IOException e){
			      log.info("图片转换过程有问题");
				  }
		          }
		        if(file3!=null){
		          CommonsMultipartFile checkfile3= (CommonsMultipartFile)file3;
				  DiskFileItem fi=(DiskFileItem)checkfile3.getFileItem();
				  File f3=fi.getStoreLocation();
				  try{
				  BufferedImage bi = ImageIO.read(f3);//读取文件是否输入图片类型
				  if(bi == null){
				  //如何不是图片类型
		          log.info("上传的不是图片,嫌疑Ip:"+request.getRemoteAddr());
				  }
				  else{//正式加载到数据库
					  int newwidth=600;
	                  int newheight=335;
	                  BufferedImage image=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
	                  Graphics graphics = image.createGraphics();
	                  graphics.drawImage(bi, 0, 0, newwidth, newheight, null);
				      String pictureOneName=file3.getOriginalFilename();//获取上传图片名字
				      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
				      Random random=new Random();
				      String num=encry.Encrupt(user.getId());
				      picthree=String.valueOf(random.nextInt(10000000))+num+"3"+"."+suffix;;
				      String path="G:\\project\\staticResources\\static\\commoditypicture\\"+picthree;
				      DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
				      ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
				  }
				  }
				  catch(IOException e){
				      log.info("图片转换过程有问题");
					  }    	
		          }
		        pro.setAddr(addr);
		        pro.setContact(contact);
		        pro.setContactphone(contactphone);
		        ICompany company=new Company();
		        company.setId(user.getId());
		        pro.setBelong(company);
		        pro.setName(name);
		        pro.setInfomation(infomation);
		        pro.setPrice(Double.valueOf(price));
		        pro.setShelve(1);
		        Date time=new Date();
		        SimpleDateFormat times=new SimpleDateFormat("yyyy MM dd HH mm ss"); 
		        pro.setCreattime(times.format(time));
		        pro.setPicone(picone);
		        pro.setPictwo(pictwo);
		        pro.setPicthree(picthree);
		        this.product.addPro(pro);
		        user.setMoney(money);
		        this.userHelper.updateUser(user);
		        model.setViewName("success");
              }
             }
		    }
		   }
		  }
		}
		}
		if(select.equals("1")){
		if(namepattern.matcher(name).matches()){
		 if(pricepattern.matcher(price).matches()){
		  if(contactpattern.matcher(contact).matches()){
		   if(phonepattern.matcher(contactphone).matches()){
			 if(infomationpattern.matcher(infomation).matches()){
 			     String picone="null";
 			     String pictwo="null";
 			     String picthree="null";
				 if(!file1.isEmpty()){
				     //检测上传的文件
				     CommonsMultipartFile checkfile= (CommonsMultipartFile)file1;
				     DiskFileItem fi=(DiskFileItem)checkfile.getFileItem();
				     File f=fi.getStoreLocation();
				     try{
			         BufferedImage bi = ImageIO.read(f);//读取文件是否输入图片类型
				     if(bi == null){
				     //如何不是图片类型
	                  log.info("上传的不是图片,嫌疑Ip:"+request.getRemoteAddr());
				      }
				     else{//正式加载到数据库
				    	  int newwidth=600;
		                  int newheight=335;
		                  BufferedImage image=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
		                  Graphics graphics = image.createGraphics();
		                  graphics.drawImage(bi, 0, 0, newwidth, newheight, null);
					      String pictureOneName=file1.getOriginalFilename();//获取上传图片名字
					      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
					      Random random=new Random();
					      String num=encry.Encrupt(user.getId());
					      picone=String.valueOf(random.nextInt(10000000))+num+"1"+"."+suffix;
					      String path="G:\\project\\staticResources\\static\\purchasepicture\\"+picone;
					      DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
					      ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
				     }
			         }
				     catch(IOException e){
					 log.info("图片转换过程有问题");
				     }
			         }
			        if(!file2.isEmpty()){
			          CommonsMultipartFile checkfile2= (CommonsMultipartFile)file2;
					  DiskFileItem fi=(DiskFileItem)checkfile2.getFileItem();
					  File f2=fi.getStoreLocation();
					  try{
				      BufferedImage bi = ImageIO.read(f2);//读取文件是否输入图片类型
					  if(bi == null){
					  //如何不是图片类型
		              log.info("上传的不是图片,嫌疑Ip:"+request.getRemoteAddr());
					  }
					  else{//正式加载到数据库
				    	  int newwidth=600;
		                  int newheight=335;
		                  BufferedImage image=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
		                  Graphics graphics = image.createGraphics();
		                  graphics.drawImage(bi, 0, 0, newwidth, newheight, null);
					      String pictureOneName=file2.getOriginalFilename();//获取上传图片名字
					      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
					      Random random=new Random();
					      String num=encry.Encrupt(user.getId());
					      pictwo=String.valueOf(random.nextInt(10000000))+num+"2"+"."+suffix;;
					      String path="G:\\project\\staticResources\\static\\purchasepicture\\"+pictwo;
					      DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
					      ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
					  }
				      }
					  catch(IOException e){
				      log.info("图片转换过程有问题");
					  }
			          }
			        if(!file3.isEmpty()){
				
			          CommonsMultipartFile checkfile3= (CommonsMultipartFile)file3;
					  DiskFileItem fi=(DiskFileItem)checkfile3.getFileItem();
					  File f3=fi.getStoreLocation();
					  try{
					  BufferedImage bi = ImageIO.read(f3);//读取文件是否输入图片类型
					  if(bi == null){
					  //如何不是图片类型
			          log.info("上传的不是图片,嫌疑Ip:"+request.getRemoteAddr());
					  }
					  else{//正式加载到数据库
				    	  int newwidth=600;
		                  int newheight=335;
		                  BufferedImage image=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
		                  Graphics graphics = image.createGraphics();
		                  graphics.drawImage(bi, 0, 0, newwidth, newheight, null);
					      String pictureOneName=file3.getOriginalFilename();//获取上传图片名字
					      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
					      Random random=new Random();
					      String num=encry.Encrupt(user.getId());
					      picthree=String.valueOf(random.nextInt(10000000))+num+"3"+"."+suffix;;
					      String path="G:\\project\\staticResources\\static\\purchasepicture\\"+picthree;
					      DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
					      ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
					  }
					  }
						  catch(IOException e){
					      log.info("图片转换过程有问题");
						  }    	
			          }
			        pur.setAddr(addr);
			        pur.setContact(contact);
			        pur.setContactphone(contactphone);
			        ICompany company=new Company();
			        company.setId(user.getId());
			        pur.setBelong(company);
			        pur.setName(name);
			        pur.setInfomation(infomation);
			        pur.setPrice(Double.valueOf(price));
			        pur.setShelve(1);
			        Date time=new Date();
			        SimpleDateFormat times=new SimpleDateFormat("yyyy MM dd HH mm ss"); 
			        pur.setCreattime(times.format(time));
			        pur.setPicone(picone);
			        pur.setPictwo(pictwo);
			        pur.setPicthree(picthree);
			        this.purchase.addPur(pur);
			        user.setMoney(money);
			        this.userHelper.updateUser(user);
			        model.setViewName("success");
			 }
		    }
		   }
		  }
		}
		}
		}
		else{
			model.setViewName("errorbalance");
		}
		}
		return model;
	}
	
	
	@RequestMapping(value="/publish/checkCode",method=RequestMethod.POST)
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
	  this.phonecodecookie.setUseCode(request,buffer.toString());
      boolean judge=false;
      if(this.phonecodecookie.isVerify(request)){
    	  judge=true;
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
	
}
