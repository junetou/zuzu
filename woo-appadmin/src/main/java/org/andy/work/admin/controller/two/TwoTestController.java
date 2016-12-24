package org.andy.work.admin.controller.two;

import javax.annotation.Resource;

import org.andy.work.admin.helper.UserHelper;
import org.andy.work.appserver.manager.DatabaseContextHolder;
import org.andy.work.appserver.sourcechoose.ISourceChoose;
import org.andy.work.appserver.sourcechoose.impl.SourceChoose;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal")
public class TwoTestController {

	@Resource
	private UserHelper userhelper;
	
	@Resource
	private ISourceChoose test;
	
	@RequestMapping(value="/two/test")
	@ResponseBody
	public ModelAndView TwoController(){
		test.setSourceTwo();
		ModelAndView model=new ModelAndView();
		if(userhelper.findUserByUsername("test3")!=null){
		model.setViewName("/WEB-INF/success.jsp");
		}
		else{
		model.setViewName("/WEB-INF/error.jsp");
		}
		test.setSource();
		return model;
	}
	
}
