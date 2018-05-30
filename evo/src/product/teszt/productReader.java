package product.teszt;

import java.util.Scanner;

import product.classes.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class productReader {
	//Term�kek, itt olvasom be a term�kek adatait �s itt elen�rz�m le a helyess�g�ket.
	
	public static int enterInteger(int min, int max) {
		Scanner scanner = new Scanner(System.in);
		int theIntegerValue = min;
		boolean notright = true;
		do {
			try {
				theIntegerValue = Integer.parseInt(scanner.nextLine());
				notright = false;
				
				if(theIntegerValue<min) {
					System.out.print("Az �rt�k kisebb mint a minimum: "+min+"\n");
					notright = true;
				}
				if(theIntegerValue>max) {
					System.out.print("Az �rt�k nagyobb mint a maximum: "+max+"\n");
					notright = true;
				}
			}catch(InputMismatchException e) {
				System.out.print("K�rem irjon be egy eg�sz sz�m �rt�ket\n");
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
					System.out.print("Az �rt�k kisebb mint a minimum: "+min+"\n");
					notright = true;
				}
				if(theDoubleValue>max) {
					System.out.print("Az �rt�k nagyobb mint a maximum: "+max+"\n");
					notright = true;
				}
			}catch(InputMismatchException e) {
				System.out.print("K�rem irjon be egy eg�sz vagy t�rt �rt�ket\n");
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
					System.out.print("A sz�veg hossz kisebb mint a minimum �rt�k: "+min+"\n");
					notright = true;
				}
				if(theStringInput.length()>max) {
					System.out.print("A sz�veg hossz nagyobb mint a maximum �rt�k: "+max+"\n");
					notright = true;
				}
			}catch(InputMismatchException e) {
				System.out.print("Nem megfelel� karakter\n");
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
		
		System.out.println("Irja be az eszk�z nev�t: ");
		String name = enterString(2,15);
		System.out.println("Irja be a eszk�z gy�rt�j�t: ");
		String producer = enterString(2,15);
		System.out.println("Irja be a eszk�z mennyis�g�t: ");
		int quantity = enterInteger(0,10000);
		System.out.println("Irja be a eszk�z �r�t: ");
		int price = enterInteger(0,500000);
		switch(type) {
			case 1:
				System.out.println("Irja be az eszk�z tipus�t");
				String productType = enterString(3,15);
				newProduct = new Other(name, producer, quantity, price, productType);
				break;
			case 2:
				System.out.println("Irja be a core sebess�g(Mhz): ");
				int cpuCore = enterInteger(100,5000);
				System.out.println("Irja be a foglalatot: ");
				String socket = enterString(2,10);
				System.out.println("Irja be a CPU magok sz�m: ");
				int coreNumber = enterInteger(1,16);
				System.out.println("Irja be a gy�rt�si tekni�t(nm): ");
				int manufactorytech = enterInteger(14,34);
				
				newProduct = new ComponentsCPU(name, producer, quantity, price, cpuCore, socket, coreNumber, manufactorytech);
				
				break;
			case 3:
				System.out.println("Irja be a core sebess�g(Mhz): ");
				int gpuCore = enterInteger(100,5000);
				System.out.println("Irja be a Ramot (Mb): ");
				int ram = enterInteger(250,8200);
				System.out.println("Irja be a mem�ria sebess�g: ");
				int memoriaSpeed = enterInteger(250,8200);
				System.out.println("Irja be a mem�ria bussz sebess�ge ");
				int busSpeed = enterInteger(250,8200);
				System.out.println("Irja be a mem�ria bussz tip�s�t ");
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
			String menu[] = {"Egy�bb term�k","CPU","GPU","kil�p�s"};
			menuPoint = writeDownMenuAndChooseOne(menu);
			if(menuPoint != 5) {
				listOfNewProducts.add(readTheNewProducts(menuPoint));
			
				String menutwo[] = {"Folytatja","vissza"};
				menuPoint = writeDownMenuAndChooseOne(menutwo);
			
				if(menuPoint == 2) {
				
					String menuthree[] = {"Ment�s","Kil�p�s ment�s n�lk�l"};
					menuPoint = writeDownMenuAndChooseOne(menuthree);
				
					if(menuPoint == 1) {
						menuPoint=5;
					}else {
					
					}
				}
			}
		}
	}
	
	public static void main(String [] angs) {
		
	}
}
