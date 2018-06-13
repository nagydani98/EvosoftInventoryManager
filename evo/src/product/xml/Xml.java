package product.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import product.classes.ComponentsCPU;
import product.classes.ComponentsGPU;
import product.classes.Other;
import product.classes.Products;

public class Xml {
	private static String fileLocation = "product.xml";
	private static Document doc = null;
	
	//megpróbálja megnyitni a fájlt
	public static boolean open(){
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		//ezzel hiba nélkül be lehet olvasni a magyar ékezetes betûket. Különösebben nem volt vele gond, de SAX konvertálása miatt okozhat gondot.Bytokat olvassa ki. Ha hibás karakter van benne nem fogja engedi a mûködést.
		
		File f = new File(fileLocation);
		if(f.exists()) {
			try {
				doc = dBuilder.parse(new InputSource(new FileInputStream(new File(fileLocation))));
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
			
			if(doc.getElementsByTagName("product").item(0)==null) {
				Element rootElement = doc.createElement("product");
				doc.appendChild(rootElement);
			}
			if(doc.getElementsByTagName("componentpc").item(0)==null) {
				Element root = doc.getDocumentElement();
				Element pcComponent = doc.createElement("componentpc");
				root.appendChild(pcComponent);
			}
			if(doc.getElementsByTagName("other").item(0)==null) {
				Element root = doc.getDocumentElement();
				Element other = doc.createElement("other");
				root.appendChild(other);
			}
			doc.getDocumentElement().normalize();
			return true;
		}else {
			doc = dBuilder.newDocument();
			
			Element rootElement = doc.createElement("product");
			doc.appendChild(rootElement);

			Element pcComponent = doc.createElement("componentpc");
			rootElement.appendChild(pcComponent);
			Element other = doc.createElement("other");
			rootElement.appendChild(other);
			return false;
		}
	}
	
	public void setFileLocation(String newFileLocation){
		fileLocation = newFileLocation;
	}
	
	public String getFileLocation() {
		return fileLocation;
	}
	
	//vissza fûzi értékeket a fájlba
	private static void xmlToString(){
		//XML fájl formázási stilus
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");	//formázási fajta, meg lehet változatni vagy törölni is
		
		//Összefûzi az xml értékét azzal a értékkel, amit modositottunk/töröltünk/hozzáadtunks
	    StringWriter sw = new StringWriter();
	    StreamResult streamresult = new StreamResult(sw);
	   
	    try {
			transformer.transform(new DOMSource(doc), new StreamResult(sw));
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String strTemp = streamresult.getWriter().toString();
	    System.out.println(strTemp);	//kiirja az egész fájl szerekezett a változtatás után
	    
	    //itt történik a vissza irás a fájlba
	    //Nos ezzel a buffer iróval, engedélyezni lehet hogy bármi féle kodolás nélkül vissza irja a magyar ékezetes betûket a fájlba. Bytokat irja vissza. Hiba nélkül. 
        BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation, false),"UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//lezárjuk és töröljük a buffer
        try {
			bufferedWriter.write(strTemp);
			bufferedWriter.flush();
	        bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static List <Products> reader(){
		List<Products> theList = new ArrayList<Products>();
		
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
					int ram = Integer.parseInt(eElement.getElementsByTagName("ram").item(0).getTextContent());
					int ram_speed = Integer.parseInt(eElement.getElementsByTagName("ram_speed").item(0).getTextContent());
					int bus_memory = Integer.parseInt(eElement.getElementsByTagName("bus_memory").item(0).getTextContent());
					String bus_type = eElement.getElementsByTagName("bus_type").item(0).getTextContent();
	
					OnlyOneProduct = new ComponentsGPU(name, producer, quantity, price, clock, ram, ram_speed, bus_memory, bus_type);
				}
				theList.add(OnlyOneProduct);
			}
		}
		
		return theList;
	}
	
	public static boolean deleteNote(String productName, String note, String tag){
		NodeList nodes = doc.getElementsByTagName("item");		//beállitjuk melyik note keresünk

	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element goods = (Element)nodes.item(i);

	      Element name = (Element)goods.getElementsByTagName(tag).item(0);	//tagon belül az értéket kiveszük. Azt amit össze akarunk hasonlitanunk, azzal az értékkkel amit törölni szeretnénk
	      String dName = name.getTextContent();
	      
	      if (dName.equals(productName)) {					//összehasonlitjuk az elemet azzal a értékkel amit keresünk
	    	  //itt töröljük ki az elemet
	    	 goods.getParentNode().removeChild(goods);
	    	 goods.normalize();
	    	 
	    	 Element pathsElement = (Element)doc.getElementsByTagName( "product" ).item( 0 );
	    	 while( pathsElement.hasChildNodes() )					//kitörli az üres sorokat a product-ból
	    		    pathsElement.removeChild( pathsElement.getFirstChild() );
	    	 
	    	 /*if(doc.getElementsByTagName("product").item(0)==null) {
					Element rootElement = doc.createElement("product");
					doc.appendChild(rootElement);
			 }
			 if(doc.getElementsByTagName("componentpc").item(0)==null) {
					Element root = doc.getDocumentElement();
					Element pcComponent = doc.createElement("componentpc");
					root.appendChild(pcComponent);
			 }
		  	 if(doc.getElementsByTagName("other").item(0)==null) {
					Element root = doc.getDocumentElement();
					Element other = doc.createElement("other");
					root.appendChild(other);
			 }*/
	    	 xmlToString();						//vissza irjuk a fájlba amit változtatunk
	    	 return true;
			}
	    }
		return false;
	}
	
	public static void writer(List <Products> newProducts){
		for(Products product : newProducts) {
			Element root = doc.getDocumentElement();
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
		
		xmlToString();
	}
}
