package service;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.MemberVO;
import dao.MemberDAO;

@Service
public class MemberService {
     
	//회원가입
	@Autowired
	private MemberDAO dao ;
	
	MemberVO member;
	public int signup(MemberVO vo) {
		System.out.println("들어옴?");
		return dao.signup(vo);
	}
	//로그인
//	public boolean signin(String userid,String userpass) {
//		HashMap<String, Object> result =dao.signin(userid);
//		if(result == null) {
//			return false;
//		}else {
//			String pwd =(String) result.get(member.getUserpass());
//		
//		if(pwd ==null) {
//			return false;
//		}else {
//			if(pwd.equals(userpass)){
//				System.out.println("로그인됨?");
//				return true;
//			}else {
//				return false;
//			}
//		}
//		
//	}
//	}
	public MemberVO signin(MemberVO vo) {
		return dao.signin(vo);
	}
	//로그아웃
	public void signout(HttpSession session) {
		session.invalidate();
	}
	public HashMap<String, Object> getMember(String userid){
		return dao.selectOne(userid);
		
	}
}
