package org.andy.work.appserver.solrpublish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.impl.ProductDAO;
import org.andy.work.appserver.dao.impl.PurchaseDAO;
import org.andy.work.appserver.doc.ProductSolrDocument;
import org.andy.work.appserver.model.ICompany;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.IPurchase;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Company;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.appserver.model.impl.Purchase;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.appserver.solrmanager.SolrManager;
import org.andy.work.commons.utils.StringUtil;
import org.andy.work.paging.PagingManagement;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;


@Component
public class ProductSolrPublish {

	@Resource
	private SolrManager solrmanager;
	
	@Resource
	private ProductDAO productdao;
	
	@Resource
	private PurchaseDAO purchasedao;
	
	/*
	 * 搜索引擎为商品
	 */
	public void startSolr() throws SolrServerException, IOException{
		
		List<IProduct> iproduct=this.productdao.SearchAllProduct();
		List<IPurchase> ipurchase=this.purchasedao.SearchAllPurchase();
		List<ProductSolrDocument> productsolrdocuments=new ArrayList<ProductSolrDocument>();
		if(!iproduct.isEmpty()){
		for(IProduct product:iproduct){
			ProductSolrDocument productsolrdocument=new ProductSolrDocument();
			ICompany companys=new Company();
			companys=product.getBelong();
			productsolrdocument.setCompanyname(companys.getDisplayName());
			productsolrdocument.setCompanyid(companys.getId());
			productsolrdocument.setContact(product.getContact());
			productsolrdocument.setContactphone(product.getContactphone());
			productsolrdocument.setAddr(product.getAddr());
			productsolrdocument.setInfomation(product.getInfomation());
			productsolrdocument.setProductname(product.getName());
			productsolrdocument.setPrice(product.getPrice());
			productsolrdocument.setId(product.getId());
			productsolrdocument.setPicone(product.getPicone());
			productsolrdocument.setPictwo(product.getPictwo());
			productsolrdocument.setPicthree(product.getPicthree());
			productsolrdocument.setVersions(1);
			productsolrdocuments.add(productsolrdocument);
		}
		}
		if(!ipurchase.isEmpty()){
		for(IPurchase purchase:ipurchase){
			ProductSolrDocument purchasesolrdocument=new ProductSolrDocument();
			ICompany companys=new Company();
			companys=purchase.getBelong();
			purchasesolrdocument.setCompanyname(companys.getDisplayName());
			purchasesolrdocument.setCompanyid(companys.getId());
			purchasesolrdocument.setContact(purchase.getContact());
			purchasesolrdocument.setContactphone(purchase.getContactphone());
			purchasesolrdocument.setAddr(purchase.getAddr());
			purchasesolrdocument.setInfomation(purchase.getInfomation());
			purchasesolrdocument.setProductname(purchase.getName());
			purchasesolrdocument.setPrice(purchase.getPrice());
			purchasesolrdocument.setId(purchase.getId()*(-1));
			purchasesolrdocument.setPicone(purchase.getPicone());
			purchasesolrdocument.setPictwo(purchase.getPictwo());
			purchasesolrdocument.setPicthree(purchase.getPicthree());
			purchasesolrdocument.setVersions(2);
			productsolrdocuments.add(purchasesolrdocument);
		}
		}
		this.solrmanager.getUpdate().addBeans(productsolrdocuments);
		this.solrmanager.commint();
	}

	
	/*
	 * 清除引擎
	 */
	public void removeSolr(){
		try{
		this.solrmanager.getUpdate().deleteByQuery("*:*");
		}
		catch(Exception e){
			System.out.println("error delete");
		}
	}
	
