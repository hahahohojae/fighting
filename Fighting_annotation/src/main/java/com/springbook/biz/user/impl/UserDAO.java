package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.userDetail.UserDetailVO;

@Repository("userDAO")
public class UserDAO{
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String USER_GET = "select * from users where id = ? and password = ?"; 
	private final String USER_INSERT = "insert into users values(?,?,?,?,?)";
	private final String USER_PASSWORD = "select password from users u inner join userdetail d on u.id = d.id where u.id = ? and u.name = ? and d.address = ?";
	private final String USER_INFO = "select * from users u inner join userdetail d on u.id = d.id where u.id = ? and u.password = ?";
	
	public UserVO passwordUser(UserDetailVO vo2 , UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_PASSWORD);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getName());
			stmt.setString(3, vo2.getAddress());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setPassword(rs.getString("PASSWORD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		} return user;
	}
	
	
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
				user.setNickName(rs.getString("NICKNAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		} return user;
	}
	
	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_INSERT);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			stmt.setString(5, vo.getNickName());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public Map<String, Object> infoUser(UserVO vo) {
	    UserVO user = null;
	    UserDetailVO user2 = null;
	    Map<String, Object> result = new HashMap<String, Object>();
	    try {
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(USER_INFO);
	        stmt.setString(1, vo.getId());
	        stmt.setString(2, vo.getPassword());
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            user = new UserVO();
	            user2 = new UserDetailVO();
	            user.setId(rs.getString("ID"));
	            user.setPassword(rs.getString("PASSWORD"));
	            user.setName(rs.getString("NAME"));
	            user.setRole(rs.getString("ROLE"));
	            user.setNickName(rs.getString("NICKNAME"));
	            user2.setPhoneNumber1(rs.getString("PHONENUMBER1"));
	            user2.setPhoneNumber2(rs.getString("PHONENUMBER2"));
	            user2.setAddress(rs.getString("ADDRESS"));
	            user2.setEmail(rs.getString("EMAIL"));
	            result.put("user", user);
	            result.put("user2", user2);
	        } 
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(rs, stmt, conn);
	    }
	    return result;
	}
	
	



}
