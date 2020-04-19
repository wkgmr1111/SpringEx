package com.board.web.menu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.web.menu.model.MenuVO;
import com.board.web.menu.service.menuService;

@RestController
@RequestMapping("/restMenu")
public class RestMenuController {

	
	
	private static final Logger logger = LoggerFactory.getLogger(RestMenuController.class);
	
	@Autowired
	private menuService service;
	
	@RequestMapping(value = "/getMenuList", method = RequestMethod.POST)
	public Map<String, Object> getMenuList(){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			result.put("menuList", service.getMenuList());
			 result.put("status", "ok");
		} catch (Exception e) {
			result.put("status", "error");
			logger.info(e.getMessage());
		}
		return result;
		
	}
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	public Map<String, Object> saveMenu(MenuVO vo){
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("menuVO : " + vo.toString());

		System.out.println("아잉");
		try {
			service.saveMenu(vo);
			System.out.println("아잉1");
			result.put("status", "ok");
			
		} catch (Exception e) {
			result.put("status", "error");
			logger.info(e.getMessage());
		}
		
		return result;
	}
	@RequestMapping(value = "/updateMenu" , method = RequestMethod.POST)
	public Map<String, Object> updateMenu(MenuVO vo){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			service.updateMenu(vo);
			result.put("status", "ok");
		} catch (Exception e) {
	        result.put("status", "error");
		    logger.info(e.getMessage());
		
		}
		return result;
	}
	@RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
	public Map<String, Object>deleteMenu(@RequestParam("code") String code){
		Map<String, Object> result  = new HashMap<String, Object>();
		
		try {
			service.deleteMenu(code);
			result.put("stauts","ok");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("stauts","error");
			logger.info(e.getMessage());
		}
		return result;
	}
}
