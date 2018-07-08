package customer;

import java.util.Scanner;

public class Customer {
	
	private String name;
	private int tax;
	private int postCode;
	private int shopNumber;
	private String email;
	
	public Customer(String name, int tax, int postCode, int shopNumber, String email) {
		this.name = name;
		this.tax = tax;
		this.postCode = postCode;
		this.shopNumber = shopNumber;
		this.email = email;
	}
	
	public Customer() {
		
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
	
	public static Customer registerNewCustomer() {
		System.out.println("Registering new customer, please provide a name: ");
		Scanner scanner = new Scanner(System.in);
		
		String name = scanner.nextLine();
		
		System.out.println("Provide a taxnumber:");
		int tax;
		
		do
		  { 
		      try {
		    	  String s = scanner.nextLine();
		    	  if (s.length()!=8) throw new RuntimeException();
		    	  tax = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("The tax number is not valid, please try again!\n");
		      }
		  }
		  while (true);
	
		
		
		System.out.println("Provide a postcode: ");
		int postcode;
		do
		  { 
		      try {
		          String s = scanner.nextLine();
		          if (s.length()!=4) throw new RuntimeException();
		          postcode = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("The postcode is not valid, please try again!\n");
		      }
		  }
		  while (true);
		
		System.out.println("Provide a shopnumber: ");
		int shopnumber;
		do
		  { 
		      try {
		          String s = scanner.nextLine();
		          shopnumber = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("Couldn't parse input, please try again!\n");
		      }
		  }
		  while (true);
		
		String email;
		do
		  { 
		      try {
		System.out.println("Provide an e-mail: ");
		 email = scanner.nextLine();
		
		String emailForm = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailForm);
           java.util.regex.Matcher m = p.matcher(email);
           if( !m.matches()) throw new Exception();
           break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("Email format is not valid, please try again!\n");
		      }
		  }
		  while (true);
		
		Customer customer = new Customer(name, tax, postcode, shopnumber, email);
		return customer;
	}
	
	
	public static Customer registerNewCustomer(String name) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Provide a taxnumber:");
		int tax;
		
		do
		  { 
		      try {
		    	  String s = scanner.nextLine();
		    	  if (s.length()!=8) throw new RuntimeException();
		    	  tax = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("The tax number is not valid, please try again!\n");
		      }
		  }
		  while (true);
	
		
		
		System.out.println("Provide a postcode: ");
		int postcode;
		do
		  { 
		      try {
		          String s = scanner.nextLine();
		          if (s.length()!=4) throw new RuntimeException();
		          postcode = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("The postcode is not valid, please try again!\n");
		      }
		  }
		  while (true);
		
		System.out.println("Provide a shopnumber: ");
		int shopnumber;
		do
		  { 
		      try {
		          String s = scanner.nextLine();
		          shopnumber = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("Couldn't parse input, please try again!\n");
		      }
		  }
		  while (true);
		
		String email;
		do
		  { 
		      try {
		System.out.println("Provide an e-mail: ");
		 email = scanner.nextLine();
		
		String emailForm = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailForm);
           java.util.regex.Matcher m = p.matcher(email);
           if( !m.matches()) throw new Exception();
           break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("Email format is not valid, please try again!\n");
		      }
		  }
		  while (true);
		
		Customer customer = new Customer(name, tax, postcode, shopnumber, email);
		return customer;
	}
	
}