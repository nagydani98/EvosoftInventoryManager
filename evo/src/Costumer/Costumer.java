package Costumer;


public class Costumer {
	
	private String name;
	private int tax;
	private int postCode;
	private int shopNumber;
	private String email;
	
	public Costumer(String name, int tax, int postCode, int shopNumber, String email) {
		this.name = name;
		this.tax = tax;
		this.postCode = postCode;
		this.shopNumber = shopNumber;
		this.email = email;
	}
	
	public Costumer() {
		
	}
	
	public String toString() {
		return "Name:" + name + ", Tax:" + tax + ", Post code:" + postCode + ", Shop number:" + shopNumber+ ", Email:" + email;
	}

	public  String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTax() {
		return tax;
	}
	
	public void setTax(int tax) {
		this.tax = tax;
	}
	
	public int getPostCode() {
		return postCode;
	}
	
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getShopNumber() {
		return shopNumber;
	}
	
	public void setShopNumber(int shopNumber) {
		this.shopNumber = shopNumber;
	}


	public boolean getRegularCostumer() {
		if (this.shopNumber > 100)
			return true;

		return false;
	}
	
}