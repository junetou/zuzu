package org.andy.work.admin.controller.one;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/secure")
public class LoginController {
	
	@RequestMapping("/login")
	public ModelAndView login(){
		
		ModelAndView model=new ModelAndView();
		model.setViewName("tiles/own/login");
		return model;
	}

}
