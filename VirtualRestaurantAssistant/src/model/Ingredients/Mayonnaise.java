package model.Ingredients;

import model.Ingredient;

public class Mayonnaise extends Ingredient{
	
	/**
	 * Instantiates Mayo object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Mayonnaise(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	
}
