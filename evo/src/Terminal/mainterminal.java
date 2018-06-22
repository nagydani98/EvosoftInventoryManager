package Terminal;
import product.terminal.ProductMenu;
import customer.*;
import product.terminal.Terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainterminal {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuOfItemsAndCusomers();
	}
	
	
	
	//menu methods
	public static void menuOfItemsAndCusomers() {
		int menunumber=0;
		boolean menustate = true;
		
		do {
		System.out.print("1. Customers\n2. Items\n3. Shopping\n4. Back Type in the menu's number you wish to enter:");
		menunumber = TerminalReaders.readInt();
		switch (menunumber) {
		case 1:
			menuOfCustomers();
			break;
		case 2:
			//menuOfItems();
			ProductMenu.theProductMenu_MainMenu();
			break;
		case 3:
			menuOfShopping();
			break;
		case 4:
			menustate = false;
			break;
		default:
			System.out.println("\nUnrecognised input");
		}
		}while(menustate);
	}
	
	public static void menuOfItems() {
		int menunumber=0;
		boolean menustate = true;
		
		do {
		System.out.print(
				"\n1. List items\n2. Search\n3. Create item\n4. Delete item\n5. Back to main menu\n6. Back\n Type in the operation's number you wish to perform:");
		menunumber = TerminalReaders.readInt();
		switch (menunumber) {
		case 1:
			//print item list
			break;
		case 2:
			//search for item
			break;
		case 3:
			//create item
			break;
		case 4:
			//delete item
			break;
		case 5:
			menustate = false;
			break;
		case 6:
			menustate = false;
			break;
		default:
			System.out.println("\nUnrecognised input");
		}
		}while(menustate);
	}
	
	public static void menuOfShopping() {
		int menunumber=0;
		boolean menustate = true;
		
		do {
		System.out.print(
				"\n1. Choose costumer\n2. Choose item\3. Back to main menu\n4. Back\n Type in the operation's number you wish to perform:");
		menunumber = TerminalReaders.readInt();
		switch (menunumber) {
		case 1:
			//Choose costumer
			break;
		case 2:
			//Choose item
			break;
		case 3:
			menustate = false;
			break;
		case 4:
			menustate = false;
			break;
		default:
			System.out.println("\nUnrecognised input!\n");
		}
		}while(menustate);
		
	}
	
	public static void menuOfCustomers() {
		int menunumber=0;
		boolean menustate = true;
		CustomerParser parser = new CustomerParser();
		Scanner scanner = new Scanner(System.in);
		
		do {
			if (!parser.tryToLoad()) 
				System.out.println("Error loading xml, perhaps the file does not exist!\n");
				
			System.out.print(
				"\n1. List cusomers\n2. Search for customer\n3. Create new customer\n4. Delete customer\n5. Back to main menu\n6. Back\n Type in the operation's number you wish to perform:");
		menunumber = TerminalReaders.readInt();
		switch (menunumber) {
		
		
		case 1:
			parser.printCustomerList();
			break;
		
		
		case 2:
			System.out.println("\nPlease provide a name to search for: ");
			String name2 = scanner.nextLine();
			
			List<Customer> foundCustomers= parser.findCostumers(name2);
			System.out.println("Customers with that name: ");
			if(!foundCustomers.isEmpty()) {
				for (Customer customer : foundCustomers) {
				System.out.println(customer.toString());
				}
			}
			else System.out.println("There is no customer with that name");
			break;
			
			
		case 3:
			System.out.println("Registering new customer, please provide a name: ");
																					
			String name3 = scanner.nextLine();
			
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
			
			Customer customer = new Customer(name3, tax, postcode, shopnumber, email);
			
			parser.getLoadedPeople().add(customer);
			
			break;
		
		
		case 4:
			
			System.out.println("\nPlease provide the customer's name which you wish to delete:");
			String name4 = scanner.nextLine();
			
			List<Customer> customersToDel= parser.findCostumers(name4); 
			
			if(!customersToDel.isEmpty() && customersToDel.size() > 1) { 
				System.out.println("Customers with that name:");
				for (int i = 0; i < customersToDel.size(); i++) {
					System.out.println(String.format("%d. %s", i+1, customersToDel.get(i).toString())); 
				}
				System.out.println("Please provide the number of the customer you wish to delete:"); 
				int num = TerminalReaders.readIntInRange(0, customersToDel.size());
				parser.deleteCustomer(customersToDel.get(num-1));
			}
			else if(customersToDel.size() == 1) { 
				parser.deleteCustomer(customersToDel.get(0));
				break;
			}
			else System.out.println("There is no customer with that name");
			break;
		
		
		case 5:
			menustate = false;
			break;
		case 6:
			menustate = false;
			break;
		default:
			System.out.println("\nUnrecognised input");
		}
		
		parser.tryToSave(parser.getLoadedPeople()); 
		}while(menustate);
		scanner.close();
		//Terminal.closeScanner();		//ha valaha vissza akarsz innen menni, akkor ezt a reszt torold ki es rakd a program vegere.
	}
}

	