package Terminal;
import Item.*;
import Costumer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		CostumerParser parser = new CostumerParser();
		
		do {
			if (!parser.tryToLoad()) //egyenl�re a loopon bel�lre raktam a loadot, �gy ha create vagy delete oper�ci�t csin�lunk, a lista automatikusan friss�l, �s el�re be lesz t�ltve
				System.out.println("Error loading xml, perhaps the file does not exist");
				
			System.out.print(
				"\n1. List cusomers\n2. Search for customer\n3. Create new cusomter\n4. Delete customer\n5. Back to main menu\n6. Back\n Type in the operation's number you wish to perform:");
		menunumber = readInt();
		switch (menunumber) {
		
		
		case 1:
			parser.printCostumerList();
			break;
		
		
		case 2:
			//search for customer
			break;
			
			
		case 3:
			Scanner scanner = new Scanner(System.in); 
			System.out.println("Registering new customer, please provide a name:"); //ez �gy m�g placeholder, hisz meg kell majd val�s�tanunk ellen�rz�seket,
																					//hogy pl az emailt megfelel� form�tumban adj�k meg
			String name = scanner.nextLine();
			
			System.out.println("Provide a taxnumber:");
			int tax = readInt();
			
			System.out.println("Provide a postcode:");
			int postcode = readInt();
			
			System.out.println("Provide a shopnumber:");
			int shopnumber = readInt();
			
			System.out.println("Provide an e-mail");
			String email = scanner.nextLine();
			
			Costumer customer = new Costumer(name, tax, postcode, shopnumber, email);
			
			parser.getLoadedPeople().add(customer);
			
			
			break;
		
		
		case 4:
			//delete customer
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

	