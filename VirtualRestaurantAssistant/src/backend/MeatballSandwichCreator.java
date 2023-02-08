package backend;

import java.util.ArrayList;

public class MeatballSandwichCreator extends SandwichCreator {
	public MeatballSandwichCreator() {
		super.recipe = new ArrayList<String>();
		recipe.add("Bread");
		for(int i = 0; i < 6; i++){
			recipe.add("Meatball");
		}
		prepStation = new ArrayList<Ingredient>();
	}
	
	
	public Sandwich createSandwich() {
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null;
		}
		MeatballSandwich sandwich = new MeatballSandwich(gatheredIngredients);
		return sandwich;
	}

}
