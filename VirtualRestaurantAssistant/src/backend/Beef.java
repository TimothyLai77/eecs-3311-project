package backend;

public class Beef implements Ingredient {
	private String name;
	private double price;
	private String type;
	
	public Beef(String name, double price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public double getPrice() {
		return this.price;
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
}
