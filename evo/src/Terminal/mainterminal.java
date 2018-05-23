package Terminal;
import Item.*;
import Costumer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class mainterminal {
//main terminal, ezt nem kell szerintem kiszervezni külön classba
	
	//main metódus
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuOfItemsAndCusomers();
	}
	
	//ellenõrzötten beolvasó metódusok, menu interakcióhoz
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
	
	//menu metódusok
	public static void menuOfItemsAndCusomers() {
		int menunumber=0;
		System.out.print("1. Customers\n2. Items\n Type in the menu's number you wish to enter:");
		menunumber = readInt();
		switch (menunumber) {
		case 1:
			//menuOfCustomers();
		case 2:
			menuOfItems();
		default:
			System.out.println("\nUnrecognised input");
			menuOfItemsAndCusomers();
		}
	}
	
	public static void menuOfItems() {
		int menunumber=0;
		System.out.print(
				"1. List items\n2. Search\n3. Create item\n4. Delete item\n5. Back to main menu\n6. Back\n Type in the operation's number you wish to perform:");
		menunumber = readInt();
		switch (menunumber) {
		case 1:
			//print item list
			menuOfItems();
		case 2:
			//search for item
		case 3:
			//create item
		case 4:
			
		case 5:
			menuOfItemsAndCusomers();
		case 6:
			menuOfItemsAndCusomers();
		default:
			System.out.println("\nUnrecognised input");
			menuOfItems();
		}
	}
}

	