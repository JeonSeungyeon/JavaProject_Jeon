package controller;

import java.util.Scanner;

import model.CD;

public class AdminImpl implements Admin {
	
	Scanner scan = new Scanner(System.in);
	CD adminCD = CD.getInstance();
	
	// =================================================================== 
	// 멤버변수
	private int CDKey;	// CD코드를 입력할 변수
	
	private String title;
	private String singer;
	private int price;
	private int count;
	
	private int total;	// 결산
	
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
		System.out.println(" 번호	제목	가수	가격	수량");
		adminCD.showMap(CD_MAP);
		System.out.println("==========================================");
	}

	@Override
	public void productAdd() {
		System.out.println("================= 상품 등록 ==================");
		System.out.print("CD 제목: ");
		title = ExceptionHandler.readString();
		System.out.print("가수 이름 : ");
		singer = ExceptionHandler.readString();
		System.out.print("CD 가격 : ");
		price = ExceptionHandler.readInt();
		System.out.print("CD 수량 : ");
		count = ExceptionHandler.readInt();
		System.out.println("==========================================");
		System.out.println("	            상품이 등록되었습니다.");
		System.out.println("==========================================");
		CD newCD =  new CD(title, singer, price, count);
		int randomNumber = (int) (Math.random() * 1000 + 1000);
		adminCD.addCDMap(randomNumber, newCD);
	}

	@Override
	public void productUpdate() {
		while(true) {
			productList();
			System.out.print("수정하려는 CD의 코드를 입력하세요. [이전:0] : ");
			CDKey = ExceptionHandler.readInt();
			if(CDKey == 0) {
				break;
			} else {
				if(adminCD.containsKeyMap(CDKey, CD_MAP)) {
					System.out.println("================= CD 수정 ==================");
					System.out.print("CD 제목: ");
					title = ExceptionHandler.readString();
					System.out.print("가수 이름 : ");
					singer = ExceptionHandler.readString();
					System.out.print("CD 가격 : ");
					price = ExceptionHandler.readInt();
					System.out.print("CD 수량 : ");
					count = ExceptionHandler.readInt();
					System.out.println("==========================================");
					System.out.println("	            상품이 수정되었습니다.");
					System.out.println("==========================================");
					CD update =  new CD(title, singer, price, count);
					adminCD.addCDMap(CDKey, update);
				} else {
					System.out.println("입력하신 CD가 없습니다.");
				}
			}
		}
	}

	@Override
	public void productRemove() {
		while(true) {
			productList();
			System.out.print("삭제하려는 CD의 코드를 입력하세요. [이전:0] : ");
			CDKey = ExceptionHandler.readInt();
			if(CDKey == 0) {
				break;
			} else {
				if(adminCD.containsKeyMap(CDKey, CD_MAP)) {
					System.out.println("================= CD 삭제 ==================");
					System.out.println("	   " + CDKey +"번 CD가 삭제되었습니다.");
					System.out.println("==========================================");
					adminCD.removeMap(CDKey, CD_MAP);
				} else {
					System.out.println("입력하신 CD가 없습니다.");
				}
			}
		}
	}

	// =================================================================== 
	// 관리자 메뉴 - 2.주문관리
	
	// -------------------------------------------------------------------
	// 2.주문관리 - 결제승인
	
	@Override
	public void orderSelect() {
		System.out.println("================ 구매 요청 목록 ================");
		System.out.println(" 번호	제목	가수	가격	수량");
		System.out.println("==========================================");
		adminCD.showMap(ORDER_MAP);
	}

	@Override
	public void orderConfirm() {
		while(true) {
			orderSelect();
			System.out.print("구매 승인할 코드를 입력하세요. [이전:0] : ");
			CDKey = ExceptionHandler.readInt();
			if(CDKey == 0) {
				break;
			} else {
				if(adminCD.containsKeyMap(CDKey, ORDER_MAP)) {
					System.out.println("==========================================");
					System.out.println("	             결제 승인 되었습니다.");
					System.out.println("==========================================");
					adminCD.addPurchaseMap(CDKey);
					adminCD.removeMap(CDKey, ORDER_MAP);
					// 결산 값 증가
					int income = adminCD.returnIncome(CDKey);
					total += income;
					// 재고 감소
					adminCD.countIncrease(CDKey);
				} else {
					System.out.println("입력하신 CD가 없습니다.");
				}
			}
		}
	}

	// -------------------------------------------------------------------
	// 2.주문관리 - 환불 처리
	@Override
	public void orderCancel() {
		while(true) {
			System.out.println("================ 환불 요청 목록 ================");
			System.out.println(" 번호	제목	가수	가격	수량");
			System.out.println("==========================================");
			adminCD.showMap(REFUND_MAP);
			System.out.print("환불 처리할 코드를 입력하세요. [이전:0] : ");
			CDKey = ExceptionHandler.readInt();
			if(CDKey == 0) {
				break;
			} else {
				if(adminCD.containsKeyMap(CDKey, REFUND_MAP)) {
					System.out.println("==========================================");
					System.out.println("	             환불 처리 되었습니다.");
					System.out.println("==========================================");
					// 결산 값 감소
					int refundFee = adminCD.returnRefundFee(CDKey);
					total -= refundFee;
					// 재고 증가
					adminCD.countDecrease(CDKey);
					
					adminCD.removeMap(CDKey, REFUND_MAP);
				} else {
					System.out.println("입력하신 CD가 없습니다.");
				}
			}
		}
	}

	// -------------------------------------------------------------------
	// 2.주문관리 - 결산
	@Override
	public void saleTotal() {
		System.out.println("결산 : " + total);
	}
	
}
