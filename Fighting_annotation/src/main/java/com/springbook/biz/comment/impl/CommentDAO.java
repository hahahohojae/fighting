package com.springbook.biz.comment.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.comment.CommentVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("commentDAO")
public class CommentDAO{

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String COMMENT_INSERT = "insert into comment(seq , co_nickname , co_content , co_seq) values(?,?,?,(select nvl(max(co_seq) , 0)+1 from comment where seq = ?))";
	private final String COMMENT_LIST = "select co_nickname , co_content, co_regdate, co_seq from comment c inner join board b on c.seq = b.seq where b.seq = ?";
	private final String COMMENT_DELETE = "delete comment where co_seq = ? and seq = ?";
	private final String COMMENT_WITH = "delete comment where seq = ?";
	
	public void withComment(CommentVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(COMMENT_WITH);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	
	}
	
	public void deleteComment(CommentVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(COMMENT_DELETE);
			stmt.setInt(1, vo.getCo_seq());
			stmt.setInt(2, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void insertComment(CommentVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(COMMENT_INSERT);
			stmt.setInt(1, vo.getSeq());
			stmt.setString(2, vo.getCo_nickName());
			stmt.setString(3, vo.getCo_content());
			stmt.setInt(4, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	

	public List<CommentVO> getcommentList(CommentVO vo) {
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(COMMENT_LIST);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			while(rs.next()) {
				CommentVO comment = new CommentVO();
				comment.setCo_nickName(rs.getString("CO_NICKNAME"));
				comment.setCo_content(rs.getString("CO_CONTENT"));
				comment.setCo_regDate(rs.getDate("CO_REGDATE"));
				comment.setCo_seq(rs.getInt("CO_SEQ"));
				commentList.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		} 
		return commentList;
	}
	
}
