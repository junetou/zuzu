package org.andy.work.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaviconController {
	
	@RequestMapping(value="/favicon.ico")
	public void Favicon(HttpServletResponse response,HttpServletRequest request) throws IOException {
		String path=request.getContextPath();
		response.sendRedirect(path + "/portal/person");
	}

}
