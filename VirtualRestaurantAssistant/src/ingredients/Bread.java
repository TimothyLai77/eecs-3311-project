package ingredients;

import model.Ingredient;

public class Bread extends Ingredient {
	
	protected String name;
	protected double price;
	protected String type;
	
	/**
	 * Instantiates bread object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Bread(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	

}
