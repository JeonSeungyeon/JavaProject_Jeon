package model;

// DTO(Data Transfer Object)
public class LP {

	// 멤버변수
	private String title;
	private String band;
	private int price;
	private int count;
	
	// 생성자
	public LP() {}

	public LP(String title, String band, int price, int count) {
		this.title = title;
		this.band = band;
		this.price = price;
		this.count = count;
	}

	// 멤버메서드(Getter, Setter, 출력정보)
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
	
	public void showLPInfo() {
		System.out.println("LP 제목 : " + title);
		System.out.println("밴드 이름 : " + band);
		System.out.println("LP 가격 : " + price);
		System.out.println("LP 수량 : " + count);
	}
}
