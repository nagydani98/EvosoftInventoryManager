package classes.order;

import java.time.LocalTime;

public class CustomerOrder {
	private String product;
	private LocalTime orderTime;
	private int id;
	private int quantity;
	private String name;
	private String email;
	private String address;
	private int zip;
	
	//Base order type. We should use this, before we finish the whole login system.
	public CustomerOrder(String name,String product,int quantity){
		this.product = product;
		this.name = name;
		this.quantity = quantity;
		orderTime = LocalTime.now();
		//this.id = id;
		//this.email = email;
		//this.zip = zip;
		//this.address = address;
	}
	public CustomerOrder(String name,String product,int quantity,int id, String email, int zip, String address){
		this.product = product;
		this.name = name;
		this.quantity = quantity;
		orderTime = LocalTime.now();
		this.id = id;
		this.email = email;
		this.zip = zip;
		this.address = address;
	}
	
	public String getProduct() {
		return product;
	}
	
	public String getName() {
		return name;
	}
	public LocalTime getOrderTime() {
		return orderTime;
	}
	
	public int getId() {
		return id;
	}
	
	public int getZip() {
		return zip;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
