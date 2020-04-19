package com.board.web.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.board.web.user.model.UserVO;

public interface UserService {

	public List<UserVO> getUserList()throws Exception;
	
	public UserVO getUserInfo(String uid)throws Exception;
	
	public void insertUser(UserVO vo)throws Exception;
	
	public void updateUser(UserVO vo)throws Exception;
	
	public void deleteUser(String uid)throws Exception;
	
    public void logout(HttpSession session)throws Exception;
}
