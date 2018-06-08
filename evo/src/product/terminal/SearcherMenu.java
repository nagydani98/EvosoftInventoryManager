package product.terminal;

import java.util.ArrayList;
import java.util.List;

import product.classes.Products;

public class SearcherMenu {
	List<Products> theList = new ArrayList<Products>();
	
	public SearcherMenu(List<Products> theList){
		this.theList = theList;
	}
	
	public List<Integer> letsfindTheRightElement(String criterium) {
		int index = 0;
		List<Integer> RightFindedIndexofList = new ArrayList<Integer>();
		
		for(Products SearchedProduct : theList ) {
			if((SearchedProduct.getName()).matches("*"+criterium+"*")) {
				RightFindedIndexofList.add(index);
			}
			index++;
		}
		
		return RightFindedIndexofList;
	}
	public void SearcherMenuOption() {
		List<Integer> RightFindedIndexofList = new ArrayList<Integer>();
		
		System.out.print("Irja be a nevét: ");
		String name = ProductReader.enterString(4, 15);
		
		RightFindedIndexofList = letsfindTheRightElement(name);
		
		for(int i : RightFindedIndexofList) {
			Products local = theList.get(i);
			local.writeDownTheParameters();
			System.out.println("\n");
		}
	}
}
