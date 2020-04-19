package com.board.web.menu.service;

import java.util.List;

import com.board.web.menu.model.MenuVO;

public interface menuService {

	public List<MenuVO> getMenuList()throws Exception;
	
	public void saveMenu(MenuVO vo)throws Exception;
	
	public void updateMenu(MenuVO vo)throws Exception;
	
	public void deleteMenu(String code)throws Exception;
}
