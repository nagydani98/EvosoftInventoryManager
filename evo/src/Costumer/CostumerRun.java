package Costumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CostumerRun {
	public static void main(String[] args) {
		System.out.println("Start");
		
		CostumerParser xmlParser = new CostumerParser();
		
		List<Costumer> costumer;
		
		boolean loadSuccess = xmlParser.tryToLoad();
		if(loadSuccess) {
			
			costumer = xmlParser.getLoadedPeople();
			System.out.println("A xml beolvas·sa sikeres volt.");
			System.out.println("Ezek a szemÈlyek voltak elmentve:");
			for(int i = 0; i < costumer.size(); i++) {
				
				Costumer person = costumer.get(i);
				
				
				System.out.println(String.format("%d. %s: %d", i, person.getName(), person.getTax()));
			}
		}
		else {
			System.out.println("SOMETHING IS WRONG");
			
			
			costumer = new ArrayList<Costumer>();
		}
		
	
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("⁄j name: ");
		String newName = scanner.nextLine();
		
		System.out.print("⁄j tax: ");
		int newTax = scanner.nextInt();
		
		System.out.print("⁄j postCode: ");
		int newPostCode = scanner.nextInt();
		
		System.out.print("⁄j shopNumber: ");
		int newShopNumber = scanner.nextInt();
		
		System.out.print("⁄j email: ");
		String newEmail = scanner.nextLine();
		
		scanner.close();
		
		Costumer newCostumer = new Costumer(newName, newTax, newPostCode, newShopNumber, newEmail);
		costumer.add(newCostumer);
		
		boolean saveSuccess = xmlParser.tryToSave(costumer);
		if(saveSuccess) {
			System.out.println("SAVED");
		}
		else {
			System.out.println("UNSAVED");
		}
	}

}
