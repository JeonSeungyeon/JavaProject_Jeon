package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.LP;

public class AdminImpl implements Admin {
	
	Scanner scan = new Scanner(System.in);
	HashMap<Integer, LP> LPMap = new HashMap<Integer, LP>();
	LP lp;
	
	private int LPKey;	// LP 코드를 입력 받기 위한 변수
	
	private String title;
	private String band;
	private int price;
	private int count;
	
	// 싱글톤
	private AdminImpl() {}
	
	private static AdminImpl admin = new AdminImpl();
	
	public static AdminImpl getInstance() {
		if(admin == null) {
			return admin = new AdminImpl();
		}
		return admin;
	}
	
	// 인터페이스 구현
	// 재고관리
	@Override
	public void productList() {
		System.out.println("================= 상품 목록 ==================");
		System.out.println(" 번호	제목	밴드	가격	수량");
		for(Map.Entry<Integer, LP> entry : LPMap.entrySet()) {
			int key = entry.getKey();
			LP value = entry.getValue();
			System.out.println(key + "\t" + value);	
			//value값 그냥 주면 주소값으로 나옴. LP에서 toString 재정의 해야함
		}
		System.out.println("==========================================");
		// 재고관리 메뉴로 넘어가는 거 구현해야함
	}

	@Override
	public void productAdd() {
		System.out.println("================= 상품 등록 ==================");
		System.out.print("LP 제목: ");
		title = scan.next();
		System.out.print("밴드 이름 : ");
		band = scan.next();
		System.out.print("LP 가격 : ");
		price = scan.nextInt();
		System.out.print("LP 수량 : ");
		count = scan.nextInt();
		System.out.println("==========================================");
		System.out.println("	            상품이 등록되었습니다.");
		System.out.println("==========================================");
		lp =  new LP(title, band, price, count);
		LPMap.put((int) (Math.random() * 1000 + 1000), lp);
		// 재고관리 메뉴로 넘어가는 거 구현해야함
	}

	@Override
	public void productUpdate() {
		System.out.print("수정하려는 책의 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {
			// 재고관리 메뉴로 넘어가는 거 구현해야함
		} else {
			if(LPMap.containsKey(LPKey)) {
				System.out.println("================= LP 수정 ==================");
				System.out.print("LP 제목: ");
				title = scan.next();
				System.out.print("밴드 이름 : ");
				band = scan.next();
				System.out.print("LP 가격 : ");
				price = scan.nextInt();
				System.out.print("LP 수량 : ");
				count = scan.nextInt();
				System.out.println("==========================================");
				lp =  new LP(title, band, price, count);
				LPMap.put((int) (LPKey), lp);
			} else {
				System.out.println("입력하신 LP가 없습니다.");
			}
		}
		
	}

	@Override
	public void productRemove() {
		System.out.print("삭제하려는 책의 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {
			// 재고관리 메뉴로 넘어가는 거 구현해야함
		} else {
			if(LPMap.containsKey(LPKey)) {
				System.out.println("================= 도서 삭제 ==================");
				System.out.println("	   " + LPKey +"번 도서가 삭제되었습니다.");
				System.out.println("==========================================");
				LPMap.remove(LPKey);
			} else {
				System.out.println("입력하신 책이 없습니다.");
			}
		}
	}

	// 주문관리
	@Override
	public void orderSelect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderConfirm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saleTotal() {
		// TODO Auto-generated method stub
		
	}
	
}
