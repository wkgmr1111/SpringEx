package com.board.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.web.common.Pagination;
import com.board.web.common.Search;
import com.board.web.model.BoardVO;
import com.board.web.model.ReplyVO;
import com.board.web.service.BoardService;
import com.sun.org.apache.bcel.internal.generic.NEW;


@Controller
public class BoardController {

	@Inject
	private BoardService service;
	
	
	//페이징 계산 위해 pagination 클래스에 보내야할 파라미터에는 현재페이지와 페이지 범위 그리고 게시물 총 개수
	//현재 페이지와 범위는 화면에서 get 방식으로 넘겨받고 처음 접속할때는 번호나 범위가없기에 디폴트 값으로 받는다
	//게시글의 총 개수는 위에서 만든 메소드를 호출하여 얻어올수있다
	
	@RequestMapping(value = "board/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model,
			@RequestParam(required = false,defaultValue = "1")int page,
			@RequestParam(required = false,defaultValue = "1")int range,
			@RequestParam(required = false,defaultValue = "title") String searchType,
			@RequestParam(required = false)String keyword) throws Exception {
		
		
		
		for(int i=2; i<=9; i++) {
			  if(i %2 == 0) {
				  for(int j=1; j<=5; j++) {
					  System.out.println(i +" * "+ j + "=" + i * j );
					  
				  }
			  }else if(i %3 ==0){
				  for(int j=1; j<=7; j++) {
					  System.out.println(i +" * "+ j + "=" + i * j );
					  
				  }
			  }
			
		}
		
		
		Search search = new Search();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		
		//전체 게시글 개수
		int listCnt = service.getBoardListCnt(search);
		search.pageInfo(page, range, listCnt);
		//Pagination 객체 생성
		//Pagination pagination = new Pagination();
		//pagination.pageInfo(page, range, listCnt);
		
		model.addAttribute("pagination", search);
		model.addAttribute("boardList",service.getBoardList(search));
		
		return "board/index";
	}
	
	@RequestMapping("board/boardForm")
	public String boardForm(@ModelAttribute("BoardVO")BoardVO VO) {
		return "board/boardForm";
	}
	@RequestMapping(value = "board/saveBoard", method = RequestMethod.POST)
	public String saveBoard(@ModelAttribute("BoardVO") BoardVO vo
			, RedirectAttributes rttr, @RequestParam("mode") String mode)throws Exception {
		//RedirectAttributes 게시물 도배 방지를 위해 저장단계를 지나 글쓰기 폼으로 돌아가게됩니다.
		if(mode.equals("edit")) {
			service.updateBoard(vo);
		}else {
			
			service.insertBoard(vo);
		}
		
		
		return "redirect:/board/getBoardList";
		
	}
	@RequestMapping(value = "board/getBoardContent" , method = RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception {
		
	
		
		model.addAttribute("boardContent",service.getBoardContent(bid));
		model.addAttribute("replyVO", new ReplyVO());
		return "board/getBoardContent";
	}
	@RequestMapping(value = "board/editForm" , method = RequestMethod.GET)
	public String editForm(@RequestParam("bid")int bid,@RequestParam("mode")String mode, Model model)throws Exception {
		model.addAttribute("boardContent",service.getBoardContent(bid));
		model.addAttribute("mode",mode);
		model.addAttribute("BoardVO", new BoardVO());
		// 모델을 보내는이유 는 입력폼의 수정을 위해 사용하게되는데 입력폼과 연계 하기 위해
		
		return "board/boardForm";
	}
	@RequestMapping(value = "board/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(RedirectAttributes rttr, @RequestParam("bid") int bid) throws Exception {
		service.deleteBoard(bid);
		return "redirect:/board/getBoardList";
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String exceptionHandler(Model model, Exception e){

	
	model.addAttribute("exception", e);

	return "error/exception";

	}




}
