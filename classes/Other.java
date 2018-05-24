package evo.classes;

import org.w3c.dom.Element;

public class Other extends Products{

	//ha otheren belül szeretnénk még egy gyermeket gyógyszernek, vagy egyébb különleges dolognak
	private String[] OtherTypes = {"cpu","gpu"};	//kivétel, hogy minden tippus irjon ki csak azokat nem
	
	public Other(String name,String producer,int quantity, int price) {
		super(name,producer,quantity,price);
		type="other";
	}
	
	public Other() {
		type="other";
	}
	
	@Override
	public void readTheFileAndWriteDown(Element eElement) {
		if( (!(eElement.getAttribute("type").equals(OtherTypes[0])) && (!(eElement.getAttribute("type").equals(OtherTypes[1])) ))) {
			super.readTheFileAndWriteDown(eElement);
		}
	}
}
