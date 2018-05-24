package evo.classes;

import org.w3c.dom.Element;

public class ComponentsGPU extends ComponentsPC{
	private int clock;
	private int ram;		//Vram méret mb-ban
	private int ram_speed;
	private int bus_memory;
	private String bus_type;
	
	
	public ComponentsGPU(String name, String producer, int quantity, int price, int clock, int ram, int ram_speed, int bus_memory, String bus_type) {
		super(name, producer, quantity, price);
		this.clock = clock;
		this.ram = ram;
		this.ram_speed = ram_speed;
		this.bus_memory = bus_memory;
		this.bus_type = bus_type;
		type="gpu";
	}
	
	public ComponentsGPU() {
		type="gpu";
	}
	
	@Override
	public void readTheFileAndWriteDown(Element eElement) {
		if(eElement.getAttribute("type").equals(type)) {
			super.readTheFileAndWriteDown(eElement);
			System.out.println("Core sebesség: " +Integer.parseInt(eElement.getElementsByTagName("clock").item(0).getTextContent()));
			System.out.println("Ram (Mb): " +Integer.parseInt(eElement.getElementsByTagName("ram").item(0).getTextContent()));
			System.out.println("Memória sebesség: " +Integer.parseInt(eElement.getElementsByTagName("ram_speed").item(0).getTextContent()));
			System.out.println("Memória bussz sebessége: " +Integer.parseInt(eElement.getElementsByTagName("bus_memory").item(0).getTextContent()));
			System.out.println("Busz tipusa: " +eElement.getElementsByTagName("bus_type").item(0).getTextContent());
			System.out.println("\n");
		}
	}
}
