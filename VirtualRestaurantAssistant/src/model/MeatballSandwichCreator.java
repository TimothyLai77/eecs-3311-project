package model;

import java.util.ArrayList;
import java.util.List;

import model.Ingredients.Ingredient;
import model.Sandwiches.MeatballSandwich;
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
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null;
		}
		MeatballSandwich sandwich = new MeatballSandwich(gatheredIngredients);
		return sandwich;
	}

}
