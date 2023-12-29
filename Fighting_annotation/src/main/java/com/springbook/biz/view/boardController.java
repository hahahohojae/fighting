package com.springbook.biz.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.comment.CommentVO;
import com.springbook.biz.comment.impl.CommentDAO;
import com.springbook.biz.user.UserVO;

@Controller
public class boardController {
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo , BoardDAO dao) {
		
		dao.insertBoard(vo);

		return "getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo , BoardDAO dao) {
		
		dao.updateBoard(vo);

		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo , BoardDAO dao , CommentVO vo2 , CommentDAO dao2) {
		
		dao2.withComment(vo2);
		dao.insertBoard(vo);

		return "getBoardList.do";
	}
	
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardVO vo, BoardDAO dao, ModelAndView mav, HttpSession session){
		
		//UserVO user = (UserVO)session.getAttribute("user");
		
		mav.addObject("user", session.getAttribute("user"));
		mav.addObject("boardList" , dao.getBoardList(vo));
		mav.setViewName("getBoardList.jsp");
		return mav;
	}
	
	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO vo , BoardDAO dao , CommentVO vo2 , CommentDAO dao2 , ModelAndView mav) {
		
		mav.addObject("commentList" , dao2.getcommentList(vo2));
		mav.addObject("board" , dao.getBoard(vo));
		mav.setViewName("getBoard.jsp");
		return mav;
		
	}
	
	@RequestMapping("/getMyList.do")
	public ModelAndView getMyList(BoardVO vo, BoardDAO dao, ModelAndView mav, HttpSession session){
		
		UserVO user = (UserVO)session.getAttribute("user");
		String writer = (String)user.getNickName();
		vo.setWriter(writer);
		
		mav.addObject("myList" , dao.getMyList(vo));
		mav.setViewName("getMyList.jsp");
		return mav;
		}
	
	@RequestMapping("/searchBoard.do")
	public ModelAndView searchBoard(BoardVO vo, BoardDAO dao, ModelAndView mav, @RequestParam("searchType") String searchType, @RequestParam("searchKeyword") String searchKeyword){
		
		if(searchType.equals("TITLE")) {
			vo.setTitle(searchKeyword);
			mav.addObject("searchList" , dao.getTSearch(vo));
		} else if(searchType.equals("CONTENT")) {
			vo.setContent(searchKeyword);
			mav.addObject("searchList" , dao.getCSearch(vo));
		}
		
		mav.setViewName("searchBoardList.jsp");
		return mav;
	} 
	
	@RequestMapping("/cntBoard.do")
	public String cntBoard(BoardVO vo, BoardDAO dao){
		
		dao.cntBoard(vo);
		
		return "getBoard.do";
	}
	
	@RequestMapping("/b_likeBoard.do")
	public String b_likeBoard(BoardVO vo, BoardDAO dao){
		
		dao.likeBoard(vo);
		
		return "getBoard.do";
	}
	
	



}
