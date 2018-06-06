package Terminal;
import Item.*;
import customer.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainterminal {
//main terminal, ezt nem kell szerintem kiszervezni k�l�n classba
	
	//main met�dus
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuOfItemsAndCusomers();
	}
	
	//ellen�rz�tten beolvas� met�dusok, menu interakci�hoz, vagy egyebekhez
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
	
	public static int readIntInRange(int floor, int ceiling) { //a delete funkci�hoz, hogy a megadott sorsz�m lista m�ret�n bel�l van-e
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
	
	//menu met�dusok
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
			menuOfItems();
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
			if (!parser.tryToLoad()) //egyenl�re a loopon bel�lre raktam a loadot, �gy ha create vagy delete oper�ci�t csin�lunk, a lista automatikusan friss�l, �s el�re be lesz t�ltve
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
			System.out.println("Registering new customer, please provide a name:"); //ez �gy m�g placeholder, hisz meg kell majd val�s�tanunk ellen�rz�seket,
																					//hogy pl az emailt megfelel� form�tumban adj�k meg
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
			int postcode = readInt();
			
			System.out.println("Provide a shopnumber:");
			int shopnumber = readInt();
			
			
			System.out.println("Provide an e-mail");
			String email = scanner3.nextLine();
			
			Customer customer = new Customer(name3, tax, postcode, shopnumber, email);
			
			parser.getLoadedPeople().add(customer);
			
			//scanner3.close(); ez az utas�t�s gondot okoz valami�rt, majd demonstr�lom
			break;
		
		
		case 4:
			
			System.out.println("\nPlease provide the customer's name which you wish to delete:");
			Scanner scanner4 = new Scanner(System.in); 
			String name4 = scanner4.nextLine();
			
			List<Customer> customersToDel= parser.findCostumers(name4); //megtal�lja az �szes customert ezzel a n�vvel, elt�rolja list�ban
			
			if(!customersToDel.isEmpty() && customersToDel.size() > 1) { //ha t�bb, mint egy ilyen nev� ember van
				System.out.println("Customers with that name:");
				for (int i = 0; i < customersToDel.size(); i++) {
					System.out.println(String.format("%d. %s", i+1, customersToDel.get(i).toString())); //ki�rja a teljes adataikat
				}
				System.out.println("Please provide the number of the customer you wish to delete:"); //megk�r, hogy �rjam be a sorzs�m�t annak, amelyiket t�r�ln�m
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
		
		parser.tryToSave(parser.getLoadedPeople()); //ment�s szint�n a loop v�g�n
		}while(menustate);
	}
}

	