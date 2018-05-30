package product.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BaseXmlOperations {
	private static String fileLocation = "proba.xml";

	//megpróbálja megnyitni a fájlt
	public static Document tryOpenTheFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		dBuilder = dbFactory.newDocumentBuilder();
		//ezzel hiba nélkül be lehet olvasni a magyar ékezetes betûket. Különösebben nem volt vele gond, de SAX konvertálása miatt okozhat gondot.Bytokat olvassa ki. Ha hibás karakter van benne nem fogja engedi a mûködést.
		doc = dBuilder.parse(new InputSource(new FileInputStream(new File(fileLocation))));
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	public void setFileLocation(String newFileLocation){
		fileLocation = newFileLocation;
	}
	
	public String getFileLocation() {
		return fileLocation;
	}
	
	//vissza fûzi értékeket a fájlba
	public static void xmlToStringTheValue(Document doc) throws TransformerException, IOException {
		//XML fájl formázási stilus
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");	//formázási fajta, meg lehet változatni vagy törölni is
		
		//Összefûzi az xml értékét azzal a értékkel, amit modositottunk/töröltünk/hozzáadtunks
	    StringWriter sw = new StringWriter();
	    StreamResult streamresult = new StreamResult(sw);
	    transformer.transform(new DOMSource(doc), new StreamResult(sw));
	    String strTemp = streamresult.getWriter().toString();
	    System.out.println(strTemp);	//kiirja az egész fájl szerekezett a változtatás után
	    
	    //itt történik a vissza irás a fájlba
	    //Nos ezzel a buffer iróval, engedélyezni lehet hogy bármi féle kodolás nélkül vissza irja a magyar ékezetes betûket a fájlba. Bytokat irja vissza. Hiba nélkül. 
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation, false),"UTF-8"));		
        bufferedWriter.write(strTemp);
        
        //lezárjuk és töröljük a buffer
        bufferedWriter.flush();
        bufferedWriter.close();
	}
}
