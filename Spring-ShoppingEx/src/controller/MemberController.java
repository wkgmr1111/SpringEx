package controller;







import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import model.MemberVO;
import service.MemberService;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
    @Autowired
	BCryptPasswordEncoder passEncoder;

	// 회원 가입 get
	@RequestMapping(value = "signup.do", method = RequestMethod.GET)
	public void getSignup()  {
	 logger.info("get signup");
	  
	}

	// 회원 가입 post
	@RequestMapping( value = "sign.do", method = RequestMethod.POST)
	public String postSignup( MemberVO vo)  {
	 logger.info("post signup");
	  
	 String inputPass = vo.getUserPass();
	 String pass = passEncoder.encode(inputPass);
	 vo.setUserPass(pass);
     System.out.println(vo.toString());
	 service.signup(vo);

	 return "redirect:home.do";
	}
	
	@RequestMapping("home.do")
	public String home() {
		
		
		return "home";
	}
	// 로그인  get
	@RequestMapping(value = "signin.do", method = RequestMethod.GET)
	public void getSignin() {
	 logger.info("get signin");
	}

	// 로그인 post
	@RequestMapping(value = "login.do", method = RequestMethod.POST)

		public String postSignin(MemberVO vo,HttpSession session, Model model) {
			 logger.info("post signin");
			   
			 MemberVO login = service.signin(vo);  
			 if(login == null) {
				 
				 return "redirect:home.do";
			 }
			 boolean passMatch = passEncoder.matches(vo.getUserPass(), login.getUserPass());
			 System.out.println(passMatch);
		
			if(login != null && passMatch) {
			  session.setAttribute("member", login);
			 } else {
			  session.setAttribute("member", null);
		        model.addAttribute("msg", false);
			  return "redirect:signin.do";
			 }  
			 
			 return "redirect:home.do";
			}
			 
	//로그아웃
	@RequestMapping("signout.do")
	public String signout(HttpSession session) {
		logger.info("get logout");
		service.signout(session);
		return "redirect:home.do";
	}
	
}
