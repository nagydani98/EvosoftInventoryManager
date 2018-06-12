package product.classes;

public abstract class Products {
	private String name; 		//termék neve
	private String producer;	//gyártó
	private int quantity;		//termék mennyisége
	private int price;			//ár
	private String type;
	private String categorical;
	//protected static int tax;		//adó, mivel termék fajtáként lehet változó ezten még dolgozunk. Nem tudom azt nem beszéltük, hogy szeretnénk felvenni élelmiszert vagy gyógyszert egyenlõre, azoknak más az adó kulcsuk
	//de akkár static tömb ként is tárolhatjuk itt, az adó fajtákat, és a következõ gyereknél beállitjuk/kiválasztjuk azt a adó nemet ami oda való
	
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
		System.out.print("Gyártó: " +producer+"\n");
		System.out.print("Darabszám: " +quantity+"\n" );
		System.out.print("Ár: " +price+"\n" );*/
		System.out.print("Type: " + type +"\n");
		System.out.print("Name of product: " + name +"\n");
		System.out.print("Producer: " +producer+"\n");
		System.out.print("Amount: " +quantity+"\n" );
		System.out.print("Price: " +price+"\n" );
	}
}
