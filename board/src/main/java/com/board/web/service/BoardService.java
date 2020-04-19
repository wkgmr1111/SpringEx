package com.board.web.service;

import java.util.List;
import java.util.Map;

import com.board.web.common.Pagination;
import com.board.web.common.Search;
import com.board.web.model.BoardVO;
import com.board.web.model.ReplyVO;

public interface BoardService {

	public List<BoardVO> getBoardList(Search search) throws Exception;
    
	public void  insertBoard(BoardVO vo) throws Exception;
	
	public BoardVO getBoardContent(int bid)throws Exception;
	
	public void updateBoard(BoardVO vo)throws Exception;
	
	public void deleteBoard(int bid)throws Exception;
	
	//총 개시글 수 확인
	public int getBoardListCnt(Search search)throws Exception;
	
	///댓글 관련
	
	public List<ReplyVO> getReplyList(int bid)throws Exception;
	
	public int saveReply(ReplyVO vo) throws Exception;
	
	public int updateReply(ReplyVO vo) throws Exception;
	
	public int deleteReply(int rid)throws Exception;
}
