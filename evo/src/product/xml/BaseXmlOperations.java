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

	//megpr�b�lja megnyitni a f�jlt
	public static Document tryOpenTheFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		dBuilder = dbFactory.newDocumentBuilder();
		//ezzel hiba n�lk�l be lehet olvasni a magyar �kezetes bet�ket. K�l�n�sebben nem volt vele gond, de SAX konvert�l�sa miatt okozhat gondot.Bytokat olvassa ki. Ha hib�s karakter van benne nem fogja engedi a m�k�d�st.
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
	
	//vissza f�zi �rt�keket a f�jlba
	public static void xmlToStringTheValue(Document doc) throws TransformerException, IOException {
		//XML f�jl form�z�si stilus
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");	//form�z�si fajta, meg lehet v�ltozatni vagy t�r�lni is
		
		//�sszef�zi az xml �rt�k�t azzal a �rt�kkel, amit modositottunk/t�r�lt�nk/hozz�adtunks
	    StringWriter sw = new StringWriter();
	    StreamResult streamresult = new StreamResult(sw);
	    transformer.transform(new DOMSource(doc), new StreamResult(sw));
	    String strTemp = streamresult.getWriter().toString();
	    System.out.println(strTemp);	//kiirja az eg�sz f�jl szerekezett a v�ltoztat�s ut�n
	    
	    //itt t�rt�nik a vissza ir�s a f�jlba
	    //Nos ezzel a buffer ir�val, enged�lyezni lehet hogy b�rmi f�le kodol�s n�lk�l vissza irja a magyar �kezetes bet�ket a f�jlba. Bytokat irja vissza. Hiba n�lk�l. 
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation, false),"UTF-8"));		
        bufferedWriter.write(strTemp);
        
        //lez�rjuk �s t�r�lj�k a buffer
        bufferedWriter.flush();
        bufferedWriter.close();
	}
}
