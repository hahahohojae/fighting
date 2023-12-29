package com.springbook.biz.boardone.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.springbook.biz.boardone.boardoneVO;
import com.springbook.biz.common.JDBCUtil;

public class boardoneDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String BOARDONE_LIKE = "select id from boardone where seq = ?";
	
	public boardoneVO getBoardone(boardoneVO vo) {
		boardoneVO board = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARDONE_LIKE);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			if(rs.next()) {
				board = new boardoneVO();
				board.setId(rs.getString("ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		} return board;
	}

}
