package view;

import java.util.HashMap;
import java.util.Scanner;

import controller.Admin;
import controller.AdminImpl;
import controller.CustomerImpl;

public class MenuImpl implements Menu {
	
	// 싱글톤 접근
	CustomerImpl customer = CustomerImpl.getInstance();
	AdminImpl admin = AdminImpl.getInstance();
	
	Scanner scan = new Scanner(System.in);
	
	// 회원맵
	HashMap<String, String> customerMap = new HashMap<String, String>();
	
	// =================================================================== 
	// 멤버변수
	
	private int menu;	// 메뉴선택
	private String id;	// 아이디 입력
	private String pw;	// 비밀번호 입력
	
	// =================================================================== 
	// 로그인

	// 로그인 메뉴
	@Override
	public void login() {
		while(true) {
			System.out.println("────────────────── 로그인 ──────────────────");
			System.out.println("    1.고객        2.관리자        3.회원가입        4.종료        ");
			System.out.println("==========================================");
			System.out.print("메뉴번호를 입력하세요: ");
			menu = scan.nextInt();
			switch(menu) {
			case 1 : customerLogin();	break;
			case 2 : adminLogin();		break;
			case 3 : signUp();			break;
			case 4 : exit();			break;
			default : System.out.println("메뉴에 있는 숫자만 입력해 주세요.");
			}
		}
	}
	
	// -------------------------------------------------------------------
	// 고객 로그인
	@Override
	public void customerLogin() {
			System.out.print("고객 ID : ");
			id = scan.next();
			if(customerMap.containsKey(id)) {
				System.out.print("고객 PW : ");
				pw = scan.next();
				if(pw.equals(customerMap.get(id))) {
					System.out.println("==========================================");
					System.out.println("		  로그인 되었습니다.");
					System.out.println("==========================================");
					customerMenu();

				} else {
					System.out.println("비밀번호가 틀렸습니다.");
				}
			} else {
				System.out.println("아이디가 존재하지 않습니다.");
			}
	}
	
	// -------------------------------------------------------------------
	// 관리자 로그인
	@Override
	public void adminLogin() {
			System.out.print("관리자 ID : ");
			id = scan.next();
			if(id.equals(Admin.ID)) {
				System.out.print("관리자 PW : ");
				pw = scan.next();
				if(pw.equals(Admin.PASSWORD)) {
					System.out.println("==========================================");
					System.out.println("		  로그인 되었습니다.");
					System.out.println("==========================================");
					adminMenu();
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
				}
			} else {
				System.out.println("아이디가 틀렸습니다.");
			}
	}
	
	// =================================================================== 
	// 회원가입

	@Override
	public void signUp() {
		System.out.println("==========================================");
		System.out.println("		     회원가입");
		System.out.println("==========================================");
		System.out.print("ID : ");
		id = scan.next();
		System.out.print("PW : ");
		pw = scan.next();
		System.out.println("==========================================");
		System.out.println("		  회원가입 완료");
		System.out.println("==========================================");
		customerMap.put(id, pw);
		login();
		
	}

	// =================================================================== 
	// 종료
	
	@Override
	public void exit() {
		System.exit(0);
	}
	
	// =================================================================== 
	// 고객 메뉴

	@Override
	public void customerMenu() {
		while(true) {
			System.out.println("───────────────── 고객 메뉴  ─────────────────");
			System.out.println("    1.장바구니       2.구매        3.환불        4.로그아웃        ");
			System.out.println("==========================================");
			menu = scan.nextInt();
			switch(menu) {
				case 1 : customerCart();	break;
				case 2 : customer.nowBuy();	break;
				case 3 : customer.refund();	break;
				case 4 : LogOut();			break;
				default : System.out.println("메뉴에 있는 숫자만 입력해 주세요.");
			}		
		}
	}
	
	@Override
	public void customerCart() {
		while(true) {
			customer.cartList();
			System.out.println("───────────────── 장바구니  ─────────────────");
			System.out.println("    1.추가          2.삭제          3.구매          4.이전        ");
			System.out.println("─────────────────────────────────────────");
			menu = scan.nextInt();
			switch(menu) {
			case 1 : customer.cartAdd();	break;
			case 2 : customer.cartRemove();	break;
			case 3 : customer.cartBuy();	break;
			case 4 : customerMenu();		break;
			default : System.out.println("메뉴에 있는 숫자만 입력해 주세요.");
			}
		}
		
	}

	// =================================================================== 
	// 관리자 메뉴
	
	@Override
	public void adminMenu() {
		while(true) {
			System.out.println("───────────────── 관리자 메뉴  ────────────────");
			System.out.println("      1.재고관리        2.주문관리        3.로그아웃            ");
			System.out.println("==========================================");
			menu = scan.nextInt();
			switch(menu) {
				case 1 : inventoryMenu();	break;
				case 2 : orderMenu();		break;
				case 3 : LogOut();			break;
				default : System.out.println("메뉴에 있는 숫자만 입력해 주세요.");
			}
		}
	}

	@Override
	public void inventoryMenu() {
		while(true) {
			System.out.println("───────────────── 재고관리  ─────────────────");
			System.out.println("    1.목록      2.추가      3.수정      4.삭제      5.이전");
			System.out.println("==========================================");
			menu = scan.nextInt();
			switch(menu) {
				case 1 : admin.productList();	break;
				case 2 : admin.productAdd();	break;
				case 3 : admin.productUpdate();	break;
				case 4 : admin.productRemove();	break;
				case 5 : adminMenu();
				default : System.out.println("메뉴에 있는 숫자만 입력해 주세요.");
			}
		}
	}

	@Override
	public void orderMenu() {
		while(true) {
			System.out.println("───────────────── 주문관리  ─────────────────");
			System.out.println("  1.주문목록   2.결제승인   3.환불처리    4.결산    5.이전");
			System.out.println("==========================================");	
			menu = scan.nextInt();
			switch(menu) {
				case 1 : admin.orderSelect();	break;
				case 2 : admin.orderConfirm();	break;
				case 3 : admin.orderCancel();	break;
				case 4 : admin.saleTotal();		break;
				case 5 : adminMenu();
				default : System.out.println("메뉴에 있는 숫자만 입력해 주세요.");
			}
		}
	}

	// ===================================================================
	// 로그아웃
	
	@Override
	public void LogOut() {
		login();
	}


}
