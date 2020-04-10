package dao;

import java.util.List;

import model.CategoryVO;
import model.GoodsInfoVO;
import model.GoodsVO;
import model.OrderListVO;
import model.OrderVO;
import model.ReplyListVO;

public interface AdminDAO {
    //카테고리
	public List<CategoryVO> category();
	
	//상품등록
	public void register(GoodsVO vo );
	//상풍목록	
	//public List<GoodsVO> goodslist();
    public List<GoodsInfoVO> goodslist();
	/*
	 * //상품 상세정보 public GoodsVO goodsView(int gdsNum);
	 */
	//상품조회 + 카테고리 조인
	public GoodsInfoVO goodsView(int gdsNum);
	//상품수정
	public void GoodsModify(GoodsVO vo);
	//상품삭제
	public void GoodsDelete(int gdsNum);
	
	//모든 유저 주문목록

	public List<OrderVO> AdminList();
	//특정 주문목록
	public List<OrderListVO> AdminView(OrderVO vo);
	//배송상태변경
	public void delivery(OrderVO vo);
	//상품수량조절(배송완료시)
	public void changeStock(GoodsVO vo);
		
	//모든 댓글 리스트
	public List<ReplyListVO> allReply();
	//댓글 삭제
	public void deleteReply(int repNum);
}
