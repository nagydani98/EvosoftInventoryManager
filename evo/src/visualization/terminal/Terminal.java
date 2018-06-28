package visualization.terminal;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import exceptions.*;

public class Terminal{
	private static Scanner scanner;
	public static void openScanner() {
		scanner = new Scanner(System.in);
	}
	 
	public static void closeScanner() {
		scanner.close();
	}
	//alma
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
		
		public static String enterString(int min, int max, boolean availableSpecialCharacter) {
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
				
				if(availableSpecialCharacter) {
					return theStringInput;
				}else {
					if(isItContainSpecialCharacter(theStringInput)) {
						System.out.print("Unfortunately, the Hungarian special characters aren't available here!\n");
						theStringInput = enterStringWithoutSpecialChar(theStringInput);
						System.out.print("Date loaded in this form:"+theStringInput+"\n");
						return theStringInput;
					}
					return theStringInput;
				}
		}
		
		private static boolean isItContainSpecialCharacter(String newStr) {
			char[] criterium = {'�','�','�','�','�','�','�'};
			for(int i = 0;i<criterium.length;i++) {
				if(newStr.matches("(.*)"+criterium[i]+"(.*)"))return true;
			}
			
			return false;
		}
		
		private static String enterStringWithoutSpecialChar(String newStr) {
			//�,�,�,�,�,�,�,�
			newStr = newStr.replace("�", "a").replace("�", "A").replace("�", "e").replace("�", "E").replace("�", "i").replace("�", "I").replace("�", "o").replace("�", "O");
			newStr = newStr.replace("�", "o").replace("�", "o").replace("�", "u").replace("�", "U").replace("�", "U").replace("�", "U");
			return newStr;
		}
		
		//Jelszo beolvasasa igen eclipse terminal alat is, allitolag toketesen megy MAC-on is.
		public static String enterSecred() {
			final String password, message = "Please enter your password";
			if( System.console() == null ) 
			{ // inside IDE like Eclipse or NetBeans	- ezt a verziot lehet hasznalni eclipsebe is, kicsit problemas volt megtalalni
			  final JPasswordField pf = new JPasswordField(); 	//- neve swing modszer, ha valaki kivancsi ra
			  password = JOptionPane.showConfirmDialog( null, pf, message,
			    JOptionPane.OK_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? 
			      new String( pf.getPassword() ) : "";
			}
			else 
			  password = new String( System.console().readPassword( "%s> ", message ) );	//ez a modszer nem mukodik Eclipse IDE alatt
			
			return password;
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
