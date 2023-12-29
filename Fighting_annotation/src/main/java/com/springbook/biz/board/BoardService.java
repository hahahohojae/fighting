package com.springbook.biz.board;

import java.util.List;

public interface BoardService{

	void insertBoard(BoardVO vo);

	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	BoardVO getBoard(BoardVO vo);

	List<BoardVO> getBoardList(BoardVO vo);
	
	void cntBoard(BoardVO vo);
	
	void likeBoard(BoardVO vo);
	
	List<BoardVO> getTSearch(BoardVO vo);
	
	List<BoardVO> getCSearch(BoardVO vo);
	

}