package model;

import java.util.ArrayList;
import java.util.List;

import model.Ingredients.Ingredient;
import model.Sandwiches.MeatballSandwich;

/*
 * Concrete creator for a meat ball sandwich
 */

public class MeatballSandwichCreator extends SandwichCreator {
	/*
	 * Constructor for the meatballsandwich creator/factory
	 */
	public MeatballSandwichCreator() {
		super.recipe = new ArrayList<String>();
		// define a recipe
		recipe.add("Bread");
		for(int i = 0; i < 6; i++){
			recipe.add("Meatball");
		}
		prepStation = new ArrayList<Ingredient>();
	}
	
	
	/*
	 * Creation method for a meatball sandwich. 
	 */
	public Sandwich createSandwich() {
		// gather ingredients with parent method
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null; // return null if not all ingredients can be gathered
		}
		// make sandwich and return
		MeatballSandwich sandwich = new MeatballSandwich(gatheredIngredients);
		return sandwich;
	}

}