	/*
	 * 搜索
	 */	
	public Map<String,Object> keyworkProductSearch(String keywork,PagingManagement pgm){
		
		Map<String,Object> pro=new HashMap<String,Object>();
		
		List<IProduct> user=new ArrayList<IProduct>();
		try{
			SolrQuery query=new SolrQuery();
			Pattern pattern=Pattern.compile("^[0-9]+([.]{0,1}[0-9]+){0,1}$");
            if(StringUtil.hasValue(keywork)){
            	if(!pattern.matcher(keywork).matches()){
            	String kw="productname:"+keywork+" or infomation:"+keywork+" or companyname:"+keywork;
            	query.setParam("q", kw).setParam("fq","versions:1").setParam("defType", "edismax").setParam("qf", "productname^20 infomation^10 companyname^5 ").setHighlight(true).setParam("hl.fl", "productname,infomation,companyname ").setHighlightSimplePost("</span>").setHighlightSimplePre("<span style=\"color:purple\">").setStart(pgm.getFirstResult()).setRows(pgm.getNumberPerPage());                            
                QueryResponse querysolr=this.solrmanager.getHttpSolr().query(query);
    			SolrDocumentList result= querysolr.getResults();
    			Map<String, Map<String, List<String>>> map = querysolr.getHighlighting();
    			user=this.getProductHighBeans(result, map);
    			pro.put("list", user);
    			pro.put("num", result.getNumFound());
            	}
            	else{
        		String kw="price:"+keywork;
            	query.setParam("q",kw).setParam("fq","versions:1").setStart(pgm.getFirstResult()).setRows(pgm.getNumberPerPage());
                QueryResponse querysolr=this.solrmanager.getHttpSolr().query(query);
        		SolrDocumentList result= querysolr.getResults();
        		user=this.getProductBeans(result); 	
        		pro.put("list", user);
        		pro.put("num", result.getNumFound());
            	}
            }
            else{
    			query.setParam("q","id:*").setParam("fq","versions:1").setStart(pgm.getFirstResult()).setRows(pgm.getNumberPerPage());
                QueryResponse querysolr=this.solrmanager.getHttpSolr().query(query);
                SolrDocumentList result= querysolr.getResults();
    			user=this.getProductBeans(result); 
    			pro.put("list", user);
    			pro.put("num", result.getNumFound());
            }  
		}catch(Exception e){
			System.out.println(e);
		}
		return pro;
	}
	
	
	private List<IProduct> getProductBeans(SolrDocumentList result) {
		
		List<IProduct> user=new ArrayList<IProduct>();
		for (SolrDocument doc : result) {
			IProduct use=new Product();
			ICompany company=new Company();
			company.setDisplayName(doc.get("companyname").toString().replace("[", "").replace("]", ""));
			company.setId(Integer.valueOf(doc.get("companyid").toString().replace("[", "").replace("]", "")));
			use.setBelong(company);
			use.setName(doc.get("productname").toString().replace("[", "").replace("]", ""));
			use.setPrice(Double.valueOf(doc.get("price").toString().replace("[", "").replace("]", "")));
			use.setAddr(doc.get("addr").toString().replace("[", "").replace("]", ""));
			use.setInfomation(doc.get("infomation").toString().replace("[", "").replace("]", ""));
			use.setContact(doc.get("contact").toString().replace("[", "").replace("]", ""));
			use.setContactphone(doc.get("contactphone").toString().replace("[", "").replace("]", ""));
			use.setPicone(doc.get("picone").toString().replace("[", "").replace("]", ""));
			use.setPictwo(doc.get("pictwo").toString().replace("[", "").replace("]", ""));
			use.setPicthree(doc.get("picthree").toString().replace("[", "").replace("]", ""));
			use.setId(Integer.valueOf(doc.get("id").toString().replace("[", "").replace("]", "")));
			user.add(use);
		}
		return user;
	}
	
