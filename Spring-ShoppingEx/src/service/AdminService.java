package service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminDAO;
import model.CategoryVO;
import model.GoodsInfoVO;
import model.GoodsVO;
import model.OrderListVO;
import model.OrderVO;
import model.ReplyListVO;

@Service
public class AdminService {
    
	@Autowired
	AdminDAO dao;
	
	//카테고리
	
	public List<CategoryVO> category(){
		return dao.category();
	}
	
	// 상품등록
	public void register(GoodsVO vo) {
		dao.register(vo);
	}
	//상품목록
	
	public List<GoodsInfoVO> goodslist(){
		return dao.goodslist();
	}
	/*
	 * //상품상세정보 public GoodsVO goodsView(int gdsNum) { return dao.goodsView(gdsNum);
	 * 
	 * }
	 */
	//상품상세정보 +카테고리 조인
	public GoodsInfoVO goodsView(int gdsNum) {
		return dao.goodsView(gdsNum);
		
	}
	//상품수정
	public void goodsModify(GoodsVO vo) {
		dao.GoodsModify(vo);
	}
	//상품삭제
	public void goodsDelete(int gdsNum) {
		dao.GoodsDelete(gdsNum);
	}
	//모든 유저 주문목록
	public List<OrderVO> AdminList(){
		return dao.AdminList();
	}
	
	//특정 주문목록
	public List<OrderListVO> AdminView(OrderVO vo){
		return dao.AdminView(vo);
		
	}
	//배송상태변경
	public void delivery(OrderVO vo) {
		dao.delivery(vo);
	}
	//상품수량변경(배송완료)
	public void changeStock(GoodsVO vo) {
		dao.changeStock(vo);
	}
	//모든 댓글 리스트
	public List<ReplyListVO> allReply(){
		return dao.allReply();
	}
	//모든 댓글 삭제(관리)
	public void deleteReply(int repNum) {
		dao.deleteReply(repNum);
	}
}
