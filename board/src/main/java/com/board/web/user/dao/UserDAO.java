package com.board.web.user.dao;

import java.util.List;

import com.board.web.user.model.UserVO;

public interface UserDAO {

	public List<UserVO> getUserList()throws Exception;
	
	public UserVO getUserInfo(String uid)throws Exception;
	
	public int insertUser(UserVO vo)throws Exception;
	
	public int updateUser(UserVO vo)throws Exception;
	
	public int deleteUser(String uid)throws Exception;
}
