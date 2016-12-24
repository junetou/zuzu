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
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.detail.ProductMessage;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.OperationType;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Company;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.appserver.model.impl.Purchase;
import org.andy.work.appserver.model.impl.Transacationofusertoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertopurchase;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.andy.work.appserver.service.ITransacationofcompanytoproductMaintenanceService;
import org.andy.work.appserver.service.ITransacationofusertoproductMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.form.AddForm;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.secure.CompanyEncrypt;
import org.andy.work.secure.Encrypt;
import org.andy.work.utils.StringUtil;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommodityController {
	
	private static Logger log = LoggerFactory.getLogger(CommodityController.class);
	
	@Resource
	private ISolrMaintenanceService solr;
	
	@Resource 
	private IProductMaintenanceService commodity;
	
	@Autowired
	private ICompanyMaintenanceService companyHelper;
	
	@Resource
	private ITransacationofusertoproductMaintenanceService transacation;
	
	@Resource
	private UserHelper userHelper;
	
	@RequestMapping(value="/commodity")
	public ModelAndView Commodity(HttpServletRequest request,@RequestParam(required=false) String keyWord){
		
		GridData<ProductMessage> grid=new GridData<ProductMessage>();
		PagingManagement pgm= PagingHelper.buildPagingManagement(request);
		
		Pattern numberpattern=Pattern.compile("^[0-9]+([.]{0,1}[0-9]+){0,1}$");
		Pattern productpattern=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$");

		this.solr.ProductStartSolr();
	
		Map<String,Object> solrproduct=null;
		
		if(StringUtil.hasValue(keyWord))
		{
			if(numberpattern.matcher(keyWord).matches()){
				solrproduct=this.solr.keyworkProductSearch(keyWord, pgm);
			}
			else if(productpattern.matcher(keyWord).matches()){
				solrproduct=this.solr.keyworkProductSearch(keyWord, pgm);
			}
			else{
				solrproduct=this.solr.keyworkProductSearch("", pgm);
			}
		}
		else{
		solrproduct=this.solr.keyworkProductSearch(keyWord, pgm);
		}
		
		Encrypt encry=new Encrypt();
		CompanyEncrypt companyencry=new CompanyEncrypt();
		
		List<IProduct> list=(List<IProduct>)solrproduct.get("list");
		long num=(long)solrproduct.get("num");
		if(solrproduct!=null){
		List<IProduct> products=list;
		List<ProductMessage> datas=new ArrayList<ProductMessage>();
		for(IProduct product:products){
			ProductMessage data=new ProductMessage();
			ICompany company=product.getBelong();
			data.setNumber(encry.Encrupt(product.getId()));
			data.setCompanyid(companyencry.Encrupt(company.getId()));
			data.setCompanyname(company.getDisplayName());
			data.setAddr(product.getAddr());
			data.setName(product.getName());
			data.setInfomation(product.getInfomation());
			data.setPrice(product.getPrice());
			if(product.getPicone().equals("null")){
			data.setPicone("null.jpg");
			}
			else{
			data.setPicone(product.getPicone());
			}
			datas.add(data);
		}
		grid.setDatas(datas);
		}
		pgm.setTotalRecord(num);
		PagingHelper.setPaging(pgm,grid);
		this.solr.ProductRemoveSolr();
		ModelAndView model=new ModelAndView();
		model.addObject("grid", grid).setViewName("tiles/index/commodity");
		this.solr.ProductRemoveSolr();
		return model;
	}
	
	@RequestMapping(value="/commodity/{text}")
	@AuthOperation(roleType=RoleType.VIEW,operationType=OperationType.VIEW)
	public ModelAndView DetailCommodity(HttpServletRequest request,@PathVariable String text){
		
		Encrypt encry=new Encrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    IProduct commidity=this.commodity.search(Integer.valueOf(number));
	    ICompany company=commidity.getBelong();
	    request.setAttribute("name", commidity.getName());
	    request.setAttribute("price", commidity.getPrice());
	    request.setAttribute("infomation", commidity.getInfomation());
	    request.setAttribute("onepic", commidity.getPicone());
	    request.setAttribute("twopic", commidity.getPictwo());
	    request.setAttribute("threepic", commidity.getPicthree());
	    request.setAttribute("addr", commidity.getAddr());
	    request.setAttribute("company", company.getDisplayName());
		request.setAttribute("number", text);
		model.setViewName("tiles/particular/productinfo");
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
	
	@RequestMapping(value="/commodity/lineman")
	@AuthOperation(roleType=RoleType.USER,operationType=OperationType.VIEW)
	public ModelAndView showInfo(@RequestParam(required=false) String number,HttpServletRequest request){
	
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser user=this.userHelper.findUserByUsername(admin.getUsername());
		ModelAndView model=new ModelAndView();
		model.setViewName("403");
		Encrypt encry=new Encrypt();
		String numbers=encry.Deciphering(number);
		List<ITransacationofusertoproduct> trade=this.transacation.searchTrade(Integer.valueOf(numbers),user.getId());
		if(trade.size()==0){
		Double money=user.getMoney()-0.05;
		if(money<0){
			model.setViewName("errorbalance");
		}
		else{
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
		if(number!=null){
		Matcher matcher=patter.matcher(number);
		boolean rs=matcher.matches();
		if(rs){
		Integer id=Integer.valueOf(numbers);
		IProduct product=this.commodity.search(id);
		ICompany company=product.getBelong();
		request.setAttribute("number", number);
		request.setAttribute("contact", product.getContact());
		request.setAttribute("contactphone", product.getContactphone());
		request.setAttribute("email", company.getEmail());
		model.setViewName("tiles/particular/productcontact");
		user.setMoney(money);
		this.userHelper.saveUser(user);
		Transacationofusertoproduct newtrade=new Transacationofusertoproduct();
		newtrade.setCompanyid(company);
		Date time=new Date();
		SimpleDateFormat newtime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		newtrade.setCreattime(newtime.format(time));
		newtrade.setLasttime(newtime.format(time));
		newtrade.setMoney(0.05);
		newtrade.setProductid(product);
		newtrade.setSuccess(1);
		newtrade.setUserid(user);
		this.transacation.addTrade(newtrade);
		}
		else{
		model.setViewName("403");	
		}
		}
		else{
		model.setViewName("403");
		}
		}
		}else{
		Integer id=Integer.valueOf(numbers);
		IProduct product=this.commodity.search(id);
		ICompany company=product.getBelong();
		request.setAttribute("number", number);
		request.setAttribute("contact", product.getContact());
		request.setAttribute("contactphone", product.getContactphone());
		request.setAttribute("email", company.getEmail());
		model.setViewName("tiles/particular/productcontact");	
		}
		return model;
	}
	
	@RequestMapping(value="/commodity/update/{companyid}")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView updatePublish(HttpServletRequest request,@PathVariable String companyid){
		
		Encrypt encry=new Encrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
	    AddForm form=new AddForm();
		if(companyid!=null){
		Matcher matcher=patter.matcher(companyid);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(companyid);
	    IProduct commidity=this.commodity.search(Integer.valueOf(number));
	    ICompany company=commidity.getBelong();
	    form.setId(companyid);
	    form.setContact(commidity.getContact());
	    form.setPhone(commidity.getContactphone());
	    form.setInfomation(commidity.getInfomation());
	    form.setName(commidity.getName());
	    form.setPrice(commidity.getPrice());
	    form.setPicone(commidity.getPicone());
	    form.setPictwo(commidity.getPictwo());
	    form.setPicthree(commidity.getPicthree());
	    form.setAddr(commidity.getAddr());
		}
		else{
		model.setViewName("403");
		}
		}
		else{
		model.setViewName("403");
		}
		model.addObject("command", form);
		model.setViewName("tiles/form/updatecommodity");
		return model;
	}
	
	@RequestMapping(value="/commodity/update/update/{companyid}")
	@ResponseBody  
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView PublishInfo(@PathVariable String companyid,AddForm form,HttpServletResponse response,HttpServletRequest request){
		
		Encrypt encry=new Encrypt();
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ICompany user=this.companyHelper.findUserByusername(admin.getUsername());
		String number=encry.Deciphering(companyid);
	    IProduct pro=this.commodity.search(Integer.valueOf(number));
		
		MultipartFile file1=null;
		MultipartFile file2=null;
		MultipartFile file3=null;
		if(request instanceof MultipartHttpServletRequest){
			file1=((MultipartHttpServletRequest) request).getFile("file1");
			file2=((MultipartHttpServletRequest) request).getFile("file2");
			file3=((MultipartHttpServletRequest) request).getFile("file3");
		}
		Pattern namepattern=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$");
		Pattern pricepattern=Pattern.compile("^[0-9.]+$");
		Pattern contactpattern=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9_.]+$");
		Pattern phonepattern=Pattern.compile("^[0-9]+$");
		Pattern infomationpattern=Pattern.compile("^[\u4e00-\u9fa5\\sa-zA-Z0-9_.。，,？?]+$");
		Pattern addrpattern=Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9_.]+$");
		String name=form.getName();
		String price=String.valueOf(form.getPrice());
		String contact=form.getContact();
		String contactphone=form.getPhone();
		String infomation=form.getInfomation();
		String addr=form.getAddr();
	    ModelAndView model=new ModelAndView();
	    if(name!=null){
	    if(pro.getBelong().getId().equals(user.getId())){
		if(namepattern.matcher(name).matches()){
		 if(pricepattern.matcher(price).matches()){
		  if(contactpattern.matcher(contact).matches()){
		   if(phonepattern.matcher(contactphone).matches()){
			 if(infomationpattern.matcher(infomation).matches()){
              if(addrpattern.matcher(addr).matches()){
 			     String picone=pro.getPicone();
 			     String pictwo=pro.getPictwo();
 			     String picthree=pro.getPicthree();
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
                  if(picone.equals("null")){
                	  String pictureOneName=file1.getOriginalFilename();//获取上传图片名字
				      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
                	  Random random=new Random();
                	  String num=encry.Encrupt(user.getId());
				      picone=String.valueOf(random.nextInt(10000000))+num+"1"+"."+suffix;;
                  }
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
	                  if(pictwo.equals("null")){
	                	  String pictureOneName=file2.getOriginalFilename();//获取上传图片名字
					      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
	                	  Random random=new Random();
	                	  String num=encry.Encrupt(user.getId());
					      pictwo=String.valueOf(random.nextInt(10000000))+num+"2"+"."+suffix;;
	                  }
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
	                  if(picthree.equals("null")){
	                	  String pictureOneName=file3.getOriginalFilename();//获取上传图片名字
					      String suffix=StringUtils.substringAfterLast(pictureOneName, ".");//获取后缀
	                	  Random random=new Random();
	                	  String num=encry.Encrupt(user.getId());
					      picthree=String.valueOf(random.nextInt(10000000))+num+"3"+"."+suffix;;
	                  }
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
		        pro.setBelong(user);
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
		        this.commodity.updatePro((Product)pro);
		        model.setViewName("success");
              }
             }
		    }
		   }
		  }
		}
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
	
	
	@RequestMapping(value="/commodity/update/delete")
	@AuthOperation(roleType=RoleType.COMPANY,operationType=OperationType.VIEW)
	public ModelAndView deletePublish(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ICompany user=this.companyHelper.findUserByusername(admin.getUsername());
		Encrypt encry=new Encrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
	    AddForm form=new AddForm();
	    String companyid=request.getParameter("companyid");
		if(companyid!=null){
		Matcher matcher=patter.matcher(companyid);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(companyid);
	    IProduct commidity=this.commodity.search(Integer.valueOf(number));
	      if(commidity.getBelong().getId().equals(user.getId())){
	    	  commidity.setShelve(0);
	    	  this.commodity.updatePro((Product)commidity);
	    	  List<ITransacationofusertoproduct> itrand=this.transacation.updateTrade(commidity.getId());
	    	  for(ITransacationofusertoproduct trands:itrand){
	    		  ITransacationofusertoproduct trandss=trands;
	    		  trandss.setSuccess(0);
	    		  this.transacation.updateTrade((Transacationofusertoproduct)trandss);
	    	  }
	    	  model.setViewName("success");
	      }
	      else{
	    	  model.setViewName("403");
	      }
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
	
}