	private List<IProduct> getProductHighBeans(SolrDocumentList result,Map<String, Map<String, List<String>>> maps){
		
		List<IProduct> user=new ArrayList<IProduct>();
		for (SolrDocument doc : result) {
			IProduct use=new Product();
			ICompany company=new Company();
			List<String> name=maps.get(doc.get("id")).get("productname");
			List<String> infomation=maps.get(doc.get("id")).get("infomation");
			List<String> companyname=maps.get(doc.get("id")).get("companyname");
			if(name!=null){
			use.setName(name.get(0));
			}
			else{
			use.setName(doc.get("productname").toString().replace("[", "").replace("]", ""));
			}
			if(infomation!=null){
			use.setInfomation(infomation.get(0));
			}
			else{
			use.setInfomation(doc.get("infomation").toString().replace("[", "").replace("]", ""));
			}
			if(companyname!=null){
			company.setDisplayName(companyname.get(0));
			company.setId(Integer.valueOf(doc.get("companyid").toString().replace("[", "").replace("]", "")));
			use.setBelong(company);
			}
			else{
			company.setDisplayName(doc.get("companyname").toString().replace("[", "").replace("]", ""));
			company.setId(Integer.valueOf(doc.get("companyid").toString().replace("[", "").replace("]", "")));
			use.setBelong(company);
			}
			use.setPrice(Double.valueOf(doc.get("price").toString().replace("[", "").replace("]", "")));
			use.setAddr(doc.get("addr").toString().replace("[", "").replace("]", ""));
			use.setContact(doc.get("contact").toString().replace("[", "").replace("]", ""));
			use.setContactphone(doc.get("contactphone").toString().replace("[", "").replace("]", ""));
			use.setPicone(doc.get("picone").toString().replace("[", "").replace("]", ""));
			use.setPictwo(doc.get("pictwo").toString().replace("[", "").replace("]", ""));
			use.setPicthree(doc.get("picthree").toString().replace("[", "").replace("]", ""));
			use.setId(Integer.valueOf(doc.get("id").toString().replace("[", "").replace("]", "")));
			user.add(use);
		   }		
		return user;

	}
	
	/*
	 * 搜索
	 */	
	public Map<String,Object> keyworkPurchaseSearch(String keywork,PagingManagement pgm){
		
		Map<String,Object> pur=new HashMap<String,Object>();
		List<IPurchase> user=new ArrayList<IPurchase>();
		try{
			SolrQuery query=new SolrQuery();
			Pattern pattern=Pattern.compile("^[0-9]+([.]{0,1}[0-9]+){0,1}$");
            if(StringUtil.hasValue(keywork)){
            	if(!pattern.matcher(keywork).matches()){
            	String kw="productname:"+keywork+" or infomation:"+keywork+" or companyname:"+keywork;
            	query.setParam("q", kw).setParam("fq","versions:2").setParam("defType", "edismax").setParam("qf", "productname^20 infomation^10 companyname^5 ").setHighlight(true).setParam("hl.fl", "productname,infomation,companyname ").setHighlightSimplePost("</span>").setHighlightSimplePre("<span style=\"color:purple\">").setStart(pgm.getFirstResult()).setRows(pgm.getNumberPerPage());
                QueryResponse querysolr=this.solrmanager.getHttpSolr().query(query);
    			SolrDocumentList result= querysolr.getResults();
    			Map<String, Map<String, List<String>>> map = querysolr.getHighlighting();
    			user=this.getPurchaseHighBeans(result, map);
    			pur.put("list", user);
    			pur.put("num", result.getNumFound());
            	}
            	else{
        		String kw="price:"+keywork;
            	query.setParam("q",kw).setParam("fq","versions:2").setStart(pgm.getFirstResult()).setRows(pgm.getNumberPerPage());
                QueryResponse querysolr=this.solrmanager.getHttpSolr().query(query);
        		SolrDocumentList result= querysolr.getResults();
        		user=this.getPurchaseBeans(result); 	
        		pur.put("list", user);
        		pur.put("num", result.getNumFound());
            	}
            }
            else{
    			query.setParam("q","id:*").setParam("fq","versions:2").setStart(pgm.getFirstResult()).setRows(pgm.getNumberPerPage());
                QueryResponse querysolr=this.solrmanager.getHttpSolr().query(query);
    			SolrDocumentList result= querysolr.getResults();
    			user=this.getPurchaseBeans(result); 
    			pur.put("list", user);
    			pur.put("num", result.getNumFound());
            }  
		}catch(Exception e){
			System.out.println(e);
		}
		return pur;
	}
	
	
	private List<IPurchase> getPurchaseBeans(SolrDocumentList result) {
		
		List<IPurchase> user=new ArrayList<IPurchase>();
		for (SolrDocument doc : result) {
			IPurchase use=new Purchase();
			ICompany company=new Company();
			company.setDisplayName(doc.get("companyname").toString().replace("[", "").replace("]", ""));
			company.setId(Integer.valueOf(doc.get("companyid").toString().replace("[", "").replace("]", "")));
			use.setBelong(company);
			use.setName(doc.get("productname").toString().replace("[", "").replace("]", ""));
			use.setPrice(Double.valueOf(doc.get("price").toString().replace("[", "").replace("]", "")));
			use.setAddr(doc.get("addr").toString().replace("[", "").replace("]", ""));
			use.setInfomation(doc.get("infomation").toString().replace("[", "").replace("]", ""));
			use.setContact(doc.get("contact").toString().replace("[", "").replace("]", ""));
			use.setContactphone(doc.get("contactphone").toString().replace("[", "").replace("]", ""));
			use.setPicone(doc.get("picone").toString().replace("[", "").replace("]", ""));
			use.setPictwo(doc.get("pictwo").toString().replace("[", "").replace("]", ""));
			use.setPicthree(doc.get("picthree").toString().replace("[", "").replace("]", ""));
			use.setId(Integer.valueOf(doc.get("id").toString().replace("[", "").replace("]", "")));
			user.add(use);
		}
		return user;
	}
	
