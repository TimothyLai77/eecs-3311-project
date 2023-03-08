package ingredients;

import model.Ingredient;

public class Cheddar extends Ingredient{
	
	/**
	 * Instantiates cheddar object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Cheddar(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	
}
