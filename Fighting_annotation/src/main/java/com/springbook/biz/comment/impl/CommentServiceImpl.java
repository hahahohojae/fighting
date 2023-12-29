package com.springbook.biz.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.comment.CommentService;
import com.springbook.biz.comment.CommentVO;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDAO dao;

	public void insertComment(CommentVO vo) {
		dao.insertComment(vo);
	}

	public List<CommentVO> getcommentList(CommentVO vo) {
		return dao.getcommentList(vo);
	}

}