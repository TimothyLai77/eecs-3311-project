package ingredients;

import model.Ingredient;

public class Chicken extends Ingredient {
	protected String name;
	protected double price;
	protected String type;
	
	/**
	 * Instantiates chicken object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Chicken(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	

}
