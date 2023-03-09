package model.Ingredients;

/*
 * ketchup ingredient class
 */
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
