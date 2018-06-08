package product.terminal;

import java.util.ArrayList;
import java.util.List;

import product.classes.Products;

public class ProductMenu {
	private List<Products> theList = new ArrayList<Products>();
	
	public void theProductMenu_MainMenu() {
		boolean continueTask = true;
		
		do {
			String[] menuBar = {"Uj adatfelvetele","Term�kek kiir�sa","Term�k t�rl�se","Term�k keres�se","Kil�p�s"};
			int decision = ProductReader.writeDownMenuAndChooseOne(menuBar, true);
			SearcherMenu lm= new SearcherMenu(theList);
			
			switch(decision) {
				case 1:
					ProductReader.newItemsMenuPoint();
					break;
				case 2:
					ListaMenu.writerMenu(theList);
					break;
				case 3:DeleteItemMenu.itemDeletemenu(theList);
					break;
				case 4:
					lm.SearcherMenuOption();
					break;
				case 5:
					continueTask = false;
					break;
			}
		}while(continueTask);
	}
}
