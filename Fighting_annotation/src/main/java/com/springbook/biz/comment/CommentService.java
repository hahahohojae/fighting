package com.springbook.biz.comment;

import java.util.List;

public interface CommentService{

	void insertComment(CommentVO vo);

	List<CommentVO> getcommentList(CommentVO vo);

}