package customer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	private String filePath = "costumer.xml";
	private List<Customer> loadedCostumer = new ArrayList<Customer>();
	
	public boolean tryToSave(List<Customer> costumer) {
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			
			Element costumerElement = doc.createElement("Users"); // dupl�n volt l�trehozva element customers n�vvel, itt
			
			
			doc.appendChild(costumerElement);
			
			
			for(int i = 0; i < costumer.size(); i++) {
				
				Customer costumers = costumer.get(i);
				
				
				Element costumersElement = doc.createElement("Customer"); // �s itt, �gy �tneveztem az el�z�t
				costumerElement.appendChild(costumersElement);
				
				
				costumersElement.setAttribute("Name", costumers.getName());
				
				costumersElement.setAttribute("Tax", Integer.toString(costumers.getTax())); 
				
				costumersElement.setAttribute("PostCode", Integer.toString(costumers.getPostCode()));
				
				costumersElement.setAttribute("ShopNumber", Integer.toString(costumers.getShopNumber()));
				
				costumersElement.setAttribute("Email", costumers.getEmail());
				
				
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
			
			loadedCostumer.clear();
			
			
			File file = new File(filePath);
			if(file.exists() && file.isFile()) {
				
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(file);
				
				
				Element costumerElement = doc.getDocumentElement();
				
				
				for(int i = 0; i < costumerElement.getChildNodes().getLength(); i++) {
					if(costumerElement.getChildNodes().item(i).getNodeName().equals("Customer")) { //nagyon fontos, hogy itt egyezzen a kiolvasand� xml Element nev�vel
						
						Element costumersElement = (Element) costumerElement.getChildNodes().item(i);
						
						
						String name = costumersElement.getAttribute("Name");
						
						int tax = Integer.parseInt(costumersElement.getAttribute("Tax"));
						
						int postCode = Integer.parseInt(costumersElement.getAttribute("PostCode"));
						
						int shopNumber = Integer.parseInt(costumersElement.getAttribute("ShopNumber"));
						
						String email = costumersElement.getAttribute("Email");
						
						
						Customer costumer = new Customer(name, tax, postCode, shopNumber, email);
						
						
						loadedCostumer.add(costumer);
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
		return loadedCostumer;
	}
	
	public void printCostumerList() { //automatikusan ki�rja az elmentett neveket �s ad�sz�mot
		if(!this.loadedCostumer.isEmpty())
			for (int i = 0; i < this.loadedCostumer.size(); i++) {
				System.out.println(String.format("%d. %s: %d", i+1, this.loadedCostumer.get(i).getName(), this.loadedCostumer.get(i).getTax()));
			}
	}
	
	//az itt l�v� mindk�t keres�met�dust m�g lehet tov�bb gondolni, pl partial matchekkel, vagy whitespace karakterek ignor�l�s�vla, toLowercase-el stb.
	
	public List<Customer> findCostumers(String name) {
		List<Customer> foundCostumersList = new ArrayList<Customer>();
		for (int i = 0; i < loadedCostumer.size(); i++) {
			if(loadedCostumer.get(i).getName().equals(name))
				foundCostumersList.add(loadedCostumer.get(i));
		}
		return foundCostumersList;
	}
	
	public void deleteCustomer(Customer toDel) {
		for (int i = 0; i < loadedCostumer.size(); i++) {
			if(loadedCostumer.get(i).equals(toDel))
				loadedCostumer.remove(i);
		}
		
	}
}
