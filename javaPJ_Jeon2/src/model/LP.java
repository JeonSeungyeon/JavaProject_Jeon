package model;

import java.util.HashMap;
import java.util.Map;

// DTO(Data Transfer Object)
public class LP {

	// =================================================================== 
	// 멤버변수
	private String title;
	private String band;
	private int price;
	private int count;
	
	private HashMap<Integer, LP> LPMap = new HashMap<Integer, LP>();;
	private HashMap<Integer, LP> cartMap = new HashMap<Integer, LP>();
	private HashMap<Integer, LP> orderMap = new HashMap<Integer, LP>();
	private HashMap<Integer, LP> purchaseMap = new HashMap<Integer, LP>();
	private HashMap<Integer, LP> refundMap = new HashMap<Integer, LP>();
	
	// =================================================================== 
	// 싱글톤
	
	private LP() {}
	
	private static LP lp = new LP();
	
	public static LP getInstance() {
		if(lp == null) {
			return lp = new LP();
		}
		return lp;
		
	}
	
	// =================================================================== 
	// 생성자

	public LP(String title, String band, int price, int count) {
		this.title = title;
		this.band = band;
		this.price = price;
		this.count = count;
	}
	
	// =================================================================== 
	// Getter, Setter - 한번도 안써서 일단 지워둠
	
	// =================================================================== 
	// Map 메서드
	// ====================================
	// 값 추가 메서드 모음
	
	// LPMap에 값 추가하는 메서드
	public void addMap(int key, LP value) {
		LPMap.put(key, value);
	}
	
	// cartMap에 값 추가하는 메서드
	public void addCartMap(int LPKey, int cartCount) {		
		LP value = LPMap.get(LPKey);
		LP cartValue = new LP(value.title, value.band, 
				value.price * cartCount, cartCount);
		cartMap.put(LPKey, cartValue);			
	}
	
	// orderMap에 값 추가하는 메서드(장바구니)
	public void addOrderMap(int LPKey) {
		LP value = cartMap.get(LPKey);
		orderMap.put(LPKey, value);
	}
	
	// orderMap에 값 추가하는 메서드(바로구매)
	public void buyNow(int LPKey, int cartCount) {		
		LP value = LPMap.get(LPKey);
		LP cartValue = new LP(value.title, value.band, 
				value.price * cartCount, cartCount);
		orderMap.put(LPKey, cartValue);			
	}
	
	// purchaseMap에 값 추가하는 메서드
	public void addPurchaseMap(int LPKey) {
		LP value = orderMap.get(LPKey);
		purchaseMap.put(LPKey, value);
	}
	
	// purchaseMap에 값 추가하는 메서드
	public void addRefundMap(int LPKey) {
		LP value = purchaseMap.get(LPKey);
		refundMap.put(LPKey, value);
	}
	
	// ====================================
	// Map에 값 가져오는 메서드
	public LP getMap(int key, int switchMap) {
		switch(switchMap) {
			case 1 : return LPMap.get(key);
			case 2 : return cartMap.get(key);
			case 3 : return orderMap.get(key);
			case 4 : return purchaseMap.get(key);
			case 5 : return refundMap.get(key);
		}
			return null;
    }
	
	// ====================================
	// Map에 값이 존재하는지 확인하는 메서드
	public boolean containsKeyMap(int key, int switchMap) {
		switch(switchMap) {
			case 1 : return LPMap.containsKey(key);
			case 2 : return cartMap.containsKey(key);
			case 3 : return orderMap.containsKey(key);
			case 4 : return purchaseMap.containsKey(key);
			case 5 : return refundMap.containsKey(key);
		}
		return false;
	    
	}
	
	// ====================================
	// Map 키에 해당하는 값 삭제하는 메서드
    public void removeMap(int key, int switchMap) {
		switch(switchMap) {
			case 1 : LPMap.remove(key);			break;
			case 2 : cartMap.remove(key);		break;
			case 3 : orderMap.remove(key);		break;
			case 4 : purchaseMap.remove(key);	break;
			case 5 : refundMap.remove(key);		break;
		}
        
    }

    // ====================================
    // entry로 Map을 출력하는 메서드
    public void entryMap(int switchMap) {
    	if(switchMap == 1){
    		for(Map.Entry<Integer, LP> entry : LPMap.entrySet()) {
    			int key = entry.getKey();
    			LP value = entry.getValue();
    			System.out.println(key + "\t" + value);	
    		}
    	} else if(switchMap == 2) {
    		for(Map.Entry<Integer, LP> entry : cartMap.entrySet()) {
    			int key = entry.getKey();
    			LP value = entry.getValue();
    			System.out.println(key + "\t" + value);	
    		}
    	} else if(switchMap == 3) {
    		for(Map.Entry<Integer, LP> entry : orderMap.entrySet()) {
    			int key = entry.getKey();
    			LP value = entry.getValue();
    			System.out.println(key + "\t" + value);	
    		}
    	} else if(switchMap == 4) {
    		for(Map.Entry<Integer, LP> entry : purchaseMap.entrySet()) {
    			int key = entry.getKey();
    			LP value = entry.getValue();
    			System.out.println(key + "\t" + value);	
    		}
    	} else if(switchMap == 5) {
    		for(Map.Entry<Integer, LP> entry : refundMap.entrySet()) {
    			int key = entry.getKey();
    			LP value = entry.getValue();
    			System.out.println(key + "\t" + value);	
    		}
    	}
    }

    // =================================================================== 
    
	@Override
	public String toString() {
		return title + "\t" + band +"\t" + price +"\t" + count;
	}
}
