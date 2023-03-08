package ingredients;

import model.Ingredient;

public class Tomato extends Ingredient{
    protected String name;
	protected double price;
	protected String type;
	
	/**
	 * Instantiates beef object with the given parameters 
	 * @param name
	 * @param price
	 * @param type
	 */
	public Tomato(String name, double price, String type) {
		super.name = name;
		super.price = price;
		super.type = type;
	}
	

}
