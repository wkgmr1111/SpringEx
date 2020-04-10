package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ShopDAO;
import model.CartListVO;
import model.CartVO;
import model.GoodsInfoVO;
import model.OrderDetailVO;
import model.OrderListVO;
import model.OrderVO;
import model.ReplyListVO;
import model.ReplyVO;

@Service
public class ShopService {
	
    @Autowired
	ShopDAO dao;
    //카테고리 상품조회
    public List<GoodsInfoVO> list(int cateCode, int level){
    	
    	int cateCodeRef = 0;
    	
    	
     	if(level == 1 && cateCode == 100 || cateCode ==200 || cateCode ==300) { //1차분류
    		cateCodeRef =cateCode;
    		System.out.println(cateCodeRef+"서비스");
    		System.out.println(cateCode+"서비스");
    		
    		return dao.list_1(cateCode,cateCodeRef);
    	
    	}else { //2차분류
    		System.out.println("2차로?");
    		return dao.list_2(cateCode);
    	}
    }
   //상품조회 + 카테고리조인
    public GoodsInfoVO goodsView(int gdsNum) {
		return dao.goodsView(gdsNum);
    	
    }
   //상품댓글,
    public void registReply(ReplyVO reply) {
    	dao.registReply(reply);
    }
    //상품 댓글 리스트
    public List<ReplyListVO> replyList(int gdsNum){
    	return dao.replyList(gdsNum);
    }
    //상품 댓글 삭제
    public void deleteReply(ReplyVO vo) {
    	dao.deleteReply(vo);
    }
    //상품 댓글 아이디 체크
    public String idCheck(int repNum) {
    	return dao.idCheck(repNum);
    }
	//댓글 수정
    public void updateReply(ReplyVO vo) {
    	dao.modifyReply(vo);
    }
    //장바구니추가
    public void addCart(CartVO vo) {
    	dao.addCart(vo);
    }
    //장바구니리스트
    public List<CartListVO> cartList(String userId){
    	return dao.cartList(userId);
    }
    //장바구니삭제
    public void deleteCart(CartVO vo) {
    	System.out.println("ㅇ?");
    	dao.deleteCart(vo);
    }
    //주문 정보
    public void orderInfo(OrderVO vo) {
    	dao.orderInfo(vo);
    }
    //주문 상세정보
    public void orderDetails(OrderDetailVO vo) {
    	dao.orderDetails(vo);
    }
    //장바구니 삭제
    public void cartAllDelete(String userId) {
    	dao.cartAllDelete(userId);
    }
    //유저가 주문한 목록 보여주기
    public List<OrderVO> orderList(String userId){
    	return dao.orderList(userId);
    }
    //특정 주문목록
    public List<OrderListVO> orderView(OrderVO vo){
    	return dao.orderView(vo);
    }
}
