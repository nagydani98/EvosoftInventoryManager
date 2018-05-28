package Costumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;

public class CostumerRun {
	public static void main(String[] args) throws Exception{
		System.out.println("Start");
		
		CostumerParser xmlParser = new CostumerParser();
		
		List<Costumer> costumer;
		
		boolean loadSuccess = xmlParser.tryToLoad();
		if(loadSuccess) {
			
			costumer = xmlParser.getLoadedPeople();
			System.out.println("A xml beolvasasa sikeres volt.");
			System.out.println("Ezek a szemelyek voltak elmentve:");
			
			xmlParser.printCostumerList();
		}
		else {
			System.out.println("SOMETHING IS WRONG");
			
			
			costumer = new ArrayList<Costumer>();
		}
		
	
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("name: ");
		String newName = scanner.nextLine();
		
		
		int newTax;
		  System.out.print("tax: ");
		  do
		  { 
		      try {
		          String s = scanner.nextLine();
		          newTax = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("Couldn't parse input, please try again");
		      }
		  }
		  while (true);
		
		  int newPostCode;
		  System.out.print("post code: ");
		  do
		  { 
		      try {
		          String s = scanner.nextLine();
		          newPostCode = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("Couldn't parse input, please try again");
		      }
		  }
		  while (true);
		  
		  int newShopNumber;
		  System.out.print("shop number: ");
		  do
		  { 
		      try {
		          String s = scanner.nextLine();
		          newShopNumber = Integer.parseInt(s);
		          break;
		      }
		      catch (Exception e)
		      {
		          System.out.print("Couldn't parse input, please try again");
		      }
		  }
		  while (true);

		
		System.out.print("email: ");
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
