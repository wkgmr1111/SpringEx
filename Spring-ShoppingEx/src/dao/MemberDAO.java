package dao;

import java.util.HashMap;

import model.MemberVO;

public interface MemberDAO {

	//회원가입
	public int signup(MemberVO vo);
	
	//로그인
	public MemberVO signin(MemberVO vo);
	//하나가져오기
	public HashMap<String, Object> selectOne(String userid);
}
