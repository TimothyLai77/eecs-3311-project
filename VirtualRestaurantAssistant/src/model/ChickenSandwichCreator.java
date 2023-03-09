package model;
import java.util.ArrayList;
import java.util.List;

import model.Ingredients.Ingredient;
import model.Sandwiches.ChickenSandwich;

/*
 * concrete creator class for chicken sandwich
 */
public class ChickenSandwichCreator extends SandwichCreator {
	
	/*
	 * Constructor for the Chicken Sandwich creator/factory
	 */
	public ChickenSandwichCreator() {
		super.recipe = new ArrayList<String>();
		recipe.add("Bread");
		recipe.add("Chicken");
		prepStation = new ArrayList<Ingredient>();
	}
	
	
	/*
	 * Creation method for a chicken sandwich. 
	 */
	public Sandwich createSandwich() {
		//gather ingredietsn from parent method
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null; // reutrn null if not all ingredietns could not be gathered
		}
		// make sandwich and return it
		ChickenSandwich sandwich = new ChickenSandwich(gatheredIngredients);
		return sandwich;
	}

}