	private List<IPurchase> getPurchaseHighBeans(SolrDocumentList result,Map<String, Map<String, List<String>>> maps){
		
		List<IPurchase> user=new ArrayList<IPurchase>();
		for (SolrDocument doc : result) {
			IPurchase use=new Purchase();
			ICompany company=new Company();
			List<String> name=maps.get(doc.get("id")).get("productname");
			List<String> infomation=maps.get(doc.get("id")).get("infomation");
			List<String> companyname=maps.get(doc.get("id")).get("companyname");
			if(name!=null){
			use.setName(name.get(0));
			}
			else{
			use.setName(doc.get("productname").toString().replace("[", "").replace("]", ""));
			}
			if(infomation!=null){
			use.setInfomation(infomation.get(0));
			}
			else{
			use.setInfomation(doc.get("infomation").toString().replace("[", "").replace("]", ""));
			}
			if(companyname!=null){
			company.setDisplayName(companyname.get(0));
			company.setId(Integer.valueOf(doc.get("companyid").toString().replace("[", "").replace("]", "")));
			use.setBelong(company);
			}
			else{
			company.setDisplayName(doc.get("companyname").toString().replace("[", "").replace("]", ""));
			company.setId(Integer.valueOf(doc.get("companyid").toString().replace("[", "").replace("]", "")));
			use.setBelong(company);
			}
			use.setPrice(Double.valueOf(doc.get("price").toString().replace("[", "").replace("]", "")));
			use.setAddr(doc.get("addr").toString().replace("[", "").replace("]", ""));
			use.setContact(doc.get("contact").toString().replace("[", "").replace("]", ""));
			use.setContactphone(doc.get("contactphone").toString().replace("[", "").replace("]", ""));
			use.setPicone(doc.get("picone").toString().replace("[", "").replace("]", ""));
			use.setPictwo(doc.get("pictwo").toString().replace("[", "").replace("]", ""));
			use.setPicthree(doc.get("picthree").toString().replace("[", "").replace("]", ""));
			use.setId(Integer.valueOf(doc.get("id").toString().replace("[", "").replace("]", "")));
			user.add(use);
		   }		
		return user;

	}
	
}
