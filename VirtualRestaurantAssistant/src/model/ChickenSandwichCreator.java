package model;
import java.util.ArrayList;
import java.util.List;
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
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null;
		}
		ChickenSandwich sandwich = new ChickenSandwich(gatheredIngredients);
		return sandwich;
	}

}
