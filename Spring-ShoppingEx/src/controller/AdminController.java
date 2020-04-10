package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.CategoryVO;
import model.GoodsInfoVO;
import model.GoodsVO;
import model.OrderListVO;
import model.OrderVO;
import model.ReplyListVO;
import net.sf.json.JSONArray;

import service.AdminService;

@Controller

public class AdminController {

	private final static Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	AdminService service;
	
	/* @Resource(name="uploadPath") */
	@Autowired @Qualifier("uploadPath")
	 String uploadPath;
	//관리자화면
	@RequestMapping(value = "index.do" , method = RequestMethod.GET)
	public void admin() {
		logger.info("Admin page");
		
	}
	//상품등록
	@RequestMapping(value = "register.do" , method = RequestMethod.GET)
	public void getGoodsRegister(Model model){
		System.out.println("아닝..");
		 
		logger.info("get goods regiter");
		List<CategoryVO> category =null;
		category = service.category();
		System.out.println(category);
	    model.addAttribute("category", JSONArray.fromObject(category));
			
		};
		@RequestMapping(value = "regist.do" , method = RequestMethod.POST)
		public String postGoodRegister(GoodsVO vo, @RequestParam("file") MultipartFile file) throws IOException {
			String imgUploadPath = uploadPath + File.separator + "imgUpload";
			String ymdPath = UploadController.calcPath(imgUploadPath);
			String fileName = null;
            //파일 여부따른 에러는 같은경우 파일자체를 비교하는거보다 파일이름을 기준으로 조건문을 해야함.
			if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			 fileName = UploadController.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
			 vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
			 vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
			} else {
			 fileName = File.separator+ "images" + File.separator + "none.png";
			 vo.setGdsImg(fileName);
			 vo.setGdsThumbImg(fileName);
			}

			
			
			
			service.register(vo);
			return "redirect:index.do";
		}
	//상품목록
	@RequestMapping(value = "list.do", method = RequestMethod.GET)
	public void getGoodsList(Model model) {
		logger.info("get goods list");
		
		List<GoodsInfoVO> list = service.goodslist();
		model.addAttribute("list",list);
	}
	//상품 상세정보
	@RequestMapping(value = "goodsinfo.do" , method = RequestMethod.GET)
	public void getGoodsView(@RequestParam("n") int gdsNum,Model model) {
		logger.info("goods info");
		GoodsInfoVO goods = service.goodsView(gdsNum);
		model.addAttribute("goods",goods);
		
	}
	
	// 상품수정
	
	@RequestMapping(value = "modify.do" , method = RequestMethod.GET)
	public void getGoodsModify(@RequestParam("n") int gdsNum,Model model) {
		logger.info("goods modify");
		
		GoodsInfoVO goods = service.goodsView(gdsNum);
		model.addAttribute("goods",goods);
		
		List<CategoryVO> category = null;
		category = service.category();
		
		model.addAttribute("category",JSONArray.fromObject(category));
		
	}
	// 상품 수정
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public String postGoodsModify(GoodsVO vo ,@RequestParam("file") MultipartFile file, HttpServletRequest req) throws IOException {
	 logger.info("post goods modify");
     //새로운 파일이 등록되어 있는지확인
	 if(file.getOriginalFilename() !=null && !file.getOriginalFilename().equals("") ) {
		 //기존파일을 삭제
		 new File(uploadPath + req.getParameter("gdsImg")).delete();
		 new File(uploadPath + req.getParameter("gdsThumbImg")).delete();
		 
		 //새로 첨부한 파일을 등록
		 
		 String imgUploadPath = uploadPath + File.separator +"imgUpload";
		 String ymdPath = UploadController.calcPath(imgUploadPath);
		 String fileName = UploadController.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(),ymdPath);
		 
		 //실제이미지가 담길 폴더경로
		 vo.setGdsImg(File.separator +"imgUpload" + ymdPath+ File.separator + fileName);
		 vo.setGdsThumbImg(File.separator +"imgUpload" + ymdPath+ File.separator + "s" + File.separator + "s_" + fileName);
		 
	 }else { //새로운 파일이 등록되지않았다면
		 //기존 이미지를 그대로 사용
		 vo.setGdsImg(req.getParameter("gdsImg"));
		 vo.setGdsThumbImg(req.getParameter("gdsThumbImg"));
		 
	 }
	 service.goodsModify(vo);
	 
	 return "redirect:index.do";
	}
	//상품삭제
	@RequestMapping(value = "delete.do" , method = RequestMethod.POST)
	public String deleteGoods(@RequestParam("n") int gdsNum) {
		logger.info("goods delete");
		service.goodsDelete(gdsNum);
		
		return "redirect:index.do";
	}
	
	//*ck에디터에서 파일업로드
	
	@RequestMapping(value = "ckUpload.do", method = RequestMethod.POST)
	public void postCKEditorImgUpload(HttpServletRequest req, HttpServletResponse res, @RequestParam MultipartFile upload) throws Exception {
	 logger.info("post CKEditor img upload");
	 
	 // 랜덤 문자 생성
	 UUID uid = UUID.randomUUID();
	 
	 OutputStream out = null;
	 PrintWriter printWriter = null;
	   
	 // 인코딩
	 res.setCharacterEncoding("utf-8");
	 res.setContentType("text/html;charset=utf-8");
	 
	 try {
	  
	  String fileName = upload.getOriginalFilename();// 파일 이름 가져오기
	  byte[] bytes = upload.getBytes();
	  
	  // 업로드 경로
	  String ckUploadPath = uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
	  
	  out = new FileOutputStream(new File(ckUploadPath));
	  out.write(bytes);
	  out.flush(); // out에 저장된 데이터를 전송하고 초기화
	  
	  String callback = req.getParameter("CKEditorFuncNum");
	  printWriter = res.getWriter();
	  String fileUrl =   "ckUpload/" + uid + "_" + fileName;// 작성화면
	  
	  // 업로드시 메시지 출력
			/*
			 * printWriter.println("<script type='text/javascript'>" +
			 * "window.parent.CKEDITOR.tools.callFunction(" + callback+",'"+
			 * fileUrl+"','이미지를 업로드하였습니다.')" +"</script>");
			 * 
			 * printWriter.flush();
			 */
			/*
			 * String jsonStr = "{\"members\":[" + "{\"name\":\"홍길동\"," +
			 * "\"email\":\"gildong@hong.com\"," + "\"age\":\"25\"" + "}," +
			 * "{\"name\":\"홍길서\"," + "\"email\":\"gilseo@hong.com\"," + "\"age\":\"23\"" +
			 * "}]}";
			 */
	  String json = "{\"uploaded\":\""+ 1 +"\","
			   +"\"filename\":\""+ fileName +"\","
			   +"\"url\":\""+ fileUrl +"\"}";
				   

			printWriter.write(json);
	 
	 } catch (IOException e) { e.printStackTrace();
	 } finally {
	  try {
	   if(out != null) { out.close(); }
	   if(printWriter != null) { printWriter.close(); }
	  } catch(IOException e) { e.printStackTrace(); }
	 }
	 return;
	
	}
	
	//주문목록
	@RequestMapping(value = "adminList.do" , method = RequestMethod.GET)
	public void AdminOrderList(Model model) {
		logger.info("admin orderList");
		
		List<OrderVO> orderList = service.AdminList();
		
		model.addAttribute("AdorderList", orderList);
	}
	
	//주문목록
	@RequestMapping(value = "adminView.do" , method = RequestMethod.GET)
	public void AdminOrderView(@RequestParam("n")String orderId,Model model,OrderVO order) {
		logger.info("admin orderView");
		
		order.setOrderId(orderId);
		List<OrderListVO> orderView = service.AdminView(order);
		model.addAttribute("AdorderView", orderView);
	}
	// 주문 상세 목록 - 상태 변경
	@RequestMapping(value = "adminView.do", method = RequestMethod.POST)
	public String delivery(OrderVO order) throws Exception {
	 logger.info("post order view");
	   
	service.delivery(order);
       //상품수량조절
	List<OrderListVO> adminView = service.AdminView(order);
	GoodsVO goods = new GoodsVO();
	
	for(OrderListVO i : adminView) {
		goods.setGdsNum(i.getGdsNum());
		goods.setGdsStock(i.getCartStock());
		service.changeStock(goods);
	}
	
	 return "redirect:adminView.do?n=" + order.getOrderId();
	}
	//모든 댓글 리스트
	@RequestMapping(value = "allReply.do" , method = RequestMethod.GET)
	public void getReplyList(Model model) {
		logger.info("admin Reply List");
		
		List<ReplyListVO> reply = service.allReply();
		model.addAttribute("reply",reply );
	}
	//댓글 삭제
	@RequestMapping(value = "allReply.do" , method = RequestMethod.POST)
	public String deleteReply(int repNum) {
		logger.info("admin delete reply");
		
		service.deleteReply(repNum);
		
		return "redirect:allReply.do";
	}
}
