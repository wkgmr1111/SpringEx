package com.board.web.dao;

import java.util.List;

import com.board.web.common.Search;
import com.board.web.model.BoardVO;
import com.board.web.model.ReplyVO;

public interface BoardDao {

   public List<BoardVO> getBoardList(Search search) throws Exception;

	

	public BoardVO getBoardContent(int bid) throws Exception;

	

	public int insertBoard(BoardVO boardVO) throws Exception;

	

	public int updateBoard(BoardVO boardVO) throws Exception;

	

	public int deleteBoard(int bid) throws Exception;

	

	public int updateViewCnt(int bid) throws Exception;
    
	//총 개시글 개수확인
	public int getBoardListCnt(Search search) throws Exception;
	
    ///댓글리스트
	
	public List<ReplyVO> getReplyList(int bid) throws Exception;
	
	public int saveReply(ReplyVO vo) throws Exception;
	
	public int updateReply(ReplyVO vo) throws Exception;
	
	public int deleteReply(int rid) throws Exception;


}
