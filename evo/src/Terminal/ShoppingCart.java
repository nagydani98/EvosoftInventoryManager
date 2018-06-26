package Terminal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import customer.*;
import product.classes.Products;

//for now, I tried giving a basic outline of how I imagine the purchase process is going to go down
public class ShoppingCart {
	//static purchase list, we want to save this into an xml, it is effectively a list of receipts
	public static List<ShoppingCart> cartList = new ArrayList<>();
	private static String cartFilePath = "carts.xml"; 
	
	private Customer customer; //the customer to whom the cart/receipt belongs
	private List<Products> purchaseList= new ArrayList<>(); //the list of products that the customer "placed" in this cart
	
	
	
	public Customer getCustomer() {
		return customer;
	}



	public List<Products> getPurchaseList() {
		return purchaseList;
	}

	
	/*this method is supposed to do multiple things
	 * 1. it will create a shopping cart object, and add it to the static cartList
	 * 2. increase shopnumber of the given customer
	 * 3. reduce the remaining quantity of all products purchased by the customer
	 * 4. calculate the sum of money that needs paying
	 * 
	 * not necessarily in that order
	 */
	public void purchase() {
		
	}
	

	public boolean saveCarts() {
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			
			Element root = doc.createElement("Carts");
			
			
			doc.appendChild(root);
			
			
			for(int i = 0; i < cartList.size(); i++) {
				
				ShoppingCart shoppingCart = cartList.get(i);
				
				Element cartElement = doc.createElement("ShoppingCart"); //cart element created and appended under root(Carts)
				root.appendChild(cartElement);
				
				/*we want two children under this cart element, one is a customer, containing the data of the customer
				 * who made the purchase, the other is products, containing all products that were purchased
				 */
				
				//creating and setting customer data
				Element customerElement = doc.createElement("Customer");
				cartElement.appendChild(customerElement);
				
					customerElement.setAttribute("Name", shoppingCart.customer.getName());
				
					customerElement.setAttribute("Tax", Integer.toString(shoppingCart.customer.getTax())); 
				
					customerElement.setAttribute("PostCode", Integer.toString(shoppingCart.customer.getPostCode()));
				
					customerElement.setAttribute("ShopNumber", Integer.toString(shoppingCart.customer.getShopNumber()));
				
					customerElement.setAttribute("Email", shoppingCart.customer.getEmail());
				
				
				/*Laci I need your help here, I want you to finish the createProductTree method(at the bottom of this class)
				 *  I want it to take the shoppingCart's list of products, and return an Element named "Products"
				 *  this element should contain all products in the list as children, structured the same as or at least similarly
				 *  to the way products.xml is structured
				 *  
				 *  this returned Element will be appended under the cartElement, then the whole xml is saved
				 * */
				Element productsElement = createProductTree(shoppingCart.getPurchaseList());
				cartElement.appendChild(productsElement);
				
				
			}
			
			
			DOMSource source = new DOMSource(doc);
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    
		    StreamResult result = new StreamResult(cartFilePath);
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.transform(source, result);
		    
		    return true;
		} 
		catch (TransformerException | ParserConfigurationException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	public Element createProductTree(List<Products> productList) {
		
	}
}
