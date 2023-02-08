package backend;
import java.util.ArrayList;
import java.util.List;
public class ChickenSandwichCreator extends SandwichCreator {

	public ChickenSandwichCreator() {
		super.recipe = new ArrayList<String>();
		recipe.add("Bread");
		recipe.add("Chicken");
		prepStation = new ArrayList<Ingredient>();
	}
	
	
	public Sandwich createSandwich() {
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null;
		}
		ChickenSandwich sandwich = new ChickenSandwich(gatheredIngredients);
		return sandwich;
	}

}
