package controller;

import java.util.Scanner;
import model.LP;

public class CustomerImpl implements Customer {
	
	AdminImpl admin = AdminImpl.getInstance();
	LP customerLp = LP.getInstance();
	
	Scanner scan = new Scanner(System.in);
	
	// =================================================================== 
	// 멤버변수
	private int LPKey;
	int cartCount;
	
	// 고객 맵
//	HashMap<String, String> customerMap = new HashMap<String, String>();
	
	// =================================================================== 
	// 싱글톤
	
	private CustomerImpl() {}
	
	private static CustomerImpl customer = new CustomerImpl();
	
	public static CustomerImpl getInstance() {
		if(customer == null) {
			return customer = new CustomerImpl();
		}
		return customer;
	}
	
	// =================================================================== 
	// 인터페이스 구현
	// 고객 메뉴 = 1.장바구니
	
	@Override
	public void cartList() {
		System.out.println("================ 장바구니 목록 ================");
		System.out.println(" 번호	제목	밴드	가격	수량");
		customerLp.entryMap(2);
		System.out.println("==========================================");
	}

	@Override
	public void cartAdd() {
		do {
			admin.productList();
			System.out.print("장바구니에 담을 책의 코드를 입력하세요. [이전:0] : ");
			LPKey = scan.nextInt();
			
			if(LPKey == 0) {
				break;
			} else {
				if(customerLp.containsKeyMap(LPKey, 1)) {
					System.out.print("수량을 입력하세요 : ");
					cartCount = scan.nextInt();
					System.out.println("==========================================");
					System.out.println("	             장바구니에 담겼습니다.");
					System.out.println("==========================================");
					customerLp.addCartMap(LPKey, cartCount);
				} else {
					System.out.println("입력하신 LP가 없습니다.");
				}
			}
		} while(true);
	}

	@Override
	public void cartRemove() {
		cartList();
		System.out.print("삭제하려는 LP의 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {
			
		} else {
			if(customerLp.containsKeyMap(LPKey, 1)) {
				System.out.println("================= LP 삭제 ==================");
				System.out.println("	           목록에서 삭제 되었습니다.");
				System.out.println("==========================================");
				customerLp.removeMap(LPKey, 2);
			} else {
				System.out.println("입력하신 LP가 없습니다.");
			}
		}
	}

	@Override
	public void cartBuy() {
		cartList();
		System.out.print("구매할 LP의 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {

		} else {
			if(customerLp.containsKeyMap(LPKey, 1)) {
				System.out.println("==========================================");
				System.out.println("	             구매 요청 되었습니다.");
				System.out.println("==========================================");
				customerLp.addOrderMap(LPKey);
				customerLp.removeMap(LPKey, 2);
			} else {
				System.out.println("입력하신 LP가 없습니다.");
			}
		}
	}
	
	// =================================================================== 
	// 고객메뉴 - 2.구매

	@Override
	public void nowBuy() {
		admin.productList();
		System.out.print("구매할 LP의 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {

		} else {
			System.out.print("수량을 입력하세요 : ");
			cartCount = scan.nextInt();
			if(customerLp.containsKeyMap(LPKey, 1)) {
				System.out.println("==========================================");
				System.out.println("	             구매 요청 되었습니다.");
				System.out.println("==========================================");
				customerLp.buyNow(LPKey, cartCount);
				customerLp.removeMap(LPKey, 2);
			} else {
				System.out.println("입력하신 LP가 없습니다.");
			}
		}
	}

	// =================================================================== 
	// 고객메뉴 - 3.환불
	@Override
	public void refund() {
		System.out.println("================ 구매 완료 목록 ================");
		System.out.println(" 번호	제목	밴드	가격	수량");
		customerLp.entryMap(4);
		System.out.println("==========================================");
		System.out.print("환불 요청할 책의 코드를 입력하세요. [이전:0] : ");
		System.out.println("==========================================");
		System.out.println("	             환불 요청 되었습니다.");
		System.out.println("==========================================");
		customerLp.addRefundMap(LPKey);
		customerLp.removeMap(LPKey, 3);
	}
	
}
