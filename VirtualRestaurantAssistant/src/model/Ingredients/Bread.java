package model.Ingredients;

/*
 * bread class
 */
public class Bread extends Ingredient {
	
	
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
