package product.classes;

public class ComponentsPC extends Products{
	public ComponentsPC(String name,String producer,int quantity, int price) {
		super(name,producer,quantity,price);
		setCategorical("componentpc");
	}
}
