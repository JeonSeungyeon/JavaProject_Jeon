package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.LP;

//import java.util.HashMap;

public class CustomerImpl implements Customer {
	
	AdminImpl admin = AdminImpl.getInstance();
	Scanner scan = new Scanner(System.in);
	
	private int LPKey;
	int count;

	// 장바구니 목록에 쓰일 배열
	HashMap<Integer, LP> CartMap = new HashMap<Integer, LP>();

	// 고객 맵
//	HashMap<String, String> customerMap = new HashMap<String, String>();
	
	// 싱글톤
	private CustomerImpl() {}
	
	private static CustomerImpl customer = new CustomerImpl();
	
	public static CustomerImpl getInstance() {
		if(customer == null) {
			return customer = new CustomerImpl();
		}
		return customer;
	}
	
	// 인터페이스 구현
	@Override
	public void cartList() {
		System.out.println("================ 장바구니 목록 ================");
		System.out.println(" 번호	제목	밴드	가격	수량");
		for(Map.Entry<Integer, LP> entry : CartMap.entrySet()) {
			int key = entry.getKey();
			LP value = entry.getValue();
			System.out.println(key + "\t" + value);	
		}
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
				System.out.print("수량을 입력하세요 : ");
				count = scan.nextInt();
				if(admin.LPMap.containsKey(LPKey)) {
					System.out.println("==========================================");
					System.out.println("	             장바구니에 담겼습니다.");
					System.out.println("==========================================");
					// CartMap에 추가 해야 하는데 번호 제목 밴드는 LPMap과 같지만 가격과 수량은 다르게 해야함
					// HashMap을 index번 까지만 복사하는 방법이 있을까?
				} else {
					System.out.println("입력하신 LP이 없습니다.");
				}
			}
		} while(true);
	}

	@Override
	public void cartRemove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartBuy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nowBuy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refund() {
		// TODO Auto-generated method stub
		
	}
	
	// 회원 가입
//	public void addCustomerMap(String id, String pw) {
//		customerMap.put(id, pw);
//	}

}
