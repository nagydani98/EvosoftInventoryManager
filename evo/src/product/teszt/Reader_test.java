package product.teszt;

import product.classes.*;


public class Reader_test {
	/*
	//megnyitja a documentumot, ha nem tudja akkor dob Exception-nokat
	
	//Kiir egy elemetet, amit meghatározunk
	public static void writeDownOnePartOfTheList() {
		org.w3c.dom.Document doc = null;
		try {
			doc = Products.tryOpenTheFile();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Products writer = new ComponentsCPU();
		
		NodeList nList = (NodeList)doc.getElementsByTagName("PC_component").item(0);	//mind kérésre meg lehet változtatni

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);	
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				//Most csak a CPU irja ki
				writer.readTheFileAndWriteDown(eElement);
			}
		}
	}
	
	//Kiirja sorbarendezés nélkül az összes elemet
	public static void whiteDownWholeStuff() {
		org.w3c.dom.Document doc = null;
		try {
			doc = Products.tryOpenTheFile();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Products writerCPU = new ComponentsCPU();
		Products writerGPU = new ComponentsGPU();
		Products writerOther = new Other();
		
		NodeList nList = doc.getElementsByTagName("item");	//mind kérésre meg lehet változtatni

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);	
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				//Most csak a CPU irja ki
				writerCPU.readTheFileAndWriteDown(eElement);
				writerGPU.readTheFileAndWriteDown(eElement);
				writerOther.readTheFileAndWriteDown(eElement);
			}
		}
	}
	public static void main(String [] angs) {
		//writeDownOnePartOfTheList();
		System.out.print("--------\n");
		whiteDownWholeStuff();
	}*/
}
