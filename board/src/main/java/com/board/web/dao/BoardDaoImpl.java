package com.board.web.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.web.common.Pagination;
import com.board.web.common.Search;
import com.board.web.model.BoardVO;
import com.board.web.model.ReplyVO;

@Repository
public class BoardDaoImpl implements BoardDao{
    
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<BoardVO> getBoardList(Search search) throws Exception {
		// TODO Auto-generated method stub
	return sqlSession.selectList("com.board.web.boardMapper.getBoardList" , search);

	
	}

	@Override
	public BoardVO getBoardContent(int bid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.board.web.boardMapper.getBoardContent",bid);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.board.web.boardMapper.insertBoard",boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("com.board.web.boardMapper.updateBoard",boardVO);
	}

	@Override
	public int deleteBoard(int bid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("com.board.web.boardMapper.deleteBoard",bid);
	}

	@Override
	public int updateViewCnt(int bid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("com.board.web.boardMapper.updateViewCnt",bid);
	}

	@Override
	public int getBoardListCnt(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.board.web.boardMapper.getBoardListCnt",search);
	}
	
	
   ///////댓글리스트
	
	
	@Override
	public List<ReplyVO> getReplyList(int bid) throws Exception {
		
		return sqlSession.selectList("com.board.web.replyMapper.getReplyList",bid);
	}

	@Override
	public int saveReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.board.web.replyMapper.saveReply",vo);
	}

	@Override
	public int updateReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("com.board.web.replyMapper.updateReply",vo);
	}

	@Override
	public int deleteReply(int rid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("com.board.web.replyMapper.deleteReply",rid);
	}

}
