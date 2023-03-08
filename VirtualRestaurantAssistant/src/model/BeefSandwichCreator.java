package model;
import java.util.ArrayList;
import java.util.List;

import model.Sandwiches.BeefSandwich;
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
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null;
		}
		BeefSandwich sandwich = new BeefSandwich(gatheredIngredients);
		return sandwich;
	}

}
