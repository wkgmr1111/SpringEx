package com.board.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.web.model.ReplyVO;
import com.board.web.service.BoardService;

@RestController
@RequestMapping(value="/restBoard")
public class RestBoardController {

  
    
	  private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	  
	  @Inject
	  private BoardService service;
	  
	  @RequestMapping(value = "/getReplyList" , method = RequestMethod.GET)
	  public List<ReplyVO> getReplyList(@RequestParam("bid") int bid)throws Exception{
		  return service.getReplyList(bid);
	  }
	  @RequestMapping(value = "/saveReply", method = RequestMethod.POST)
	  public Map<String, Object> saveReply(@RequestBody ReplyVO replyVO)throws Exception{
		  Map<String, Object> result = new HashMap<String, Object>();
		  
		  try {
		      service.saveReply(replyVO);
		      result.put("status", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
		}
		return result;
		  
	  }
	  @RequestMapping(value = "/updateReply", method = {RequestMethod.POST, RequestMethod.GET })
	  public Map<String, Object> updateReply(@RequestBody ReplyVO replyVO)throws Exception{
		  Map<String, Object> result = new HashMap<String, Object>();
		  
		  try {
			service.updateReply(replyVO);
			result.put("status", "ok");
		} catch (Exception e) {
		      e.printStackTrace();
		      result.put("status", "error");
		}
		  return result;
	  }
	  @RequestMapping(value = "/deleteReply", method = RequestMethod.GET)
	  public Map<String, Object> deleteReply(@RequestParam("rid")int rid)throws Exception{
		  Map<String, Object> result = new HashMap<String, Object>();
		  
		  try {
			service.deleteReply(rid);
			result.put("status", "ok");
		} catch (Exception e) {
			result.put("status", "error");
		}
		  return result;
	  }
}
