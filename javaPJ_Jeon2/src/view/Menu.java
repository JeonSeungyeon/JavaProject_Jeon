package view;

public interface Menu {
	
	// 로그인 메뉴 구현
	public void login(); 			// 공통 메뉴
	public void customerLogin();	// 고객 메뉴
	public void adminLogin();		// 관리자 메뉴
	public void signUp();			// 회원가입 메뉴
	public void exit();				// 종료
	
	// 공통 기능
	public void LogOut();

	// 고객 메뉴
	public void customerMenu();	
	// 고객 메뉴 - 장바구니
	public void customerCart();
	
	//관리자 메뉴
	public void adminMenu();	
	// 관리자 메뉴 - 재고관리
	public void inventoryMenu();
	// 관리자 메뉴 - 주문관리
	public void orderMenu();
}
