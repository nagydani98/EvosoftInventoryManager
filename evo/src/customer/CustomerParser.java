package customer;

import java.io.File;
import java.io.IOException;
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
import org.xml.sax.SAXException;

public class CustomerParser {
	
	private String filePath = "customer.xml";
	private List<Customer> loadedCustomer = new ArrayList<Customer>();
	
	public boolean tryToSave(List<Customer> customer) {
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			
			Element customerElement = doc.createElement("Users");
			
			
			doc.appendChild(customerElement);
			
			
			for(int i = 0; i < customer.size(); i++) {
				
				Customer customers = customer.get(i);
				
				
				Element customersElement = doc.createElement("Customer");
				customerElement.appendChild(customersElement);
				
				
				customersElement.setAttribute("Name", customers.getName());
				
				customersElement.setAttribute("Tax", Integer.toString(customers.getTax())); 
				
				customersElement.setAttribute("PostCode", Integer.toString(customers.getPostCode()));
				
				customersElement.setAttribute("ShopNumber", Integer.toString(customers.getShopNumber()));
				
				customersElement.setAttribute("Email", customers.getEmail());
				
				
			}
			
			
			DOMSource source = new DOMSource(doc);
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    
		    StreamResult result = new StreamResult(filePath);
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.transform(source, result);
		    
		    return true;
		} 
		catch (TransformerException | ParserConfigurationException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean tryToLoad() {
		try {
			
			loadedCustomer.clear();
			
			
			File file = new File(filePath);
			if(file.exists() && file.isFile()) {
				
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(file);
				
				
				Element customerElement = doc.getDocumentElement();
				
				
				for(int i = 0; i < customerElement.getChildNodes().getLength(); i++) {
					if(customerElement.getChildNodes().item(i).getNodeName().equals("Customer")) {
						
						Element customersElement = (Element) customerElement.getChildNodes().item(i);
						
						
						String name = customersElement.getAttribute("Name");
						
						int tax = Integer.parseInt(customersElement.getAttribute("Tax"));
						
						int postCode = Integer.parseInt(customersElement.getAttribute("PostCode"));
						
						int shopNumber = Integer.parseInt(customersElement.getAttribute("ShopNumber"));
						
						String email = customersElement.getAttribute("Email");
						
						
						Customer customer = new Customer(name, tax, postCode, shopNumber, email);
						
						
						loadedCustomer.add(customer);
					}
				}
				
				return true;
			}
			else {
				return false;
			}
		} 
		catch (SAXException | IOException | ParserConfigurationException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Customer> getLoadedPeople() {
		return loadedCustomer;
	}
	
	public void printCustomerList() { //automatikusan kiirja az elmentett neveket es adjaszamot
		if(!this.loadedCustomer.isEmpty())
			for (int i = 0; i < this.loadedCustomer.size(); i++) {
				//System.out.println(String.format("%d. %s: %d", i+1, this.loadedCustomer.get(i).getName(), this.loadedCustomer.get(i).getTax()));
				System.out.println(i+1+"." + this.loadedCustomer.get(i).getName()+ ":" +"\n" +"Tax: " + + this.loadedCustomer.get(i).getTax()+ "\n" +"Email: " + this.loadedCustomer.get(i).getEmail() +"\n" +"Post code: " + this.loadedCustomer.get(i).getPostCode()+"\n" +"Shop number: " + this.loadedCustomer.get(i).getShopNumber()+"\n");
			}
	}
	
	//az itt levo mindket keresomet�dust me lehet tovabb gondolni, pl partial matchekkel, vagy whitespace karakterek ignorolasaval, toLowercase-el stb.
	
	public List<Customer> findCostumers(String name) {
		List<Customer> foundCustomersList = new ArrayList<Customer>();
		for (int i = 0; i < loadedCustomer.size(); i++) {
			if(loadedCustomer.get(i).getName().toLowerCase().contains(name.toLowerCase()))
				foundCustomersList.add(loadedCustomer.get(i));
		}
		return foundCustomersList;
	}
	
	public void deleteCustomer(Customer toDel) {
		for (int i = 0; i < loadedCustomer.size(); i++) {
			if(loadedCustomer.get(i).equals(toDel))
				loadedCustomer.remove(i);
		}
		
	}
}
