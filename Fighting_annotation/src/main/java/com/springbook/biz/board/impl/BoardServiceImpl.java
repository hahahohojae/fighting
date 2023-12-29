package com.springbook.biz.board.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO dao;

	public void insertBoard(BoardVO vo) {
		dao.insertBoard(vo);
	}

	public void updateBoard(BoardVO vo) {
		dao.updateBoard(vo);
	}

	public void deleteBoard(BoardVO vo) {
		dao.deleteBoard(vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		return dao.getBoard(vo);
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		return dao.getBoardList(vo);
	}
	public void cntBoard(BoardVO vo) {
		dao.cntBoard(vo);
	}
	
	public void likeBoard(BoardVO vo) {
		dao.likeBoard(vo);
	}
	
	public List<BoardVO> getTSearch(BoardVO vo) {
		return dao.getTSearch(vo);
	}
	
	public List<BoardVO> getCSearch(BoardVO vo) {
		return dao.getCSearch(vo);
	}
}