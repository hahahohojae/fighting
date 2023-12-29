package com.springbook.biz.userDetail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.userDetail.UserDetailService;
import com.springbook.biz.userDetail.UserDetailVO;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailService{

	@Autowired
	private UserDetailDAO dao;
	
	public void insertUserDetail(UserDetailVO vo) {
		dao.insertUserDetail(vo);
	}

}