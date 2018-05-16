package Costumer;

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

public class CostumerParser {
	
	private String filePath = "costumer.xml";
	private List<Costumer> loadedCostumer = new ArrayList<Costumer>();
	
	public boolean tryToSave(List<Costumer> costumer) {
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			
			Element costumerElement = doc.createElement("Costumer");
			
			
			doc.appendChild(costumerElement);
			
			
			for(int i = 0; i < costumer.size(); i++) {
				
				Costumer costumer = costumer.get(i);
				
				
				Element costumerElement = doc.createElement("Costumer");
				costumerElement.appendChild(costumerElement);
				
				
				costumerElement.setAttribute("Name", costumer.getName());
				
				
				costumerElement.setAttribute("Tax", Integer.toString(costumer.getTax()));
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
				
				
				Element peopleElement = doc.getDocumentElement();
				
				
				for(int i = 0; i < costumerElement.getChildNodes().getLength(); i++) {
					if(costumerElement.getChildNodes().item(i).getNodeName().equals("Costumer")) {
						
						Element costumerElement = (Element) costumerElement.getChildNodes().item(i);
						
						
						String name = costumerElement.getAttribute("Name");
						
						int tax = Integer.parseInt(costumerElement.getAttribute("Tax"));
						
						
						Costumer costumer = new Costumer(name, tax);
						
						
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
}
