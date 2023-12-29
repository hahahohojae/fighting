package com.springbook.biz.view;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;
import com.springbook.biz.userDetail.UserDetailVO;
import com.springbook.biz.userDetail.impl.UserDetailDAO;

@Controller
public class userController {
	
	@RequestMapping("/newMember.do")
	public String newMember(UserVO userVO, UserDAO userDAO, UserDetailVO userDetailVO, UserDetailDAO userDetailDAO){
		
		userDAO.insertUser(userVO);
		userDetailDAO.insertUserDetail(userDetailVO);
		
		return "login.jsp";
	}
	
	@RequestMapping("/idUser.do")
	public ModelAndView idUser(UserDetailVO vo, UserDetailDAO dao, ModelAndView mav){
	
		UserDetailVO user = dao.idUser(vo);
		
		if(user != null) {
			mav.addObject("user", user);
			mav.setViewName("idResult.jsp");
			return mav;
		} else {
			mav.setViewName("login.jsp");
			return mav;
		}
	}
	
	@RequestMapping("/passwordUser.do")
	public ModelAndView passwordUser(UserVO vo, UserDAO dao, UserDetailVO vo2, ModelAndView mav){
		
		UserVO user = dao.passwordUser(vo2, vo);
		
		if(user != null) {
			mav.addObject("user", user);
			mav.setViewName("passwordResult.jsp");
			return mav;
		} else {
			mav.setViewName("login.jsp");
			return mav;
		}
		
	}
	
	@RequestMapping("/infoUser.do")
	public String infoUser(UserVO vo, UserDAO dao, HttpSession session, ModelAndView mav){
		
		
		UserVO user = (UserVO)session.getAttribute("user"); //o
			
		String id = user.getId();
		String password = user.getPassword();
		
		vo.setId(id);
		vo.setPassword(password);
		
		Map<String, Object> userList= dao.infoUser(vo); //o
		
		UserVO u = (UserVO)userList.get("user");
		UserDetailVO u2 = (UserDetailVO)userList.get("user2");	
		
		session.setAttribute("u", u);
		session.setAttribute("u2", u2);
		/*
		mav.addObject("u" , u);
		mav.addObject("u2" , u2);*/
		
		return "infoUser.jsp";
	}

	



}
