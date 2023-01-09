package model;

import java.util.HashMap;
import java.util.Map;

// DTO(Data Transfer Object)
public class CD {

	// =================================================================== 
	// 멤버변수
	private String title;
	private String singer;
	private int price;
	private int count;
	
	private HashMap<Integer, CD> CDMap = new HashMap<Integer, CD>();
	private HashMap<Integer, CD> cartMap = new HashMap<Integer, CD>();
	private HashMap<Integer, CD> orderMap = new HashMap<Integer, CD>();
	private HashMap<Integer, CD> purchaseMap = new HashMap<Integer, CD>();
	private HashMap<Integer, CD> refundMap = new HashMap<Integer, CD>();
	
	// =================================================================== 
	// 싱글톤
	
	private CD() {}
	
	private static CD cd = new CD();
	
	public static CD getInstance() {
		if(cd == null) {
			return cd = new CD();
		}
		return cd;
	}
	
	// =================================================================== 
	// 생성자

	public CD(String title, String singer, int price, int count) {
		this.title = title;
		this.singer = singer;
		this.price = price;
		this.count = count;
	}
	
	// =================================================================== 
	// Getter, Setter - 한번도 안써서 일단 지워둠
	
	// =================================================================== 
	// Map 메서드
	
	// -------------------------------------------------------------------
	// 값 추가 메서드 모음
	
	// CDMap에 값 추가하는 메서드
	public void addMap(int CDKey, CD value) {
		CDMap.put(CDKey, value);
	}
	
	// cartMap에 값 추가하는 메서드
	public void addCartMap(int CDKey, int cartCount) {		
		CD value = CDMap.get(CDKey);
		CD cartValue = new CD(value.title, value.singer, 
				value.price * cartCount, cartCount);
		cartMap.put(CDKey, cartValue);			
	}
	
	// orderMap에 값 추가하는 메서드(장바구니)
	public void addOrderMap(int CDKey) {
		CD value = cartMap.get(CDKey);
		orderMap.put(CDKey, value);
	}
	
	// orderMap에 값 추가하는 메서드(바로구매)
	public void buyNow(int CDKey, int cartCount) {		
		CD value = CDMap.get(CDKey);
		CD cartValue = new CD(value.title, value.singer, 
				value.price * cartCount, cartCount);
		orderMap.put(CDKey, cartValue);			
	}
	
	// purchaseMap에 값 추가하는 메서드
	public void addPurchaseMap(int CDKey) {
		CD value = orderMap.get(CDKey);
		purchaseMap.put(CDKey, value);
	}
	
	// refundMap에 값 추가하는 메서드
	public void addRefundMap(int CDKey) {
		CD value = purchaseMap.get(CDKey);
		refundMap.put(CDKey, value);
	}
	
	// -------------------------------------------------------------------
	// 재고 감소
	public void countIncrease(int CDKey) {
		CD CDMapValue = CDMap.get(CDKey);			// CDMap 값 복사
		CD purchaseMapValue = purchaseMap.get(CDKey);	// purchaseMap 값 복사
		CD value = new CD(CDMapValue.title, CDMapValue.singer, 
				CDMapValue.price, CDMapValue.count - purchaseMapValue.count);
				// 나머지는 CDMap 그대로 사용하고 count만 계산해서 CDMap value 값에 넘김
		CDMap.put(CDKey, value);
	}
	
	// 재고 증가
	public void countDecrease(int CDKey) {
		CD CDMapValue = CDMap.get(CDKey);
		CD purchaseMapValue = refundMap.get(CDKey);
		CD value = new CD(CDMapValue.title, CDMapValue.singer, 
				CDMapValue.price, CDMapValue.count + purchaseMapValue.count);
		CDMap.put(CDKey, value);
	}
	
	// -------------------------------------------------------------------
	// Map에 값 가져오는 메서드
	public CD getMap(int CDkey, int switchMap) {
		switch(switchMap) {
			case 1 : return CDMap.get(CDkey);
			case 2 : return cartMap.get(CDkey);
			case 3 : return orderMap.get(CDkey);
			case 4 : return purchaseMap.get(CDkey);
			case 5 : return refundMap.get(CDkey);
		}
			return null;
    }
	
	// -------------------------------------------------------------------
	// 결산 증가
	public int returnIncome(int CDKey) {
		CD value = purchaseMap.get(CDKey);
		return value.price;
	}
	
	// 결산 감소
	public int returnRefundFee(int CDKey) {
		CD value = refundMap.get(CDKey);
		return value.price;
	}
	
	// -------------------------------------------------------------------
	// Map에 값이 존재하는지 확인하는 메서드
	public boolean containsKeyMap(int CDKey, int switchMap) {
		switch(switchMap) {
			case 1 : return CDMap.containsKey(CDKey);
			case 2 : return cartMap.containsKey(CDKey);
			case 3 : return orderMap.containsKey(CDKey);
			case 4 : return purchaseMap.containsKey(CDKey);
			case 5 : return refundMap.containsKey(CDKey);
		}
		return false;
	    
	}
	
	// -------------------------------------------------------------------
	// Map 키에 해당하는 값 삭제하는 메서드
    public void removeMap(int CDKey, int switchMap) {
		switch(switchMap) {
			case 1 : CDMap.remove(CDKey);			break;
			case 2 : cartMap.remove(CDKey);			break;
			case 3 : orderMap.remove(CDKey);		break;
			case 4 : purchaseMap.remove(CDKey);		break;
			case 5 : refundMap.remove(CDKey);		break;
		}
        
    }

	// -------------------------------------------------------------------
    // entry로 Map을 출력하는 메서드
    public void showMap(int switchMap) {
    	HashMap<Integer, CD> map = null;
    	switch(switchMap) {
    		case 1 : map = CDMap; 			break;
    		case 2 : map = cartMap; 		break;
    		case 3 : map = orderMap; 		break;
    		case 4 : map = purchaseMap; 	break;
    		case 5 : map = refundMap; 		break;
    	}
    	
    	for(Map.Entry<Integer, CD> entry : map.entrySet()) {
			int key = entry.getKey();
			CD value = entry.getValue();
			System.out.println(key + "\t" + value);	
    	}
    }

    // =================================================================== 
    
	@Override
	public String toString() {
		return title + "\t" + singer +"\t" + price +"\t" + count;
	}
}
