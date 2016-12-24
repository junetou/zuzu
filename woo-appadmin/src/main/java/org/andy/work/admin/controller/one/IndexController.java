package org.andy.work.admin.controller.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.detail.ProductMessage;
import org.andy.work.admin.helper.VerifyCodeCookie;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.IPurchaseMaintenanceService;
import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.andy.work.appserver.service.IUserMaintenanceService;
import org.andy.work.paging.GridData;
import org.andy.work.secure.CompanyEncrypt;
import org.andy.work.secure.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@Resource
	private IUserMaintenanceService user; 
	
	@Resource
	private IProductMaintenanceService product;
	
	@Resource
	private IPurchaseMaintenanceService purchase;
	
	@Resource
	private ISolrMaintenanceService solr; 
	
	@Autowired 
	private VerifyCodeCookie codecookie;
    
	@RequestMapping(value="/403")
	public ModelAndView error403(){
		
		ModelAndView model=new ModelAndView();
		model.setViewName("403");
		return model;
	}
	
	@RequestMapping(value="/index")
	public ModelAndView Index(){
		
		GridData<ProductMessage> productgrid=new GridData<ProductMessage>();
		GridData<ProductMessage> productgridtwo=new GridData<ProductMessage>();
		GridData<ProductMessage> productgridthree=new GridData<ProductMessage>();
		GridData<ProductMessage> purchasegrid=new GridData<ProductMessage>();
		GridData<ProductMessage> purchasegridtwo=new GridData<ProductMessage>();
		GridData<ProductMessage> purchasegridthree=new GridData<ProductMessage>();
		Random random=new Random();
		Integer productnumber=product.count();
		Integer purchasenumber=purchase.count();
		Encrypt encry=new Encrypt();
		CompanyEncrypt companyencry=new CompanyEncrypt();
		List<ProductMessage> productdatas=new ArrayList<ProductMessage>();
		List<ProductMessage> producttwodatas=new ArrayList<ProductMessage>();
		List<ProductMessage> productthreedatas=new ArrayList<ProductMessage>();
		List<ProductMessage> purchasedatas=new ArrayList<ProductMessage>();
		List<ProductMessage> purchasetwodatas=new ArrayList<ProductMessage>();
		List<ProductMessage> purchasethreedatas=new ArrayList<ProductMessage>();
		for(int i=0;i<3;i++){
			IProduct products=product.search(random.nextInt(productnumber-1)+1);
			while(products==null){
		    products=product.search(random.nextInt(productnumber-1)+1);
			}
			ProductMessage data=new ProductMessage();
			ICompany company=products.getBelong();
			data.setNumber(encry.Encrupt(products.getId()));
			data.setCompanyname(company.getCompanyname());
			data.setCompanyid(companyencry.Encrupt(company.getId()));
			data.setAddr(products.getAddr());
			data.setName(products.getName());
			data.setInfomation(products.getInfomation());
			data.setPrice(products.getPrice());
			if(products.getPicone().equals("null")){
				data.setPicone("null.jpg");
			}else{
			data.setPicone(products.getPicone());
			}
			switch(i){
			case 0:productdatas.add(data);break;
			case 1:producttwodatas.add(data);break;
			case 2:productthreedatas.add(data);break;
			}
		}
		for(int i=0;i<3;i++){
			IPurchase purchases=purchase.search(random.nextInt(purchasenumber-1)+1);
			while(purchases==null){
			    purchases=purchase.search(random.nextInt(productnumber-1)+1);
			}
			ProductMessage data=new ProductMessage();
			ICompany company=purchases.getBelong();
			data.setNumber(encry.Encrupt(purchases.getId()*(-1)));
			data.setCompanyname(company.getCompanyname());
			data.setCompanyid(companyencry.Encrupt(company.getId()));
			data.setAddr(purchases.getAddr());
			data.setName(purchases.getName());
			data.setInfomation(purchases.getInfomation());
			data.setPrice(purchases.getPrice());
			if(purchases.getPicone().equals("null")){
			data.setPicone("null.jpg");
			}else{
			data.setPicone(purchases.getPicone());
			}
			switch(i){
			case 0:purchasedatas.add(data);break;
			case 1:purchasetwodatas.add(data);break;
			case 2:purchasethreedatas.add(data);break;
			}
		}
		
		productgrid.setDatas(productdatas);
		productgridtwo.setDatas(producttwodatas);
		productgridthree.setDatas(productthreedatas);
        purchasegrid.setDatas(purchasedatas);
        purchasegridtwo.setDatas(purchasetwodatas);
        purchasegridthree.setDatas(purchasethreedatas);
		
		ModelAndView model=new ModelAndView();
		model.addObject("productgird", productgrid)
             .addObject("productgirdtwo", productgridtwo)
             .addObject("productgirdthree", productgridthree)
             .addObject("purchasegird", purchasegrid)
             .addObject("purchasegirdtwo", purchasegridtwo)
             .addObject("purchasegirdthree", purchasegridthree)
		     .setViewName("tiles/index/index");
		return model;
	}
	
	@RequestMapping(value="/ajaxcode",method=RequestMethod.POST)
	public void check(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
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
	  this.codecookie.setUseCode(request, buffer.toString());
	  boolean judge=this.codecookie.isVerify(request);
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
