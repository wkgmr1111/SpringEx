package com.board.web.user.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.web.user.model.UserVO;
import com.board.web.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	@RequestMapping(value = "/getUserList" , method = RequestMethod.GET)
	public String getUserList(Model model) throws Exception {
		logger.info("getUserList");
		
		model.addAttribute("userList" ,service.getUserList());
		
		return "user/userList";
	}
	
	@RequestMapping(value = "/insertUser" ,  method = RequestMethod.POST)
	public String insertUser(@ModelAttribute("userVO") UserVO vo , RedirectAttributes rttr) throws Exception {
		
		   service.insertUser(vo);
		   
		   return "redirect:/user/getUserList";
		
	}
	@RequestMapping(value = "/mypage", method = RequestMethod.POST )
	public String mypage(HttpSession session, Model model,String uid ) throws Exception {
		 UserVO vo = service.getUserInfo(uid); //아이디 가져오기
		 System.out.println(vo);
	
	
		model.addAttribute("user",vo);
		
		
		return "user/mypage";
		
	}
	
	@RequestMapping(value = "/signout")
	public String signout(HttpSession session) throws Exception {
		service.logout(session);
		return "redirect:/login/login";
		
	}
}
