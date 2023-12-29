package com.springbook.biz.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.comment.CommentVO;
import com.springbook.biz.comment.impl.CommentDAO;

@Controller
public class commentController {
	
	@RequestMapping("/insertComment.do")
	public String insertComment(CommentVO vo, CommentDAO dao){
		
		dao.insertComment(vo);
		
		return "getBoard.do";
	}
	
	@RequestMapping("/deleteComment.do")
	public String deleteComment(CommentVO vo, CommentDAO dao) {
		
		dao.deleteComment(vo);
		
		return "getBoard.do";
	}
	
	

}
