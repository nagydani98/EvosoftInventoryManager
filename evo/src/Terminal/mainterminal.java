package Terminal;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class mainterminal {
//main terminal, ezt nem kell szerintem kiszervezni külön classba
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		terminal.println("Indul");
		
		terminal.dispose();
		textIO.dispose();
		
	}

}