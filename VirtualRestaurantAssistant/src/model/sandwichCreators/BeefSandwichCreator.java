package model.sandwichCreators;
import java.util.ArrayList;
import java.util.List;

import model.Sandwich;
import model.Ingredients.Ingredient;
import model.Sandwiches.BeefSandwich;


/*
 * A Concrete creator class to make a BeefSandwich
 */

public class BeefSandwichCreator extends SandwichCreator {

	/*
	 * Constructor for the Beef creator/factory
	 */
	public BeefSandwichCreator() {
		super.recipe = new ArrayList<String>();
		recipe.add("Bread");
		recipe.add("Beef");
		prepStation = new ArrayList<Ingredient>();
	}
	
	
	/*
	 * Creation method for a beef sandwich. 
	 */
	public Sandwich createSandwich() {
		// call the parent's gather ingredient method
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null; // not all ingredients exist return null
		}

		// construct a new beef sandwich with the gathered ingredients
		BeefSandwich sandwich = new BeefSandwich(gatheredIngredients);
		return sandwich;
	}

}
