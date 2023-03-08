package ingredients;

import model.Ingredient;

public class Veggiepatty extends Ingredient {
	protected String name;
	protected double price;
	protected String type;
	
	/**
	 * Instantiates veggiepatty object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Veggiepatty(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	

}