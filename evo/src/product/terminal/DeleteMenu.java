package product.terminal;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import product.classes.Products;
import product.terminal.ProductReader;
import product.xml.DeleteXmlElement;

public class DeleteMenu {
	public static List <Products> itemDeletemenu(List <Products> productList) {
		do {
			for (int i = 0; i < productList.size(); i++) {
				Products thistag = productList.get(i);
				System.out.println(i+".elem:");
				thistag.writeDownTheParameters();
				System.out.println("\n");
			}
			
			System.out.println("Melyik elemet szeretné törölni:");
			int decision = ProductReader.enterInteger(1, productList.size()) - 1;
		
			System.out.println("Biztos törölni szeretné az elemet:");
			
			if(ProductReader.enterBoolean()) {
				Products thisProduct = productList.get(decision);
				try {
					DeleteXmlElement.deleteNoteFromTheXml(thisProduct.getName(),thisProduct.getCategorical(),"item");
					productList.remove(thisProduct);
				} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Szeretné folytatni:");
			
		}while(ProductReader.enterBoolean());
		
		return productList;
	}
}
