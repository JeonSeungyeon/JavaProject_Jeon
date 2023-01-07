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
	int count;
	
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
			// 수량을 넘겨줘야하는 때가 있을 거 같음 아마 관리자 주문관리에서
			// 그리고 지금 당장 바로 아래에서도 CartMap에 수량 넘겨줘야 함
			if(LPKey == 0) {
				break;
				// 장바구니 메뉴로 넘어가는 거 구현해야함
			} else {
				if(customerLp.containsKeyMap(LPKey, 1)) {
					System.out.print("수량을 입력하세요 : ");
					count = scan.nextInt();
					System.out.println("==========================================");
					System.out.println("	             장바구니에 담겼습니다.");
					System.out.println("==========================================");
					LP newLP =  new LP(customerLp.getTitle(), customerLp.getBand(), customerLp.getPrice(), count);
					customerLp.addMap(LPKey, newLP, 2);
					// CartMap에 추가 해야 하는데 번호 제목 밴드는 LPMap과 같지만 가격과 수량은 다르게 해야함
					// Cart 클래스를 따로 만들어야 하나?
				} else {
					System.out.println("입력하신 LP가 없습니다.");
				}
			}
		} while(true);
	}

	@Override
	public void cartRemove() {
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
		System.out.print("구매할 LP의 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {
			// 장바구니 메뉴로 넘어가는 거 구현해야함
		} else {
			if(customerLp.containsKeyMap(LPKey, 1)) {
				System.out.println("================= LP 삭제 ==================");
				System.out.println("	             구매 요청 되었습니다.");
				System.out.println("==========================================");
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
		// TODO Auto-generated method stub
		
	}

	// =================================================================== 
	// 고객메뉴 - 3.환불
	@Override
	public void refund() {
		// TODO Auto-generated method stub
		
	}
	
	// 회원 가입
//	public void addCustomerMap(String id, String pw) {
//		customerMap.put(id, pw);
//	}

}
