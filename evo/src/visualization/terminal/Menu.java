package visualization.terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Terminal.mainterminal;
import classes.product.ComponentsCPU;
import classes.product.ComponentsGPU;
import classes.product.Other;
import classes.product.Products;
import customer.Customer;
import customer.CustomerParser;
import classes.order.*;
import exceptions.NoProductAvailableException;
import exceptions.NoSuchElementException;
import fileOpperations.Xml;
import visualization.terminal.Terminal;

public class Menu {
	private static List<Products> theList = new ArrayList<Products>();
	
	public static void setList(List<Products> ChangeList) {
		theList = ChangeList;
	}
	public static List<Products> getList(){
		return theList;
	}
	
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
			
			//System.out.print("Kérem irja be a termék nevét: ");
			
			try {
				if(theList.isEmpty()) throw new NoProductAvailableException();
				System.out.print("Please write the name of the product: ");
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
		
			do {
				continueDelete = false;
				if(j%5 == 0 || j == 0) {
					for (int i = 0; i < theList.size(); i++) {
						Products thistag = theList.get(i);
						//System.out.print(i+1+".elem:\n");***********
						System.out.print(i+1+".item:\n");
						thistag.writeDownTheParameters();
						if(i!=theList.size()-1) {
							System.out.println("\n");
						}
					}
				}
				
				//System.out.print("Melyik elemet szeretné törölni:");
				System.out.print("Which item would you delete(if you write zero, you'll exit):");
				int decision = Terminal.operation.enterInteger(0, theList.size()) - 1;
				if(decision != -1) {
				//System.out.print("Biztos törölni szeretné az elemet:\n");
					System.out.print("Are you sure to delete this item:\n");
				
					if(Terminal.operation.enterBoolean()) {
						Products thisProduct = theList.get(decision);
						if(Xml.product.deleteNote(thisProduct.getName(),thisProduct.getCategorical(),"name")) {
							theList.remove(thisProduct);
							//System.out.print("Törlés sikeres!\n");
							System.out.print("Delete is successful!\n");
						}else {
						//System.out.print("Törlés sikertelen a: "+thisProduct.getName()+"\n");
						System.out.print("Delete is unsuccesful for this item: "+thisProduct.getName()+"\n");
						}
					}
				
				if(theList.isEmpty()) {
					//System.out.println("Nincs több item!")
					System.out.println("No more item!");
					continueDelete = false;
				}else {
					//System.out.println("Szeretné folytatni:");
					System.out.println("Would you like to continue it:");
					continueDelete = Terminal.operation.enterBoolean();
				}
					j++;
				}
				}while(continueDelete);
			}
		
	}
	
	
	
	public static class echoProd{
		
		public static void display() {
			try {
				if(theList.isEmpty()) {
					//System.out.print("Nincs termék!\n");
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
					
			//System.out.println("Irja be az eszköz nevét: ");
			System.out.println("Please write the name of the product: ");
			String name = Terminal.operation.enterString(2,15,true);
			//System.out.println("Irja be a eszköz gyártóját: ");
			System.out.println("Please write the name of the producer: ");
			String producer = Terminal.operation.enterString(2,15,true);
			//System.out.println("Irja be a eszköz mennyiségét: ");
			System.out.println("Please write the amount of the product: ");
			int quantity = Terminal.operation.enterInteger(0,10000);
			//System.out.println("Irja be a eszköz árát (Ft-ban): ");	//gross, net
			System.out.println("Please write the net price of the product(in Ft-s): ");
			int price = Terminal.operation.enterInteger(0,500000);
				
			switch(type) {
				case 1:
					//System.out.println("Irja be az eszköz tipusát");
					System.out.println("Please write type of the product");
					String productType = Terminal.operation.enterString(3,15,true);
					newProduct = new Other(name, producer, quantity, price, productType);
					break;
				case 2:
					//System.out.println("Irja be a core sebesség(Mhz): ");
					System.out.println("Please write the speed of the core(Mhz): ");
					int cpuCore = Terminal.operation.enterInteger(100,5000);
					//System.out.println("Irja be a foglalatot: ");
					System.out.println("Please write the socket: ");
					String socket = Terminal.operation.enterString(2,10,true);
					System.out.println("Please write the numbers of the cpu cores: ");
					int coreNumber = Terminal.operation.enterInteger(1,16);
					//System.out.println("Irja be a gyártási tekniát(nm): ");
					System.out.println("Please write the manufacturers technology(nano milimeter): ");
					int manufactorytech = Terminal.operation.enterInteger(14,34);
							
					newProduct = new ComponentsCPU(name, producer, quantity, price, cpuCore, socket, coreNumber, manufactorytech);
							
					break;
				case 3:
					//System.out.println("Irja be a core sebesség(Mhz): ");
					System.out.println("Please write the speed of the gpu core(Mhz): ");
					int gpuCore = Terminal.operation.enterInteger(100,5000);
					//System.out.println("Irja be a Ramot (Mb): ");
					System.out.println("Please write the size of ram(Mega Byte): ");
					int ram = Terminal.operation.enterInteger(250,8200);
					//System.out.println("Irja be a memória sebesség: ");
					System.out.println("Please write the speed of ram: ");
					int memoriaSpeed = Terminal.operation.enterInteger(250,8200);
					//System.out.println("Irja be a memória bussz sebessége ");
					System.out.println("Please write speed of memory buss: ");
					int busSpeed = Terminal.operation.enterInteger(250,8200);
					//System.out.println("Irja be a memória bussz tipúsát: ");
					System.out.println("Please write the type of memory: ");
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
				//String menu[] = {"Egyébb termék","CPU ","GPU ","kilépés"};
				String menu[] = {"Other product","CPU ","GPU ","exit"};
				menuPoint = Terminal.operation.writeDownMenuAndChooseOne(menu,false);
				if(menuPoint != 4) {
					listOfNewProducts.add(readTheNewProducts(menuPoint));
						
					//String menutwo[] = {"Folytatja","kilépés"};
					String menutwo[] = {"Continue","exit"};
					menuPoint = Terminal.operation.writeDownMenuAndChooseOne(menutwo,false);
				}
				
				if((menuPoint == 2)||(menuPoint==4 && !listOfNewProducts.isEmpty())) {
					//String menuthree[] = {"Mentés","Kilépés mentés nélkül"};
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
		private static String account;
		private static boolean login;
		
		public static void logout(){
			login = false;
		}
		public static boolean getLogout() {
			return login;
		}
		
		//eredeti elkebzelesel ellentetbe. Amikor bejottek az email cim alapjan valo belepes. Akkor vele egyutt bejott egy login rendszer, nos azonban az objectumba sok adatag kerult
		//igy az eredeti elkebzelesel szemben erdemesebb lenne nem staticba csinalni, visszont az utolso nap mar nem akartam atirni es peldanyositani az egeszet
		
		private static boolean checkTheAccount() {
			CustomerParser cp = new CustomerParser();
			if(!cp.tryToLoad()) return false;
			List<Customer> customer  = cp.getLoadedPeople();
			
			List<Customer> result = customer.stream()
				     .filter(item -> item.getEmail().equals(account))
				     .collect(Collectors.toList());
			
			if(result.isEmpty())
					 return false;	
			return true;
		}
		
		private static void accountPart() {
			String [] menu = {"Login","Registration","exit"};
			int choosed = Terminal.operation.writeDownMenuAndChooseOne(menu, false);
			CustomerParser parser = new CustomerParser();
			
			
			switch(choosed) {
				case 1:
					int tryCount = 1;
					do {
						System.out.println("Enter email:");
						account = Terminal.operation.enterEmail();
						if(!checkTheAccount()) {
							System.out.println("No such email contain in database!");
							if(tryCount % 3 == 0) {
								System.out.println("Do you want registered one?");
								if(Terminal.operation.enterBoolean()) {
									mainterminal.customerRegistration(parser);
									login = true;
									break;
								}else {
									break;
								}
							}
						}else {
							login = true;
							break;
						}
					
						tryCount++;
						
					}while(true);
					break;
				case 2:
					mainterminal.customerRegistration(parser);
					login = true;
					break;
				case 3:
					account = null;
					break;
			}
		}
		
		public static void display() {
			boolean continueBuying = true;
			CustomerOrder newOrder = null;
			
			String name = null;
			int j = 0;
			
			do {
			try {
				if(theList.isEmpty()) {
					continueBuying = false;
					throw new NoProductAvailableException();
				}
				
				if(!login)
					accountPart();
				
				if(account == null)
					continueBuying = false;
				
				if(continueBuying) {
					List<CustomerOrder> BuyingList = new ArrayList<CustomerOrder>();
					
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
					
					System.out.print("Which product do you want to buy? (if you write zero, you'll exit):");
					int thisProductIWantBuy = Terminal.operation.enterInteger(0, theList.size())-1;
					
					if(thisProductIWantBuy !=-1 ) {
						int count = 1;
						newOrder = new CustomerOrder(name,theList.get(thisProductIWantBuy).getName(),count,theList.get(thisProductIWantBuy).getPrice());
						BuyingList.add(newOrder);
					
						System.out.println("Do you want to continue the buying?");
						continueBuying = Terminal.operation.enterBoolean();
					}else {
						continueBuying = false;
					}
					
					if(newOrder != null) {
						BuyingList.add(newOrder);
					}
					
					if((!continueBuying && thisProductIWantBuy !=-1) ||(thisProductIWantBuy == -1 && !BuyingList.isEmpty())) {
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
					System.out.print("Please visit again a little bit later!\n");
					e.printStackTrace();
				}
			}while(continueBuying);
		}
	}
}

