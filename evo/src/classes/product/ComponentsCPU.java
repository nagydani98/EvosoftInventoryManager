package classes.product;

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
		setCategorical("componentpc");
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
		/*System.out.print("Core sebesség: "+clock+"\n");
		System.out.print("Foglalat: " +socket+"\n");
		System.out.print("CPU magok szám: " +cpu_cores+"\n");
		System.out.print("Gyártási teknia"+manufactorytech+"\n")*/
		System.out.print("Core speed: "+clock+"\n");
		System.out.print("Socket: " +socket+"\n");
		System.out.print("Numbers of cpu cores: " +cpu_cores+"\n");
		System.out.print("Manufactured techniques: "+manufactorytech+"\n");
	}
}
