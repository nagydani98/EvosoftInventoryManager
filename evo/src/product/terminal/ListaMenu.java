package product.terminal;

import java.util.List;

import	product.classes.*;

public class ListaMenu {
	public static void writerMenu(List <Products> productList) {
		for (int i = 0; i < productList.size(); i++) {
			Products thistag = productList.get(i);
			thistag.writeDownTheParameters();
			System.out.print("\n");
		}
		
	}
}
