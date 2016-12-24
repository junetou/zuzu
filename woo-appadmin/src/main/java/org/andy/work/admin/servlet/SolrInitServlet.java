package org.andy.work.admin.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.appserver.service.ISolrMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class SolrInitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired  
	private ISolrMaintenanceService solr; 
	
	@Override
	public void init() throws ServletException {
		new Thread(){
			@Override
			public void run() {
			}
		}.start();
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

}
