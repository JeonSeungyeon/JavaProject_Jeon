package controller;

public class CustomerImpl implements Customer {

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartAdd() {
		// TODO Auto-generated method stub
		
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

}
