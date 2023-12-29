package com.springbook.biz.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class logoutController{

	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		
		session.invalidate();
	
		return "login.jsp";
	
	}

}
