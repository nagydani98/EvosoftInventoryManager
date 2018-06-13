package product.terminal;

import java.util.ArrayList;
import java.util.List;

import product.classes.Products;
import product.exceptions.NoProductAvailableException;
import product.terminal.Menu;
import product.xml.Xml;

public class ProductMenu {
	private static List<Products> theList = new ArrayList<Products>();
	
	public static void theProductMenu_MainMenu() {
		boolean continueTask = true;
		boolean fileExist = false;
		
		Terminal.openScanner();
		fileExist = Xml.open();
		theList = Xml.reader();
		Menu.setList(theList);
		
		do {
			boolean noMoreProd = false;
			try {
				if(theList.isEmpty()) {
					throw new NoProductAvailableException();
				}
				
				if(fileExist) {
					//String[] menuBar = {"Uj adatfelvetele","Termékek kiirása","Termék törlése","Termék keresése","Kilépés"};
					String[] menuBar = {"Create new product","List products","Delete product","Search for product","Exit"};
					int decision = Terminal.operation.writeDownMenuAndChooseOne(menuBar, true);
					switch(decision) {
						case 1:
							Menu.inputProd.display();
							theList = Menu.getList();
							break;
						case 2:
							Menu.echoProd.display();
							break;
						case 3:
							Menu.iDelete.display();
							theList = Menu.getList();
							break;
						case 4:
							Menu.isearch.Display();
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
