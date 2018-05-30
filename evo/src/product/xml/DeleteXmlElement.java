package product.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import product.classes.*;

public class DeleteXmlElement {
	
	//hozz�f�z �j �rt�keket
	
	//t�rli a kapott �rt�keket
	public static void deleteNoteFromTheXml(String productName, String note, String tag) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		Document doc = BaseXmlOperations.tryOpenTheFile();
		NodeList nodes = doc.getElementsByTagName(note);		//be�llitjuk melyik note keres�nk

	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element goods = (Element)nodes.item(i);

	      Element name = (Element)goods.getElementsByTagName(tag).item(0);	//tagon bel�l az �rt�ket kivesz�k. Azt amit �ssze akarunk hasonlitanunk, azzal az �rt�kkkel amit t�r�lni szeretn�nk
	      String dName = name.getTextContent();
	      
	      if (dName.equals(productName)) {					//�sszehasonlitjuk az elemet azzal a �rt�kkel amit keres�nk
	    	 goods.getParentNode().removeChild(goods);		//itt t�r�lj�k ki az elemet
	    	 goods.normalize();
	    	 BaseXmlOperations.xmlToStringTheValue(doc);						//vissza irjuk a f�jlba amit v�ltoztatunk
			}
	    }
	}
	
	//p�lda
	/*<?xml version="1.0" encoding="UTF-8"?>
	<Products>
	 <PC_component>
	   <item type="cpu">
	    <name>Ryzen 5 1600</name>
		<producer>AMD</producer>
		<quantity>1</quantity>
		<price>46000</price>
	    <clock>3200</clock>
	    <socket>AM4</socket>
		<cpu_cores>6</cpu_cores>
	    <manufactorytech>14</manufactorytech>
	   </item>
	 </PC_component>
	</Products>
	*/
	public static void main(String[] args) {
		try {
			deleteNoteFromTheXml("Ryzen 5 1600", "item", "name");
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
