package product.terminal;

import java.util.ArrayList;
import java.util.List;

import product.classes.Products;
import product.exceptions.NoProductAvailableException;
import product.terminal.Menu;
import product.xml.Xml;

public class ProductMenu {
	private List<Products> theList = new ArrayList<Products>();
	
	public void theProductMenu_MainMenu() {
		boolean continueTask = true;
		boolean fileExist = false;
		
		Terminal.openScanner();
		fileExist = Xml.open();
		theList = Xml.reader();
		Menu.setList(theList);
		
		do {
			try {
				if(theList.isEmpty()) {
					throw new NoProductAvailableException();
				}
				if(fileExist) {
					String[] menuBar = {"Uj adatfelvetele","Termékek kiirása","Termék törlése","Termék keresése","Kilépés"};
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
						e.printStackTrace();
						System.out.print("\nEnter the new products:");
						Menu.inputProd.display();
			}
		}while(continueTask);
		
		
		Terminal.closeScanner();
	}
}
