package org.andy.work.admin.controller.one;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.detail.ProductMessage;
import org.andy.work.admin.detail.TradeMessage;
import org.andy.work.admin.permission.OperationType;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.ICompanyhead;
import org.andy.work.appserver.model.IFindpassword;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Company;
import org.andy.work.appserver.model.impl.Findpassword;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.ICompanyheadMaintenanceService;
import org.andy.work.appserver.service.IFindpasswordMaintenanceService;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.IPurchaseMaintenanceService;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.secure.CompanyEncrypt;
import org.andy.work.secure.Encrypt;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CompanyController {
	
	private static Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	@Resource
	private ICompanyMaintenanceService company;
	
	@Autowired
	private ICompanyMaintenanceService companyHelper;
	
	@Resource
	private ICompanyheadMaintenanceService companyhead;
	
	@Resource 
	private IProductMaintenanceService commodity;
	
	@Resource 
	private IPurchaseMaintenanceService purchase;
	
	@Resource
	private IFindpasswordMaintenanceService findps;
	
	@RequestMapping(value="/CoLtd/{text}")
	@AuthOperation(roleType=RoleType.VIEW,operationType=OperationType.VIEW)
	public ModelAndView DetailCommodity(HttpServletRequest request,@PathVariable String text){
		
		CompanyEncrypt encry=new CompanyEncrypt();
		String number="";
		ModelAndView model=new ModelAndView();
		if(text!=null){
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
		Integer id=(Integer.valueOf(number));
		List<ICompany> companys=this.company.SearchCompany(id);
		ICompany companyss=companys.get(0);
		ICompanyhead companyheads=this.companyhead.getHead(companyss);
		request.setAttribute("name", companyss.getDisplayName());
		request.setAttribute("infomation", companyss.getInfomation());
		request.setAttribute("picone",companyheads.getHeadone());
		request.setAttribute("pictwo",companyheads.getHeadtwo());
		request.setAttribute("picthree",companyheads.getHeadthree());
		model.setViewName("tiles/particular/companyinfo");
		}
		else{
		model.setViewName("403");
		}
		}
		else{
		model.setViewName("403");
		}
		return model;
	}
	
	
	@RequestMapping(value="/CoLtd/edit")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView editMyself(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ICompany use=this.companyHelper.findUserByusername(admin.getUsername());
		ICompanyhead companyheads=this.companyhead.getHead(use);
		request.setAttribute("phone", use.getPhone());
		request.setAttribute("email", use.getEmail());
		request.setAttribute("infomation", use.getInfomation());
		request.setAttribute("com1", companyheads.getHeadone());
		request.setAttribute("com2", companyheads.getHeadtwo());
		request.setAttribute("com3", companyheads.getHeadthree());
		ModelAndView model=new ModelAndView();
		model.setViewName("tiles/own/editcompanyinfo");
		return model;
	}
	
	@RequestMapping(value="/CoLtd/edit/update")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView updateCompany(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView model=new ModelAndView();
		MultipartFile file1=null;
		MultipartFile file2=null;
		MultipartFile file3=null;
		if(request instanceof MultipartHttpServletRequest){
			file1=((MultipartHttpServletRequest) request).getFile("file1");
			file2=((MultipartHttpServletRequest) request).getFile("file2");
			file3=((MultipartHttpServletRequest) request).getFile("file3");
		}
		CompanyEncrypt encry=new CompanyEncrypt();
		ICompany user=this.companyHelper.findUserByusername(admin.getUsername());
		ICompanyhead userhead=this.companyhead.getHead(user);
		Pattern phonepattern=Pattern.compile("^[0-9]+$");
		Pattern emailpattern=Pattern.compile("^[a-zA-Z0-9.@]+$");
		Pattern infomationpattern=Pattern.compile("^[\u4e00-\u9fa5\\sa-zA-Z0-9_.。，,？?]+$");
		model.setViewName("403");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String infomation=request.getParameter("infomation");
		if(phone!=null && email!=null){
		if(phonepattern.matcher(phone).matches()){
		  if(emailpattern.matcher(email).matches()){
			 if(infomationpattern.matcher(infomation).matches()){
			     String picone=userhead.getHeadone();
			     String pictwo=userhead.getHeadtwo();
			     String picthree=userhead.getHeadthree();
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
			   String num=encry.Encrupt(user.getId());
			   if(suffix.equals("png")){
			   picone=num+"1"+"."+"jpg";
			   String path="G:\\project\\staticResources\\static\\companyhead\\"+picone;
			   DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
			   ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
			   }
			   else{
			   picone=num+"1"+"."+suffix;
			   String path="G:\\project\\staticResources\\static\\companyhead\\"+picone;
			   DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
			   ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
			   }
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
				String num=encry.Encrupt(user.getId());
				     if(suffix.equals("png")){
				     pictwo=num+"2"+"."+"jpg";
				     String path="G:\\project\\staticResources\\static\\companyhead\\"+pictwo;
				     DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
					 ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
				     }
				     else{
				     pictwo=num+"2"+"."+suffix;
				     String path="G:\\project\\staticResources\\static\\companyhead\\"+pictwo;
				     DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
					 ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
				     }
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
				String num=encry.Encrupt(user.getId());
				     if(suffix.equals("png")){
				     picthree=num+"3"+"."+"jpg";
				     String path="G:\\project\\staticResources\\static\\companyhead\\"+picthree;
					 DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
					 ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
				     }
				     else{
				     picthree=num+"3"+"."+suffix;
					 String path="G:\\project\\staticResources\\static\\companyhead\\"+picthree;
					 DataOutputStream outt = new DataOutputStream(new FileOutputStream(path));// 存放文件的绝对路径
					 ImageIO.write(image, "jpg", outt);//这是第二种图片写入方法
				     }
				}
				}
				catch(IOException e){
				      log.info("图片转换过程有问题");
			    }    	
		        }
                user.setEmail(email);
                user.setPhone(phone);
                user.setInfomation(infomation);
                this.companyHelper.updateUser(user);
                userhead.setHeadone(picone);
                userhead.setHeadtwo(pictwo);
                userhead.setHeadthree(picthree);
                this.companyhead.updateHead(userhead);
		        model.setViewName("success");  
			 }
		  }	
		}	
		}
		return model;
	}
	
	@RequestMapping(value="/CoLtd/commodity")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView companyCommodity(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ICompany company=this.company.findUserByusername(admin.getUsername());
		List<IProduct> product=this.commodity.searchUserPro(company.getId());
		GridData<ProductMessage> productgird=new GridData<ProductMessage>();
		PagingManagement pgm=PagingHelper.buildPagingManagement(request);
        List<ProductMessage> list=new ArrayList<ProductMessage>();
        List<ProductMessage> lists=new ArrayList<ProductMessage>();
        Encrypt encry=new Encrypt();
        ModelAndView model=new ModelAndView();
		if(product!=null){
        	for(IProduct products:product){
        		ProductMessage message=new ProductMessage();
        		String id=encry.Encrupt(products.getId());
        		message.setCompanyid(id);
        		message.setInfomation(products.getInfomation());
        		message.setName(products.getName());
        		message.setPicone(products.getPicone());
        		message.setPrice(products.getPrice());
        		message.setContact(products.getContact());
        		message.setContactphone(products.getContactphone());
        		list.add(message);
        	}
        }
		int y=pgm.getFirstResult();
		int z=list.size()-y;
		if(z>20){
		   for(int i=0;i<20;i++){
			ProductMessage productmessage=list.get(y);
			lists.add(productmessage);
			y++;
		   }
		}
		else if(z<=20&&z>=0){
		   for(int i=0;i<=z-1;i++){
			ProductMessage productmessage=list.get(y);
			lists.add(productmessage);
			y++;
		   }
		}
		else{
			if(list.size()<20){
			lists=list;
			}
		}
		productgird.setDatas(lists);
		pgm.setTotalRecord(list.size());
		productgird.setPgm(pgm);
		model.addObject("grid", productgird).setViewName("tiles/own/commoditypublish");
		return model;
	}
	
	@RequestMapping(value="/CoLtd/purchase")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView companyPurchase(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ICompany company=this.company.findUserByusername(admin.getUsername());
		List<IPurchase> purchase=this.purchase.searchUserPur(company.getId());
		GridData<ProductMessage> productgird=new GridData<ProductMessage>();
		PagingManagement pgm=PagingHelper.buildPagingManagement(request);
        List<ProductMessage> list=new ArrayList<ProductMessage>();
        List<ProductMessage> lists=new ArrayList<ProductMessage>();
        Encrypt encry=new Encrypt();
        ModelAndView model=new ModelAndView();
		if(purchase!=null){
        	for(IPurchase products:purchase){
        		ProductMessage message=new ProductMessage();
        		String id=encry.Encrupt(products.getId()*(-1));
        		message.setCompanyid(id);
        		message.setInfomation(products.getInfomation());
        		message.setName(products.getName());
        		message.setPicone(products.getPicone());
        		message.setPrice(products.getPrice());
        		message.setContact(products.getContact());
        		message.setContactphone(products.getContactphone());
        		list.add(message);
        	}
        }
		int y=pgm.getFirstResult();
		int z=list.size()-y;
		if(z>20){
		   for(int i=0;i<20;i++){
			ProductMessage productmessage=list.get(y);
			lists.add(productmessage);
			y++;
		   }
		}
		else if(z<=20&&z>=0){
		   for(int i=0;i<=z-1;i++){
			ProductMessage productmessage=list.get(y);
			lists.add(productmessage);
			y++;
		   }
		}
		else{
			if(list.size()<20){
			lists=list;
			}
		}
		productgird.setDatas(lists);
		pgm.setTotalRecord(list.size());
		productgird.setPgm(pgm);
		model.addObject("grid", productgird).setViewName("tiles/own/purchasepublish");
		return model;
	}
	
	@RequestMapping(value="/CoLtd/cash")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView showMoney(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ICompany use=this.companyHelper.findUserByusername(admin.getUsername());
		request.setAttribute("name", use.getDisplayName());
		request.setAttribute("phone", use.getPhone());
		request.setAttribute("cash", use.getMoney());
		ModelAndView model=new ModelAndView();
		model.setViewName("tiles/own/companybalance");
		return model;
	}
	
	@RequestMapping(value="/CoLtd/updatePasswd")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView updatePw(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ICompany use=this.companyHelper.findUserByusername(admin.getUsername());
		String pw0=request.getParameter("password");
		String pw=DigestUtils.md5Hex(pw0);
		String pw1=DigestUtils.md5Hex(pw);
		String pw2=DigestUtils.md5Hex(pw1);
		ModelAndView model=new ModelAndView();
		use.setPassword(pw2);
		this.company.updateUser(use);
		model.setViewName("success");
		return model;
	}
	
	@RequestMapping(value="/findPass")
	public ModelAndView forgetPw(HttpServletRequest request){
		
		ModelAndView model=new ModelAndView();
		model.setViewName("tiles/form/findpass");
		return model;
	}
	
}
