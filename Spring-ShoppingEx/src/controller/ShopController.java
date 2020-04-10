package controller;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.CartListVO;
import model.CartVO;
import model.GoodsInfoVO;
import model.MemberVO;
import model.OrderDetailVO;
import model.OrderListVO;
import model.OrderVO;
import model.ReplyListVO;
import model.ReplyVO;
import service.ShopService;

@Controller
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	ShopService service;

	// 카테고리별 상품 리스트
	@RequestMapping(value = "slist.do", method = RequestMethod.GET)
	public void getList(@RequestParam("cateCode") int cateCode, @RequestParam("l") int level, Model model)
			throws Exception {
		logger.info("get llist");

		List<GoodsInfoVO> list = null;
		list = service.list(cateCode, level);
		System.out.println(level + "컨트롤러");
		System.out.println(cateCode + "컨트롤러");
		model.addAttribute("slist", list);

	}

	// 상품조회
	@RequestMapping(value = "sview.do", method = RequestMethod.GET)
	public void getViewInfo(@RequestParam("n") int gdsNum, Model model) {

		logger.info("getView");
		GoodsInfoVO view = service.goodsView(gdsNum); //
		model.addAttribute("sview", view);
		
		  
	}


	// 상품조회 -소감(댓긁)작성
	/*
	 * @RequestMapping(value = "sview.do", method = RequestMethod.POST) public
	 * String registReply(ReplyVO reply, HttpSession session) {
	 * logger.info("Reply!!"); MemberVO member = (MemberVO)
	 * session.getAttribute("member"); reply.setUserId(member.getUserId());
	 * service.registReply(reply);
	 * 
	 * return "redirect:sview.do?n=" + reply.getGdsNum(); }
	 */
	
	
	//상품소감 댓글 목록(비동기식)
