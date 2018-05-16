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
			// ha sikeres a beolvasas akkor kiirjuk a szemelytt
			costumer = xmlParser.getLoadedPeople();
			System.out.println("A xml beolvas·sa sikeres volt.");
			System.out.println("Ezek a szemÈlyek voltak elmentve:");
			for(int i = 0; i < costumer.size(); i++) {
				// Az i. szemely objektuma.
				Costumer person = costumer.get(i);
				
				// Megjelenitjuk az i. szemÈly adatait.
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
		int newAge = scanner.nextInt();
		scanner.close();
		
		Costumer newCostumer = new Costumer(newName, newTax);
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
