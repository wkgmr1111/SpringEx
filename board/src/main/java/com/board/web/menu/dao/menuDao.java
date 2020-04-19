package com.board.web.menu.dao;

import java.util.List;

import com.board.web.menu.model.MenuVO;

public interface menuDao {

	public List<MenuVO> getMenuList()throws Exception;
	
	public int saveMenu (MenuVO vo)throws Exception;
	
	public int updateMenu(MenuVO vo)throws Exception;
	
	public int deleteMenu(String code)throws Exception;
}
