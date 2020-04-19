package com.board.web.menu.service;

import java.util.List;


import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.web.menu.dao.menuDao;
import com.board.web.menu.model.MenuVO;

@Service
public class menuServiceImpl implements menuService{

	@Autowired
	private menuDao dao;
	
	@Override
	public List<MenuVO> getMenuList() throws Exception {
		// TODO Auto-generated method stub
		return dao.getMenuList();
	}

	@Override
	public void saveMenu(MenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("서비스 들어오니");
		dao.saveMenu(vo);
	}

	@Override
	public void updateMenu(MenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.updateMenu(vo);
	}

	@Override
	public void deleteMenu(String code) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteMenu(code);
	}

}
