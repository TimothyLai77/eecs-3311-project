package backend;

public class Beef implements Ingredient {
	private String name;
	private double price;
	private String type;
	
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
