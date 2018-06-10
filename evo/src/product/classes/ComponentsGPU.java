package product.classes;

public class ComponentsGPU extends ComponentsPC{
	private int clock;
	private int ram;		//Vram m�ret mb-ban
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
		setCategorical("componentpc");
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
		System.out.print("Core sebess�g: " +clock+"\n");
		System.out.print("Ram (Mb): " +ram+"\n");
		System.out.print("Mem�ria sebess�g: " +ram_speed+"\n");
		System.out.print("Mem�ria bussz sebess�ge: " +bus_memory+"\n");
		System.out.print("Busz tipusa: " +bus_type+"\n");
	}
}
