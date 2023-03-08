package ingredients;

import model.Ingredient;

public class Ketchup extends Ingredient{
	
	/**
	 * Instantiates ketchup object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Ketchup(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	
}
