package backend;
import java.util.ArrayList;

public class BeefSandwichCreator extends SandwichCreator {
	private Inventory inventory;
	public BeefSandwichCreator() {
		super.recipe = new ArrayList<String>();
		recipe.add("Bread");
		recipe.add("Beef");
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
		
		BeefSandwich sandwich = new BeefSandwich(prepStation);
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
