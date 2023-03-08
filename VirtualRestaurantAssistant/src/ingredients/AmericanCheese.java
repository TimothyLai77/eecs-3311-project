package ingredients;

import model.Ingredient;

public class AmericanCheese extends Ingredient{
	
	/**
	 * Instantiates americancheese object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public AmericanCheese(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	
}
