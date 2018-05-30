package product.classes;

public class ComponentsCPU extends ComponentsPC{
	private int clock;
	private String socket;       
	private int cpu_cores;
	private int manufactorytech;
	

	public ComponentsCPU(String name, String producer, int quantity, int price,int clock, String socket,int cpu_cores, int manufactorytech) {
		super(name, producer, quantity, price);
		this.clock = clock;
		this.socket = socket;
		this.cpu_cores = cpu_cores;
		this.manufactorytech = manufactorytech;
		setType("cpu");
	}
	
	public int getClock() {
		return clock;
	}
	
	public String getSocket() {
		return socket;
	}
	
	public int getCpuCores() {
		return cpu_cores;
	}
	
	public int getManufactorytech() {
		return manufactorytech;
	}
	
	@Override
	public void writeDownTheParameters() {
		super.writeDownTheParameters();
		System.out.println("Core sebesség: "+clock+"\n");
		System.out.println("Foglalat: " +socket+"\n");
		System.out.println("CPU magok szám: " +cpu_cores+"\n");
		System.out.println("Gyártási teknia"+manufactorytech+"\n");
	}
}
