package controller;

public interface Customer {
	
	// CD 클래스 switchMap
	static final int CD_MAP = 1;
	static final int CART_MAP = 2;
	static final int ORDER_MAP = 3;	
	static final int PURCHASE_MAP = 4;	
	static final int REFUND_MAP = 5;
	
	public static final String ID = "customer";
	public static final String PASSWORD = "customer";
	
	// 고객 - 장바구니
	public void cartList();		// 장바구니 목록
	public void cartAdd();		// 장바구니 추가
	public void cartRemove();	// 장바구니 삭제
	public void cartBuy();		// 장바구니 구매
	
	// 고객 - 바로구매
	public void nowBuy();		// 바로구매
	
	// 고객 - 환불
	public void refund();		// 결산
}
