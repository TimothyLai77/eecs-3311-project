package model;

import java.util.ArrayList;
import java.util.List;

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
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null;
		}
		VeggiepattySandwich sandwich = new VeggiepattySandwich(gatheredIngredients);
		return sandwich;
	}

}
