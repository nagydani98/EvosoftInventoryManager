package classes.product;

public class Other extends Products{
	public Other(String name,String producer,int quantity, int price, String type) {
		super(name,producer,quantity,price);
		setCategorical("other");
		setType(type);
	}
	
	@Override
	public void writeDownTheParameters() {
		super.writeDownTheParameters();
	}
}
