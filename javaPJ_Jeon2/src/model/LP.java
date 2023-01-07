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
	
	private HashMap<Integer, LP> LPMap;
	private HashMap<Integer, LP> cartMap;
	
	// =================================================================== 
	// 싱글톤
	
	private LP() {
		LPMap = new HashMap<Integer, LP>();
		cartMap = new HashMap<Integer, LP>();
	}
	
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
	// 멤버메서드(Getter, Setter)
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	// =================================================================== 
	// Map 메서드
	
	// Map에 값 추가하는 메서드
	public void addMap(int key, LP value, int switchMap) {	// switchMap을 상수로 바꾸면 좋을 거 같음
		switch(switchMap) {
			case 1 : LPMap.put(key, value);		break;
			case 2 : cartMap.put(key, value);	break;
		}
	}
	
	// Map에 값 가져오는 메서드
	public LP getMap(int key, int switchMap) {
		switch(switchMap) {
			case 1 : return LPMap.get(key);
			case 2 : return cartMap.get(key);
		}
			return null;
    }
	
	// Map에 값이 존재하는지 확인하는 메서드
	public boolean containsKeyMap(int key, int switchMap) {
		switch(switchMap) {
			case 1 : return LPMap.containsKey(key);
			case 2 : return cartMap.containsKey(key);
		}
		return false;
	    
	}
	
	// Map 키에 해당하는 값 삭제하는 메서드
    public void removeMap(int key, int switchMap) {
		switch(switchMap) {
			case 1 : LPMap.remove(key);		break;
			case 2 : cartMap.remove(key);	break;
		}
        
    }

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
    	}
    }

    // =================================================================== 
    
	@Override
	public String toString() {
		return title + "\t" + band +"\t" + price +"\t" + count;
	}
}
