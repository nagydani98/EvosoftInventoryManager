package Terminal;
import Item.*;

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
//main terminal, ezt nem kell szerintem kiszervezni kelen classba
	
	//main metedus
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuOfItemsAndCusomers();
	}
	
	//ellenerzetten beolvase metedusok, menu interakciehoz, vagy egyebekhez
	public static int readInt() {
		int num = 0;
		InputStreamReader inputStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inputStream);
		boolean unsuccessful = true;
		
		do {
			try {
				num = Integer.valueOf(input.readLine()).intValue();
				unsuccessful = false;
			} catch (NumberFormatException | IOException e) {
			}
		} while (unsuccessful);
		return num;
	}
	
	public static int readIntInRange(int floor, int ceiling) { //a delete funkciehoz, hogy a megadott sorszem lista mereten belel van-e
		int num = -1;
		boolean unsuccessful = true;
		
		do {
			
				num = readInt();
				if(num <= ceiling && num >= floor)
					unsuccessful = false;
				else System.out.println("Invalid input");
				
		} while (unsuccessful);
		return num;
	}
	
	//menu metedusok
	public static void menuOfItemsAndCusomers() {
		int menunumber=0;
		boolean menustate = true;
		
		do {
		System.out.print("1. Customers\n2. Items\n3. Back\n Type in the menu's number you wish to enter:");
		menunumber = readInt();
		switch (menunumber) {
		case 1:
			menuOfCustomers();
			break;
		case 2:
			//menuOfItems();
			ProductMenu.theProductMenu_MainMenu();
			break;
		case 3:
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
		menunumber = readInt();
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
	
	public static void menuOfCustomers() {
		int menunumber=0;
		boolean menustate = true;
		CustomerParser parser = new CustomerParser();
		
		do {
			if (!parser.tryToLoad()) //egyenlore a loopon belïulre raktam a loadot, egy ha create vagy delete opereciet csinelunk, a lista automatikusan frissel, es elere be lesz teltve
				System.out.println("Error loading xml, perhaps the file does not exist");
				
			System.out.print(
				"\n1. List cusomers\n2. Search for customer\n3. Create new customer\n4. Delete customer\n5. Back to main menu\n6. Back\n Type in the operation's number you wish to perform:");
		menunumber = readInt();
		switch (menunumber) {
		
		
		case 1:
			parser.printCustomerList();
			break;
		
		
		case 2:
			System.out.println("\nPlease provide a name to search for:");
			Scanner scanner2 = new Scanner(System.in); 
			String name2 = scanner2.nextLine();
			
			List<Customer> foundCustomers= parser.findCostumers(name2);
			System.out.println("Customers with that name:");
			if(!foundCustomers.isEmpty()) {
				for (Customer customer : foundCustomers) {
				System.out.println(customer.toString());
				}
			}
			else System.out.println("There is no customer with that name");
			break;
			
			
		case 3:
			Scanner scanner3 = new Scanner(System.in); 
			System.out.println("Registering new customer, please provide a name:"); //ez egy meg placeholder, hisz meg kell majd valesetanunk ellenerzeseket,
																					//hogy pl az emailt megfelele formetumban adjek meg
			String name3 = scanner3.nextLine();
			
			System.out.println("Provide a taxnumber:");
			int tax;
			do
			  { 
			      try {
			          String s = scanner3.nextLine();
			          tax = Integer.parseInt(s);
			          break;
			      }
			      catch (Exception e)
			      {
			          System.out.print("Couldn't parse input, please try again");
			      }
			  }
			  while (true);
			
			System.out.println("Provide a postcode:");
			int postcode;
			do
			  { 
			      try {
			          String s = scanner3.nextLine();
			          postcode = Integer.parseInt(s);
			          break;
			      }
			      catch (Exception e)
			      {
			          System.out.print("Couldn't parse input, please try again");
			      }
			  }
			  while (true);
			
			System.out.println("Provide a shopnumber:");
			int shopnumber;
			do
			  { 
			      try {
			          String s = scanner3.nextLine();
			          shopnumber = Integer.parseInt(s);
			          break;
			      }
			      catch (Exception e)
			      {
			          System.out.print("Couldn't parse input, please try again");
			      }
			  }
			  while (true);
			
			
			System.out.println("Provide an e-mail");
			String email = scanner3.nextLine();
			
			Customer customer = new Customer(name3, tax, postcode, shopnumber, email);
			
			parser.getLoadedPeople().add(customer);
			
			//scanner3.close(); ez az utasetes gondot okoz valamiert, majd demonstrelom
			break;
		
		
		case 4:
			
			System.out.println("\nPlease provide the customer's name which you wish to delete:");
			Scanner scanner4 = new Scanner(System.in); 
			String name4 = scanner4.nextLine();
			
			List<Customer> customersToDel= parser.findCostumers(name4); //megtalelja az eszes customert ezzel a nevvel, elterolja listeban
			
			if(!customersToDel.isEmpty() && customersToDel.size() > 1) { //ha tebb, mint egy ilyen neve ember van
				System.out.println("Customers with that name:");
				for (int i = 0; i < customersToDel.size(); i++) {
					System.out.println(String.format("%d. %s", i+1, customersToDel.get(i).toString())); //kierja a teljes adataikat
				}
				System.out.println("Please provide the number of the customer you wish to delete:"); //megker, hogy erjam be a sorzsemet annak, amelyiket terelnem
				int num = readIntInRange(0, customersToDel.size());
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
		
		parser.tryToSave(parser.getLoadedPeople()); //mentes szinten a loop vegen
		}while(menustate);
		Terminal.closeScanner();		//ha valaha vissza akarsz innen menni, akkor ezt a reszt torold ki és rakd a program vegere.
	}
}

	