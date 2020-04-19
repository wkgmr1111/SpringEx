package com.board.web.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.web.common.Pagination;
import com.board.web.common.Search;
import com.board.web.dao.BoardDao;
import com.board.web.model.BoardVO;
import com.board.web.model.ReplyVO;

@Service
public class BoardServieImpl implements BoardService{
    
	@Inject
	private BoardDao dao;
	@Override
	public List<BoardVO> getBoardList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return dao.getBoardList(search);
	}
	
	public void insertBoard(BoardVO vo) throws Exception {
		dao.insertBoard(vo);
	}
    
	@Transactional
	@Override
	public BoardVO getBoardContent(int bid) throws Exception {
		     BoardVO vo = new BoardVO();
		   
			dao.updateViewCnt(bid);//조회수 1증가 
			vo = dao.getBoardContent(bid);
		/*
		 * try { vo.setBid(bid); vo.setCate_cd(
		 * "1111111111111111111111111111111111111111111111111111111111111111111111111111"
		 * ); dao.updateBoard(vo); }catch (RuntimeException e) { throw new
		 * NotFoundException(null); }
		 */
			return vo;
	}


	@Override
	public void updateBoard(BoardVO vo) throws Exception {
		dao.updateBoard(vo);
		
	}

	@Override
	public void deleteBoard(int bid) throws Exception {
		dao.deleteBoard(bid);
	}
	
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		// TODO Auto-generated method stub
		return dao.getBoardListCnt(search);
	}
/////////////////////댓글 관련
	
	@Override
	public List<ReplyVO> getReplyList(int bid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReplyList(bid);
	}

	@Override
	public int saveReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.saveReply(vo);
	}

	@Override
	public int updateReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateReply(vo);
	}

	@Override
	public int deleteReply(int rid) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteReply(rid);
	}

}
