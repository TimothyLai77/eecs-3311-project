package backend;

import java.util.List;


/**
 * Abstract class representing a sandwich.
 * Cannot be instantiated, meant to be extended by subclasses.
 */

public abstract class Sandwich {
	/** List of ingredients for the sandwich. */
	protected List<Ingredient> ingredientList;
	/** Name of the sandwich. */
	protected String sandwichName;
	/** Description of the sandwich. */
	protected String description;
	
	/**
     * Calculates and returns the total cost of the sandwich.
     * Adds up the price of each ingredient in the ingredientList.
     *
     * @return the total cost of the sandwich
     */

	public double getCost() {
	        double cost = 0;
	        for (Ingredient ingredient : ingredientList) {
	            cost += ingredient.getPrice();
	        }
	        return cost;
	  }
	
    /**
     * Returns the list of ingredients for the sandwich.
     *
     * @return the list of ingredients for the sandwich
     */
	 
	 public List<Ingredient> getIngredientList() {
	        return ingredientList;
	 }
	 
	    /**
	     * Returns the name of the sandwich.
	     *
	     * @return the name of the sandwich
	     */
	
	 public String getName() {
	        return sandwichName;
	 } 
	 /**
	     * Returns the description of the sandwich.
	     *
	     * @return the description of the sandwich
	     */

	 public String getDescription() {
	        return description;
	    }
}
