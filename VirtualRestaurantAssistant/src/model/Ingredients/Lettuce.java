package model.Ingredients;

public class Lettuce extends Ingredient{
	
	/**
	 * Instantiates Lettuce object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Lettuce(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	
}
