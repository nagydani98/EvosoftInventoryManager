package product.classes;

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
		setType("gpu");
	}
	
	public int getClock() {
		return clock;
	}
	
	public int getRam() {
		return ram;
	}
	
	public int getRamSpeed() {
		return ram_speed;
	}
	
	public int getBusMemory() {
		return bus_memory;
	}
	
	public String getBusType() {
		return bus_type;
	}
	
	@Override
	public void writeDownTheParameters() {
		super.writeDownTheParameters();
		System.out.println("Core sebesség: " +clock+"\n");
		System.out.println("Ram (Mb): " +ram+"\n");
		System.out.println("Memória sebesség: " +ram_speed+"\n");
		System.out.println("Memória bussz sebessége: " +bus_memory+"\n");
		System.out.println("Busz tipusa: " +bus_type+"\n");
	}
}
