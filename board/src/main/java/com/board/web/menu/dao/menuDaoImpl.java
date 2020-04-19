package com.board.web.menu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.web.menu.model.MenuVO;

@Repository
public class menuDaoImpl implements menuDao {
   
	@Autowired
	private SqlSession session;
	
	
	@Override
	public List<MenuVO> getMenuList() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList("com.board.web.menu.menuMapper.getMenuList");
	}

	@Override
	public int saveMenu(MenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DAO 들어오니?");
		return session.insert("com.board.web.menu.menuMapper.insertMenu", vo);
	}

	@Override
	public int updateMenu(MenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.update("com.board.web.menu.menuMapper.updateMenu",vo);
	}

	@Override
	public int deleteMenu(String code) throws Exception {
		// TODO Auto-generated method stub
		return session.delete("com.board.web.menu.menuMapper.deleteMenu",code);
	}

}
