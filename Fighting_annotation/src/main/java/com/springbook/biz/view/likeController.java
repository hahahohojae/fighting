package com.springbook.biz.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;

@Controller
public class likeController {

	@RequestMapping("/getMyList.do")
	public ModelAndView getMyList(BoardVO vo, BoardDAO dao, ModelAndView mav, HttpSession session){
		
		UserVO user = (UserVO)session.getAttribute("user");
		String writer = (String)user.getNickName();
		vo.setWriter(writer);
		
		mav.addObject("myList" , dao.getMyList(vo));
		mav.setViewName("getMyList.jsp");
		return mav;
		}
}
