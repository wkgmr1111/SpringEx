package com.board.web.user.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.board.web.user.dao.UserDAO;
import com.board.web.user.model.UserVO;

@Service
public class UserServiceImpl implements UserService {
    
	@Inject
	private UserDAO dao;
	
	
	@Override
	public List<UserVO> getUserList() throws Exception {
		// TODO Auto-generated method stub
		return dao.getUserList();
	}

	@Override
	public UserVO getUserInfo(String uid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getUserInfo(uid);
	}

	@Override
	public void insertUser(UserVO vo) throws Exception {
		dao.insertUser(vo);
	}

	@Override
	public void updateUser(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.updateUser(vo);
		
	}

	@Override
	public void deleteUser(String uid) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteUser(uid);
	}
   //로그아웃
	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();
		
	}

	
}
