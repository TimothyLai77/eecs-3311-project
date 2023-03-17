package model.sandwichCreators;

import java.util.ArrayList;
import java.util.List;

import model.Sandwich;
import model.Ingredients.Ingredient;
import model.Sandwiches.VeggiepattySandwich;

/*
 * Concrete creator class for a veggie patty sandwich
 */

public class VeggiepattySandwichCreator extends SandwichCreator{
	
	/*
	 * Constructor for the VeggiepattySandwich creator/factory
	 */
	public VeggiepattySandwichCreator() {
		super.recipe = new ArrayList<String>();
		// define a recipe
		recipe.add("Bread");
		recipe.add("Veggiepatty");
		prepStation = new ArrayList<Ingredient>();
	}

	/**
	 * Creation method for a Veggiepatty sandwich.
	 */
	@Override
	public Sandwich createSandwich() {
		// call the parents' method to gather ingredients
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null; // not all ingredients exist return null
		}
		// make new sandwich with gathered ingredients
		VeggiepattySandwich sandwich = new VeggiepattySandwich(gatheredIngredients);
		return sandwich;
	}

}
