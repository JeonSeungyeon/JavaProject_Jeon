package model;

import java.util.HashMap;
import java.util.Map;

// DTO(Data Transfer Object)
public class CD {

	// =================================================================== 
	// 멤버변수
	private String title;	// 제목
	private String singer;	// 가수
	private int price;		// 가격
	private int count;		// 수량
	
	private HashMap<Integer, CD> CDMap = new HashMap<Integer, CD>();		// CD재고 Map
	private HashMap<Integer, CD> cartMap = new HashMap<Integer, CD>();		// 장바구니 Map
	private HashMap<Integer, CD> orderMap = new HashMap<Integer, CD>();		// 주문요청 Map
	private HashMap<Integer, CD> purchaseMap = new HashMap<Integer, CD>();	// 구매완료 Map
	private HashMap<Integer, CD> refundMap = new HashMap<Integer, CD>();	// 환불요청 Map
	
	// =================================================================== 
	// 싱글톤
	// CD클래스를 다른 클래스에서 사용하기 위해 인스턴스를 생성하게 되면 Map도 초기화 되기 때문에 싱글톤을 사용
	
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
	// Map 메서드
	
	// -------------------------------------------------------------------
	// 값 추가 메서드 모음
	
	// CDMap에 값 추가하는 메서드
	public void addMap(int CDKey, CD value) {
		CDMap.put(CDKey, value);
	}

	// cartMap에 값 추가하는 메서드
	public void addCartMap(int CDKey, int cartCount) {		
		calMap(CDKey, cartCount, 1);	// 기존값을 복사하고 계산하는 메서드를 호출한다.		
	}
	
	// orderMap에 값 추가하는 메서드(장바구니)
	public void addOrderMap(int CDKey) {
		copyMap(CDKey, 3);	// 기존값을 복사하는 메서드를 호출한다
	}
	
	// orderMap에 값 추가하는 메서드(바로구매)
	public void buyNow(int CDKey, int orderCount) {		
		calMap(CDKey, orderCount, 3);	
	}
	
	// purchaseMap에 값 추가하는 메서드
	public void addPurchaseMap(int CDKey) {
		copyMap(CDKey, 4);
	}
	
	// refundMap에 값 추가하는 메서드
	public void addRefundMap(int CDKey) {
		copyMap(CDKey, 5);
	}
	
	// -------------------------------------------------------------------
	// 기존 값을 복사하는 메서드
	public void copyMap(int CDKey, int switchMap) {
    	HashMap<Integer, CD> copyMap = null;	// 복사할 Map을 저장할 Map
    	HashMap<Integer, CD> PasteMap = null;	// 붙여넣을 Map을 저장할 Map
    	switch(switchMap) {
    		// switch case를 이용해서 switchMap에 따라 복사할 맵과 붙여넣을 맵을 다르게 한다
    		case 3 : copyMap = cartMap; PasteMap = orderMap;		break;	 
    		case 4 : copyMap = orderMap; PasteMap = purchaseMap;	break;	
    		case 5 : copyMap = purchaseMap; PasteMap = refundMap;	break;	
    	}
		CD value = copyMap.get(CDKey);	// CDKey에 해당하는 value값에 복사할 맵의 value값을 복사한다.
		PasteMap.put(CDKey, value);		// CDKey를 key 값으로 위에서 복사한 value값을 value값으로 put한다.
	}
	
	// 복사한 값을 계산하는 메서드
	public void calMap (int CDKey, int count, int switchMap) {
    	HashMap<Integer, CD> copyMap = null;
    	HashMap<Integer, CD> PasteMap = null;
    	switch(switchMap) {
    		case 1 : copyMap = CDMap; PasteMap = cartMap;	break;
    		case 3 : copyMap = CDMap; PasteMap = orderMap;	break;
    	}
		CD value = copyMap.get(CDKey);	// CDKey에 해당하는 value값에 복사할 맵의 value값을 복사한다.
		CD calValue = new CD(value.title, value.singer, 
				value.price * count, count);	
		// 새로운 CD클래스를 만들고 매개변수로 값을 넘겨준다
		// title, singer는 복사한 값을 그대로 사용한다
		// count 값은 메서드를 호출할 때 받은 count를 사용하고 price는 받은 count값과 곱한다 
		
		PasteMap.put(CDKey, calValue);	// CDKey를 key 값으로 새로운 클래스를 value값으로 put한다.
	}
	
	// -------------------------------------------------------------------
	   // 재고 감소
	   public void countIncrease(int CDKey) {
	      CD CDMapValue = CDMap.get(CDKey);         		// CDMap 값 복사
	      CD purchaseMapValue = purchaseMap.get(CDKey);   	// purchaseMap 값 복사
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
	// CDMap의 count값만 리턴하는 메서드 
	public int returnCount(int CDKey) {
		CD value = CDMap.get(CDKey);
		return value.count;
	}
	
	// -------------------------------------------------------------------
	// 결산 증가
	public int returnIncome(int CDKey) {
		CD value = purchaseMap.get(CDKey);	// 구매한 가격을 리턴해줌
		return value.price;
	}
	
	// 결산 감소
	public int returnRefundFee(int CDKey) {
		CD value = refundMap.get(CDKey);	// 환불한 가격을 리턴해줌
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
    	// switchMap 따라 entrySet앞에 들어갈 Map을 다르게 준다 
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
		// 목록을 재정의 해서 멤버변수들이 일정한 간격으로 출력한다
		return title + "\t" + singer +"\t" + price +"\t" + count;
	}
}
