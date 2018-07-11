package Terminal;
import customer.*;
import fileOpperations.Xml;
import visualization.*;
import visualization.terminal.Menu;
import visualization.terminal.ProductMenu;
import visualization.terminal.Terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.product.Products;

public class mainterminal {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuOfItemsAndCusomers();
	}
	
	//menu methods
	public static void menuOfItemsAndCusomers() {
		int menunumber=0;
		boolean menustate = true;
		//Ezt a reszt en irtam be Megyeri, itt nyitom meg a xml es olvasom be az adatokat utana beallitom Product menu es Vasarlas menut is ahol termeket szeretnenk venni
		Terminal.openScanner();
		List<Products> theList = new ArrayList<Products>();
		if(Xml.product.open()) {
			theList = Xml.product.reader();
			ProductMenu.setList(theList);
			Menu.setList(theList);
		}
		
		do {
		System.out.print("1. Customers\n2. Items\n3. Shopping\n4. Exit \n Type in the menu's number you wish to enter:");
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
	
	public static void menuOfShopping() {
		int menunumber=0;
		boolean menustate = true;
		
		
		CustomerParser customerParser = new CustomerParser();
		Scanner scanner = new Scanner(System.in);
		do {
			if (!customerParser.tryToLoad()) 
				System.out.println("Error loading customer xml, perhaps the file does not exist!\n");
			
		if(Menu.buying.getLogout()) {
			String[] ChoseValue= {"List customers","Search for customer","List products","Search for product","Create order","Logout","Back"};
			menunumber = Terminal.operation.writeDownMenuAndChooseOne(ChoseValue, true);
		}else {
			String[] ChoseValue= {"List customers","Search for customer","List products","Search for product","Create order","Back"};
			menunumber = Terminal.operation.writeDownMenuAndChooseOne(ChoseValue, true);
		}
		
		
		switch (menunumber) {
		case 1:
			customerParser.printCustomerList();
			break;
		case 2:
			System.out.println("\nPlease provide a name to search for: ");
			String name2 = scanner.nextLine();
			
			List<Customer> foundCustomers= customerParser.findCostumers(name2);
			System.out.println("Customers with that name: ");
			if(!foundCustomers.isEmpty()) {
				for (Customer customer : foundCustomers) {
				System.out.println(customer.toString());
				}
			}
			else System.out.println("There is no customer with that name");
			break;
		case 3:
			Menu.echoProd.display();
			break;
		case 4:
			Menu.isearch.Display();
			break;
		case 5:
			//cartMenu()
			Menu.buying.display();		// ezt a reszt adtam hozza, gondolom az adatokat szereted volna beallitani a 6-os pontba, nos en ugy gondoltam nincs login rendszer meg.
			break;						// Ezert kicsit fajdalmas egyesevel beleteni minden vasarlas megkezdesenel a szemelyes adatokat, ezert egyszeruen csak a nevet kertem be a felhasznalotol, nincs ellenorzes hogy valoban benne van-e customerbe meg.
										// Azon lesz rogzitve a vasarlas, ugy csinaltam meg a CustomerOrder osztalyt, hogy benne vannak a tobbi adatok. De nem hasznalja oket, igy ha kesz a regisztracio. Akkor az adatokat majd egyszeruen hozza adjuk a regisztracihoz ha kell
		case 6:
			if(Menu.buying.getLogout()) {
				Menu.buying.logout();
				System.out.println("Logout is successfull");
				menustate = false;
			}
		case 7:
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
				"\n1. List cusomers\n2. Search for customer\n3. Create new customer\n4. Delete customer\n5. Back\n Type in the operation's number you wish to perform:");
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
			Customer customer = Customer.registerNewCustomer();
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
		default:
			System.out.println("\nUnrecognised input");
		}
		
		parser.tryToSave(parser.getLoadedPeople()); 
		}while(menustate);
		//scanner.close();
		//Terminal.closeScanner();		//ha valaha vissza akarsz innen menni, akkor ezt a reszt torold ki es rakd a program vegere.
	}
}

	