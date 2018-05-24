package evo.classes;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public abstract class Products {
	private String name; 		//termék neve
	private String producer;	//gyártó
	private int quantity;		//termék mennyisége
	private int price;			//ár
	
	private static String fileLocation = "products.xml";
	protected static String type;
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
	
	public static String getFileLocation() {
		return fileLocation;
	}
	
	/*public static void setNode(Element locElement) {
		eElement = locElement;
	}*/
	
	/*public void chechType() {
		if(eElement.getAttribute("cpu") == type) {
			readTheFile(eElement);
		}
	}*/
	
	
	public void readTheFileAndWriteDown(Element eElement) {
			if(eElement!=null) {
				System.out.print("Termek neve: " +eElement.getElementsByTagName("name").item(0).getTextContent() +"\n");
				System.out.print("Gyártó: " +eElement.getElementsByTagName("producer").item(0).getTextContent() +"\n");
				System.out.print("Darabszám: " +eElement.getElementsByTagName("quantity").item(0).getTextContent() +"\n" );
				System.out.print("Ár: " +eElement.getElementsByTagName("price").item(0).getTextContent() +"\n" );
			}else {
				System.out.print("false");
			}
	}

	
}
