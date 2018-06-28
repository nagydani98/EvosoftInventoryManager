package classes.product;

public abstract class Products {
	private String name; 		//term�k neve
	private String producer;	//gy�rt�
	private int quantity;		//term�k mennyis�ge
	private int price;			//�r
	private String type;
	private String categorical;
	//protected static int tax;		//ad�, mivel term�k fajt�k�nt lehet v�ltoz� ezten m�g dolgozunk. Nem tudom azt nem besz�lt�k, hogy szeretn�nk felvenni �lelmiszert vagy gy�gyszert egyenl�re, azoknak m�s az ad� kulcsuk
	//de akk�r static t�mb k�nt is t�rolhatjuk itt, az ad� fajt�kat, �s a k�vetkez� gyerekn�l be�llitjuk/kiv�lasztjuk azt a ad� nemet ami oda val�
	
	public Products(String name,String producer,int quantity, int price) {
		this.name = name;
		this.producer = producer;	
		this.quantity = quantity;	
		this.price = price;
		//this.tax = ;
	}
	public void setCategorical(String categorical) {
		this.categorical = categorical;
	}
	public String getCategorical() {
		return categorical;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getProducer() {
		return producer;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getPrice() {
		return price;
	}
	
	
	public void writeDownTheParameters() {
		/*System.out.print("Tippus: " + type +"\n");
		System.out.print("Termek neve: " + name +"\n");
		System.out.print("Gy�rt�: " +producer+"\n");
		System.out.print("Darabsz�m: " +quantity+"\n" );
		System.out.print("�r: " +price+"\n" );*/
		System.out.print("Type: " + type +"\n");
		System.out.print("Name of product: " + name +"\n");
		System.out.print("Producer: " +producer+"\n");
		System.out.print("Amount: " +quantity+"\n" );
		System.out.print("Price: " +price+"\n" );
	}
}
