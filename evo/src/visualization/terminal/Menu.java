package product.terminal;

import java.util.ArrayList;
import java.util.List;

import product.classes.ComponentsCPU;
import product.classes.ComponentsGPU;
import product.classes.Other;
import product.classes.Products;
import product.exceptions.NoSuchElementException;
import product.xml.Xml;
import product.terminal.Terminal;

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
				if((SearchedProduct.getName()).matches("(.*)"+criterium+"(.*)")) {
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
		public static void Display() {
			List<Integer> RightFindedIndexofList = new ArrayList<Integer>();
			
			//System.out.print("Kérem irja be a termék nevét: ");
			System.out.print("Please write the name of the product: ");
			String name = Terminal.operation.enterString(4, 15,true);
			
			try {
				RightFindedIndexofList = letsfindTheRightElement(name);
				for(int i : RightFindedIndexofList) {
					Products local = theList.get(i);
					local.writeDownTheParameters();
					System.out.println("\n");
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	static class iDelete{
		public static void display() {
		int j = 0;
		boolean continueDelete = true;
		
		System.out.print("Are you sure you want continue delete:\n");
		
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
				
				//System.out.print("Melyik elemet szeretné törölni:");
				System.out.print("Which item would you delete:");
				int decision = Terminal.operation.enterInteger(1, theList.size()) - 1;
				
				//System.out.print("Biztos törölni szeretné az elemet:\n");
				System.out.print("Are you sure to delete this item:\n");
				
				if(Terminal.operation.enterBoolean()) {
					Products thisProduct = theList.get(decision);
					if(Xml.deleteNote(thisProduct.getName(),thisProduct.getCategorical(),"name")) {
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
				}while(continueDelete);
		}
		
		}
	}
	
	
	
	public static class echoProd{
		
		public static void display() {
			for (Products i : theList) {
				i.writeDownTheParameters();
				System.out.print("\n");
			}
			if(theList.isEmpty()) {
				//System.out.print("Nincs termék!\n");
				System.out.print("We don't have product!\n");
			}
		}
	}
	
	
	
	static class inputProd{
		
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
						Xml.writer(listOfNewProducts);
						break;
					}
				}
			}
		}
	}
}

