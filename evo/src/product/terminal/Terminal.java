package product.terminal;

import java.util.InputMismatchException;
import product.exceptions.*;
import java.util.Scanner;

public class Terminal{
	private static Scanner scanner;
	public static void openScanner() {
		scanner = new Scanner(System.in);
	}
	 
	public static void closeScanner() {
		scanner.close();
	}
	
	public static class operation{
		
		public static int enterInteger(int min, int max) {
			boolean notright = true;
			int theIntegerValue = 0;
			do {
				try {
					theIntegerValue = Integer.parseInt(scanner.nextLine().replace(" ", ""));
					notright = false;
					
					if(theIntegerValue<min) {
						throw new LessThanMinNumException();
					}
					if(theIntegerValue>max) {
						throw new LargerThanMaxNumException();
					}
				}catch(NumberFormatException | LessThanMinNumException | LargerThanMaxNumException e) {
					e.printStackTrace();
					System.out.print("Min value is: "+min+" .Max value is:"+max+"\n");
					notright = true;
				}
			}while(notright);
			return theIntegerValue;
		}
		
		public static boolean enterBoolean() {
			//System.out.print("1 - igen 2 - nem:");
			System.out.print("1 - yes 2 - no:");
			int decision = enterInteger(1, 2);
			
			if(decision==1) {
				return true;
			}else {
				return false;
			}
			
		}
		
		public static double enterDouble(int min, int max) {
			double theDoubleValue = min;
			boolean notRight = true;
			do {
				try {
					theDoubleValue = Double.parseDouble(scanner.nextLine().replace(",","."));

					
					if(theDoubleValue<min) {
						throw new LessThanMinNumException();
						
					}
					if(theDoubleValue>max) {
						throw new LargerThanMaxNumException();
					}
				}catch(InputMismatchException | LessThanMinNumException | LargerThanMaxNumException e) {
					notRight = true;
					e.printStackTrace();
					System.out.print("Min value is: "+min+" .Max value is:"+max+"\n");
				}
			}while(notRight);
			return theDoubleValue;
		}
		
		public static String enterString(int min, int max) {
			String theStringInput = null;
			boolean notright = true;
			do {
				try {
					theStringInput = scanner.nextLine();
					notright = false;
					
					if(theStringInput.length()<min) {
						throw new ShorterThanMinTextExeption();
					}
					if(theStringInput.length()>max) {
						throw new LongerThanMaxTextExeption();
					}
				}catch(InputMismatchException | ShorterThanMinTextExeption | LongerThanMaxTextExeption e) {
					notright = true;
					e.printStackTrace();
					System.out.print("\nMin lenght is: "+min+" max lenght is:"+max+"\n");
				}
			}while(notright);
			return theStringInput;
		}
		
		public static int writeDownMenuAndChooseOne(String menu[],boolean spacebreak) {
			for(int i = 0; i < menu.length; i++) {
				if(spacebreak) {
					System.out.print((i+1)+". "+menu[i]+"\n");
				}else {
					System.out.print((i+1)+". "+menu[i]+" ");
					if(i == menu.length-1) {
						System.out.print("\n");
					}
				}
				
			}
			System.out.print("Type in the menu's number you wish to enter:");
			int choosed = enterInteger(1,menu.length);
			return choosed;
		}
	}
	 
}
