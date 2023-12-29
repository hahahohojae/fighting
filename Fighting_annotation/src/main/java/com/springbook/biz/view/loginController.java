package com.springbook.biz.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class loginController{

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(UserVO vo){
		
		return "login.jsp";
	}
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO vo, UserDAO dao, HttpSession session){
		
		UserVO user = dao.getUser(vo);
		
		if(user!=null) {
			session.setAttribute("user", user);
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}
	
	
	
	
	

	

}
