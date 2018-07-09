package visualization.terminal;

import java.util.ArrayList;
import java.util.List;

import classes.product.Products;
import exceptions.NoProductAvailableException;
import visualization.terminal.Menu;

public class ProductMenu {
	private static List<Products> theList = new ArrayList<Products>();
	
	public static void setList(List<Products> input) {
		theList = input;
	}
	
	public static void theProductMenu_MainMenu() {
		boolean continueTask = true;
		
		/*Terminal.openScanner();
		theList = Xml.product.reader();
		*/
		
		do {
			boolean noMoreProd = false;
			try {
				if(theList.isEmpty()) {
					throw new NoProductAvailableException();
				}else {
					//String[] menuBar = {"Uj adatfelvetele","Term�kek kiir�sa","Term�k t�rl�se","Term�k keres�se","Kil�p�s"};
					String[] menuBar = {"List products","Search for product","Create new product","Delete product","Back"};
					int decision = Terminal.operation.writeDownMenuAndChooseOne(menuBar, true);
					switch(decision) {
						case 1:
							Menu.echoProd.display();
							break;
						case 2:
							Menu.isearch.Display();
							break;
						case 3:
							Menu.inputProd.display();
							theList = Menu.getList();
							break;
						case 4:
							Menu.iDelete.display();
							theList = Menu.getList();
							break;
						case 5:
							continueTask = false;
							break;
					}
				}
			}catch(NoProductAvailableException e) {
						noMoreProd = true;
						e.printStackTrace();
			}
			if(noMoreProd) {
				System.out.print("Enter the new products:\n");
				Menu.inputProd.display();
				theList = Menu.getList();
				if(theList.isEmpty()) {
					continueTask = false;
				}
			}
		}while(continueTask);
		
		
		//Terminal.closeScanner();
	}
}
