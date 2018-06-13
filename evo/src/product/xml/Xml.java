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
	
	//megpr�b�lja megnyitni a f�jlt
	public static boolean open(){
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		//ezzel hiba n�lk�l be lehet olvasni a magyar �kezetes bet�ket. K�l�n�sebben nem volt vele gond, de SAX konvert�l�sa miatt okozhat gondot.Bytokat olvassa ki. Ha hib�s karakter van benne nem fogja engedi a m�k�d�st.
		
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
	
	//vissza f�zi �rt�keket a f�jlba
	private static void xmlToString(){
		//XML f�jl form�z�si stilus
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");	//form�z�si fajta, meg lehet v�ltozatni vagy t�r�lni is
		
		//�sszef�zi az xml �rt�k�t azzal a �rt�kkel, amit modositottunk/t�r�lt�nk/hozz�adtunks
	    StringWriter sw = new StringWriter();
	    StreamResult streamresult = new StreamResult(sw);
	   
	    try {
			transformer.transform(new DOMSource(doc), new StreamResult(sw));
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String strTemp = streamresult.getWriter().toString();
	    System.out.println(strTemp);	//kiirja az eg�sz f�jl szerekezett a v�ltoztat�s ut�n
	    
	    //itt t�rt�nik a vissza ir�s a f�jlba
	    //Nos ezzel a buffer ir�val, enged�lyezni lehet hogy b�rmi f�le kodol�s n�lk�l vissza irja a magyar �kezetes bet�ket a f�jlba. Bytokat irja vissza. Hiba n�lk�l. 
        BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation, false),"UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//lez�rjuk �s t�r�lj�k a buffer
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
	
	public static boolean deleteNote(String productName, String note, String tag){
		NodeList nodes = doc.getElementsByTagName(note);		//be�llitjuk melyik note keres�nk

	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element goods = (Element)nodes.item(i);

	      Element name = (Element)goods.getElementsByTagName(tag).item(0);	//tagon bel�l az �rt�ket kivesz�k. Azt amit �ssze akarunk hasonlitanunk, azzal az �rt�kkkel amit t�r�lni szeretn�nk
	      String dName = name.getTextContent();
	      
	      if (dName.equals(productName)) {					//�sszehasonlitjuk az elemet azzal a �rt�kkel amit keres�nk
	    	 System.out.print("Sikeres\n");
	    	 if(goods.getParentNode().removeChild(goods)!=null) {
	    		 while( name.hasChildNodes() )
	    			 name.removeChild( name.getFirstChild());
	    	 }	//itt t�r�lj�k ki az elemet
	    	 goods.normalize();
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
	    	 xmlToString();						//vissza irjuk a f�jlba amit v�ltoztatunk
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
