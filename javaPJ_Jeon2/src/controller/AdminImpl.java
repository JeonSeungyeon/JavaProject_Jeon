package controller;

import java.util.Scanner;

import model.LP;

public class AdminImpl implements Admin {
	
	Scanner scan = new Scanner(System.in);
	LP adminLP = LP.getInstance();
	
	// =================================================================== 
	// 멤버변수
	private int LPKey;	// LP 코드를 입력 받기 위한 변수
	
	private String title;
	private String band;
	private int price;
	private int count;
	private int total;
	
	// =================================================================== 
	// 싱글톤
	
	private AdminImpl() {}
	
	private static AdminImpl admin = new AdminImpl();
	
	public static AdminImpl getInstance() {
		if(admin == null) {
			return admin = new AdminImpl();
		}
		return admin;
	}
	
	// =================================================================== 
	// 인터페이스 구현
	// 관리자 메뉴 - 1.재고관리
	
	@Override
	public void productList() {
		System.out.println("================= 상품 목록 ==================");
		System.out.println(" 번호	제목	밴드	가격	수량");
		adminLP.entryMap(1);
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
		LP newLP =  new LP(title, band, price, count);
		int randomNumber = (int) (Math.random() * 1000 + 1000);
		// randomNumber이 기존 코드와 같을 경우 처리하기		
		adminLP.addMap(randomNumber, newLP);
	}

	@Override
	public void productUpdate() {
		productList();
		System.out.print("수정하려는 LP의 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {

		} else {
			if(adminLP.containsKeyMap(LPKey, 1)) {
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
				LP update =  new LP(title, band, price, count);
				adminLP.addMap(LPKey, update);
			} else {
				System.out.println("입력하신 LP가 없습니다.");
			}
		}
		
	}

	@Override
	public void productRemove() {
		productList();
		System.out.print("삭제하려는 LP의 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {
			
		} else {
			if(adminLP.containsKeyMap(LPKey, 1)) {
				System.out.println("================= LP 삭제 ==================");
				System.out.println("	   " + LPKey +"번 LP가 삭제되었습니다.");
				System.out.println("==========================================");
				adminLP.removeMap(LPKey, 1);
			} else {
				System.out.println("입력하신 LP가 없습니다.");
			}
		}
	}

	// =================================================================== 
	// 관리자 메뉴 - 2.주문관리
	
	@Override
	public void orderSelect() {
		System.out.println("================ 구매 요청 목록 ================");
		System.out.println(" 번호	제목	밴드	가격	수량");
		System.out.println("==========================================");
		adminLP.entryMap(3);
	}

	@Override
	public void orderConfirm() {
		orderSelect();
		System.out.print("구매 승인할 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {
			
		} else {
			if(adminLP.containsKeyMap(LPKey, 3)) {
				System.out.println("==========================================");
				System.out.println("	             결제 승인 되었습니다.");
				System.out.println("==========================================");
				adminLP.addPurchaseMap(LPKey);
				adminLP.removeMap(LPKey, 3);
				// 결산 값 증가
				int income = adminLP.totalIncrease(LPKey);
				total += income;
			} else {
				System.out.println("입력하신 LP가 없습니다.");
			}
		}
	}

	@Override
	public void orderCancel() {
		System.out.println("================ 환불 요청 목록 ================");
		System.out.println(" 번호	제목	밴드	가격	수량");
		System.out.println("==========================================");
		adminLP.entryMap(5);
		System.out.print("구매 승인할 코드를 입력하세요. [이전:0] : ");
		LPKey = scan.nextInt();
		if(LPKey == 0) {
			
		} else {
			if(adminLP.containsKeyMap(LPKey, 5)) {
				adminLP.removeMap(LPKey, 5);
				System.out.println("==========================================");
				System.out.println("	             환불 처리 되었습니다.");
				System.out.println("==========================================");
				// 결산 값 감소
				int refundFee = adminLP.totalIncrease(LPKey);
				total -= refundFee;
			} else {
				System.out.println("입력하신 LP가 없습니다.");
			}
		}
		
	}

	@Override
	public void saleTotal() {
		System.out.println("결산 : " + total);
	}
	
}
