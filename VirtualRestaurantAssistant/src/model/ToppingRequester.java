package model;
import java.util.List;

import model.Ingredients.Ingredient;

import java.util.ArrayList;



/**
 * Class that requests the toppings from the inventory and applies the toppings to the base sandwich
 */
public class ToppingRequester {
	private List<Ingredient> requestedIngredients;
	private Sandwich baseSandwich;
	
	public ToppingRequester(Sandwich sandwich) {
		this.baseSandwich = sandwich;
		this.requestedIngredients = new ArrayList<Ingredient>();
	}
	
	/**
	 * Method Checks that toppings requested exist in the database. 
	 * @param requested, list of lists of each ingredient name requested
	 * @return returns true or false of all the ingredients exist in the inventory
	 */
	public boolean requestToppings(List<String> requested) {
		// check the db and determine if its possible to add the ingredients
		Inventory inventory = DbInventory.getInstance();
		for(String ingredientName : requested){
			// check if ingredient exists
			if(!inventory.searchIngredient(ingredientName)){
				return false; // not all ingredients exist
			}else{
				Ingredient i = inventory.getIngredient(ingredientName);
				requestedIngredients.add(i); // ingredient exists add to list of ingredients
			}
		}
		return true; // all ingredients exist in db, can proceed to add toppings
	}
	

	/**
	 * Method that applies the requested toppings to the base sandwich
	 * @return returns a sandwich with toppings.
	 */
	public Sandwich applyToppings() {
		// pass in the base sandwich to a constructor that 
		SandwichWithToppings sandwichWithToppings = new SandwichWithToppings(baseSandwich);
		
		// apply the toppings and return a sandwich with toppings
		sandwichWithToppings.addToppings(this.requestedIngredients);
		return sandwichWithToppings;
	}
}
