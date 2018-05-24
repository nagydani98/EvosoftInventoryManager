package evo.classes;

import org.w3c.dom.Element;

public class ComponentsPC extends Products{
	public ComponentsPC(String name,String producer,int quantity, int price) {
		super(name,producer,quantity,price);
	}
	public ComponentsPC() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void readTheFileAndWriteDown(Element eElement) {
		super.readTheFileAndWriteDown(eElement);
	}

}
