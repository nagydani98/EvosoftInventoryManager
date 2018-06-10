package product.terminal;

import java.util.ArrayList;
import java.util.List;

import product.classes.ComponentsCPU;
import product.classes.ComponentsGPU;
import product.classes.Other;
import product.classes.Products;
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
	
	
	public static class isearch {
		private static List<Integer> letsfindTheRightElement(String criterium) {
			int index = 0;
			List<Integer> RightFindedIndexofList = new ArrayList<Integer>();
			
			for(Products SearchedProduct : theList ) {
				if((SearchedProduct.getName()).matches("*"+criterium+"*")) {
					RightFindedIndexofList.add(index);
				}
				index++;
			}
			
			return RightFindedIndexofList;
		}
		public static void Display() {
			List<Integer> RightFindedIndexofList = new ArrayList<Integer>();
			
			System.out.print("Irja be a nevét: ");
			String name = Terminal.operation.enterString(4, 15);
			
			RightFindedIndexofList = letsfindTheRightElement(name);
			
			for(int i : RightFindedIndexofList) {
				Products local = theList.get(i);
				local.writeDownTheParameters();
				System.out.println("\n");
			}
		}
	}
	
	
	
	static class iDelete{
		public static void display() {
		int j = 0;
		boolean continueDelete = false;
		do {
			if(j%5 == 0 || j == 0) {
				for (int i = 0; i < theList.size(); i++) {
					Products thistag = theList.get(i);
					System.out.print(i+1+".elem:\n");
					thistag.writeDownTheParameters();
					if(i==theList.size()-1) {
						System.out.println("\n");
					}
				}
			}
			
			System.out.print("Melyik elemet szeretné törölni:");
			int decision = Terminal.operation.enterInteger(1, theList.size()) - 1;
		
			System.out.print("Biztos törölni szeretné az elemet:\n");
			
			if(Terminal.operation.enterBoolean()) {
				Products thisProduct = theList.get(decision);
				if(Xml.deleteNote(thisProduct.getName(),thisProduct.getCategorical(),"name")) {
					theList.remove(thisProduct);
					System.out.print("Törlés sikeres!\n");
				}else {
					System.out.print("Törlés sikertelen a: "+thisProduct.getName()+"\n");
				}
			}
			if(!theList.isEmpty()) {
				System.out.println("Szeretné folytatni:");
				Terminal.operation.enterBoolean();
				continueDelete = true;
			}
			
			j++;
			}while(continueDelete);
		}
	}
	
	
	
	static class echoProd{
		
		public static void display() {
			for (Products i : theList) {
				i.writeDownTheParameters();
				System.out.print("\n");
			}
			if(theList.isEmpty()) {
				System.out.print("Nincs termék!\n");
			}
		}
	}
	
	
	
	static class inputProd{
		
		private static Products readTheNewProducts(int type){
			Products newProduct = null;
					
			System.out.println("Irja be az eszköz nevét: ");
			String name = Terminal.operation.enterString(2,15);
			System.out.println("Irja be a eszköz gyártóját: ");
			String producer = Terminal.operation.enterString(2,15);
			System.out.println("Irja be a eszköz mennyiségét: ");
			int quantity = Terminal.operation.enterInteger(0,10000);
			System.out.println("Irja be a eszköz árát (Ft-ban): ");
			int price = Terminal.operation.enterInteger(0,500000);
				
			switch(type) {
				case 1:
					System.out.println("Irja be az eszköz tipusát");
					String productType = Terminal.operation.enterString(3,15);
					newProduct = new Other(name, producer, quantity, price, productType);
					break;
				case 2:
					System.out.println("Irja be a core sebesség(Mhz): ");
					int cpuCore = Terminal.operation.enterInteger(100,5000);
					System.out.println("Irja be a foglalatot: ");
					String socket = Terminal.operation.enterString(2,10);
					System.out.println("Irja be a CPU magok szám: ");
					int coreNumber = Terminal.operation.enterInteger(1,16);
					System.out.println("Irja be a gyártási tekniát(nm): ");
					int manufactorytech = Terminal.operation.enterInteger(14,34);
							
					newProduct = new ComponentsCPU(name, producer, quantity, price, cpuCore, socket, coreNumber, manufactorytech);
							
					break;
				case 3:
					System.out.println("Irja be a core sebesség(Mhz): ");
					int gpuCore = Terminal.operation.enterInteger(100,5000);
					System.out.println("Irja be a Ramot (Mb): ");
					int ram = Terminal.operation.enterInteger(250,8200);
					System.out.println("Irja be a memória sebesség: ");
					int memoriaSpeed = Terminal.operation.enterInteger(250,8200);
					System.out.println("Irja be a memória bussz sebessége ");
					int busSpeed = Terminal.operation.enterInteger(250,8200);
					System.out.println("Irja be a memória bussz tipúsát ");
					String busType= Terminal.operation.enterString(2,10);
							
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
				String menu[] = {"Egyébb termék","CPU ","GPU ","kilépés"};
				menuPoint = Terminal.operation.writeDownMenuAndChooseOne(menu,false);
				if(menuPoint != 4) {
					listOfNewProducts.add(readTheNewProducts(menuPoint));
						
					String menutwo[] = {"Folytatja","kilépés"};
					menuPoint = Terminal.operation.writeDownMenuAndChooseOne(menutwo,false);
				}
				if(menuPoint == 1)menuPoint = 1;
				
				if((menuPoint == 2)||(menuPoint==4 && !listOfNewProducts.isEmpty())) {
					
					String menuthree[] = {"Mentés","Kilépés mentés nélkül"};
					menuPoint = Terminal.operation.writeDownMenuAndChooseOne(menuthree,false);
				}
				if(menuPoint == 1) {
					Xml.writer(listOfNewProducts);
				break;
				}else {
					break;
				}
				
			}
		}
	}
}

