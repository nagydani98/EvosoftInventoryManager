package Terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalReaders {
	public static int readInt() {
		int num = 0;
		InputStreamReader inputStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inputStream);
		boolean unsuccessful = true;
		
		do {
			try {
				num = Integer.valueOf(input.readLine()).intValue();
				unsuccessful = false;
			} catch (NumberFormatException | IOException e) {
			}
		} while (unsuccessful);
		return num;
	}
	
	public static int readIntInRange(int floor, int ceiling) { 
		int num = -1;
		boolean unsuccessful = true;
		
		do {
			
				num = readInt();
				if(num <= ceiling && num >= floor)
					unsuccessful = false;
				else System.out.println("Invalid input");
				
		} while (unsuccessful);
		return num;
	}
}
