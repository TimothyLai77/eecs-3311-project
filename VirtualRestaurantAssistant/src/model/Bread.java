package model;

public class Bread implements Ingredient {
	
	private String name;
	private double price;
	private String type;
	
	/**
	 * Instantiates bread object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Bread(String name, double price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	/**
	 * @return:- This method returns the price of bread objects
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
	 * @return:- This method returns the name of ingredient
	 */
	public String getType() {
		return this.type;
	}

}
