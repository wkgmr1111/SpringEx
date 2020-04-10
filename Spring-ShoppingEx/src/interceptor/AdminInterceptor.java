package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import model.MemberVO;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	 public boolean preHandle(HttpServletRequest req,
	    HttpServletResponse res, Object obj) throws Exception {
	  
	  HttpSession session = req.getSession();
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  
	  if(member == null) {
		  res.sendRedirect("/signin.do");
		  return false;
		 }
	  if(member.getVerify() != 9 ) {
		  res.sendRedirect("/home.do");
		  return false;
	  }
	  
	  return true;
	 }
	}