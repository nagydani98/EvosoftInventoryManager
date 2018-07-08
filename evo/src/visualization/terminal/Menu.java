package visualization.terminal;

import java.util.ArrayList;
import java.util.List;

import classes.product.ComponentsCPU;
import classes.product.ComponentsGPU;
import classes.product.Other;
import classes.product.Products;
import classes.order.*;
import exceptions.NoProductAvailableException;
import exceptions.NoSuchElementException;
import fileOpperations.Xml;
import visualization.terminal.Terminal;
import customer.*;

public class Menu {
	private static List<Products> theList = new ArrayList<Products>();
	
	public static void setList(List<Products> ChangeList) {
		theList = ChangeList;
	}
	public static List<Products> getList(){
		return theList;
	}
	
	//
	public static class isearch {
		private static List<Integer> letsfindTheRightElement(String criterium) throws NoSuchElementException {
			int index = 0;
			List<Integer> RightFindedIndexofList = new ArrayList<Integer>();
			
			for(Products SearchedProduct : theList ) {
				if((SearchedProduct.getName()).toLowerCase().matches("(.*)"+criterium+"(.*)")) {
					RightFindedIndexofList.add(index);
				}
				index++;
			}
			if(RightFindedIndexofList.isEmpty()) {
				throw new NoSuchElementException();
			}else {
				return RightFindedIndexofList;
			}
		}
		private static String tryToReplaceAllUnnecessarySpaces(String input) {
			input.toLowerCase();
			input.trim().replaceAll(" +", " ");
			return input;
		}
		
