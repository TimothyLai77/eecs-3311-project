package backend;

public class Meatball implements Ingredient {
	private String name;
	private double price;
	private String type;
	
	/**
	 * Instantiates meat ball object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Meatball(String name, double price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	/**
	 * @return:- this method returns the price of meat ball objects
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * @return:- This method returns the name of ingredient
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return:- This method returns the type of the ingredient
	 */
	public String getType() {
		return this.type;
	}
}
