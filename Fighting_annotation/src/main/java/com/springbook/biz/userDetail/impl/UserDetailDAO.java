package com.springbook.biz.userDetail.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.userDetail.UserDetailVO;

@Repository("userDetailDAO")
public class UserDetailDAO{

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	private final String USERDETAIL_INSERT = "insert into userdetail values(?,?,?,?,?)";
	private final String USER_ID = "select id from userdetail where phonenumber1 = ? and phonenumber2 = ? and email = ?";
	
	public UserDetailVO idUser(UserDetailVO vo) {
		UserDetailVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_ID);
			stmt.setString(1, vo.getPhoneNumber1());
			stmt.setString(2, vo.getPhoneNumber2());
			stmt.setString(3, vo.getEmail());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserDetailVO();
				user.setId(rs.getString("ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		} return user;
	}
	
	
	public void insertUserDetail(UserDetailVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USERDETAIL_INSERT);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPhoneNumber1());
			stmt.setString(3, vo.getPhoneNumber2());
			stmt.setString(4, vo.getAddress());
			stmt.setString(5, vo.getEmail());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}


}

