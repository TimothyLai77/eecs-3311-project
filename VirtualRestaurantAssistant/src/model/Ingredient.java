package model;

public abstract class Ingredient {
	protected String name;
	protected double price;
	protected String type;
	
	
	/**
	 * @return:- This method returns the price of beef objects
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * @return:- This method returns the name of ingredient
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return:- This method returns the type of the ingredient
	 */
	public String getType() {
		return this.type;
	}
}
