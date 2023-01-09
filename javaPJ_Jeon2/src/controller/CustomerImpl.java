package controller;

import java.util.Scanner;
import model.CD;

public class CustomerImpl implements Customer {
	
	AdminImpl admin = AdminImpl.getInstance();
	CD customerCD = CD.getInstance();
	
	Scanner scan = new Scanner(System.in);
	
	// =================================================================== 
	// 멤버변수
	private int CDKey;
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
		System.out.println(" 번호	제목	가수	가격	수량");
		customerCD.showMap(CART_MAP);
		System.out.println("==========================================");
	}

	@Override
	public void cartAdd() {
		do {
			admin.productList();
			System.out.print("장바구니에 담을 CD의 코드를 입력하세요. [이전:0] : ");
			CDKey = scan.nextInt();
			
			if(CDKey == 0) {
				break;
			} else {
				if(customerCD.containsKeyMap(CDKey, CD_MAP)) {
					System.out.print("수량을 입력하세요 : ");
					cartCount = scan.nextInt();
					System.out.println("==========================================");
					System.out.println("	             장바구니에 담겼습니다.");
					System.out.println("==========================================");
					customerCD.addCartMap(CDKey, cartCount);
				} else {
					System.out.println("입력하신 CD가 없습니다.");
				}
			}
		} while(true);
	}

	@Override
	public void cartRemove() {
		cartList();
		System.out.print("삭제하려는 CD의 코드를 입력하세요. [이전:0] : ");
		CDKey = scan.nextInt();
		if(CDKey == 0) {
			
		} else {
			if(customerCD.containsKeyMap(CDKey, CART_MAP)) {
				System.out.println("================= CD 삭제 ==================");
				System.out.println("	           목록에서 삭제 되었습니다.");
				System.out.println("==========================================");
				customerCD.removeMap(CDKey, CART_MAP);
			} else {
				System.out.println("입력하신 CD가 없습니다.");
			}
		}
	}

	@Override
	public void cartBuy() {
		cartList();
		System.out.print("구매할 CD의 코드를 입력하세요. [이전:0] : ");
		CDKey = scan.nextInt();
		if(CDKey == 0) {

		} else {
			if(customerCD.containsKeyMap(CDKey, CART_MAP)) {
				System.out.println("==========================================");
				System.out.println("	             구매 요청 되었습니다.");
				System.out.println("==========================================");
				customerCD.addOrderMap(CDKey);
				customerCD.removeMap(CDKey, CART_MAP);
			} else {
				System.out.println("입력하신 CD가 없습니다.");
			}
		}
	}
	
	// =================================================================== 
	// 고객메뉴 - 2.구매

	@Override
	public void nowBuy() {
		admin.productList();
		System.out.print("구매할 CD의 코드를 입력하세요. [이전:0] : ");
		CDKey = scan.nextInt();
		if(CDKey == 0) {

		} else {
			System.out.print("수량을 입력하세요 : ");
			cartCount = scan.nextInt();
			if(customerCD.containsKeyMap(CDKey, CD_MAP)) {
				System.out.println("==========================================");
				System.out.println("	             구매 요청 되었습니다.");
				System.out.println("==========================================");
				customerCD.buyNow(CDKey, cartCount);
			} else {
				System.out.println("입력하신 CD가 없습니다.");
			}
		}
	}

	// =================================================================== 
	// 고객메뉴 - 3.환불
	@Override
	public void refund() {
		System.out.println("================ 구매 완료 목록 ================");
		System.out.println(" 번호	제목	가수	가격	수량");
		customerCD.showMap(PURCHASE_MAP);
		System.out.println("==========================================");
		System.out.print("환불 요청할 CD의 코드를 입력하세요. [이전:0] : ");
		CDKey = scan.nextInt();
		if(CDKey == 0) {
			
		} else {
			if(customerCD.containsKeyMap(CDKey, PURCHASE_MAP)) {
				System.out.println("==========================================");
				System.out.println("	             환불 요청 되었습니다.");
				System.out.println("==========================================");
				customerCD.addRefundMap(CDKey);
				customerCD.removeMap(CDKey, PURCHASE_MAP);
			} else {
				System.out.println("입력하신 CD가 없습니다.");
			}
			
		}
	}
	
}