		public static void Display() {
			List<Integer> RightFindedIndexofList = new ArrayList<Integer>();
			
			//System.out.print("K�rem irja be a term�k nev�t: ");
			
			try {
				if(theList.isEmpty()) throw new NoProductAvailableException();
				System.out.print("Please type in the name of the product: ");
				String name = Terminal.operation.enterString(2, 15,true);
				
				name = tryToReplaceAllUnnecessarySpaces(name);
				RightFindedIndexofList = letsfindTheRightElement(name);
				
				for(int i : RightFindedIndexofList) {
					Products local = theList.get(i);
					local.writeDownTheParameters();
					System.out.println("\n");
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} catch (NoProductAvailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	static class iDelete{
		public static void display() {
		int j = 0;
		boolean continueDelete = true;
		
		System.out.print("Are you sure you want to continue deletion:\n");
		
		if(Terminal.operation.enterBoolean()) {
			do {
				if(j%5 == 0 || j == 0) {
					for (int i = 0; i < theList.size(); i++) {
						Products thistag = theList.get(i);
						//System.out.print(i+1+".elem:\n");***********
						System.out.print(i+1+".item:\n");
						thistag.writeDownTheParameters();
						if(i==theList.size()-1) {
							System.out.println("\n");
						}
					}
				}
				
				//System.out.print("Melyik elemet szeretn� t�r�lni:");
				System.out.print("Which item do you wish to delete:");
				int decision = Terminal.operation.enterInteger(1, theList.size()) - 1;
				
				//System.out.print("Biztos t�r�lni szeretn� az elemet:\n");
				System.out.print("Are you sure you want to delete this item:\n");
				
				if(Terminal.operation.enterBoolean()) {
					Products thisProduct = theList.get(decision);
					if(Xml.product.deleteNote(thisProduct.getName(),thisProduct.getCategorical(),"name")) {
						theList.remove(thisProduct);
						//System.out.print("T�rl�s sikeres!\n");
						System.out.print("Deletion successful!\n");
					}else {
						//System.out.print("T�rl�s sikertelen a: "+thisProduct.getName()+"\n");
						System.out.print("Deletion was unsuccesful for this item: "+thisProduct.getName()+"\n");
					}
				}
				if(theList.isEmpty()) {
					//System.out.println("Nincs t�bb item!")
					System.out.println("There are no more items!");
					continueDelete = false;
				}else {
					//System.out.println("Szeretn� folytatni:");
					System.out.println("Would you like to continue:");
					continueDelete = Terminal.operation.enterBoolean();
				}
					j++;
				}while(continueDelete);
		}
		
		}
	}
	
	
	
	public static class echoProd{
		
		public static void display() {
			try {
				if(theList.isEmpty()) {
					//System.out.print("Nincs term�k!\n");
					throw new NoProductAvailableException();
				}
			
				for (Products i : theList) {
					i.writeDownTheParameters();
					System.out.print("\n");
				}
			}catch(NoProductAvailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static class inputProd{
		
		private static Products readTheNewProducts(int type){
			Products newProduct = null;
					
			//System.out.println("Irja be az eszk�z nev�t: ");
			System.out.println("Please type in the name of the product: ");
			String name = Terminal.operation.enterString(2,15,true);
			//System.out.println("Irja be a eszk�z gy�rt�j�t: ");
			System.out.println("Please type in the name of the manufacturer: ");
			String producer = Terminal.operation.enterString(2,15,true);
			//System.out.println("Irja be a eszk�z mennyis�g�t: ");
			System.out.println("Please type in the quantity of the product: ");
			int quantity = Terminal.operation.enterInteger(0,10000);
			//System.out.println("Irja be a eszk�z �r�t (Ft-ban): ");	//gross, net
			System.out.println("Please write the net price of the product(HUF): ");
			int price = Terminal.operation.enterInteger(0,500000);
				
			switch(type) {
				case 1:
					//System.out.println("Irja be az eszk�z tipus�t");
					System.out.println("Please type in the type of the product");
					String productType = Terminal.operation.enterString(3,15,true);
					newProduct = new Other(name, producer, quantity, price, productType);
					break;
				case 2:
					//System.out.println("Irja be a core sebess�g(Mhz): ");
					System.out.println("Please type in the speed of the core(Mhz): ");
					int cpuCore = Terminal.operation.enterInteger(100,5000);
					//System.out.println("Irja be a foglalatot: ");
					System.out.println("Please type in the socket type: ");
					String socket = Terminal.operation.enterString(2,10,true);
					System.out.println("Please type in the number of cpu cores: ");
					int coreNumber = Terminal.operation.enterInteger(1,16);
					//System.out.println("Irja be a gy�rt�si tekni�t(nm): ");
					System.out.println("Please type in the manufacturer's technology(nanometer): ");
					int manufactorytech = Terminal.operation.enterInteger(14,34);
							
					newProduct = new ComponentsCPU(name, producer, quantity, price, cpuCore, socket, coreNumber, manufactorytech);
							
					break;
				case 3:
					//System.out.println("Irja be a core sebess�g(Mhz): ");
					System.out.println("Please type in the speed of the gpu core(Mhz): ");
					int gpuCore = Terminal.operation.enterInteger(100,5000);
					//System.out.println("Irja be a Ramot (Mb): ");
					System.out.println("Please type in the size of ram(in MegaBytes): ");
					int ram = Terminal.operation.enterInteger(250,8200);
					//System.out.println("Irja be a mem�ria sebess�g: ");
					System.out.println("Please type in the speed of ram: ");
					int memoriaSpeed = Terminal.operation.enterInteger(250,8200);
					//System.out.println("Irja be a mem�ria bussz sebess�ge ");
					System.out.println("Please type in speed of memory bus: ");
					int busSpeed = Terminal.operation.enterInteger(250,8200);
					//System.out.println("Irja be a mem�ria bussz tip�s�t: ");
					System.out.println("Please type in the memory bus' type: ");
					String busType= Terminal.operation.enterString(2,10,true);
							
					newProduct = new ComponentsGPU(name, producer, quantity, price, gpuCore, ram, memoriaSpeed, busSpeed, busType);
							
					break;
			}
		return newProduct;
	}
	
	public static void display() {
			int menuPoint = 0;
			List<Products> listOfNewProducts = new ArrayList<Products>();
					
			while(menuPoint != 4) {
				menuPoint = 0;
				//String menu[] = {"Egy�bb term�k","CPU ","GPU ","kil�p�s"};
				String menu[] = {"Other product","CPU ","GPU ","exit"};
				menuPoint = Terminal.operation.writeDownMenuAndChooseOne(menu,false);
				if(menuPoint != 4) {
					listOfNewProducts.add(readTheNewProducts(menuPoint));
						
					//String menutwo[] = {"Folytatja","kil�p�s"};
					String menutwo[] = {"Continue","exit"};
					menuPoint = Terminal.operation.writeDownMenuAndChooseOne(menutwo,false);
				}
				
				if((menuPoint == 2)||(menuPoint==4 && !listOfNewProducts.isEmpty())) {
					//String menuthree[] = {"Ment�s","Kil�p�s ment�s n�lk�l"};
					String menuthree[] = {"Save","exit without save"};
					
					menuPoint = Terminal.operation.writeDownMenuAndChooseOne(menuthree,false);
					if(menuPoint == 1) {
						theList.addAll(listOfNewProducts);
						Xml.product.writer(listOfNewProducts);
						break;
					}
				}
			}
		}
	}
	
	public static class buying{
		public static void display() {
			boolean continueBuying = true;
			String name;
			//CustomerParser customerParser = new CustomerParser();
			
			do {
				/*if(!customerParser.tryToLoad())
					System.out.println(
							"Error loading customer list, perhaps the file does not exist, or there are no customers registered currently!");*/
			try {
				if(theList.isEmpty()) {
					continueBuying = false;
					throw new NoProductAvailableException();
				}
				
				int j = 0;
				if(continueBuying) {
					List<CustomerOrder> BuyingList = new ArrayList<CustomerOrder>();
					System.out.print("Please type in the name of the customer:\n");  // ez a ket sor majd kifog kerulni, mivel semmi szukseg ra. Helyere a felhasznalo regisztralt neve fog kerulni!
					name = Terminal.operation.enterString(3, 15,false);
					
					/*List<Customer> customerList = customerParser.findCostumers(name);
					
					if(!customerList.isEmpty()) {
						System.out.println("Registered customers with that name:");
						for (Customer x : customerList) {
							System.out.println(x.toString());
						}
					}
					else { System.out.println(
							"No customers registered with that name, would you like to register a new customer?");
						if(Terminal.operation.enterBoolean()) {
							Customer customer = Customer.registerNewCustomer(name);
							customerParser.getLoadedPeople().add(customer);
						}
					}*/
					if(j%5 == 0 || j == 0) {
						for (int i = 0; i < theList.size(); i++) {
							Products thistag = theList.get(i);
							//System.out.print(i+1+".elem:\n");***********
							System.out.print(i+1+".item:\n");
							thistag.writeDownTheParameters();
							if(i==theList.size()-1) {
								System.out.println("\n");
							}
						}
					}
					System.out.print("Which product do you want to buy? (writing zero will make you exit):");
					int thisProductIWantBuy = Terminal.operation.enterInteger(0, theList.size())-1;
					
					if(thisProductIWantBuy !=-1) {
						int count = 1;
						CustomerOrder newOrder = new CustomerOrder(name,theList.get(thisProductIWantBuy).getName(),count,theList.get(thisProductIWantBuy).getPrice());
						BuyingList.add(newOrder);
					
						System.out.println("Do you want to continue buying?");
						continueBuying = Terminal.operation.enterBoolean();
					}else {
						continueBuying = false;
					}
					if(!continueBuying && thisProductIWantBuy !=-1) {
						if(BuyingList.size()>1) {
							System.out.println("Are you sure you want to buy these products?");
						}else {
							System.out.println("Are you sure you want to buy this product?");
						}
						
						if(Terminal.operation.enterBoolean()) {
							Xml.buyerOrder.open();
							Xml.buyerOrder.writer(BuyingList);
						}
						
					}
					
					j++;
				}
				
				}catch(NoProductAvailableException e) {
					System.out.print("No products available currently, please visit bakc later!\n");
					e.printStackTrace();
				}
			//customerParser.tryToSave(customerParser.getLoadedPeople());
			}while(continueBuying);
		}
		public static void login() {
			
		}
	}
}

