package controller;

public interface Admin {
	
	// CD 클래스 switchMap
	static final int CD_MAP = 1;
	static final int ORDER_MAP = 3;	
	static final int REFUND_MAP = 5;
	
	public static final String ID = "admin";
	public static final String PASSWORD = "admin";
	
	// 관리자 - 재고관리
	public void productList();	// 상품목록
	public void productAdd();	// 상품추가
	public void productUpdate();	// 상품수정
	public void productRemove();	// 상품삭제
	
	
	// 관리자 - 주문관리
	public void orderSelect();		// 주문 목록
	public void orderConfirm();		// 결제승인
	public void orderCancel();		// 결제취소
	
	// 관리자 - 결산
	public void saleTotal();		// 결산
	
}
