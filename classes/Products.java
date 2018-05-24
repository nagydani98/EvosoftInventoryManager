package evo.classes;

import java.io.IOException;
import java.io.StringWriter;

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

public abstract class Products {
	private String name; 		//termék neve
	private String producer;	//gyártó
	private int quantity;		//termék mennyisége
	private int price;			//ár
	
	private static String fileLocation = "products.xml";
	
	protected String type;
	//protected static int tax;		//adó, mivel termék fajtáként lehet változó ezten még dolgozunk. Nem tudom azt nem beszéltük, hogy szeretnénk felvenni élelmiszert vagy gyógyszert egyenlõre, azoknak más az adó kulcsuk
	//de akkár static tömb ként is tárolhatjuk itt, az adó fajtákat, és a következõ gyereknél beállitjuk/kiválasztjuk azt a adó nemet ami oda való
	
	public Products(String name,String producer,int quantity, int price) {
		this.name = name;
		this.producer = producer;	
		this.quantity = quantity;	
		this.price = price;
		//this.tax = ;
	}
	
	public Products() {
		
	}
	
	public String getType() {
		return type;
	}
	
	public static String getFileLocation() {
		return fileLocation;
	}
	
	public static void setFileLocation(String fileloc) {
		fileLocation = fileloc;
	}
	
	public void readTheFileAndWriteDown(Element eElement) {
		if(eElement!=null) {
			System.out.println("\nTippus: " +type);
			System.out.print("Termek neve: " +eElement.getElementsByTagName("name").item(0).getTextContent() +"\n");
			System.out.print("Gyártó: " +eElement.getElementsByTagName("producer").item(0).getTextContent() +"\n");
			System.out.print("Darabszám: " +eElement.getElementsByTagName("quantity").item(0).getTextContent() +"\n" );
			System.out.print("Ár: " +eElement.getElementsByTagName("price").item(0).getTextContent() +"\n" );
		}else {
			System.out.print("false");
		}
	}
	
	//megpróbálja megnyitni a fájlt
	public static Document tryOpenTheFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		org.w3c.dom.Document doc = null;		//valamiért nem tudja elérni az ut vonalat ha nincs benne a "org.w3c.dom.Document". Feljebb mutatja, hogy nem használom az importot, passz nem tuduom miért.
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(getFileLocation());
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	//vissza fûzi értékeket a fájlba
	public static void toString(Document newDoc) throws TransformerException {
		//XML fájl formázási stilus
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		//vissza irás az xmlbe
		DOMSource domSource = new DOMSource(newDoc);
	    StringWriter sw = new StringWriter();
	    StreamResult sr = new StreamResult(sw);
	    transformer.transform(domSource, sr);
	   // System.out.println(sw.toString());		kiirja az elemeket a hozzá füzés törlés után, benne hagyom hátha még jó lesz
	}
}