//    @ResponseBody
//	@RequestMapping(value ="rList.do", method = RequestMethod.GET)
//	public ModelAndView getReplyList(@RequestParam("n") int gdsNum){
//    	 ModelAndView mave = new ModelAndView();
//    	 System.out.println("들어오사니?");
//		logger.info("get reply lIST");
//		List<ReplyListVO> reply = service.replyList(gdsNum);
//		mave.addObject("reply",reply);
//		mave.setViewName("sview");
//		
//		
//		return mave;
//		
//	}
	@ResponseBody
	@RequestMapping(value = "registReply.do", method = RequestMethod.POST)
    public void registReply( ReplyVO vo, HttpSession session) throws Exception{
		logger.info("regist reply");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		vo.setUserId(member.getUserId());
		service.registReply(vo);
	}
	
	// 상품 소감(댓글) 목록
	@ResponseBody
	@RequestMapping(value = "replyList.do", method = RequestMethod.GET)
	public List<ReplyListVO> getReplyList(@RequestParam("n") int gdsNum) throws Exception {
	 logger.info("get reply list");
	   
	 List<ReplyListVO> reply =  service.replyList(gdsNum);
	 
	 return reply;
	} 
	// 소감(댓글) 삭제
	
	@ResponseBody
	@RequestMapping(value = "deleteReply.do" , method = RequestMethod.POST )
	public int deleteReply(ReplyVO reply, HttpSession session) {
		logger.info("post delete");
		
		int result =0;
		MemberVO member =(MemberVO)session.getAttribute("member");
		String userId = service.idCheck(reply.getRepNum()); 
		
		if(member.getUserId().equals(userId)) {
			reply.setUserId(member.getUserId());
			service.deleteReply(reply);
			
			result = 1;
		}
		return result;
		
	}
	//댓글수정
	@ResponseBody
	@RequestMapping(value = "updateReply.do", method = RequestMethod.POST)
	public int modifyReply(ReplyVO reply, HttpSession session) {
		logger.info("reply update");
		int result = 0 ;
		
		MemberVO member =(MemberVO)session.getAttribute("member");
		String userId =service.idCheck(reply.getRepNum());
		
		if(member.getUserId().equals(userId)) {
			reply.setUserId(member.getUserId());
			service.updateReply(reply);
			result = 1;
		}
		
		return result;
	}
	
	//장바구니 추가
	@ResponseBody
	@RequestMapping(value = "add.do" , method = RequestMethod.POST)
	public int addCart(CartVO cart, HttpSession session) {
		int result = 0 ;
		 System.out.println("add.do들어왓니?");
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member != null) {
			
			cart.setUserId(member.getUserId());
			service.addCart(cart);
			result = 1;
		}else {
			System.out.println("아디없음..");
		}
          return result;
	}
	//장바구니 리스트
	@RequestMapping(value = "cartList.do" ,method = RequestMethod.GET)
	public void getCartList(HttpSession session,Model model) {
		logger.info("cartList");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		String userId = member.getUserId();
		
		List<CartListVO> cartList = service.cartList(userId);
		
		model.addAttribute("cartList", cartList);
	}
	// 장바구니 삭제
	@ResponseBody
	@RequestMapping(value = "deleteCart.do" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public int deleteCart(HttpServletRequest request, HttpSession session,@RequestParam(value = "chBox[]")List<String>chArr, CartVO cart) throws Exception {
		// ajax 에서 전송받는 배열 chbox를 리스트형 변수 chArr로 받은뒤  for문을 이용해 가지고있는 값의 갯수를 반복
		// 변수 result를 이용해 로그인이 안되었거나 세션만료로 자동 로그아웃이 된 경우 작동하지않기위함
		logger.info("deleteCart");
		
		/*
		 * List<Integer> list =
		 * Stream.of(s.split(", ")).map(Integer::valueOf).collect(Collectors.toList());
		 * System.out.println(list);
		 */
		//문제점 : List 배열을 캐스팅 불가..numberformatexception error String [] 배열캐스팅안됨
		//값은 출력되었지만 안됨
	
	   
		/*
		 * int ad = Integer.parseInt(chArr.get(0)); System.out.println(ad);
		 */
        System.out.println(chArr);
		MemberVO member = (MemberVO) session.getAttribute("member");
		String userId = member.getUserId();//아이디가져오기
		int result =0;
		int cartNum =0;
		 
	try {
		
		if(member != null) {
			cart.setUserId(userId);
					
			for(String a :chArr) {
		     
				cartNum = Integer.parseInt(a);
			    cart.setCartNum(cartNum);
			    
				System.out.println(cartNum);
				service.deleteCart(cart);
			}
			result = 1;
			
		}
	} catch (NumberFormatException E) {
		E.printStackTrace();
	}
	return result;
		
	}
	
	//주문
	@RequestMapping(value = "cartList.do" , method = RequestMethod.POST)
	public String order( HttpSession session, OrderVO order, OrderDetailVO details) {
		  logger.info("order");
		  
		  
		  MemberVO member =(MemberVO)session.getAttribute("member");
		  String userId = member.getUserId();
		  //달력 메서드를 사용하여 연/월/일 을 추출하고 6자리의 랜덤숫자로 만들어진 subNum을 더하여 날짜 랜덤숫자로 이루어진
		  //최대한 중복되지않는 고유한 문자열를 생성 //uuid사용가능
		  
		  Calendar cal = Calendar.getInstance();
		  int year = cal.get(Calendar.YEAR);
		  String ym =year + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		  String ymd = ym + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		  String subNum ="";
		  
		  for(int i=0; i<=6; i++) {
			  subNum +=(int)(Math.random() *10);
			  
		  }
		  String orderId = ymd +"_"+subNum;
		  
		  order.setOrderId(orderId);
		  order.setUserId(userId);
		  service.orderInfo(order);
		  
		  details.setOrderId(orderId);
		  service.orderDetails(details);
		  service.cartAllDelete(userId);
		  return "redirect:orderList.do";
	}
	@RequestMapping(value = "orderList.do", method = RequestMethod.GET)
	public void getOrderList(OrderVO order,HttpSession session,Model model) {
		logger.info("get orderList");
	
	MemberVO member =(MemberVO)session.getAttribute("member");
	String userId = member.getUserId();
	
	order.setUserId(userId);
	
	List<OrderVO> orderList = service.orderList(userId);
	model.addAttribute("olist",orderList);
	
	}
	@RequestMapping(value = "orderView.do", method = RequestMethod.GET)
	public void getOrderView(HttpSession session, @RequestParam("n")String orderId, OrderVO order,Model model) {
         logger.info("get orderVIEW");
         
         MemberVO member =(MemberVO) session.getAttribute("member");
         String userId = member.getUserId();
         
         order.setUserId(userId);
         order.setOrderId(orderId);
         
         List<OrderListVO> orderView = service.orderView(order);
         
         model.addAttribute("orderView",orderView);
	}
	
	
	
	
}