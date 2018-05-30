package product.xml;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import product.classes.*;

public class WriteXmlNewTags {
	public static void writeNewXml(List <Products> newProducts) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Document doc = BaseXmlOperations.tryOpenTheFile();
		
		Element root = doc.getDocumentElement();
		for(Products product : newProducts) {
			
			Element type =  (Element) root .getElementsByTagName(product.getCategorical()).item(0);
				
			Element item = doc.createElement("item");
			item.setAttribute("type", product.getType());
			type.appendChild(item);
			
			
			Element outName = doc.createElement("name");
			outName.setTextContent(product.getName());	
			item.appendChild(outName);
			
			Element outProducer = doc.createElement("producer");
			outProducer.setTextContent(product.getProducer());	
			item.appendChild(outProducer);
			
			Element outQuantity = doc.createElement("quantity");
			outQuantity.setTextContent(Integer.valueOf(product.getQuantity()).toString());
			item.appendChild(outQuantity);
			
			Element outPrice = doc.createElement("price");
			outPrice.setTextContent(Integer.valueOf(product.getPrice()).toString());
			item.appendChild(outPrice);
			
			if(product.getClass() == Other.class) {
				
			}
			
			if(product.getClass() == ComponentsCPU.class) {
				Element outClock = doc.createElement("clock");
				outClock.setTextContent(Integer.valueOf(((ComponentsCPU) product).getClock()).toString());
				item.appendChild(outClock);
				
				Element outSocket = doc.createElement("socket");
				outSocket.setTextContent(((ComponentsCPU) product).getSocket());
				item.appendChild(outSocket);
				
				Element outCpuCores = doc.createElement("cpu_cores");
				outCpuCores.setTextContent(Integer.valueOf(((ComponentsCPU) product).getCpuCores()).toString());
				item.appendChild(outCpuCores);
				
				Element outManufactorytech = doc.createElement("manufactorytech");
				outManufactorytech.setTextContent(Integer.valueOf(((ComponentsCPU) product).getManufactorytech()).toString());
				item.appendChild(outManufactorytech);
			}
			
			if(product.getClass() == ComponentsGPU.class) {
				Element outClock = doc.createElement("clock");
				outClock.setTextContent(Integer.valueOf(((ComponentsGPU) product).getClock()).toString());
				item.appendChild(outClock);
				
				Element outCpuCores = doc.createElement("ram");
				outCpuCores.setTextContent(Integer.valueOf(((ComponentsGPU) product).getRam()).toString());
				item.appendChild(outCpuCores);
				
				Element outManufactorytech = doc.createElement("ram_speed");
				outManufactorytech.setTextContent(Integer.valueOf(((ComponentsGPU) product).getRamSpeed()).toString());
				item.appendChild(outManufactorytech);
				
				Element outBusMemory = doc.createElement("bus_memory");
				outBusMemory.setTextContent(Integer.valueOf(((ComponentsGPU) product).getBusMemory()).toString());
				item.appendChild(outBusMemory);
				
				Element outBusType = doc.createElement("bus_type");
				outBusType.setTextContent(((ComponentsGPU) product).getBusType());
				item.appendChild(outBusType);
			}
		}
		
		BaseXmlOperations.xmlToStringTheValue(doc);
	}
}
