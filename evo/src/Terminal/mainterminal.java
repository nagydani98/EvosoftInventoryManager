package Terminal;
import Item.*;
import Costumer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainterminal {
//main terminal, ezt nem kell szerintem kiszervezni külön classba
	
	//main metódus
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuOfItemsAndCusomers();
	}
	
	//ellenõrzötten beolvasó metódusok, menu interakcióhoz, vagy egyebekhez
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
	
	public static int readIntInRange(int floor, int ceiling) { //a delete funkcióhoz, hogy a megadott sorszám lista méretén belül van-e
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
	
	//menu metódusok
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
		CostumerParser parser = new CostumerParser();
		
		do {
			if (!parser.tryToLoad()) //egyenlõre a loopon belülre raktam a loadot, így ha create vagy delete operációt csinálunk, a lista automatikusan frissül, és elõre be lesz töltve
				System.out.println("Error loading xml, perhaps the file does not exist");
				
			System.out.print(
				"\n1. List cusomers\n2. Search for customer\n3. Create new customer\n4. Delete customer\n5. Back to main menu\n6. Back\n Type in the operation's number you wish to perform:");
		menunumber = readInt();
		switch (menunumber) {
		
		
		case 1:
			parser.printCostumerList();
			break;
		
		
		case 2:
			System.out.println("\nPlease provide a name to search for:");
			Scanner scanner2 = new Scanner(System.in); 
			String name2 = scanner2.nextLine();
			
			List<Costumer> foundCostumers= parser.findCostumers(name2);
			System.out.println("Customers with that name:");
			if(!foundCostumers.isEmpty()) {
				for (Costumer costumer : foundCostumers) {
				System.out.println(costumer.toString());
				}
			}
			else System.out.println("There is no customer with that name");
			break;
			
			
		case 3:
			Scanner scanner3 = new Scanner(System.in); 
			System.out.println("Registering new customer, please provide a name:"); //ez így még placeholder, hisz meg kell majd valósítanunk ellenõrzéseket,
																					//hogy pl az emailt megfelelõ formátumban adják meg
			String name3 = scanner3.nextLine();
			
			System.out.println("Provide a taxnumber:");
			int tax = readInt();
			
			System.out.println("Provide a postcode:");
			int postcode = readInt();
			
			System.out.println("Provide a shopnumber:");
			int shopnumber = readInt();
			
			System.out.println("Provide an e-mail");
			String email = scanner3.nextLine();
			
			Costumer customer = new Costumer(name3, tax, postcode, shopnumber, email);
			
			parser.getLoadedPeople().add(customer);
			
			//scanner3.close(); ez az utasítás gondot okoz valamiért, majd demonstrálom
			break;
		
		
		case 4:
			
			System.out.println("\nPlease provide the customer's name which you wish to delete:");
			Scanner scanner4 = new Scanner(System.in); 
			String name4 = scanner4.nextLine();
			
			List<Costumer> costumersToDel= parser.findCostumers(name4); //megtalálja az öszes customert ezzel a névvel, eltárolja listában
			
			if(!costumersToDel.isEmpty() && costumersToDel.size() > 1) { //ha több, mint egy ilyen nevû ember van
				System.out.println("Customers with that name:");
				for (int i = 0; i < costumersToDel.size(); i++) {
					System.out.println(String.format("%d. %s", i+1, costumersToDel.get(i).toString())); //kiírja a teljes adataikat
				}
				System.out.println("Please provide the number of the customer you wish to delete:"); //megkér, hogy írjam be a sorzsámát annak, amelyiket törölném
				int num = readIntInRange(0, costumersToDel.size());
				parser.deleteCustomer(costumersToDel.get(num-1));
			}
			else if(costumersToDel.size() == 1) { 
				parser.deleteCustomer(costumersToDel.get(0));
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
		
		parser.tryToSave(parser.getLoadedPeople()); //mentés szintén a loop végén
		}while(menustate);
	}
}

	