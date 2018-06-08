package product.teszt;

import java.util.Scanner;

import product.classes.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class productReader {
	//Termékek, itt olvasom be a termékek adatait és itt elenõrzöm le a helyességüket.
	
	public static int enterInteger(int min, int max) {
		Scanner scanner = new Scanner(System.in);
		int theIntegerValue = min;
		boolean notright = true;
		do {
			try {
				theIntegerValue = Integer.parseInt(scanner.nextLine());
				notright = false;
				
				if(theIntegerValue<min) {
					System.out.print("Az érték kisebb mint a minimum: "+min+"\n");
					notright = true;
				}
				if(theIntegerValue>max) {
					System.out.print("Az érték nagyobb mint a maximum: "+max+"\n");
					notright = true;
				}
			}catch(InputMismatchException e) {
				System.out.print("Kérem irjon be egy egész szám értéket\n");
			}
		}while(notright);
		scanner.close();
		return theIntegerValue;
	}
	
	public static double enterDouble(int min, int max) {
		Scanner scanner = new Scanner(System.in);
		double theDoubleValue = min;
		boolean notright = true;
		do {
			try {
				theDoubleValue = Double.parseDouble(scanner.nextLine().replace(",","."));
				notright = false;
				
				if(theDoubleValue<min) {
					System.out.print("Az érték kisebb mint a minimum: "+min+"\n");
					notright = true;
				}
				if(theDoubleValue>max) {
					System.out.print("Az érték nagyobb mint a maximum: "+max+"\n");
					notright = true;
				}
			}catch(InputMismatchException e) {
				System.out.print("Kérem irjon be egy egész vagy tört értéket\n");
			}
		}while(notright);
		scanner.close();
		return theDoubleValue;
	}
	
	public static String enterString(int min, int max) {
		Scanner scanner = new Scanner(System.in);
		String theStringInput = null;
		boolean notright = true;
		do {
			try {
				theStringInput = scanner.nextLine();
				notright = false;
				
				if(theStringInput.length()<min) {
					System.out.print("A szöveg hossz kisebb mint a minimum érték: "+min+"\n");
					notright = true;
				}
				if(theStringInput.length()>max) {
					System.out.print("A szöveg hossz nagyobb mint a maximum érték: "+max+"\n");
					notright = true;
				}
			}catch(InputMismatchException e) {
				System.out.print("Nem megfelelõ karakter\n");
			}
		}while(notright);
		scanner.close();
		return theStringInput;
	}
	
	public static int writeDownMenuAndChooseOne(String menu[]) {
		for(int i = 0; i < menu.length; i++) {
			System.out.print(menu[i]+" - "+ (i+1) +"  ");
		}
		System.out.println("\n");
		return enterInteger(1,menu.length)-1;
	}

	public static Products readTheNewProducts(int type){
		Products newProduct = null;
		
		System.out.println("Irja be az eszköz nevét: ");
		String name = enterString(2,15);
		System.out.println("Irja be a eszköz gyártóját: ");
		String producer = enterString(2,15);
		System.out.println("Irja be a eszköz mennyiségét: ");
		int quantity = enterInteger(0,10000);
		System.out.println("Irja be a eszköz árát: ");
		int price = enterInteger(0,500000);
		switch(type) {
			case 1:
				System.out.println("Irja be az eszköz tipusát");
				String productType = enterString(3,15);
				newProduct = new Other(name, producer, quantity, price, productType);
				break;
			case 2:
				System.out.println("Irja be a core sebesség(Mhz): ");
				int cpuCore = enterInteger(100,5000);
				System.out.println("Irja be a foglalatot: ");
				String socket = enterString(2,10);
				System.out.println("Irja be a CPU magok szám: ");
				int coreNumber = enterInteger(1,16);
				System.out.println("Irja be a gyártási tekniát(nm): ");
				int manufactorytech = enterInteger(14,34);
				
				newProduct = new ComponentsCPU(name, producer, quantity, price, cpuCore, socket, coreNumber, manufactorytech);
				
				break;
			case 3:
				System.out.println("Irja be a core sebesség(Mhz): ");
				int gpuCore = enterInteger(100,5000);
				System.out.println("Irja be a Ramot (Mb): ");
				int ram = enterInteger(250,8200);
				System.out.println("Irja be a memória sebesség: ");
				int memoriaSpeed = enterInteger(250,8200);
				System.out.println("Irja be a memória bussz sebessége ");
				int busSpeed = enterInteger(250,8200);
				System.out.println("Irja be a memória bussz tipúsát ");
				String busType= enterString(2,10);
				
				newProduct = new ComponentsGPU(name, producer, quantity, price, gpuCore, ram, memoriaSpeed, busSpeed, busType);
				
				break;
		}
		
		return newProduct;
	}
	
	public static void newItemsMenuPoint() {
		int menuPoint = 0;
		List<Products> listOfNewProducts = new ArrayList<Products>();
		
		while(menuPoint != 5) {
			String menu[] = {"Egyébb termék","CPU","GPU","kilépés"};
			menuPoint = writeDownMenuAndChooseOne(menu);
			if(menuPoint != 5) {
				listOfNewProducts.add(readTheNewProducts(menuPoint));
			
				String menutwo[] = {"Folytatja","vissza"};
				menuPoint = writeDownMenuAndChooseOne(menutwo);
			
				if(menuPoint == 2) {
				
					String menuthree[] = {"Mentés","Kilépés mentés nélkül"};
					menuPoint = writeDownMenuAndChooseOne(menuthree);
				
					if(menuPoint == 1) {
						menuPoint=5;
					}else {
					
					}
				}
			}
		}
	}
}
