package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import model.CartListVO;
import model.CartVO;
import model.GoodsInfoVO;
import model.OrderDetailVO;
import model.OrderListVO;
import model.OrderVO;
import model.ReplyListVO;
import model.ReplyVO;

public interface ShopDAO {

	//카테고리별 상품리스트 1차
	public List<GoodsInfoVO> list_1(@Param("cateCode") int cateCode, @Param("cateCodeRef")int cateCodeRef);
	//카테고리별 상품리스트 2차
    public List<GoodsInfoVO> list_2(int cateCode);
    
    //상품조회
    public GoodsInfoVO goodsView(int gdsNum);
    
    //상품 댓글기능
    public void registReply(ReplyVO reply);
    
    //상품 댓글 리스트
    public List<ReplyListVO> replyList(@PathVariable int gdsNum);
    
    //상품 댓글 삭제
    public void deleteReply(ReplyVO vo);
    
    //아이디 체크
    public String idCheck (int repNum);
    // 상품 댓글 수정
    public void modifyReply(ReplyVO vo);
    // 장바구니 추가기능
    public void addCart(CartVO vo);
    //카트리스트
    public List<CartListVO> cartList(String userId);
    //장바구니 삭제
    public void deleteCart(CartVO vo);
    
    //주문정보
    public void orderInfo(OrderVO vo);
    
    //주문상세정보
    public void orderDetails(OrderDetailVO vo);
    //장바구나 삭제
    public void cartAllDelete(String userId);
    
    //유저에게 주문모록보여주기
    public List<OrderVO> orderList(String userId);
    //특정 주문
    public List<OrderListVO> orderView(OrderVO vo);
}
