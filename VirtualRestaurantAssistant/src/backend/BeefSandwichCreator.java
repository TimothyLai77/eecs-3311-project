package backend;
import java.util.ArrayList;
import java.util.List;
public class BeefSandwichCreator extends SandwichCreator {

	public BeefSandwichCreator() {
		super.recipe = new ArrayList<String>();
		recipe.add("Bread");
		recipe.add("Beef");
		prepStation = new ArrayList<Ingredient>();
	}
	
	
	public Sandwich createSandwich() {
		List<Ingredient> gatheredIngredients = super.gatherIngredients();
		if(gatheredIngredients == null){
			return null;
		}
		BeefSandwich sandwich = new BeefSandwich(gatheredIngredients);
		return sandwich;
	}

}
