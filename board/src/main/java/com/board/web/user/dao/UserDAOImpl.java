package com.board.web.user.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.web.user.model.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
    
	@Inject
	private SqlSession session;
	
	
	@Override
	public List<UserVO> getUserList() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList("com.board.web.user.userMapper.getUserList");
	}

	@Override
	public UserVO getUserInfo(String uid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne("com.board.web.user.userMapper.getUserInfo",uid);
	}

	@Override
	public int insertUser(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.insert("com.board.web.user.userMapper.insertUser",vo);
	}

	@Override
	public int updateUser(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.update("com.board.web.user.userMapper.updateUser",vo);
	}

	@Override
	public int deleteUser(String uid) throws Exception {
		// TODO Auto-generated method stub
		return session.delete("com.board.web.user.userMapper.deleteUser",uid);
	}

	
}
