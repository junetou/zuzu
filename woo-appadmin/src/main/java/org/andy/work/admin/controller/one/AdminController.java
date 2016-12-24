package org.andy.work.admin.controller.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.detail.CompanyMessage;
import org.andy.work.admin.detail.ProductMessage;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.OperationType;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.ICompanyhead;
import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.IFindpassword;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.ITransacationofusertoproduct;
import org.andy.work.appserver.model.ITransacationofusertopurchase;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Companyhead;
import org.andy.work.appserver.model.impl.Fed;
import org.andy.work.appserver.model.impl.Findpassword;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.appserver.model.impl.Purchase;
import org.andy.work.appserver.model.impl.Transacationofusertoproduct;
import org.andy.work.appserver.model.impl.Transacationofusertopurchase;
import org.andy.work.appserver.service.ICompanyMaintenanceService;
import org.andy.work.appserver.service.ICompanyheadMaintenanceService;
import org.andy.work.appserver.service.IFedMaintenanceService;
import org.andy.work.appserver.service.IFindpasswordMaintenanceService;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.IPurchaseMaintenanceService;
import org.andy.work.appserver.service.ITransacationofusertoproductMaintenanceService;
import org.andy.work.appserver.service.ITransacationofusertopurchaseMaintenanceService;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.secure.CompanyEncrypt;
import org.andy.work.secure.Encrypt;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {

	@Resource
	private ICompanyMaintenanceService company;
	
	@Resource
	private IProductMaintenanceService product; 
	
	@Resource
	private IPurchaseMaintenanceService purchase; 
	
	@Resource
	private IFedMaintenanceService fed;
	
	@Resource
	private ITransacationofusertoproductMaintenanceService transacation;
	
	@Resource
	private ITransacationofusertopurchaseMaintenanceService purtransacation;
	
	@Resource
	private UserHelper userHelper;
	
	@Resource
	private IFindpasswordMaintenanceService findpw;
	
	@Autowired
	private ICompanyheadMaintenanceService companyhead;
	
	@RequestMapping(value="/admin/auth")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView showCompany(HttpServletRequest request){
		
		ModelAndView model=new ModelAndView();
		GridData<CompanyMessage> productgird=new GridData<CompanyMessage>();
		PagingManagement pgm=PagingHelper.buildPagingManagement(request);
		List<ICompany> companys=this.company.findRegisterUsr();
		List<CompanyMessage> comMessage=new ArrayList<CompanyMessage>();
		List<CompanyMessage> comMessages=new ArrayList<CompanyMessage>();
		CompanyEncrypt encry=new CompanyEncrypt();
		if(companys.size()>0){
			for(ICompany companyss:companys){
				CompanyMessage com=new CompanyMessage();
				com.setAddr(companyss.getAddr());
				com.setContact(companyss.getContact());
				com.setPic(companyss.getHead());
				com.setName(companyss.getDisplayName());
				com.setContactphone(companyss.getPhone());
				com.setEmail(companyss.getEmail());
				String id=encry.Encrupt(companyss.getId());
				com.setId(id);
				comMessage.add(com);
			}
		}
		int y=pgm.getFirstResult();//20,40,60,120
		int z=companys.size()-y;//-20
		if(z>20){
		   for(int i=0;i<20;i++){
			CompanyMessage productmessage=comMessage.get(y);
			comMessages.add(productmessage);
			y++;
		   }
		}
		else if(z<=20&&z>=0){
		   for(int i=0;i<=z-1;i++){
			 CompanyMessage productmessage=comMessage.get(y);
		     comMessages.add(productmessage);
			 y++;
		   }
		}
		else{
			if(comMessage.size()<20){
			comMessages=comMessage;
			}
		}
		productgird.setDatas(comMessages);
		pgm.setTotalRecord(comMessages.size());
		productgird.setPgm(pgm);
		model.addObject("grid", productgird).setViewName("tiles/own/adminregister");
		return model;
	}
	
	@RequestMapping(value="/admin/update={text}")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView UpdateCompany(HttpServletRequest request,@PathVariable String text){
		
		CompanyEncrypt encry=new CompanyEncrypt();
		SendEmailController email=new SendEmailController();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    Integer id=Integer.valueOf(number);
	    List<ICompany> companys=this.company.SearchCompany(id);
	    ICompany companyss=companys.get(0);
	    companyss.setLocked("n");
	    Double money=1.00;
	    companyss.setMoney(money);
	    this.company.updateUser(companyss);
	    email.sendRegister(companyss.getEmail(),"已通过审核，我们已赠送1元到你的账号中，再次感谢贵公司对本网站的支持！");
	    ICompanyhead heads=new Companyhead();
	    heads.setCompanyid(companyss);
	    heads.setHeadone("null");
	    heads.setHeadtwo("null");
	    heads.setHeadthree("null");
	    this.companyhead.saveHead(heads);
	    model.setViewName("success");
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
	
	
	@RequestMapping(value="/admin/delete={text}")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView DeleteCompany(HttpServletRequest request,@PathVariable String text){
		
		CompanyEncrypt encry=new CompanyEncrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
	    SendEmailController email=new SendEmailController();
	    model.setViewName("403");
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    Integer id=Integer.valueOf(number);
	    List<ICompany> companys=this.company.SearchCompany(id);
	    ICompany companyss=companys.get(0);
	    email.sendRegister(companyss.getEmail(),"未通过审核，可能是没提交工商营业照，也可能是其他原因，具体情况请联系客服！再次感谢贵公司对本产品的支持");
	    this.company.deleteUser(companyss);
	    model.setViewName("success");
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
	
	@RequestMapping(value="/commodity/addFed")
	@AuthOperation(roleType=RoleType.USER,operationType=OperationType.VIEW)
	public ModelAndView addProductFed(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    IUser user=this.userHelper.findUserByUsername(admin.getUsername());
		String fedinfomation=request.getParameter("infomation");
		Pattern pattern=Pattern.compile("^[\u4e00-\u9fa5\\sa-zA-Z0-9_.。，,？?]+$");
	    ModelAndView model=new ModelAndView();
	    model.setViewName("403");
	    Encrypt encry=new Encrypt();
	    String text=request.getParameter("userId");
	    if(pattern.matcher(fedinfomation).matches()){
	    	IFed fed=new Fed();
	    	fed.setInfomation(fedinfomation);
	    	fed.setUserid(user);
	    	String pw=encry.Deciphering(text);
	    	IProduct product=this.product.search(Integer.valueOf(pw));
	    	fed.setProductid(Integer.valueOf(pw));
	    	fed.setBelong(0);
	    	fed.setShalve(1);
	    	fed.setCompanyid(product.getBelong());
	    	this.fed.addFed(fed);
	    	model.setViewName("success");
	    }
		return model;
	}
	
	@RequestMapping(value="/purchase/addFed")
	@AuthOperation(roleType=RoleType.USER,operationType=OperationType.VIEW)
	public ModelAndView addPurchaseFed(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    IUser user=this.userHelper.findUserByUsername(admin.getUsername());
		String fedinfomation=request.getParameter("infomation");
		Pattern pattern=Pattern.compile("^[\u4e00-\u9fa5\\sa-zA-Z0-9_.。，,？?]+$");
	    ModelAndView model=new ModelAndView();
	    model.setViewName("403");
	    String text=request.getParameter("userId");
	    Encrypt encry=new Encrypt();
		String numbers=encry.Deciphering(text);
	    if(pattern.matcher(fedinfomation).matches()){
	    	IFed fed=new Fed();
	    	fed.setInfomation(fedinfomation);
	    	fed.setUserid(user);
	    	IPurchase purchases=this.purchase.search((Integer.valueOf(numbers)*(-1)));
	    	fed.setProductid(purchases.getId());
	    	fed.setBelong(1);
	    	fed.setShalve(1);
	    	fed.setCompanyid(purchases.getBelong());
	    	this.fed.addFed(fed);
	    	model.setViewName("success");
	    }
		return model;
	}
	
	@RequestMapping(value="/admin/show/fed")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView showFed(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    IUser user=this.userHelper.findUserByUsername(admin.getUsername());
	    ModelAndView model=new ModelAndView();
		GridData<ProductMessage> productgird=new GridData<ProductMessage>();
		PagingManagement pgm=PagingHelper.buildPagingManagement(request);
		List<IFed> feds=this.fed.searchFed();
		List<ProductMessage> proMessage=new ArrayList<ProductMessage>();
		List<ProductMessage> proMessages=new ArrayList<ProductMessage>();
		Encrypt encry=new Encrypt();
		if(feds.size()>0){
			for(IFed fedss:feds){
				ProductMessage pro=new ProductMessage();
				pro.setBelong(fedss.getCompanyid());
				if(fedss.getBelong()==0){
					IProduct pros=this.product.search(fedss.getProductid());
					if(pros!=null){
					pro.setAddr(pros.getAddr());
					pro.setName(pros.getName());
					pro.setContact(pros.getContact());
					pro.setContactphone(pros.getContactphone());
					pro.setInfomation(pros.getInfomation());
					pro.setPicone(pros.getPicone());
					pro.setCreattime(fedss.getInfomation());//举报信息
					String ency=encry.Encrupt(pros.getId());
					pro.setNumber(ency);
                    pro.setShelve(1);
					}
				}
				else{
					IPurchase pros=this.purchase.search(fedss.getProductid());
					if(pros!=null){
					pro.setAddr(pros.getAddr());
					pro.setName(pros.getName());
					pro.setContact(pros.getContact());
					pro.setContactphone(pros.getContactphone());
					pro.setInfomation(pros.getInfomation());
					pro.setPicone(pros.getPicone());
					pro.setCreattime(fedss.getInfomation());//举报信息
					String ency=encry.Encrupt(pros.getId()*(-1));
					pro.setNumber(ency);
                    pro.setShelve(2);	
					}
				}
				proMessage.add(pro);
			}
		}
		int y=pgm.getFirstResult();//20,40
		int z=proMessage.size()-y;
		if(z>20){
		   for(int i=0;i<20;i++){
			ProductMessage productmessage=proMessage.get(y);
			proMessages.add(productmessage);
			y++;
		   }
		}
		else if(z<=20&&z>=0){
		   for(int i=0;i<=z-1;i++){
			   ProductMessage productmessage=proMessage.get(y);
			   proMessages.add(productmessage);
			   y++;
		   }
		}
		else{
			if(proMessage.size()<20){
			proMessages=proMessage;}
		}
		productgird.setDatas(proMessages);
		pgm.setTotalRecord(proMessages.size());
		productgird.setPgm(pgm);
		model.addObject("grid", productgird).setViewName("tiles/own/showfed");
		return model;
	}
	
	@RequestMapping(value="/admin/commodity/delete/{text}")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView DeleteCommodity(HttpServletRequest request,@PathVariable String text){
		
		Encrypt encry=new Encrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    List<IFed> feds=this.fed.searchProFed(Integer.valueOf(number));
	    for(IFed fedss:feds){
	    fedss.setShalve(0);
	    this.fed.updateFed(fedss);
	    }
	    IProduct commodity=this.product.search(Integer.valueOf(number));
	    commodity.setShelve(0);
	    this.product.updatePro((Product)commodity);
  	    List<ITransacationofusertoproduct> itrand=this.transacation.updateTrade(commodity.getId());
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
		return model;
	}
	
	@RequestMapping(value="/admin/purchase/delete/{text}")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView DeletePurchase(HttpServletRequest request,@PathVariable String text){
		
		Encrypt encry=new Encrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    List<IFed> feds=this.fed.searchPurFed(Integer.valueOf(number)*(-1));
	    for(IFed fedss:feds){
	    fedss.setShalve(0);
	    this.fed.updateFed(fedss);
	    }
	    IPurchase purchase=this.purchase.search(Integer.valueOf(number)*(-1));
	    purchase.setShelve(0);
  	    List<ITransacationofusertopurchase> itrand=this.purtransacation.updateTrade(purchase.getId());
  	    for(ITransacationofusertopurchase trands:itrand){
  		  ITransacationofusertopurchase trandss=trands;
  		  trandss.setSuccess(0);
  		  this.purtransacation.updateTrade((Transacationofusertopurchase)trandss);
  	     }
	    this.purchase.updatePur((Purchase)purchase);
		model.setViewName("success");
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
	
	@RequestMapping(value="/admin/commodity/ignore/{text}")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView ignoreCommodity(HttpServletRequest request,@PathVariable String text){
		
		Encrypt encry=new Encrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    List<IFed> feds=this.fed.searchProFed(Integer.valueOf(number));
	    for(IFed fedss:feds){
	    fedss.setShalve(0);
	    this.fed.updateFed(fedss);
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
		return model;
	}
	
	@RequestMapping(value="/admin/purchase/ignore/{text}")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView ignorePurchase(HttpServletRequest request,@PathVariable String text){
		
		Encrypt encry=new Encrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    List<IFed> feds=this.fed.searchPurFed(Integer.valueOf(number)*(-1));
	    for(IFed fedss:feds){
	    fedss.setShalve(0);
	    this.fed.updateFed(fedss);
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
		return model;
	}
	
	@RequestMapping(value="/findPass/check")
	public ModelAndView forgetPw(HttpServletRequest request){
		
		ModelAndView model=new ModelAndView();
		model.setViewName("success");;
		Pattern usernamepattern=Pattern.compile("^[a-zA-Z0-9_.]+$");
		Pattern namepattern=Pattern.compile("^[\u4e00-\u9fa5\\sa-zA-Z0-9_.]+$");
		Pattern contactphonepattern=Pattern.compile("^[0-9]+$");
		Pattern monthpattern=Pattern.compile("^[0-9]+$");
		Pattern daypattern=Pattern.compile("^[0-9]+$");
		String usrname=request.getParameter("username");
		String name=request.getParameter("name");
		String contactphone=request.getParameter("contactphone");
		String months=request.getParameter("createtimemonth");
		String days=request.getParameter("createtimeday");
		if(usrname!=null){
			if(usernamepattern.matcher(usrname).matches()){
				if(namepattern.matcher(name).matches()){
					if(contactphonepattern.matcher(contactphone).matches()){
					 if(monthpattern.matcher(months).matches()){
					  if(daypattern.matcher(days).matches()){	 
						ICompany company=this.company.findUserByusername(usrname);
						if(company==null){
							model.setViewName("success");
						}
						else{
						    String comusrname=company.getCompanyname();
						    String comconname=company.getContact();
						    String comphone=company.getPhone();
							if(comusrname.equals(usrname) && comconname.equals(name) && comphone.equals(contactphone)){
								String time=company.getCreatetime();
								char month1=time.charAt(5);
								char month2=time.charAt(6);
								StringBuffer month=new StringBuffer();
								month.append(month1);
								month.append(month2);
							    char day1=time.charAt(8);
							    char day2=time.charAt(9);
							    StringBuffer day=new StringBuffer();
							    day.append(day1);
							    day.append(day2);
							    if(month.toString().equals(months)){
							     if(day.toString().equals(days)){
							      IFindpassword findspa=new Findpassword();
							      findspa.setCompanyid(company);
							      findspa.setShelve(1);
							      this.findpw.saveObject(findspa);
							       }
							    }  
							    model.setViewName("success");
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
	
	@RequestMapping(value="/admin/findpasswd/show")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView showFindPassWord(HttpServletRequest request){
		
		AdminUserDetails admin=(AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    IUser user=this.userHelper.findUserByUsername(admin.getUsername());
	    ModelAndView model=new ModelAndView();
		GridData<CompanyMessage> productgird=new GridData<CompanyMessage>();
		PagingManagement pgm=PagingHelper.buildPagingManagement(request);
		List<IFindpassword> feds=this.findpw.searchAll();
		List<CompanyMessage> proMessage=new ArrayList<CompanyMessage>();
		List<CompanyMessage> proMessages=new ArrayList<CompanyMessage>();
		CompanyEncrypt encry=new CompanyEncrypt();
		if(feds.size()>0){
			for(IFindpassword fedss:feds){
				CompanyMessage pro=new CompanyMessage();
				ICompany com=fedss.getCompanyid();
				pro.setName(com.getDisplayName());
				pro.setContact(com.getContact());
				pro.setContactphone(com.getPhone());
				String id=encry.Encrupt(com.getId()*(-1));
				pro.setId(id);
				proMessage.add(pro);
			}
		}
		int y=pgm.getFirstResult();//20,40
		int z=proMessage.size()-y;
		if(z>20){
		   for(int i=0;i<20;i++){
			CompanyMessage productmessage=proMessage.get(y);
			proMessages.add(productmessage);
			y++;
		   }
		}
		else if(z<=20&&z>=0){
		   for(int i=0;i<=z-1;i++){
			CompanyMessage productmessage=proMessage.get(y);
			proMessages.add(productmessage);
			y++;
		   }
		}
		else{
			if(proMessage.size()<20){
			proMessages=proMessage;}
		}
		productgird.setDatas(proMessages);
		pgm.setTotalRecord(proMessages.size());
		productgird.setPgm(pgm);
		model.addObject("grid", productgird).setViewName("tiles/own/showfindpw");
		return model;
	}
	
	
	@RequestMapping(value="/admin/findpasswd/sendEmail/{text}")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView findPasswdSendEmail(HttpServletRequest request,@PathVariable String text){
		
		CompanyEncrypt encry=new CompanyEncrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
	    model.setViewName("403");
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    List<IFindpassword> feds=this.findpw.searchUser(Integer.valueOf(number)*(-1));
	    for(IFindpassword fedss:feds){
	        fedss.setShelve(0);;
	        this.findpw.updateObject(fedss);
	    }
	    if(feds!=null){
		    Random random=new Random();
		    String one=String.valueOf(random.nextInt(10));
			String two=String.valueOf(random.nextInt(10));
			String three=String.valueOf(random.nextInt(10));
			String four=String.valueOf(random.nextInt(10));
			String five=String.valueOf(random.nextInt(10));
			String six=String.valueOf(random.nextInt(10));
			String seven=String.valueOf(random.nextInt(10));
			String eight=String.valueOf(random.nextInt(10));
			String night=String.valueOf(random.nextInt(10));
			String ten=String.valueOf(random.nextInt(10));
			String emailString=one+two+three+four+five+six+seven+eight+night+ten;
			SendEmailController emailsend=new SendEmailController();
			ICompany com=this.company.findUserByusername(feds.get(0).getCompanyid().getCompanyname());
			emailsend.sendRegister(com.getEmail(),"新密码为"+emailString+"请尽快修改密码");
			String pw0=emailString;
			String pw=DigestUtils.md5Hex(pw0);
			String pw1=DigestUtils.md5Hex(pw);
			String pw2=DigestUtils.md5Hex(pw1);
			com.setPassword(pw2);
			this.company.updateUser(com);
			model.setViewName("success");
	    }
		}
		}
		return model;
	}
	
	@RequestMapping(value="/admin/findpasswd/ignore/{text}")
	@AuthOperation(roleType=RoleType.ADMIN,operationType=OperationType.VIEW)
	public ModelAndView ignoreFindPassWord(HttpServletRequest request,@PathVariable String text){
		
		CompanyEncrypt encry=new CompanyEncrypt();
		String number="";
		Pattern patter=Pattern.compile("^[a-z0-9A-Z]*$");
	    ModelAndView model=new ModelAndView();
	    model.setViewName("403");
		if(text!=null){
		Matcher matcher=patter.matcher(text);
		boolean rs=matcher.matches();
		if(rs){
	    number=encry.Deciphering(text);
	    List<IFindpassword> feds=this.findpw.searchUser(Integer.valueOf(number)*(-1));
	    for(IFindpassword fedss:feds){
	        fedss.setShelve(0);;
	        this.findpw.updateObject(fedss);
	       }
	    model.setViewName("success");
		} 
	    }
		return model;
	}
	
}
