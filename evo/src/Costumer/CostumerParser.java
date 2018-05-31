package Costumer;

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

public class CostumerParser {
	
	private String filePath = "costumer.xml";
	private List<Costumer> loadedCostumer = new ArrayList<Costumer>();
	
	public boolean tryToSave(List<Costumer> costumer) {
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			
			Element costumerElement = doc.createElement("Users"); // duplán volt létrehozva element customers névvel, itt
			
			
			doc.appendChild(costumerElement);
			
			
			for(int i = 0; i < costumer.size(); i++) {
				
				Costumer costumers = costumer.get(i);
				
				
				Element costumersElement = doc.createElement("Customer"); // és itt, így átneveztem az elõzõt
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
					if(costumerElement.getChildNodes().item(i).getNodeName().equals("Customer")) { //nagyon fontos, hogy itt egyezzen a kiolvasandó xml Element nevével
						
						Element costumersElement = (Element) costumerElement.getChildNodes().item(i);
						
						
						String name = costumersElement.getAttribute("Name");
						
						int tax = Integer.parseInt(costumersElement.getAttribute("Tax"));
						
						int postCode = Integer.parseInt(costumersElement.getAttribute("PostCode"));
						
						int shopNumber = Integer.parseInt(costumersElement.getAttribute("ShopNumber"));
						
						String email = costumersElement.getAttribute("Email");
						
						
						Costumer costumer = new Costumer(name, tax, postCode, shopNumber, email);
						
						
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
	
	public List<Costumer> getLoadedPeople() {
		return loadedCostumer;
	}
	
	public void printCostumerList() { //automatikusan kiírja az elmentett neveket és adószámot
		if(!this.loadedCostumer.isEmpty())
			for (int i = 0; i < this.loadedCostumer.size(); i++) {
				System.out.println(String.format("%d. %s: %d", i+1, this.loadedCostumer.get(i).getName(), this.loadedCostumer.get(i).getTax()));
			}
	}
	
	//az itt lévõ mindkét keresõmetódust még lehet tovább gondolni, pl partial matchekkel, vagy whitespace karakterek ignorálásávla, toLowercase-el stb.
	
	public List<Costumer> findCostumers(String name) {
		List<Costumer> foundCostumersList = new ArrayList<Costumer>();
		for (int i = 0; i < loadedCostumer.size(); i++) {
			if(loadedCostumer.get(i).getName().equals(name))
				foundCostumersList.add(loadedCostumer.get(i));
		}
		return foundCostumersList;
	}
	
	public void deleteCustomer(Costumer toDel) {
		for (int i = 0; i < loadedCostumer.size(); i++) {
			if(loadedCostumer.get(i).equals(toDel))
				loadedCostumer.remove(i);
		}
		
	}
}
