package evo.teszt;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import evo.classes.*;


public class Teszt {
	public static org.w3c.dom.Document tryOpenTheFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		org.w3c.dom.Document doc = null;		//valamiért nem tudja elérni az ut vonalat "org.w3c.dom.Document" feljebb mutatja, hogy nem használom az importot, passz nem tuduom miért
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(Products.getFileLocation());
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static void writeDownTheList() {
		org.w3c.dom.Document doc = null;
		try {
			doc = tryOpenTheFile();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Products writer = new ComponentsCPU();
		
		NodeList nList = (NodeList)doc.getElementsByTagName("PC_component").item(0);

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);	
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				//Most csak a CPU irja ki
				writer.readTheFileAndWriteDown(eElement);
			}
		}
	}

	public static void main(String [] angs) {
		writeDownTheList();
	}
}
