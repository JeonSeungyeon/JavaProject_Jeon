package model;

import java.util.HashMap;
import java.util.Map;

// DTO(Data Transfer Object)
public class LP {

	// 멤버변수
	private String title;
	private String band;
	private int price;
	private int count;
	
	private HashMap<Integer, LP> LPMap;
	
	// =================================================================== 
	// 생성자
	public LP() {
		LPMap = new HashMap<Integer, LP>();
	}

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
	// LPMap 메서드
	
	// LPMap에 값 추가하는 메서드
	public void addLPMap(int key, LP value) {
		LPMap.put(key, value);
	}
	
	// LPMap에 값 가져오는 메서드
	public LP getLPMap(int key) {
        return LPMap.get(key);
    }
	
	// LPMap에 값이 존재하는지 확인하는 메서드
	public boolean containsKeyLPMap(int key) {
	    return LPMap.containsKey(key);
	}
	
	// LPMap 키에 해당하는 값 삭제하는 메서드
    public void removeLPMap(int key) {
        LPMap.remove(key);
    }

    // entry로 LPMap을 출력하는 메서드
    public void entryLPMap() {
    		for(Map.Entry<Integer, LP> entry : LPMap.entrySet()) {
    			int key = entry.getKey();
    			LP value = entry.getValue();
    			System.out.println(key + "\t" + value);	
    		}
    }

    // =================================================================== 
	@Override
	public String toString() {
		return title + "\t" + band +"\t" + price +"\t" + count;
	}
}
