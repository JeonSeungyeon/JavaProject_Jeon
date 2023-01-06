package controller;

public class AdminImpl implements Admin {
	
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
	@Override
	public void productList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void productAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void productUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void productRemove() {
		// TODO Auto-generated method stub
		
	}

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
