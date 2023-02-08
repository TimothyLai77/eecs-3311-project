package backend;
import java.util.ArrayList;
public class ChickenSandwichCreator extends SandwichCreator {
	private Inventory inventory;
	public ChickenSandwichCreator() {
		super.recipe = new ArrayList<String>();
		recipe.add("Bread");
		recipe.add("Chicken");
		this.inventory = Inventory.getIntsance();
		prepStation = new ArrayList<Ingredient>();
	}
	
	
	public Sandwich createSandwich() {
		// this calls the SanwichCreator's createSandwich method
		Inventory inventory = Inventory.getIntsance();
		for(String s : recipe) {
			if(!inventory.searchIngredient(s)) {
				refundIngredients();
				return null; // sandwich cannot be made with the current inventory stock
			}else {
				prepStation.add(inventory.getIngredient(s)); // add item to prep
			}
		}
		
		ChickenSandwich sandwich = new ChickenSandwich(prepStation);
		prepStation.clear();
		return sandwich;
	}
	
	/*
	 * Helper function to return ingredients back to inventory.
	 */
	private void refundIngredients() {
		for(Ingredient i : prepStation) {
			inventory.putIngredient(i.getName(), i);
		}
		prepStation.clear();
	}
}
