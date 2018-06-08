package product.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;

import product.classes.*;

public class ReadTagsFromXml {

	public List <Products> readTheXml() throws ParserConfigurationException, SAXException, IOException{
		List<Products> theList = new ArrayList<Products>();
		Document doc = BaseXmlOperations.tryOpenTheFile();
		
		NodeList nList = doc.getElementsByTagName("item");
		
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);	
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				Products OnlyOneProduct = null;
				
				String name = eElement.getElementsByTagName("name").item(0).getTextContent();
				String producer = eElement.getElementsByTagName("producer").item(0).getTextContent();
				int quantity = Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent());
				int price = Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent());
				
				String type = eElement.getAttribute("type");
				
				if(!(type.equals("cpu")||type.equals("gpu"))){
					OnlyOneProduct = new Other(name,producer,quantity,price,type);
				}
				
				if(type.equals("cpu")) {	
					int clock = Integer.parseInt(eElement.getElementsByTagName("clock").item(0).getTextContent());
					String socket = eElement.getElementsByTagName("socket").item(0).getTextContent();
					int cpu_cores = Integer.parseInt(eElement.getElementsByTagName("cpu_cores").item(0).getTextContent());
					int manufactorytech = Integer.parseInt(eElement.getElementsByTagName("manufactorytech").item(0).getTextContent());
					
					OnlyOneProduct = new ComponentsCPU(name, producer, quantity, price, clock, socket, cpu_cores, manufactorytech);
				}
				if(type.equals("gpu")) {
					int clock = Integer.parseInt(eElement.getElementsByTagName("clock").item(0).getTextContent());
					int ram = Integer.parseInt(eElement.getElementsByTagName("cpu_cores").item(0).getTextContent());
					int ram_speed = Integer.parseInt(eElement.getElementsByTagName("cpu_cores").item(0).getTextContent());
					int bus_memory = Integer.parseInt(eElement.getElementsByTagName("cpu_cores").item(0).getTextContent());
					String bus_type = eElement.getElementsByTagName("bus_type").item(0).getTextContent();
	
					OnlyOneProduct = new ComponentsGPU(name, producer, quantity, price, clock, ram, ram_speed, bus_memory, bus_type);
				}
				theList.add(OnlyOneProduct);
			}
		}
		
		return theList;
	}
}
